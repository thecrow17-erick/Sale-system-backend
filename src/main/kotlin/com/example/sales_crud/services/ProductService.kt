package com.example.sales_crud.services

import com.example.sales_crud.model.Product
import com.example.sales_crud.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ProductService(
    @Autowired
    private val productRepository: ProductRepository
) {
    fun findAllProduct(search: String?,page: Int, size: Int): Page<Product>{
        val pageable = PageRequest.of(page,size);
        return if(search.isNullOrBlank()){
            this.productRepository.findAllBy(pageable);
        }else {
            this.productRepository.searchByNameOrCode(search,pageable);
        }
    }
}