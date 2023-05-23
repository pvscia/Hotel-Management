package complaints;

import java.time.*;

public class Complaint {
	LocalDate date;
	String complaint;
	String id;
	boolean resolved = false;
	public Complaint(String id, String complaint){
		this.complaint = complaint;
		this.id = id;
		this.date = LocalDate.now();
	}
	
	public void setResolved() {
		resolved = true;
	}
	
	public void printComplaint() {
		System.out.println(id + ": " + complaint);
	}
}