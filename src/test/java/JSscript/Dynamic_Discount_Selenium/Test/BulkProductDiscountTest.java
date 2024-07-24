package JSscript.Dynamic_Discount_Selenium.Test;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DTO.BulkProductDTO;
import DataProvider.BulkDataProvider;
import Utils.URLTextUtils;
import Utils.WebDriveUtils;
import Xpath.Xpath;
import Xpath.Xpath.GlobalXpath;
import Xpath.Xpath.GoToDynamicDiscount;
import Xpath.Xpath.WooCommerce;

public class BulkProductDiscountTest {

	private static WebDriver driver;

	@BeforeTest
	public void LoginTest() throws InterruptedException {
		driver = WebDriveUtils.initializeDriver();
		driver.get(URLTextUtils.LoginUrl.BaseUrl);

		driver.findElement(By.xpath(Xpath.loginXpath.USERNAME_INPUT)).sendKeys("admin");
		Thread.sleep(2500);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.xpath(Xpath.loginXpath.PASSWORD_INPUT)).sendKeys("admin");
		driver.findElement(By.xpath(Xpath.loginXpath.LOGIN_BUTTON)).click();
	}

	@Test(dataProvider = "BulkData", dataProviderClass = BulkDataProvider.class)
	public void TestBulkProduct(List<BulkProductDTO> biDtos) throws InterruptedException {
		for (BulkProductDTO bulkProductDTO : biDtos) {
			driver.findElement(By.xpath(GoToDynamicDiscount.ELEMENT_TO_HOVER)).click();
			driver.findElement(By.xpath(GoToDynamicDiscount.TARGET_ELEMENT)).click();
			driver.findElement(By.xpath(GlobalXpath.BUTTON_TO_CLICK)).click();
			driver.findElement(By.xpath(GlobalXpath.DISCOUNT_INPUT)).sendKeys("Bulk Product Discount");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("discount_type")));

			// Create a Select object
			Select select = new Select(dropdown);

			// Select the "Bulk Product Discount" option by visible text
			select.selectByVisibleText("Bulk Product Discount");

			// Select Specific Product
			WebElement discouOption = driver.findElement(By.xpath(
					"/html/body/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[3]/div/div[2]/section/div/form/div[2]/div[2]/div/select"));
			discouOption.click();
			Select select2 = new Select(discouOption);

			select2.selectByVisibleText("Specific Products");

			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement dropdown1 = wait1
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.css-13cymwt-control")));
			System.out.println("Dropdown found and clicked.");
			dropdown1.click();

			// Wait until the input field inside the dropdown is present
			WebElement searchBox = wait1.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[id='react-select-2-input']")));
			System.out.println("Search box found.");

			// Select Product from excel
			searchBox.sendKeys(bulkProductDTO.getProducts());
			System.out.println("Get product1 name from the excel");

			WebElement productOption1 = wait1.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[text()='" + bulkProductDTO.getProducts() + "']")));
			System.out.println("Product option found and clicked.");
			productOption1.click();

			// Max Min value
			driver.findElement(By.xpath(GlobalXpath.Min_Qty)).sendKeys("10");
			driver.findElement(By.xpath(GlobalXpath.Max_Qty)).sendKeys("15");
			// Discount value
			driver.findElement(By.xpath(GlobalXpath.Bulk_Dicount_Value)).sendKeys("20");
			//
			driver.findElement(By.xpath(GlobalXpath.Bulk_Title)).sendKeys("Bulk Amount Discount");

			// Click on Submit
			driver.findElement(By.xpath(GlobalXpath.CLICKONSUBMIT)).click();

			// Go to WooCommerce shop
			driver.get(URLTextUtils.WooCommerceShop.WooShop);
			// Search the product
			WebElement searchproduct = driver.findElement(By.xpath(WooCommerce.CLICKONSEARCH));
			searchproduct.sendKeys(bulkProductDTO.getProducts());
			searchproduct.sendKeys(Keys.ENTER);
			// Product Bulk Amount Input

			driver.findElement(By.xpath(GlobalXpath.Product_Bulk_Amount_Input)).sendKeys("15");
			// Add to cart
			driver.findElement(By.xpath(WooCommerce.CLICKONADDTOCART)).click();

			driver.findElement(By.xpath(WooCommerce.CLICKONADDTOCART)).click();

			Thread.sleep(100);
			// driver.findElement(By.xpath(WooCOmmerce.CLICKONVIEWCART)).click();

			driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]/div/a")).click();

			driver.findElement(By.xpath(WooCommerce.PROCEEDTOCHECKOUT)).click();

			Thread.sleep(1000);

			driver.findElement(By.xpath(WooCommerce.CLICKONPLACEORDER)).click();

			WebElement element = driver.findElement(
					By.xpath("/html/body/div[2]/div[2]/div/div[1]/main/article/div[1]/div/div/ul/li[4]/strong/span"));
			
			String textamount =  element.getText();
			String amount = textamount.replaceAll("$", "").replaceAll(",", "").trim();
			
			System.out.println(amount);
			
			Scanner scanner = new Scanner(System.in);
			String ExpectedAmount = scanner.next();
			
			Assert.assertEquals(amount, ExpectedAmount);
		}
	}
}
