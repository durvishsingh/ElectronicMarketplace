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

public class Customer extends Application
{
	public static GridPane Customer(final Stage primaryStage) throws FileNotFoundException
	{
		try
		{
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
	        i.setFitHeight(300); 
	      	i.setFitWidth(300);
	      	i.setPreserveRatio(true);
	      	i.setStyle("-fx-background-color: #494949;");
	        
	        
	        Button login = new Button();
	        login.setText("Login");
	        login.setId("LoginButton");
	        login.setMinWidth(350);
	        
	        Button clear = new Button();
	        clear.setText("Reset");
			clear.setId("ResetButton");
			clear.setMinWidth(350);
	        
	        Button newuser = new Button();
	        newuser.setText("Register");
	        newuser.setId("GeneralButtonGreen");
	        newuser.setMinWidth(350);
			
			
	        final TextField username = new TextField();
	        username.setPromptText("Customer Login");
	        username.setAlignment(Pos.BASELINE_LEFT);
	        username.setText("");
	        username.setText("Durvish");
	        
	        final PasswordField password = new PasswordField();
	        password.setPromptText("Password");
	        password.setAlignment(Pos.BASELINE_LEFT);
	        password.setText("");
	        password.setText("durvish17231");
	        
	        Label name = new Label();
	        name.setStyle("-fx-text-fill: #ffffff;");
	        name.setText("USERNAME");
	        
	        Label pass = new Label();
	        pass.setStyle("-fx-text-fill: #ffffff;");
	        pass.setText("PASSWORD");
	        
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Information Dialog");
	        alert.setHeaderText(null);
	        alert.setContentText("Logged In successfully !");
	        alert.initStyle(StageStyle.UNDECORATED);
	        
	        Alert err = new Alert(AlertType.ERROR);
	        err.setTitle("Error Dialog");
	        err.setHeaderText(null);
			err.setContentText("Incorrect Login or Password !");
			err.initStyle(StageStyle.UNDECORATED);
			
			GridPane grid = new GridPane();
	        grid.getStylesheets().add("button.css");
	        grid.add(i, 1, 0);
	        grid.setPadding(new Insets(10, 10, 10, 50));
	        grid.setStyle("-fx-background-color: #3a3a3a;");
	        grid.setAlignment(Pos.BASELINE_RIGHT);
	        grid.setMinHeight(600);
	        grid.setMinWidth(600);
	        grid.setMaxWidth(500);
	        grid.setHgap(15);grid.setVgap(60);
	        grid.maxWidth(50);
	        grid.add(name, 0, 1);
	        grid.add(username, 1, 1);
	        grid.add(pass, 0, 2);
	        grid.add(password, 1, 2);
	        grid.add(login, 1, 3);
	        grid.add(clear, 1, 4);
	        grid.add(newuser, 1, 5);
	        grid.setAlignment(Pos.TOP_LEFT);
	        
	        Alert error = new Alert(AlertType.ERROR);
	        error.setTitle("Error Dialog");
	        error.setHeaderText(null);
	        error.setContentText("Incorrect Login or Password !");
	        error.initStyle(StageStyle.UNDECORATED);
	        
	        newuser.setOnAction(e -> 
	        {
	        	
        		NewCustomer obj = new NewCustomer();
        		Stage tertiaryStage = new Stage();
        		try 
        		{
					obj.start(tertiaryStage);
				} 
        		catch (Exception e1) 
        		{
					e1.printStackTrace();
				}
	        });
	        
	        login.setOnAction(e -> 
	        {
	        	String temp_id = exists(username.getText(),password.getText()); 
	        	if(temp_id!=null)
	        	{
	        		primaryStage.close();
	        		Customer_Home_Page obj = new Customer_Home_Page(temp_id, username.getText(),password.getText());
	        		Stage secondaryStage = new Stage();
	        		obj.start(secondaryStage);
	        		System.out.println("Username : " + username.getText());
	            	System.out.println("Password : " + password.getText());
	        	}
	        	else
	        	{
	        		error.showAndWait();
	        		username.setText(null);
	    	  		password.setText(null);
	        	}
	        });
	  		clear.setOnAction(e ->
	  		{
		  		username.setText(null);
		  		password.setText(null);
			});
	  		return grid;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static String exists(String username, String password)
	{
		String flag = null;
		try
		{   
			flag = null;
			String encrtyped_password = "123" + password + "123" ;
			Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from Account_User");	
			
			while(rs.next())
			{
//				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
//				System.out.println(rs.getString(2)+" "+username);
//				System.out.println(rs.getString(3)+" "+password);
				if(rs.getString(2).equals(username) && (rs.getString(3).equals(password) || rs.getString(3).equals(encryption(encrtyped_password))))
				{
					flag = rs.getString(1);
				}
			}
			con.close();
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
		
		return flag;
		
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
	@Override
	public void start(Stage arg0) throws Exception 
	{
		
	}

}
