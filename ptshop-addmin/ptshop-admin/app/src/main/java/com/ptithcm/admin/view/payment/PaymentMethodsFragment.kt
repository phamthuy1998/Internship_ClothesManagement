package com.ptithcm.admin.view.payment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.CreditCard
import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseFragment
import com.ptithcm.admin.constant.ERROR_CODE_404
import com.ptithcm.admin.constant.KEY_ARGUMENT_BOOLEAN
import com.ptithcm.admin.constant.KEY_ARGUMENT_STRING
import com.ptithcm.admin.databinding.FragmentPaymentMethodBinding
import com.ptithcm.admin.ext.*
import com.ptithcm.admin.util.RecyclerItemTouchHelper
import com.ptithcm.admin.view.MainActivity
import com.ptithcm.admin.view.payment.adapter.PaymentMethodsAdapter
import com.ptithcm.admin.viewmodel.ListenerViewModel
import com.ptithcm.admin.viewmodel.PaymentViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentMethodsFragment : BaseFragment<FragmentPaymentMethodBinding>(),
    RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    private val paymentViewModel: PaymentViewModel by viewModel()
    private val listenerViewModel: ListenerViewModel by sharedViewModel()
    private lateinit var paymentAdapter: PaymentMethodsAdapter
    override val layoutId: Int
        get() = R.layout.fragment_payment_method

    private var isFromCheckout = false
    private var selectedCardId: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isFromCheckout = arguments?.getBoolean(KEY_ARGUMENT_BOOLEAN) ?: false
        selectedCardId = arguments?.getString(KEY_ARGUMENT_STRING) ?: ""
        observeViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.btnNav?.visibility = View.GONE
        viewBinding.refreshContainer.isRefreshing = true
        viewBinding.refreshContainer.setOnRefreshListener {
            paymentViewModel.getPaymentMethods()
        }
        paymentViewModel.getPaymentMethods()
        val itemTouchHelperCallback =
            RecyclerItemTouchHelper(
                0,
                ItemTouchHelper.START or ItemTouchHelper.END,
                this
            )
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(viewBinding.rcvPaymentMethod)
        setupToolbar()
    }

    private fun observeViewModel() {
        paymentViewModel.getPaymentMethodLiveData.observe(this, Observer {
            it.add(null)
            paymentAdapter = PaymentMethodsAdapter(it, this::eventListener, isFromCheckout, selectedCardId)
            viewBinding.rcvPaymentMethod.adapter = paymentAdapter
            viewBinding.rcvPaymentMethod.layoutManager = LinearLayoutManager(requireContext())
            viewBinding.refreshContainer.isRefreshing = false
        })
        paymentViewModel.deletePaymentMethodLiveData.observe(this, Observer {
            (requireActivity() as? MainActivity)?.isShowLoadingPayment(false)
            messageHandler?.runMessageHandler("Delete credit card success")
        })
        paymentViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                messageHandler?.runMessageErrorHandler(it.first)
            }
        })
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
                getString(R.string.payment_methods)
            )
        }
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        if (viewHolder is PaymentMethodsAdapter.PaymentMethodViewHolder) {
            paymentAdapter.getItem(position)?.id?.let { paymentViewModel.deletePaymentMethod(it) }
            (requireActivity() as? MainActivity)?.isShowLoadingPayment(true)
            paymentAdapter.removeItem(viewHolder.getAdapterPosition())
        }
    }

    private fun eventListener(creditCard: CreditCard?) {
        if (creditCard != null) {
            if (isFromCheckout) {
                listenerViewModel.changePayment.value = creditCard
                navController.popBackStack()
                return
            }
            val args = Bundle()
            args.putParcelable("card", creditCard)
            args.putInt("numberOfCards", paymentAdapter.itemCount - 2)
            navController.navigate(R.id.creditCardDetailFragment, args)
        } else {
            navController.navigate(
                R.id.creditCardDetailFragment,
                bundleOf("numberOfCards" to paymentAdapter.itemCount - 1)
            )
        }
    }
}