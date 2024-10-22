package com.example.sales_crud.controllers

import com.example.sales_crud.dto.response.ApiResponse
import com.example.sales_crud.model.Product
import com.example.sales_crud.services.ProductService
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
        @RequestBody file: MultipartFile,
    ): String {
        println("size: "+file.size)
        println("name: "+file.name)
        println("type: "+file.contentType)
        println("buffer: "+file.bytes)
        println("original name: "+file.originalFilename)
        return file.name;
    }
}