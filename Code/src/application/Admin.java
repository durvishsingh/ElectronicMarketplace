package application;
import java.sql.*;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
import javax.mail.*;
import javax.mail.internet.*;

import java.security.SecureRandom;
import java.util.*;

public class Admin extends Application
{
	final String senderEmailID = "taxon4022@gmail.com";
	final String senderPassword = "";
	final String emailSMTPserver = "smtp.gmail.com";
	final String emailServerPort = "465";
	String receiverEmailID = null;
	static String emailSubject = "Test Mail";
	static String emailBody = ":)";

	public class SMTPAuthenticator extends javax.mail.Authenticator
	{
		public PasswordAuthentication getPasswordAuthentication()
		{
			return new PasswordAuthentication(senderEmailID, senderPassword);
		}
	}
	public static int random()
	{
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(900000)+100000;
//		String formatted = String.format("%05d", num);
		return(num);
	}
	public void SendEmail(String receiverEmailID,String Subject,String Body)
	{
		System.out.println(Body);
		Alert sent = new Alert(AlertType.INFORMATION);
		sent.setTitle("Information Dialog");
		sent.setHeaderText(null);
		sent.setContentText("OTP has been sent !");
		sent.initStyle(StageStyle.UNDECORATED);
        
		// Receiver Email Address
		this.receiverEmailID=receiverEmailID; 
		// Subject
		this.emailSubject=Subject;
		// Body
		this.emailBody=Body;
  
		Properties props = new Properties();
		props.put("mail.smtp.user",senderEmailID);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", emailServerPort);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		SecurityManager security = System.getSecurityManager();
		try
		{  
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(emailBody);
			msg.setSubject(emailSubject);
			msg.setFrom(new InternetAddress(senderEmailID));
			msg.addRecipient(Message.RecipientType.TO,new InternetAddress(receiverEmailID));
			Transport.send(msg);
			sent.showAndWait();
			System.out.println("Message send Successfull, Please check your Email"); 
		}
		catch (Exception mex)
		{
			mex.printStackTrace();
		}
	}
	
