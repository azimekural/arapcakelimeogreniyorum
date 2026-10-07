package com.azimut.arapcakelimeogreniyorum.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azimut.arapcakelimeogreniyorum.data.local.entity.DiacriticEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiacriticDao {
    @Query("SELECT * FROM diacritics ORDER BY orderIndex ASC")
    fun getAllDiacritics(): Flow<List<DiacriticEntity>>

    @Query("SELECT * FROM diacritics WHERE id = :id")
    fun getDiacriticById(id: Int): Flow<DiacriticEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(diacritics: List<DiacriticEntity>)

    @Query("SELECT COUNT(*) FROM diacritics")
    suspend fun getCount(): Int
}
