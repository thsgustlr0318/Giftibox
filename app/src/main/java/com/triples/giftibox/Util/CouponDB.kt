package com.triples.giftibox.Util

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.triples.giftibox.dao.CouponDao
import com.triples.giftibox.data.Coupon

@Database(entities = [Coupon::class], version = 1)
abstract class CouponDB: RoomDatabase() {
    abstract fun CouponDao(): CouponDao

    companion object {
        private var INSTANCE: CouponDB? = null

        fun getInstance(context: Context): CouponDB? {
            if (INSTANCE == null) {
                synchronized(Coupon::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        CouponDB::class.java, "coupon.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}