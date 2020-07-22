package com.kiyanitsa.test.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import lombok.Data
import javax.persistence.*

@Entity
class Сategories {

    @Id
    @JsonProperty("id")
    var id:Long?=0

    @JsonProperty("language")
    private val language: String?=null

    @ManyToOne
    @JsonProperty("parent")
    private var parent: Сategories?=null

    @JsonProperty("name")
    private var name: String?=null
}