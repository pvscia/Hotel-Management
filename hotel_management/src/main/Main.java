package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stg) throws Exception {
		BorderPane root= new BorderPane();
		stg.setTitle("WOWOOWOWOWO");
		stg.setScene(new Scene(root,300,400));
		stg.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
