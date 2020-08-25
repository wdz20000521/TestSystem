package com.example.teststsyem.logic.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.teststsyem.logic.model.*

@Dao
interface QuestionDao {

    @Query("SELECT * FROM danxuan WHERE id = :id")
    fun loadDanxuanFromAllByID(id: Long): danxuan

    @Query("SELECT * FROM duoxuan WHERE id = :id")
    fun loadDuoxuanFromAllByID(id: Long): duoxuan

    @Query("SELECT * FROM panduan WHERE id = :id")
    fun loadPanduanFromAllByID(id: Long): panduan

    @Query("SELECT * FROM paperDATA WHERE paperID = :id")
    fun loadPaperDATAByPaperID(id: String): List<paperDATA>

    @Insert
    fun insertNewQuestion(paperDATA: paperDATA)

    @Update
    fun setQuestionData(paperDATA: paperDATA)

    @Query("SELECT * FROM paperlist")
    fun getAllPaperList() : List<PaperList>
}