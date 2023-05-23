package finance;

import java.util.Date;
import rooms.Room;

public class Booking {
	private String id,guestID;
	private Date checkIn,checkOut;
	private int roomNumber;
	public Booking(String id, String guestID, Date checkIn, Date checkOut, int roomNumber) {
		super();
		this.id = id;
		this.guestID = guestID;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.roomNumber = roomNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGuestID() {
		return guestID;
	}
	public void setGuestID(String guestID) {
		this.guestID = guestID;
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
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
}
