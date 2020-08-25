package com.example.teststsyem.logic.model

import androidx.room.Entity

@Entity
data class paperDATA(val paperID: String, val questionID: Long, val questionType: Int, var yourAnswer: String?, var corrections: String) {
    companion object{
        val SINGLE_CHOICE = 1
        val MULTIBLE_CHOICE = 2
        val JUDGE = 3
    }
}