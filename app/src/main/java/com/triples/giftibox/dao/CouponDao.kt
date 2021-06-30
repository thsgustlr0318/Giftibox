package com.triples.giftibox.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.triples.giftibox.data.Coupon

@Dao
interface CouponDao {
    /*
    @Query("SELECT * FROM coupon")
    fun getAll(): List<Coupon>
    @Insert(onConflict = REPLACE)
    fun insert(coupon: Coupon)

    @Query("DELETE from coupon")
    fun deleteAll()

     */
}