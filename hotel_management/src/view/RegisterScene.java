package view;

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
	
	public RegisterScene() {
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
		registerScene = new Scene(bpRegister,600,475);
	}
}
