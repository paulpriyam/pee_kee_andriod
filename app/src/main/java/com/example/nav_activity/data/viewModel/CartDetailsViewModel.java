package com.example.nav_activity.data.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.nav_activity.Cart;
import com.example.nav_activity.data.entity.CartDetails;
import com.example.nav_activity.data.repository.CartDetailsRepository;

import java.util.List;
public class CartDetailsViewModel extends AndroidViewModel {
    private CartDetailsRepository mRepository;
    private LiveData<List<CartDetails>> items;
    public CartDetailsViewModel (Application application) {
        super(application);
        mRepository = new CartDetailsRepository(application);
        items = mRepository.getCartItems();
    }
    public LiveData<List<CartDetails>> getCartItems() { return items; }
    public void insert(CartDetails cartDetails) { mRepository.insert(cartDetails); }
    public void delete(CartDetails cartDetails){ mRepository.delete(cartDetails);}
    public void update(int cartId,int qty){
        mRepository.update(cartId,qty);
    }
}
