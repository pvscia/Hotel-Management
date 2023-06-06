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
import javafx.scene.control.TableCell;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import main.Main;
import ratings.Rating;
import view.MainScene;

public class ReservationsAdminScene {
	public static Scene adminReservationScene;
	ScrollPane sp = new ScrollPane();
	TableView<Booking> tvBookings = new TableView<>();
	Button back = new Button("Back");
	ObservableList<Booking> bookings;
	HBox hb = new HBox();
	VBox vb = new VBox();
	VBox vb1 = new VBox();
	Label lblGuestID = new Label("Guest ID");
	TextField tfGuestID = new TextField();
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
		tvBookings.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		tvBookings.setPrefWidth(Region.USE_COMPUTED_SIZE);
		tvBookings.setMaxWidth(Double.MAX_VALUE);
		
		lblGuestID.setStyle("-fx-text-fill: white; -fx-font-family: \"Montserrat\";");
		tfGuestID.setMaxWidth(100);
		
		id.setCellFactory(column -> new TableCell<Booking, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});

		roomNo.setCellFactory(column -> new TableCell<Booking, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});

		check_in.setCellFactory(column -> new TableCell<Booking, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});

		
	}
	
	public ReservationsAdminScene(Stage stg) {
		load();
//		sp.setContent(tvBookings);
//		sp.setFitToWidth(true);
		
		hb.getChildren().add(tvBookings);
		hb.setAlignment(Pos.CENTER);
		
		vb.getChildren().addAll(hb,back,lblGuestID,tfGuestID,btnCheckOut);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(6); 
		
		vb.getStyleClass().add("background");
		
		tfGuestID.prefWidthProperty().bind(tvBookings.widthProperty()); 
		back.getStyleClass().add("button");
		btnCheckOut.getStyleClass().add("button");
		adminReservationScene = new Scene(vb,1000,500);
		adminReservationScene.getStylesheets().add(getClass().getResource("/resources/ReservationsAdmin.css").toExternalForm());
		
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
					String query = "SELECT * FROM booking WHERE guestID = '"+tfGuestID.getText()+"'";
					Connections.state = Connections.connect.prepareStatement(query);
					ResultSet rs = Connections.state.executeQuery();
					if(rs.next()) {
						query = "SELECT guestID, b.roomNumber, roomPrice,  CURRENT_DATE - checkIn as days FROM booking b JOIN room r ON r.roomNumber = b.roomNumber WHERE guestID = '"+tfGuestID.getText()+"'";
						Connections.state = Connections.connect.prepareStatement(query);
						rs = Connections.state.executeQuery();
						if(rs.next()) {
							boolean f = Functions.confirmUser("Checking out this guest?");
							if(f) {
								Functions.informUser("Total charge : \n"+rs.getInt("roomPrice")+" * "+(rs.getInt("days")<1 ? 1 : rs.getInt("days"))+" days = "+ (rs.getInt("roomPrice")*(rs.getInt("days")<1 ? 1 : rs.getInt("days"))));
								
								query = "UPDATE room SET availability = 'Need Cleaning', wake_up_call = false, do_not_disturb = false WHERE roomNumber = " + rs.getInt("roomNumber");
								Connections.state = Connections.connect.prepareStatement(query);
								Connections.state.executeUpdate();

								query = "DELETE FROM booking WHERE guestID = '"+tfGuestID.getText()+"'";
								Connections.state = Connections.connect.prepareStatement(query);
								Connections.state.executeUpdate();
								
								stg.setScene(MainScene.mainScene);
								stg.setTitle("Main Menu");
							}
						}
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
