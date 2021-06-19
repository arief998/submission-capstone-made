package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    var id: Int,
    var firstAirDate: String,
    var overview: String,
    var posterPath: String,
    var originalName: String,
    var voteAverage: Double,
    var favorite: Boolean = false
): Parcelable
