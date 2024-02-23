package logic

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.net.ConnectException

fun get(url:String) : String{
    val client = OkHttpClient()

    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).execute().use { response ->
        val code = response.code

        if(code != 200) throw IOException("Error al hacer un get a ${url} con codigo ${code}")

        val responseBody = response.body

        if (responseBody == null) throw IOException("Cuerpo vacio con hacer get a ${url}")

        val responseText = responseBody.string()
        println("Response code: ${response.code}")
        println("Response body: $responseText")
        return responseText
    }
}
