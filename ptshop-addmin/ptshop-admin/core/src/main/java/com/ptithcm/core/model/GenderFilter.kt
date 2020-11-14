package com.ptithcm.core.model

data class GenderFilter(var order : Int? = 0,
                        var value : Int? = 0,
                        var text : String? = "",
                        var isChoose : Boolean = false)