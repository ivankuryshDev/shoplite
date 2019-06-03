package in.shop.java.dao;

import java.util.List;

import in.shop.java.model.Category;
import in.shop.java.model.Subcategory;


public interface SubCategoryDao {
	
	Subcategory findById(int id);
	
	void save(Subcategory subCategory);
	
	void deleteByName(String name);
	
	List<Subcategory> findAllSubCategories();
	
	List<Subcategory> findByParent(Category category);
	
	Subcategory findByName(String name);
}
