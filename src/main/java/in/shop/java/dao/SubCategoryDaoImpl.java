package in.shop.java.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import in.shop.java.model.Category;
import in.shop.java.model.Subcategory;

@Repository("SubCategoryDao")
public class SubCategoryDaoImpl extends AbstractDao<Integer, Subcategory> implements SubCategoryDao{
	
	static final Logger logger = LoggerFactory.getLogger(SubCategoryDaoImpl.class);

	public Subcategory findById(int id) {
		Subcategory subCategory = getByKey(id);
		return subCategory;
	}

	public Subcategory findByName(String name) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", name));
		Subcategory subCategory = (Subcategory)crit.uniqueResult();
		return subCategory;
	}
	
	@SuppressWarnings("unchecked")
	public List<Subcategory> findByParent(Category category) {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("id"));
		crit.add(Restrictions.eq("parent", category));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Subcategory> subCategories = (List<Subcategory>)crit.list();
		return subCategories;
	}

	public void save(Subcategory subCategory) {
		persist(subCategory);
	}

	public void deleteByName(String name) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", name));
		Subcategory subCategory = (Subcategory)crit.uniqueResult();
		delete(subCategory);
	}

	@SuppressWarnings("unchecked")
	public List<Subcategory> findAllSubCategories() {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("id"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Subcategory> subCategories = (List<Subcategory>)crit.list();
		return subCategories;
	}
}
