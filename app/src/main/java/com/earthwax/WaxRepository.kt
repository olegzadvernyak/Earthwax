package com.earthwax

import androidx.lifecycle.LiveData

class WaxRepository(private val waxDao: WaxDao) {

    suspend fun add(wax: Wax) {
        waxDao.insert(wax)
    }

    suspend fun delete(waxes: List<Wax>) {
        waxDao.delete(waxes)
    }

    suspend fun deleteAll() {
        waxDao.deleteAll()
    }

    fun getAll(): LiveData<List<Wax>> = waxDao.getAll()

}