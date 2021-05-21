package com.intellect.ello.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddServiceEntity (
    @SerialName("status")
    var status:Boolean

)
