package application;
import java.sql.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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

public class Customer_Home_Page extends Application
{
	public static String user ;
	public static String pass;
	public static String id;
	private double xOffset = 0;
    private double yOffset = 0;
	@Override
	
	public void start(final Stage primaryStage)
	{
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
	        nav1.setText("Search");
	        
	        Button nav2 = new Button();
	        nav2.setId("NavigationButton");
	        nav2.setText("Add Product(s) to Cart");
	        
	        Button nav3 = new Button();
	        nav3.setId("NavigationButton");
	        nav3.setText("Pending Order");
	        
	        Button nav4 = new Button();
	        nav4.setId("NavigationButton");
	        nav4.setText("Change Password");
	        
	        Button nav5 = new Button();
	        nav5.setId("NavigationButton");
	        nav5.setText("Check Out");

	        Button nav6 = new Button();
	        nav6.setId("NavigationButton");
	        nav6.setText("Order History");
	        
	        Button nav8 = new Button();
	        nav8.setId("GeneralButtonRed");
	        nav8.setPrefSize(1000, 20);
	        nav8.setText("Log Out");
	        
	        Button nav7 = new Button();
	        nav7.setId("NavigationButton");
	        nav7.setPrefSize(1000, 20);
	        nav7.setText("Recommender");
	        
	        Label user = new Label();
	        user.setStyle("-fx-text-fill: #ffffff;");
	        user.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffffff;-fx-font-size: 20;");
	        
	        Image img = new Image(new FileInputStream("customer.png"));
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
	      	navigation.setMargin(nav0, new Insets(30,10,0,10));
	      	navigation.setMargin(nav1, new Insets(0,10,0,10));
	  		navigation.setMargin(nav2, new Insets(0,10,0,10));
	  		navigation.setMargin(nav3, new Insets(0,10,0,10));
	  		navigation.setMargin(nav6, new Insets(0,10,0,10));
	      	navigation.setMargin(nav4, new Insets(0,10,0,10));
	      	navigation.setMargin(nav5, new Insets(0,10,0,10));
	      	navigation.setMargin(nav6, new Insets(0,10,0,10));
	      	navigation.setMargin(nav7, new Insets(0,10,0,10));
	      	navigation.setMargin(nav8, new Insets(60,10,0,10));
	      	navigation.setVgap(0);
	      	
	      	navigation.add(i, 0, 1);
	      	navigation.add(user, 0, 2);
	      	navigation.add(nav0, 0, 3);
	      	navigation.add(nav1, 0, 4);
	      	navigation.add(nav2, 0, 5);
      		navigation.add(nav4, 0, 6);
      		navigation.add(nav5, 0, 7);
      		navigation.add(nav3, 0, 8);
      		navigation.add(nav6, 0, 9);
      		navigation.add(nav7, 0, 10);
          	navigation.add(nav8, 0, 11);

          	Customer_Home_Page_Panels obj = new Customer_Home_Page_Panels(Customer_Home_Page.id,Customer_Home_Page.user, Customer_Home_Page.pass);
          	root.setCenter(obj.HomePanel());
            nav0.setOnAction(e ->
            {
//            	Home Panel
            	VBox temp = obj.HomePanel();
            	root.setCenter(temp);
            });
            nav1.setOnAction(e ->
            {
//            	Search By Different Aspects of a product
            	VBox temp = obj.SearchPanel();
            	root.setCenter(temp);
            });
            nav2.setOnAction(e ->
            {
//            	Helps the user to put the chosen items in the panel
            	VBox temp = obj.AddOrUpdateCartPanel();
            	root.setCenter(temp);
            });
            nav3.setOnAction(e ->
            {
//            	Helps the user to put the chosen items in the panel
            	VBox temp = obj.PendingOrderPanel();
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
            	VBox temp = obj.CheckOutPanel();
            	root.setCenter(temp);
            });
            nav6.setOnAction(e ->
            {
//            	Updates the Username and the Password for the logged in user
            	VBox temp = obj.OrderHistoryPanel();
            	root.setCenter(temp);
            });
            nav7.setOnAction(e ->
            {
//            	Updates the Username and the Password for the logged in user
            	VBox temp = obj.RecommenderPanel();
            	root.setCenter(temp);
            });
            nav8.setOnAction(e ->
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
	public Customer_Home_Page(String a, String b, String c) 
	{
		// TODO Auto-generated constructor stub
		Customer_Home_Page.id = a;
		Customer_Home_Page.user = b;
		Customer_Home_Page.pass = c;
		
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		launch(args);
	}

}