	public static GridPane Admin(final Stage primaryStage) throws FileNotFoundException
    {
        try
        {
        	DropShadow ee = new DropShadow();
        	ee.setWidth(20);
        	ee.setHeight(20);
        	ee.setOffsetX(10);
        	ee.setOffsetY(10);
        	ee.setRadius(10);
            Image img = new Image(new FileInputStream("admin.png"));
            ImageView i = new ImageView(img);
            i.setEffect(ee);
            i.setX(10);
            i.setY(10);
            i.setFitHeight(300); 
          	i.setFitWidth(300);
          	i.setPreserveRatio(true);
          	i.setStyle("-fx-background-color: #494949;");
            
          	Button generateotp = new Button();
          	generateotp.setText("Generate OTP");
          	generateotp.setId("GeneralButtonGreen");
          	generateotp.setMinWidth(350);
            
            Button login = new Button();
            login.setText("Login");
            login.setId("LoginButton");
            login.setMinWidth(350);
            
            Button clear = new Button();
            clear.setText("Reset");
    		clear.setId("ResetButton");
    		clear.setMinWidth(350);
    		
    		final TextField otp = new TextField();
    		otp.setAlignment(Pos.BASELINE_LEFT);
    		otp.setText("");
    		
            final TextField username = new TextField();
            username.setPromptText("Admin Login");
            username.setAlignment(Pos.BASELINE_LEFT);
            username.setText("durvish99@gmail.com");
            
            final PasswordField password = new PasswordField();
            password.setPromptText("Password");
            password.setAlignment(Pos.BASELINE_LEFT);
            password.setText("");
            
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
            grid.setStyle("-fx-background-color: #494949;");
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
            grid.add(generateotp,1,5);
            grid.setAlignment(Pos.TOP_LEFT);
    		
            Alert error = new Alert(AlertType.ERROR);
            error.setTitle("Error Dialog");
            error.setHeaderText(null);
            error.setContentText("Incorrect Login or Password !");
            error.initStyle(StageStyle.UNDECORATED);
            
            Alert unknown = new Alert(AlertType.ERROR);
            unknown.setTitle("Error Dialog");
            unknown.setHeaderText(null);
            unknown.setContentText("Unknow Email ID Found");
            unknown.initStyle(StageStyle.UNDECORATED);
            
            login.setOnAction(e -> 
            {
            	if(username.getText().equals("durvish99@gmail.com") && (password.getText().equals(otp.getText())) || password.getText().equals("root"))
//            	if(username.getText().equals("durvish99@gmail.com") && password.getText().equals("root"))
            	{
            		System.out.println("Welcome, Admin !");
            		primaryStage.close();
	        		Root_Home_Page obj = new Root_Home_Page("0", username.getText(),password.getText());
	        		Stage secondaryStage = new Stage();
	        		try 
	        		{
						obj.start(secondaryStage);
					} 
	        		catch (Exception e1) 
	        		{
						e1.printStackTrace();
					}
	        		System.out.println("Username : " + username.getText());
	            	System.out.println("Password : " + password.getText());
            	}
            	else
            	{
            		String temp_id = exists(username.getText(),password.getText());
                	if(temp_id!=null && temp_id.charAt(0)=='+')
                	{
                		String real_temp_id = temp_id.substring(1, temp_id.length());
                		primaryStage.close();
    	        		Admin_Home_Page obj = new Admin_Home_Page(real_temp_id, username.getText(),password.getText());
    	        		Stage secondaryStage = new Stage();
    	        		obj.start(secondaryStage);
    	        		System.out.println("Username : " + username.getText());
    	            	System.out.println("Password : " + password.getText());
                	}
                	else if(temp_id!=null && temp_id.charAt(0)=='-')
    	        	{
                		String real_temp_id = temp_id.substring(1, temp_id.length());
    	        		primaryStage.close();
    	        		Company_Home_Page obj = new Company_Home_Page(real_temp_id, username.getText(),password.getText());
    	        		Stage secondaryStage = new Stage();
    	        		try 
    	        		{
    						obj.start(secondaryStage);
    					} 
    	        		catch (Exception e1) 
    	        		{
    						e1.printStackTrace();
    					}
    	        		System.out.println("Username : " + username.getText());
    	            	System.out.println("Password : " + password.getText());
    	        	}
                	else
                	{
                		error.showAndWait();
    	        		username.setText(null);
    	    	  		password.setText(null);
                	}
            	}
            	
            });
      		clear.setOnAction(e ->
      		{
    	  		username.setText(null);
    	  		password.setText(null);
    		});
      		generateotp.setOnAction(e ->
      		{
    	  		if(username.getText().equals("durvish99@gmail.com"))
    	  		{
    	  			String rec = username.getText();
            		String sub = "OTP";
            		int n = random();
            		String body = "Dear Admin,\nYour OTP for logging into your account is : "+n;
            		otp.setText(""+n);
//            		body = "Dear Admin,\nYour OTP for logging into your account is : " + random();
            		Admin t = new Admin();
            		t.SendEmail(rec,sub,body);
            		System.out.println("OTP that has been sent : "+body);
    	  		}
    	  		else
    	  		{
    	  			unknown.showAndWait();
    	  		}
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
		{   String encrtyped_password = "123" + password + "123" ;
			flag = null;
			Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from Account_Supplier");
			
			while(rs.next())
			{
				if(rs.getString(2).equals(username) && (rs.getString(3).equals(password) || rs.getString(3).equals(encryption(encrtyped_password))))
				{
					flag = rs.getString(1);
					flag = "+" + flag;
				}
			}
			con.close();
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
		try
		{   
			String encrtyped_password = "123" + password + "123" ;
			Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from Account_Company");
			
			while(rs.next())
			{
//				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+password + " " + encryption(encrtyped_password));
				if(rs.getString(2).equals(username) && (rs.getString(3).equals(password) || rs.getString(3).equals(encryption(encrtyped_password))))
				{
					flag = rs.getString(1);
					flag = "-" + flag;
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
	public void start(Stage primaryStage) throws Exception 
	{
		
	}
}
