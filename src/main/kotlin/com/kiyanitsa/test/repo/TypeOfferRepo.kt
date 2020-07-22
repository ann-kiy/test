package com.kiyanitsa.test.repo

import com.kiyanitsa.test.model.CategoryOffer
import com.kiyanitsa.test.model.TypeOffer
import org.springframework.data.jpa.repository.JpaRepository

interface TypeOfferRepo: JpaRepository<TypeOffer, Long> {
}