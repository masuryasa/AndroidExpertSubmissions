package com.suryasa.made.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "popularmovies")
@Parcelize
data class MovieEntity (
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var id: String,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "overview")
        var overview: String,

        @ColumnInfo(name = "posterUrl")
        var posterUrl: String,

        @ColumnInfo(name = "release")
        var release: String,

        @ColumnInfo(name = "rating")
        var rating: String,

        @ColumnInfo(name = "favorite")
        var favorite: Boolean = false
) : Parcelable