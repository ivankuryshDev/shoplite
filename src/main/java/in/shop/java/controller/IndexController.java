package in.shop.java.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.shop.java.model.Category;
import in.shop.java.model.Product;
import in.shop.java.model.Subcategory;
import in.shop.java.service.CategoryService;
import in.shop.java.service.ProductService;
import in.shop.java.service.SubCategoryService;

@Controller
public class IndexController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SubCategoryService subCategoryService;
	
	@Autowired
	ProductService productService;
	
	public IndexController(){}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException{
		
		int sizePage = 16;
		int lastPage = (int)Math.ceil(productService.findTotalCount()/sizePage);

		model.addAttribute("currentPage", 1);
		
		model.addAttribute("lastPage", lastPage + 1);
		
		List<Product> products = productService.findProductsByPage(1, sizePage);
		model.addAttribute("products", products);

		List<Subcategory> subCategories = subCategoryService.findAllSubCategories();
		model.addAttribute("subCategory", subCategories);
		
		List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("category", categories);
		
		return "index";
		
	}
	
}
