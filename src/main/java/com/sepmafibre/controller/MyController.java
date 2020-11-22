package com.sepmafibre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sepmafibre.model.Product;
import com.sepmafibre.service.ProductService;

@Controller
public class MyController {

	@Autowired
	private ProductService productService;
	
	// display list of Products
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "name", "asc", model);		
	}
	
	@GetMapping("/showNewProductForm")
	public String showNewProductForm(Model model) {
		// create model attribute to bind form data
		Product Product = new Product();
		model.addAttribute("Product", Product);
		return "new_product";
	}
	
	@GetMapping("/home")
	public String getHome() {
		return "home";
	}
	
	@GetMapping("/blog")
	public String getBlog() {
		return "blog";
	}
	
	@GetMapping("/contact")
	public String getContact() {
		return "contact";
	}
	
	@GetMapping("/product")
	public String getProduct(Model model) {
		return findPaginatedProducts(1, "name", "asc", model);
	}
	
	@GetMapping("/about")
	public String getAbout() {
		return "about";
	}
	
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("Product") Product Product) {
		// save Product to database
		productService.saveProduct(Product);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get Product from the service
		Product Product = productService.getProductById(id);
		
		// set Product as a model attribute to pre-populate the form
		model.addAttribute("Product", Product);
		return "update_product";
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable (value = "id") long id) {
		
		// call delete Product method 
		this.productService.deleteProductById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Product> page = productService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Product> listProducts = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listProducts", listProducts);
		return "index";
	}
	
	
	public String findPaginatedProducts(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Product> page = productService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Product> listProducts = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listProducts", listProducts);
		return "product";
	}
}

