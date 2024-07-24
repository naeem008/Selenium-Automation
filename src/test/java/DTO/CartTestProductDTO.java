package DTO;

public class CartTestProductDTO {

	private String cproductDTO1;
	private String cproductDTO2;

	public String getCproductDTO1() {
		return cproductDTO1;
	}

	public void setCproductDTO1(String cproductDTO1) {
		this.cproductDTO1 = cproductDTO1;
	}

	public String getCproductDTO2() {
		return cproductDTO2;
	}

	public void setCproductDTO2(String cproductDTO2) {
		this.cproductDTO2 = cproductDTO2;
	}

	@Override
	public String toString() {
		return "CartTestProductDTO [cproductDTO1=" + cproductDTO1 + ", cproductDTO2=" + cproductDTO2 + "]";
	}



}
