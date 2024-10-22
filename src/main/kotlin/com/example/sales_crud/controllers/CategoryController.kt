package com.example.sales_crud.controllers

import com.example.sales_crud.dto.category.CategoryResponse
import com.example.sales_crud.dto.category.CreateCategoryDto
import com.example.sales_crud.dto.response.ApiResponse
import com.example.sales_crud.model.Category
import com.example.sales_crud.services.CategoryService
import jakarta.validation.Valid
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

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getCategoryId(
        @PathVariable id: Long
    ): ApiResponse<CategoryResponse> {
        return ApiResponse(
            statusCode = HttpStatus.OK.value(),
            message = "Ver la categoria",
            data = CategoryResponse(this.categoryService.findCategoryId(id))
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCategory(
        @Valid @RequestBody createCategoryDto: CreateCategoryDto
    ): ApiResponse<CategoryResponse> {
        return ApiResponse(
            statusCode = HttpStatus.CREATED.value(),
            message = "Categoria creada",
            data = CategoryResponse(this.categoryService.createCategory(createCategoryDto))
        )
    }
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun updateCategory(
        @PathVariable id: Long,
        @Valid @RequestBody createCategoryDto: CreateCategoryDto
    ): ApiResponse<CategoryResponse> {
        return ApiResponse(
            statusCode =  HttpStatus.ACCEPTED.value(),
            message = "Categoria actualizada",
            data = CategoryResponse(this.categoryService.updateCategory(id, createCategoryDto))
        )
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun deleteCategory(
        @PathVariable id: Long
    ): ApiResponse<CategoryResponse> {
        return ApiResponse(
            statusCode = HttpStatus.ACCEPTED.value(),
            message = "Categoria Eliminada",
            data = CategoryResponse(this.categoryService.deleteCategory(id))
        );
    }
}