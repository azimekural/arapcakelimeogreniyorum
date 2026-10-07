package com.azimut.arapcakelimeogreniyorum.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azimut.arapcakelimeogreniyorum.data.local.entity.UserProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProgressDao {
    @Query("SELECT * FROM user_progress")
    fun getAllProgress(): Flow<List<UserProgressEntity>>

    @Query("SELECT * FROM user_progress WHERE itemType = :itemType AND itemId = :itemId LIMIT 1")
    fun getProgressByItem(itemType: String, itemId: Int): Flow<UserProgressEntity?>

    @Query("SELECT * FROM user_progress WHERE itemType = :itemType")
    fun getProgressByType(itemType: String): Flow<List<UserProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(progress: UserProgressEntity)

    @Query("SELECT COUNT(*) FROM user_progress WHERE itemType = :itemType AND isCompleted = 1")
    fun getCompletedCountByType(itemType: String): Flow<Int>

    @Query("DELETE FROM user_progress")
    suspend fun resetAllProgress()
}
