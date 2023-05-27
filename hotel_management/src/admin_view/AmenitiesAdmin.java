package admin_view;

import java.sql.ResultSet;
import java.util.ArrayList;

import database.Connections;
import database.Functions;
import facilities.Inventory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.MainScene;

public class AmenitiesAdmin {
	public static Scene amenitiesAdminScene;
	ScrollPane sp = new ScrollPane();
	VBox vb = new VBox();
	Button back = new Button("Back");
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
			vb.getChildren().add(back);
			for (Inventory i : inven) {
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
				
				vb.getChildren().add(temp);
				
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
			Connections.closeCon();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		vb.setSpacing(10);
		sp.setContent(vb);
		amenitiesAdminScene = new Scene(sp,500,500);
		
		back.setOnAction(e->{
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
	}
}
