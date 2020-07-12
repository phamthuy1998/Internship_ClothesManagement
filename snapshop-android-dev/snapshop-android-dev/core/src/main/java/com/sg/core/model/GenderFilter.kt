package com.sg.core.model

data class GenderFilter(var order : Int? = 0,
                        var value : Int? = 0,
                        var text : String? = "",
                        var isChoose : Boolean = false)