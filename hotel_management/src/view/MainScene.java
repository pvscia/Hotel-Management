package view;

import java.sql.ResultSet;

import admin_view.AmenitiesAdmin;
import admin_view.FacilityAdmin;
import admin_view.RatingsView;
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
import javafx.scene.paint.Color;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.Main;
import javafx.scene.layout.HBox;

public class MainScene {
	public static Scene mainScene;
	public static VBox vbMain = new VBox();

	
	public static FlowPane containerPane = new FlowPane();
	HBox buttonContainer = new HBox();
	HBox buttonContainer2 = new HBox();
	VBox contentBox = new VBox();
	Button btnAmenities = new Button("Order Amenities");
	Button btnFacility = new Button("Book Facility");
	Button btnDND = new Button("Do Not Disturb");
	Button btnWakeUp = new Button("Wake Up Call (06.00)");
	Button btnRating = new Button("Add Rating");
	Button btnLogOut = new Button("Log Out");
	
	
	public static FlowPane containerPaneAdmin = new FlowPane();
	HBox buttonContainerAdmin = new HBox();
	HBox buttonContainer2Admin = new HBox();
	HBox buttonContainer3Admin = new HBox();
	VBox contentBoxAdmin = new VBox();
	Button btnRooms = new Button("Rooms");
	Button btnBookings = new Button("Reservations");
	Button btnAmenitiesAdmin = new Button("Amenities");
	Button btnFacilitiesAdmin = new Button("Facilities");
	Button btnRatings = new Button("Ratings");
	Button btnUsers = new Button("Users");
	Button btnLogOutA = new Button("Log Out");
	
	public static Label lbl = new Label();
	public static Label lblA = new Label();
	
	public static GridPane gpUserCheckIn = new GridPane();
	public static GridPane gpAdmin = new GridPane();

	
	
	public MainScene(Stage stg) {
//		gpUserCheckIn.add(btnAmenities, 0, 0);
//		gpUserCheckIn.add(btnFacility, 1, 0);
//		gpUserCheckIn.add(btnDND, 0, 1);
//		gpUserCheckIn.add(btnWakeUp, 1, 1);
//		gpUserCheckIn.add(btnRating, 0, 2);
//		gpUserCheckIn.add(btnLogOut, 1, 2);
//		gpUserCheckIn.setAlignment(Pos.CENTER);
		buttonContainer.getChildren().addAll(btnAmenities, btnFacility, btnRating);
		buttonContainer2.getChildren().addAll(btnWakeUp, btnDND, btnLogOut);
		
		contentBox.setAlignment(Pos.CENTER);
		contentBox.setSpacing(10);
		
		contentBox.getChildren().add(lbl);
		contentBox.getChildren().add(buttonContainer);
		contentBox.getChildren().add(buttonContainer2);	
		
		containerPane.setAlignment(Pos.CENTER);
		containerPane.getChildren().add(contentBox);
		
		
//		gpAdmin.add(btnRooms, 0, 0);
//		gpAdmin.add(btnBookings, 0, 1);
//		gpAdmin.add(btnAmenitiesAdmin, 0, 2);
//		gpAdmin.add(btnFacilitiesAdmin, 0, 3);
//		gpAdmin.add(btnUsers, 0, 4);
//		gpAdmin.add(btnRatings, 0, 5);
//		gpAdmin.add(btnLogOutA, 0, 6);
//		gpAdmin.setAlignment(Pos.CENTER);
		buttonContainerAdmin.getChildren().addAll(btnRooms, btnBookings, btnAmenitiesAdmin);
		buttonContainer2Admin.getChildren().addAll(btnFacilitiesAdmin, btnUsers, btnRatings);
		buttonContainer3Admin.getChildren().addAll(btnLogOutA);
		
		contentBoxAdmin.setAlignment(Pos.CENTER);
		contentBoxAdmin.setSpacing(10);
		
		contentBoxAdmin.getChildren().add(lblA);
		contentBoxAdmin.getChildren().add(buttonContainerAdmin);
		contentBoxAdmin.getChildren().add(buttonContainer2Admin);
		contentBoxAdmin.getChildren().add(buttonContainer3Admin);
		
		containerPaneAdmin.setAlignment(Pos.CENTER);
		containerPaneAdmin.getChildren().add(contentBoxAdmin);
		
		
//		containerPane.setStyle("-fx-background-color: lightgray; -fx-padding: 10px; -fx-alignment:center");
		vbMain.getStyleClass().add("background");
		lbl.getStyleClass().add("label");
		
		containerPane.getStyleClass().add("container-pane");
		buttonContainer.getStyleClass().add("button-spacing");
		buttonContainer2.getStyleClass().add("button-spacing");
		contentBox.getStyleClass().add("button-spacing");
		btnAmenities.getStyleClass().add("button");
		btnFacility.getStyleClass().add("button");
		btnDND.getStyleClass().add("button");
		btnWakeUp.getStyleClass().add("button");
		btnRating.getStyleClass().add("button");
		btnLogOut.getStyleClass().add("button");
		buttonContainer.setSpacing(10);
		
//		gpAdmin.add(btnRooms, 0, 0);
//		gpAdmin.add(btnBookings, 0, 1);
//		gpAdmin.add(btnAmenitiesAdmin, 0, 2);
//		gpAdmin.add(btnFacilitiesAdmin, 0, 3);
//		gpAdmin.add(btnUsers, 0, 4);
//		gpAdmin.add(btnRatings, 0, 5);
//		gpAdmin.add(btnLogOutA, 0, 6);
//		gpAdmin.setAlignment(Pos.CENTER);
		
		containerPaneAdmin.getStyleClass().add("container-pane");
		buttonContainerAdmin.getStyleClass().add("button-spacing");
		buttonContainer2Admin.getStyleClass().add("button-spacing");
		buttonContainer3Admin.getStyleClass().add("adminLogoutBtn");
		btnRooms.getStyleClass().add("button");
		btnBookings.getStyleClass().add("button");
		btnAmenitiesAdmin.getStyleClass().add("button");
		btnFacilitiesAdmin.getStyleClass().add("button");
		btnUsers.getStyleClass().add("button");
		btnRatings.getStyleClass().add("button");
		btnLogOutA.getStyleClass().add("button");
		buttonContainerAdmin.setSpacing(10);
		
		
		

		
//		vbMain.getChildren().add(lbl);
//		vbMain.getChildren().add(containerPane);
//		vbMain.getChildren().add(gpAdmin);
		
		vbMain.setAlignment(Pos.CENTER);
		
		
		mainScene = new Scene(vbMain,1000,500);
		System.out.println(getClass().getResource("/resources/MainScene.css").toExternalForm());
		mainScene.getStylesheets().add(getClass().getResource("/resources/MainScene.css").toExternalForm());
		
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
		
		btnRating.setOnAction(e->{
			stg.setScene(AddRatingScene.addRatingScene);
			stg.setTitle("Add Rating");
		});
		
		btnRatings.setOnAction(e->{
			stg.setScene(RatingsView.ratingsScene);
			stg.setTitle("Ratings");
		});	}
}
