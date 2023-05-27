package admin_view;

import finance.Booking;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ReservationsAdminScene {
	public static Scene adminReservationScene;
	TableView<Booking> rvReservations = new TableView<>();
	ScrollPane sp = new ScrollPane();
	TextField tfBookingID = new TextField();
	TextField roomNo = new TextField();
	Button btnAllocateRoom = new Button("Allocate Room");
}
