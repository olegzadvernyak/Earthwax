package com.earthwax

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [
    Wax::class
])
abstract class EarthwaxDatabase : RoomDatabase() {

    abstract fun waxDao(): WaxDao

}