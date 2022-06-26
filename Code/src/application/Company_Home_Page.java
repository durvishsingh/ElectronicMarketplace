package application;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;  
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import javax.swing.text.html.StyleSheet.ListPainter;

import com.sun.javafx.scene.control.skin.DatePickerSkin;

import application.Customer_Home_Page_Panels.orderhistory;
import application.Customer_Home_Page_Panels.pending_order;
import application.Customer_Home_Page_Panels.product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;


import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Company_Home_Page extends Application
{
	public static String user ;
	public static String pass;
	public static String id;
	private double xOffset = 0;
    private double yOffset = 0;
	public Company_Home_Page() {}
	public static void main(String[] args) 
	{
		launch(args);
	}
	public Company_Home_Page(String a, String b, String c)
	{
		// TODO Auto-generated method stub
		Company_Home_Page.id = a;
		Company_Home_Page.user = b;
		Company_Home_Page.pass = c;
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try 
		{
			primaryStage.initStyle(StageStyle.UNDECORATED);
			
			BorderPane root = new BorderPane();
			root.setStyle("-fx-background-color: #b5b5b5;");
	        
	        final Tooltip tooltip = new Tooltip();
	        tooltip.setText("\nClose\n");
	        root.setOnMousePressed(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                xOffset = event.getSceneX();
	                yOffset = event.getSceneY();
	            }
	        });
	        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                primaryStage.setX(event.getScreenX() - xOffset);
	                primaryStage.setY(event.getScreenY() - yOffset);
	            }
	        });
	        
	        Button mini = new Button();
	        mini.setId("OpenMinButton");
	        mini.setTooltip(new Tooltip("Minimize"));
	        mini.setText("-");
	        mini.setOnAction(e -> {((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);});
	        
	        
	        Label text = new Label();
	        text.setText("Super Marche");
	        text.setStyle("-fx-text-fill: #ffffff;");
	        text.setId("MarketName");
	        text.setAlignment(Pos.TOP_LEFT);
	        
	        Button exit = new Button();
	        exit.setId("OpenMinButton");
	        exit.setTooltip(new Tooltip("Close"));
	        exit.setText("x");
	        exit.setOnAction(e -> primaryStage.close());
	        
	        ColumnConstraints col1 = new ColumnConstraints();
	        col1.setPercentWidth(94);
	        ColumnConstraints col2 = new ColumnConstraints();
	        col2.setPercentWidth(3);
	        ColumnConstraints col3 = new ColumnConstraints();
	        col3.setPercentWidth(3);
	        
	        GridPane grid = new GridPane();
	        grid.getStylesheets().add("button.css");
	        grid.setStyle("-fx-background-color: #dc143c;");
	        grid.setPadding(new Insets(5, 0, 10, 5));
	        grid.setVgap(5); 
	        grid.setHgap(5);
	        grid.add(exit, 2, 0);
	        grid.add(mini, 1, 0);
	        grid.add(text, 0, 0);
	        grid.getColumnConstraints().addAll(col1);
	        root.setTop(grid);
			
			Label name = new Label("ASTRALIS");
			name.setStyle("-fx-text-fill: #ffffff;");
			name.setAlignment(Pos.CENTER_LEFT);
			
			Label pass = new Label(" v 1.0 bETA");
			pass.setStyle("-fx-text-fill: #ffffff;");
			pass.setAlignment(Pos.CENTER_RIGHT);
			
			GridPane foot = new GridPane();
			foot.setStyle("-fx-background-color: #dc143c;");
			foot.setPadding(new Insets(10, 10, 10, 20));
			foot.add(name, 0, 0);
			foot.add(pass, 1, 0);
			root.setBottom(foot);
			
			Button nav0 = new Button();
	        nav0.setId("NavigationButton");
	        nav0.setText("Home");
			
			Button nav1 = new Button();
	        nav1.setId("NavigationButton");
	        nav1.setText("Add items to Inventory");
	        
	        Button nav2 = new Button();
	        nav2.setId("NavigationButton");
	        nav2.setText("Add Product To Market");
	        
	        Button nav3 = new Button();
	        nav3.setId("NavigationButton");
	        nav3.setText("Three");
	        
	        Button nav4 = new Button();
	        nav4.setId("NavigationButton");
	        nav4.setText("Change Password");
	        
	        Button nav5 = new Button();
	        nav5.setId("NavigationButton");
	        nav5.setText("Queries");

	        Button nav6 = new Button();
	        nav6.setId("NavigationButton");
	        nav6.setText("Console");
	        
	        Button nav7 = new Button();
	        nav7.setId("GeneralButtonRed");
	        nav7.setPrefSize(1000, 20);
	        nav7.setText("Log Out");
	        
	        Label user = new Label();
	        user.setStyle("-fx-text-fill: #ffffff;");
	        user.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffffff;-fx-font-size: 20;");
	        
	        Image img = new Image(new FileInputStream("admin.png"));
	        ImageView i = new ImageView(img);
	        i.setFitHeight(230);
	      	i.setFitWidth(230);
	      	i.setPreserveRatio(true);
	      	i.setStyle("-fx-background-color: #494949;");
	      	
	      	GridPane navigation = new GridPane();
	        navigation.setMinHeight(500);
	      	navigation.setMinWidth(300);
	      	navigation.setMaxWidth(300);
	      	navigation.setStyle("-fx-background-color: #494949;");
	        navigation.setAlignment(Pos.TOP_CENTER);
	        navigation.getStylesheets().add("button.css");
	      	navigation.setHgap(0);
	      	navigation.setPadding(new Insets(0,0,0,0));
	      	navigation.setMargin(user, new Insets(20,10,10,10));
	      	navigation.setMargin(i, new Insets(30,30,30,30));
	      	navigation.setMargin(nav0, new Insets(60,10,0,10));
	      	navigation.setMargin(nav1, new Insets(0,10,0,10));
	  		navigation.setMargin(nav2, new Insets(0,10,0,10));
	  		navigation.setMargin(nav3, new Insets(0,10,0,10));
	  		navigation.setMargin(nav6, new Insets(0,10,0,10));
	      	navigation.setMargin(nav4, new Insets(0,10,0,10));
	      	navigation.setMargin(nav6, new Insets(0,10,0,10));
	      	navigation.setMargin(nav7, new Insets(60,10,0,10));
	      	navigation.setVgap(0);
	      	
	      	navigation.add(i, 0, 1);
	      	navigation.add(user, 0, 2);
	      	navigation.add(nav0, 0, 3);
	      	navigation.add(nav1, 0, 4);
	      	navigation.add(nav2, 0, 5);
      		navigation.add(nav4, 0, 6);
      		navigation.add(nav5, 0, 7);
      		navigation.add(nav6, 0, 8);
          	navigation.add(nav7, 0, 9);

          	Company_Home_Page_Panels obj = new Company_Home_Page_Panels(Company_Home_Page.id,Company_Home_Page.user, Company_Home_Page.pass);
          	root.setCenter(obj.HomePanel());
            nav0.setOnAction(e ->
            {
//            	Home Panel that Displays the username and the pass of the logged in user
            	VBox temp = obj.HomePanel();
            	root.setCenter(temp);
            });
            nav1.setOnAction(e ->
            {
//            	Search By Different Aspects of a product
            	VBox temp = obj.InventoryPanel();
            	root.setCenter(temp);
            });
            nav2.setOnAction(e ->
            {
//            	Helps the user to put the chosen items in the panel
            	VBox temp = obj.AddNewProductPanel();
            	root.setCenter(temp);
            });
            nav4.setOnAction(e ->
            {
//            	Updates the Username and the Password for the logged in user
            	VBox temp = obj.PasswordUpdatePanel();
            	root.setCenter(temp);
            });
            nav5.setOnAction(e ->
            {
//            	Updates the Username and the Password for the logged in user
            	VBox temp = obj.QueryPanel();
            	root.setCenter(temp);
            });
            nav6.setOnAction(e ->
            {
//            	SQL Consoles Commands
            	VBox temp = obj.ConsolePanel();
            	root.setCenter(temp);
            });
            nav7.setOnAction(e ->
            {
            	Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Logged Out successfully !");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                
                Main main = new Main();
                
        		primaryStage.close();
				Stage n = new Stage();
				main.start(n);
            });
            
	      	root.setLeft(navigation);
	      	
			Scene scene = new Scene(root, 1200, 900);
	        primaryStage.setScene(scene);
	        primaryStage.setMinHeight(900);
	        primaryStage.setMinWidth(1200);
	        primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();	
		}
	}
	
}
