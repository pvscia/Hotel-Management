package admin_view;

import java.sql.ResultSet;
import java.util.ArrayList;

import database.Connections;
import database.Functions;
import facilities.Facility;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.MainScene;

public class FacilityAdmin {
	public static Scene facilityAdminScene;
	ScrollPane sp = new ScrollPane();
	VBox vb = new VBox();
	Button back = new Button("Back");
	ArrayList<Facility> facilities = new ArrayList<>();
	
	
	
	public FacilityAdmin(Stage stg) {
		try {
			Connections.openCon();
			String query = "SELECT * FROM facility";
			Connections.state = Connections.connect.prepareStatement(query);
			ResultSet rs = Connections.state.executeQuery();
			while(rs.next()) {
				facilities.add(new Facility(rs.getString("name"), rs.getInt("capacity"), rs.getString("status"), rs.getString("location"), rs.getInt("custCount")));
			}
			vb.getChildren().add(back);
			for (Facility i : facilities) {
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
				temp.getChildren().add(new Label("Status:"));
				temp.getChildren().add(cb);
				temp.getChildren().add(new Label("Capacity"));
				temp.getChildren().add(capacity);
				temp.getChildren().add(btn);
				
				vb.getChildren().add(temp);
				
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
			
			Button btnAvailable = new Button("Open All");
			Button btnClose = new Button("Close All");
			vb.getChildren().add(btnAvailable);
			vb.getChildren().add(btnClose);
			
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
		sp.setContent(vb);
		facilityAdminScene = new Scene(sp,1000,500);
		
		back.setOnAction(e->{
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
	}
}
