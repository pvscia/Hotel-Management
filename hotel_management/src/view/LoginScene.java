package view;

import java.sql.ResultSet;

import database.Connections;
import database.Functions;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Main;
import persons.Guest;
import persons.Staff;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;

public class LoginScene {
	public static Scene loginScene;
	GridPane gpLogin = new GridPane();
	BorderPane bpLogin = new BorderPane();
	HBox hbLogin = new HBox();
	Label lLoginEmail = new Label("Email");
	Label lLoginPassword = new Label("Password");
	TextField tfLoginEmail = new TextField();
	PasswordField pfLoginPassword = new PasswordField();
	Button btnLogin_login = new Button("Login");
	Button btnLogin_register = new Button("Register");
	
	public void clearFields() {
		tfLoginEmail.clear();
		pfLoginPassword.clear();
	}
	
	public LoginScene(Stage stg) {
		tfLoginEmail.setPromptText("xxxxxx@gmail.com");
		pfLoginPassword.setPromptText("Password");
		
		hbLogin.getChildren().addAll(btnLogin_login,btnLogin_register);
		hbLogin.setSpacing(10);
		
		gpLogin.setVgap(5);
		gpLogin.add(lLoginEmail, 0, 0);
		gpLogin.add(tfLoginEmail, 0, 1);
		gpLogin.add(lLoginPassword, 0, 2);
		gpLogin.add(pfLoginPassword, 0, 3);
		gpLogin.add(hbLogin, 0, 4);
		
		hbLogin.setSpacing(5);
		hbLogin.setAlignment(Pos.BOTTOM_RIGHT);
		
		gpLogin.setAlignment(Pos.CENTER);
		
		bpLogin.setCenter(gpLogin);
		Image image = new Image("bed.jpg");
		BackgroundSize backgroundSize = new BackgroundSize(100.0, 100.0, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
		bpLogin.setBackground(new Background(backgroundImage));
		Background background = new Background(backgroundImage);
		bpLogin.setBackground(background);
		
		loginScene = new Scene(bpLogin,1000,500);
		
		loginScene.getStylesheets().add(getClass().getResource("/resources/mainss.css").toExternalForm());
		
		
		 //LOGIN SCENE => REGISTER SCENE
		 btnLogin_register.setOnAction(e->{
			 clearFields();
			 stg.setScene(RegisterScene.registerScene);
			 stg.setTitle("Register");
		 });
		 
//		//LOGIN VALIDATION => CHECK ROLE => MAIN SCENE
		 btnLogin_login.setOnAction(e->{
			 ResultSet rs;
			 String alert=null;
			 try {
				 	Connections.openCon();
					String query = "SELECT * FROM user WHERE email = ?";
					Connections.state = Connections.connect.prepareStatement(query);
					Connections.state.setString(1,tfLoginEmail.getText());
					rs = Connections.state.executeQuery();
					if(rs.next()) {
						if(pfLoginPassword.getText().equals(rs.getString("password"))) {
							 if(rs.getString("role").equals("admin")) {
									Main.user = new Staff(rs.getString("id"), rs.getString("name"), rs.getString("gender"), rs.getString("username"), rs.getString("email"), rs.getString("password"));
									MainScene.lblA.setText("Welcome, "+Main.user.getName()+"\nUserID: "+Main.user.getId());
									try {
										MainScene.vbMain.getChildren().remove(0);
									}
									catch(Exception exc) {
										System.out.println("nothing to remove");
									}
									
									MainScene.vbMain.getChildren().add(MainScene.containerPaneAdmin);
								  
//									MainScene.gpUserCheckIn.setVisible(false);
//									MainScene.gpAdmin.setVisible(true);
								}else if(rs.getString("role").equals("user")) {
									Main.user = new Guest(rs.getString("id"), rs.getString("name"), rs.getString("gender"), rs.getString("username"), rs.getString("email"), rs.getString("password"));
									query = "SELECT * FROM booking WHERE guestID = ?";
									Connections.state = Connections.connect.prepareStatement(query);
									Connections.state.setString(1, rs.getString("id"));
									rs = Connections.state.executeQuery();
									if(rs.next()) {
										Main.roomNo = rs.getInt("roomNumber");
										MainScene.lbl.setText("Welcome, "+Main.user.getName()+"\nUserID: "+Main.user.getId());
										try {
											MainScene.vbMain.getChildren().remove(0);
										}
										catch(Exception excu) {
											System.out.println("nothing to remove");
										}
										
										MainScene.vbMain.getChildren().add(MainScene.containerPane);	
//										MainScene.gpUserCheckIn.setVisible(true);
//										MainScene.gpAdmin.setVisible(false);
									}else {
										alert = "Welcome, "+Main.user.getName()+"\nUserID: "+Main.user.getId() +"\nYou have not booked / checked in\nPlease contact reception to book a room";
									}
								}
							 	if(alert == null) {
							 		clearFields();
							 		stg.setScene(MainScene.mainScene);
							 		stg.setTitle("Main Menu");	 		
							 	}
						}else {
							alert = "Password doesn't match!";
						}
					}else {
						alert ="Email doesn't exist!";
					}
					Connections.closeCon();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			 
			 if(alert!=null) {
				 Functions.alertUser(alert);
			 }
		 });
	}
}
