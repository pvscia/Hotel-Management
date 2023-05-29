package finance;

import java.util.Date;
import rooms.Room;

public class Booking {
	private String guestID;
	private Date checkIn;
	private int roomNumber;
	public Booking(String guestID, Date checkIn, int roomNumber) {
		super();
		this.guestID = guestID;
		this.checkIn = checkIn;
		this.roomNumber = roomNumber;
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
	
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
}
