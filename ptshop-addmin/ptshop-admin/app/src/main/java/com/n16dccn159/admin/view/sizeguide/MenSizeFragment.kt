package com.n16dccn159.admin.view.sizeguide

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.constant.KEY_EMPTY
import com.n16dccn159.admin.databinding.FragmentMenSizeBinding

class MenSizeFragment : BaseFragment<FragmentMenSizeBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_men_size

    private val COLUMN_WIDTH = 25
    private val FIRST_COLUMN_WIDTH = 37
    private val COLUMN_HEIGHT = 6

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
        var fixedColumn = viewBinding.fixedColumnTrouser
        //rest of the table (within a scroll view)
        var scrollablePart = viewBinding.scrollablePartTrouser
        for (i in 0..3) {
            if (i == 0) {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row, MenSizeGuide.sizes,
                    KEY_EMPTY, FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                )
            } else {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row, MenSizeGuide.sizeDetailsTrouser[i - 1],
                    MenSizeGuide.arrCountriesTrouser[i - 1], FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                )
            }
        }

        fixedColumn = viewBinding.fixedColumnTshirt
        //rest of the table (within a scroll view)
        scrollablePart = viewBinding.scrollablePartTshirt
        for (i in 0..5) {
            if (i == 0) {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row, MenSizeGuide.sizes,
                   KEY_EMPTY, FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                )
            } else {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row, MenSizeGuide.sizeDetailsTShirt[i - 1],
                    MenSizeGuide.arrCountriesTShirt[i - 1], FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                )
            }
        }
        fixedColumn = viewBinding.fixedColumnShirt
        //rest of the table (within a scroll view)
        scrollablePart = viewBinding.scrollablePartShirt
        for (i in 0..3) {
            if (i == 0) {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row, MenSizeGuide.sizesShirt,
                    KEY_EMPTY, FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                )
            } else {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row, MenSizeGuide.sizeDetailsShirt[i - 1],
                    MenSizeGuide.arrCountriesShirt[i - 1], FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                )
            }
        }
        fixedColumn = viewBinding.fixedColumnJacket
        //rest of the table (within a scroll view)
        scrollablePart = viewBinding.scrollablePartJacket
        for (i in 0..5) {
            if (i == 0) {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row, MenSizeGuide.sizesShirt,
                    KEY_EMPTY, FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                )
            } else {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row, MenSizeGuide.sizeDetailsJacket[i - 1],
                    MenSizeGuide.arrCountriesJacket[i - 1], FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                )
            }
        }
        fixedColumn = viewBinding.fixedColumnShoe
        //rest of the table (within a scroll view)
        scrollablePart = viewBinding.scrollablePartShoe
        for (i in 0..3) {
            if (i == 0) {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row, MenSizeGuide.sizeDetailsShoe[0],
                    KEY_EMPTY, FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                )
            } else {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row, MenSizeGuide.sizeDetailsShoe[i],
                    MenSizeGuide.arrCountriesShoe[i - 1], FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT)
            }
        }
    }
}