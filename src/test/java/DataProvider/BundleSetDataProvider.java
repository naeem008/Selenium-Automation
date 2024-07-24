package DataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import Utils.ExcelUtils;

public class BundleSetDataProvider {

	@DataProvider(name = "BundleSetData")
	public static Object [][] getBundleSetData(){
		try {
			return new Object[][]{
					{
						ExcelUtils.getBundleSetData()
					},
				};
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}	        
    }
}
