package admin_view;

import java.sql.ResultSet;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
import view.MainScene;

public class ReservationsAdminScene {
	public static Scene adminReservationScene;
	ScrollPane sp = new ScrollPane();
	TableView<Booking> tvBookings = new TableView<>();
	Button back = new Button("Back");
	ObservableList<Booking> bookings;
	VBox vb = new VBox();
	Label lblGuestID = new Label("Guest ID");
	Label lblRoomNumber = new Label("Room Number");
	TextField tfGuestID = new TextField();
	Spinner<Integer> roomNumber = new Spinner<>();
	SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(101,310);
	Button btnCheckOut = new Button("Check Out");
	
	TableColumn<Booking,String> id = new TableColumn<>("Guest ID: ");
	TableColumn<Booking,String> roomNo = new TableColumn<>("Room Number: ");
	TableColumn<Booking,String> check_in = new TableColumn<>("Check In");
	
	@SuppressWarnings("unchecked")
	public void load() {
		bookings = FXCollections.observableArrayList();
		try {
			Connections.openCon();
			String query = "SELECT * FROM booking";
			Connections.state = Connections.connect.prepareStatement(query);
			ResultSet rs = Connections.state.executeQuery();
			while(rs.next()) {
				bookings.add(new Booking(rs.getString("guestID"), rs.getDate("checkIn"), rs.getInt("roomNumber")));
			}
			Connections.closeCon();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		tvBookings.setItems(bookings);
		id.setCellValueFactory(bookings -> new SimpleStringProperty(bookings.getValue().getGuestID()));
		roomNo.setCellValueFactory(bookings -> new SimpleStringProperty(Integer.toString(bookings.getValue().getRoomNumber())));
		check_in.setCellValueFactory(bookings -> new SimpleStringProperty(Main.formatter.format(bookings.getValue().getCheckIn())));
		
		tvBookings.getColumns().setAll(id,roomNo,check_in);
	}
	
	public ReservationsAdminScene(Stage stg) {
		load();
		roomNumber.setValueFactory(svf);
		roomNumber.setEditable(true);
		sp.setContent(tvBookings);
		vb.getChildren().addAll(back,sp,lblGuestID,tfGuestID,lblRoomNumber,roomNumber,btnCheckOut);
		adminReservationScene = new Scene(vb,1000,500);
		
		back.setOnAction(e->{
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
		
		btnCheckOut.setOnAction(e->{
			if(tfGuestID.getText().isEmpty()) {
				Functions.alertUser("Please insert guest id");
			}else {
				try {
					Connections.openCon();
					String query = "SELECT * FROM booking WHERE guestID = '"+tfGuestID+"'";
					Connections.state = Connections.connect.prepareStatement(query);
					ResultSet rs = Connections.state.executeQuery();
					if(rs.next()) {
						query = "SELECT * FROM booking WHERE guestID = '"+tfGuestID+"' AND roomNumber = " + roomNumber.getValue();
						Connections.state = Connections.connect.prepareStatement(query);
						rs = Connections.state.executeQuery();
					}else {
						Functions.alertUser("Guest is not booking any room");
					}
					Connections.closeCon();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
	}
}
