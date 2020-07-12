package com.sg.snapshop.view.refine

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sg.core.model.Colour
import com.sg.core.param.ColourRefine
import com.sg.core.param.RefineParam
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.KEY_ARGUMENT
import com.sg.snapshop.databinding.FragmentColoursBinding
import com.sg.snapshop.ext.initToolBar
import com.sg.snapshop.ext.initToolbar
import com.sg.snapshop.ext.setupToolbar
import com.sg.snapshop.ext.visible
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.home.StoryDetailActivity
import com.sg.snapshop.view.refine.adapter.ColourRecyclerViewAdapter
import com.sg.snapshop.viewmodel.ProductFilterViewModel
import com.sg.snapshop.viewmodel.RefineViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ColoursFragment : BaseFragment<FragmentColoursBinding>(), View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_colours

    private val viewModel: ProductFilterViewModel by sharedViewModel(from = { requireActivity() })
    private val refineViewModel : RefineViewModel by sharedViewModel(from = {requireActivity()})

    val numberSelected = ObservableField(0)
    private lateinit var adapter: ColourRecyclerViewAdapter
    private var refineParam : RefineParam? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refineParam = arguments?.getParcelable(KEY_ARGUMENT)
    }

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.data = this
        (requireActivity() as? BaseActivity<*>)?.apply {
            initToolbar(  hasBackRight = false, hasRight = false, hasLeft = false)
            setupToolbar(getString(R.string.colours), true)
            when(this){
                is MainActivity -> {
                    viewBinding.layoutToolbar.tvClear.visible()
                    viewBinding.layoutToolbar.tvClear.setOnClickListener(this@ColoursFragment)
                }
                is StoryDetailActivity -> {
                    viewBinding.layoutToolbar.tvClear.visible()
                    viewBinding.layoutToolbar.tvClear.setOnClickListener(this@ColoursFragment)
                }
            }
        }
        initEvent()
        initAdapter()
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.productFilterLiveData.observe(this, Observer {
           initChoose(it.colour, refineParam)
            adapter.colours = it.colour
            adapter.notifyDataSetChanged()
        })
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tvClear -> {
                clearColoursSelected()
            }
            R.id.btnApply -> {
                coloursSelected()
                refineViewModel.refineLiveData.value = Pair(refineParam ?: RefineParam(), false)
                navController.popBackStack()
            }
        }
    }

    private fun initChoose(colours : ArrayList<Colour>?, refineParam: RefineParam?){
        colours?.map {
            it.isChoose = refineParam?.colours?.contains(ColourRefine(it.code, it.value)) == true
        }
        numberSelected.set(colours?.filter { it.isChoose }?.size ?: 0)
    }

    private fun initAdapter() {
        adapter = ColourRecyclerViewAdapter(arrayListOf(), this::listener)
        viewBinding.rvColours.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewBinding.rvColours.adapter = adapter
    }

    private fun listener(colour : Colour?, position : Int){
        adapter.colours?.get(position)?.isChoose = !(colour?.isChoose ?: false)
        adapter.notifyItemChanged(position)
        numberSelected.set(adapter.colours?.filter { it.isChoose }?.size ?: 0)
    }

    private fun initEvent(){
        viewBinding.btnApply.setOnClickListener(this)
    }

    private fun coloursSelected(){
        refineParam?.colours = arrayListOf()
        adapter.colours?.forEach {
            if (it.isChoose){
                refineParam?.colours?.add(ColourRefine(it.code, it.value))
            }
        }
    }

    private fun clearColoursSelected(){
        adapter.colours?.map {
            it.isChoose = false
        }
        adapter.notifyDataSetChanged()
        numberSelected.set(0)
    }

}