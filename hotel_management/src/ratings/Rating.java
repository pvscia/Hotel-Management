package ratings;

import java.sql.Date;
import java.time.*;

public class Rating {
	private Date date;
	private String complaint;
	private int roomNumber;
	private int rating;
	public Rating(int roomNumber,  int rating, String complaint, Date date){
		if(rating < 1 || rating > 5) {
			System.out.println("Rating must be within 1 and 5");
			return;
		}
		this.roomNumber = roomNumber;
		this.complaint = complaint;
		this.date = date;
		this.rating = rating;
	}
	
	public Rating(int roomNumber,  int rating, String complaint, int day, int month, int year) {
		if(rating < 1 || rating > 5) {
			System.out.println("Rating must be within 1 and 5");
			return;
		}
		this.roomNumber = roomNumber;
		this.complaint = complaint;
		this.rating = rating;
//		date = LocalDate.of(year, month, day);
	}
	
	public void printComplaint() {
//		System.out.println("[" + date + "|" + roomNumber + "]" + id + ": " + complaint);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}


	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}