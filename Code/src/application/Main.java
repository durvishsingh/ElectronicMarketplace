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


public class Main extends Application 
{
	private double xOffset = 0;
    private double yOffset = 0;
	@Override
	public void start(final Stage primaryStage) 
	{
		try 
		{
			primaryStage.initStyle(StageStyle.UNDECORATED);
			
			BorderPane root = new BorderPane();
			root.setStyle("-fx-background-color: #ffffff;");
	        
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
	                primaryStage.	setY(event.getScreenY() - yOffset);
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
	        
	        
	        VBox over1 = new VBox();
	        over1.setStyle("-fx-background-color: #474747;");
	    	over1.setMaxWidth(500);
	        over1.setMinWidth(600);
	        over1.setMinHeight(600);
	    	
	    	root.setRight(Customer.Customer(primaryStage));
	        
	        VBox over2 = new VBox();
	        over2.setStyle("-fx-background-color: #3a3a3a;");
	    	over2.setMaxWidth(500);
	        over2.setMinWidth(600);
	        over2.setMinHeight(600);
	        
	        root.setLeft(Admin.Admin(primaryStage));
			
			Label name = new Label("ASTRALIS");
			name.setStyle("-fx-text-fill: #ffffff;");
			
			Label pass = new Label(" v 1.0 bETA");
			pass.setStyle("-fx-text-fill: #ffffff;");
			name.setAlignment(Pos.CENTER_LEFT);
			pass.setAlignment(Pos.CENTER_RIGHT);
			
			GridPane foot = new GridPane();
			foot.setStyle("-fx-background-color: #dc143c;");
			foot.setPadding(new Insets(10, 10, 10, 20));
			foot.add(name, 0, 0);
			foot.add(pass, 1, 0);
			root.setBottom(foot);
			
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
