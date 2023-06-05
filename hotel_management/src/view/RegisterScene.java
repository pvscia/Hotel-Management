package view;

import java.sql.ResultSet;

import database.Connections;
import database.Functions;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterScene {
	public static Scene registerScene;
	HBox hbRegisterBtns =new HBox();
	HBox hbRegisterTerms =new HBox();
	HBox hbRegisterGender =new HBox();
	VBox vbRegister = new VBox();
	BorderPane bpRegister = new BorderPane();
	Label lRegisterName = new Label("Name");
	Label lRegisterUsername = new Label("Username");
	Label lRegisterPassword = new Label("Password");
	Label lRegisterConfirmPassword = new Label("Confirm Password");
	Label lRegisterEmail = new Label("Email");
	Label lRegisterGender = new Label("Gender");
	Label lRegisterTerms = new Label("I agree with Terms and Conditions");
	TextField tfRegisterName  = new TextField();
	TextField tfRegisterUsername  = new TextField();
	PasswordField pfRegisterPassword = new PasswordField();
	PasswordField pfRegisterConfirmPassword = new PasswordField();
	TextField tfRegisterEmail  = new TextField();
	CheckBox cbRegister = new CheckBox();
	RadioButton rbRegisterMale = new RadioButton("Male");
	RadioButton rbRegisterFemale = new RadioButton("Female");
	ToggleGroup tgGender = new ToggleGroup();
	Button btnRegister_login = new Button("Login");
	Button btnRegister_register = new Button("Register");
	
	public void clearFields() {
		tfRegisterName.clear();
		tfRegisterUsername.clear();
		tfRegisterEmail.clear();
		pfRegisterConfirmPassword.clear();
		pfRegisterPassword.clear();
		rbRegisterFemale.setSelected(false);
		rbRegisterMale.setSelected(false);
		cbRegister.setSelected(false);
	}
	
	public RegisterScene(Stage stg) {
		hbRegisterTerms.getChildren().addAll(cbRegister,lRegisterTerms);
		hbRegisterBtns.getChildren().addAll(btnRegister_login,btnRegister_register);
		hbRegisterGender.getChildren().addAll(rbRegisterMale,rbRegisterFemale);
		
		rbRegisterFemale.setToggleGroup(tgGender);
		rbRegisterMale.setToggleGroup(tgGender);
		
		tfRegisterName.setPromptText("Name");
		tfRegisterUsername.setPromptText("Username");
		pfRegisterPassword.setPromptText("Password");
		pfRegisterConfirmPassword.setPromptText("Confirm Password");
		tfRegisterEmail.setPromptText("xxxxxx@gmail.com");
	
		hbRegisterBtns.setAlignment(Pos.BOTTOM_RIGHT);
		
		vbRegister.getChildren().addAll(lRegisterName,tfRegisterName,
										lRegisterUsername,tfRegisterUsername,
										lRegisterPassword,pfRegisterPassword,
										lRegisterConfirmPassword,pfRegisterConfirmPassword,
										lRegisterEmail,tfRegisterEmail,
										lRegisterGender,hbRegisterGender,
										hbRegisterTerms,hbRegisterBtns);
		vbRegister.setSpacing(7);
		hbRegisterBtns.setSpacing(10);
		hbRegisterGender.setSpacing(10);
		hbRegisterTerms.setSpacing(10);

		bpRegister.setCenter(vbRegister);
		bpRegister.setPadding(new Insets(30));
		
		lRegisterName.getStyleClass().add("text");
		lRegisterUsername.getStyleClass().add("text");
		lRegisterPassword.getStyleClass().add("text");
		lRegisterConfirmPassword.getStyleClass().add("text");
		lRegisterEmail.getStyleClass().add("text");
		lRegisterGender.getStyleClass().add("text");
		lRegisterTerms.getStyleClass().add("text");
		bpRegister.getStyleClass().add("background");
		
		registerScene = new Scene(bpRegister,1000,500);
		registerScene.getStylesheets().add(getClass().getResource("/resources/RegisterScene.css").toExternalForm());
		
		//REGISTER SCENE => LOGIN SCENE
		 btnRegister_login.setOnAction(e->{
			 clearFields();
			 stg.setScene(LoginScene.loginScene);
			 stg.setTitle("Login");
			 
		 });
		 
		 btnRegister_register.setOnAction(e->{
			 String alert=null;
			 ResultSet rs;
			 if(Functions.validateName(tfRegisterName.getText())) {
				 if(Functions.validateUsername(tfRegisterUsername.getText())) {
					 if(Functions.validatePassword(pfRegisterPassword.getText())) {
						 if(Functions.validateConfPass(pfRegisterConfirmPassword.getText(), pfRegisterPassword.getText())) {
							 if(Functions.validateEmail(tfRegisterEmail.getText())) {
								 if(cbRegister.isSelected()) {
									 if(rbRegisterFemale.isSelected()||rbRegisterMale.isSelected()) {
										 alert =null;
										 }else {
											 alert ="You must select a gender!";
										 }
									 }else {
										alert ="You must agree with terms and condition if you want to continue";
									 }
								 }else {
									 alert="Email must contain 1 @, unique, and ends with @gmail.com!";
								 }
							 }else {
								 alert = "Password doesn't match!";
								 }
						 }else {
							 alert = "Password must contain at least 8 characters and alphanumeric!";
							 }
					 }else {
						 alert = "Username must be 3 - 10 characters!";
						 }
				 }else {
					 alert ="Name must be 5 - 20 characters!";
					 }
			 
			 
			 try {
				
				Connections.openCon();
				String query = "SELECT * FROM user WHERE email =?";
				Connections.state = Connections.connect.prepareStatement(query);
				Connections.state.setString(1,tfRegisterEmail.getText());
				rs = Connections.state.executeQuery();
				if(rs.next()) {
					alert = "Email must be unique";
				}
				Connections.closeCon();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			 
			 if(alert!=null) {
				Functions.alertUser(alert);
			 }else {
				 String id;
				 try {
					 Connections.openCon();
					while(true) {
						id = Functions.generateID("user");
						String query = "SELECT * FROM user WHERE id =?";
						Connections.state = Connections.connect.prepareStatement(query);
						Connections.state.setString(1,id);
						rs=Connections.state.executeQuery();
						if(rs.next()) {
							continue;
						}else {
							break;
						}
					}
					
					String query = "INSERT INTO user(id, name, gender, username, email,password, role) VALUES (?,?,?,?,?,?,?)";
					Connections.state = Connections.connect.prepareStatement(query);
					Connections.state.setString(1,id);
					Connections.state.setString(2,tfRegisterName.getText());
					String gender = null;
					if(rbRegisterFemale.isSelected()) {
						gender = rbRegisterFemale.getText();
					}else if(rbRegisterMale.isSelected()) {
						gender = rbRegisterMale.getText();
					}
					Connections.state.setString(3,gender);
					Connections.state.setString(4,tfRegisterUsername.getText());
					Connections.state.setString(5,tfRegisterEmail.getText());
					Connections.state.setString(6,pfRegisterPassword.getText());
					Connections.state.setString(7,"user");
					Connections.state.executeUpdate();
					Connections.closeCon();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				 stg.setScene(LoginScene.loginScene);
				 stg.setTitle("Login"); 
			 }
		 });
	}
}
