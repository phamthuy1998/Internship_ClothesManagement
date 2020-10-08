package com.ptithcm.ptshop.view.question.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.Question
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseViewAdapter
import com.ptithcm.ptshop.databinding.LayoutQuestionItemBinding
import com.ptithcm.ptshop.databinding.LayoutQuestionSubItemBinding


class QuestionAdapter(
    val listener: ((item: Question?, position: Int?, typeEvent: Int) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var questionList = arrayListOf<Question>()

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
                questionList[position].showReplies= true

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

        fun bind(item: Question) {
            binding.apply {
                question = item


                btnReply.setOnClickListener {
                    currentPosition = adapterPosition
                    listener?.invoke(item, adapterPosition, 1)
                }

                btnHideQuestion.setOnClickListener {
                    currentPosition = adapterPosition
                    item.showReplies = !item.showReplies
                    notifyItemChanged(adapterPosition)
                }
                if (item.subQuestions?.size ?: 0 > 0) {
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
                            data = item.subQuestions,
                            bindFunc = { binding, item, position ->
                                binding.question = item?.get(position)
                                binding.executePendingBindings()
                            },
                            getItemCountFunc = null,
                            getItemIdFunc = null

                        )
                        setRecycledViewPool(viewPool)
                    }
                }
                executePendingBindings()
            }
        }
    }
}