package com.ptithcm.ptshop.view.uploadproduct

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.ptithcm.core.model.Tag
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.databinding.FragmentAddTagBinding
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.isShowErrorNetwork
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.uploadproduct.adapter.TagGroupAdapter
import com.ptithcm.ptshop.viewmodel.ShareDataViewModel
import kotlinx.android.synthetic.main.layout_toolbar_upload_product.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddTagFragment : BaseFragment<FragmentAddTagBinding>(), View.OnClickListener {

    override val layoutId: Int
        get() = R.layout.fragment_add_tag

    private val shareDataViewModel: ShareDataViewModel by sharedViewModel(from = { requireActivity() })

    private lateinit var tagGroupAdapter: TagGroupAdapter

    private var currentSelectedTags = arrayListOf<Tag>()
    private var counter = 0
    private var listTags = arrayListOf<Tag>()

    private fun setupToolbar() {
        (requireActivity() as? UploadProductActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar,
                false,
                hasBackRight = false,
                hasTextRight = true,
                hasCloseButton = true
            )
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.tags),
                isBackPress = false,
                messageQueue = { view ->
                    when (view.id) {
                        R.id.tvRight -> onClickClear()
                        R.id.ivClose -> navController.popBackStack()
                    }
                })
            viewBinding.layoutToolbar.toolbar.tvTitleToolbar.textSize = 14f
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        initAdapter()
    }

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.btnApply.setOnClickListener(this)
    }

    override fun bindViewModel() {
        super.bindViewModel()

        shareDataViewModel.tagsLiveData.observe(this, Observer { tags ->
            listTags = tags
            val listGroupTags = listOf(tags.filter { it.tag_type == 1 },
                tags.filter { it.tag_type == 2 },
                tags.filter { it.tag_type == 3 })
            tagGroupAdapter.setList(listGroupTags)
        })

        shareDataViewModel.error.observe(this, Observer {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
        })

        shareDataViewModel.currentSelectedTags.observe(this, Observer {
            counter = it.size
            currentSelectedTags = it
        })
    }

    private fun initAdapter() {
        tagGroupAdapter = TagGroupAdapter(listOf(), this::onItemSelected)
        viewBinding.rcvGroupTag.adapter = tagGroupAdapter
    }

    private fun onItemSelected(tag: Tag?) {
        when (tag?.obserIsSelected?.get()) {
            true -> {
                counter--
                tag.obserIsSelected.set(false)
                currentSelectedTags.removeAll { it.id == tag.id }
            }
            false -> {
                counter++
                tag.obserIsSelected.set(true)
                currentSelectedTags.add(tag)

            }
        }
        viewBinding.btnApply.text = if (counter == 0) getString(R.string.apply) else
            getString(R.string.applys, counter.toString())
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnApply -> {
                shareDataViewModel.requestSelectTag(currentSelectedTags)
                navController.popBackStack()
            }
        }
    }

    private fun onClickClear() {
        counter = 0
        viewBinding.btnApply.text = getString(R.string.apply)
        currentSelectedTags.clear()
        listTags.forEach {
            it.obserIsSelected.set(false)
        }
        val listGroupTags = listOf(listTags.filter { it.tag_type == 1 },
            listTags.filter { it.tag_type == 2 },
            listTags.filter { it.tag_type == 3 })
        tagGroupAdapter.setList(listGroupTags)
    }
}