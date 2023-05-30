package admin_view;

import java.sql.ResultSet;

import database.Connections;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import persons.Guest;
import persons.Person;
import view.MainScene;

public class UsersView {
	public static Scene usersViewScene;
	ScrollPane sp = new ScrollPane();
	TableView<Person> tvUser = new TableView<>();
	Button back = new Button("Back");
	ObservableList<Person> users;
	VBox vb = new VBox();
	
	TableColumn<Person,String> id = new TableColumn<>("ID");
	TableColumn<Person,String> name = new TableColumn<>("Name");
	TableColumn<Person,String> email = new TableColumn<>("Email");
	
	@SuppressWarnings("unchecked")
	public void load() {
		users = FXCollections.observableArrayList();
		try {
			Connections.openCon();
			String query = "SELECT * FROM user WHERE role = 'user' ORDER BY name ASC ";
			Connections.state = Connections.connect.prepareStatement(query);
			ResultSet rs = Connections.state.executeQuery();
			while(rs.next()) {
				users.add(new Guest(rs.getString("id"), rs.getString("name"), rs.getString("gender"), rs.getString("username"), rs.getString("email"), rs.getString("password")));
			}
			Connections.closeCon();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		tvUser.setItems(users);
		id.setCellValueFactory(users -> new SimpleStringProperty(users.getValue().getId()));
		name.setCellValueFactory(users -> new SimpleStringProperty(users.getValue().getName()));
		email.setCellValueFactory(users -> new SimpleStringProperty(users.getValue().getEmail()));
		
		tvUser.getColumns().setAll(id,name,email);
	}
	
	public UsersView(Stage stg) {
		load();
		sp.setContent(tvUser);
		vb.getChildren().addAll(back,sp);
		usersViewScene = new Scene(vb,1000,500);
		
		back.setOnAction(e->{
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
	}
	
}
