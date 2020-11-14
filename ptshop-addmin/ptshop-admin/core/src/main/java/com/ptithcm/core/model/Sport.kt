package com.ptithcm.core.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "sport")
@Parcelize
data class Sport(var order : Int? = 0,
                var value : String? = "",
                 @PrimaryKey
                var text : String? = "") : Parcelable