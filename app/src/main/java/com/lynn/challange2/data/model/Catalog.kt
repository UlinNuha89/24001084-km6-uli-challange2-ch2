package com.lynn.challange2.data.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Catalog(
    var id: String = UUID.randomUUID().toString(),
    @DrawableRes
    var image: Int,
    var price: Double,
    var name: String,
    var desc: String,
    var location: String,
    var locUrl: String
) : Parcelable
