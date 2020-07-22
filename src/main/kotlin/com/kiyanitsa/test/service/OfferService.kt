package com.kiyanitsa.test.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kiyanitsa.test.model.Merchant
import com.kiyanitsa.test.model.Offer
import com.kiyanitsa.test.model.Result
import com.kiyanitsa.test.repo.CategoryOfferRepo
import com.kiyanitsa.test.repo.MerchantRepo
import com.kiyanitsa.test.repo.OfferRepo
import com.kiyanitsa.test.repo.TypeOfferRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.IOException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path

@Service
class OfferService {
    @Autowired
    lateinit var offerRepo: OfferRepo
    @Autowired
    lateinit var merchantRepo: MerchantRepo
    @Autowired
    lateinit var typeOfferRepo:TypeOfferRepo
    @Autowired
    lateinit var categoryOfferRepo:CategoryOfferRepo

    fun parseJSONFromURL(path:String): List<Offer>? {
        val parser = jacksonObjectMapper()
        try {
            val data = URL(path).readText()
            val convertData: Result<Offer> = parser.readValue(data)
            return convertData.results
        }catch (e: IOException){
            println("Что-то пошло не так: $e")
        }
        return null
    }

    fun parseJSONFromFile(path:String): List<Offer>? {
        val parser = jacksonObjectMapper()
        try {
            val data = Files.readString(Path.of(path))
            val convertData: Result<Offer> = parser.readValue(data)
            return convertData.results
        }catch (e: IOException){
            println("Что-то пошло не так: $e")
        }
        return null
    }
    fun list(): Iterable<Offer>? {
        return offerRepo.findAll()
    }

    fun saveAll(offer: List<Offer>) {
        offer.forEach { i->
            if(merchantRepo.existsById(i.campaign!!.id)){
                i.types?.forEach { type->
                    if(!typeOfferRepo.existsById(type.id!!))
                        typeOfferRepo.save(type)
                }
                i.categories?.forEach { category->
                    if(!categoryOfferRepo.existsById(category.id!!))
                        categoryOfferRepo.save(category)
                }
                offerRepo.save(i)
            }
        }
//        return offerRepo!!.saveAll(offer)
    }
    fun findByCompaignName(name:String): List<Offer>{
        return offerRepo.findAllByCampaign_Name(name)
    }
}