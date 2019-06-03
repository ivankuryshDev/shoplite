package in.shop.java.service;

import java.util.List;

import in.shop.java.model.Category;

public interface CategoryService {
	Category findCategoryById(int id);
	
	Category findCategoryByName(String name);
	
	void saveCategory(Category category);
	
	void deleteCategory(String category);
	
	List<Category> findAllCategories();
}
