package com.intellect.ello.network

import com.intellect.ello.model.OrganizationEntity
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.decodeFromString

class OrganizationAPI {
    val client = HttpClient()
    {
        install(JsonFeature){
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    companion object{
         val OrganizationURL = "https://techsoln.000webhostapp.com/ello/getOrganization.php"
        val OrganizationSearcURL = "https://techsoln.000webhostapp.com/ello/searchOrganization.php"
    }

    suspend fun getOrganizations(id:Int): ArrayList<OrganizationEntity>{

        var x:ArrayList<OrganizationEntity> ?= null

        try {
           x = client.get(OrganizationURL){
               parameter("sid", "$id")
           }

        } catch (e: NoTransformationFoundException){
            val y:String = client.get(OrganizationURL){
                parameter("sid", "$id")
            }
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            x = json.decodeFromString(y)
        }

        return x!!
    }

    suspend fun searchOrganization(name:String): ArrayList<OrganizationEntity> {
        var res: ArrayList<OrganizationEntity> ?= null
        try {
            res = client.get(OrganizationAPI.OrganizationSearcURL){

                    parameter("name", name)
            }
        }catch (e: NoTransformationFoundException) {
            val meal: String = client.get(OrganizationAPI.OrganizationSearcURL){
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