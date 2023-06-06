package view;

import java.time.LocalDateTime;

import database.Connections;
import database.Functions;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import main.Main;

public class AddRatingScene {
	public static Scene addRatingScene;
	Button back = new Button("Back");
	VBox vb = new VBox();
	Label lblComplaint = new Label("Complaint");
	TextField tfComplaint = new TextField();
	Label lblRating = new Label("Rating");
	Slider rating = new Slider(1,5,3);
	Button submit = new Button("Submit");
	
	
	void cleanFields() {
		rating.setValue(1);
		tfComplaint.clear();
	}
	
	public AddRatingScene(Stage stg) {
		vb.getChildren().addAll(back,lblComplaint,tfComplaint,lblRating,rating,submit);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(5);
		
		vb.getStyleClass().add("background");
		
		tfComplaint.setPromptText("Type if there is any complaint");
		tfComplaint.setMaxWidth(300);
		
		rating.setMaxWidth(300);
		
		rating.setShowTickLabels(true);
		rating.setShowTickMarks(true);
		rating.setMajorTickUnit(1);
		
		back.getStyleClass().add("submit");
		submit.getStyleClass().add("submit");
		
		addRatingScene = new Scene(vb,1000,500);
		addRatingScene.getStylesheets().add(getClass().getResource("/resources/AddRating.css").toExternalForm());
		rating.valueProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				Pane sp = (Pane) rating.lookup(".track");
				String style = "-fx-background-color: linear-gradient(to right, #f0eaa8 "+((arg2.doubleValue()-1)*25)+"%, #3D484F "+((arg2.doubleValue()-1)*25)+"%);";
				sp.setStyle(style);
			}
			
		});;
		
		
		back.setOnAction(e->{
			cleanFields();
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
		
		submit.setOnAction(e->{
			try {
				Connections.openCon();
				LocalDateTime now = LocalDateTime.now(); 
				String query = "INSERT INTO `rating`(`date`, `complaint`, `roomNumber`, `rating`) VALUES ('"+ Main.dtf.format(now)+"','"+tfComplaint.getText()+"',"+Main.roomNo+","+(int)rating.getValue()+")";
				Connections.state = Connections.connect.prepareStatement(query);
				Connections.state.executeUpdate();
				Connections.closeCon();
				
				cleanFields();
				stg.setScene(MainScene.mainScene);
				stg.setTitle("Main Menu");
				Functions.informUser("Rating submitted");
			} catch (Exception e2) {
				// TODO: handle exception
			}
		});
	}
}
