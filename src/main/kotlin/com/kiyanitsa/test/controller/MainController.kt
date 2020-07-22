package com.kiyanitsa.test.controller


import com.kiyanitsa.test.model.Advertiser
import com.kiyanitsa.test.model.Link
import com.kiyanitsa.test.model.Merchant
import com.kiyanitsa.test.model.Offer
import com.kiyanitsa.test.service.AdvertiserService
import com.kiyanitsa.test.service.LinkService
import com.kiyanitsa.test.service.MerchantService
import com.kiyanitsa.test.service.OfferService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.io.IOException


@RestController
class MainController {
    @Autowired
    lateinit var merchantService: MerchantService
    @Autowired
    private lateinit var advertiserService: AdvertiserService
    @Autowired
    private lateinit var offerService: OfferService
    @Autowired
    private lateinit var linkService: LinkService

    @GetMapping("/merchantp_page")
    fun paginMerchantByName(@RequestParam("name") name: String,
                        @RequestParam(value = "page", defaultValue = "0") page: Int,
                      @RequestParam(value = "size",  defaultValue = "5") size:Int): List<Merchant>{
        return merchantService.findByNamePaginated(name, page, size)
    }

    @GetMapping("/full")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String): String {
        try {
            var listMerchant = merchantService.saveAll(merchantService.parseJSONFromFile("C:/Users/admin/Desktop/test/src/main/resources/files/ad-merchant.json")!!)
            var listOffer = offerService.saveAll(offerService.parseJSONFromURL("https://s3.eu-central-1.amazonaws.com/jobs.softomate.com/testdata/ad-offers.json")!!)

            var listAdvert = advertiserService.saveAll(advertiserService.parseXMLFromFile("C:/Users/admin/Desktop/test/src/main/resources/files/cj-merchant.xml")!!)
            var listLink = linkService.saveAll(linkService.parseXMLFromFile("C:\\Users\\admin\\Desktop\\test\\src\\main\\resources\\files\\cj-offers.xml")!!)
            return " full!"
        }catch (e: IOException){
            return " error full("
        }//
        }
    @GetMapping("/advertisers")
    fun getAdvertiser(): Iterable<Advertiser>? {
        return advertiserService.list()
    }

    @GetMapping("/link")
    fun getLinks(): Iterable<Link>? {
        return linkService.list()
    }
    @GetMapping("/offer")
    fun getOffer(): Iterable<Offer>? {
        return offerService.list()
    }
    @GetMapping("/merchant")
    fun getMerchant(): Iterable<Merchant>? {
        return merchantService.list()
    }
    @GetMapping("/offer_by")
    fun getOfferByCompaign(@RequestParam("name") name: String): List<Offer>? {
        return offerService.findByCompaignName(name)
    }
    @PutMapping("/merchant/{id}")
    fun update(@PathVariable("id") merchantFromDB: Merchant, merchant: Merchant){
        merchantService.update(merchantFromDB, merchant)
    }


}
