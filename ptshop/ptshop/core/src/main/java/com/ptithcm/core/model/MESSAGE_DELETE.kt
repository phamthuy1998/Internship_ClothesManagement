package com.ptithcm.core.model
const val MESSAGE_DELETE = "MESSAGE_DELETE"
const val MESSAGE_REPLY = "MESSAGE_REPLY"
const val MESSAGE_REPLY_DELETE = "MESSAGE_REPLY_DELETE"
const val MESSAGE_NORMAL = "MESSAGE_NORMAL"

data class MessageData(
    var action: String? = "",
    var messageId: String? = "",
    var messageCreatedAt: String? = "",
    var messageUserName: String? = "",
    var messageSortText: String? = "",
    var messageThumbnailSize: ThumbnailSize? = null,
    var messageThumbnailSizeReply: ThumbnailSize? = null
)

data class ThumbnailSize(
    var width: Double? = 0.0,
    var height: Double? = 0.0,
    var url: String? = ""
)