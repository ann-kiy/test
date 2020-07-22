package com.kiyanitsa.test.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import lombok.Data
import javax.persistence.*

class Advertisers {
    @JsonProperty("total-matched")
    private var totalMatched:String?=null

    @JsonProperty("records-returned")
    private var recordsReturned:String?=null

    @JsonProperty("page-number")
    private var pageNumber:String?=null

    @JacksonXmlElementWrapper(localName = "advertisers")
    val advertisers: List<Advertiser>? = null
}

@Embeddable
class PrimaryCategory{
    @JsonProperty("parent")
    private var parent:String?=null

    @JsonProperty("child")
    private var child:String?=null
}
@Entity
class Advertiser {
    @Id
    @JsonProperty("advertiser-id")
    private var id:Long?=0

    @JsonProperty("account-status")
    private var accountStatus:String?=null

    @JsonProperty("seven-day-epc")
    private var sevenDayEpc:String?=null

    @JsonProperty("three-month-epc")
    private var threeMonthEpc:String?=null

    @JsonProperty("language")
    private var language:String?=null

    @JsonProperty("advertiser-name")
    private var name: String? =null

    @JsonProperty("program-url")
    private var programUrl: String? =null

    @JsonProperty("relationship-status")
    private var relationshipStatus:String? =null

    @JsonProperty("network-rank")
    private var networkRank:String? =null

    @Embedded
    @JsonProperty("primary-category")
    var primaryCategory: PrimaryCategory?=null



}