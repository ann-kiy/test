package com.kiyanitsa.test.repo

import com.kiyanitsa.test.model.Merchant
import com.kiyanitsa.test.model.Status
import org.springframework.data.domain.Pageable

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface MerchantRepo: JpaRepository<Merchant, Long> {
    fun findAllByName(name: String, page: Pageable):List<Merchant>

}