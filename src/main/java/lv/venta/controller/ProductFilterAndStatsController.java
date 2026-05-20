package lv.venta.controller;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.model.Category;
import lv.venta.model.Product;
import lv.venta.service.IProductFillerAndStatsService;

@Controller
@RequestMapping("/product/filter")
public class ProductFilterAndStatsController {

	@Autowired
	private IProductFillerAndStatsService service;
	
@GetMapping("/price/{threshold}")
public String getControllerFilterByPrice(@PathVariable(name = "threshold") float threshold, Model model) {
	try {
	ArrayList<Product> resultFromDB = service.filterByPriceLessThan(threshold);
	model.addAttribute("package", resultFromDB);
	model.addAttribute("info", "Produkti, kuru cena ir mazaka par " + threshold + " eur");
	return "show-all-products";
	
	
	} catch (Exception e) {
		model.addAttribute("package", e.getMessage());
		return "error-page";
	}
}
	
@GetMapping("/category/{category}")
public String getControllerFilterByCategory(@PathVariable(name = "category") Category category, Model model) {
	try {
		ArrayList<Product> resultFromDB = service.filterByCategory(category);
		model.addAttribute("package", resultFromDB);
		model.addAttribute("info", "Produkts ar kategoriju " + category);
		return "show-all-products";
	} catch (Exception e) {
		model.addAttribute("package", e.getMessage());
		return "error-page";
	}
}

@GetMapping("/keyword/{keyword}")
public String getControllerFilterByKeyword(@PathVariable(name = "keyword") String keyword, Model model) {
	try {
		ArrayList<Product> resultFromDB = service.filterByKeyWord(keyword);
		model.addAttribute("package", resultFromDB);
		model.addAttribute("info", "Produkts ar atslēgas vārdu: " + keyword);
		return "show-all-products";
	} catch (Exception e) {
		model.addAttribute("package", e.getMessage());
		return "error-page";
	}
}

@GetMapping("/avgprice")
public String getControllerAveragePriceCalculator(Model model) {
	try {
		float avgPrice = service.calculateAveragePrice();
		model.addAttribute("package", avgPrice);
		model.addAttribute("info", "Vidējā cena: " + avgPrice);
		return "data";
	} catch (Exception e) {
		model.addAttribute("package", e.getMessage());
		return "error-page";
	}
}


}
