

package com.kiyanitsa.test.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime
import javax.persistence.*


@Entity
class RegionMerchant(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @Column(unique = true)
        @JsonProperty("region")
        var region:String?=null
)

@Entity
class Merchant {
    @Id
    @JsonProperty("id")
    val id: Long = 0

    @JsonProperty("goto_cookie_lifetime")
    private var goto_cookie_lifetime: Int = 0
    @JsonProperty("rating")
    private var rating: Float = 0.0f
    @JsonProperty("image")
    private var image: String? = null
    @JsonProperty("currency")
    private  var currency: Currency? = null

    @JsonProperty("activation_date")
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    private  var activation_date: LocalDateTime? = null

    @JsonProperty("connection_status")
    private  var connection_status: Status? = null
    @JsonProperty("gotolink")
    private  var gotolink: String? = null
    @JsonProperty("site_url")
    private  var site_url: String? = null


    @OneToMany(fetch = FetchType.LAZY,  targetEntity = RegionMerchant::class, cascade = [CascadeType.ALL])
    @JoinColumn(name="region_id")
//    @ElementCollection(targetClass = RegionMerchant::class)
//    @CollectionTable(name = "merchant_regions", joinColumns = [JoinColumn(name = "merchant_id")])
    @JsonProperty("regions")
    var  regions: Set<RegionMerchant>? = null

    @JsonProperty("avg_hold_time")
    private  var avgHoldTime: Int? = 0

    @JsonProperty("status")
    private  var status: Status? = null

    @JsonProperty("description")
    @Column(length = 4096)
    private  var description: String? = null

    @JsonProperty("modified_date")
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    private  var modified_date: LocalDateTime? = null

    @OrderColumn
    @OneToMany(fetch = FetchType.LAZY,  targetEntity = Сategories::class, cascade = [CascadeType.ALL])
    @JoinColumn(name = "categoryId")
    @JsonProperty("categories")
    var categories: Array<Сategories>? =  arrayOf()

    @JsonProperty("name")
    private var name:String?=null
}