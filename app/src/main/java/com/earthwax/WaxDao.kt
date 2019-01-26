package com.earthwax

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WaxDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wax: Wax)

    @Query("select * from waxes")
    fun getAll(): LiveData<List<Wax>>

}