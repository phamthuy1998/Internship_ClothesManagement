package com.sg.snapshop.ext.refine

import com.sg.core.model.MainCategories
import com.sg.core.model.TypeCategories
import com.sg.core.param.CategoriesRefine

fun getItemCategory(
    mainCategories: ArrayList<MainCategories>?,
    position: Triple<Int, Int, Int>
): MainCategories? {
    return when {
        position.second == -1 && position.third == -1 -> {
            mainCategories?.get(position.first)
        }
        position.second > -1 && position.third == -1 -> {
            mainCategories?.get(position.first)?.childCategories?.get(position.second)
        }
        position.third > -1 -> {
            mainCategories?.get(position.first)?.childCategories?.get(
                position.second
            )?.childCategories?.get(position.third)
        }
        else -> {
            mainCategories?.get(position.first)
        }
    }
}

private fun removeParentCategories(mainCategories: ArrayList<MainCategories>?, position: Triple<Int, Int, Int>, categories : ArrayList<CategoriesRefine>?){
    val positionParent = Triple(position.first, 0, -1)
    if (getItemCategory(mainCategories, positionParent)?.isChoose == true) {
        getItemCategory(mainCategories, positionParent)?.isChoose = false
        categories?.remove(
            CategoriesRefine(
                getItemCategory(mainCategories, positionParent)?.value, getItemCategory(
                    mainCategories, positionParent
                )?.text, positionParent, TypeCategories.MAIN_CATEGORIES
            )
        )
    }
}

private fun removeCategories(mainCategories: ArrayList<MainCategories>?, position: Triple<Int, Int, Int>, categories : ArrayList<CategoriesRefine>?){
    val positionTriple = Triple(position.first, 0, position.third)
    if (getItemCategory(mainCategories, positionTriple)?.isChoose == true){
        getItemCategory(mainCategories, positionTriple)?.isChoose = false
        categories?.remove(CategoriesRefine(id = getItemCategory(
            mainCategories,
            positionTriple
        )?.value,
            name = getItemCategory(mainCategories, positionTriple)?.text,
            position = positionTriple, type = TypeCategories.MAIN_CATEGORIES
        ))
    }
}

private fun removeCategoriesFilter(mainCategories: ArrayList<MainCategories>?, position: Triple<Int, Int, Int>, categories : ArrayList<CategoriesRefine>?){
    val positionTriple = Triple(position.first, position.second, 0)
    if (getItemCategory(mainCategories, positionTriple)?.isChoose == true){
        getItemCategory(mainCategories, positionTriple)?.isChoose = false
        categories?.remove(CategoriesRefine(id = getItemCategory(
            mainCategories,
            positionTriple
        )?.value,
            name = getItemCategory(mainCategories, positionTriple)?.text,
            position = positionTriple, type = TypeCategories.CATEGORIES))
    }
}

fun removeItemDefaultCategory(mainCategories: ArrayList<MainCategories>?, position: Triple<Int, Int, Int>, categories : ArrayList<CategoriesRefine>?){
    when{
        position.second > 0 && position.third == -1 -> {
            removeParentCategories(mainCategories, position, categories)
            removeCategories(mainCategories, position, categories)
        }
        position.third > 0 -> {
            removeParentCategories(mainCategories, position, categories)
            removeCategoriesFilter(mainCategories, position, categories)
        }
        position.second == 0 && position.third == -1 -> {
            mainCategories?.get(position.first)?.childCategories?.forEachIndexed { index, it ->
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
                }
                it.childCategories?.forEachIndexed { index_child, item ->
                    item.isChoose = false
                  categories?.remove(CategoriesRefine(item.value, item.text, Triple(position.first, index,  index_child), if(index_child > 0) TypeCategories.FILTER else TypeCategories.CATEGORIES))
                }
            }
        }
        position.second > -1 && position.third == 0 -> {
            removeParentCategories(mainCategories, position, categories)
            mainCategories?.get(position.first)?.childCategories?.get(position.second)?.childCategories?.mapIndexed { index, item ->
                if (index > 0){
                    item.isChoose = false
                   categories?.remove(CategoriesRefine(item.value, item.text, Triple(position.first, position.second,  index), TypeCategories.FILTER))
                }
            }
        }
        else -> {
            return
        }
    }
}

fun eventRefineParam(
    mainCategories: ArrayList<MainCategories>?, positionTriple: Triple<Int, Int, Int>,
    categories : ArrayList<CategoriesRefine>?, type: TypeCategories
) {
    if (getItemCategory(mainCategories, positionTriple)?.isChoose == true) {
        categories?.add(
            CategoriesRefine(
                id = getItemCategory(mainCategories, positionTriple)?.value,
                name = getItemCategory(mainCategories, positionTriple)?.text,
                position = positionTriple, type = type
            )
        )
    } else {
        categories?.remove(
            CategoriesRefine(
                id = getItemCategory(mainCategories, positionTriple)?.value,
                name = getItemCategory(mainCategories, positionTriple)?.text,
                position = positionTriple, type = type
            )
        )
    }
}

fun clearAllChooseCategories(mainCategories: ArrayList<MainCategories>?, categoriesRefine: ArrayList<CategoriesRefine>?) {
    categoriesRefine?.forEach {
        getItemCategory(mainCategories, it.position)?.isChoose = false
    }
}

fun clearChooseCategories(mainCategories: ArrayList<MainCategories>?, categoriesRefine: ArrayList<CategoriesRefine>?) {
    categoriesRefine?.forEach {
        getItemCategory(mainCategories, it.position)?.isChoose = false
    }
    mainCategories?.first()?.isChoose = true
}