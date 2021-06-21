package com.example.cashback.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "MyCart")
class Cart {
    @NotNull
    @PrimaryKey
    var id : Int =0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "imageid")
    var imageid: String? = null

    @ColumnInfo(name = "actualprice")
    var actualprice: String? = null

    @ColumnInfo(name = "offerprice")
    var offerprice: String? = null

}
