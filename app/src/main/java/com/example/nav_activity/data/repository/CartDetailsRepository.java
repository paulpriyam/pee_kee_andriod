package com.example.nav_activity.data.repository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.example.nav_activity.data.GuestDataBase;
import com.example.nav_activity.data.dao.CartDetailsDAO;
import com.example.nav_activity.data.entity.CartDetails;

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
    public void update(int cartId,int qty){
        new CartDetailsRepository.updateAsyncTask(cartDetailsDAO).execute(cartId,qty);
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
    private static class updateAsyncTask extends AsyncTask<Integer, Void, Void> {
        private CartDetailsDAO mAsyncTaskDao;
        updateAsyncTask(CartDetailsDAO dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Integer... params) {
            mAsyncTaskDao.update(params[0],params[1]);
            return null;
        }
    }
}
