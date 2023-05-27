package main;

import javafx.application.Application;
import javafx.stage.Stage;
import persons.Person;
import view.AmenitiesAdmin;
import view.LoginScene;
import view.MainScene;
import view.OrderAmenities;
import view.RegisterScene;

public class Main extends Application {
	
	public static Person user;
	public static int roomNo;
	
	@Override
	public void start(Stage stg) throws Exception {
		new LoginScene(stg);
		new RegisterScene(stg);
		new MainScene(stg);
		new OrderAmenities(stg);
		new AmenitiesAdmin(stg);
		stg.setTitle("Login");
		stg.setScene(LoginScene.loginScene);
		stg.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
