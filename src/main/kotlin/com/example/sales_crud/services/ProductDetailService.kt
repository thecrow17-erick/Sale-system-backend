package com.example.sales_crud.services

import com.example.sales_crud.model.Product_Detail
import com.example.sales_crud.repositories.ProductDetailRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ProductDetailService(
    @Autowired private val productDetailRepository: ProductDetailRepository
) {

    fun findProductsSales(search: String?, page: Int, size: Int): Page<Product_Detail> {
        val pageable = PageRequest.of(page,size);
        return if(search.isNullOrBlank()){
            this.productDetailRepository.findAllBy(pageable);
        }else{
            this.productDetailRepository.searchByNameOrCode(search,pageable);
        }
    }

}