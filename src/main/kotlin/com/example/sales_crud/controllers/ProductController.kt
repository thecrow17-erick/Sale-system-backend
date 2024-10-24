package com.example.sales_crud.controllers

import com.example.sales_crud.dto.product.CreateProductDto
import com.example.sales_crud.dto.product.ProductResponse
import com.example.sales_crud.dto.response.ApiResponse
import com.example.sales_crud.model.Product
import com.example.sales_crud.services.ProductService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/product")
class ProductController(
    private val productService: ProductService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAllProduct(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam search: String?
    ): ApiResponse<Page<Product>> {
        return ApiResponse(
            statusCode = HttpStatus.OK.value(),
            message = "Todos los productos",
            data = this.productService.findAllProduct(search,page,size)
        )
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(
        @Valid @RequestPart(value = "body") body: CreateProductDto,
        @RequestPart(value = "image") image: MultipartFile
    ): ApiResponse<ProductResponse> {
        return ApiResponse(
            statusCode = HttpStatus.CREATED.value(),
            message = "Producto creado",
            ProductResponse(this.productService.createProduct(body,image))
        )
    }
}