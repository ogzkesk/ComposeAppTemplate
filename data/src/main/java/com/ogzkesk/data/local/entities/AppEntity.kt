package com.ogzkesk.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ogzkesk.data.util.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class AppEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0
)
