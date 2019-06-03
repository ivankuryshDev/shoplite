package in.shop.java.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.shop.java.model.Cart;
import in.shop.java.model.Category;
import in.shop.java.model.Product;
import in.shop.java.model.User;
import in.shop.java.service.CartService;
import in.shop.java.service.CategoryService;
import in.shop.java.service.ProductService;
import in.shop.java.service.UserService;

@Controller
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserService userService;
	
	public CartController(){}
	
	@RequestMapping(value="/cart", method=RequestMethod.GET)
	public String cart(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException{
		User user = userService.findBySSO(cartService.getPrincipal());
		List<Cart> carts = cartService.findByUser(user);
		model.addAttribute("carts", carts);
		
		List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("category", categories);
        
		return "cart";
	}
	
	@RequestMapping(value="/addToCart-{name}", method=RequestMethod.GET)
	public String addToCart(@PathVariable String name, Model model, HttpServletResponse response, HttpServletRequest request) throws IOException{
		Product product = productService.findProductByName(name);
		User user = userService.findBySSO(cartService.getPrincipal());
		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setUser(user);
		cartService.save(cart);
		return "redirect:/cart";
	}
	
	@RequestMapping(value="/deleteFromCart-{id}", method=RequestMethod.GET)
	public String deleteFromCart(@PathVariable int id, Model model, HttpServletResponse response, HttpServletRequest request) throws IOException{
		cartService.deleteById(id);
		return "redirect:/cart";
	}
}
