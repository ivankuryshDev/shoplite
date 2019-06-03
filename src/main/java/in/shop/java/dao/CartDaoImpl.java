package in.shop.java.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import in.shop.java.model.Cart;
import in.shop.java.model.User;

@Repository("cartDao")
public class CartDaoImpl extends AbstractDao<Integer, Cart> implements CartDao {
	
	static final Logger logger = LoggerFactory.getLogger(CartDaoImpl.class);

	public Cart findById(int id) {
		Cart cart = getByKey(id);
		return cart;
	}

	@SuppressWarnings("unchecked")
	public List<Cart> findByUser(User user) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("user", user));
		crit.add(Restrictions.eq("checked", false));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Cart> carts = (List<Cart>)crit.list();
		return carts;
	}

	public void save(Cart cart) {
		persist(cart);
	}

	public void deleteById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Cart cart = (Cart)crit.uniqueResult();
		delete(cart);
	}
	
	@SuppressWarnings("unchecked")
	public void deleteByUser(User user) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("user", user));
		List<Cart> carts = (List<Cart>)crit.list();
		deleteP(carts);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cart> findAllCarts() {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("id"));
		crit.add(Restrictions.eq("checked", true));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Cart> carts = (List<Cart>)crit.list();
		return carts;
	}
	
}
