package finance;

import java.util.Date;
import rooms.Room;

public class Booking {
	private String ID,guestID,billingID;
	private Date checkIn,checkOut;
	private Room room;
	public Booking(String iD, String guestID, String billingID, Date checkIn, Date checkOut, Room room) {
		super();
		ID = iD;
		this.guestID = guestID;
		this.billingID = billingID;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.room = room;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getGuestID() {
		return guestID;
	}
	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}
	public String getBillingID() {
		return billingID;
	}
	public void setBillingID(String billingID) {
		this.billingID = billingID;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	
	
	
}
