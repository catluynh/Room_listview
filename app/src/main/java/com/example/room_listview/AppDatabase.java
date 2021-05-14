package com.example.room_listview;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Address.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AddressDao addressDao();
}
