package main;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import admin_view.AmenitiesAdmin;
import admin_view.FacilityAdmin;
import admin_view.RatingsView;
import admin_view.RoomsAdminScene;
import admin_view.UsersView;
import database.Functions;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import persons.Person;
import view.AddRatingScene;
import view.BookFacility;
import view.LoginScene;
import view.MainScene;
import view.OrderAmenities;
import view.RegisterScene;

public class Main extends Application {
	
	public static Person user;
	public static int roomNo;
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	
	@Override
	public void start(Stage stg) throws Exception {
		new LoginScene(stg);
		new RegisterScene(stg);
		new MainScene(stg);
		new OrderAmenities(stg);
		new AmenitiesAdmin(stg);
		new BookFacility(stg);
		new FacilityAdmin(stg);
		new RoomsAdminScene(stg);
		new UsersView(stg);
		new AddRatingScene(stg);
		new RatingsView(stg);
		stg.setTitle("Login");
		stg.setScene(LoginScene.loginScene);
		Image img = new Image(getClass().getResource("/resources/logo.png").toExternalForm());
		stg.getIcons().add(img);
		stg.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
