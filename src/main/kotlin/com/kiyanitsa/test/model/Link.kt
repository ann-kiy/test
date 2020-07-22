package com.kiyanitsa.test.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.*

class Links {
    @JsonProperty("total-matched")
    private var totalMatched:String?=null

    @JsonProperty("records-returned")
    private var recordsReturned:String?=null

    @JsonProperty("page-number")
    private var pageNumber:String?=null

    @JacksonXmlElementWrapper(localName = "links")
    val links: List<Link>? = null
}

@Entity
class Link {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private var id: Long?=null

    @JsonProperty("advertiser-id")
    private var idAdvertiser:Long?=null

    @JsonProperty("advertiser-name")
    private var advertiserName:String?=null

    @JsonProperty("category")
    private var category: String? = null

    @JsonProperty("language")
    private var language: String?=null

    @JsonProperty("description")
    private var description: String?= null

    @JsonProperty("destination")
    private var destination: String?=null

    @JsonProperty("promotion-end-date")
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private  var promotionEndDate: LocalDateTime? = null

    @JsonProperty("promotion-start-date")
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private  var promotionStartDate: LocalDateTime? = null

    @JsonProperty("promotion-type")
    private var promotionType: String?= null

    @JsonProperty("coupon-code")
    private var couponCode: String?=null

    @JsonProperty("relationship-status")
    private var relationshipStatus: String?=null

    @JsonProperty("sale-commission")
    private var saleCommission: String?= null

    @JsonProperty("seven-day-epc")
    private var sevenDayEpc: Float = 0.0f

    @JsonProperty("three-month-epc")
    private var threeMonthEpc: Float = 0.0f

    @JsonProperty("clickUrl")
    private var clickUrl: String?=null
}