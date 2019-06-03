package in.shop.java.dao;

import java.util.List;

import in.shop.java.model.Cart;
import in.shop.java.model.User;

public interface CartDao {
	
	Cart findById(int id);
	
	List<Cart> findByUser(User user);
	
	void save(Cart cart);
	
	void deleteById(int id);
	
	void deleteByUser(User user);
	
	List<Cart> findAllCarts();
}
