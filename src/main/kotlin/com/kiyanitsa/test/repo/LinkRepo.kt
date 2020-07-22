package com.kiyanitsa.test.repo

import com.kiyanitsa.test.model.Link
import com.kiyanitsa.test.model.Merchant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface LinkRepo: JpaRepository<Link, Long> {
}