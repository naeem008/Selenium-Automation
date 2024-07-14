package DTO;

public class ProductDTO {
	private String productName;
	private String expectedamount;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getExpectedamount() {
		return expectedamount;
	}

	public void setExpectedamount(String expectedamount) {
		this.expectedamount = expectedamount;
	}

	@Override
	public String toString() {
		return "ProductDataProvider [productName=" + productName + ", expectedamount=" + expectedamount + "]";
	}

}
