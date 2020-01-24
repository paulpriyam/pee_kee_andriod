package com.example.splashscreen.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.splashscreen.data.dao.CartDetailsDAO;
import com.example.splashscreen.data.entity.CartDetails;

@Database(entities = CartDetails.class, exportSchema = false,version = 1)
public abstract class GuestDataBase extends RoomDatabase {
    public abstract CartDetailsDAO cartDetailsDAO();
    private static GuestDataBase INSTANCE;
    public static GuestDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GuestDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GuestDataBase.class, "GUESTDB")
                            .fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                }
            };
}
