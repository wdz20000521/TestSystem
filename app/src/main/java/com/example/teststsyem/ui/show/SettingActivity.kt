package com.example.teststsyem.ui.show

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.edit
import com.example.teststsyem.R
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val sh = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        singleChoiceNumber.setText(sh.getInt("paperSingleChoiceNum", 0).toString())
        mutableChoiceNumber.setText(sh.getInt("paperMutibleChoiceNum", 0).toString())
        judgeNumber.setText(sh.getInt("paperJudgeNum", 0).toString())
        questionTime.setText(sh.getInt("paperQuestionTime", 0).toString())
    }

    override fun finish() {
        super.finish()
        getSharedPreferences("AppSettings", Context.MODE_PRIVATE).edit {
            putInt("paperSingleChoiceNum", singleChoiceNumber.text.toString().toInt())
            putInt("paperMutibleChoiceNum", mutableChoiceNumber.text.toString().toInt())
            putInt("paperJudgeNum", judgeNumber.text.toString().toInt())
            putInt("paperQuestionTime", questionTime.text.toString().toInt())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}