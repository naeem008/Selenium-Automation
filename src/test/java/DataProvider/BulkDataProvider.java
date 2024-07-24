package DataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import Utils.ExcelUtils;

public class BulkDataProvider {

	@DataProvider(name = "BulkData")
	public static Object [][] getBulknData(){
		try {
			return new Object[][]{
					{
						ExcelUtils.getBulkData()
					},
				};
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}	        
    }
}
