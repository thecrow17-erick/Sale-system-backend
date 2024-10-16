package com.example.sales_crud.controllers

import com.example.sales_crud.dto.response.ApiResponse
import com.example.sales_crud.model.Category
import com.example.sales_crud.services.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/category")
class CategoryController(
    @Autowired
    private val categoryService: CategoryService
) {
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    fun getCategories(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "search") search: String?
    ): ApiResponse<Page<Category>> {
        return ApiResponse(
            message = "Lista de las categorias",
            statusCode = HttpStatus.OK.value(),
            data = categoryService.findAllCategories(search, page,size)
        );
    }
}