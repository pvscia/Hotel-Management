package admin_view;

import java.sql.ResultSet;
import java.util.ArrayList;

import database.Connections;
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
				cb.getItems().addAll("Available","On Renovation");
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
				
			}
			Connections.closeCon();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		vb.setSpacing(10);
		sp.setContent(vb);
		facilityAdminScene = new Scene(sp,500,500);
		
		back.setOnAction(e->{
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
	}
}
