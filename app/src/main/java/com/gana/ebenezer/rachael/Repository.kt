package com.gana.ebenezer.rachael

import android.util.Log
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException

class Repository(private val apiService: ApiService) {

    suspend fun sendAudioFile(audioFile: File?):ByteArray?{
        if (audioFile == null) {
            Log.e("Error", "Audio file is null")
            return null

        }

        val requestFile = audioFile.asRequestBody("audio/mpeg".toMediaTypeOrNull())
        val audioPart = MultipartBody.Part.createFormData("file", audioFile.name, requestFile)

        try {
            val response = apiService.uploadAudio(audioPart)

            if (response.isSuccessful) {
                val audioResponseBody = response.body()
                if (audioResponseBody != null) {
                    val audioData = audioResponseBody.bytes()
                    Log.d("AudioData", audioData.toString())
                    return audioData

                } else {
                    Log.e("Error", "Response body is null")
                }
            } else {
                Log.e("Error", "Request not successful, HTTP status code: ${response.code()}")
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("Error", "File-related error: ${e.message}")
        } catch (e: HttpException) {
            e.printStackTrace()
            Log.e("Error", "HTTP error: ${e.code()}")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Error", "Unexpected error: ${e.message}")
        }

        return null

    }


}