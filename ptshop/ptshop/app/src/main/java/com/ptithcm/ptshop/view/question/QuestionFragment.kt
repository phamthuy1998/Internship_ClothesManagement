package com.ptithcm.ptshop.view.question

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.Question
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.ERROR_CODE_404
import com.ptithcm.ptshop.databinding.FragmentQuestionBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.question.adapter.QuestionAdapter
import com.ptithcm.ptshop.viewmodel.QuestionsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionFragment : BaseFragment<FragmentQuestionBinding>() {
    override val layoutId: Int = R.layout.fragment_question
    private val viewModel: QuestionsViewModel by viewModel()

    private var productId: Int? = null
    private var question: Question? = null
    private var posAddSubQuestion: Int? = null
    private var _parentQuestionID: Int? = null
    private val adapter: QuestionAdapter by lazy {
        QuestionAdapter(this::adapterEvent)
    }

    private fun adapterEvent(item: Question?, position: Int?, typeEvent: Int) {
        when (typeEvent) {
            1 -> {
                viewBinding.edtQuestion.setText("@ ${item?.username}")
                _parentQuestionID = item?.questionID
                posAddSubQuestion = position
                requireActivity().showKeyBoard()
                viewBinding.edtQuestion.setCursorEnd( viewBinding.edtQuestion.text.toString())
            }
            2 -> {
            }
            3 -> {
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productId = arguments?.get("productId") as Int?
        productId?.let { viewModel.getQuestion(it) }
        setupToolbar()
        initAdapter()
        initViews()
    }

    private fun initViews() {
        viewBinding.constraintLayout.setOnClickListener {
            requireActivity().hideKeyboard()
        }

        var questionStr = ""
        viewBinding.btnSend.setOnClickListener {
            if (CoreApplication.instance.account?.id == null) {
                navController.navigateAnimation(
                    R.id.loginFragment,
                    bundle = bundleOf("fromQuestionFragment" to true)
                )
                return@setOnClickListener
            }
            questionStr = viewBinding.edtQuestion.getTextTrim()
            if (questionStr.isNotEmpty()) {
                question = Question().apply {
                    accountID = CoreApplication.instance.account?.id
                    username = CoreApplication.instance.account?.username
                    question = questionStr
                    productID = productId
                    parentQuestionID =_parentQuestionID
                }
                question?.let { it1 -> viewModel.addQuestion(it1) }

                if (posAddSubQuestion == null) {
                    question?.let { it1 -> adapter.addQuestion(it1) }
                } else {
                    adapter.addSubQuestion(question, posAddSubQuestion)
                }
                viewBinding.rvQuestion.smoothScrollToPosition(adapter.itemCount)
            } else {
                messageHandler?.runMessageErrorHandler(getString(R.string.error_empty, "Question"))
            }
        }
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar, true,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false
            )
            setupToolbar(
                viewBinding.layoutToolbar.toolbar,
                getString(R.string.question_product).capitalize()
            )
        }
    }

    private fun initAdapter() {
        viewBinding.rvQuestion.adapter = adapter
        viewBinding.swlRefresh.setOnRefreshListener {
            productId?.let { viewModel.getQuestion(it) }
        }
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.listQuestion.observe(this, Observer {
            viewBinding.swlRefresh.isRefreshing = false
            if (it == null) {
                viewBinding.tvNoQuestions.visible()
            } else {
                viewBinding.tvNoQuestions.gone()
                adapter.submitList(it)
            }
        })
        viewModel.networkState.observe(this, Observer {
            if (it == true) viewBinding.progressbar.visible() else viewBinding.progressbar.gone()
        })
        viewModel.error.observe(this, Observer {
            viewBinding.progressbar.gone()
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                messageHandler?.runMessageHandler(it.first)
            }
        })

        viewModel.addQuestionResult.observe(this, Observer {
            if (it.status == true) {
                messageHandler?.runMessageHandler(getString(R.string.addQuestionSuccess))
                viewBinding.edtQuestion.emptyEdt()
                requireActivity().hideKeyboard()
                question = null
                posAddSubQuestion = null
                _parentQuestionID = null
            } else {
                messageHandler?.runMessageErrorHandler(getString(R.string.addQuestionFail))
                adapter.delQuestionFailure()
                viewBinding.rvQuestion.smoothScrollToPosition(adapter.itemCount)
            }
        })
    }
}