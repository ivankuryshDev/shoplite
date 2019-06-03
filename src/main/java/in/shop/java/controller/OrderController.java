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
import in.shop.java.model.User;
import in.shop.java.service.CartService;
import in.shop.java.service.CategoryService;
import in.shop.java.service.ProductService;
import in.shop.java.service.UserService;

@Controller
public class OrderController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserService userService;
	
	public OrderController(){}
	
	@RequestMapping(value="/deleteFromOrder-{id}", method=RequestMethod.GET)
	public String deleteFromOrder(@PathVariable int id, Model model, HttpServletResponse response, HttpServletRequest request) throws IOException{
		cartService.deleteById(id);
		return "redirect:/allOrders";
	}
	
	@RequestMapping(value="/allOrders", method=RequestMethod.GET)
	public String orders(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException{
		
		List<Cart> orders = cartService.findAllCarts();
		model.addAttribute("orders", orders);
		
		List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("category", categories);
        
		return "order";
	}
	
	@RequestMapping(value="/order", method=RequestMethod.GET)
	public String order(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException{
		User user = userService.findBySSO(cartService.getPrincipal());
		List<Cart> orders = cartService.findByUser(user);
		
		if(orders != null){
			cartService.updateOrders(orders);
		}
		
		List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("category", categories);
        model.addAttribute("success", "Your order registered successfully!");
		return "registrationSuccess";
	}
}
