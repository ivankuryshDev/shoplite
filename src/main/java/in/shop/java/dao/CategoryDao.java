package in.shop.java.dao;

import java.util.List;

import in.shop.java.model.Category;

public interface CategoryDao {
	
	Category findById(int id);

	Category findByName(String name);
	
	void save(Category category);
	
	void deleteByName(String name);
	
	List<Category> findAllCategories();
}
