package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;

public class OrderAmenities {
	public static Scene orderAmenitiesScene;
	ComboBox<String> cbAmenities = new ComboBox<>();
	Spinner<Integer> qty = new Spinner<>();
	Button submit = new Button("Submit");
}
