package complaints;

import java.time.*;

public class Complaint {
	LocalDate date;
	String complaint;
	String id;
	int roomNumber;
	boolean resolved = false;
	public Complaint(int roomNumber, String id, String complaint){
		this.roomNumber = roomNumber;
		this.complaint = complaint;
		this.id = id;
		this.date = LocalDate.now();
	}
	
	public Complaint(int roomNumber, String id, String complaint, int day, int month, int year) {
		this.roomNumber = roomNumber;
		this.complaint = complaint;
		this.id = id;
		date = LocalDate.of(year, month, day);
	}
	
	public void setResolved() {
		resolved = true;
	}
	
	public void printComplaint() {
		System.out.println("[" + date + "|" + roomNumber + "]" + id + ": " + complaint);
	}
}
