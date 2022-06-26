package application;

import java.sql.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

public class NewCustomer extends Application 
{

	private double xOffset = 0;
    private double yOffset = 0;
    
	public NewCustomer() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
	public static String encryption(String input) 
    { 
        try 
        { 
  
        	// Static getInstance method is called with hashing MD5 
        	MessageDigest md = MessageDigest.getInstance("MD5"); 
  
        	// digest() method is called to calculate message digest 
        	//  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
            
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) 
            { 
            	hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) 
        { 
        	throw new RuntimeException(e); 
        } 
    }
	public static GridPane right(GridPane leftgrid, Stage primaryStage)
	{
		Button login = new Button();
        login.setText("Login");
        login.setId("LoginButton");
        login.setMinWidth(350);
        
        Button clear = new Button();
        clear.setText("Reset");
		clear.setId("ResetButton");
		clear.setMinWidth(350);
      	
      	Label state = new Label();
      	state.setStyle("-fx-text-fill: #ffffff;");
      	state.setText("State ");
      	final TextField state_text = new TextField();
      	state_text.setPromptText("Enter your State");
      	state_text.setAlignment(Pos.BASELINE_LEFT);
      	state_text.setText("");
      	
      	Label city = new Label();
      	city.setStyle("-fx-text-fill: #ffffff;");
      	city.setText("City ");
      	final TextField city_text = new TextField();
      	city_text.setPromptText("Enter your City");
      	city_text.setAlignment(Pos.BASELINE_LEFT);
      	city_text.setText("");
      	
      	Label pincode = new Label();
      	pincode.setStyle("-fx-text-fill: #ffffff;");
      	pincode.setText("Pincode");
      	final TextField pincode_text = new TextField();
      	pincode_text.setPromptText("Enter your Pincode");
      	pincode_text.setAlignment(Pos.BASELINE_LEFT);
      	pincode_text.setText("");
      	
      	Label country = new Label();
      	country.setStyle("-fx-text-fill: #ffffff;");
      	country.setText("Country");
      	final TextField country_text = new TextField();
      	country_text.setPromptText("Enter your Country");
      	country_text.setAlignment(Pos.BASELINE_LEFT);
      	country_text.setText("");
      	
      	Label address = new Label();
      	address.setStyle("-fx-text-fill: #ffffff;");
      	address.setText("Address");
      	final TextField address_text = new TextField();
      	address_text.setPromptText("Enter your Address");
      	address_text.setAlignment(Pos.BASELINE_LEFT);
      	address_text.setText("");
		
		GridPane rightgrid = new GridPane();
      	rightgrid.getStylesheets().add("button.css");
      	rightgrid.setPadding(new Insets(10, 10, 10, 50));
      	rightgrid.setStyle("-fx-background-color: #474747;");
      	rightgrid.setAlignment(Pos.BASELINE_LEFT);
      	rightgrid.setMinHeight(600);
      	rightgrid.setMinWidth(600);
      	rightgrid.setMaxWidth(500);
      	rightgrid.setHgap(15);
      	rightgrid.setVgap(60);
      	rightgrid.maxWidth(50);
      	rightgrid.add(address, 0, 1);
      	rightgrid.add(address_text, 1, 1);
      	rightgrid.add(country, 0, 2);
      	rightgrid.add(country_text, 1, 2);
      	rightgrid.add(state, 0, 3);
      	rightgrid.add(state_text, 1, 3);
      	rightgrid.add(city, 0, 4);
      	rightgrid.add(city_text, 1, 4);
      	rightgrid.add(pincode, 0, 5);
      	rightgrid.add(pincode_text, 1, 5);
      	rightgrid.add(login, 1, 6);
      	rightgrid.add(clear, 1, 7);
      	
      	Alert error = new Alert(AlertType.ERROR);
        error.setTitle("Error Dialog");
        error.setHeaderText(null);
        error.setContentText("Username Already Taken !!!");
        error.initStyle(StageStyle.UNDECORATED);
        
        Alert erroradd = new Alert(AlertType.ERROR);
        erroradd.setTitle("Error Dialog");
        erroradd.setHeaderText(null);
        erroradd.setContentText("Can't create contact");
        erroradd.initStyle(StageStyle.UNDECORATED);
        
        Alert passerror = new Alert(AlertType.ERROR);
        passerror.setTitle("Error Dialog");
        passerror.setHeaderText(null);
        passerror.setContentText("Password Mismatch");
        passerror.initStyle(StageStyle.UNDECORATED);
        
        Alert success = new Alert(AlertType.INFORMATION);
        success.setTitle("Success");
        success.setHeaderText(null);
        success.setContentText("New Customer created !!");
        success.initStyle(StageStyle.UNDECORATED);
      	
      	login.setOnAction(e -> 
        {
        	long cust_id = 0 ;
        	ObservableList<Node> node = leftgrid.getChildren();
        	String user = "",email = "",add = "", cntry = "", stt = "", cty = "", pass = "", repass = "";
        	long phone = 0,pin = 0;
        	
        	for(int i=2;i<node.size();i+=2)
        	{
        		final TextField field = (TextField) node.get(i);
        		if(i==2)
        		{
        			user = field.getText();
        		}
        		if(i==4)
        		{
        			if(!field.getText().equals(""))
        			{
        				phone = Long.parseLong(field.getText());
        			}
        		}
        		if(i==6)
        		{
        			email = field.getText();
        		}
        		if(i==8)
        		{
        			pass = field.getText();
        		}
        		if(i==10)
        		{
        			repass = field.getText();
        		}
        	}
        	add = address_text.getText();
        	cntry = country_text.getText();
        	stt = state_text.getText();
        	cty = city_text.getText();
        	if(!pincode_text.getText().equals(""))
			{
        		pin = Long.parseLong(pincode_text.getText());
			}
        	try
    		{ 
        		
        		if(pass.equals(repass))
        		{
        			pass = "123"+pass+"123";
        			pass = encryption(pass);
        			Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
        			Statement stmt=con.createStatement();
        			String query = "INSERT INTO Account_User (username,password) VALUES (\'"+user+"\',\'"+pass+"\');";
        			stmt.executeUpdate(query);
        			con.close();
        		}
        		else
        		{
        			passerror.showAndWait();
        		}
    		}
    		catch(Exception ee)
    		{ 
    			error.showAndWait();
    		}
        	try
    		{
        		Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
    			Statement stmt=con.createStatement();
    			String query = "SELECT customer_id from account_user where username = \'"+user+"\';";
    			ResultSet rs = stmt.executeQuery(query);
    			rs.next();
    			System.out.println(rs.getString(1));
    			cust_id = Long.parseLong(rs.getString(1));
    			con.close();
    		}
    		catch(Exception ee)
    		{ 
    			error.showAndWait();
    		}
        	try
    		{ 
        		Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
    			Statement stmt=con.createStatement();
    			String query = "INSERT INTO Contact (phone_number,email,locality,pincode,city,state,country,customer_id,supplier_id) VALUES"
    					+ " ("+phone+",\'"+email+"\',\'"+add+"\',"+pin+",\'"+stt+"\',\'"+cty+"\',\'"+cntry+"\',"+cust_id+",null);";
    			stmt.executeUpdate(query);
    			con.close();
    		}
    		catch(Exception ee)
    		{ 
    			System.err.println(ee);
    		}
        	success.showAndWait();
        	primaryStage.close();
        });
      	clear.setOnAction(e -> 
        {
        	primaryStage.close();
        });
      	
      	return rightgrid;
	}
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		try 
		{
			primaryStage.initStyle(StageStyle.UNDECORATED);
			
			BorderPane root = new BorderPane();
			root.setStyle("-fx-background-color: #494949;");
	        
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
	        root.setTop(grid);
	        grid.getColumnConstraints().addAll(col1);
	        
			Label name = new Label("ASTRALIS");
			name.setStyle("-fx-text-fill: #ffffff;");
			
			Label pass = new Label(" v 1.0 bETA");
			pass.setStyle("-fx-text-fill: #ffffff;");
			name.setAlignment(Pos.	CENTER_LEFT);
			pass.setAlignment(Pos.CENTER_RIGHT);
			
			name.setAlignment(Pos.	CENTER_LEFT);
			pass.setAlignment(Pos.CENTER_RIGHT);
			
			GridPane foot = new GridPane();
			foot.setStyle("-fx-background-color: #dc143c;");
			foot.setPadding(new Insets(10, 10, 10, 20));
			foot.add(name, 0, 0);
			foot.add(pass, 1, 0);
			root.setBottom(foot);
			
			DropShadow ee = new DropShadow();
	    	ee.setWidth(20);
	    	ee.setHeight(20);
	    	ee.setOffsetX(10);
	    	ee.setOffsetY(10);
	    	ee.setRadius(10);
	        Image img = new Image(new FileInputStream("customer.png"));
	        ImageView i = new ImageView(img);
	        i.setEffect(ee);
	        i.setX(10);
	        i.setY(10);
	        i.setFitHeight(320); 
	      	i.setFitWidth(320);
	      	i.setPreserveRatio(true);
	      	i.setStyle("-fx-background-color: #494949;");
			
	      	Button login = new Button();
	        login.setText("Login");
	        login.setId("LoginButton");
	        login.setMinWidth(180);
	        
	        Button clear = new Button();
	        clear.setText("Reset");
			clear.setId("ResetButton");
			clear.setMinWidth(180);
	      	
	      	Label username = new Label();
	      	username.setStyle("-fx-text-fill: #ffffff;");
	      	username.setText("Username");
	      	final TextField username_text = new TextField();
	      	username_text.setPromptText("Enter your Username");
	      	username_text.setAlignment(Pos.BASELINE_LEFT);
	      	username_text.setText("");
	      	
	      	Label phone = new Label();
	      	phone.setStyle("-fx-text-fill: #ffffff;");
	      	phone.setText("Phone");
	      	final TextField phone_text = new TextField();
	      	phone_text.setPromptText("Enter your Phone");
	      	phone_text.setAlignment(Pos.BASELINE_LEFT);
	      	phone_text.setText("");
	      	
	      	Label email = new Label();
	      	email.setStyle("-fx-text-fill: #ffffff;");
	      	email.setText("Email");
	      	final TextField email_text = new TextField();
	      	email_text.setPromptText("sample@sample.com");
	      	email_text.setAlignment(Pos.BASELINE_LEFT);
	      	email_text.setText("");
	      	
	      	Label country = new Label();
	      	country.setStyle("-fx-text-fill: #ffffff;");
	      	country.setText("Country");
	      	final TextField country_text = new TextField();
	      	country_text.setPromptText("Enter your Country");
	      	country_text.setAlignment(Pos.BASELINE_LEFT);
	      	country_text.setText("");
	      	
	      	Label address = new Label();
	      	address.setStyle("-fx-text-fill: #ffffff;");
	      	address.setText("Address");
	      	final TextField address_text = new TextField();
	      	address_text.setPromptText("Enter your Address");
	      	address_text.setAlignment(Pos.BASELINE_LEFT);
	      	address_text.setText("");
	      	
	      	Label password = new Label();
	      	password.setStyle("-fx-text-fill: #ffffff;");
	      	password.setText("Password");
	      	final PasswordField password_text = new PasswordField();
	      	password_text.setPromptText("Password");
	      	password_text.setAlignment(Pos.BASELINE_LEFT);
	      	password_text.setText("");

	      	Label repassword = new Label();
	      	repassword.setStyle("-fx-text-fill: #ffffff;");
	      	repassword.setText("Confirm Password");
	        final PasswordField repassword_text = new PasswordField();
	        repassword_text.setPromptText("Retype Password");
	        repassword_text.setAlignment(Pos.BASELINE_LEFT);
	        repassword_text.setText("");
	        
	      	GridPane leftgrid = new GridPane();
	      	leftgrid.getStylesheets().add("button.css");
	      	leftgrid.add(i, 1, 0);
	      	leftgrid.setPadding(new Insets(10, 10, 10, 50));
	      	leftgrid.setStyle("-fx-background-color: #3a3a3a;");
	      	leftgrid.setAlignment(Pos.BASELINE_LEFT);
	      	leftgrid.setMinHeight(600);
	      	leftgrid.setMinWidth(600);
	      	leftgrid.setMaxWidth(500);
	      	leftgrid.setHgap(15);
	      	leftgrid.setVgap(60);
	      	leftgrid.maxWidth(50);
	      	leftgrid.add(username, 0, 1);
	      	leftgrid.add(username_text, 1, 1);
	      	leftgrid.add(phone, 0, 2);
	      	leftgrid.add(phone_text, 1, 2);
	      	leftgrid.add(email, 0, 3);
	      	leftgrid.add(email_text, 1, 3);
	      	leftgrid.add(password, 0, 4);
	      	leftgrid.add(password_text, 1, 4);
	      	leftgrid.add(repassword, 0, 5);
	      	leftgrid.add(repassword_text, 1, 5);
	      	
	      	VBox over1 = new VBox();
	        over1.setStyle("-fx-background-color: #474747;");
	    	over1.setMaxWidth(500);
	        over1.setMinWidth(600);
	        over1.setMinHeight(600);
	      	
	      	root.setLeft(leftgrid);
	      	
	      	VBox over2 = new VBox();
	        over2.setStyle("-fx-background-color: #3a3a3a;");
	    	over2.setMaxWidth(500);
	        over2.setMinWidth(600);
	        over2.setMinHeight(600);
	      	
	        root.setRight(right(leftgrid,primaryStage));
	      	
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
