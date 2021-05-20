package com.intellect.ello.network

//import io.ktor.client.*
//import io.ktor.client.features.json.*
//import io.ktor.client.features.json.serializer.*

import com.intellect.ello.model.ServiceEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ServiceAPI {

    val client = HttpClient{
        install(JsonFeature){
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
            accept(ContentType.Application.Json)
        }
    }

    companion object {
        private const val SERVICES_ENDPOINT = "https://techsoln.000webhostapp.com/ello/getService.php"
        private const val SEARC_SERVICES_ENDPOINT = "https://techsoln.000webhostapp.com/ello/searchService.php"

    }

    suspend fun getService() : List<ServiceEntity> {
        var serve:List<ServiceEntity> ?= null
        try {
            serve = client.get(SERVICES_ENDPOINT)
        } catch (e: NoTransformationFoundException) {
            val mealsString: String = client.get(SERVICES_ENDPOINT)
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            }
            serve = json.decodeFromString(mealsString)
        }

        return serve!!

    }

    suspend fun searchService(name:String): List<ServiceEntity> {
        var res:List<ServiceEntity> ?= null
        try {
            res = client.get(SEARC_SERVICES_ENDPOINT){

                parameter("name", name)
            }
        }catch (e: NoTransformationFoundException) {
            val meal: String = client.get(SEARC_SERVICES_ENDPOINT){

                parameter("name", name)
            }
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            }
            res = json.decodeFromString(meal)
        }
        return res!!
    }
}