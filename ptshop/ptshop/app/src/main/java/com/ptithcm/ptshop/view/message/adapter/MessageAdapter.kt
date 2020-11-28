//package com.ptithcm.ptshop.view.message.adapter
//
//import android.annotation.SuppressLint
//import android.os.Handler
//import android.os.Looper
//import android.util.Log
//import android.view.*
//import android.view.animation.Animation
//import android.view.animation.ScaleAnimation
//import android.widget.ProgressBar
//import android.widget.TextView
//import androidx.appcompat.widget.AppCompatImageView
//import androidx.appcompat.widget.AppCompatTextView
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.databinding.DataBindingUtil
//import androidx.databinding.ViewDataBinding
//import androidx.recyclerview.widget.RecyclerView
//import com.google.gson.Gson
//import com.ptithcm.core.model.MESSAGE_DELETE
//import com.ptithcm.core.model.MESSAGE_REPLY
//import com.ptithcm.core.model.MESSAGE_REPLY_DELETE
//import com.ptithcm.core.model.MessageData
//import com.ptithcm.ptshop.R
//import com.ptithcm.ptshop.databinding.*
//import com.ptithcm.ptshop.ext.gone
//import com.ptithcm.ptshop.ext.visible
//import com.ptithcm.ptshop.util.DateUtils.compareDayBetweenTimeStamp
//import com.ptithcm.ptshop.util.DateUtils.formatTime
//import com.ptithcm.ptshop.util.ImageUtils.displayImageFromUrl
//import com.ptithcm.ptshop.util.SyncManagerUtils
//import com.sendbird.android.BaseMessage
//import com.sendbird.android.FileMessage
//import com.sendbird.android.GroupChannel
//import com.sendbird.android.UserMessage
//import com.sendbird.uikit.utils.DateUtils
//import java.util.*
//import kotlin.Comparator
//import kotlin.properties.Delegates
//
//
//class MessageDetailAdapter(
//    private val userId: String,
//    private val onReplyMessage: (msg: BaseMessage?) -> Unit,
//    private val onRemoveUserMessage: (msg: BaseMessage?) -> Unit,
//    private val onRemoveFileMessage: (msg: BaseMessage?) -> Unit,
//    private val onRemoveReplyUserMessage: (msg: BaseMessage?) -> Unit,
//    private val onRemoveReplyFileMessage: (msg: BaseMessage?) -> Unit,
//    private val onClickOriginalMessage: (position: Int, id: Long?) -> Unit,
//    private val onClickImage: (string: String) -> Unit,
//    private val onClickCopy: (String?) -> Unit,
//    private val onClickMention: (String?) -> Unit,
//    private val onClickHashtag: (String?) -> Unit
//
//) :
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private var mChannel: GroupChannel? = null
//    private val mMessageList: MutableList<BaseMessage>
//    private val mFailedMessageList: MutableList<BaseMessage>
//    private val mResendingMessageSet: MutableSet<String>
//    private var mFileMessageMap: HashMap<FileMessage, ProgressBar>? = null
//    private var mTargetMessage: Pair<Long?, Int>? = null // <messageId, position>
//    var mAction: String? = null
//
//    init {
//        mMessageList = ArrayList()
//        mFailedMessageList = ArrayList()
//        mResendingMessageSet = HashSet()
//        mFileMessageMap = HashMap()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//
//        return when (viewType) {
//            VIEW_TYPE_MY_MESSAGE_DELETED -> {
//                val binding = DataBindingUtil.inflate<LayoutItemMessageDeleteSendBinding>(
//                    LayoutInflater.from(parent.context),
//                    R.layout.layout_item_message_delete_send, parent, false
//                )
//                MyMessageDeletedViewHolder(binding)
//            }
//            VIEW_TYPE_OTHER_MESSAGE_DELETED -> {
//                val binding = DataBindingUtil.inflate<LayoutItemMessageDeleteReceiveBinding>(
//                    LayoutInflater.from(parent.context),
//                    R.layout.layout_item_message_delete_receive, parent, false
//                )
//                OtherMessageDeletedViewHolder(binding)
//            }
//            VIEW_TYPE_USER_MESSAGE_ME -> {
//                val binding = DataBindingUtil.inflate<LayoutItemMessageSendBinding>(
//                    LayoutInflater.from(parent.context),
//                    R.layout.layout_item_message_send, parent, false
//                )
//                MyUserMessageHolder(binding)
//            }
//            VIEW_TYPE_USER_MESSAGE_OTHER -> {
//                val binding = DataBindingUtil.inflate<LayoutItemMessageReceiveBinding>(
//                    LayoutInflater.from(parent.context),
//                    R.layout.layout_item_message_receive, parent, false
//                )
//                OtherUserMessageHolder(binding)
//            }
//            VIEW_TYPE_FILE_MESSAGE_IMAGE_OTHER -> {
//                val binding = DataBindingUtil.inflate<LayoutItemMessageImageReceiveBinding>(
//                    LayoutInflater.from(parent.context),
//                    R.layout.layout_item_message_image_receive, parent, false
//                )
//                OtherImageFileMessageHolder(binding)
//            }
//            else -> {
//                val binding = DataBindingUtil.inflate<LayoutItemMessageImageSendBinding>(
//                    LayoutInflater.from(parent.context),
//                    R.layout.layout_item_message_image_send, parent, false
//                )
//                MyImageFileMessageHolder(binding)
//            }
//
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val message = getMessage(position) ?: return
//
//        var isNewDay = false
//        // If there is at least one item preceding the current one, check the previous message.
//        if (position < mMessageList.size + mFailedMessageList.size - 1) {
//            val prevMessage = getMessage(position + 1)
//
//            // If the date of the previous message is different, display the date before the message,
//            // and also set isContinuous to false to show information such as the sender's nickname
//            // and profile image.
//            if (!DateUtils.hasSameDate(message.createdAt, prevMessage!!.createdAt)) {
//                isNewDay = true
//            }
//        } else if (position == mFailedMessageList.size + mMessageList.size - 1) {
//            isNewDay = true
//        }
//        val isTempMessage = isTempMessage(message)
//        val isFailedMessage = isFailedMessage(message)
//
//        when (holder.itemViewType) {
//            VIEW_TYPE_MY_MESSAGE_DELETED -> {
//                (holder as MyMessageDeletedViewHolder).bind(message, isNewDay, position)
//            }
//            VIEW_TYPE_OTHER_MESSAGE_DELETED -> {
//                (holder as OtherMessageDeletedViewHolder).bind(message, isNewDay, position)
//            }
//            VIEW_TYPE_USER_MESSAGE_ME -> (holder as MyUserMessageHolder).bind(
//                message as UserMessage,
//                isNewDay,
//                position
//            )
//            VIEW_TYPE_USER_MESSAGE_OTHER -> (holder as OtherUserMessageHolder).bind(
//                message as UserMessage,
//                isNewDay,
//                position
//            )
//            VIEW_TYPE_FILE_MESSAGE_IMAGE_OTHER -> (holder as OtherImageFileMessageHolder).bind(
//                message as FileMessage,
//                isNewDay,
//                isTempMessage,
//                isFailedMessage,
//                position
//            )
//            VIEW_TYPE_FILE_MESSAGE_IMAGE_ME -> (holder as MyImageFileMessageHolder).bind(
//                message as FileMessage,
//                isNewDay,
//                isTempMessage,
//                isFailedMessage,
//                position
//            )
//            else -> {
//            }
//        }
//    }
//
//    /**
//     * Declares the View Type according to the type of message and the sender.
//     */
//    override fun getItemViewType(position: Int): Int {
//        val message = getMessage(position)
//        var isMyMessage = false
//
//        if (message is UserMessage) {
//            val requestState = message.sendingStatus
//            if (requestState == BaseMessage.SendingStatus.PENDING
//                || requestState == BaseMessage.SendingStatus.FAILED
//                || message.sender.userId == userId
//            ) {
//                isMyMessage = true
//            }
//        } else if (message is FileMessage) {
//            val requestState = message.sendingStatus
//            if (requestState == BaseMessage.SendingStatus.PENDING
//                || requestState == BaseMessage.SendingStatus.FAILED
//                || message.sender.userId == userId
//            ) {
//                isMyMessage = true
//            }
//        }
//
//        if (message?.data?.contains(MESSAGE_DELETE) == true) {
//            return if (isMyMessage) {
//                VIEW_TYPE_MY_MESSAGE_DELETED
//            } else {
//                VIEW_TYPE_OTHER_MESSAGE_DELETED
//            }
//        }
//
//        if (message is UserMessage) {
//            return if (isMyMessage) {
//                VIEW_TYPE_USER_MESSAGE_ME
//            } else {
//                VIEW_TYPE_USER_MESSAGE_OTHER
//            }
//        } else if (message is FileMessage) {
//            if (message.type.toLowerCase(Locale.getDefault()).startsWith("image")) {
//                return if (isMyMessage)
//                    VIEW_TYPE_FILE_MESSAGE_IMAGE_ME
//                else VIEW_TYPE_FILE_MESSAGE_IMAGE_OTHER
//            }
//        }
//        return -1
//    }
//
//    override fun getItemCount(): Int {
//        return mMessageList.size + mFailedMessageList.size
//    }
//
//    private fun getMessage(position: Int): BaseMessage? {
//        return when {
//            position < mFailedMessageList.size -> mFailedMessageList[position]
//            position < mFailedMessageList.size + mMessageList.size -> mMessageList[position - mFailedMessageList.size]
//            else -> null
//        }
//    }
//
//    fun setChannel(channel: GroupChannel) {
//        mChannel = channel
//    }
//
//    private fun isTempMessage(message: BaseMessage): Boolean {
//        return message.messageId == 0L
//    }
//
//    private fun isFailedMessage(message: BaseMessage?): Boolean {
//        return if (message == null) {
//            false
//        } else mFailedMessageList.contains(message)
//
//    }
//
//    fun insertSucceededMessages(messages: List<BaseMessage?>) {
//        for (message in messages) {
//            val index = SyncManagerUtils.findIndexOfMessage(mMessageList, message!!)
//            mMessageList.add(index, message)
////            notifyItemInserted(index)
//        }
//        notifyDataSetChanged()
//
//        mTargetMessage?.let {
//            val pos = mMessageList.indexOfFirst { msg ->
//                msg.messageId == it.first
//            }
//            if (pos < 0) {
//                onClickOriginalMessage.invoke(mMessageList.size - 1, mTargetMessage?.first)
//            } else {
//                onClickOriginalMessage.invoke(pos, mTargetMessage?.first)
//                mTargetMessage = null
//            }
//        }
//    }
//
//    fun updateSucceededMessages(messages: List<BaseMessage>) {
//        for (message in messages) {
//            val index = SyncManagerUtils.getIndexOfMessage(mMessageList, message)
//            if (index != -1) {
//                mMessageList[index] = message
//                notifyItemChanged(index)
//            }
//            val messageData = Gson().fromJson(message.data, MessageData::class.java)
//            if (messageData.action == MESSAGE_DELETE) {
//                mMessageList.forEach { targetMessage ->
//                    if (targetMessage.sender.userId == userId) {
//                        val data = Gson().fromJson(targetMessage.data, MessageData::class.java)
//                        if (data?.messageId == message.messageId.toString()) {
//                            if (targetMessage is UserMessage) {
//                                onRemoveReplyUserMessage(targetMessage)
//                            } else {
//                                onRemoveReplyFileMessage(targetMessage)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    fun removeSucceededMessages(messages: List<BaseMessage>) {
//        for (message in messages) {
//            val index = SyncManagerUtils.getIndexOfMessage(mMessageList, message)
//            if (index != -1) {
//                mMessageList.removeAt(index)
//                notifyItemRemoved(index)
//            }
//        }
//    }
//
//    private fun getRequestId(message: BaseMessage): String {
//        if (message is UserMessage) {
//            return message.requestId
//        } else if (message is FileMessage) {
//            return message.requestId
//        }
//
//        return ""
//    }
//
//    fun insertFailedMessages(messages: List<BaseMessage>) {
//        synchronized(mFailedMessageList) {
//            for (message in messages) {
//                val requestId = getRequestId(message)
//                if (requestId.isEmpty()) {
//                    continue
//                }
//                mResendingMessageSet.add(requestId)
//                mFailedMessageList.add(message)
//            }
//            mFailedMessageList.sortWith(Comparator { m1, m2 ->
//                val x = m1.createdAt
//                val y = m2.createdAt
//                if (x < y) 1 else if (x == y) 0 else -1
//            })
//        }
//
//        notifyDataSetChanged()
//    }
//
//    fun updateFailedMessages(messages: List<BaseMessage>) {
//        synchronized(mFailedMessageList) {
//            for (message in messages) {
//                val requestId = getRequestId(message)
//                if (requestId.isEmpty()) {
//                    continue
//                }
//
//                mResendingMessageSet.remove(requestId)
//            }
//        }
//
//        notifyDataSetChanged()
//    }
//
//    fun removeFailedMessages(messages: List<BaseMessage>) {
//        synchronized(mFailedMessageList) {
//            for (message in messages) {
//                val requestId = getRequestId(message)
//                mResendingMessageSet.remove(requestId)
//                mFailedMessageList.remove(message)
//            }
//        }
//
//        notifyDataSetChanged()
//    }
//
//    fun failedMessageListContains(message: BaseMessage?): Boolean {
//        if (mFailedMessageList.isEmpty()) {
//            return false
//        }
//        for (failedMessage in mFailedMessageList) {
//            if (message is UserMessage && failedMessage is UserMessage) {
//                if (message.requestId == failedMessage.requestId) {
//                    return true
//                }
//            } else if (message is FileMessage && failedMessage is FileMessage) {
//                if (message.requestId == failedMessage.requestId) {
//                    return true
//                }
//            }
//        }
//        return false
//    }
//
//    fun clear() {
//        mMessageList.clear()
//        mFailedMessageList.clear()
//        notifyDataSetChanged()
//    }
//
//    /**
//     * Notifies that the user has read all (previously unread) messages in the channel.
//     * Typically, this would be called immediately after the user enters the chat and loads
//     * its most recent messages.
//     */
//    fun markAllMessagesAsRead() {
//        if (mChannel != null) {
//            mChannel!!.markAsRead()
//        }
////        notifyDataSetChanged()
//    }
//
//    fun getLastReadPosition(lastRead: Long): Int {
//        for (i in mMessageList.indices) {
//            if (mMessageList[i].createdAt == lastRead) {
//                return i + mFailedMessageList.size
//            }
//        }
//
//        return 0
//    }
//
//    open inner class BaseViewHolder(private val binding: ViewDataBinding) :
//        RecyclerView.ViewHolder(binding.root), View.OnCreateContextMenuListener,
//        MenuItem.OnMenuItemClickListener {
//        private val dateText: TextView? = itemView.findViewById(R.id.tvTime)
//        private val ctlReplyContainer: ConstraintLayout? =
//            itemView.findViewById(R.id.incMessageReply)
//        private val tvNameTime: AppCompatTextView? = itemView.findViewById(R.id.tvNameTime)
//        private val tvMessageReply: AppCompatTextView? = itemView.findViewById(R.id.tvMessageReply)
//        private val ivMessageReply: AppCompatImageView? = itemView.findViewById(R.id.ivMessageReply)
//        private val tvMessageReplyDeleted: AppCompatTextView? =
//            itemView.findViewById(R.id.tvMessageReplyDeleted)
//        private val cardGroupChatMessage: View? =
//            itemView.findViewById(R.id.card_group_chat_message)
//
//        var data: MessageData? = null
//        var mMessage: BaseMessage? = null
//        var mTimeSent = 0L
//        private var mPosition by Delegates.notNull<Int>()
//        private var runnable: Runnable? = null
//        val messageId
//            get() = mMessage?.messageId
//
//        fun startAnimation() {
//            val anim: Animation = ScaleAnimation(
//                1f,
//                1.05f,
//                1f,
//                1.05f,
//                Animation.RELATIVE_TO_SELF, 0.5f,
//                Animation.RELATIVE_TO_SELF, 0.5f
//            )
//            anim.duration = 200
//            cardGroupChatMessage?.startAnimation(anim)
//        }
//
//        @SuppressLint("SetTextI18n")
//        internal fun bind(message: BaseMessage, isNewDay: Boolean, position: Int) {
//            Log.d(
//                "MessageDetailAdapter",
//                "bind at position: $position with message: ${message.message}"
//            )
//            mMessage = message
//            mTimeSent = (System.currentTimeMillis() - message.createdAt)
//            if (mTimeSent < SEVEN_MINUTES) {
//                runnable = object : Runnable {
//                    override fun run() {
//                        if (runnable == this) {
//                            Log.d(
//                                "MessageDetailAdapter",
//                                "REMOVE OFF at position: $position with message: ${message.message}"
//                            )
//                            notifyItemChanged(position)
//                        }
//                    }
//                }
//                Handler(Looper.getMainLooper()).postDelayed(runnable as Runnable, SEVEN_MINUTES - mTimeSent)
//            } else {
//                runnable = null
//            }
//            mPosition = position
//            ctlReplyContainer?.gone()
//            // Show the date if the message was sent on a different date than the previous message.
//            if (isNewDay) {
//                dateText?.visible()
//                when (compareDayBetweenTimeStamp(message.createdAt)) {
//                    0L -> dateText?.text = itemView.context.getString(R.string.today)
//                    -1L -> dateText?.text = itemView.context.getString(R.string.yesterday_cap)
//                    else -> dateText?.text = DateUtils.formatDate(message.createdAt)
//                }
////                dateText.text = DateUtils.formatDate(message.createdAt)
//            } else {
//                dateText?.gone()
//            }
//
//            data = Gson().fromJson(message.data, MessageData::class.java)
//
//            if (data?.action == MESSAGE_REPLY_DELETE) {
//                ctlReplyContainer?.visible()
//                tvMessageReplyDeleted?.visible()
//                tvNameTime?.gone()
//                tvMessageReply?.gone()
//                ivMessageReply?.gone()
//                ctlReplyContainer?.setOnClickListener(null)
//                tvMessageReply?.setOnClickListener(null)
//            } else if (data?.action == MESSAGE_REPLY) {
//                ctlReplyContainer?.visible()
//                tvMessageReplyDeleted?.gone()
//                tvNameTime?.visible()
//                tvMessageReply?.visible()
//                ivMessageReply?.visible()
//                tvMessageReply?.setOnClickListener { findOriginalMessage(data) }
//                ctlReplyContainer?.setOnClickListener { findOriginalMessage(data) }
//                val createdAt =
//                    if (data?.messageCreatedAt?.isEmpty() == true) 0L else data?.messageCreatedAt?.toLong()
//                        ?: 0L
//                val date = when (compareDayBetweenTimeStamp(createdAt)) {
//                    0L -> itemView.context.getString(R.string.today)
//                    -1L -> itemView.context.getString(R.string.yesterday_cap)
//                    else -> DateUtils.formatDate(message.createdAt)
//                }
//                val time = formatTime(createdAt).toLowerCase(Locale.US)
//                tvNameTime?.text = "$date $time"
////                tvMessageReply?.textSize =
//////                    if (EmojiUtils.isSingleEmoji(data?.messageSortText)) 20f else 14f //pixel
////                tvMessageReply?.text = data?.messageSortText
//                if (data?.messageThumbnailSizeReply?.url.isNullOrEmpty().not()) {
//                    tvMessageReply?.gone()
//                    ivMessageReply?.visible()
//                    displayImageFromUrl(
//                        itemView.context,
//                        data?.messageThumbnailSizeReply?.url,
//                        ivMessageReply,
//                        null,
//                        null,
//                        5,
//                        data?.messageThumbnailSizeReply
//                    )
//
//                } else {
//                    ivMessageReply?.gone()
//                }
//
//            }
//        }
//
//        private fun findOriginalMessage(data: MessageData?) {
//            val pos = mMessageList.indexOfFirst { msg ->
//                msg.messageId == data?.messageId?.toLong()
//            }
//            mTargetMessage = Pair(data?.messageId?.toLong(), pos)
//            if (pos < 0) {
//                onClickOriginalMessage.invoke(mMessageList.size - 1, mTargetMessage?.first)
//            } else {
//                onClickOriginalMessage.invoke(pos, mTargetMessage?.first)
//                mTargetMessage = null
//            }
//        }
//
//        override fun onCreateContextMenu(
//            menu: ContextMenu?,
//            v: View?,
//            menuInfo: ContextMenu.ContextMenuInfo?
//        ) {
//            var menuCopy: MenuItem? = null
//            var menuRemove: MenuItem? = null
//            val menuReply = menu?.add(
//                Menu.NONE,
//                MENU_REPLY_ID,
//                3,
//                binding.root.context.resources.getString(R.string.reply)
//            )
//            when (getItemViewType(mPosition)) {
//                VIEW_TYPE_USER_MESSAGE_ME -> {
//                    menuCopy = menu?.add(
//                        Menu.NONE,
//                        MENU_COPY_ID,
//                        2,
//                        binding.root.context.resources.getString(R.string.copy)
//                    )
//                    if (mTimeSent < SEVEN_MINUTES) {
//                        menuRemove = menu?.add(
//                            Menu.NONE,
//                            MENU_REMOVE_ID,
//                            4,
//                            binding.root.context.resources.getString(R.string.remove)
//                        )
//                    }
//                }
//                VIEW_TYPE_USER_MESSAGE_OTHER -> {
//                    menuCopy = menu?.add(
//                        Menu.NONE,
//                        MENU_COPY_ID,
//                        2,
//                        binding.root.context.resources.getString(R.string.copy)
//                    )
//                }
//                VIEW_TYPE_FILE_MESSAGE_IMAGE_ME -> {
//                    if (mTimeSent < SEVEN_MINUTES) {
//                        menuRemove = menu?.add(
//                            Menu.NONE,
//                            MENU_REMOVE_ID,
//                            4,
//                            binding.root.context.resources.getString(R.string.remove)
//                        )
//                    }
//                }
//                VIEW_TYPE_FILE_MESSAGE_IMAGE_OTHER -> {
//                }
//            }
//            menuCopy?.setOnMenuItemClickListener(this)
//            menuReply?.setOnMenuItemClickListener(this)
//            menuRemove?.setOnMenuItemClickListener(this)
//        }
//
//        override fun onMenuItemClick(item: MenuItem?): Boolean {
//            when (item?.itemId) {
//                MENU_COPY_ID -> {
//                    if (mMessage is UserMessage)
//                        onClickCopy.invoke((mMessage as UserMessage).message)
//                }
//                MENU_REPLY_ID -> {
//                    mAction = ACTION_REPLY
//                    onReplyMessage(mMessage)
//                }
//                MENU_REMOVE_ID -> {
//                    mAction = ACTION_REMOVE
//                    if (mMessage is UserMessage)
//                        onRemoveUserMessage.invoke(mMessage)
//                    else
//                        onRemoveFileMessage.invoke(mMessage)
//                }
//            }
//            return true
//        }
//
//        open fun onRemoveHighlight() {}
//
//    }
//
//    private inner class MyMessageDeletedViewHolder(binding: LayoutItemMessageDeleteSendBinding) :
//        BaseViewHolder(binding)
//
//    private inner class OtherMessageDeletedViewHolder(binding: LayoutItemMessageDeleteReceiveBinding) :
//        BaseViewHolder(binding)
//
//    private open inner class MyUserMessageHolder(private val binding: LayoutItemMessageSendBinding) :
//        BaseViewHolder(binding) {
//
//        override fun onRemoveHighlight() {
//            binding.isFocus = false
//        }
//
//        fun bind(
//            message: UserMessage,
//            isNewDay: Boolean,
//            position: Int
//        ) {
//          /*  super.bind(message, isNewDay, position)
//            binding.tvMessageSend.textSize =
//                if (EmojiUtils.isSingleEmoji(message.message)) 24f else 16f //pixel
//            binding.tvMessageSend.text = message.message
//            binding.tvMessageSend.setOnClickLinkify { value, mode ->
//                if (mode == CustomLinkify.MENTION) {
//                    onClickMention.invoke(value)
//                } else if (mode == CustomLinkify.HASH_TAG) {
//                    onClickHashtag.invoke(value)
//                }
//            }
//            binding.tvTimeSend.text = DateUtils.formatTime(message.createdAt).toLowerCase(Locale.US)
////            val widthMessage = binding.tvMessageSend.layoutParams.width
////                binding.cardGroupChatMessage.layoutParams.width = widthMessage + 16
//
//            binding.cardGroupChatMessage.setOnCreateContextMenuListener(this)
//            binding.cardGroupChatMessage.setOnLongClickListener {
//                binding.isFocus = true
//                false
//            }
//            binding.tvMessageSend.setOnLongClickListener {
//                binding.isFocus = true
//                false
//            }*/
//
//        }
//    }
//
//    private inner class OtherUserMessageHolder(private val binding: LayoutItemMessageReceiveBinding) :
//        BaseViewHolder(binding) {
//
//        override fun onRemoveHighlight() {
//            binding.isFocus = false
//        }
//
//        fun bind(
//            message: UserMessage,
//            isNewDay: Boolean,
//            position: Int
//        ) {
////            super.bind(message, isNewDay, position)
////            binding.tvMessageRecieve.textSize =
////                if (EmojiUtils.isSingleEmoji(message.message)) 24f else 16f //pixel
////            binding.tvMessageRecieve.text = message.message
////            binding.tvMessageRecieve.setOnClickLinkify { value, mode ->
////                if (mode == CustomLinkify.MENTION) {
////                    onClickMention.invoke(value)
////                } else if (mode == CustomLinkify.HASH_TAG) {
////                    onClickHashtag.invoke(value)
////                }
////            }
////            binding.tvTimeRecieve.text =
////                DateUtils.formatTime(message.createdAt).toLowerCase(Locale.US)
////            binding.cardGroupChatMessage.setOnCreateContextMenuListener(this)
////            binding.cardGroupChatMessage.setOnLongClickListener {
////                binding.isFocus = true
////                false
////            }
////            binding.tvMessageRecieve.setOnLongClickListener {
////                binding.isFocus = true
////                false
////            }
//
//        }
//    }
//
//    private inner class OtherImageFileMessageHolder(private val binding: LayoutItemMessageImageReceiveBinding) :
//        BaseViewHolder(binding) {
//
//        override fun onRemoveHighlight() {
//            binding.isFocus = false
//        }
//
//        fun bind(
//            message: FileMessage,
//            isNewDay: Boolean,
//            isTempMessage: Boolean,
//            isFailedMessage: Boolean,
//            position: Int
//        ) {
//            super.bind(message, isNewDay, position)
//            val context = binding.root.context
//
//            if (message.sendingStatus == BaseMessage.SendingStatus.PENDING
//                || isFailedMessage && mResendingMessageSet.contains(message.requestId)
//            ) {
//                binding.progressBar.visibility = View.VISIBLE
//                mFileMessageMap!![message] = binding.progressBar
//            } else if (isTempMessage) {
//                binding.progressBar.visibility = View.GONE
//                mFileMessageMap!!.remove(message)
//            } else {
//                binding.progressBar.visibility = View.GONE
//                mFileMessageMap!!.remove(message)
//            }
//
///*
//            ImageUtils.displayImageFromUrl(
//                context,
//                message.url,
//                binding.ivMessageReceive,
//                null,
//                null,
//                5,
//                data?.messageThumbnailSize
//            )
//*/
//
//            binding.ivMessageReceive.setOnClickListener {
//                onClickImage(message.url)
//            }
////            binding.tvTimeReceive.text =
////                DateUtils.formatTime(message.createdAt).toLowerCase(Locale.US)
//            binding.ivMessageReceive.setOnCreateContextMenuListener(this)
//            binding.ivMessageReceive.setOnLongClickListener {
//                binding.isFocus = true
//                false
//            }
//        }
//    }
//
//    private inner class MyImageFileMessageHolder(private val binding: LayoutItemMessageImageSendBinding) :
//        BaseViewHolder(binding) {
//
//        override fun onRemoveHighlight() {
//            binding.isFocus = false
//        }
//
//        fun bind(
//            message: FileMessage,
//            isNewDay: Boolean,
//            isTempMessage: Boolean,
//            isFailedMessage: Boolean,
//            position: Int
//        ) {
//            super.bind(message, isNewDay, position)
//            val context = binding.root.context
//
//            if (message.sendingStatus == BaseMessage.SendingStatus.PENDING
//                || isFailedMessage && mResendingMessageSet.contains(message.requestId)
//            ) {
//                binding.progressBar.visibility = View.VISIBLE
//                mFileMessageMap!![message] = binding.progressBar
//            } else if (isTempMessage) {
//                binding.progressBar.visibility = View.GONE
//                mFileMessageMap!!.remove(message)
//            } else {
//                binding.progressBar.visibility = View.GONE
//                mFileMessageMap!!.remove(message)
//            }
//
////            ImageUtils.displayImageFromUrl(
////                context,
////                message.url,
////                binding.ivMessageSend,
////                null,
////                null,
////                5,
////                data?.messageThumbnailSize
////            )
//
//
//            binding.ivMessageSend.setOnClickListener {
//                onClickImage(message.url)
//            }
//            binding.ivMessageSend.setOnCreateContextMenuListener(this)
//            binding.ivMessageSend.setOnLongClickListener {
//                binding.isFocus = true
//                false
//            }
////            binding.tvTimeSend.text = DateUtils.formatTime(message.createdAt).toLowerCase(Locale.US)
//        }
//    }
//
//    companion object {
//        private const val VIEW_TYPE_USER_MESSAGE_ME = 10
//        private const val VIEW_TYPE_USER_MESSAGE_OTHER = 11
//        private const val VIEW_TYPE_FILE_MESSAGE_IMAGE_ME = 22
//        private const val VIEW_TYPE_FILE_MESSAGE_IMAGE_OTHER = 23
//        private const val VIEW_TYPE_MY_MESSAGE_DELETED = 31
//        private const val VIEW_TYPE_OTHER_MESSAGE_DELETED = 32
//        private const val MENU_COPY_ID = 2
//        private const val MENU_REPLY_ID = 3
//        private const val MENU_REMOVE_ID = 4
//        const val ACTION_REMOVE = "ACTION_REMOVE"
//        const val ACTION_REPLY = "ACTION_REPLY"
//        const val SEVEN_MINUTES = 420000
//    }
//}