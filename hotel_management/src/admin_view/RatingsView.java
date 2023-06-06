package admin_view;

import java.sql.ResultSet;

import database.Connections;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
	HBox hb = new HBox();
	
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
		tvRatings.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		tvRatings.setPrefWidth(Region.USE_COMPUTED_SIZE);
		tvRatings.setMaxWidth(Double.MAX_VALUE);
		
		date.setCellFactory(column -> new TableCell<Rating, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});

		complaint.setCellFactory(column -> new TableCell<Rating, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});

		roomNumber.setCellFactory(column -> new TableCell<Rating, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});

		rating.setCellFactory(column -> new TableCell<Rating, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});
	}
	
	public RatingsView(Stage stg) {
		load();
		sp.setContent(tvRatings);
		sp.setFitToWidth(true);
		
		hb.getChildren().add(sp);
		hb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(hb,back);
		vb.setAlignment(Pos.CENTER);
		
		back.getStyleClass().add("button");
		vb.getStyleClass().add("background");
		
		
		ratingsScene = new Scene(vb,1000,500);
		ratingsScene.getStylesheets().add(getClass().getResource("/resources/RatingsView.css").toExternalForm());
		
		
		back.setOnAction(e->{
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
	}
	
}
