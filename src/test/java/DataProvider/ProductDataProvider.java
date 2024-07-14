package DataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import Utils.ExcelUtils;

public class ProductDataProvider {
	
	@DataProvider(name = "ProductData")
	public static Object [][] ProductData(){
		try {
			return new Object[][]{
					{
						ExcelUtils.getProductDTO()
					},
				};
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}	        
    }
	
}
