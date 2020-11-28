package com.ptithcm.ptshop.util

import android.content.Context
import com.sendbird.android.*
import com.sendbird.syncmanager.SendBirdSyncManager
import com.sendbird.syncmanager.handler.CompletionHandler

object SyncManagerUtils {
    fun setup(context: Context, userId: String, handler: CompletionHandler) {
        val options = SendBirdSyncManager.Options.Builder()
            .setMessageResendPolicy(SendBirdSyncManager.MessageResendPolicy.AUTOMATIC)
            .setAutomaticMessageResendRetryCount(5)
            .build()
        SendBirdSyncManager.setup(context, userId, options, handler)
    }

    /**
     * It returns the index that targetChannel should be inserted to the given channel list.
     */
    fun findIndexOfChannel(
        channels: List<GroupChannel>?,
        targetChannel: GroupChannel,
        order: GroupChannelListQuery.Order
    ): Int {
        if (channels?.size == 0) {
            return 0
        }

        val index = channels?.size ?: 0
        channels?.let {
            for (i in it.indices) {
                val c = it[i]
                if (c.url == targetChannel.url) {
                    return i
                }

                if (compareTo(targetChannel, c, order) < 0) {
                    return i
                }
            }
        }
        return index
    }

    /**
     * It returns the index of targetChannel in the given channel list.
     * If not exists, it will return -1.
     */
    fun getIndexOfChannel(channels: List<GroupChannel>?, targetChannel: GroupChannel): Int {
        channels?.let {
            for (i in it.indices) {
                if (it[i].url == targetChannel.url) {
                    return i
                }
            }
        }

        return -1
    }

    /**
     * If returned value is negative, it means that index of c1 is less than c2's
     * If returned value is zero, it means that index of c1 and c2 is same.
     * If returned value is positive, it means that index of c1 is larger than c2's
     */
    fun compareTo(c1: GroupChannel, c2: GroupChannel, order: GroupChannelListQuery.Order): Int {
        when (order) {
            GroupChannelListQuery.Order.CHRONOLOGICAL -> if (c1.createdAt > c2.createdAt) {
                return -1
            } else return if (c1.createdAt < c2.createdAt) {
                1
            } else {
                0
            }
            GroupChannelListQuery.Order.LATEST_LAST_MESSAGE -> {
                val m1 = c1.lastMessage
                val m2 = c2.lastMessage

                val createdAt1 = if (m1 != null) m1.createdAt else c1.createdAt
                val createdAt2 = if (m2 != null) m2.createdAt else c2.createdAt

                if (createdAt1 > createdAt2) {
                    return -1
                } else return if (createdAt1 < createdAt2) {
                    1
                } else {
                    0
                }
            }
            GroupChannelListQuery.Order.CHANNEL_NAME_ALPHABETICAL -> return c1.name.compareTo(c2.name)

            GroupChannelListQuery.Order.METADATA_VALUE_ALPHABETICAL -> {
            }
        }

        return 0
    }

    /**
     * It returns the index that targetMessage should be inserted to the given message list.
     * If isLatestFirst is set to true, latest message's index will be zero.
     * If isLatestFirst is set to true, oldest message's index will be zero.
     *
     * @param messages `BaseMessage` list associated with view.
     * @param newMessage New `BaseMessage` to be inserted to existing message list.
     * @return Index of new message have to be inserted.
     */
    fun findIndexOfMessage(messages: List<BaseMessage>, newMessage: BaseMessage): Int {
        if (messages.size == 0) {
            return 0
        }

        if (messages[0].createdAt < newMessage.createdAt) {
            return 0
        }

        for (i in 0 until messages.size - 1) {
            val m1 = messages[i]
            val m2 = messages[i + 1]

            if (m1.createdAt > newMessage.createdAt && newMessage.createdAt > m2.createdAt) {
                return i + 1
            }
        }

        return messages.size
    }

    /**
     * It returns the index of targetMessage in the given message list.
     * If not exists, it will return -1.
     *
     * @param messages `BaseMessage` list associated with view.
     * @param targetMessage Target `BaseMessage` to find out.
     * @return Index of target message in the given message list.
     */

    fun getIndexOfMessage(messages: List<BaseMessage>, targetMessage: BaseMessage): Int {
        for (i in messages.indices) {
            val msgId1 = messages[i].messageId
            val msgId2 = targetMessage.messageId

            if (msgId1 == msgId2) {
                if (msgId1 == 0L) {
                    if (getRequestId(messages[i]) == getRequestId(targetMessage)) {
                        return i
                    }
                } else {
                    return i
                }
            }
        }

        return -1
    }

    private fun getRequestId(message: BaseMessage): String {
        if (message is AdminMessage) {
            return ""
        }

        if (message is UserMessage) {
            return message.requestId
        }

        return if (message is FileMessage) {
            (message as FileMessage).requestId
        } else ""

    }
}