package lv.venta.service.impl;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Category;
import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductFillerAndStatsService;

@Service
public class FiltetStatsProductServiceImpl implements IProductFillerAndStatsService {

	@Autowired
	private IProductRepo prodRepo;

	@Override
	public ArrayList<Product> filterByPriceLessThan(float threshold) throws Exception {
		if (threshold <= 0) {
			throw new Exception("Ievades dati nav korekti");
		}

		if (prodRepo.count() == 0) {
			throw new Exception("DB nav produktu, tāpēc neko nevar filtrēt");
		}

		ArrayList<Product> filteredProducts = prodRepo.findByPriceLessThan(threshold);
		if (filteredProducts.isEmpty()) {
			throw new Exception("Nav neviena produkts kura cena ir mazaka par " + threshold + "eur");
		}
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> filterByCategory(Category category) throws Exception {
		if (category == null) {
			throw new Exception("Ievades parametri nav pareizi");
		}

		if (prodRepo.count() == 0) {
			throw new Exception("DB nav produktu, tāpēc neko nevar filtrēt");
		}

		ArrayList<Product> filteredProducts = prodRepo.findByCategory(category);

		if (filteredProducts.isEmpty()) {
			throw new Exception("Nav neviens produkts" + category + "kategorija");
		}
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> filterByKeyWord(String keyword) throws Exception {
		if (keyword == null) {
			throw new Exception("Ievades parametrs nav pareizs");
		}

		if (prodRepo.count() == 0) {
			throw new Exception("DB nav produktu, tāpēc to nevar atrast");
		}

		ArrayList<Product> filteredProducts = prodRepo.findByTitleContainingOrDescriptionContaining(keyword, keyword);
		if (filteredProducts.isEmpty()) {
			throw new Exception("Nav neviens produkts " + keyword + " atrasts");
		}
		return filteredProducts;
	}

	@Override
	public float calculateAveragePrice() throws Exception {
		if (prodRepo.count() == 0) {
			throw new Exception("DB ir tukšs, nevar aprēķināt");
		}
		
		float avgPrice = prodRepo.calculateAVGPriceFromDB();
		
		return avgPrice;
	}

}
