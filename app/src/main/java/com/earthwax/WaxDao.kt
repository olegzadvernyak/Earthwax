package com.earthwax

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WaxDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wax: Wax)

    @Query("select * from waxes")
    fun getAll(): LiveData<List<Wax>>

    @Delete
    suspend fun delete(waxes: List<Wax>)

    @Query("delete from waxes")
    suspend fun deleteAll()

}