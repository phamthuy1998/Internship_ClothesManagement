package com.ptithcm.ptshop.view.refine

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.ptithcm.core.model.Tag
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.databinding.FragmentRefineHomeBinding
import com.ptithcm.ptshop.ext.gone
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.ext.visible
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.refine.adapter.HomeRefineAdapter
import com.ptithcm.ptshop.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeRefineFragment:BaseFragment<FragmentRefineHomeBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_refine_home

    private val homeViewModel: HomeViewModel by sharedViewModel()

    private val param = arrayListOf<Int>()

    private val arr = arrayListOf<Tag>()

    private val refineAdapter = HomeRefineAdapter{
        if (param.remove(it?.id ?: 0).not()){
            param.add(it?.id ?: 0)
        }
        setResultTxt(param.size)
    }

    private var emptyChooseList = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emptyChooseList = arguments?.getBoolean(KEY_ARGUMENT) ?: false
    }

    override fun bindViewModel() {
        homeViewModel.tagsLiveData.observe(this, Observer {
            arr.addAll(it.sortedBy {
                it.tag_type
            })
            if (emptyChooseList){
                clearAllTag()
            }
            if(param.isNotEmpty())
                checkSelectedItem()

            refineAdapter.addList(it.groupBy { it.tag_type }.values.toList() as? ArrayList<ArrayList<Tag>> ?: arrayListOf())
        })

        homeViewModel.listTagChoose.observe(this, Observer {
            if (param.isEmpty()) {
                if (emptyChooseList){
                    clearAllTag()
                }
                param.clear()
                param.addAll(it)
                checkSelectedItem()
                setResultTxt(it.size)
            }
        })
    }

    override fun bindEvent() {
        init()
        viewBinding.fragment = this
        setUpRv()
    }

    private fun init(){
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.btnNav.gone()

            initToolBar(
                viewBinding.layoutToolbar.toolbar,
                hasBackRight = false,
                hasRight = false,
                hasLeft = false
            )

            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.refine))

            viewBinding.layoutToolbar.ivBack.apply {
                setImageResource(R.drawable.ic_black_close)
                setOnClickListener {
                    navController.popBackStack()
                }
            }

            viewBinding.layoutToolbar.tvClear.apply {
                visible()

                setOnClickListener {
                    clearAllTag()
                }
            }
        }
    }

    fun onClick(v: View?){
        when(v?.id){
            // back with list tags
            R.id.btnShowResult -> {
                if(homeViewModel.listTagChoose.value != param)
                    homeViewModel.listTagChoose.value = param
                navController.popBackStack()
            }
        }
    }

    private fun checkSelectedItem(){
        arr.forEach {
            it.isCheck = param.contains(it.id)
        }
    }

    private fun setResultTxt(size: Int){
        viewBinding.btnShowResult.text = if (size > 0) getString(R.string.show_results, size.toString()) else getString(R.string.show_result)
    }

    private fun clearAllTag(){
        param.clear()
        setResultTxt(param.size)
        arr.forEach {
            it.isCheck = false
        }
        refineAdapter.notifyDataSetChanged()
    }

    private fun setUpRv(){
        viewBinding.rvListTag.adapter = refineAdapter
    }

}