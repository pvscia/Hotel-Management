package view;

import java.sql.ResultSet;

import admin_view.AmenitiesAdmin;
import admin_view.FacilityAdmin;
import admin_view.ReservationsAdminScene;
import admin_view.RoomsAdminScene;
import admin_view.UsersView;
import database.Connections;
import database.Functions;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;

public class MainScene {
	public static Scene mainScene;
	public static VBox vbMain = new VBox();
	Button btnReserve = new Button("New Reservation");
	Button btnAmenities = new Button("Order Amenities");
//	Button btnOrderFnB = new Button("Order Food and Drink");
	Button btnFacility = new Button("Book Facility");
	Button btnDND = new Button("Do Not Disturb");
	Button btnWakeUp = new Button("Wake Up Call (06.00)");
	Button btnComplaint = new Button("Complaints");
	Button btnLogOut = new Button("Log Out");
	
	Button btnRooms = new Button("Rooms");
	Button btnBookings = new Button("Reservations");
	Button btnAmenitiesAdmin = new Button("Amenities");
	Button btnFacilitiesAdmin = new Button("Facilities");
	Button btnUsers = new Button("Users");
	Button btnLogOutA = new Button("Log Out");
	
	public static Label lbl= new Label();
	
	public static GridPane gpUserCheckIn = new GridPane();
	public static GridPane gpAdmin = new GridPane();

	
	
	
	public MainScene(Stage stg) {
		gpUserCheckIn.add(btnAmenities, 0, 0);
		gpUserCheckIn.add(btnFacility, 1, 0);
		gpUserCheckIn.add(btnDND, 0, 1);
		gpUserCheckIn.add(btnWakeUp, 1, 1);
		gpUserCheckIn.add(btnComplaint, 0, 2);
		gpUserCheckIn.add(btnLogOut, 1, 2);
		gpUserCheckIn.setAlignment(Pos.CENTER);

		gpAdmin.add(btnReserve, 0, 0);
		gpAdmin.add(btnRooms, 0, 1);
		gpAdmin.add(btnBookings, 0, 2);
		gpAdmin.add(btnAmenitiesAdmin, 0, 3);
		gpAdmin.add(btnFacilitiesAdmin, 0, 4);
		gpAdmin.add(btnUsers, 0, 5);
		gpAdmin.add(btnLogOutA, 0, 6);
		gpAdmin.setAlignment(Pos.CENTER);
		
		vbMain.getChildren().add(lbl);
		vbMain.getChildren().add(gpUserCheckIn);
//		vbMain.getChildren().add(gpAdmin);
		vbMain.setAlignment(Pos.CENTER);
		mainScene = new Scene(vbMain,1000,500);
		
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
		
		btnFacility.setOnAction(e->{
			stg.setScene(BookFacility.bookFacilityScene);
			stg.setTitle("Book Facility");
		});
		
		btnFacilitiesAdmin.setOnAction(e->{
			stg.setScene(FacilityAdmin.facilityAdminScene);
			stg.setTitle("Facility");
		});
		
		btnRooms.setOnAction(e->{
			new RoomsAdminScene(stg);
			stg.setScene(RoomsAdminScene.adminRoomsScene);
			stg.setTitle("Rooms");
		});
		
		btnUsers.setOnAction(e->{
			new UsersView(stg);
			stg.setScene(UsersView.usersViewScene);
			stg.setTitle("Users");
		});
		
		btnBookings.setOnAction(e->{
			new ReservationsAdminScene(stg);
			stg.setScene(ReservationsAdminScene.adminReservationScene);
			stg.setTitle("Reservations");
		});
		
		btnDND.setOnAction(e->{
			try {
				Connections.openCon();
				String query = "SELECT * FROM room WHERE roomNumber = "+Main.roomNo;
				Connections.state = Connections.connect.prepareStatement(query);
				ResultSet rs = Connections.state.executeQuery();
				if(rs.next()) {
					if(rs.getBoolean("do_not_disturb")==false) {
						query = "UPDATE room SET do_not_disturb = true WHERE roomNumber = "+Main.roomNo;
						Functions.informUser("Your room is set to do not disturb");
					}else {
						query = "UPDATE room SET do_not_disturb = false WHERE roomNumber = "+Main.roomNo;
						Functions.informUser("Do not disturb turned off");

					}
				}
				Connections.state = Connections.connect.prepareStatement(query);
				Connections.state.executeUpdate();
				Connections.closeCon();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		});
		
		btnWakeUp.setOnAction(e->{
			try {
				Connections.openCon();
				String query = "SELECT * FROM room WHERE roomNumber = "+Main.roomNo;
				Connections.state = Connections.connect.prepareStatement(query);
				ResultSet rs = Connections.state.executeQuery();
				if(rs.next()) {
					if(rs.getBoolean("wake_up_call")==false) {
						query = "UPDATE room SET wake_up_call = true WHERE roomNumber = "+Main.roomNo;
						Functions.informUser("You will receive wake up call on 06.00AM");
					}else {
						query = "UPDATE room SET wake_up_call = false WHERE roomNumber = "+Main.roomNo;
						Functions.informUser("Wake up call turned off");

					}
				}
				Connections.state = Connections.connect.prepareStatement(query);
				Connections.state.executeUpdate();
				Connections.closeCon();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		});
	}
}
