package com.example.teststsyem

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import com.example.teststsyem.logic.database.DatabaseUtil
import com.example.teststsyem.logic.model.InitViewModel
import com.example.teststsyem.ui.show.TopActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val initViewModel = InitViewModel()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        val ifNotFirstStart = getSharedPreferences("AppSettings", Context.MODE_PRIVATE).getBoolean("notFirstStart", false)
        if (ifNotFirstStart) {
            val intent = Intent(this, TopActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            initViewModel.process.observe(this) { process ->
                progressBar.progress = process.toInt()
                processtextView.text = "正在初始化数据 ${String.format("%.1f", process)} %"
            }
            thread {
                Thread.sleep(1000)
                for (i in 1 .. 1000 - 2) {
                    runOnUiThread {
                        initViewModel.addProcess(0.1)
                        Log.d("初始化界面", "${String.format("%.1f", initViewModel.process.value)}%")
                    }
                    Thread.sleep(10)
                }
                getSharedPreferences("AppSettings", Context.MODE_PRIVATE).edit {
                    putBoolean("notFirstStart", true)
                    putInt("paperSingleChoiceNum", 20)
                    putInt("paperMutibleChoiceNum", 5)
                    putInt("paperJudgeNum", 20)
                    putInt("paperQuestionTime", 30)
                }
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
                } else {
                    DatabaseUtil.packDataBase(this)
                    val intent = Intent(this, TopActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                DatabaseUtil.packDataBase(this)
                val intent = Intent(this, TopActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}