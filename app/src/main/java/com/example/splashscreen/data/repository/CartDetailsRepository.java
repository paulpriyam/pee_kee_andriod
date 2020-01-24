package com.example.splashscreen.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.splashscreen.data.GuestDataBase;
import com.example.splashscreen.data.dao.CartDetailsDAO;
import com.example.splashscreen.data.entity.CartDetails;

import java.util.List;

public class CartDetailsRepository {
    private CartDetailsDAO cartDetailsDAO;
    private LiveData<List<CartDetails>> items;
    public CartDetailsRepository(Application application) {
        GuestDataBase db = GuestDataBase.getDatabase(application);
        cartDetailsDAO = db.cartDetailsDAO();
        items= cartDetailsDAO.getCartItems();
    }
    public LiveData<List<CartDetails>> getCartItems() {
        return items;
    }
    public void insert (CartDetails cartDetails) {
        new CartDetailsRepository.insertAsyncTask(cartDetailsDAO).execute(cartDetails);
    }
    public void delete(CartDetails cartDetails){
        new CartDetailsRepository.deleteAsyncTask(cartDetailsDAO).execute(cartDetails);
    }
    private static class insertAsyncTask extends AsyncTask<CartDetails, Void, Void> {
        private CartDetailsDAO mAsyncTaskDao;
        insertAsyncTask(CartDetailsDAO dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final CartDetails... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class deleteAsyncTask extends AsyncTask<CartDetails, Void, Void> {
        private CartDetailsDAO mAsyncTaskDao;
        deleteAsyncTask(CartDetailsDAO dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final CartDetails... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
