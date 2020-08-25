package com.example.teststsyem.logic.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teststsyem.logic.model.*

@Database(version = 1, entities = [paperDATA::class, danxuan::class, duoxuan::class, panduan::class, PaperList::class])
abstract class QuestionDatabase : RoomDatabase() {
    abstract fun questionDao() : QuestionDao

    companion object {
        private var instance: QuestionDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): QuestionDatabase{
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext, QuestionDatabase::class.java, "question")
                .build()
                .apply {
                instance = this
            }
        }
    }
}