package view;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class LoginScene {
	public static Scene loginScene;
	GridPane gpLogin = new GridPane();
	BorderPane bpLogin = new BorderPane();
	HBox hbLogin =new HBox();
	Label lLoginEmail = new Label("Email");
	Label lLoginPassword = new Label("Password");
	TextField tfLoginEmail = new TextField();
	PasswordField pfLoginPassword = new PasswordField();
	Button btnLogin_login = new Button("Login");
	Button btnLogin_register = new Button("Register");
	
	public LoginScene() {
		tfLoginEmail.setPromptText("xxxxxx@gmail.com");
		pfLoginPassword.setPromptText("Password");
		
		hbLogin.getChildren().addAll(btnLogin_login,btnLogin_register);
		
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
		
		loginScene = new Scene(bpLogin,400,400);
	}
}
