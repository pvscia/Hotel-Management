package admin_view;

import java.sql.ResultSet;
import java.util.ArrayList;

import database.Connections;
import database.Functions;
import facilities.Inventory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.MainScene;

public class AmenitiesAdmin {
	public static Scene amenitiesAdminScene;
	ScrollPane sp = new ScrollPane();
	VBox vb = new VBox();
	HBox hbox = new HBox();
	HBox bg = new HBox();
	Button back = new Button("Back");
	ArrayList<Inventory> inven = new ArrayList<>();
	ArrayList<HBox> hboxList = new ArrayList<>();
	
	VBox deliveryBox = new VBox();
	
	// Will contain both deliveryBox and hbox
//	VBox combiner = new VBox();
	public AmenitiesAdmin(Stage stg) {
		try {
			Connections.openCon();
			String query = "SELECT * FROM inventory";
			Connections.state = Connections.connect.prepareStatement(query);
			ResultSet rs = Connections.state.executeQuery();
			while(rs.next()) {
				inven.add(new Inventory(rs.getString("name"), rs.getInt("stock")));
			}
			vb.getChildren().add(back);
			
			int counter = -1;
			int listIndex = -1;
			
			for (Inventory i : inven) {
				counter++;
				
				if(counter % 3 == 0) {
					HBox hbox = new HBox();
					hbox.getStyleClass().add("amenityContainer");
					hboxList.add(hbox);
					
					listIndex++;
				}
				
				Spinner<Integer> stock = new Spinner<>();
				SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, i.getStock());
				stock.setValueFactory(svf);
				stock.setEditable(true);
				stock.getValueFactory().setValue(i.getStock());
				String str = "Name: " + i.getName();
				Label lbl = new Label(str);
				Button btn = new Button("Update Stock");
				VBox temp = new VBox();
				temp.getChildren().add(lbl);
				temp.getChildren().add(stock);
				temp.getChildren().add(btn);
				
				
				temp.getStyleClass().add("amenity");
				lbl.getStyleClass().add("text");
				
//				vb.getChildren().add(temp);
				hboxList.get(listIndex).getChildren().add(temp);
				
				
				btn.setOnAction(e->{
					try {
						Connections.openCon();
						String qry = "UPDATE inventory SET stock = ? WHERE name = ?";
						Connections.state = Connections.connect.prepareStatement(qry);
						Connections.state.setInt(1, stock.getValue());
						Connections.state.setString(2, i.getName());
						Connections.state.executeUpdate();
						Connections.closeCon();
						
						inven.removeAll(inven);
						vb.getChildren().removeAll(vb.getChildren());
						new AmenitiesAdmin(stg);
						Functions.informUser("Stock Updated!");
						stg.setScene(MainScene.mainScene);
						stg.setTitle("Main Menu");
						
						
						Connections.closeCon();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				
				});
				
				
			}
			
			for(int i = 0; i <= listIndex; i++) {
				vb.getChildren().add(hboxList.get(i));
			}
			query = "SELECT * FROM amenities_req";
			Connections.state = Connections.connect.prepareStatement(query);
			rs = Connections.state.executeQuery();
			while(rs.next()) {
				VBox tempVBox = new VBox();
				String name = rs.getString("name");
				int no =rs.getInt("roomNumber");
				String temp = "Room number " + rs.getInt("roomNumber") + " requests " + rs.getInt("qty") + " " + name;
				HBox hb = new HBox();
				
				Label request = new Label(temp);
				request.getStyleClass().add("text");
				hb.getChildren().add(request);
				Button deliver = new Button("Deliver");
				hb.getChildren().add(deliver);
//				vb.getChildren().add(hb);
				hb.setSpacing(5);
				
				tempVBox.getChildren().add(hb);
				tempVBox.getStyleClass().add("amenityRequest");
				hb.setAlignment(Pos.CENTER);
				deliveryBox.getChildren().add(tempVBox);
				
				deliver.setOnAction(e->{
					try {
						Connections.openCon();
						String qry = "DELETE FROM `amenities_req` WHERE name = ? AND roomNumber = ?";
						Connections.state = Connections.connect.prepareStatement(qry);
						Connections.state.setString(1, name);
						Connections.state.setInt(2, no);
						Connections.state.executeUpdate();
						Connections.closeCon();
						
						inven.removeAll(inven);
						vb.getChildren().removeAll(vb.getChildren());
						new AmenitiesAdmin(stg);
						Functions.informUser("Amenities Delivered");
						stg.setScene(MainScene.mainScene);
						stg.setTitle("Main Menu");
						
						
						Connections.closeCon();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				});


			}
			deliveryBox.getStyleClass().add("deliveryBox");
			vb.getChildren().add(deliveryBox);
			Connections.closeCon();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		vb.setSpacing(10);
		vb.setPadding(new Insets(10));
		
		hbox.getChildren().add(vb);
		hbox.setAlignment(Pos.CENTER);
		
		deliveryBox.setAlignment(Pos.CENTER);
		
//		combiner.getChildren().addAll(hbox, deliveryBox);
//		combiner.setAlignment(Pos.CENTER);
		
		
		hbox.getStyleClass().add("background");
		vb.getStyleClass().add("componentBackground");
		
		
		amenitiesAdminScene = new Scene(hbox,1000,500);
		amenitiesAdminScene.getStylesheets().add(getClass().getResource("/resources/AmenitiesAdmin.css").toExternalForm());
		back.setOnAction(e->{
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
	}
}
