package com.n16dccn159.admin.view.question.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.n16dccn159.core.model.Question
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseViewAdapter
import com.n16dccn159.admin.databinding.LayoutQuestionItemBinding
import com.n16dccn159.admin.databinding.LayoutQuestionSubItemBinding
import java.lang.reflect.Field

const val ITEM_REPLY = 1
const val ITEM_DEL = 2
const val ITEM_EDIT = 3

class QuestionAdapter(
    val listener: ((item: Question?, position: Int?, typeEvent: Int, isSubQuestion: Boolean?) -> Unit)? = null,
    val accID: Int?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var questionList = arrayListOf<Question>()

    var currentPosition: Int? = null

    override fun getItemId(position: Int): Long =
        questionList[position].questionID.hashCode().toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutQuestionItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_question_item,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = questionList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.bind(questionList[position])
    }

    fun submitList(list: ArrayList<Question>) {
        questionList.clear()
        questionList.addAll(list)
        notifyDataSetChanged()
    }

    fun addQuestion(question: Question) {
        questionList.add(question)
        notifyItemChanged(questionList.size - 1)
    }

    fun addSubQuestion(question: Question?, position: Int?) {
        if (position != null) {
            if (question != null) {
                questionList[position].subQuestions?.add(question)
                questionList[position].showReplies = true

            }
            notifyItemChanged(position)
        }
    }

    fun delQuestionFailure() {
        questionList.removeAt(questionList.size - 1)
    }

    fun removeItem(position: Int?) {
        position ?: return
        questionList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ItemViewHolder(val binding: LayoutQuestionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("RestrictedApi")
        fun bind(questionItem: Question) {
            binding.apply {
                question = questionItem
                accountID = accID
                btnReply.setOnClickListener {
                    currentPosition = adapterPosition
                    listener?.invoke(questionItem, adapterPosition, ITEM_REPLY, false)
                }

                btnHideQuestion.setOnClickListener {
                    currentPosition = adapterPosition
                    questionItem.showReplies = !questionItem.showReplies
                    notifyItemChanged(adapterPosition)
                }
                if (questionItem.subQuestions?.size ?: 0 > 0) {
                    val viewPool = RecyclerView.RecycledViewPool()
                    rvSubQuestion.apply {
                        adapter = BaseViewAdapter<LayoutQuestionSubItemBinding, List<Question>>(
                            onCreateViewHolderFunc = { viewGroup, _ ->
                                LayoutQuestionSubItemBinding.inflate(
                                    LayoutInflater.from(viewGroup.context),
                                    viewGroup,
                                    false
                                )
                            },
                            data = questionItem.subQuestions,
                            bindFunc = { binding, item, position ->
                                binding.apply {
                                    question = item?.get(position)
                                    accountID = accID
                                    btnMoreSubQuestion.setOnClickListener {
                                        currentPosition = adapterPosition
                                        showPopup(questionItem.subQuestions?.get(position), btnMoreSubQuestion, true)
                                    }
                                    executePendingBindings()
                                }
                            },
                            getItemCountFunc = null,
                            getItemIdFunc = null

                        )
                        setRecycledViewPool(viewPool)
                    }
                }
                btnMore.setOnClickListener {
                    currentPosition = adapterPosition
                    showPopup(questionItem, btnMore, false)
                }
                executePendingBindings()
            }
        }

        @SuppressLint("RestrictedApi")
        private fun showPopup(item: Question?, btnMore: AppCompatImageView, isSubQuestion: Boolean?) {
            val popup = PopupMenu(btnMore.context, btnMore)
            popup.menuInflater.inflate(R.menu.menu_question_item, popup.menu)

            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menuEdit -> {
                        listener?.invoke(item, adapterPosition, ITEM_EDIT, isSubQuestion)
                        true
                    }
                    R.id.menuDel -> {
                        listener?.invoke(item, adapterPosition, ITEM_DEL, isSubQuestion)
                        true
                    }

                    else -> false
                }
            }
            try {
                val mFieldPopup: Field = popup.javaClass.getDeclaredField("mPopup")
                mFieldPopup.isAccessible = true
                val mPopup: MenuPopupHelper = mFieldPopup.get(popup) as MenuPopupHelper
                mPopup.setForceShowIcon(true)
            } catch (e: Exception) {
            }

            popup.show()
        }
    }
}