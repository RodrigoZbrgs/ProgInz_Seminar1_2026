package lv.venta.service;

import java.util.ArrayList;
import java.util.Locale.Category;

import lv.venta.model.Product;

public interface IProductFillerAndStatsService {
	//TODO abstraktas funkcijas
	// filtret produktus pec cenas, kas mazaka par ienakoso mainiga vertibu
	public abstract ArrayList<Product> filterByPriceLessThan(float threshold) throws Exception;
	
	//filtret produktus pec tipa
	public abstract ArrayList<Product> filterByCategory(Category category) throws Exception;
	//filtret produktus pec atslegas varda, kas varetu but title vai ari description
	public abstract ArrayList<Product> filterByKeyWord(String keyword) throws Exception;
	//aprekinat videjo vertibu produktu cenam
	public abstract float calculateAveragePrice() throws Exception;
	
}
