package com.kiyanitsa.test.repo

import com.kiyanitsa.test.model.Merchant
import com.kiyanitsa.test.model.Offer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface OfferRepo: JpaRepository<Offer, Long> {
    fun findAllByCampaign_Name(name:String):List<Offer>
}