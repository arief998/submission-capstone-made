package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShowFavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertShow(tvShow: List<TvShowEntity>)

    @Delete
    fun deleteShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM TvShowEntity")
    fun getAllShowFavorite(): Flow<List<TvShowEntity>>

    @Update
    fun updateShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM TvShowEntity WHERE favorite = 1")
    fun getListShowFavorite(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM TvShowEntity WHERE id = :showId")
    fun getShowById(showId: Int): Flow<List<TvShowEntity>>
}