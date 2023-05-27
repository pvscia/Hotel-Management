package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScene {
	public static Scene mainScene;
	VBox vbMain = new VBox();
	Button btnReserve = new Button("New Reservation");
	Button btnAmenities = new Button("Order Amenities");
//	Button btnOrderFnB = new Button("Order Food and Drink");
	Button btnFacility = new Button("Book Facility");
	Button btnDND = new Button("Do Not Disturb");
	Button btnWakeUp = new Button("Wake Up Call (06.00)");
	Button btnLogOut = new Button("Log Out");
	
	Button btnRooms = new Button("Rooms");
	Button btnBookings = new Button("Reservations");
	Button btnAmenitiesAdmin = new Button("Amenities");
	Button btnFacilitiesAdmin = new Button("Facilities");
	Button btnLogOutA = new Button("Log Out");
	
	public static GridPane gpUserCheckIn = new GridPane();
	public static GridPane gpAdmin = new GridPane();

	
	
	
	public MainScene(Stage stg) {
		gpUserCheckIn.add(btnAmenities, 0, 0);
		gpUserCheckIn.add(btnFacility, 1, 0);
		gpUserCheckIn.add(btnDND, 0, 1);
		gpUserCheckIn.add(btnWakeUp, 1, 1);
		gpUserCheckIn.add(btnLogOut, 0, 2);
		gpUserCheckIn.setAlignment(Pos.CENTER);

		gpAdmin.add(btnReserve, 0, 0);
		gpAdmin.add(btnRooms, 0, 1);
		gpAdmin.add(btnBookings, 0, 2);
		gpAdmin.add(btnAmenitiesAdmin, 0, 3);
		gpAdmin.add(btnFacilitiesAdmin, 0, 4);
		gpAdmin.add(btnLogOutA, 0, 5);
		gpAdmin.setAlignment(Pos.CENTER);

		vbMain.getChildren().add(gpUserCheckIn);
		vbMain.getChildren().add(gpAdmin);
		vbMain.setAlignment(Pos.CENTER);
		mainScene = new Scene(vbMain,400,400);
		
		btnAmenities.setOnAction(e->{
			stg.setScene(OrderAmenities.orderAmenitiesScene);
			stg.setTitle("Order Amenities");
		});
		
		btnAmenitiesAdmin.setOnAction(e->{
			stg.setScene(AmenitiesAdmin.amenitiesAdminScene);
			stg.setTitle("Amenities");
		});
		
		btnLogOut.setOnAction(e->{
			stg.setScene(LoginScene.loginScene);
			stg.setTitle("Login");
		});
		
		btnLogOutA.setOnAction(e->{
			stg.setScene(LoginScene.loginScene);
			stg.setTitle("Login");
		});
	}
}
