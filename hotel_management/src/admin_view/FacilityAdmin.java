package admin_view;

import java.sql.ResultSet;
import java.util.ArrayList;

import database.Connections;
import database.Functions;
import facilities.Facility;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.MainScene;

public class FacilityAdmin {
	public static Scene facilityAdminScene;
	ScrollPane sp = new ScrollPane();
	VBox vb = new VBox();
	HBox hb = new HBox();
	Button back = new Button("Back");
	ArrayList<Facility> facilities = new ArrayList<>();
	ArrayList<HBox> hboxlist = new ArrayList<>();
	HBox buttonContainer = new HBox();
	
	public FacilityAdmin(Stage stg) {
		try {
//			System.out.println("here");
			Connections.openCon();
			String query = "SELECT * FROM facility";
			Connections.state = Connections.connect.prepareStatement(query);
			ResultSet rs = Connections.state.executeQuery();
			while(rs.next()) {
//				System.out.println("facility added");
				facilities.add(new Facility(rs.getString("name"), rs.getInt("capacity"), rs.getString("status"), rs.getString("location"), rs.getInt("custCount")));
			}
			int counter = -1;
			int listIndex = -1;
			for (Facility i : facilities) {
				counter++;
//				System.out.println(counter);
				if(counter % 3 == 0) {
//					System.out.println("new hbox added");
					HBox hbox = new HBox();
					hbox.getStyleClass().add("facilityContainer");
					hboxlist.add(hbox);
					
					listIndex++;
				}
//				System.out.println("listIndex:" + listIndex);
				
				
				Spinner<Integer> capacity = new Spinner<>();
				SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, i.getCapacity());
				capacity.setValueFactory(svf);
				capacity.setEditable(true);
				capacity.getValueFactory().setValue(i.getCapacity());
				ComboBox<String> cb = new ComboBox<>();
				cb.getItems().addAll("Available","On Renovation","Closed");
				cb.setValue(i.getStatus());
				String str = "Name: " + i.getName();
				Label lbl = new Label(str);
				Button btn = new Button("Submit Changes");
				VBox temp = new VBox();
				temp.getChildren().add(lbl);
				temp.setSpacing(7);
				
				Label lblStatus = new Label("Status:");
				Label lblCapacity = new Label("Capacity:");
				VBox temp_1 = new VBox();
				VBox temp_2 = new VBox();
				temp_1.getChildren().add(lblStatus);
				temp_1.getChildren().add(cb);
				temp_2.getChildren().add(lblCapacity);
				temp_2.getChildren().add(capacity);
				temp.getChildren().addAll(temp_1,temp_2,btn);
				
				temp.getStyleClass().add("facility");
				lbl.getStyleClass().add("text");
				lblStatus.getStyleClass().add("text");
				lblCapacity.getStyleClass().add("text");
//				System.out.println("b4hboxlist");
				
				hboxlist.get(listIndex).getChildren().add(temp);
//				System.out.println("afterhboxlist");
//				vb.getChildren().add(temp);
				
				btn.setOnAction(e->{
					try {
						Connections.openCon();
						if(cb.getValue()!=null) {
							String qry = "UPDATE facility SET status = ? ,custCount = 0, capacity = ? WHERE name = ?";
							Connections.state = Connections.connect.prepareStatement(qry);
							Connections.state.setString(1, cb.getValue());
							Connections.state.setInt(2, capacity.getValue());
							Connections.state.setString(3, i.getName());
							Connections.state.executeUpdate();
							
							qry = "DELETE FROM facility_book WHERE fac_name =?";
							Connections.state = Connections.connect.prepareStatement(qry);
							Connections.state.setString(1, i.getName());
							Connections.state.executeUpdate();
							
							facilities.removeAll(facilities);
							vb.getChildren().removeAll(vb.getChildren());
							new FacilityAdmin(stg);
							Functions.informUser("Facility Updated");
							stg.setScene(MainScene.mainScene);
							stg.setTitle("Main Menu");
						}else {
							Functions.alertUser("Choose Facility Status!");
						}
						Connections.closeCon();
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
				});
				
			}
			
			for(int i = 0; i <= listIndex; i++) {
				vb.getChildren().add(hboxlist.get(i));
			}
			
			Button btnAvailable = new Button("Open All");
			Button btnClose = new Button("Close All");
//			vb.getChildren().add(btnAvailable);
//			vb.getChildren().add(btnClose);
			
			
			buttonContainer.getChildren().addAll(back, btnAvailable, btnClose);
			buttonContainer.setAlignment(Pos.CENTER);
			vb.getChildren().add(buttonContainer);
			
			vb.setAlignment(Pos.CENTER);
			
			btnAvailable.setOnAction(e->{
				try {
					Connections.openCon();
					String qry = "UPDATE facility SET status = 'Available' ,custCount = 0";
					Connections.state = Connections.connect.prepareStatement(qry);
					Connections.state.executeUpdate();
					
					qry = "DELETE FROM facility_book";
					Connections.state = Connections.connect.prepareStatement(qry);
					Connections.state.executeUpdate();
						
					facilities.removeAll(facilities);
					vb.getChildren().removeAll(vb.getChildren());
					new FacilityAdmin(stg);
					Functions.informUser("Facility Updated");
					stg.setScene(MainScene.mainScene);
					stg.setTitle("Main Menu");
					
					Connections.closeCon();
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			});
			
			btnClose.setOnAction(e->{
				try {
					Connections.openCon();
					String qry = "UPDATE facility SET status = 'Closed' ,custCount = 0";
					Connections.state = Connections.connect.prepareStatement(qry);
					Connections.state.executeUpdate();
					
					qry = "DELETE FROM facility_book";
					Connections.state = Connections.connect.prepareStatement(qry);
					Connections.state.executeUpdate();
						
					facilities.removeAll(facilities);
					vb.getChildren().removeAll(vb.getChildren());
					new FacilityAdmin(stg);
					Functions.informUser("Facility Updated");
					stg.setScene(MainScene.mainScene);
					stg.setTitle("Main Menu");
					
					Connections.closeCon();
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			});
			Connections.closeCon();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		vb.setSpacing(10);
		vb.setPadding(new Insets(10));
		

		hb.getChildren().add(vb);
		hb.setAlignment(Pos.CENTER);
		
		hb.getStyleClass().add("background");
		vb.getStyleClass().add("componentBackground");
		buttonContainer.getStyleClass().add("buttonContainer");
		
		facilityAdminScene = new Scene(hb,1000,500);
		facilityAdminScene.getStylesheets().add(getClass().getResource("/resources/FacilityAdmin.css").toExternalForm());
		back.setOnAction(e->{
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
	}
}
