package com.intellect.ello.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServiceEntity (
    @SerialName("id")
    var id:Int,
    @SerialName("name")
    var name:String,
    @SerialName("status")
    var status:String
)