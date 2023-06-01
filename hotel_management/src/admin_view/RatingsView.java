package admin_view;

import java.sql.ResultSet;

import database.Connections;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
import persons.Guest;
import persons.Person;
import ratings.Rating;
import view.MainScene;

public class RatingsView {
	public static Scene ratingsScene;
	TableView<Rating> tvRatings = new TableView<>();
	ScrollPane sp = new ScrollPane();
	Button back = new Button("Back");
	ObservableList<Rating> ratings;
	VBox vb = new VBox();
	
	TableColumn<Rating,String> id = new TableColumn<>("ID");
	TableColumn<Rating,String> date = new TableColumn<>("Date");
	TableColumn<Rating,String> complaint = new TableColumn<>("Complaint");
	TableColumn<Rating,String> roomNumber = new TableColumn<>("Room Number");
	TableColumn<Rating,String> rating = new TableColumn<>("Rating");
	
	@SuppressWarnings("unchecked")
	public void load() {
		ratings = FXCollections.observableArrayList();
		try {
			Connections.openCon();
			String query = "SELECT * FROM rating ";
			Connections.state = Connections.connect.prepareStatement(query);
			ResultSet rs = Connections.state.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("roomNumber"));
				ratings.add(new Rating(rs.getInt("roomNumber"), rs.getInt("rating"), rs.getString("complaint"), rs.getDate("date")));
			}
			Connections.closeCon();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		tvRatings.setItems(ratings);
		date.setCellValueFactory(ratings -> new SimpleStringProperty(Main.formatter.format(ratings.getValue().getDate())));
		complaint.setCellValueFactory(ratings -> new SimpleStringProperty(ratings.getValue().getComplaint()));
		roomNumber.setCellValueFactory(ratings -> new SimpleStringProperty(Integer.toString(ratings.getValue().getRoomNumber())));
		rating.setCellValueFactory(ratings -> new SimpleStringProperty(Integer.toString(ratings.getValue().getRating())));
		
		tvRatings.getColumns().setAll(date,complaint,roomNumber,rating);
	}
	
	public RatingsView(Stage stg) {
		load();
		sp.setContent(tvRatings);
		vb.getChildren().addAll(back,sp);
		ratingsScene = new Scene(vb,1000,500);
		
		back.setOnAction(e->{
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
	}
	
}
