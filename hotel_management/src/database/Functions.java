package database;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Functions {
	public static void alertUser(String alert) {
		 Alert popUp = new Alert(AlertType.WARNING);
		 popUp.setContentText(alert);
		 popUp.show();
	}
	
	public static void informUser(String inform) {
		Alert popUp = new Alert(AlertType.INFORMATION);
		 popUp.setContentText(inform);
		 popUp.show();
	}
	
	public static String generateID(String type) {
		if(type.equals("user")) {
			return "U" + (int)Math.floor(Math.random() * 10000);
		}else {
			return type + (int)Math.floor(Math.random() * 10000);
			
		}
	}
	
	public static boolean validateName(String name) {
		if(name.isEmpty()) {
			return false;
		}
		
		return name.length()>=5 && name.length()<=20;
	}
	
	public static boolean validateUsername(String uname) {
		if(uname.isEmpty()) {
			return false;
		}
		
		return uname.length()>=3 && uname.length()<=10;
	}
	
	public static boolean validatePassword(String password) {
		if(password.isEmpty()) {
			return false;
		}
		
		boolean alpha = false;
		boolean num = false;
		for(int i=0;i<password.length();i++) {
			if(Character.isLetter(password.charAt(i))) {
				alpha=true;
			}
			
			if(Character.isDigit(password.charAt(i))) {
				num=true;
			}
			
			if(alpha&&num&&password.length()>=8) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean validateConfPass(String conf,String pass) {
		if(conf.isEmpty()||pass.isEmpty()) {
			return false;
		}
		
		return pass.equals(conf);
	}
	
	public static boolean validatePhoneNum(String phoneNum) {
		
		if(phoneNum.isEmpty()) {
			return false;
		}
		
		for(int i=3;i<phoneNum.length();i++) {
			if(Character.isDigit(phoneNum.charAt(i))==false) {
				return false;
			}
		}
		
		return (phoneNum.charAt(0)=='+' && phoneNum.charAt(1)=='6'&&phoneNum.charAt(2)=='2') &&phoneNum.length()>=10;
		
	}
		
	public static boolean validateEmail(String email) {
		if(email.isEmpty() || email.charAt(0)=='@' ) {
			return false;
		}
		int counter=0;
		String checker = "@gmail.com";
		
		for(int i=email.length()-10,j=0;i<email.length();i++,j++) {
			
			if(email.charAt(i)!=checker.charAt(j)) {
				return false;
			}
		}
		
		for(int i=0;i<email.length();i++) {
			if(email.charAt(i)=='@') {
				counter++;
			}
			if(counter>1) {
				return false;
			}
		}
		return true;
	}
	
	
	
}
