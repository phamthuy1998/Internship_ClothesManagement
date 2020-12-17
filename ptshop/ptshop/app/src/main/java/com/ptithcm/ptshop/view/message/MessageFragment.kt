package com.ptithcm.ptshop.view.message

import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.databinding.FragmentMessageBinding
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.view.MainActivity

class MessageFragment : BaseFragment<FragmentMessageBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_message


    //    private lateinit var layoutManager: LinearLayoutManager
//    private var messageCollection: MessageCollection? = null
////    private  val adapter: MessageAdapter by lazy {
////        MessageAdapter()
////    }
//
    override fun bindEvent() {
        super.bindEvent()
        setupToolbar()
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar, false,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false
            )
            setupToolbar(
                viewBinding.layoutToolbar.toolbar,
                getString(R.string.message)
            )
        }
    }

//
//    private fun setupRecyclerview() {
//
//        layoutManager = LinearLayoutManager(requireContext())
//        layoutManager.reverseLayout = true
//        viewBinding.rvMessages.layoutManager = layoutManager
//        viewBinding.rvMessages.setHasFixedSize(true)
//        viewBinding.rvMessages.setItemViewCacheSize(20)
////        viewBinding.rvMessages.adapter = adapter
//        viewBinding.rvMessages.addOnScrollListener(object :
//            RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(
//                recyclerView: RecyclerView,
//                newState: Int
//            ) {
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    if (layoutManager.findFirstVisibleItemPosition() <= 0) {
//                        messageCollection?.fetchSucceededMessages(
//                            MessageCollection.Direction.NEXT,
//                            null
//                        )
//                    }
////                    if (layoutManager.findLastVisibleItemPosition() == adapter.itemCount - 1) {
////                        messageCollection?.fetchSucceededMessages(
////                            MessageCollection.Direction.PREVIOUS,
////                            null
////                        )
////                    }
//                }
//            }
//        })
//    }
//
//
//    private fun fetchNewMessage() {
//        if (messageCollection != null) {
//            if (layoutManager.findFirstVisibleItemPosition() <= 0) {
//                messageCollection?.fetchSucceededMessages(
//                    MessageCollection.Direction.NEXT,
//                    null
//                )
//            }
////            if (layoutManager.findLastVisibleItemPosition() == adapter.itemCount - 1) {
////                messageCollection?.fetchSucceededMessages(
////                    MessageCollection.Direction.PREVIOUS,
////                    null
////                )
////            }
//        }
//    }*/
}