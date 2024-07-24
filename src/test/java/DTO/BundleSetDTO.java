package DTO;

public class BundleSetDTO {
	 
	private String Product;

	public String getProduct() {
		return Product;
	}

	public void setProduct(String product) {
		Product = product;
	}
	
	@Override
	public String toString() {
		return "BundleSetDTO [Product=" + Product + "]";
	}

}
