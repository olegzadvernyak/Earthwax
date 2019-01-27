package com.earthwax

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "waxes")
data class Wax(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is Wax) return this.id == other.id
        return false
    }

    override fun hashCode(): Int {
        return id.toInt()
    }

}