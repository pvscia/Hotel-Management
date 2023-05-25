package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainScene {
	public static Scene mainScene;
	VBox vbMain;
	Button btnReserve = new Button("New Reservation");
	Button btnAmenities = new Button("Order Amenities");
//	Button btnOrderFnB = new Button("Order Food and Drink");
//	Button btnFacility = new Button("Book Facility");
	Button btnDND = new Button("Do Not Disturb");
	Button btnWakeUp = new Button("Wake Up Call (06.00)");
	Button btnBill = new Button("See current charge");
	Button btnCheckOut = new Button("Log Out");
	
	Button btnRooms = new Button("Rooms");
	Button btnBookings = new Button("Reservations");
	Button btnAmenitiesAdmin = new Button("Amenities");
//	Button btnFacilitiesAdmin = new Button("Facilities");
	
	GridPane gpUserNoOrder = new GridPane();
	GridPane gpUserCheckIn = new GridPane();
	GridPane gpAdmin = new GridPane();
	
	
	
	public MainScene() {
		
		mainScene = new Scene(vbMain,1500,800);
	}
}
