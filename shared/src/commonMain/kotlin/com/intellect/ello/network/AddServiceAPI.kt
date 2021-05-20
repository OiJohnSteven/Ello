package com.intellect.ello.network


import com.intellect.ello.model.AddServiceEntity
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AddServiceAPI {

    val client = HttpClient()
    {
        install(JsonFeature){
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    companion object{
        val AddServiceURL = "https://techsoln.000webhostapp.com/ello/insertService.php"
    }

    suspend fun addService(name:String): AddServiceEntity{

        var x:AddServiceEntity ?= null

        try {
            x = client.post(AddServiceURL){
                parameter("name", name)
                parameter("status", "unfeatured")
            }

        } catch (e: NoTransformationFoundException){
            val y:String = client.post(AddServiceURL){
                parameter("name", name)
                parameter("status", "unfeatured")
            }
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            x = json.decodeFromString(y)
        }

        return x!!
    }
}