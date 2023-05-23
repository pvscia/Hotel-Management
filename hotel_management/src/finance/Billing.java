package finance;

import java.util.Date;

public class Billing {
	private String billingID,reservationID,staffID;
	private Date time;
	public Billing(String billingID, String reservationID, String staffID, Date time) {
		super();
		this.billingID = billingID;
		this.reservationID = reservationID;
		this.staffID = staffID;
		this.time = time;
	}
	public String getBillingID() {
		return billingID;
	}
	public void setBillingID(String billingID) {
		this.billingID = billingID;
	}
	public String getReservationID() {
		return reservationID;
	}
	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}
	public String getStaffID() {
		return staffID;
	}
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
	
}
