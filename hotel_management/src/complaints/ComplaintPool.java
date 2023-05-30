package complaints;

import java.util.*;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class ComplaintPool {
	ArrayList<Complaint> complaints = new ArrayList<Complaint>();
	int totalComplaint = 0;
	int resolvedComplaint = 0;
	
	ComplaintPool(){
		
	}
	
	public void addComplaint(int roomNumber, String id, String complaint){
//		Adds complaint with the date of entering the complaint as the date
		
		complaints.add(new Complaint(roomNumber, id, complaint));
		totalComplaint++;
	}
	
	public void addComplaintManual(int roomNumber, String id, String complaint, int day, int month, int year) {
//		Adds complaint with a custom date
		complaints.add(new Complaint(roomNumber, id, complaint, day, month, year));
		totalComplaint++;
	}
	
	public void setResolved(int index) {
//		Set a complaint as resolved
		complaints.get(index).setResolved();
		resolvedComplaint++;
	}
	
	public void viewAllComplaint() {
		for(int i = 0; i < totalComplaint; i++) {
			complaints.get(i).printComplaint();
		}
	}
	
	public void viewComplaint(int index) {
		complaints.get(index).printComplaint();
	}
	
	public void dropComplaint(int index) {
		if(complaints.get(index).resolved) resolvedComplaint--;
		complaints.remove(index);
		totalComplaint--;
		
	}
	
	public void checkComplaintWeek() {
//		Deletes all complaints older than 7 days
		
		for(int i = 0; i < totalComplaint; i++) {
			LocalDate now = LocalDate.now();
			long diff = ChronoUnit.DAYS.between(complaints.get(i).date, now);
			if(diff > 7) dropComplaint(i);
		}
	}
}
