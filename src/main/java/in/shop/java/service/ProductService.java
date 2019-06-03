package in.shop.java.service;

import java.io.IOException;
import java.util.List;

import in.shop.java.model.FileBucket;
import in.shop.java.model.Product;
import in.shop.java.model.Subcategory;

public interface ProductService {
	
	Product findProductById(int id);
	
	Product findProductByName(String name);
	
	void saveProduct(Product product);
	
	void deleteProductByName(String name);
	
	List<Product> findAllProducts();
		
	boolean isProductUnique(Integer id, String name);
	
	void updateProduct(FileBucket fileBucket, String name) throws IOException;
	
	void saveProduct(FileBucket fileBucket) throws IOException;
	
	List<Product> findProductsBySubCategory(Subcategory subCategory);
	
	List<Product> findProductsByCategoryMinMaxPrice(Subcategory subCategory, Double minPrice, Double maxPrice);
	
	List<Product> findProductsByNameMinMaxPrice(String name, Double minPrice, Double maxPrice);
	
	List<Product> findProductsByPage(int pageNumber, int pageSize);
	
	Long findTotalCount();
}
