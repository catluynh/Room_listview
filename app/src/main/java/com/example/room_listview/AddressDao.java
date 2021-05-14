package com.example.room_listview;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AddressDao {
    @Query("SELECT * FROM Address")
    List<Address> getAll();

    @Query("SELECT * FROM Address WHERE id = :addressId ")
    Address getById(Integer addressId);

    @Insert
    void addAddress(Address address);

    @Delete
    void deleteAddress(Address address);

}
