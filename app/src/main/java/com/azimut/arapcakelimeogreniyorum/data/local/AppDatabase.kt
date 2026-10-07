package com.azimut.arapcakelimeogreniyorum.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.azimut.arapcakelimeogreniyorum.data.local.dao.AlphabetDao
import com.azimut.arapcakelimeogreniyorum.data.local.dao.DiacriticDao
import com.azimut.arapcakelimeogreniyorum.data.local.dao.QuizQuestionDao
import com.azimut.arapcakelimeogreniyorum.data.local.dao.UserProgressDao
import com.azimut.arapcakelimeogreniyorum.data.local.dao.VocabularyDao
import com.azimut.arapcakelimeogreniyorum.data.local.entity.AlphabetEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.DiacriticEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.QuizQuestionEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.UserProgressEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.VocabularyEntity
import com.azimut.arapcakelimeogreniyorum.data.local.seed.InitialDataSeed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        AlphabetEntity::class,
        DiacriticEntity::class,
        VocabularyEntity::class,
        QuizQuestionEntity::class,
        UserProgressEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun alphabetDao(): AlphabetDao
    abstract fun diacriticDao(): DiacriticDao
    abstract fun vocabularyDao(): VocabularyDao
    abstract fun quizQuestionDao(): QuizQuestionDao
    abstract fun userProgressDao(): UserProgressDao

    suspend fun ensureSeeded() {
        if (alphabetDao().getCount() == 0) {
            alphabetDao().insertAll(InitialDataSeed.alphabetList)
        }
        if (diacriticDao().getCount() == 0) {
            diacriticDao().insertAll(InitialDataSeed.diacriticList)
        }
        if (vocabularyDao().getCount() == 0) {
            vocabularyDao().insertAll(InitialDataSeed.vocabularyList)
        }
        if (quizQuestionDao().getCount() == 0) {
            quizQuestionDao().insertAll(InitialDataSeed.quizQuestionList)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(
            context: Context,
            scope: CoroutineScope = CoroutineScope(Dispatchers.IO),
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "arabic_learning_db",
                )
                    .addCallback(
                        object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                scope.launch(Dispatchers.IO) {
                                    INSTANCE?.ensureSeeded()
                                }
                            }
                        }
                    )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
