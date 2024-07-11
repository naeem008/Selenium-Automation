package JSscript.Dynamic_Discount_Selenium.Test;

import java.awt.RenderingHints.Key;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;

import Utils.URLTextUtils;
import Utils.WebDriveUtils;
import Xpath.Xpath;
import Xpath.Xpath.GoToDynamicDiscount;
import Xpath.Xpath.ProductBaseDiscount;
import Xpath.Xpath.WooCOmmerce;

public class ProductBaseDiscountTest {

	private WebDriver driver;

	@BeforeTest
	public void Login() throws InterruptedException {
		driver = WebDriveUtils.initializeDriver();

		driver.get(URLTextUtils.LoginUrl.BaseUrl);
		driver.findElement(By.xpath(Xpath.login.USERNAME_INPUT)).sendKeys("admin");
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.xpath(Xpath.login.PASSWORD_INPUT)).sendKeys("admin");
		driver.findElement(By.xpath(Xpath.login.LOGIN_BUTTON)).click();
	}

	@Test
	public void TestforProductBaseDiscount() throws InterruptedException {
		driver.findElement(By.xpath(GoToDynamicDiscount.ELEMENT_TO_HOVER)).click();
		driver.findElement(By.xpath(GoToDynamicDiscount.TARGET_ELEMENT)).click();
		driver.findElement(By.xpath(ProductBaseDiscount.BUTTON_TO_CLICK)).click();
		driver.findElement(By.xpath(ProductBaseDiscount.DISCOUNT_INPUT)).sendKeys("Product Base Discount");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("discount_type")));

		// Create a Select object
		Select select = new Select(dropdown);

		// Select the "Product Base Discount" option by visible text
		select.selectByVisibleText("Product Base Discount");

		driver.findElement(By.xpath(ProductBaseDiscount.DISCOUNT_AMOUNT_INPUT)).sendKeys("20");

		WebElement discouOption = driver.findElement(By.xpath(ProductBaseDiscount.ClickOnDsicountOption));
		discouOption.click();
		Select select2 = new Select(discouOption);

		select2.selectByVisibleText("Specific Products");

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dropdown1 = wait1
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.css-13cymwt-control")));
		System.out.println("Dropdown found and clicked.");
		dropdown1.click();

		// Wait until the input field inside the dropdown is present
		WebElement searchBox = wait1
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[id='react-select-2-input']")));
		System.out.println("Search box found.");

		// Type 'Polo' into the search box
		searchBox.sendKeys("Polo");
		System.out.println("Typed 'Polo' into the search box.");

		// Wait for the result to be present and then click it
		WebElement productOption = wait1
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Polo']")));
		System.out.println("Product option found and clicked.");
		productOption.click();

		// Click on submit
		driver.findElement(By.xpath(ProductBaseDiscount.CLICKONSUBMIT)).click();

		// Go to WooCommerce Shop

		driver.get(URLTextUtils.WooCommerceShop.WooShop);

		// Search Product
		WebElement searchproduct = driver.findElement(By.xpath(WooCOmmerce.CLICKONSEARCH));
		searchproduct.sendKeys("Polo");
		searchproduct.sendKeys(Keys.ENTER);

		driver.findElement(By.xpath(WooCOmmerce.CLICKONADDTOCART)).click();
		driver.findElement(By.xpath(WooCOmmerce.CLICKONVIEWCART)).click();
		driver.findElement(By.xpath(WooCOmmerce.PROCEEDTOCHECKOUT)).click();

		Thread.sleep(10000);

		JavascriptExecutor scrolldown = (JavascriptExecutor) driver;
		scrolldown.executeScript("window.scrollBy(0,-350)", "");
		driver.findElement(By.xpath(WooCOmmerce.CLICKONPLACEORDER)).click();
		
		Thread.sleep(1000);

		WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait11.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement productElement = (WebElement) js.executeScript(
				"return document.evaluate(\"//td[@class='woocommerce-table__product-name product-name']/a[contains(text(),'Polo')]\","
						+ " document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;");

		if (productElement == null) {
			System.out.println("Product element not found.");
			driver.quit();
			return;
		}

		System.out.println("Product element found: " + productElement.getText());

		WebElement parentRow = (WebElement) js.executeScript("return arguments[0].closest('tr');", productElement);

		if (parentRow == null) {
			System.out.println("Parent row not found.");
			driver.quit();
			return;
		}

		WebElement amountElement = (WebElement) js.executeScript(
				"return document.evaluate(\".//td[@class='woocommerce-table__product-total product-total']/span/bdi\","
						+ " arguments[0], null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;",
				parentRow);

		if (amountElement == null) {
			System.out.println("Amount element not found.");
			driver.quit();
			return;
		}

		System.out.println("Amount element found: " + amountElement.getText());

		String amountText = amountElement.getText();
		System.out.println("Amount Text is : " + amountText);

		String amountValue = amountText.replace("৳", "").replace(",", "").trim();
		System.out.println("Amount Value is : " + amountValue);

		// Convert the amount value to a double
		double amount = Double.parseDouble(amountValue);
		System.out.println("Amount after conversion is : " + amount);

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the expected amount: ");
		double expectedAmount = scanner.nextDouble();

		System.out.println("Expected Amount is : " + expectedAmount);
		scanner.close();

		Assert.assertEquals(amount, expectedAmount, "Amount does not match the expected value!");

		//driver.quit();
	}
}