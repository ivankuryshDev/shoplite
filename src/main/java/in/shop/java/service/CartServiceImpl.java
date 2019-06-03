package in.shop.java.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.shop.java.dao.CartDao;
import in.shop.java.model.Cart;
import in.shop.java.model.User;

@Service("cartService")
@Transactional
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDao dao;

	public Cart findById(int id) {
		return dao.findById(id);
	}

	public List<Cart> findByUser(User user) {
		return dao.findByUser(user);
	}

	public void save(Cart cart) {
		dao.save(cart);
	}

	public void deleteById(int id){
		dao.deleteById(id);
	}

	public List<Cart> findAllCarts() {
		return dao.findAllCarts();
	}
	
	public void deleteByUser(User user){
		dao.deleteByUser(user);
	}
	
	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	public String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	public void updateOrders(List<Cart> orders) throws IOException{
		for (Cart c : orders) {
			Cart cart = dao.findById(c.getId());
			cart.setChecked(true);
			dao.save(cart);
		}
	}
	
}
