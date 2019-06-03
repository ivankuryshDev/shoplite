package in.shop.java.service;

import java.util.List;

import in.shop.java.model.Category;
import in.shop.java.model.Subcategory;

public interface SubCategoryService {
	
	Subcategory findSubCategoryById(int id);
	
	List<Subcategory> findByParent(Category category);
	
	void saveSubCategory(Subcategory subCategory);
	
	void deleteSubCategory(String subCategory);
	
	List<Subcategory> findAllSubCategories();
	
	Subcategory findByName(String name);
}
