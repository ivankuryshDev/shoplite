package in.shop.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.shop.java.dao.SubCategoryDao;
import in.shop.java.model.Category;
import in.shop.java.model.Subcategory;

@Service("subCategoryService")
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService {
	
	@Autowired
	private SubCategoryDao dao;

	public Subcategory findSubCategoryById(int id) {
		return dao.findById(id);
	}

	public List<Subcategory> findByParent(Category category) {
		return dao.findByParent(category);
	}

	public void saveSubCategory(Subcategory subCategory) {
		dao.save(subCategory);
	}

	public void deleteSubCategory(String subCategory) {
		dao.deleteByName(subCategory);
	}

	public List<Subcategory> findAllSubCategories() {
		return dao.findAllSubCategories();
	}

	public Subcategory findByName(String name) {
		return dao.findByName(name);
	}
}
