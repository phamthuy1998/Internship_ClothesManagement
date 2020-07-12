package com.sg.snapshop.view.sizeguide

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.databinding.FragmentKidSizeBinding

class KidSizeFragment : BaseFragment<FragmentKidSizeBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_kid_size

    private val COLUMN_WIDTH = 25
    private val FIRST_COLUMN_WIDTH = 36
    private val COLUMN_HEIGHT = 7

    companion object {
        private val fragment = MenSizeFragment()

        fun newInstance(): MenSizeFragment {
            return fragment
        }
    }

    //util method
    private var sizeGuideFunc: SizeGuideFunc? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sizeGuideFunc = SizeGuideFunc(requireContext())
    }

    override fun bindEvent() {
        super.bindEvent()
        val wrapWrapTableRowParams = TableRow.LayoutParams(
            TableLayout.LayoutParams.WRAP_CONTENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        var row : TableRow
        val fixedColumn = viewBinding.fixedColumn
        //rest of the table (within a scroll view)
        val scrollablePart = viewBinding.scrollablePart
        for (i in 0..2) {
            row = TableRow(context)
            sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
            sizeGuideFunc?.addRow(
                fixedColumn, scrollablePart, row, KidSizeGuide.arrX[i],
                KidSizeGuide.arrY[i], FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
            )
        }

    }

}

