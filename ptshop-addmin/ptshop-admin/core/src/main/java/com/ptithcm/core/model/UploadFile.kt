package com.ptithcm.core.model

import android.os.Parcelable
import androidx.databinding.ObservableBoolean
import kotlinx.android.parcel.Parcelize
import java.io.File

@Parcelize
data class UploadFile(
    val file: File? = null,
    var editFile: File? = null,
    var trimFile: File? = null,
    var canSelect: Boolean = true,
    var isVideo: Boolean? = false,
    var obserIsSelected: ObservableBoolean = ObservableBoolean(false)
) : Parcelable