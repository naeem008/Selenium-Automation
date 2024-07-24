package DTO;

public class BulkProductDTO {
	
	private String products;

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "BulkProductDTO [products=" + products + "]";
	}
}
