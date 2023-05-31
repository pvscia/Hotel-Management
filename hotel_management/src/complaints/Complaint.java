package complaints;

import java.time.*;

public class Complaint {
	LocalDate date;
	String complaint;
	String id;
	int roomNumber;
	int rating;
	public Complaint(int roomNumber, String id, int rating, String complaint){
		if(rating < 1 || rating > 5) {
			System.out.println("Rating must be within 1 and 5");
			return;
		}
		this.roomNumber = roomNumber;
		this.complaint = complaint;
		this.id = id;
		this.date = LocalDate.now();
		this.rating = rating;
	}
	
	public Complaint(int roomNumber, String id, int rating, String complaint, int day, int month, int year) {
		if(rating < 1 || rating > 5) {
			System.out.println("Rating must be within 1 and 5");
			return;
		}
		this.roomNumber = roomNumber;
		this.complaint = complaint;
		this.id = id;
		this.rating = rating;
		date = LocalDate.of(year, month, day);
	}
	
	public void printComplaint() {
		System.out.println("[" + date + "|" + roomNumber + "]" + id + ": " + complaint);
	}
}
