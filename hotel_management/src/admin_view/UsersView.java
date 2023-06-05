package admin_view;

import java.sql.ResultSet;

import database.Connections;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import persons.Guest;
import persons.Person;
import ratings.Rating;
import view.MainScene;

public class UsersView {
	public static Scene usersViewScene;
	ScrollPane sp = new ScrollPane();
	TableView<Person> tvUser = new TableView<>();
	Button back = new Button("Back");
	ObservableList<Person> users;
	VBox vb = new VBox();
	HBox tableContainer = new HBox();
	
	
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
		
		tvUser.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		tvUser.setPrefWidth(Region.USE_COMPUTED_SIZE);
		tvUser.setMaxWidth(Double.MAX_VALUE);
		
		id.setCellFactory(column -> new TableCell<Person, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});
		
		name.setCellFactory(column -> new TableCell<Person, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});
		
		email.setCellFactory(column -> new TableCell<Person, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(item);
		        setTextFill(Color.WHITE); // Set text color to white
		    }
		});
	}
	
	public UsersView(Stage stg) {
		load();
		sp.setContent(tvUser);
		tableContainer.getChildren().add(sp);
		tableContainer.setAlignment(Pos.CENTER);
		
		vb.getChildren().addAll(tableContainer,back);
		vb.setAlignment(Pos.CENTER);
		
		vb.getStyleClass().add("background");
		
		usersViewScene = new Scene(vb,1000,500);
		// Uses the same resource as RatingsView
		usersViewScene.getStylesheets().add(getClass().getResource("/resources/RatingsView.css").toExternalForm());
		back.setOnAction(e->{
			stg.setScene(MainScene.mainScene);
			stg.setTitle("Main Menu");
		});
	}
	
}
