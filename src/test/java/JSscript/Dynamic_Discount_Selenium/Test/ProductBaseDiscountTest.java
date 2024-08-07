package JSscript.Dynamic_Discount_Selenium.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
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
import org.testng.annotations.Test;

import DTO.LoginDTO;
import DTO.ProductDTO;
import DataProvider.LoginDataProvider;
import DataProvider.ProductDataProvider;
import Utils.URLTextUtils;
import Utils.WebDriveUtils;
import Xpath.Xpath;
import Xpath.Xpath.GlobalXpath;
import Xpath.Xpath.GoToDynamicDiscount;

import Xpath.Xpath.WooCommerce;

public class ProductBaseDiscountTest {

    private WebDriver driver;

	/*
	 * @BeforeTest public void Login() throws InterruptedException {
	 * 
	 * }
	 */

    @Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    public void LoginTest(List<LoginDTO> logdata) throws InterruptedException {
    	driver = WebDriveUtils.initializeDriver();
        driver.get(URLTextUtils.LoginUrl.BaseUrl);
        
        for(LoginDTO login : logdata)
        {
            driver.findElement(By.xpath(Xpath.loginXpath.USERNAME_INPUT)).sendKeys(login.getUsername());
            Thread.sleep(2500);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            driver.findElement(By.xpath(Xpath.loginXpath.PASSWORD_INPUT)).sendKeys(login.getPassword());
            driver.findElement(By.xpath(Xpath.loginXpath.LOGIN_BUTTON)).click();
        }
    }

    @Test (dataProvider = "ProductData" , dataProviderClass = ProductDataProvider.class )
    public void TestforProductBaseDiscount(List<ProductDTO> Product) throws IOException, InterruptedException {
    	
    	for(ProductDTO product : Product)
    	{
    		driver.findElement(By.xpath(GoToDynamicDiscount.ELEMENT_TO_HOVER)).click();
    		driver.findElement(By.xpath(GoToDynamicDiscount.TARGET_ELEMENT)).click();
    		driver.findElement(By.xpath(GlobalXpath.BUTTON_TO_CLICK)).click();
    		driver.findElement(By.xpath(GlobalXpath.DISCOUNT_INPUT)).sendKeys("Product Base Discount");

    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("discount_type")));

    		// Create a Select object
    		Select select = new Select(dropdown);

    		// Select the "Product Base Discount" option by visible text
    		select.selectByVisibleText("Product Base Discount");

    		driver.findElement(By.xpath(GlobalXpath.DISCOUNT_AMOUNT_INPUT)).sendKeys("20");

    		WebElement discouOption = driver.findElement(By.xpath(GlobalXpath.ClickOnDsicountOption));
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
    		searchBox.sendKeys(product.getProductName());
    		System.out.println("Get product name from the excel");

    		// Wait for the result to be present and then click it
    		WebElement productOption = wait1
    				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + product.getProductName() + "']")));//////////////////////
    		System.out.println("Product option found and clicked.");
    		productOption.click();

    		// Click on submit
    		driver.findElement(By.xpath(GlobalXpath.CLICKONSUBMIT)).click();

    		// Go to WooCommerce Shop

    		driver.get(URLTextUtils.WooCommerceShop.WooShop);

    		// Search Product
    		WebElement searchproduct = driver.findElement(By.xpath(WooCommerce.CLICKONSEARCH));
    		searchproduct.sendKeys(product.getProductName());
    		searchproduct.sendKeys(Keys.ENTER);
    		
    		 WebDriverWait wait111 = new WebDriverWait(driver, Duration.ofSeconds(10));
    	        wait111.until(ExpectedConditions.visibilityOfElementLocated(By.className("products")));

    	        // Find the specific product and its "Add to Cart" button
    	        List<WebElement> products = driver.findElements(By.xpath("//h2[@class='woocommerce-loop-product__title']"));
    	        for (WebElement productElement : products) {
    	            if (productElement.getText().equalsIgnoreCase(product.getProductName())) {
    	                WebElement parentElement = productElement.findElement(By.xpath("ancestor::li"));
    	                WebElement addToCartButton = parentElement.findElement(By.xpath(".//a[contains(@class, 'add_to_cart_button')]"));
    	                addToCartButton.click();
    	                break;
    	            }
    	        }
    		

    		//driver.findElement(By.xpath(WooCOmmerce.CLICKONADDTOCART)).click();
    		driver.findElement(By.xpath(WooCommerce.CLICKONVIEWCART)).click();
    		driver.findElement(By.xpath(WooCommerce.PROCEEDTOCHECKOUT)).click();

    		JavascriptExecutor scrolldown = (JavascriptExecutor) driver;
    		scrolldown.executeScript("window.scrollBy(0,-350)", "");
    		
    		Thread.sleep(5000);
    		driver.findElement(By.xpath(WooCommerce.CLICKONPLACEORDER)).click();

    		Thread.sleep(5000);

    		WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(10));
    		wait11.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
    		JavascriptExecutor js = (JavascriptExecutor) driver;

    		WebElement productElement = (WebElement) js.executeScript(
                    "return document.evaluate(\"//td[@class='woocommerce-table__product-name product-name']/a[contains(text(),'"
                            + product.getProductName() + "')]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;");

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

    		String amountValue = amountText.replace("$", "").replace(",", "").trim();
    		System.out.println("Amount Value is : " + amountValue);

    		// Convert the amount value to a double
    		double amount = Double.parseDouble(amountValue);
    		System.out.println("Amount after conversion is : " + amount);

    		// Predefined expected amount
    		System.out.println("Enter The Expected Amount");
    		Scanner scanner = new Scanner(System.in);
    		Double expectedAmount =  scanner.nextDouble();
    		scanner.close();
    		
    		
    	//	double expectedAmount = 16.00; // Set the expected amount value here

    		System.out.println("Expected Amount is : " + expectedAmount);

    		// Adding more logging before the assertion
    		System.out.println("Asserting the amount value...");
    		Assert.assertEquals(amount, expectedAmount);
    	}
		

		
	}
}