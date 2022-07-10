package entities;

public class Allowance {

	private int allowanceID;
	private String Description;
	public Allowance(int allowanceID, String description) {
		this.allowanceID = allowanceID;
		Description = description;
	}
	public Allowance() {
	}
	public int getAllowanceID() {
		return allowanceID;
	}
	public void setAllowanceID(int allowanceID) {
		this.allowanceID = allowanceID;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
}
