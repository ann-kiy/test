package com.kiyanitsa.test.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime
import javax.persistence.*


@Entity
class TypeOffer{
    @Id
    @JsonProperty("id")
    var id:Long?=null

    @JsonProperty("name")
    private var name:String?=null
}

@Entity
class CategoryOffer{
    @Id
    @JsonProperty("id")
    var id:Long?=null

    @JsonProperty("name")
    private var name:String?=null
}

@Entity
class Offer {
    @Id
    @JsonProperty("id")
    @GeneratedValue( strategy = GenerationType.AUTO )
    private var id: Long?=null

    @JsonProperty("status")
    private var status:Status?=null

    @JsonProperty("rating")
    private var rating:Float?=0.0f

    @JsonProperty("date_start")
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    private  var dateStart: LocalDateTime? = null

    @ManyToOne
    @JsonProperty("campaign")
    var campaign: Merchant?=null

    @JsonProperty("short_name")
    private var shortName: String?=null

    @JsonProperty("exclusive")
    private var exclusive: Boolean?=null

    @JsonProperty("name")
    private var name: String?=null

    @JsonProperty("date_end")
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    private  var dateEnd: LocalDateTime? = null

    @JsonProperty("promocode")
    private var promocode: String?=null

    @ElementCollection(targetClass = Region::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "offer_region", joinColumns = [JoinColumn(name = "offer_id")])
    @Enumerated(EnumType.STRING)
    @JsonProperty("regions")
    private val  regions: Set<Region>? = null

    @JsonProperty("discount")
    private var discount: String?=null

    @OrderColumn
    @OneToMany(fetch = FetchType.LAZY,  targetEntity = TypeOffer::class, cascade = [CascadeType.ALL])
    @JoinColumn(name = "typeId")
    @JsonProperty("types")
    var types: Array<TypeOffer>? = arrayOf()

    @JsonProperty("image")
    private var image: String?=null

    @JsonProperty("frameset_link")
    private var frameset_link: String?=null

    @JsonProperty("goto_link")
    private var goto_link: String?=null

    @JsonProperty("species")
    private var species: String?=null

    @OrderColumn
    @OneToMany(fetch = FetchType.LAZY,  targetEntity = CategoryOffer::class, cascade = [CascadeType.ALL])
    @JoinColumn(name = "categoryId")
    @JsonProperty("categories")
    var categories: Array<CategoryOffer>? = arrayOf()

    @JsonProperty("description")
    private var description: String?=null
}