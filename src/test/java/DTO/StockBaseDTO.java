package DTO;

public class StockBaseDTO {

	private String Product1;
	private String Product2;

	public String getProduct1() {
		return Product1;
	}

	public void setProduct1(String product1) {
		Product1 = product1;
	}

	public String getProduct2() {
		return Product2;
	}

	public void setProduct2(String product2) {
		Product2 = product2;
	}

	@Override
	public String toString() {
		return "StockBaseDTO [Product1=" + Product1 + ", Product2=" + Product2 + "]";
	}
}
