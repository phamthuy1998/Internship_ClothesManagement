package com.n16dccn159.core.api

import com.n16dccn159.core.model.NotificationParam
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationApi {

    companion object {
        const val Authorization =
            "AAAAWM-v4IY:APA91bEu_81o9O-HRiUTW9qcFAZqwqDTF0HpB1wUhjD_1X6bXtvpdbX_K40Bf5tXehPgMA0W28w1A9oP_DPq-gXu0bfB_e9po9fr47K-p_0Fv2wdZWQ0gjPcHjtoG794xYtK2lL5HPWL"
    }

    @Headers(
        "Authorization: key=$Authorization",
        "Content-Type: application/json"
    )
    @POST("fcm/send")
    suspend fun sendNotificationAsync(@Body param: NotificationParam)
}