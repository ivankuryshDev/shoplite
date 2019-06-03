package in.shop.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.shop.java.dao.CategoryDao;
import in.shop.java.model.Category;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao dao;

	public Category findCategoryById(int id) {
		return dao.findById(id);
	}

	public Category findCategoryByName(String name) {
		Category category = dao.findByName(name);
		return category;
	}

	public void saveCategory(Category category) {
		dao.save(category);
	}

	public void deleteCategory(String category) {
		dao.deleteByName(category);
	}

	public List<Category> findAllCategories() {
		return dao.findAllCategories();
	}

}
