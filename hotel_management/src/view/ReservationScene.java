package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;

public class ReservationScene {
	public static Scene reservationScene;
	DatePicker datePick = new DatePicker();
	ComboBox<String> cbRooms = new ComboBox<>();
	Button bookRoom = new Button("Book Room");
	
}
