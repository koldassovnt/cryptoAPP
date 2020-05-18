package com.example.cryptoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptoapp.pojo.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object{
        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context) : AppDatabase{
            synchronized(LOCK) {
                db?.let { return it }  // if it's not null return itself
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                ).build() // will be executed if it is null
                db = instance
                return instance
            }
        }
    }

    abstract fun coinPriceInfoDao() : CoinPriceInfoDao
}