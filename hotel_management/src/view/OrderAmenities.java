package view;


import java.sql.ResultSet;

import database.Connections;
import database.Functions;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Main;

public class OrderAmenities {
	public static Scene orderAmenitiesScene;
	
	
	VBox containerBox = new VBox();
	HBox hbox = new HBox();
	HBox buttonBox = new HBox();
	VBox vbAmenities = new VBox();
	BorderPane bp = new BorderPane();
	ComboBox<String> cbAmenities = new ComboBox<>();
	Spinner<Integer> qty = new Spinner<>();
	SpinnerValueFactory<Integer> svfQty = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
	Button submit = new Button("Submit");
	Label lblAmenities = new Label("Amenities");
	Label lblQty = new Label("Qty");
	Button btnBack = new Button("Cancel");
	
	void clearFields() {
		qty.getValueFactory().setValue(1);
		cbAmenities.valueProperty().set(null);
	}
	
	@SuppressWarnings("static-access")
	public OrderAmenities(Stage stg) {
		cbAmenities.getItems().addAll("Shampoo","Soap","Toothbrush","Towel","Extra Bed","Tissue","Slippers");
		qty.setValueFactory(svfQty);
		qty.setEditable(true);
		
		VBox amenity = new VBox(lblAmenities, cbAmenities);
		VBox quantity = new VBox(lblQty, qty);
		
		VBox input = new VBox(amenity, quantity);
		
		buttonBox.getChildren().addAll(submit, btnBack);
		
		vbAmenities.getChildren().addAll(input, buttonBox);
		vbAmenities.setSpacing(5);

		bp.setCenter(vbAmenities);
		bp.setAlignment(vbAmenities, Pos.CENTER);
	
		hbox.getChildren().add(bp);
		hbox.setAlignment(Pos.CENTER);
		
		containerBox.getChildren().add(hbox);
		containerBox.setAlignment(Pos.CENTER);
		
		cbAmenities.setPrefWidth(200);
		qty.setPrefWidth(200);
		containerBox.getStyleClass().addAll("background", "vbox");
		hbox.getStyleClass().add("hbox");
		lblAmenities.getStyleClass().add("text");
		lblQty.getStyleClass().add("text");
		submit.getStyleClass().add("button");
		btnBack.getStyleClass().add("button");
		buttonBox.getStyleClass().add("buttonBox");
		vbAmenities.getStyleClass().add("vbox");
		
		
		orderAmenitiesScene= new Scene(containerBox,1000,500);
		orderAmenitiesScene.getStylesheets().add(getClass().getResource("/resources/OrderAmenities.css").toExternalForm());
		
		
		
		btnBack.setOnAction(e->{
			clearFields();
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
		
		submit.setOnAction(e->{
			if(cbAmenities.getSelectionModel().isEmpty()) {
				Functions.alertUser("Please choose amenities you want to request");
			}else {
				try {
					Connections.openCon();
					String query = "SELECT * FROM inventory WHERE name = ?";
					Connections.state = Connections.connect.prepareStatement(query);
					Connections.state.setString(1, cbAmenities.getValue());
					ResultSet rs = Connections.state.executeQuery();
					if(rs.next()) {
						int stock = rs.getInt("stock");
						if(stock<qty.getValue()) {
							Functions.alertUser("The product you want is out of stock");
						}else {
							query = "SELECT * FROM `amenities_req` WHERE roomNumber = ? AND name = ?";
							Connections.state = Connections.connect.prepareStatement(query);
							Connections.state.setInt(1, Main.roomNo);
							Connections.state.setString(2, cbAmenities.getValue());
							rs = Connections.state.executeQuery();
							if(rs.next()) {
								query = "UPDATE `amenities_req` SET qty = ? WHERE roomNumber = ? AND name = ?";
								Connections.state = Connections.connect.prepareStatement(query);
								Connections.state.setInt(1, rs.getInt("qty")+qty.getValue());
								Connections.state.setInt(2, Main.roomNo);
								Connections.state.setString(3, cbAmenities.getValue());
								Connections.state.executeUpdate();
							}else {
								query = "INSERT INTO `amenities_req`(`name`, `roomNumber`, `qty`) VALUES (?,?,?)";
								Connections.state = Connections.connect.prepareStatement(query);
								Connections.state.setString(1, cbAmenities.getValue());
								Connections.state.setInt(2, Main.roomNo);
								Connections.state.setInt(3, qty.getValue());
								Connections.state.executeUpdate();
							}
							
							
							query = "UPDATE `inventory` SET `stock`= ? WHERE name = ?";
							Connections.state = Connections.connect.prepareStatement(query);
							Connections.state.setInt(1, stock - qty.getValue());
							Connections.state.setString(2, cbAmenities.getValue());
							Connections.state.executeUpdate();
							
							
							
							Functions.informUser("Order Successful");
							clearFields();
							stg.setScene(MainScene.mainScene);
							stg.setTitle("Main Menu");
							
						}
					}
					Connections.closeCon();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
	}
}
