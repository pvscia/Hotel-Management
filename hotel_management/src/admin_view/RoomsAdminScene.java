package admin_view;

import java.sql.ResultSet;

import database.Connections;
import database.Functions;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rooms.Room;
import view.MainScene;

public class RoomsAdminScene {
	public static Scene adminRoomsScene;
	TableView<Room> rooms = new TableView<>();
	ScrollPane sp = new ScrollPane();
	HBox hb = new HBox();
	TextField tf = new TextField();
	Button wakeUp = new Button("Wake Up");
	Button clean = new Button("Clean");
	Button back = new Button("Back");
	Label lblRoom = new Label("Room Number");
	Spinner<Integer> roomNo = new Spinner<>();
	SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(101, 310);
	VBox vb = new VBox();
	static ObservableList<Room> olRooms;
	
	Label lblGuest = new Label("Guest ID");
	Label lblRoomNo = new Label("Room Number");
	Label checkIn = new Label("NEW RESERVATION : ");
	TextField tfGuest = new TextField();
	Spinner<Integer> ciRoomNo = new Spinner<>();
	SpinnerValueFactory<Integer> ciSVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(101, 310);
	Button btnCheckIn = new Button("Check In");
	
	TableColumn<Room,String> roomNumber = new TableColumn<>("No");
	TableColumn<Room,String> bedType = new TableColumn<>("Bed Type");
	TableColumn<Room,String> roomType = new TableColumn<>("Room Type");
	TableColumn<Room,String> roomView = new TableColumn<>("View");
	TableColumn<Room,String> roomSize = new TableColumn<>("Size");
	TableColumn<Room,String> roomCapacity = new TableColumn<>("Capacity");
	TableColumn<Room,String> roomPrice = new TableColumn<>("Price");
	TableColumn<Room,String> dnd = new TableColumn<>("DND");
	TableColumn<Room,String> wakeUpCall = new TableColumn<>("Wake Up");
	TableColumn<Room,String> availability = new TableColumn<>("Status");
	TableColumn<Room,String> name = new TableColumn<>("Guest Name");
	
	public RoomsAdminScene(Stage stg) {
		load();
		sp.setContent(rooms);
		vb.getChildren().add(back);
		vb.getChildren().add(sp);
		roomNo.setValueFactory(svf);
		roomNo.setEditable(true);
		hb.getChildren().addAll(lblRoom,roomNo,clean,wakeUp);
		vb.getChildren().add(hb);

		adminRoomsScene = new Scene(vb,1000,500);
		back.setOnAction(e->{
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
		
		clean.setOnAction(e->{
			try {
				Connections.openCon();
				String query = "SELECT * FROM room WHERE roomNumber = " + roomNo.getValue();
				Connections.state = Connections.connect.prepareStatement(query);
				ResultSet rs = Connections.state.executeQuery();
				if(rs.next()) {
					if(rs.getString("availability").equals("Need Cleaning")) {
						query = "UPDATE room SET availability = 'Available' WHERE roomNumber = " + roomNo.getValue();
						Connections.state = Connections.connect.prepareStatement(query);
						Connections.state.executeUpdate();
						Functions.informUser("Room is clean now");
						
						stg.setScene(MainScene.mainScene);
						stg.setTitle("Main Menu");
					}else if(rs.getString("availability").equals("Available")) {
						Functions.alertUser("Room is already clean");
					}else {
						Functions.alertUser("The room is occupied and cannot be cleaned");
					}
				}else {
					Functions.alertUser("Room is not in our list");
				}
				Connections.closeCon();

			} catch (Exception e2) {
				// TODO: handle exception
			}
		});
		
		wakeUp.setOnAction(e->{
			try {
				Connections.openCon();
				String query = "SELECT * FROM room WHERE roomNumber = " + roomNo.getValue();
				Connections.state = Connections.connect.prepareStatement(query);
				ResultSet rs = Connections.state.executeQuery();
				if(rs.next()) {
					if(rs.getBoolean("wake_up_call")) {
						query = "UPDATE room SET wake_up_call = false WHERE roomNumber = " + roomNo.getValue();
						Connections.state = Connections.connect.prepareStatement(query);
						Connections.state.executeUpdate();
						Functions.informUser("Waking up the guest now");
						
						stg.setScene(MainScene.mainScene);
						stg.setTitle("Main Menu");
					}else{
						Functions.alertUser("The room is not requesting wake up call");
					}
				}else {
					Functions.alertUser("Room is not in our list");
				}
				Connections.closeCon();

			} catch (Exception e2) {
				// TODO: handle exception
			}
		});
	}
	
	
	@SuppressWarnings("unchecked")
	public void  load() {
		olRooms = FXCollections.observableArrayList();
		try {
			Connections.openCon();
			String query = "SELECT r.roomNumber,`bedType`,`roomType`,`roomView`,`availability`,`roomSize`,`roomCapacity`,`roomPrice`,`do_not_disturb`,`wake_up_call`, `name` FROM `room` r LEFT JOIN `booking` b ON b.roomNumber=r.roomNumber LEFT JOIN `user` u ON guestID = u.id ORDER BY roomNumber ASC; ";
			Connections.state = Connections.connect.prepareStatement(query);
			ResultSet rs = Connections.state.executeQuery();
			while(rs.next()) {
				olRooms.add(new Room(rs.getString("bedType"), rs.getString("roomType"), rs.getString("roomView"), rs.getString("availability"), rs.getString("name"), rs.getInt("roomSize"), rs.getInt("roomCapacity"), rs.getInt("roomNumber"), rs.getInt("roomPrice"), rs.getBoolean("do_not_disturb"), rs.getBoolean("wake_up_call")));
			}
			Connections.closeCon();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		rooms.setItems(olRooms);
		roomNumber.setCellValueFactory(olRooms -> new SimpleStringProperty(Integer.toString(olRooms.getValue().getRoomNumber())));
		bedType.setCellValueFactory(olRooms -> new SimpleStringProperty(olRooms.getValue().getBedType()));
		roomType.setCellValueFactory(olRooms -> new SimpleStringProperty(olRooms.getValue().getRoomType()));
		roomView.setCellValueFactory(olRooms -> new SimpleStringProperty(olRooms.getValue().getRoomView()));
		roomSize.setCellValueFactory(olRooms -> new SimpleStringProperty(Integer.toString(olRooms.getValue().getRoomSize())));
		roomCapacity.setCellValueFactory(olRooms -> new SimpleStringProperty(Integer.toString(olRooms.getValue().getRoomCapacity())));
		roomPrice.setCellValueFactory(olRooms -> new SimpleStringProperty(Integer.toString(olRooms.getValue().getRoomPrice())));
		dnd.setCellValueFactory(olRooms -> new SimpleStringProperty(Boolean.toString(olRooms.getValue().isDo_not_disturb())));
		wakeUpCall.setCellValueFactory(olRooms -> new SimpleStringProperty(Boolean.toString(olRooms.getValue().isWake_up_call())));
		availability.setCellValueFactory(olRooms -> new SimpleStringProperty(olRooms.getValue().getAvaibility()));
		name.setCellValueFactory(olRooms -> new SimpleStringProperty(olRooms.getValue().getGuest()));
		
		rooms.getColumns().setAll(roomNumber,bedType,roomType,roomView,roomSize,roomCapacity,roomPrice,dnd,wakeUpCall,availability,name);
		
	}
}
