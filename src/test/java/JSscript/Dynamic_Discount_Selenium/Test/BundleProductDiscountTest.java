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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DTO.BulkProductDTO;
import DataProvider.BulkDataProvider;
import DataProvider.BundleSetDataProvider;
import Utils.URLTextUtils;
import Utils.WebDriveUtils;
import Xpath.Xpath;
import Xpath.Xpath.GlobalXpath;
import Xpath.Xpath.GoToDynamicDiscount;
import Xpath.Xpath.WooCommerce;

public class BundleProductDiscountTest {

	private WebDriver driver;

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
	public void TestBulkProduct(List<BulkProductDTO> Bundle) throws InterruptedException {
		for (BulkProductDTO BundleData : Bundle) {
			driver.findElement(By.xpath(GoToDynamicDiscount.ELEMENT_TO_HOVER)).click();
			driver.findElement(By.xpath(GoToDynamicDiscount.TARGET_ELEMENT)).click();
			driver.findElement(By.xpath(GlobalXpath.BUTTON_TO_CLICK)).click();
			driver.findElement(By.xpath(GlobalXpath.DISCOUNT_INPUT)).sendKeys("Bundle(Set) Discount");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("discount_type")));

			// Create a Select object
			Select select = new Select(dropdown);

			// Select the "Bulk Product Discount" option by visible text
			select.selectByVisibleText("Bundle (Set) Discount");

			WebElement discouOption = driver.findElement(By.xpath(
					"/html/body/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[3]/div/div[2]/section/div/form/div[2]/div[2]/div/select"));
			discouOption.click();
			Select select2 = new Select(discouOption);

			select2.selectByVisibleText("Specific Products");

			WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement dropdown11 = wait11
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.css-13cymwt-control")));
			System.out.println("Dropdown found and clicked.");
			dropdown11.click();

			// Wait until the input field inside the dropdown is present
			WebElement searchBox = wait11.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[id='react-select-2-input']")));
			System.out.println("Search box found.");

			// Select Product
			searchBox.sendKeys(BundleData.getProducts());
			System.out.println("Get product1 name from the excel");

			// Wait for the result to be present and then click it
			WebElement productOption = wait11.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[text()='" + BundleData.getProducts() + "']")));
			System.out.println("Product option found and clicked.");
			productOption.click();
			
			Thread.sleep(100);

			driver.findElement(By.name("quantity")).sendKeys("10");

			driver.findElement(By.xpath(GlobalXpath.Bundle_Discount_Value)).sendKeys("50");

			driver.findElement(By.xpath(GlobalXpath.CLICKONSUBMIT)).click();

			driver.get(URLTextUtils.WooCommerceShop.WooShop);

			WebElement searchproduct = driver.findElement(By.xpath(WooCommerce.CLICKONSEARCH));
			searchproduct.sendKeys(BundleData.getProducts());
			searchproduct.sendKeys(Keys.ENTER);
			
			//Input Quantity
			WebElement inputQuantity = driver.findElement(By.name("quantity"));
			inputQuantity.clear();
			inputQuantity.sendKeys("10");
			

			driver.findElement(By.xpath(WooCommerce.CLICKONADDTOCART)).click();

			WebElement searchproduct1 = driver.findElement(By.xpath(WooCommerce.CLICKONSEARCH));
			searchproduct1.sendKeys(BundleData.getProducts());
			searchproduct1.sendKeys(Keys.ENTER);

			driver.findElement(By.xpath(WooCommerce.CLICKONADDTOCART)).click();

			Thread.sleep(100);
			// driver.findElement(By.xpath(WooCOmmerce.CLICKONVIEWCART)).click();

			driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]/div/a")).click();

			driver.findElement(By.xpath(WooCommerce.PROCEEDTOCHECKOUT)).click();

			Thread.sleep(1000);

			driver.findElement(By.xpath(WooCommerce.CLICKONPLACEORDER)).click();

			Thread.sleep(10000);
			// Taking Total Price from Order Details Page

			WebElement tiElement = driver.findElement(
					By.xpath("/html/body/div[2]/div[2]/div/div[1]/main/article/div[1]/div/div/ul/li[4]/strong/span"));

			String textString = tiElement.getText();

			String Amount = textString.replace("$", "").replace(",", "").trim();
			System.out.println("Amount Value is : " + Amount);

			System.out.print("Enter your String Expectenamount = ");

			Scanner scanner = new Scanner(System.in);
			String Expectenamount = scanner.next();

			Assert.assertEquals(Expectenamount, Amount);
		}

	}
}
