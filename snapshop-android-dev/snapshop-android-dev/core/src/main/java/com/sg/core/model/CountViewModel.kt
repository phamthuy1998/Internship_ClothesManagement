package com.sg.core.model

import com.sg.core.vo.ItemViewModel

data class CountViewModel(val count : Int?, override val id: Int, var banner : String?): ItemViewModel