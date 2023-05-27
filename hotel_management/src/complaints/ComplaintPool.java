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
	
	public void addComplaint(String id, String complaint){
		complaints.add(new Complaint(id, complaint));
		totalComplaint++;
	}
	
	public void setResolved(int index) {
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
		for(int i = 0; i < totalComplaint; i++) {
			LocalTime now = LocalTime.now();
			long diff = ChronoUnit.DAYS.between(complaints.get(i).date, now);
			
			if(diff > 7) dropComplaint(i);
		}
	}
}
