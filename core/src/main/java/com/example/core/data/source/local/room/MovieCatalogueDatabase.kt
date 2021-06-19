package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.TvShowEntity


@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class MovieCatalogueDatabase: RoomDatabase() {
    abstract fun movieFavoriteDao(): MovieFavoriteDao
    abstract fun showFavoriteDao(): ShowFavoriteDao
}