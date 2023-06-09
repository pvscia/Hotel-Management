package admin_view;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import database.Connections;
import database.Functions;
import finance.Booking;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import main.Main;
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
	
	Label lblGuest = new Label("Guest ID: ");
	Label lblRoomNo = new Label("Room Number: ");
	Label checkIn = new Label("NEW RESERVATION : ");
	TextField tfGuest = new TextField();
	Spinner<Integer> ciRoomNo = new Spinner<>();
	SpinnerValueFactory<Integer> ciSVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(101, 310);
	Button btnCheckIn = new Button("Check In");
	HBox hbCheckIn = new HBox();
	
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
		rooms.setMinWidth(700);
		
		wakeUp.getStyleClass().add("button");
		clean.getStyleClass().add("button");
		back.getStyleClass().add("button");
		
//		sp.setContent(rooms);
//		sp.setFitToWidth(true);
//		sp.setMaxWidth(750);
		
		vb.getChildren().add(back);
		vb.getChildren().add(rooms);
		vb.setAlignment(Pos.CENTER);
		
		roomNo.setValueFactory(svf);
		roomNo.setEditable(true);
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(clean, wakeUp);
		hb1.getStyleClass().add("buttonBox");
		hb.getChildren().addAll(lblRoom,roomNo, hb1);
		hb.setAlignment(Pos.CENTER);
		hb.getStyleClass().add("buttonBox");
		
		vb.getChildren().add(hb);
		vb.setAlignment(Pos.CENTER);
		
		vb.getChildren().addAll(checkIn,hbCheckIn);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(5);
		
		vb.getStyleClass().add("background");
		
		ciRoomNo.setValueFactory(ciSVF);
		ciRoomNo.setEditable(true);
		
		
		
		btnCheckIn.getStyleClass().add("button");
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(lblGuest, tfGuest);
		HBox hb3 = new HBox();
		hb3.getChildren().addAll(lblRoomNo, ciRoomNo);
		HBox hb4 = new HBox();
		hb4.getChildren().add(btnCheckIn);
		
		hbCheckIn.getStyleClass().add("buttonBox");
		hbCheckIn.getChildren().addAll(hb2, hb3, hb4);
		hbCheckIn.setAlignment(Pos.CENTER);
		hbCheckIn.setSpacing(2);
		

		adminRoomsScene = new Scene(vb,1000,500);
		adminRoomsScene.getStylesheets().add(getClass().getResource("/resources/RoomsAdmin.css").toExternalForm());
		
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
		
		btnCheckIn.setOnAction(e->{
			try {
				Connections.openCon();
				String query = "SELECT * FROM user WHERE id = '" + tfGuest.getText()+"'";
				Connections.state = Connections.connect.prepareStatement(query);
				ResultSet rs = Connections.state.executeQuery();
				if(rs.next()) {
					query = "SELECT * FROM room WHERE roomNumber = " + ciRoomNo.getValue();
					Connections.state = Connections.connect.prepareStatement(query);
					rs = Connections.state.executeQuery();
					if(rs.next()) {
						if(rs.getString("availability").equals("Need Cleaning")) {
							Functions.alertUser("Room still dirty, clean it first!");
						}else if(rs.getString("availability").equals("Available")) {
							query = "UPDATE room SET availability = 'Booked' WHERE roomNumber = " + ciRoomNo.getValue();
							Connections.state = Connections.connect.prepareStatement(query);
							Connections.state.executeUpdate();
							
							
							LocalDateTime now = LocalDateTime.now(); 
							query = "INSERT INTO `booking`(`guestID`, `roomNumber`, `checkIn`) VALUES ('"+tfGuest.getText()+"',"+ciRoomNo.getValue()+",'"+ Main.dtf.format(now)+"')";
							Connections.state = Connections.connect.prepareStatement(query);
							System.out.println(query);
							Connections.state.executeUpdate();
							
							Functions.informUser("Room is booked");
							stg.setScene(MainScene.mainScene);
							stg.setTitle("Main Menu");
						}else {
							Functions.alertUser("The room is occupied");
						}
					}else {
						Functions.alertUser("Room is not in our list");
					}
					
				}else {
					Functions.alertUser("User ID is not available");
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
		rooms.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		rooms.setPrefWidth(Region.USE_COMPUTED_SIZE);
		rooms.setMaxWidth(Double.MAX_VALUE);
		
		lblRoom.setStyle("-fx-text-fill: white; -fx-font-family: \"Montserrat\";");
		lblGuest.setStyle("-fx-text-fill: white; -fx-font-family: \"Montserrat\";");
		lblRoomNo.setStyle("-fx-text-fill: white; -fx-font-family: \"Montserrat\";");
		checkIn.setStyle("-fx-text-fill: white; -fx-font-family: \"Montserrat\";");
		
		roomNumber.setCellFactory(column -> new TableCell<Room, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});

		bedType.setCellFactory(column -> new TableCell<Room, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});

		roomType.setCellFactory(column -> new TableCell<Room, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});
		
		roomView.setCellFactory(column -> new TableCell<Room, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});
		
		roomNumber.setCellFactory(column -> new TableCell<Room, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});

		roomSize.setCellFactory(column -> new TableCell<Room, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});

		roomCapacity.setCellFactory(column -> new TableCell<Room, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});
		
		roomPrice.setCellFactory(column -> new TableCell<Room, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});

		dnd.setCellFactory(column -> new TableCell<Room, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});

		wakeUpCall.setCellFactory(column -> new TableCell<Room, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});
		
		availability.setCellFactory(column -> new TableCell<Room, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});

		name.setCellFactory(column -> new TableCell<Room, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});

	}
}
