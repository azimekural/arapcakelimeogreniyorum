package com.azimut.arapcakelimeogreniyorum.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azimut.arapcakelimeogreniyorum.data.local.entity.AlphabetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlphabetDao {
    @Query("SELECT * FROM alphabet ORDER BY orderIndex ASC")
    fun getAllAlphabet(): Flow<List<AlphabetEntity>>

    @Query("SELECT * FROM alphabet WHERE id = :id")
    fun getAlphabetById(id: Int): Flow<AlphabetEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(letters: List<AlphabetEntity>)

    @Query("SELECT COUNT(*) FROM alphabet")
    suspend fun getCount(): Int
}
