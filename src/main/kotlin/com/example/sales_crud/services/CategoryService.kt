package com.example.sales_crud.services

import com.example.sales_crud.model.Category
import com.example.sales_crud.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class CategoryService(
    @Autowired
    val categoryRepository: CategoryRepository
) {
    fun findAllCategories(search: String?, page: Int, size: Int): Page<Category> {
        val pageable = PageRequest.of(page,size);
        return if(search.isNullOrBlank()) {
            println(categoryRepository.findAllBy(pageable));
            categoryRepository.findAllBy(pageable);
        }else {
            println(categoryRepository.searchByNameOrDescription(search, pageable).content)
            categoryRepository.searchByNameOrDescription(search, pageable);
        }
    }
}