package in.shop.java.dao;

import java.util.List;

import in.shop.java.model.Product;
import in.shop.java.model.Subcategory;

public interface ProductDao {
	
	Product findById(int id);
	
	Product findByName(String name);
	
	void save(Product product);
	
	void deleteByName(String name);
	
	List<Product> findAllProducts();
	
	List<Product> findProductsBySubCategory(Subcategory subCategory);
	
	List<Product> findProductsByCategoryMinMaxPrice(Subcategory subCategory, Double minPrice, Double maxPrice);
	
	List<Product> findProductsByNameMinMaxPrice(String name, Double minPrice, Double maxPrice);
	
	List<Product> findProductsByPage(int pageNumber, int pageSize);
	
	Long findTotalCount();

}
