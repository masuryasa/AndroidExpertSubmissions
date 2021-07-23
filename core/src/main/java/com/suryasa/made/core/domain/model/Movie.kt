package com.suryasa.made.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
        val movieId: String,
        val title: String,
        val overview: String,
        val posterUrl: String,
        val releaseDate: String,
        val rating: String,
        val favorite: Boolean = false
): Parcelable
