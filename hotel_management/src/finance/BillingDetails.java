package finance;

public class BillingDetails {
	private String billingID,description;
	private int cost,frequency;
	public BillingDetails(String billingID, String description, int cost, int frequency) {
		super();
		this.billingID = billingID;
		this.description = description;
		this.cost = cost;
		this.frequency = frequency;
	}
	public String getBillingID() {
		return billingID;
	}
	public void setBillingID(String billingID) {
		this.billingID = billingID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	
	
}
