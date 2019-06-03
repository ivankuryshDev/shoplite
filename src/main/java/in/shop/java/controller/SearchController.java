package in.shop.java.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.shop.java.model.Category;
import in.shop.java.model.Product;
import in.shop.java.model.Subcategory;
import in.shop.java.service.CategoryService;
import in.shop.java.service.ProductService;
import in.shop.java.service.SubCategoryService;

@Controller
public class SearchController {
	
	static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SubCategoryService subCategoryService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/filter/searchByCategory/{category}", method=RequestMethod.GET)
	public String searchByCategory(@PathVariable String category, Model model) throws IOException{
		
		List<Category> categories = categoryService.findAllCategories();
		model.addAttribute("categories", categories);
		logger.debug("registering. added categories to model");
		
		Boolean hasCategories = !(categories.size() > 0);
		model.addAttribute("hasCategories", hasCategories);
		
		List<Subcategory> subcategories = subCategoryService.findAllSubCategories();
		model.addAttribute("subcategories", subcategories);
		
		
		Subcategory subCategory = subCategoryService.findByName(category);
		model.addAttribute("subCategory", subCategory);
		
		List<Product> products = productService.findProductsBySubCategory(subCategory);
		model.addAttribute("products", products);
		
		model.addAttribute("isCategory", true);
		
		return "search";
	}
	
	@RequestMapping(value="/filter/searchByName/{name}", method=RequestMethod.GET)
	public String searchByName(@PathVariable String name, Model model) throws IOException{
		
		List<Category> categories = categoryService.findAllCategories();
		model.addAttribute("categories", categories);
		logger.debug("registering. added categories to model");
		
		Boolean hasCategories = !(categories.size() > 0);
		model.addAttribute("hasCategories", hasCategories);
		
		List<Subcategory> subcategories = subCategoryService.findAllSubCategories();
		model.addAttribute("subcategories", subcategories);
		
		List<Product> products = productService.findProductsByNameMinMaxPrice(name, 0.0, 1000000.0);
		model.addAttribute("products", products);
		
		Subcategory subCategory = subCategoryService.findByName(name);
		model.addAttribute("subCategory", subCategory);
		
		model.addAttribute("isCategory", false);
		
		model.addAttribute("name", name);
		
		return "search";
	}
	
	
	@RequestMapping(value = {"/filter/searchByName/searchByNameMinMax", "/filter/searchByCategory/searchByNameMinMax"})
	public @ResponseBody List<Product> searchByNameMinMax(@RequestParam(required = false, defaultValue = "name", value = "pName") String pName, 
											@RequestParam(required = false, defaultValue = "1", value = "pMinPrice") String pMinPrice, 
											@RequestParam(required = false, defaultValue = "10000", value = "pMaxPrice") String pMaxPrice){
		Double minPrice = Double.parseDouble(pMinPrice);
		Double maxPrice = Double.parseDouble(pMaxPrice);
		List<Product> product = productService.findProductsByNameMinMaxPrice(pName, minPrice, maxPrice);
		return product;
	}
	
	@RequestMapping(value = {"/filter/searchByCategory/searchByCategoryMinMax", "/filter/searchByName/searchByCategoryMinMax"})
	public @ResponseBody List<Product> searchByCategoryMinMax(@RequestParam(required = false, defaultValue = "name", value = "pName") String pName, 
											@RequestParam(required = false, defaultValue = "1", value = "pMinPrice") String pMinPrice, 
											@RequestParam(required = false, defaultValue = "10000", value = "pMaxPrice") String pMaxPrice){
		Subcategory subCategory = subCategoryService.findByName(pName);
		
		Double minPrice = Double.parseDouble(pMinPrice);
		Double maxPrice = Double.parseDouble(pMaxPrice);
		
		List<Product> product = productService.findProductsByCategoryMinMaxPrice(subCategory, minPrice, maxPrice);
		return product;
	}
	
	@RequestMapping(value={"/page/{page}"}, method=RequestMethod.GET)
	public String index(@PathVariable int page, Model model) throws IOException{
		
		int sizePage = 16;
		int lastPage = (int)Math.ceil(productService.findTotalCount()/sizePage);

		model.addAttribute("currentPage", page);
		
		model.addAttribute("lastPage", lastPage + 1);
		
		List<Product> products = productService.findProductsByPage(page, sizePage);
		model.addAttribute("products", products);

		List<Subcategory> subCategories = subCategoryService.findAllSubCategories();
		model.addAttribute("subCategory", subCategories);
		
		List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("category", categories);
		
		return "index";
	}
	
}
