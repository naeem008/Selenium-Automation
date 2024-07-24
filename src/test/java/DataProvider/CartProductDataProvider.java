package DataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import Utils.ExcelUtils;

public class CartProductDataProvider {
	
	@DataProvider(name = "Cdata")
	public static Object [][] getCdata(){
		try {
			return new Object[][]{
					{
						ExcelUtils.getCdata()
					},
				};
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}	        
    }
}
