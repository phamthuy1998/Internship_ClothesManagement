package com.n16dccn159.core.model

data class GenderFilter(var order : Int? = 0,
                        var value : Int? = 0,
                        var text : String? = "",
                        var isChoose : Boolean = false)