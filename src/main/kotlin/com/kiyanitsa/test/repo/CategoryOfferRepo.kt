package com.kiyanitsa.test.repo

import com.kiyanitsa.test.model.CategoryOffer
import com.kiyanitsa.test.model.Link
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryOfferRepo: JpaRepository<CategoryOffer, Long> {
}