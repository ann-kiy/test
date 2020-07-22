package com.kiyanitsa.test.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kiyanitsa.test.model.Advertiser
import com.kiyanitsa.test.model.Merchant
import com.kiyanitsa.test.model.Result
import com.kiyanitsa.test.model.Status
import com.kiyanitsa.test.repo.CategoriesRepo
import com.kiyanitsa.test.repo.MerchantRepo
import com.kiyanitsa.test.repo.RegionsRepo
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.io.IOException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path

@Service
class MerchantService {
    @Autowired
    lateinit var merchantRepo: MerchantRepo

    @Autowired
    lateinit var categoriesRepo: CategoriesRepo

    @Autowired
    lateinit var regionsRepo:RegionsRepo
    fun parseJSONFromURL(path:String): List<Merchant>? {
        val parser = jacksonObjectMapper()
        try {
            val data = URL(path).readText()
            val convertData: Result<Merchant> = parser.readValue(data)
            return convertData.results
        }catch (e: IOException){
            println("Что-то пошло не так: $e")
        }
        return null
    }
    fun parseJSONFromFile(path:String): List<Merchant>? {
        val parser = jacksonObjectMapper()
        try {
            val data = Files.readString(Path.of(path))
            val convertData: Result<Merchant> = parser.readValue(data)
            return convertData.results
        }catch (e: IOException){
            println("Что-то пошло не так: $e")
        }
        return null
    }
    fun list():Iterable<Merchant>?{
        return merchantRepo.findAll()
    }

    fun saveAll(merchants: List<Merchant>): Iterable<Merchant>? {
        merchants.forEach { data ->
            data.categories?.forEach { category->
                if(categoriesRepo.findById(category.id!!).isEmpty)
                    categoriesRepo.save(category)
            }
            data.regions!!.forEach { j->
                if(!regionsRepo.existsByRegion(j.region))
                    regionsRepo.save(j)
                else
                    j.id=regionsRepo.findByRegion(j.region).id

            }
        }
        return merchantRepo.saveAll(merchants)
    }
    fun findByNamePaginated(name:String, page:Int, size: Int): List<Merchant> {
        var page: Pageable = PageRequest.of(page,size)
        return merchantRepo.findAllByName(name, page)
    }
    fun update(merchantFromDB:Merchant, merchantNew:Merchant): Merchant? {
        if(merchantRepo.existsById(merchantFromDB.id)){
            BeanUtils.copyProperties(merchantNew,merchantFromDB,"id")
            return merchantRepo.save(merchantFromDB)
        }
        return null
    }
}