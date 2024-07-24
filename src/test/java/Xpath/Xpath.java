package Xpath;

public class Xpath {

	public class loginXpath {

		public static final String USERNAME_INPUT = "/html/body/div/form/p[1]/input";
		public static final String PASSWORD_INPUT = "/html/body/div/form/div/div/input";
		public static final String LOGIN_BUTTON = "/html/body/div/form/p[3]/input[1]";
	}

	public class GoToDynamicDiscount {
		public static final String ELEMENT_TO_HOVER = "/html/body/div[1]/div[1]/div[2]/ul/li[11]/a/div[3]";
		public static final String TARGET_ELEMENT = "/html/body/div[1]/div[1]/div[2]/ul/li[11]/ul/li[4]/a";
	}

	public class GlobalXpath {
		public static final String BUTTON_TO_CLICK = "/html/body/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[3]/div/div[1]/div[2]/button/span";
		public static final String DISCOUNT_INPUT = "/html/body/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[3]/div/div[2]/section/div/form/div[1]/div[1]/div/input";
		public static final String DISCOUNT_AMOUNT_INPUT = "/html/body/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[3]/div/div[2]/section/div/form/div[2]/div[3]/div/input";
		public static final String PRODUCT_TYPE_DROPDOWN = "product_type";
		public static final String ClickOnDsicountOption = "/html/body/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[3]/div/div[2]/section/div/form/div[2]/div[4]/div/select";
		public static final String SEARCH_INPUT = "input[id='react-select-2-input']";
		public static final String OPTION_TO_SELECT = "//div[contains(@class, 'css-9jq23d') and text()='Polo']";
		public static final String CLICKONSUBMIT = "/html/body/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[3]/div/div[2]/section/div/form/div[3]/button/span";
		public static final String percentage_fixedprice = "/html/body/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[3]/div/div[2]/section/div/form/div[2]/div[2]/div/div[2]/div/input";
		public static final String Min_Qty = "/html/body/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[3]/div/div[2]/section/div/form/div[2]/div[4]/div/div[1]/div/input";
		public static final String Max_Qty = "/html/body/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[3]/div/div[2]/section/div/form/div[2]/div[4]/div/div[2]/div/input";
		public static final String Bulk_Title = "/html/body/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[3]/div/div[2]/section/div/form/div[2]/div[4]/div/div[5]/div/input";
		public static final String Bulk_Dicount_Value = "/html/body/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[3]/div/div[2]/section/div/form/div[2]/div[4]/div/div[4]/div/input";
		public static final String Product_Bulk_Amount_Input = "/html/body/div[2]/div[2]/div/div[2]/main/div[2]/div[2]/form/div/input";
		public static final String Bundle_Product_Quantity = "/html/body/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[3]/div/div[2]/section/div/form/div[2]/div[3]/div/div[1]/div/input";
		public static final String Bundle_Discount_Value = "/html/body/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[3]/div/div[2]/section/div/form/div[2]/div[4]/div/div[3]/div/input";
	}

	public class WooCommerce {
		public static final String CLICKONSEARCH = "/html/body/div[2]/header/div[1]/div[2]/div/form/input[1]";
		// public static final String CLICKONPOLO =
		// "/html/body/div[2]/div[2]/div/div[2]/main/article/header/h2/a";
		public static final String CLICKONADDTOCART = "/html/body/div[2]/div[2]/div/div[2]/main/div[2]/div[2]/form/button";
		public static final String CLICKONVIEWCART = "/html/body/div[2]/div[2]/div/div[2]/main/ul/li[2]/a[3]";
		public static final String PROCEEDTOCHECKOUT = "/html/body/div[2]/div[2]/div/div[2]/main/article/div[1]/div/div[2]/div/div/a";
		public static final String CLICKONPLACEORDER = "/html/body/div[2]/div[2]/div/div[1]/main/article/div[1]/div/form[2]/div[2]/div/div/button";

	}

}