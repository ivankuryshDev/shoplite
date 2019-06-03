package in.shop.java.service;

import java.io.IOException;
import java.util.List;

import in.shop.java.model.Cart;
import in.shop.java.model.User;

public interface CartService {
	
	Cart findById(int id);
	
	List<Cart> findByUser(User user);
	
	void save(Cart cart);
	
	void deleteById(int id);
	
	void deleteByUser(User user);
	
	List<Cart> findAllCarts();
	
	String getPrincipal();
	
	void updateOrders(List<Cart> orders) throws IOException;
}
