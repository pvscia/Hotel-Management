package admin_view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rooms.Room;
import view.MainScene;

public class RoomsAdminScene {
	public static Scene adminRoomsScene;
	TableView<Room> rooms = new TableView<>();
	ScrollPane sp = new ScrollPane();
	TextField tf = new TextField();
	Button wakeUp = new Button("Wake Up");
	Button clean = new Button("Clean");
	Button back = new Button("Back");
	VBox vb = new VBox();
	
	TableColumn<Room,String> roomNumber = new TableColumn<>("No");
	TableColumn<Room,String> bedType = new TableColumn<>("Bed Type");
	TableColumn<Room,String> roomType = new TableColumn<>("Room Type");
	TableColumn<Room,String> roomView = new TableColumn<>("View");
	TableColumn<Room,String> roomSize = new TableColumn<>("Size");
	TableColumn<Room,String> roomCapacity = new TableColumn<>("Capacity");
	TableColumn<Room,String> roomPrice = new TableColumn<>("Price");
	TableColumn<Room,String> dnd = new TableColumn<>("DND");
	TableColumn<Room,String> wakeUpCall = new TableColumn<>("Wake Up");
	TableColumn<Room,String> availability = new TableColumn<>("Status");
	
	@SuppressWarnings("unchecked")
	public RoomsAdminScene(Stage stg) {
		sp.setContent(rooms);
		rooms.getColumns().setAll(roomNumber,bedType,roomType,roomView,roomSize,roomCapacity,roomPrice,dnd,wakeUpCall,availability);
		vb.getChildren().add(back);
		vb.getChildren().add(sp);
		adminRoomsScene = new Scene(vb,850,850);
		back.setOnAction(e->{
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
	}
	
	
}
