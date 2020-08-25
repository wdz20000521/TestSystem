package com.example.teststsyem.logic.model

import androidx.room.Entity

@Entity
data class PaperList(val paperID: String, val score: Int, val summary: Int, val danxuan_num: Int, val duoxuan_num: Int, val panduan_num: Int) {
}