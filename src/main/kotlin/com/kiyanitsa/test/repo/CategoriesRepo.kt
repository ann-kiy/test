package com.kiyanitsa.test.repo

import com.kiyanitsa.test.model.Merchant
import com.kiyanitsa.test.model.Сategories
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository


interface CategoriesRepo: JpaRepository<Сategories, Long> {

}