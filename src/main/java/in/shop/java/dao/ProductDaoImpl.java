package in.shop.java.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import in.shop.java.model.Product;
import in.shop.java.model.Subcategory;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Product> implements ProductDao{
	
	static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
	
	public Product findById(int id) {
		Product product = getByKey(id);
		return product;
	}

	public Product findByName(String name) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", name));
		Product product = (Product)crit.uniqueResult();
		return product;
	}

	public void save(Product product) {
		persist(product);
	}

	public void deleteByName(String name) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", name));
		Product product = (Product)crit.uniqueResult();
		delete(product);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAllProducts() {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("id"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Product> products = (List<Product>)crit.list();
		return products;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findProductsBySubCategory(Subcategory subCategory) {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("id"));
		crit.add(Restrictions.eq("subCategory", subCategory));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Product> products = (List<Product>)crit.list();
		return products;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findProductsByCategoryMinMaxPrice(Subcategory subCategory, Double minPrice, Double maxPrice) {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("id"));
		crit.add(Restrictions.between("price", minPrice, maxPrice));
		crit.add(Restrictions.eq("subCategory", subCategory));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Product> products = (List<Product>)crit.list();
		return products;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findProductsByNameMinMaxPrice(String name, Double minPrice, Double maxPrice) {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("id"));
		crit.add(Restrictions.between("price", minPrice, maxPrice));
		crit.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Product> products = (List<Product>)crit.list();
		return products;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findProductsByPage(int pageNumber, int pageSize) {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("name"));
		crit.setFirstResult((pageNumber - 1) * pageSize);
		crit.setMaxResults(pageSize);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Product> products = (List<Product>)crit.list();
		return products;
	}
	
	public Long findTotalCount() {
		Criteria crit = createEntityCriteria();
		crit.setProjection(Projections.rowCount());
		Long count = (Long) crit.uniqueResult();
		return count;
	}

}
