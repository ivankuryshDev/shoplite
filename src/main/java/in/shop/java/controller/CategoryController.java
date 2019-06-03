package in.shop.java.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import in.shop.java.model.Category;
import in.shop.java.model.FileBucket;
import in.shop.java.model.Subcategory;
import in.shop.java.service.CategoryService;
import in.shop.java.service.SubCategoryService;

@Controller
public class CategoryController extends HttpServlet{

	private static final long serialVersionUID = -8171404596604819379L;

	static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	SubCategoryService subCategoryService;
	
	public CategoryController(){};
	
	@RequestMapping(value="/newCategory", method=RequestMethod.GET)
	public String test(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException{
		
		List<Category> categories = categoryService.findAllCategories();
		model.addAttribute("category", categories);
		logger.debug("registering. added categories to model");
		
		Boolean hasCategories = !(categories.size() > 0);
		model.addAttribute("hasCategories", hasCategories);
		
		Category category = new Category();
		model.addAttribute("newCategory", category);
		
		List<Subcategory> subcategories = subCategoryService.findAllSubCategories();
		model.addAttribute("subcategories", subcategories);
		
		
		System.out.println("Test 0 out ======== Test 1 out ======= Test 2 out");
		System.err.println("Test 0 err ======== Test 1 err ======= Test 2 err");	
		
		return "newCategory";
	}
	
	@RequestMapping(value="/newCategory", method=RequestMethod.POST)
	public String saveCategory(@Valid @ModelAttribute("newCategory") Category newCategory, BindingResult result, ModelMap model) throws IOException{
		if (result.hasErrors()) {
			System.out.println("validation errors");
			return "index";
		}
		
		
		List<Category> categories = categoryService.findAllCategories();
		Boolean hasCategories = !(categories.size() > 0);
		model.addAttribute("category", categories);
		model.addAttribute("hasCategories", hasCategories);
		categoryService.saveCategory(newCategory);
		
		return "redirect:/adminProfile";
	}
	
	@RequestMapping(value="/newSubCategory", method=RequestMethod.GET)
	public String newSubCategory(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException{
		
		List<Category> categories = categoryService.findAllCategories();
		model.addAttribute("category", categories);
		logger.debug("registering. added categories to model");
		
		Boolean hasCategories = !(categories.size() > 0);
		model.addAttribute("hasCategories", hasCategories);
		
		List<Subcategory> subcategories = subCategoryService.findAllSubCategories();
		model.addAttribute("subcategories", subcategories);
		
		Subcategory subCategory = new Subcategory();
		model.addAttribute("subCategory", subCategory);
		
		FileBucket fileBucket = new FileBucket();
		model.addAttribute("fileBucket", fileBucket);
		return "newSubCategory";
	}
	
	@RequestMapping(value="/newSubCategory", method=RequestMethod.POST)
	public String saveSubCategory(@Valid Subcategory subCategory, BindingResult result, ModelMap model) throws IOException{
		if (result.hasErrors()) {
			System.out.println("validation errors");
			return "index";
		}
		
		List<Category> categories = categoryService.findAllCategories();
		Boolean hasCategories = !(categories.size() > 0);
		model.addAttribute("category", categories);
		model.addAttribute("hasCategories", hasCategories);
		
		subCategoryService.saveSubCategory(subCategory);
		
		return "redirect:/adminProfile";
	}
	
	@RequestMapping(value="/delete-category-{name}", method=RequestMethod.GET)
	public String deleteCategory(@PathVariable String name){
		categoryService.deleteCategory(name);
		return "redirect:/adminProfile";
	}
	
	@RequestMapping(value="/delete-subCategory-{name}", method=RequestMethod.GET)
	public String deleteSubCategory(@PathVariable String name){
		subCategoryService.deleteSubCategory(name);
		return "redirect:/adminProfile";
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Category.class, null, new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						if (!text.equals("null")) {
							Category category = categoryService.findCategoryById(Integer.parseInt(text));
							if (category != null) {
								setValue(category);
							} else {
								setValue(null);
							}
						} else {
							setValue(null);
						}
					}
		});
	}
}
