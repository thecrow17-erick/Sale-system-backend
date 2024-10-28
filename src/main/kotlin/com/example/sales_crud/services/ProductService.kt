package com.example.sales_crud.services

import com.example.sales_crud.dto.product.CreateProductDto
import com.example.sales_crud.error.exception.BadRequestException
import com.example.sales_crud.error.exception.NotFoundException
import com.example.sales_crud.model.Product
import com.example.sales_crud.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class ProductService(
    @Autowired
    private val productRepository: ProductRepository,
    @Autowired
    private val categoryService: CategoryService,
    @Autowired
    private val azureServices: AzureServices
) {
    fun findAllProduct(search: String?,page: Int, size: Int): Page<Product>{
        val pageable = PageRequest.of(page,size);
        return if(search.isNullOrBlank()){
            this.productRepository.findAllBy(pageable);
        }else {
            this.productRepository.searchByNameOrCode(search,pageable);
        }
    }
    fun findProductId(id: Long): Product {
        val findProduct = this.productRepository.findProductById(id);
        if(findProduct.isEmpty)
            throw NotFoundException("El producto no existe");
        return findProduct.get();
    }
    fun createProduct(createProductDto: CreateProductDto, image: MultipartFile): Product {
        //saco la extension del archivo
        val fileContenType = image.originalFilename!!.split(".").last();
        //pregunto si es valido la extension en jpg,png,jpeg
        if(!this.isImgOrPngOrJpeg(fileContenType))
            throw BadRequestException("Ingrese un archivo tipo jgp, jpeg o png");
        //nose pueden repetir mismos codigos o mismo nombres
        val findProduct = this.productRepository.findProductByCodeOrName(createProductDto.code, createProductDto.name);
        if(findProduct.size > 0)
            throw BadRequestException("Ingrese otro codigo o nombre para el producto");
        //busco la categoria del producto
        val findCategory = this.categoryService.findCategoryId(createProductDto.category_id);
        //el nombre que ira en la bd y en azure
        val nameImg = UUID.randomUUID().toString()+"."+fileContenType;
        //ahora si creo el producto
        val createProduct = Product(
            name = createProductDto.name,
            description = createProductDto.description,
            code = createProductDto.code,
            category = findCategory,
            price = createProductDto.price,
            img_url = nameImg
        );
        //ingreso la imagen del producto a azure
        this.azureServices.uploadFile("sales",nameImg,image);
        //salvo el producto creado
        return productRepository.save(createProduct);
    }
    fun updateProduct(id: Long,createProductDto: CreateProductDto, image: MultipartFile?): Product {
        val findProductId = this.findProductId(id);
        val findProduct = this.productRepository.findProductByCodeOrNameAndIdNot(
            code = createProductDto.code,
            name = createProductDto.name,
            id = id
        );
        println(findProduct)
        if(findProduct.size > 0)
            throw BadRequestException("Ingrese otro codigo o nombre para el producto");

        if(findProductId.category.id != createProductDto.category_id){
            val findCategory = this.categoryService.findCategoryId(createProductDto.category_id);
            findProductId.category = findCategory;
        }

        findProductId.name = createProductDto.name;
        findProductId.code = createProductDto.code;
        findProductId.price = createProductDto.price;
        findProductId.description = createProductDto.description;
        if(image != null && !image.isEmpty){
            val fileContenType = image.originalFilename!!.split(".").last();
            if(!this.isImgOrPngOrJpeg(fileContenType))
                throw BadRequestException("Ingrese una archivo de tipo jpg,png o jpeg");
            this.azureServices.deleteFile("sales",findProductId.img_url);
            val nameImg = UUID.randomUUID().toString()+"."+fileContenType;
            findProductId.img_url = nameImg;
            this.azureServices.uploadFile("sales",nameImg,image);
        }
        return this.productRepository.save(findProductId) ;
    }
    fun isImgOrPngOrJpeg(contentType: String): Boolean {
        val validations = listOf("png","jpeg","jpg");
        return validations.contains(contentType);
    }
    fun deleteProduct(id: Long): Product {
        val findProductId = this.findProductId(id);
        findProductId.status = !findProductId.status;
        return this.productRepository.save(findProductId);
    }
}