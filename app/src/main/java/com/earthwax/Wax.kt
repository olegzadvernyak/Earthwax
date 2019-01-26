package com.earthwax

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "waxes")
data class Wax(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String
)