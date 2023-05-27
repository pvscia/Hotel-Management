package admin_view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import rooms.Room;

public class RoomsAdminScene {
	public static Scene adminRoomsScene;
	TableView<Room> rooms = new TableView<>();
	ScrollPane sp = new ScrollPane();
	TextField tf = new TextField();
	Button wakeUp = new Button("Wake Up");
	Button clean = new Button("Clean");
	
	
}
