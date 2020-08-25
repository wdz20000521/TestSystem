package com.example.teststsyem.logic.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class danxuan(val question: String, val answerA: String, val answerB: String, val answerC: String, val answerD: String, val correctAnswer: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}