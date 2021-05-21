package com.intellect.ello.network

import com.intellect.ello.model.AddOrganizationEntity
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString


class AddOrganizationAPI {

    val client = HttpClient()
    {
        install(JsonFeature){
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    companion object{
        val AddOrganizationURL = "https://techsoln.000webhostapp.com/ello/insertOrganization.php"
        val uploadURL = "https://techsoln.000webhostapp.com/ello/upload.php"
    }

    suspend fun upload(file:String, name: String): AddOrganizationEntity{
        var x: AddOrganizationEntity?= null

        try {
            x = client.submitFormWithBinaryData(
                AddOrganizationAPI.uploadURL,
                formData {
                    append("image", file)
                    //, Headers.build {
                        //append(HttpHeaders.ContentType, fileMimeType)
                      //  append(HttpHeaders.ContentDisposition, ContentDisposition.File.withParameter(ContentDisposition.Parameters.FileName, name))
                    //})
                }
            )

        } catch (e: NoTransformationFoundException){
            val y:String = client.submitFormWithBinaryData(
                AddOrganizationAPI.uploadURL,
                formData {
                    append("image", file)
                    //, Headers.build {
                        //append(HttpHeaders.ContentType, fileMimeType)
                      //  append(HttpHeaders.ContentDisposition, ContentDisposition.File.withParameter(ContentDisposition.Parameters.FileName, name))
                    //})
                }
            )
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            x = json.decodeFromString(y)
        }

        return x!!
    }


    suspend fun addOrganization(sid:Int, name:String, desc:String, contact:String, email:String, location:String, imUrl:String, status:String): AddOrganizationEntity {

        var x: AddOrganizationEntity?= null
        //val imName = "/image/$imUrl"

        try {
            x = client.post(AddOrganizationURL){
//                client.submitFormWithBinaryData<HttpResponse>(
//                    url = "https://techsoln.000webhostapp.com/ello/image",
//                    formData = formData {
//                        append("imUrl", imUrl, Headers.build {
//                            //append(HttpHeaders.ContentType, fileMimeType)
//                            append(HttpHeaders.ContentDisposition, ContentDisposition.File.withParameter(ContentDisposition.Parameters.FileName, imUrl))
//                        })
//
/////
//                    }
//                )

                MultiPartFormDataContent(formData {
                    append("imUrl", imUrl)
                })

                parameter("sid", sid)
                parameter("name", name)
                parameter("description", desc)
                parameter("contact", contact)
                parameter("email", email)
                parameter("location", location)
                parameter("imUrl", name)
                parameter("status", status)
            }

        } catch (e: NoTransformationFoundException){
            val y:String = client.post(AddOrganizationURL){
                MultiPartFormDataContent(formData {
                    append("imUrl", imUrl)
                })

//                client.submitFormWithBinaryData<HttpResponse>(
//                    url = "https://techsoln.000webhostapp.com/ello/image",
//                    formData = formData {
//                        append("imUrl", imUrl, Headers.build {
//                            //append(HttpHeaders.ContentType, fileMimeType)
//                            append(
//                                HttpHeaders.ContentDisposition,
//                                ContentDisposition.File.withParameter(
//                                    ContentDisposition.Parameters.FileName,
//                                    imUrl
//                                )
//                            )
//                        })
//                    })

                parameter("sid", sid)
                parameter("name", name)
                parameter("description", desc)
                parameter("contact", contact)
                parameter("email", email)
                parameter("location", location)
                parameter("imUrl", name)
                parameter("status", status)
            }
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            x = json.decodeFromString(y)
        }

        return x!!
    }
}