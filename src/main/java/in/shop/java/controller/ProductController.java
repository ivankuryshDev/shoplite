package in.shop.java.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import in.shop.java.model.Category;
import in.shop.java.model.FileBucket;
import in.shop.java.model.Picture;
import in.shop.java.model.Product;
import in.shop.java.model.Subcategory;
import in.shop.java.service.CategoryService;
import in.shop.java.service.PictureService;
import in.shop.java.service.ProductService;
import in.shop.java.service.SubCategoryService;
import in.shop.java.util.FileValidator;

@Controller
public class ProductController {
	
	@Autowired
	SubCategoryService subCategoryService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	PictureService pictureService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
    FileValidator fileValidator;
	
	public ProductController(){}
	
	@RequestMapping(value="/newProduct", method=RequestMethod.GET)
	public String product(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException{
		Product product = new Product();
		model.addAttribute("product", product);
		
		Picture picture = new Picture();
		model.addAttribute("picture", picture);
		
		List<Product> products = productService.findAllProducts();
		model.addAttribute("products", products);
		
		List<Picture> pictures = pictureService.findAllPictures();
		model.addAttribute("pictures", pictures);
		
		FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
		
		List<Subcategory> subCategories = subCategoryService.findAllSubCategories();
		Boolean hasSubCategories = !(subCategories.size() > 0);
		model.addAttribute("subCategory", subCategories);
		model.addAttribute("hasSubCategories", hasSubCategories);
		
		List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("category", categories);
		
		model.addAttribute("edit", false);
		
		return "newProduct";
	}
	
	@RequestMapping(value="/newProduct", method=RequestMethod.POST)
	public String saveProduct(@Valid FileBucket fileBucket, @Valid Product product, BindingResult result, ModelMap model) throws IOException{
		if (result.hasErrors()) {
			return "newProduct";
		}
		
		if(!productService.isProductUnique(product.getId(), product.getName())){
			FieldError nameError = new FieldError("product", "name", messageSource.getMessage("non.unique.name", new String[]{product.getName()}, Locale.getDefault()));
			result.addError(nameError);
			return "newProduct";
		}
		
		List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("category", categories);
        
		List<Subcategory> subCategories = subCategoryService.findAllSubCategories();
		Boolean hasSubCategories = !(subCategories.size() > 0);
		model.addAttribute("subCategory", subCategories);
		model.addAttribute("hasSubCategories", hasSubCategories);
		productService.saveProduct(fileBucket);
		model.addAttribute("success", "Product " + product.getName() + " registered successfully");
		return "registrationSuccess";
	}
	
	
	@RequestMapping(value = { "/edit-product-{name}" }, method = RequestMethod.GET)
	public String editProduct(@PathVariable String name, ModelMap model) {
		Product product = productService.findProductByName(name);
		model.addAttribute("product", product);
		
		FileBucket fileBucket = new FileBucket();
		fileBucket.setName(product.getName());
		fileBucket.setSubCategory(product.getSubCategory());
		fileBucket.setDescription(product.getDescription());
		fileBucket.setPrice(product.getPrice());
        model.addAttribute("fileBucket", fileBucket);
        
        List<Subcategory> subCategories = subCategoryService.findAllSubCategories();
		Boolean hasSubCategories = !(subCategories.size() > 0);
		model.addAttribute("subCategory", subCategories);
		model.addAttribute("hasSubCategories", hasSubCategories);
		
		List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("category", categories);
		model.addAttribute("edit", true);
        
		return "newProduct";
	}
	
	
	
	@RequestMapping(value = { "/edit-product-{name}" }, method = RequestMethod.POST)
	public String updateProduct(@Valid FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable String name) throws IOException {

		if (result.hasErrors()) {
			return "newProduct";
		}
		
		List<Subcategory> subCategories = subCategoryService.findAllSubCategories();
		Boolean hasSubCategories = !(subCategories.size() > 0);
		model.addAttribute("subCategory", subCategories);
		model.addAttribute("hasSubCategories", hasSubCategories);

		productService.updateProduct(fileBucket, name);
		
		List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("category", categories);

		model.addAttribute("success", "Product " + fileBucket.getName() + " updated successfully");
		return "registrationSuccess";
	}
	
	@RequestMapping(value="/delete-product-{name}", method=RequestMethod.GET)
	public String deleteProduct(@PathVariable String name){
		productService.deleteProductByName(name);
		return "redirect:/adminProfile";
	}
	
	@RequestMapping(value="/product-{name}", method=RequestMethod.GET)
	public String showProduct(@PathVariable String name, ModelMap model){
		Product product = productService.findProductByName(name);
		model.addAttribute("product", product);
		
		List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("category", categories);
        
		return "product";
	}
	
	@InitBinder("fileBucket")
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.setValidator(fileValidator);
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Subcategory.class, null, new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						if (!text.equals("null")) {
							Subcategory subCategory = subCategoryService.findSubCategoryById(Integer.parseInt(text));
							if (subCategory != null) {
								setValue(subCategory);
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
