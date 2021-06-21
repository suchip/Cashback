package com.example.cashback.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface CartDao {

    @Insert
    fun addToCart(cart: Cart?)

    @Query("SELECT * FROM MyCart")
    fun getData(): List<Cart?>?

    @Query("SELECT EXISTS (SELECT 1 FROM MyCart WHERE id=:id)")
    fun isAddToCart(id: Int): Int

    @Query("select COUNT (*) from MyCart")
    fun countCart(): Int

    @Query("DELETE FROM MyCart WHERE id=:id ")
    fun deleteItem(id: Int): Int


}