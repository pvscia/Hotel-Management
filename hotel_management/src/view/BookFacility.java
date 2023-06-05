package view;

import java.sql.ResultSet;

import database.Connections;
import database.Functions;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import main.Main;

public class BookFacility {
	public static Scene bookFacilityScene;
	VBox vb = new VBox();
	Spinner<Integer> people = new Spinner<>();
	SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4,1);
	ComboBox<String> facilities = new ComboBox<>();
	Button btnBack = new Button("Back");
	Button btnSubmit = new Button("Submit");
	
	void clearFields() {
		facilities.setValue(null);
		people.getValueFactory().setValue(1);
	}
	
	public BookFacility(Stage stg) {
		
		vb.getChildren().add(btnBack);
		vb.getChildren().add(new Label("Currently Booked Facilities : "));
		vb.setAlignment(Pos.CENTER);
		
		vb.getStyleClass().add("background");
		
		try {
			Connections.openCon();
			String qry = "SELECT * FROM facility_book WHERE roomNo = "+Main.roomNo;
			Connections.state = Connections.connect.prepareStatement(qry);
			ResultSet rs = Connections.state.executeQuery();
			
			while(rs.next()) {
				vb.getChildren().add(new Label(rs.getString("fac_name")+ " for " + rs.getInt("no_people") +" person(s)"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		vb.setSpacing(5);
		facilities.getItems().addAll("Gym","Playground","Rooftop","Spa","Swimming Pool");
		people.setValueFactory(svf);
		people.setEditable(true);
		vb.getChildren().addAll(facilities,people,btnSubmit);
		
		bookFacilityScene = new Scene(vb,1000,500);
		bookFacilityScene.getStylesheets().add(getClass().getResource("/resources/BookFacility.css").toExternalForm());
		
		btnSubmit.setOnAction(e->{
			if(facilities.getValue()==null) {
				Functions.alertUser("Select facility you want to book");
			}else {
				try {
					Connections.openCon();
					String query = "SELECT * FROM facility_book WHERE fac_name = ? AND roomNo = ?; ";
					Connections.state = Connections.connect.prepareStatement(query);
					Connections.state.setString(1, facilities.getValue());
					Connections.state.setInt(2, Main.roomNo);
					ResultSet rs = Connections.state.executeQuery();
					if(rs.next()) {
						Functions.alertUser("You Already Booked This Facility");
					}else { 
						query = "SELECT * FROM facility WHERE name = ? ";
						Connections.state = Connections.connect.prepareStatement(query);
						Connections.state.setString(1, facilities.getValue());
						rs = Connections.state.executeQuery();
						if(rs.next()) {
							if(!rs.getString("status").equals("Available") ){
								Functions.alertUser("This Facility is " + rs.getString("status"));
							}else {
								if(rs.getInt("capacity")>=rs.getInt("custCount")+people.getValue()) {
									query = "INSERT INTO `facility_book`(`roomNo`, `fac_name`, `no_people`) VALUES (?,?,?)";
									Connections.state = Connections.connect.prepareStatement(query);
									Connections.state.setInt(1, Main.roomNo);
									Connections.state.setString(2, facilities.getValue());
									Connections.state.setInt(3, people.getValue());
									Connections.state.executeUpdate();
									
									query = "UPDATE `facility` SET custCount = ?, status = ? WHERE name = ?";
									Connections.state = Connections.connect.prepareStatement(query);
									Connections.state.setInt(1, rs.getInt("custCount")+people.getValue());
									String str=null;
									if(rs.getInt("custCount")+people.getValue()==rs.getInt("capacity")) {
										str = "Full Booked";
									}else {
										str = rs.getString("status");
									}
									Connections.state.setString(2, str);
									Connections.state.setString(3, facilities.getValue());
									Connections.state.executeUpdate();
									
									clearFields();
									vb.getChildren().removeAll(vb.getChildren());
									new BookFacility(stg);
									Functions.informUser("You have booked the facility");
									stg.setScene(MainScene.mainScene);
									stg.setTitle("Main Menu");
								}else{
									Functions.alertUser("Capacity is not enough");
								}
							}
						}
						
					}
					
					Connections.closeCon();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
			
			
		});
		
		btnBack.setOnAction(e->{
			clearFields();
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
	}
}
