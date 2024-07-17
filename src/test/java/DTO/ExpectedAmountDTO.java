package DTO;

public class ExpectedAmountDTO {

	private String expectedAmount;

	public String getExpectedAmount() {
		return expectedAmount;
	}

	public void setExpectedAmount(String expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	@Override
	public String toString() {
		return "ExpectedAmountDTO [expectedAmount=" + expectedAmount + "]";
	}
}
