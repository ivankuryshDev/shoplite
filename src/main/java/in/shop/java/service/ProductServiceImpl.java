package in.shop.java.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import in.shop.java.dao.PictureDao;
import in.shop.java.dao.ProductDao;
import in.shop.java.model.FileBucket;
import in.shop.java.model.Picture;
import in.shop.java.model.Product;
import in.shop.java.model.Subcategory;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao dao;
	
	@Autowired
	private PictureDao Pdao;
	
	public Product findProductById(int id) {
		return dao.findById(id);
	}
	
	public Product findProductByName(String name) {
		Product product = dao.findByName(name);
		return product;
	}
	
	public void saveProduct(Product product) {
		dao.save(product);
	}
	
	public void deleteProductByName(String name) {
		dao.deleteByName(name);
	}
	
	public List<Product> findAllProducts() {
		return dao.findAllProducts();
	}

	public boolean isProductUnique(Integer id, String name) {
		Product product = findProductByName(name);
		return (product == null || ((id != null) && (product.getId() == id)));
	}
	
	public void saveProduct(FileBucket fileBucket) throws IOException{
        Product product = new Product();
        //MultipartFile multipartFile = fileBucket.getFile();
        List<MultipartFile> multipartFiles = fileBucket.getFiles();
        product.setName(fileBucket.getName());
        product.setDescription(fileBucket.getDescription());
        product.setSubCategory(fileBucket.getSubCategory());
        product.setPrice(fileBucket.getPrice());
        product.setPictures(fileBucket.getPictures());
        dao.save(product);
        boolean ismain = true;
        for(MultipartFile f:multipartFiles){
        	if(f.isEmpty()){
        		continue;
        	}
        	Picture picture = new Picture();
        	picture.setPicture(f.getBytes());
        	picture.setProduct(product);
        	if(ismain == true){
        		picture.setMain(true);
        		ismain = false;
        	}else{
        		picture.setMain(false);
        	}
        	
        	Pdao.save(picture);
        }
    }
	
	public void updateProduct(FileBucket fileBucket, String name) throws IOException{
        //MultipartFile multipartFile = fileBucket.getFile();
        List<MultipartFile> multipartFiles = fileBucket.getFiles();
        //Product a = productService.findProductById(id);
		Product product = dao.findByName(name);
		product.setName(fileBucket.getName());
		product.setDescription(fileBucket.getDescription());
		product.setSubCategory(fileBucket.getSubCategory());
		product.setPrice(fileBucket.getPrice());
		product.setPictures(null);
		product.setPictures(fileBucket.getPictures());
        dao.save(product);
        boolean ismain = false;
        for(MultipartFile f:multipartFiles){
        	if(f.isEmpty()){
        		continue;
        	}
        	Picture picture = new Picture();
        	picture.setPicture(f.getBytes());
        	picture.setProduct(product);
        	if(ismain == true){
        		picture.setMain(true);
        		ismain = false;
        	}else{
        		picture.setMain(false);
        	}
        	
        	Pdao.save(picture);
        }
    }

	public List<Product> findProductsBySubCategory(Subcategory subCategory) {
		return dao.findProductsBySubCategory(subCategory);
	}


	public List<Product> findProductsByCategoryMinMaxPrice(Subcategory subCategory, Double minPrice, Double maxPrice) {
		return dao.findProductsByCategoryMinMaxPrice(subCategory, minPrice, maxPrice);
	}
	
	public List<Product> findProductsByNameMinMaxPrice(String name, Double minPrice, Double maxPrice){
		return dao.findProductsByNameMinMaxPrice(name, minPrice, maxPrice);
	}

	public List<Product> findProductsByPage(int pageNumber, int pageSize) {
		return dao.findProductsByPage(pageNumber, pageSize);
	}

	public Long findTotalCount() {
		return dao.findTotalCount();
	}
}
