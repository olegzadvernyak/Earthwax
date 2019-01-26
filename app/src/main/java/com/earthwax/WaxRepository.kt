package com.earthwax

import androidx.lifecycle.LiveData

class WaxRepository(private val waxDao: WaxDao) {

    suspend fun add(wax: Wax) {
        waxDao.insert(wax)
    }

    fun getAll(): LiveData<List<Wax>> = waxDao.getAll()

}