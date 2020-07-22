package com.kiyanitsa.test.service

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kiyanitsa.test.model.Advertiser
import com.kiyanitsa.test.model.Advertisers
import com.kiyanitsa.test.model.Link
import com.kiyanitsa.test.model.Links
import com.kiyanitsa.test.repo.LinkRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.IOException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path

@Service
class LinkService {
    @Autowired
    private lateinit var linkRepo: LinkRepo

    fun parseXMLFromURL(path:String):List<Link>? {
        val xmlMapper = XmlMapper()
        try {
            val data = URL(path).readText()
            val convertData= xmlMapper.readValue(data, Links::class.java)
            return convertData.links
        }catch (e: IOException){
            println("Что-то пошло не так: $e")
        }
        return null
    }
    fun parseXMLFromFile(path:String):List<Link>? {
        val xmlMapper = XmlMapper()
        try {
            val data = Files.readString(Path.of(path))
            val convertData = xmlMapper.readValue(data, Links::class.java)
            return convertData.links
        }catch (e: IOException){
            println("Что-то пошло не так: $e")
        }
        return null
    }

    fun list(): Iterable<Link>? {
        return linkRepo.findAll()
    }

    fun saveAll(link: List<Link>?): Iterable<Link>? {
        return linkRepo.saveAll(link!!)
    }

}