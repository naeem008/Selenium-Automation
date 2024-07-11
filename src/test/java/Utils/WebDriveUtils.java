package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriveUtils {

	public static WebDriver initializeDriver() {
		
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;

	}
}
