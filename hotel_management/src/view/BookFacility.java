package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookFacility {
	public static Scene bookFacilityScene;
	VBox vb = new VBox();
	Spinner<Integer> people = new Spinner<>();
	SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2,1);
	ComboBox<String> facilities = new ComboBox<>();
	Button btnBack = new Button("Back");
	Button btnSubmit = new Button("Submit");
	
	public BookFacility(Stage stg) {
		vb.getChildren().add(btnBack);
		vb.setSpacing(5);
		facilities.getItems().addAll("Gym","Playground","Rooftop","Spa","Swimming Pool");
		people.setValueFactory(svf);
		vb.getChildren().addAll(facilities,people,btnSubmit);
		bookFacilityScene = new Scene(vb,500,500);
		
		btnBack.setOnAction(e->{
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
	}
}
