package com.kiyanitsa.test.repo

import com.kiyanitsa.test.model.RegionMerchant
import org.springframework.data.jpa.repository.JpaRepository

interface RegionsRepo: JpaRepository<RegionMerchant, Long> {
    fun findByRegion(region: String?): RegionMerchant
    fun existsByRegion(region: String?):Boolean
}