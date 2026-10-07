package com.azimut.arapcakelimeogreniyorum.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azimut.arapcakelimeogreniyorum.data.local.entity.VocabularyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VocabularyDao {
    @Query("SELECT * FROM vocabulary ORDER BY id ASC")
    fun getAllVocabulary(): Flow<List<VocabularyEntity>>

    @Query("SELECT * FROM vocabulary WHERE category = :category ORDER BY id ASC")
    fun getVocabularyByCategory(category: String): Flow<List<VocabularyEntity>>

    @Query("SELECT * FROM vocabulary WHERE id = :id")
    fun getVocabularyById(id: Int): Flow<VocabularyEntity?>

    @Query("SELECT * FROM vocabulary WHERE isFavorite = 1 ORDER BY id ASC")
    fun getFavoriteVocabulary(): Flow<List<VocabularyEntity>>

    @Query("SELECT DISTINCT category FROM vocabulary")
    fun getAllCategories(): Flow<List<String>>

    @Query("SELECT * FROM vocabulary WHERE arabicText LIKE '%' || :query || '%' OR turkishMeaning LIKE '%' || :query || '%' OR transliteration LIKE '%' || :query || '%'")
    fun searchVocabulary(query: String): Flow<List<VocabularyEntity>>

    @Query("UPDATE vocabulary SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

    @Query("UPDATE vocabulary SET masteryLevel = :masteryLevel WHERE id = :id")
    suspend fun updateMasteryLevel(id: Int, masteryLevel: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<VocabularyEntity>)

    @Query("SELECT COUNT(*) FROM vocabulary")
    suspend fun getCount(): Int
}
