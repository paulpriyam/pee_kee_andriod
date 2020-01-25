package com.example.nav_activity.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.nav_activity.data.entity.CartDetails;

import java.util.List;
@Dao
public interface CartDetailsDAO {
    @Query("SELECT * FROM CARTDETAILS")
    LiveData<List<CartDetails>> getCartItems();
    @Insert
    void insert(CartDetails cartDetails);

    @Delete
    void delete(CartDetails cartDetails);

    @Query("UPDATE CARTDETAILS SET quantity=:qty WHERE cartId=:cartId")
    void update(int cartId,int qty);
}
