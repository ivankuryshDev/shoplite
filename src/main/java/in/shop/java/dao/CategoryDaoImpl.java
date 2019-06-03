package in.shop.java.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import in.shop.java.model.Category;

@Repository("categoryDao")
public class CategoryDaoImpl extends AbstractDao<Integer, Category> implements CategoryDao{
	
	static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);

	public Category findById(int id) {
		Category category = getByKey(id);
		return category;
	}

	public Category findByName(String name) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", name));
		Category category = (Category)crit.uniqueResult();
		return category;
	}

	public void save(Category category) {
		persist(category);
	}

	public void deleteByName(String name) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", name));
		Category category = (Category)crit.uniqueResult();
		delete(category);
	}

	@SuppressWarnings("unchecked")
	public List<Category> findAllCategories() {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("id"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Category> categories = (List<Category>)crit.list();
		return categories;
	}

}
