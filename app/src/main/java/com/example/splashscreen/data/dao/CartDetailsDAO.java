package com.example.splashscreen.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.splashscreen.data.entity.CartDetails;

import java.util.List;

@Dao
public interface CartDetailsDAO {
    @Query("SELECT * FROM CARTDETAILS")
    LiveData<List<CartDetails>> getCartItems();
    @Insert
    void insert(CartDetails cartDetails);
    @Delete
    void delete(CartDetails cartDetails);
}
