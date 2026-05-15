package lv.venta.service.impl;

import java.util.ArrayList;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductFillerAndStatsService;

@Service
public class FiltetStatsProductServiceImpl implements IProductFillerAndStatsService{

	@Autowired
	private IProductRepo prodRepo;
	
	@Override
	public ArrayList<Product> filterByPriceLessThan(float threshold) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> filterByCategory(Category category) throws Exception {
		if(category == null) {
			throw new Exception("Ievades parametri nav pareizi");
			}
		
		if(prodRepo.count() == 0) {
			throw new Exception("DB nav produktu, tāpēc neko nevar filtrēt");
		}
		
		ArrayList<Product> filteredProducts = prodRepo.findbyCategory(category);
		
		if(filteredProducts.isEmpty()) {
			throw new Exception("Nav neviens produkts" + category + "kategorija");
		}
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> filterByKeyWord(String keyword) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float calculateAveragePrice() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
}
