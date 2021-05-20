package com.intellect.ello.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrganizationEntity (
    @SerialName("id")
    var id:Int,
    @SerialName("sid")
    var sid:Int,
    @SerialName("name")
    var name:String,
    @SerialName("location")
    var location:String,
    @SerialName("contact")
    var contact:String,
    @SerialName("email")
    var email:String,
    @SerialName("description")
    var description:String,
    @SerialName("status")
    var status:String,
)