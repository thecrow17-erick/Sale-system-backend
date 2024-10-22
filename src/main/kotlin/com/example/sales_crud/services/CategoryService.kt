package com.example.sales_crud.services

import com.example.sales_crud.dto.category.CreateCategoryDto
import com.example.sales_crud.error.exception.BadRequestException
import com.example.sales_crud.error.exception.NotFoundException
import com.example.sales_crud.model.Category
import com.example.sales_crud.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CategoryService(
    @Autowired
    val categoryRepository: CategoryRepository
) {
    fun findAllCategories(search: String?, page: Int, size: Int): Page<Category> {
        val pageable = PageRequest.of(page,size);
        return if(search.isNullOrBlank()) {
            this.categoryRepository.findAllBy(pageable);
        }else {
            this.categoryRepository.searchByNameOrDescription(search, pageable);
        }
    }
    fun findCategoryId(id: Long): Category {
        val findIdCategory = this.categoryRepository.findCategoryById(id);
        if(findIdCategory.isEmpty)
            throw NotFoundException("Categoria no encontrada");
        return findIdCategory.get();
    }
    fun createCategory(createCategoryDto: CreateCategoryDto): Category{
        val findNameCategory = this.categoryRepository.findCategoryByName(createCategoryDto.name);
        if(findNameCategory.size > 0)
            throw BadRequestException("Ingrese otro nombre, este ya esta en uso")
        val createCategory = Category(
            name = createCategoryDto.name,
            description = createCategoryDto.description
        );
        return categoryRepository.save(createCategory);
    }
    fun updateCategory(id: Long, createCategoryDto: CreateCategoryDto): Category {
        var findIdCategory = this.findCategoryId(id);
        val findName = this.categoryRepository.findCategoryByName(createCategoryDto.name);
        if(findName.size > 0)
            throw BadRequestException("Ingrese nombre, este ya esta en uso");
        findIdCategory.name = createCategoryDto.name;
        findIdCategory.description = createCategoryDto.description
        return this.categoryRepository.save(findIdCategory);
    }
    fun deleteCategory(id: Long): Category {
        var findId = this.findCategoryId(id);
        findId.status = !findId.status;
        return this.categoryRepository.save(findId);
    }
}