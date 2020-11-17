package com.ptithcm.admin.ext.refine

import com.ptithcm.core.model.MainCategories
import com.ptithcm.core.model.TypeCategories
import com.ptithcm.core.param.CategoriesRefine

private fun removeParentCategories(
    mainCategories: ArrayList<MainCategories>?,
    categories: ArrayList<CategoriesRefine>?
) {
    val positionParent = Triple(0, -1, -1)
    if (getItemCategory(mainCategories, positionParent)?.isChoose == true) {
        getItemCategory(mainCategories, positionParent)?.isChoose = false
        categories?.remove(
            CategoriesRefine(
                getItemCategory(
                    mainCategories,
                    positionParent
                )?.value,
                getItemCategory(mainCategories, positionParent)?.text,
                positionParent,
                TypeCategories.MAIN_CATEGORIES
            )
        )
    }
}

private fun removeCategories(
    mainCategories: ArrayList<MainCategories>?,
    position: Triple<Int, Int, Int>,
    categories: ArrayList<CategoriesRefine>?
) {
    val positionTriple = Triple(position.first, 0, -1)
    if (getItemCategory(mainCategories, positionTriple)?.isChoose == true) {
        getItemCategory(mainCategories, positionTriple)?.isChoose = false
        categories?.remove(
            CategoriesRefine(
                id = getItemCategory(mainCategories, positionTriple)?.value,
                name = getItemCategory(mainCategories, positionTriple)?.text,
                position = positionTriple, type = TypeCategories.CATEGORIES
            )
        )
    }
}

fun removeItemMainCategory(
    mainCategories: ArrayList<MainCategories>?,
    position: Triple<Int, Int, Int>,
    categories: ArrayList<CategoriesRefine>?
) {
    when {
        position.first == 0 -> {
            mainCategories?.forEachIndexed { index, it ->
                if (index > 0) {
                    it.isChoose = false
                    categories?.remove(
                        CategoriesRefine(
                            it.value,
                            it.text,
                            Triple(position.first, index, -1),
                            TypeCategories.CATEGORIES
                        )
                    )
                    it.childCategories?.mapIndexed { childIndex, item ->
                        item.isChoose = false
                        categories?.remove(
                            CategoriesRefine(
                                item.value,
                                item.text,
                                Triple(index, childIndex, -1),
                                if (childIndex == 0) TypeCategories.CATEGORIES else TypeCategories.FILTER
                            )
                        )
                    }
                }
            }
        }
        position.second == 0 -> {
            removeParentCategories(mainCategories, categories)
            mainCategories?.get(position.first)?.childCategories?.mapIndexed { index, item ->
                item.isChoose = false
                categories?.remove(
                    CategoriesRefine(
                        item.value,
                        item.text,
                        Triple(position.first, index, -1),
                        TypeCategories.FILTER
                    )
                )
            }
        }
        position.second > 0 -> {
            removeParentCategories(mainCategories, categories)
            removeCategories(mainCategories, position, categories)
        }
        else -> {
            return
        }
    }
}
