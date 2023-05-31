package complaints;

import java.util.*;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class ComplaintPool {
//	Complaint Pool is where complaints are stored
//	Complaints: critique and suggestions
	
	ArrayList<Complaint> complaints = new ArrayList<Complaint>();
	int totalComplaint = 0;
	float avgRating;
	
	ComplaintPool(){
		
	}
	
	public void updateRating() {
//		Update the avgRating
		int temp = 0;
		for(int i = 0; i < totalComplaint; i++) {
			temp += complaints.get(i).rating;
		}
		avgRating = temp / totalComplaint;
	}
	
	
	public void updateRatingFast(int rating) {
//		For use after adding a complaint
		float temp = avgRating * totalComplaint;
		temp += rating;
		avgRating = temp / (totalComplaint + 1);
	}
	
	
	public void updateRatingFastN(int rating) {
//		For use after removing a complaint
		float temp = avgRating * totalComplaint;
		temp -= rating;
		avgRating = temp / (totalComplaint - 1);
		
	}
	
	public void getRating() {
		System.out.println(avgRating);
	}
	
	public void addComplaint(int roomNumber, String id, int rating, String complaint){
//		Adds complaint with the date of entering the complaint as the date
		if(rating < 1 || rating > 5) {
			System.out.println("Rating must be within 1 and 5");
			return;
		}
		complaints.add(new Complaint(roomNumber, id, rating, complaint));
		updateRatingFast(rating);
		totalComplaint++;
	}
	
	public void addComplaintManual(int roomNumber, String id, int rating, String complaint, int day, int month, int year) {
//		Adds complaint with a custom date
		if(rating < 1 || rating > 5) {
			System.out.println("Rating must be within 1 and 5");
			return;
		}
		complaints.add(new Complaint(roomNumber, id, rating, complaint, day, month, year));
		updateRatingFast(rating);
		totalComplaint++;
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
		updateRatingFastN(complaints.get(index).rating);
		complaints.remove(index);
		
		totalComplaint--;
	}
}
