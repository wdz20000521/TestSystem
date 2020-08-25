package com.example.teststsyem.logic.database

import android.annotation.SuppressLint
import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


/**
 * 将assets中的db文件拷贝到databases中
 * @author Botision.Huang
 * @Date: 2015-8-18 下午4:11:24
 * @Descp: TODO
 */
object DatabaseUtil {
    @SuppressLint("SdCardPath")
    fun packDataBase(context: Context) {
        // com.kinth.youdian 是程序的包名，请根据自己的程序调整
        // /data/data/com.kinth.youdian/databases目录是准备放 SQLite 数据库的地方，也是 Android 程序默认的数据库存储目录
        // 数据库名为 db_youdian.db
        val PACKAGE_NAME = context.packageName
        val DB_PATH = "/data/data/${PACKAGE_NAME}/databases/"
        val DB_NAME = "question.db"

        Log.d("数据库操作", "DB_PATH = ${DB_PATH}")
        // 检查 SQLite 数据库文件是否存在
        if (!File(DB_PATH + DB_NAME).exists()) {
            // 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
            val f = File(DB_PATH)
            // 如 database 目录不存在，新建该目录
            if (!f.exists()) {
                f.mkdir()
            }
            try {
                // 得到 assets 目录下我们实现准备好的 SQLite 数据库作为输入流
                val iS = context.assets.open(DB_NAME)
                // 输出流,在指定路径下生成db文件
                val os: OutputStream = FileOutputStream(DB_PATH + DB_NAME)
                // 文件写入
                val buffer = ByteArray(1024)
                var length: Int
                while (iS.read(buffer).also { length = it } > 0) {
                    os.write(buffer, 0, length)
                }

                // 关闭文件流
                os.flush()
                os.close()
                iS.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}