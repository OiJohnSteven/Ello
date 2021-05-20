package com.intellect.ello.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddOrganizationEntity (
    @SerialName("message")
    var message: String
)