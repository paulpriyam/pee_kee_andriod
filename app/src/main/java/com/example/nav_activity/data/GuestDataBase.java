package com.example.nav_activity.data;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.nav_activity.data.dao.CartDetailsDAO;
import com.example.nav_activity.data.entity.CartDetails;

@Database(entities = CartDetails.class, exportSchema = false,version = 3)
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
//                    new PopulateDbAsync(INSTANCE).execute();

                }
            };
    /*private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CartDetailsDAO mDao;
        String[] words = {"dolphin", "crocodile", "cobra"};

        PopulateDbAsync(GuestDataBase db) {
            mDao = db.cartDetailsDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            //mDao.deleteAll();

//            for (int i = 0; i <= words.length - 1; i++) {
//                Word word = new Word(words[i]);
//            }

            CartDetails cartDetails=new CartDetails();
            cartDetails.setCartId(1223);
            cartDetails.setMerchantId("78");
            cartDetails.setMerchantName("puih");
            cartDetails.setPrice(23.0);
            cartDetails.setProductDesc("gdsehufdu hfudfgjerh");
            cartDetails.setQuantity(2);
            cartDetails.setTotalPrice(267);
            mDao.insert(cartDetails);
            return null;
        }
    }*/
}
