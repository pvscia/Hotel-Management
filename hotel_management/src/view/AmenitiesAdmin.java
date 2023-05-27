package view;

import java.sql.ResultSet;
import java.util.ArrayList;

import database.Connections;
import facilities.Inventory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AmenitiesAdmin {
	public static Scene amenitiesAdminScene;
	ScrollPane sp = new ScrollPane();
	VBox vb = new VBox();
	ArrayList<Inventory> inven = new ArrayList<>();
	
	
	
	public AmenitiesAdmin(Stage stg) {
		try {
			Connections.openCon();
			String query = "SELECT * FROM inventory";
			Connections.state = Connections.connect.prepareStatement(query);
			ResultSet rs = Connections.state.executeQuery();
			while(rs.next()) {
				inven.add(new Inventory(rs.getString("name"), rs.getInt("stock")));
			}
			
			for (Inventory i : inven) {
				System.out.println(i.getName());
				Spinner<Integer> stock = new Spinner<>();
				SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, i.getStock());
				stock.setValueFactory(svf);
				stock.getValueFactory().setValue(i.getStock());
				String str = "Name: " + i.getName();
				Label lbl = new Label(str);
				Button btn = new Button("Update Stock");
				VBox temp = new VBox();
				temp.getChildren().add(lbl);
				temp.getChildren().add(stock);
				temp.getChildren().add(btn);
				
				vb.getChildren().add(temp);
				
				btn.setOnAction(e->{
					
				});
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		vb.setSpacing(5);
		sp.setContent(vb);
		amenitiesAdminScene = new Scene(sp,500,500);
	}
}
