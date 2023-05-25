package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.LoginScene;
import view.MainScene;
import view.RegisterScene;

public class Main extends Application {

	@Override
	public void start(Stage stg) throws Exception {
		new MainScene();
		stg.setScene(MainScene.mainScene);
		stg.show();

	}

	public static void main(String[] args) {
		launch(args);
//		System.out.println("omomomomo");
	}

}
