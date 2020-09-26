package com.example.dog

import android.util.Log
import com.example.dog.model.dbremote.ApiInterface
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.security.cert.CertPath


@RunWith(JUnit4::class)
class NetworkTest {

    private lateinit var service : ApiInterface
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    @After
    fun shutDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun requestBreedList(){
        runBlocking {
            enqueueResponse("api-response.json")
            val resultResponse = service.getDataFromBreed()
            val request = mockWebServer.takeRequest()
           // asserNotNull(resultResponse)
           // asserThat(request.path, 'is'("/breeds/list/"))
        }

    }

    private fun enqueueResponse (filename: String, headers: Map<String, String> = emptyMap()){
        val source = getJson(filename)
        val mockResponse = MockResponse

//        //si tuvieramos headres
        for((key, value) in headers){
//           // mockResponse.addHeader(key, value)
        }
        //mockWebServer.enqueue(mockResponse.setBody(source))
    }

    private fun getJson(path: String): String{
        val url = javaClass.classLoader!!.getResource(path)
        val file = File(url.path)
        return String(file.readBytes())
    }

}