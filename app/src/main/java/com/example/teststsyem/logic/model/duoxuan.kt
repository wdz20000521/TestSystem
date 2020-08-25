package com.example.teststsyem.logic.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class duoxuan(val question: String, val answerA: String, val answerB: String, val answerC: String, val answerD: String, val correctAnswer: String, val answerACorrection: String, val answerBCorrection: String, val answerCCorrection: String, val answerDCorrection: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}