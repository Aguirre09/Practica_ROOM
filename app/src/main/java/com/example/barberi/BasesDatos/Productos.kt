package com.example.barberi.BasesDatos


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Productos (@PrimaryKey(autoGenerate = true)var id: Int ,
                 @ColumnInfo(name = "Producto") var market: String?,
                 @ColumnInfo(name = "Precio") var pesos:Int?,
                @ColumnInfo(name = "cantidad") var pas:Int?
)