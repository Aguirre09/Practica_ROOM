package com.example.barberi.BasesDatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Barberos (@PrimaryKey(autoGenerate = true)var id: Int ,
               @ColumnInfo(name = "Name") var name: String?,
               @ColumnInfo(name = "Password") var passw:String?
)


