package com.kiyanitsa.test.repo

import com.kiyanitsa.test.model.Advertiser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface AdvertiserRepo: JpaRepository<Advertiser, Long> {
}