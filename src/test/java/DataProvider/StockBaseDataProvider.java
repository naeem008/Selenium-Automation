package DataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import Utils.ExcelUtils;

public class StockBaseDataProvider {
	
	@DataProvider(name = "StockBaseData")
	public static Object [][] getStockData(){
		try {
			return new Object[][]{
					{
						ExcelUtils.getStockData()
					},
				};
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}	        
    }
}
