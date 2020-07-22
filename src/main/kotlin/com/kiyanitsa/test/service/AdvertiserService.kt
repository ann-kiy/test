package com.kiyanitsa.test.service

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kiyanitsa.test.model.Advertiser
import com.kiyanitsa.test.model.Advertisers
import com.kiyanitsa.test.model.Result
import com.kiyanitsa.test.repo.AdvertiserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.IOException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path

@Service
class AdvertiserService {
    @Autowired
    private lateinit var advertiserRepo: AdvertiserRepo

    fun parseXMLFromURL(path:String):List<Advertiser>? {
        val xmlMapper = XmlMapper()
        try {
            val data = URL(path).readText()
            val convertData= xmlMapper.readValue(data, Advertisers::class.java)
            return convertData.advertisers
        }catch (e: IOException){
            println("Что-то пошло не так: $e")
        }
        return null
    }
    fun parseXMLFromFile(path:String):List<Advertiser>? {
        val xmlMapper = XmlMapper()
        try {
            val data = Files.readString(Path.of(path))
            val convertData = xmlMapper.readValue(data, Advertisers::class.java)
            return convertData.advertisers
        }catch (e: IOException){
            println("Что-то пошло не так: $e")
        }
        return null
    }

    fun list(): Iterable<Advertiser>? {
        return advertiserRepo.findAll()
    }

    fun saveAll(advertisers: List<Advertiser>?): Iterable<Advertiser>? {
        return advertiserRepo.saveAll(advertisers!!)
    }
}