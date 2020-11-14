package com.ptithcm.ptshop.view.sizeguide

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.KEY_EMPTY
import com.ptithcm.ptshop.databinding.FragmentWomenSizeBinding

class WomenSizeFragment : BaseFragment<FragmentWomenSizeBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_women_size

    private val COLUMN_WIDTH = 25
    private val FIRST_COLUMN_WIDTH = 34
    private val COLUMN_HEIGHT = 5

    companion object {
        private val fragment = WomenSizeFragment()

        fun newInstance(): WomenSizeFragment {
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
        var row: TableRow

        var fixedColumn = viewBinding.fixedColumn
        //rest of the table (within a scroll view)
        var scrollablePart = viewBinding.scrollablePart
        for (i in 0..10) {
            if (i == 0) {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row, WomenSizeGuide.sizes,
                    KEY_EMPTY, FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                )
            } else {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row,
                    WomenSizeGuide.sizeDetails[i - 1], WomenSizeGuide.arrCountries[i - 1],
                    FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                )
            }
        }

        fixedColumn = viewBinding.fixedColumnShoe
        //rest of the table (within a scroll view)
        scrollablePart = viewBinding.scrollablePartShoe
        for (i in 0..3) {
            row = TableRow(context)
            sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
            sizeGuideFunc?.addRow(
                fixedColumn, scrollablePart, row,
                WomenSizeGuide.sizeDetailsShoe[i], WomenSizeGuide.arrCountriesShoe[i],
                FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
            )
        }
        fixedColumn = viewBinding.fixedColumnRing
        //rest of the table (within a scroll view)
        scrollablePart = viewBinding.scrollablePartRing
        for (i in 0..3) {
            row = TableRow(context)
            sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
            sizeGuideFunc?.addRow(
                fixedColumn, scrollablePart, row,
                WomenSizeGuide.sizeDetailsRing[i], WomenSizeGuide.arrCountries[i],
                FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
            )
        }
        fixedColumn = viewBinding.fixedColumnGlove
        //rest of the table (within a scroll view)
        scrollablePart = viewBinding.scrollablePartGlove
        for (i in 0..1) {
            row = TableRow(context)
            sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
            sizeGuideFunc?.addRow(
                fixedColumn, scrollablePart, row,
                WomenSizeGuide.sizeDetailsGlove[i], WomenSizeGuide.arrGlove[i],
                FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
            )
        }
        fixedColumn = viewBinding.fixedColumnBelt
        //rest of the table (within a scroll view)
        scrollablePart = viewBinding.scrollablePartBelt
        for (i in 0..4) {
            if (i == 0) {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row, WomenSizeGuide.beltSizes,
                    KEY_EMPTY, FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                )
            } else {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                if (i != 4) {
                    sizeGuideFunc?.addRow(
                        fixedColumn, scrollablePart, row,
                        WomenSizeGuide.sizeDetailsBelt[i - 1], WomenSizeGuide.arrCountries[i - 1],
                        FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                    )
                } else {
                    sizeGuideFunc?.addRow(
                        fixedColumn, scrollablePart,
                        row, WomenSizeGuide.sizeDetailsBelt[i - 1], "EUROPE",
                        FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                    )
                }
            }
        }
        fixedColumn = viewBinding.fixedColumnHat
        //rest of the table (within a scroll view)
        scrollablePart = viewBinding.scrollablePartHat
        for (i in 0..3) {
            if (i == 0) {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row, WomenSizeGuide.sizesHat,
                    KEY_EMPTY, FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                )
            } else {
                row = TableRow(context)
                sizeGuideFunc?.initRow(row, wrapWrapTableRowParams)
                sizeGuideFunc?.addRow(
                    fixedColumn, scrollablePart, row,
                    WomenSizeGuide.sizeDetailsHat[i - 1], WomenSizeGuide.arrCountriesHat[i - 1],
                    FIRST_COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_HEIGHT
                )
            }
        }
    }
}