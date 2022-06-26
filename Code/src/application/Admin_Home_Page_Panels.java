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

public class Admin_Home_Page_Panels extends Application 
{
	public static String id;
	public static String user;
	public static String pass;	
	private static TableView table = new TableView();
	public static class inventory
	{
		long product_id;
		String product_name;
		double price;
		int quantity;
		long supplier_id;
		String supplier_name;
		
		public inventory(long a, String b, double c, int d, long e, String f)
		{
			this.setProduct_id(a);
			this.setProduct_name(b);
			this.setPrice(c);
			this.setQuantity(d);
			this.setSupplier_id(e);
			this.setSupplier_name(f);
		}
		
		public void setProduct_id(long a) {this.product_id = a;};
		public void setProduct_name(String a) {this.product_name = a;};
		public void setPrice(double a) {this.price = a;};
		public void setQuantity(int a) {this.quantity = a;};
		public void setSupplier_id(long a) {this.supplier_id = a;};
		public void setSupplier_name(String a) {this.supplier_name = a;};
		
		public long getProduct_id() {return this.product_id;};
		public String getProduct_name() {return this.product_name;};
		public double getPrice() {return this.price;};
		public int getQuantity() {return this.quantity;};
		public long getSupplier_id() {return this.supplier_id;};
		public String getSupplier_name() {return this.supplier_name;};
	}
	public static class pending_order
	{
		long customer_id;
		long supplier_id;
		long product_id;
		int quantity;
		String date,customername;
		public pending_order(long a, long b, long c, int d, String e, String f)
		{
			this.setCustomer_id(b);	
			this.setSupplier_id(a);
			this.setProduct_id(c);
			this.setQuantity(d);
			this.setDate(e);
			this.setCustomername(f);
		}
		public void setCustomer_id(long quantity) {this.customer_id = quantity;}
		public void setSupplier_id(long quantity) {this.supplier_id = quantity;}
		public void setProduct_id(long quantity) {this.product_id = quantity;}
		public void setQuantity(int quantity) {this.quantity = quantity;}
		public void setDate(String e) {this.date = e;}
		public void setCustomername(String f) {this.customername = f;}
		
		public long getCustomer_id(){ return customer_id;};
		public long getSupplier_id(){ return supplier_id;};
		public long getProduct_id(){ return product_id;};
		public int getQuantity(){ return quantity;};
		public String getDate(){ return date;};
		public String getCustomername(){ return customername;};
	}
	public static class temp_order 
	{
		long id;
		int quantity;
		public temp_order(long a, int h)
		{
			this.setId(a);
			this.setQuantity(h);
		}
		public void setId(long id){this.id = id;}
		public void setQuantity(int quantity) {this.quantity = quantity;}
		
		public long getId(){return id;}
		public long getQuantity(){return quantity;}
	}
	public static void showcart()
	{
		
	}
	public static class order
	{
		String blank1;
		String blank2;
		long id;
    	String name;
    	double price;
    	String company;
    	String category;
    	String color;
    	double rating;
    	int quantity;
    	double cost;
    	public static double final_cost = 0;
    	public order(long a, String b, double c, String d, String e, String f, double g, int h)
    	{
    		this.blank1 = " ";
    		this.blank2 = " ";
    		this.setId(a);
    		this.setName(b);
    		this.setPrice(c);
    		this.setCompany(d);
    		this.setCategory(e);
    		this.setColor(f);
    		this.setRating(g);
    		this.setQuantity(h);
    		this.cost = c*h;
    		final_cost = final_cost + cost;
    	}
    	public void setId(long id){this.id = id;}
    	public void setName(String name){this.name = name;}
    	public void setPrice(double price){this.price = price;}
    	public void setCompany(String company){this.company = company;}
    	public void setCategory(String category){this.category = category;}
    	public void setColor(String color){this.color = color;}
    	public void setRating(double rating){this.rating = rating;}
    	public void setQuantity(int quantity) {this.quantity = quantity;}
    	
    	public  String getBlank2() {return blank2;}
    	public String getBlank1() {return blank1;}
    	public long getId(){return id;}
    	public String getName(){return name;}
    	public double getPrice(){return price;}	
    	public String getCompany(){return company;}
    	public String getCategory(){return category;}
    	public String getColor(){return color;}
    	public double getRating(){return rating;}
    	public long getQuantity(){return quantity;}
    	public double getCost() {return cost;}
    	public double getFinal_cost() {return final_cost;}
	}
	public static class orderhistory
	{
		long order_id;
		long customer_id;
		long product_id;
		long supplier_id;
		String order_date,time,name,delivery_date,status ;
		public orderhistory(long a,long b, long b2,long c, String d, String e, String f, String g, String h)
		{
			this.setOrder_id(a);
			this.setCustomer_id(b);
			this.setSupplier_id(b2);
			this.setProduct_id(c);
			this.setOrder_date(d);
			this.setTime(e);
			this.setName(f);
			this.setDelivery_date(g);
			this.setStatus(h);
		}
		public void setOrder_id(long a){this.order_id = a;}
		public void setCustomer_id(long a){this.customer_id = a;}
		public void setSupplier_id(long a){this.supplier_id = a;}
		public void setProduct_id(long a){this.product_id = a;}
		public void setOrder_date(String a){this.order_date = a;}
		public void setTime(String a){this.time = a;}
		public void setName(String a){this.name = a;}
		public void setDelivery_date(String a){this.delivery_date = a;}
		public void setStatus(String a){this.status = a;}
		
		public long getOrder_id(){return order_id;}
		public long getCustomer_id(){return supplier_id;}
		public long getProduct_id(){return product_id;}
		public String getOrder_date(){return order_date;}
		public String getTime(){return time;}
		public String getName(){return name;}
		public String getDelivery_date(){return delivery_date;}
		public String getStatus(){return status;}
	}
	public static class product 
    {
    	long id;
    	String name;
    	double price;
    	String company;
    	String category;
    	String color;
    	double rating;
    	long quantity;
    	public product(long a, String b, double c, String d, String e, String f, double g, long h)
    	{
    		this.setId(a);
    		this.setName(b);
    		this.setPrice(c);
    		this.setCompany(d);
    		this.setCategory(e);
    		this.setColor(f);
    		this.setRating(g);
    		this.setQuantity(h);
    	}
    	public void setId(long id){this.id = id;}
    	public void setName(String name){this.name = name;}
    	public void setPrice(double price){this.price = price;}
    	public void setCompany(String company){this.company = company;}
    	public void setCategory(String category){this.category = category;}
    	public void setColor(String color){this.color = color;}
    	public void setRating(double rating){this.rating = rating;}
    	public void setQuantity(long quantity) {this.quantity = quantity;}
    	
    	public long getId(){return id;}
    	public String getName(){return name;}
    	public double getPrice(){return price;}	
    	public String getCompany(){return company;}
    	public String getCategory(){return category;}
    	public String getColor(){return color;}
    	public double getRating(){return rating;}
    	public long getQuantity(){return quantity;}
    	
    	public String toString(){return(this.id+""+this.name+""+this.price+""+this.company+""+this.category+""+this.color+""+this.rating);}
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
	public Admin_Home_Page_Panels(String a, String b, String c) 
	{
		// TODO Auto-generated constructor stub
		Admin_Home_Page_Panels.id = a;
		Admin_Home_Page_Panels.user = b;
		Admin_Home_Page_Panels.pass = c;
		System.out.println("Logged in as : " + a + " " + b + " " + c);
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	public static void DisplayInventory(VBox ans, ObservableList<inventory> temp)
	{
		TableView<inventory> table = new TableView<inventory>();
		table.setId("table-view");
//		table.setStyle("-fx-background-color: #ff9393;");
		table.setEditable(false);
		TableColumn Id = new TableColumn("Product ID");
		Id.setMinWidth(25);
		Id.setCellValueFactory(new PropertyValueFactory<product, String>("product_id"));
		
		TableColumn Name = new TableColumn("Product Name");
		Name.setMinWidth(25);
		Name.setCellValueFactory(new PropertyValueFactory<product, String>("product_name"));
		
		TableColumn Price = new TableColumn("Product Cost");
		Price.setMinWidth(25);
		Price.setCellValueFactory(new PropertyValueFactory<product, String>("price"));
		
		TableColumn Company = new TableColumn("Quantity (in stock)");
		Company.setMinWidth(25);
		Company.setCellValueFactory(new PropertyValueFactory<product, String>("quantity"));
		
		TableColumn Category = new TableColumn("Supplier ID");
		Category.setMinWidth(25);
		Category.setCellValueFactory(new PropertyValueFactory<product, String>("supplier_id"));
		
		TableColumn Color = new TableColumn("Supplier Name");
		Color.setMinWidth(25);
		Color.setCellValueFactory(new PropertyValueFactory<product, String>("supplier_name"));
		
		table.getColumns().addAll(Id,Name,Company,Category,Color,Price);
		
//		grid.setPadding(new Insets(10,10,10,10));
		table.setItems(temp);
//		((Group) scene.getRoot()).getChildren().addAll(grid);
		if(ans.getChildren().size()>1)
		{
			ans.getChildren().remove(ans.getChildren().size()-1);
		}
		ans.getChildren().add(table);
	}
	public static void DisplayOrder (VBox ans, ObservableList<order> temp) 
	{
		TableView<order> table = new TableView<order>();
		table.setId("table-view");
//		table.setStyle("-fx-background-color: #ff9393;");
		table.setEditable(false);
		TableColumn Id = new TableColumn("ID");
		Id.setMinWidth(25);
		Id.setCellValueFactory(new PropertyValueFactory<product, String>("id"));
		
		TableColumn Name = new TableColumn("Name");
		Name.setMinWidth(25);
		Name.setCellValueFactory(new PropertyValueFactory<product, String>("name"));
		
		TableColumn Price = new TableColumn("Price");
		Price.setMinWidth(25);
		Price.setCellValueFactory(new PropertyValueFactory<product, String>("price"));
		
		TableColumn Company = new TableColumn("Company");
		Company.setMinWidth(25);
		Company.setCellValueFactory(new PropertyValueFactory<product, String>("company"));
		
		TableColumn Category = new TableColumn("Category");
		Category.setMinWidth(25);
		Category.setCellValueFactory(new PropertyValueFactory<product, String>("category"));
		
		TableColumn Color = new TableColumn("Color");
		Color.setMinWidth(25);
		Color.setCellValueFactory(new PropertyValueFactory<product, String>("color"));
		
		TableColumn Rating = new TableColumn("Rating");
		Rating.setMinWidth(25);
		Rating.setCellValueFactory(new PropertyValueFactory<product, String>("rating"));
		
		TableColumn Quantity = new TableColumn("Quantity");
		Quantity.setMinWidth(25);
		Quantity.setCellValueFactory(new PropertyValueFactory<product, String>("quantity"));
   	 	
		TableColumn Cost = new TableColumn("Cost");
		Cost.setMinWidth(25);
		Cost.setCellValueFactory(new PropertyValueFactory<product, String>("cost"));
		
		TableColumn Dash = new TableColumn(" ");
		Dash.setMinWidth(25);
		Dash.setCellValueFactory(new PropertyValueFactory<product, String>("blank1"));
		
		TableColumn Final_Cost = new TableColumn(" Total Cost ");
		Final_Cost.setMinWidth(25);
		Final_Cost.setCellValueFactory(new PropertyValueFactory<product, String>("final_cost"));
		
		table.getColumns().addAll(Id,Name,Company,Category,Color,Rating,Quantity,Price,Dash,Cost,Final_Cost);
		
//		grid.setPadding(new Insets(10,10,10,10));
		table.setItems(temp);
//		((Group) scene.getRoot()).getChildren().addAll(grid);
		if(ans.getChildren().size()>1)
		{
			ans.getChildren().remove(ans.getChildren().size()-1);
		}
		ans.getChildren().add(table);
	}
	public static void DisplayOrderHistory(VBox ans, ObservableList<orderhistory> temp)
	{
		TableView<orderhistory> table = new TableView<orderhistory>();
		table.setId("table-view");
//		table.setStyle("-fx-background-color: #ff9393;");
		table.setEditable(false);
		TableColumn Id = new TableColumn("Order ID");
		Id.setMinWidth(25);
		Id.setCellValueFactory(new PropertyValueFactory<product, String>("order_id"));
		
		TableColumn Supplier = new TableColumn("Customer ID");
		Supplier.setMinWidth(25);
		Supplier.setCellValueFactory(new PropertyValueFactory<product, String>("customer_id"));
		
		TableColumn Name = new TableColumn("Date Ordered");
		Name.setMinWidth(25);
		Name.setCellValueFactory(new PropertyValueFactory<product, String>("order_date"));
		
		TableColumn Price = new TableColumn("Time");
		Price.setMinWidth(25);
		Price.setCellValueFactory(new PropertyValueFactory<product, String>("time"));
		
		TableColumn Company = new TableColumn("Product Name");
		Company.setMinWidth(25);
		Company.setCellValueFactory(new PropertyValueFactory<product, String>("name"));
		
		TableColumn Category = new TableColumn("Delivery Date");
		Category.setMinWidth(25);
		Category.setCellValueFactory(new PropertyValueFactory<product, String>("delivery_date"));
		
		TableColumn Color = new TableColumn("Status");
		Color.setMinWidth(25);
		Color.setCellValueFactory(new PropertyValueFactory<product, String>("status"));
		
		TableColumn Rating = new TableColumn("Product ID");
		Rating.setMinWidth(25);
		Rating.setCellValueFactory(new PropertyValueFactory<product, String>("product_id"));
		
		table.getColumns().addAll(Id,Supplier,Name,Company,Category,Color,Rating,Price);
		
//		grid.setPadding(new Insets(10,10,10,10));
		table.setItems(temp);
//		((Group) scene.getRoot()).getChildren().addAll(grid);
		if(ans.getChildren().size()>1)
		{
			ans.getChildren().remove(ans.getChildren().size()-1);
		}
		ans.getChildren().add(table);
	}
	public static void DisplayProduct(VBox ans,ObservableList<product> temp)
	{
		TableView<product> table = new TableView<product>();
		table.setId("table-view");
//		table.setStyle("-fx-background-color: #ff9393;");
		table.setEditable(false);
		TableColumn Id = new TableColumn("ID");
		Id.setMinWidth(25);
		Id.setCellValueFactory(new PropertyValueFactory<product, String>("id"));
		
		TableColumn Name = new TableColumn("Name");
		Name.setMinWidth(25);
		Name.setCellValueFactory(new PropertyValueFactory<product, String>("name"));
		
		TableColumn Price = new TableColumn("Price");
		Price.setMinWidth(25);
		Price.setCellValueFactory(new PropertyValueFactory<product, String>("price"));
		
		TableColumn Company = new TableColumn("Company");
		Company.setMinWidth(25);
		Company.setCellValueFactory(new PropertyValueFactory<product, String>("company"));
		
		TableColumn Category = new TableColumn("Category");
		Category.setMinWidth(25);
		Category.setCellValueFactory(new PropertyValueFactory<product, String>("category"));
		
		TableColumn Color = new TableColumn("Color");
		Color.setMinWidth(25);
		Color.setCellValueFactory(new PropertyValueFactory<product, String>("color"));
		
		TableColumn Rating = new TableColumn("Rating");
		Rating.setMinWidth(25);
		Rating.setCellValueFactory(new PropertyValueFactory<product, String>("rating"));
		
		TableColumn Quantity = new TableColumn("Quantity");
		Quantity.setMinWidth(25);
		Quantity.setCellValueFactory(new PropertyValueFactory<product, String>("quantity"));
   	 
		table.getColumns().addAll(Id,Name,Price,Company,Category,Color,Rating,Quantity);
		
//		grid.setPadding(new Insets(10,10,10,10));
		table.setItems(temp);
//		((Group) scene.getRoot()).getChildren().addAll(grid);
		if(ans.getChildren().size()>1)
		{
			ans.getChildren().remove(ans.getChildren().size()-1);
		}
		ans.getChildren().add(table);
	}
	public static void DisplayPendingOrder(VBox ans,ObservableList<pending_order> temp)
	{
		TableView<pending_order> table = new TableView<pending_order>();
		table.setId("table-view");
//		table.setStyle("-fx-background-color: #ff9393;");
		table.setEditable(false);
		TableColumn Id = new TableColumn("Supplier ID");
		Id.setMinWidth(25);
		Id.setCellValueFactory(new PropertyValueFactory<product, String>("supplier_id"));
		
		TableColumn Name = new TableColumn("Customer ID");
		Name.setMinWidth(25);
		Name.setCellValueFactory(new PropertyValueFactory<product, String>("customer_id"));
		
		TableColumn Price = new TableColumn("Customer Name");
		Price.setMinWidth(25);
		Price.setCellValueFactory(new PropertyValueFactory<product, String>("customername"));
		
		TableColumn Company = new TableColumn("Product ID");
		Company.setMinWidth(25);
		Company.setCellValueFactory(new PropertyValueFactory<product, String>("product_id"));
		
		TableColumn Category = new TableColumn("Quantity");
		Category.setMinWidth(25);
		Category.setCellValueFactory(new PropertyValueFactory<product, String>("quantity"));
		
		TableColumn Color = new TableColumn("Date Ordered");
		Color.setMinWidth(25);
		Color.setCellValueFactory(new PropertyValueFactory<product, String>("date"));
		
		table.getColumns().addAll(Id,Name,Price,Company,Category,Color);
		
//		grid.setPadding(new Insets(10,10,10,10));
		table.setItems(temp);
//		((Group) scene.getRoot()).getChildren().addAll(grid);
		if(ans.getChildren().size()>1)
		{
			ans.getChildren().remove(ans.getChildren().size()-1);
		}
		ans.getChildren().add(table);
	}
	public static VBox SearchPanel()
	{
		try
		{
			VBox ans = new VBox();
	    	ans.setSpacing(20);
	    	ans.getStylesheets().add("button.css");
	    	ans.setPrefSize(1500, 1500);
	    	ans.setStyle("-fx-background-color: #b5b5b5;");
	    	
	    	GridPane grid = new GridPane();
	    	grid.setHgap(20);
	    	grid.setVgap(10);
	    	grid.setPadding(new Insets(30,10,30,20));
	    	grid.getStylesheets().add("button.css");
	        grid.setStyle("-fx-background-color: #3a3a3a;");
	        
	        Label company_label = new Label();
	        company_label.setStyle("-fx-text-fill: #ffffff;");
	        company_label.setText("Company");
	        company_label.setAlignment(Pos.BASELINE_LEFT);
	        
	        final TextField company_textfield = new TextField();
	        company_textfield.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        company_textfield.setPromptText("Search For Any Company");
	        company_textfield.setAlignment(Pos.BASELINE_LEFT);
	        company_textfield.setText("");
	        company_textfield.setMinWidth(200);
	        
	        Label category_label = new Label();
	        category_label.setStyle("-fx-text-fill: #ffffff;");
	        category_label.setText("Category");
	        
	        final TextField category_textfield = new TextField();
	        category_textfield.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        category_textfield.setPromptText("Search For Any Category");
	        category_textfield.setAlignment(Pos.BASELINE_LEFT);
	        category_textfield.setText("");
	        category_textfield.setMinWidth(200);
	        
	        Label price_label = new Label();
	        price_label.setStyle("-fx-text-fill: #ffffff;");
	        price_label.setText("Price Range");
	        
	        final TextField price_min_textfield = new TextField();
	        price_min_textfield.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        price_min_textfield.setPromptText("Minimum Range");
	        price_min_textfield.setAlignment(Pos.BASELINE_LEFT);
	        price_min_textfield.setText("");
	        price_min_textfield.setMinWidth(200);
	        
	        final TextField price_max_textfield = new TextField();
	        price_max_textfield.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        price_max_textfield.setPromptText("Maximum Range");
	        price_max_textfield.setAlignment(Pos.BASELINE_LEFT);
	        price_max_textfield.setText("");
	        price_max_textfield.setMinWidth(200);
	        
	        Label other_label = new Label();
	        other_label.setStyle("-fx-text-fill: #ffffff;");
	        other_label.setText("Details");
	        
	        final TextField other_textfield = new TextField();
	        other_textfield.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        other_textfield.setPromptText("Details");
	        other_textfield.setAlignment(Pos.BASELINE_LEFT);
	        other_textfield.setText("");
	        other_textfield.setMinWidth(200);
	        
	        Button submit = new Button();
	        submit.setText("Search");
			submit.setId("LoginButton");
			submit.setMinWidth(150);
			
			Button clear = new Button();
	        clear.setText("Clear");
			clear.setId("ResetButton");
			clear.setMinWidth(150);
			
			grid.setMargin(company_label, new Insets(0,0,0,0));
	        grid.setMargin(company_textfield, new Insets(0,0,0,0));
	        grid.setMargin(category_label, new Insets(0,0,0,0));
	        grid.setMargin(category_textfield, new Insets(0,0,0,0));
	        grid.setMargin(price_label, new Insets(0,0,0,0));
	        grid.setMargin(price_min_textfield, new Insets(0,0,0,0));
	        grid.setMargin(price_max_textfield, new Insets(0,0,0,0));
	        grid.setMargin(other_label, new Insets(0,0,0,0));
	        grid.setMargin(other_textfield, new Insets(0,0,0,0));
	        grid.setMargin(submit, new Insets(0,0,0,0));
	        grid.setMargin(clear, new Insets(0,0,0,0));
	        
	        grid.setHalignment(company_label, HPos.CENTER);
	        grid.setHalignment(company_textfield, HPos.CENTER);
	        grid.setHalignment(category_label, HPos.CENTER);
	        grid.setHalignment(category_textfield, HPos.CENTER);
	        grid.setHalignment(price_label, HPos.CENTER);
	        grid.setHalignment(price_min_textfield, HPos.CENTER);
	        grid.setHalignment(price_max_textfield, HPos.CENTER);
	        grid.setHalignment(other_textfield, HPos.CENTER);
	        grid.setHalignment(other_label, HPos.CENTER);
	        
	        
	        grid.add(company_label, 0, 0);
	        grid.add(company_textfield, 1, 0);
	        grid.add(category_label, 0, 1);
	        grid.add(category_textfield, 1, 1);
	        grid.add(price_label, 0, 2);
	        grid.add(price_min_textfield, 1, 2);
	        grid.add(price_max_textfield, 2, 2);
	        grid.add(other_label, 0, 3);
	        grid.add(other_textfield, 1, 3);
	        grid.add(submit, 0, 4);
	        grid.add(clear, 1, 4);
	        ans.getChildren().add(grid);
			
			submit.setOnAction(e -> 
			{	double min;
				double max;
				String company = "";
				String category = "";
				if(price_min_textfield.getText().equals(""))
				{
					min = Double.MIN_VALUE;
				}
				else
				{
					min = Double.parseDouble(price_min_textfield.getText());
				}
				if(price_max_textfield.getText().equals(""))
				{
					max = Double.MAX_VALUE;
				}
				else
				{
					max = Double.parseDouble(price_max_textfield.getText());
				}
				if(company_textfield.getText().equals(""))
				{
					company = "";
					company = company + "is not NULL";
				}
				else
				{
					company = "";
					company = company + "= \"" + company_textfield.getText() + "\"";
				}
				if(category_textfield.getText().equals(""))
				{
					category = "";
					category = category + "is not NULL";
				}
				else
				{
					category = "";
					category = category + "= \"" + category_textfield.getText() + "\"";
				}
				try
				{   
					Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
					Statement stmt=con.createStatement();
					String query = "select A.*,B.quantity from Product_Type A, Inventory B where A.product_id = B.product_id and A.brand_name " + company + " and A.type_1 " + category + " and "+ min + " <=" + " A.price and A.price <= " + max + ";";
//					select A.*,B.quantity from Product_Type A,Inventory B where A.product_id = B.product_id;
					ResultSet rs=stmt.executeQuery(query);
					ObservableList<product> list = FXCollections.observableArrayList();
					
					while(rs.next())
					{
//						list.add(new product(rs.getLong(1),"d",rs.getDouble(3),"a","b","c",rs.getDouble(7)));
						list.add(new product(rs.getLong(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7),rs.getLong(8)));
//						System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getString(4) + rs.getString(5) + rs.getString(6) + rs.getString(7));
					}
					con.close();
//					for(int i=0;i<list.size();i++)
//					{
//						System.out.println(list.get(i).toString());
//					}
					DisplayProduct(ans,list);
				}
				catch(Exception error)
				{ 
					System.out.println(error);
				}
			});
			
			clear.setOnAction(e ->
	  		{
	  			company_textfield.setText(null);
	  			category_textfield.setText(null);
	  			price_min_textfield.setText(null);
	  			price_max_textfield.setText(null);
			});
	    	return ans;
	    	
		}
		catch(Exception e) 
		{
			e.printStackTrace();	
		}
		return null;
	}
	
	public static VBox QueryPanel()
	{
		try
		{
			VBox ans = new VBox();
	    	ans.setSpacing(30);
	    	ans.getStylesheets().add("button.css");
	    	ans.setPrefSize(1500, 1500);
	    	ans.setPadding(new Insets(50,0,50,0));;
	    	ans.setStyle("-fx-background-color: #3a3a3a;");
	    	
	    	GridPane grid = new GridPane();
	    	grid.setHgap(50);
	    	grid.setVgap(30);
	    	grid.setPadding(new Insets(10,10,10,50));
	    	grid.getStylesheets().add("button.css");
	        grid.setStyle("-fx-background-color: #3a3a3a;");
	        ans.getChildren().add(grid);
	        
	        Label commands_label = new Label();
	        commands_label.setStyle("-fx-text-fill: #ffffff;");
	        commands_label.setText("Enter SQL Commands");
	        commands_label.setAlignment(Pos.BASELINE_LEFT);
	        
	        final TextField commands_textfield = new TextField();
	        commands_textfield.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        commands_textfield.setPromptText("SQL Commands");
	        commands_textfield.setAlignment(Pos.BASELINE_LEFT);
	        commands_textfield.setText("");
	        commands_textfield.setMinWidth(800);
	        commands_textfield.setMinHeight(100);
	        
	        Label result_textfield = new Label();
	        result_textfield.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        result_textfield.setText("");
	        result_textfield.setAlignment(Pos.BASELINE_LEFT);
	        result_textfield.setMinWidth(800);
	        result_textfield.setMinHeight(300);

	        Button query1 = new Button();
	        query1.setText("List all orders which were delivered on 20th april 2020");
	        query1.setId("LoginButton");
	        query1.setMinWidth(800);

			Button query2 = new Button();
			query2.setText("List all the free suppliers' info i.e. having no pending orders that are ready to deliver the goods?");
			query2.setId("LoginButton");
			query2.setMinWidth(800);
			
			Button query3 = new Button();
			query3.setText("Print products that are out of stock");
			query3.setId("LoginButton");
			query3.setMinWidth(800);
			
			Button query4 = new Button();
			query4.setText("List suppliers that supply samsung mobiles");
			query4.setId("LoginButton");
			query4.setMinWidth(800);
	        
	        Button query5 = new Button();
	        query5.setText("");
	        query5.setId("LoginButton");
	        query5.setMinWidth(800);
			
			Button clear = new Button();
	        clear.setText("Cancel");
			clear.setId("ResetButton");
			clear.setMinWidth(150);
			
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setContent(result_textfield);
			scrollPane.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
			scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
			scrollPane.setMinHeight(300);
			scrollPane.setMaxHeight(300);
			
	        grid.setMargin(commands_label, new Insets(0,0,0,0));
	        grid.setMargin(commands_textfield, new Insets(0,0,0,0));
	        grid.setMargin(query1, new Insets(0,0,0,0));
	        grid.setMargin(query2, new Insets(0,0,0,0));
	        grid.setMargin(query3, new Insets(0,0,0,0));
	        grid.setMargin(query4, new Insets(0,0,0,0));
	        grid.setMargin(query5, new Insets(0,0,0,0));
	        grid.setMargin(clear, new Insets(0,0,0,0));
	        
	        grid.setHalignment(commands_label, HPos.CENTER);
	        grid.setHalignment(commands_textfield, HPos.CENTER);
	        
//	        grid.add(commands_label, 0, 0);
//	        grid.add(commands_textfield, 0, 1);
	        grid.add(query1, 0, 0);
	        grid.add(query2, 0, 1);
	        grid.add(query3, 0, 2);
	        grid.add(query4, 0, 3);
//	        grid.add(query5, 0, 4);
//	        grid.add(clear, 0, 5);
	        grid.add(scrollPane, 0, 5);
	        
	        Alert error = new Alert(AlertType.ERROR);
	        error.setTitle("Error Dialog");
	        error.setHeaderText(null);
	        error.setContentText("Either Old or New Password doesn't match !");
	        error.initStyle(StageStyle.UNDECORATED);
	        
	        Alert empty = new Alert(AlertType.ERROR);
	        empty.setTitle("Error Dialog");
	        empty.setHeaderText(null);
	        empty.setContentText("Empty or Invalid Query");
	        empty.initStyle(StageStyle.UNDECORATED);
	        
	        Alert success = new Alert(AlertType.INFORMATION);
	        success.setTitle("Error Dialog");
	        success.setHeaderText(null);
	        success.setContentText("Password Changed Successfully !");
	        success.initStyle(StageStyle.UNDECORATED);
	        
	        Alert updatequerysucess = new Alert(AlertType.INFORMATION);
	        updatequerysucess.setTitle("Error Dialog");
	        updatequerysucess.setHeaderText(null);
	        updatequerysucess.setContentText("SQL Query Successful");
	        updatequerysucess.initStyle(StageStyle.UNDECORATED);
	        
	        String str = "";
	        
	        query1.setOnAction(e -> 
			{
				try
	  			{
					result_textfield.setText("");
	  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
					Statement stmt=con.createStatement();
					String query = "select * from order_history where delivery_date = '2020-04-20';";
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					int n = rs.getMetaData().getColumnCount();
					for(int i =0;i<n;i++)
					{
						result_textfield.setText(result_textfield.getText().concat(rsmd.getColumnName(i+1)+"\t"));
					}
					result_textfield.setText(result_textfield.getText().concat("\n"));
					while(rs.next())
					{
						for(int i=0;i<n;i++)
						{
							result_textfield.setText(result_textfield.getText().concat(rs.getString(i+1)+"\t"));
						}
						result_textfield.setText(result_textfield.getText().concat("\n"));
					}
					con.close();
	  			}
	  			catch(Exception err)
	  			{
	  				empty.showAndWait();
	  			}
			});
	        query2.setOnAction(e -> 
			{
				try
	  			{
					result_textfield.setText("");
	  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
					Statement stmt=con.createStatement();
//					String query = "create view supplier_info as select supplier_id, username, rating from account_supplier;";
//					stmt.executeUpdate(query);
					String query = "select * from supplier_info where supplier_id not in (select supplier_id from requests);";
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					int n = rs.getMetaData().getColumnCount();
					for(int i =0;i<n;i++)
					{
						result_textfield.setText(result_textfield.getText().concat(rsmd.getColumnName(i+1)+"\t"));
					}
					result_textfield.setText(result_textfield.getText().concat("\n"));
					while(rs.next())
					{
						for(int i=0;i<n;i++)
						{
							result_textfield.setText(result_textfield.getText().concat(rs.getString(i+1)+"\t"));
						}
						result_textfield.setText(result_textfield.getText().concat("\n"));
					}
					con.close();
	  			}
	  			catch(Exception err)
	  			{
	  				empty.showAndWait();
	  			}
			});
	        query3.setOnAction(e -> 
			{
				try
	  			{
					result_textfield.setText("");
	  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
					Statement stmt=con.createStatement();
					String query = "select * from product_type where product_id in (select product_id from inventory where quantity=0);";
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					int n = rs.getMetaData().getColumnCount();
					for(int i =0;i<n;i++)
					{
						result_textfield.setText(result_textfield.getText().concat(rsmd.getColumnName(i+1)+"\t"));
					}
					result_textfield.setText(result_textfield.getText().concat("\n"));
					while(rs.next())
					{
						for(int i=0;i<n;i++)
						{
							result_textfield.setText(result_textfield.getText().concat(rs.getString(i+1)+"\t"));
						}
						result_textfield.setText(result_textfield.getText().concat("\n"));
					}
					con.close();
	  			}
	  			catch(Exception err)
	  			{
	  				
	  				empty.showAndWait();
	  			}
			});
	        query4.setOnAction(e -> 
			{
				try
	  			{
					result_textfield.setText("");
	  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
					Statement stmt=con.createStatement();
					String query = "select * from supplier_info where supplier_id  in (select supplier_id from product_type where brand_name = 'samsung');";
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					int n = rs.getMetaData().getColumnCount();
					for(int i =0;i<n;i++)
					{
						result_textfield.setText(result_textfield.getText().concat(rsmd.getColumnName(i+1)+"\t"));
					}
					result_textfield.setText(result_textfield.getText().concat("\n"));
					while(rs.next())
					{
						for(int i=0;i<n;i++)
						{
							result_textfield.setText(result_textfield.getText().concat(rs.getString(i+1)+"\t"));
						}
						result_textfield.setText(result_textfield.getText().concat("\n"));
					}
					con.close();
	  			}
	  			catch(Exception err)
	  			{
	  				err.printStackTrace();
//	  				empty.showAndWait();
	  			}
			});
	        query5.setOnAction(e -> 
			{
				try
	  			{
					result_textfield.setText("");
	  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
					Statement stmt=con.createStatement();
					String query = "select * from product_type natural join mobiles where product_type.brand_name = 'Samsung';";
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					int n = rs.getMetaData().getColumnCount();
					for(int i =0;i<n;i++)
					{
						result_textfield.setText(result_textfield.getText().concat(rsmd.getColumnName(i+1)+"\t"));
					}
					result_textfield.setText(result_textfield.getText().concat("\n"));
					while(rs.next())
					{
						for(int i=0;i<n;i++)
						{
							result_textfield.setText(result_textfield.getText().concat(rs.getString(i+1)+"\t"));
						}
						result_textfield.setText(result_textfield.getText().concat("\n"));
					}
					con.close();
	  			}
	  			catch(Exception err)
	  			{
	  				empty.showAndWait();
	  			}
			});
			clear.setOnAction(e ->
	  		{
	  			commands_textfield.setText(null);
	  		});
	        
	        return ans;
		}
		catch(Exception e) 
		{
			e.printStackTrace();	
		}
		return null;
	}
	public static VBox HomePanel()
	{
		try
		{
			int sid = Integer.parseInt(Admin_Home_Page_Panels.id);
			String info = "";
			try
  			{
  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
				Statement stmt=con.createStatement();
				String query = "SELECT * FROM contact WHERE supplier_id = " + sid + ";";
				ResultSet rs = stmt.executeQuery(query);
				rs.next();
				info = "Phone : "+rs.getString(1)+"\nEmail : "+rs.getString(2)+"\nAddress : "+rs.getString(3)+"\nCity : "+rs.getString(5)+"\nState : "+rs.getString(6)+"\nCountry : "+rs.getString(7)+"\nPincode : "+rs.getString(4);
				con.close();
  			}
  			catch(Exception err)
  			{
  				err.printStackTrace();
  			}
			
			String temp_user = Admin_Home_Page_Panels.user;
			String temp_pass = Admin_Home_Page_Panels.pass;
			
			VBox ans = new VBox();
	    	ans.setSpacing(20);
	    	ans.getStylesheets().add("button.css");
	    	ans.setPrefSize(1500, 1500);
	    	ans.setPadding(new Insets(100,0,0,250));
	    	ans.setStyle("-fx-background-color: #b5b5b5;");
	    	
	    	VBox box = new VBox();
	    	box.setPadding(new Insets(0,0,0,0));
	    	Separator line = new Separator();
	    	line.setOpacity(0);
	        line.setOrientation(Orientation.HORIZONTAL);
	        Separator line1 = new Separator();
	        line1.setOrientation(Orientation.HORIZONTAL);
	        line1.setOpacity(0);
	        Separator line2 = new Separator();
	        line2.setOrientation(Orientation.HORIZONTAL);
	        line2.setOpacity(0);
	        Separator line3 = new Separator();
	        line3.setOrientation(Orientation.HORIZONTAL);
	        line3.setOpacity(0);
	    	
	    	Label s = new Label();
	        s.setText("WELCOME");
	        s.setPadding(new Insets(10,0,0,10));
	        s.setPrefSize(200, 50);
	        s.setStyle("-fx-text-fill: #3a3a3a; -fx-font-size: 30; -fx-background-color: #b5b5b5;");
	        
	        Label information = new Label();
	        information.setText("Personal Information\n"+info);
	        information.setPadding(new Insets(10,0,0,10));
	        information.setPrefSize(500, 500);
	        information.setStyle("-fx-text-fill: #3a3a3a; -fx-font-size: 30; -fx-background-color: #b5b5b5;");
	        
	        Button username = new Button();
//	        username.setText("Username");
	        username.setAlignment(Pos.CENTER);
	        username.setId("LoginButton");
	        username.setMinWidth(350);
	        username.textProperty().bind(
	                Bindings.when(username.hoverProperty())
	                        .then(temp_user)
	                        .otherwise("Username"));
	        username.hoverProperty().addListener((ov, oldValue, newValue) -> {
	            if (newValue) {
	            	username.setText(temp_user);
	            } else {
	            	username.setText("Username");
	            }
	        });
	        
	        Button password = new Button();
//	        password.setText("Password");
	        password.setAlignment(Pos.CENTER);
	        password.setId("ResetButton");
	        password.setMinWidth(350);
	        password.textProperty().bind(
	                Bindings.when(password.hoverProperty())
	                        .then(temp_pass)
	                        .otherwise("Password"));
	        password.hoverProperty().addListener((ov, oldValue, newValue) -> {
	            if (newValue) {
	            	password.setText(temp_pass);
	            } else {
	            	password.setText("Password");
	            }
	        });
	        
	        ans.getChildren().addAll(s, line);
	        ans.getChildren().addAll(username, line1);
	        ans.getChildren().addAll(password, line2);
	        ans.getChildren().addAll(information, line3);
			
	    	return ans;
		}
		catch(Exception e) 
		{
			e.printStackTrace();	
		}
		return null;
	}
	public static VBox PasswordUpdatePanel()
	{
		try
		{
			VBox ans = new VBox();
	    	ans.setSpacing(20);
	    	ans.getStylesheets().add("button.css");
	    	ans.setPrefSize(1500, 1500);
	    	ans.setPadding(new Insets(200,0,200,0));;
	    	ans.setStyle("-fx-background-color: #3a3a3a;");
	    	
	    	GridPane grid = new GridPane();
	    	grid.setHgap(50);
	    	grid.setVgap(30);
	    	grid.setPadding(new Insets(30,10,30,220));
	    	grid.getStylesheets().add("button.css");
	        grid.setStyle("-fx-background-color: #3a3a3a;");
	        ans.getChildren().add(grid);
	        
	        Label oldpassword_label = new Label();
	        oldpassword_label.setStyle("-fx-text-fill: #ffffff;");
	        oldpassword_label.setText("Enter your Current Password ");
	        oldpassword_label.setAlignment(Pos.BASELINE_CENTER);
	        
	        final PasswordField oldpassword_textfield = new PasswordField();
	        oldpassword_textfield.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        oldpassword_textfield.setPromptText("Current Password");
	        oldpassword_textfield.setAlignment(Pos.BASELINE_LEFT);
	        oldpassword_textfield.setText("");
	        oldpassword_textfield.setMinWidth(200);
	        
	        Label newpassword_label = new Label();
	        newpassword_label.setStyle("-fx-text-fill: #ffffff;");
	        newpassword_label.setText("Enter your New Password ");
	        newpassword_label.setAlignment(Pos.BASELINE_CENTER);
	        
	        final PasswordField newpassword_textfield = new PasswordField();
	        newpassword_textfield.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        newpassword_textfield.setPromptText("New Password");
	        newpassword_textfield.setAlignment(Pos.BASELINE_LEFT);
	        newpassword_textfield.setText("");
	        newpassword_textfield.setMinWidth(200);
	        
	        Label renewpassword_label = new Label();
	        renewpassword_label.setStyle("-fx-text-fill: #ffffff;");
	        renewpassword_label.setText("Enter your New Password ");
	        renewpassword_label.setAlignment(Pos.BASELINE_CENTER);
	        
	        final PasswordField renewpassword_textfield = new PasswordField();
	        renewpassword_textfield.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        renewpassword_textfield.setPromptText("Retype New Password");
	        renewpassword_textfield.setAlignment(Pos.BASELINE_LEFT);
	        renewpassword_textfield.setText("");
	        renewpassword_textfield.setMinWidth(200);
	        
	        Button submit = new Button();
	        submit.setText("Submit");
			submit.setId("LoginButton");
			submit.setMinWidth(150);
			
			Button clear = new Button();
	        clear.setText("Cancel");
			clear.setId("ResetButton");
			clear.setMinWidth(150);
			
	        grid.setMargin(oldpassword_label, new Insets(0,0,0,0));
	        grid.setMargin(oldpassword_textfield, new Insets(0,0,0,0));
	        grid.setMargin(newpassword_label, new Insets(0,0,0,0));
	        grid.setMargin(newpassword_textfield, new Insets(0,0,0,0));
	        grid.setMargin(renewpassword_label, new Insets(0,0,0,0));
	        grid.setMargin(renewpassword_textfield, new Insets(0,0,0,0));
	        grid.setMargin(submit, new Insets(0,0,0,0));
	        grid.setMargin(clear, new Insets(0,0,0,0));
	        
	        grid.setHalignment(oldpassword_label, HPos.CENTER);
	        grid.setHalignment(oldpassword_textfield, HPos.CENTER);
	        grid.setHalignment(newpassword_label, HPos.CENTER);
	        grid.setHalignment(newpassword_textfield, HPos.CENTER);
	        grid.setHalignment(renewpassword_label, HPos.CENTER);
	        grid.setHalignment(renewpassword_textfield, HPos.CENTER);
	        
	        grid.add(oldpassword_label, 0, 0);
	        grid.add(oldpassword_textfield, 1, 0);
	        grid.add(newpassword_label, 0, 1);
	        grid.add(newpassword_textfield, 1, 1);
	        grid.add(renewpassword_label, 0, 2);
	        grid.add(renewpassword_textfield, 1, 2);
	        grid.add(submit, 0, 3);
	        grid.add(clear, 1, 3);
	        
	        Alert error = new Alert(AlertType.ERROR);
	        error.setTitle("Error Dialog");
	        error.setHeaderText(null);
	        error.setContentText("Either Old or New Password doesn't match !");
	        error.initStyle(StageStyle.UNDECORATED);
	        
	        Alert success = new Alert(AlertType.INFORMATION);
	        success.setTitle("Error Dialog");
	        success.setHeaderText(null);
	        success.setContentText("Password Changed Successfully !");
	        success.initStyle(StageStyle.UNDECORATED);
	        
	        submit.setOnAction(e -> 
			{
				if(oldpassword_textfield.getText().equals(Admin_Home_Page_Panels.pass) && newpassword_textfield.getText().equals(renewpassword_textfield.getText()))
				{
					try
					{
						Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
						Statement stmt=con.createStatement();
						String n = newpassword_textfield.getText();
						n = "123"+n+"123";
						n = encryption(n);
						String query = "update Account_Supplier set password = \""+n+"\" where supplier_id = \""+Admin_Home_Page_Panels.id+"\"";
						Admin_Home_Page_Panels.pass = newpassword_textfield.getText();
						stmt.executeUpdate(query);
						con.close();
						success.showAndWait();
					}
					catch(Exception err)
					{
						err.printStackTrace();
						oldpassword_textfield.setText(null);
		        		newpassword_textfield.setText(null);
					}
				}
				else
				{
					error.showAndWait();
				}
			});
			clear.setOnAction(e ->
	  		{
	  			
	  			oldpassword_textfield.setText(null);
        		newpassword_textfield.setText(null);
        		renewpassword_textfield.setText(null);

	  		});
	        
	        return ans;
		}
		catch(Exception e) 
		{
			e.printStackTrace();	
		}
		return null;
	}
	public static VBox InventoryPanel()
	{
		try
		{
			VBox ans = new VBox();
	    	ans.setSpacing(20);
	    	ans.getStylesheets().add("button.css");
	    	ans.setPrefSize(1500, 1500);
	    	ans.setStyle("-fx-background-color: #b5b5b5;");
	    	
	    	GridPane grid = new GridPane();
	    	grid.setHgap(50);
	    	grid.setVgap(60);
	    	grid.setPadding(new Insets(30,10,30,30));
	    	grid.getStylesheets().add("button.css");
	        grid.setStyle("-fx-background-color: #3a3a3a;");
	        ans.getChildren().add(grid);
	        
	        GridPane last_grid = new GridPane();
	        last_grid.setHgap(50);
	        last_grid.setVgap(60);
	        last_grid.setPadding(new Insets(30,10,30,30));
	        last_grid.getStylesheets().add("button.css");
	        last_grid.setStyle("-fx-background-color: #3a3a3a;");
	        
	        Label cost_label = new Label();
	        cost_label.setStyle("-fx-text-fill: #ffffff;");
	        cost_label.setText("Order Number");
	        cost_label.setAlignment(Pos.BASELINE_LEFT);
	        cost_label.setMinWidth(250);
	        
	        final TextField confirmed_order_textfield = new TextField();
	        confirmed_order_textfield.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        confirmed_order_textfield.setPromptText("Order That got delivered today !");
	        confirmed_order_textfield.setAlignment(Pos.BASELINE_LEFT);
	        confirmed_order_textfield.setText("");
	        confirmed_order_textfield.setMinWidth(200);
	        
	        Button confirm = new Button();
	        confirm.setText("Submit");
	        confirm.setId("GeneralButtonGreen");
	        confirm.setMinWidth(250);
			
			Button show = new Button();
			show.setText("Show Inventory");
			show.setId("LoginButton");
			show.setMinWidth(250);
			
			Button empty = new Button();
			empty.setText("Empty your Cart");
			empty.setId("ResetButton");
			empty.setMinWidth(250);
			
			grid.setMargin(cost_label, new Insets(0,0,0,0));
	        grid.setMargin(confirmed_order_textfield, new Insets(0,0,0,0));

	        grid.setHalignment(cost_label, HPos.LEFT);
	        grid.setHalignment(confirmed_order_textfield, HPos.LEFT);
			
	        
//			grid.add(cost_label, 1, 0);
//			grid.add(confirmed_order_textfield, 2, 0);
			grid.add(show, 0, 0);
			
	        
	        Alert error = new Alert(AlertType.ERROR);
	        error.setTitle("Error Dialog");
	        error.setHeaderText(null);
	        error.setContentText("Incorrect Entry !");
	        error.initStyle(StageStyle.UNDECORATED);
	        
	        Alert success = new Alert(AlertType.INFORMATION);
	        success.setTitle("Error Dialog");
	        success.setHeaderText(null);
	        success.setContentText("Added Successfully !\nThank you for shopping with us");
	        success.initStyle(StageStyle.UNDECORATED);
	        
	        Alert removed_success = new Alert(AlertType.INFORMATION);
	        removed_success.setTitle("Error Dialog");
	        removed_success.setHeaderText(null);
	        removed_success.setContentText("Removed Successfully !");
	        removed_success.initStyle(StageStyle.UNDECORATED);
	        
	        Alert confirm_pop_up = new Alert(AlertType.CONFIRMATION);
	        confirm_pop_up.setTitle("Error Dialog");
	        confirm_pop_up.setHeaderText(null);
	        confirm_pop_up.setContentText("You sure you want to proceed ?");
	        confirm_pop_up.initStyle(StageStyle.UNDECORATED);
	        
	        show.setOnAction(e ->
	  		{	
	  			ObservableList<inventory> list = FXCollections.observableArrayList();
				ObservableList<inventory> final_list = FXCollections.observableArrayList();
	  			try
	  			{
	  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
					Statement stmt=con.createStatement();
					String query = "select I.product_id,P.product_name,P.price,I.quantity,I.supplier_id,S.username from inventory as I,product_type as P,account_supplier as S where I.product_id = P.product_id and I.supplier_id = S.supplier_id;";
					ResultSet rs = stmt.executeQuery(query);
					int i = 0;
					while(rs.next())
					{
						long pid = Long.parseLong(rs.getString(1));
						String pname = rs.getString(2);
						double price = Double.parseDouble(rs.getString(3));
						int qty = Integer.parseInt(rs.getString(4));
						long sid = Long.parseLong(rs.getString(5));
						String sname = rs.getString(6);
						final_list.add(new inventory(pid,pname,price,qty,sid,sname));
						i++;
					}
					con.close();
	  			}
	  			catch(Exception err)
	  			{
	  				err.printStackTrace();
	  			}
	  			DisplayInventory(ans, final_list);
	  		});
	        
	        return ans;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static VBox PendingOrderPanel()
	{
		try
		{
			VBox ans = new VBox();
	    	ans.setSpacing(20);
	    	ans.getStylesheets().add("button.css");
	    	ans.setPrefSize(1500, 1500);
	    	ans.setStyle("-fx-background-color: #b5b5b5;");
	    	
	    	GridPane grid = new GridPane();
	    	grid.setHgap(50);
	    	grid.setVgap(30);
	    	grid.setPadding(new Insets(30,10,30,30));
	    	grid.getStylesheets().add("button.css");
	        grid.setStyle("-fx-background-color: #3a3a3a;");
	        ans.getChildren().add(grid);
	        
	        GridPane last_grid = new GridPane();
	        last_grid.setHgap(50);
	        last_grid.setVgap(30);
	        last_grid.setPadding(new Insets(30,10,30,30));
	        last_grid.getStylesheets().add("button.css");
	        last_grid.setStyle("-fx-background-color: #3a3a3a;");
	        
	        Label cid_label = new Label();
	        cid_label.setStyle("-fx-text-fill: #ffffff;");
	        cid_label.setText("Customer ID");
	        cid_label.setAlignment(Pos.BASELINE_CENTER);
	        
	        final TextField cid_textfield = new TextField();
	        cid_textfield.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        cid_textfield.setPromptText("Enter Product ID");
	        cid_textfield.setAlignment(Pos.BASELINE_LEFT);
	        cid_textfield.setText("");
	        cid_textfield.setMinWidth(50);
	        
	        Label dateoforder = new Label();
	        dateoforder.setStyle("-fx-text-fill: #ffffff;");
	        dateoforder.setText("Date of Order Placed (yyyy-mm-dd)");
	        dateoforder.setAlignment(Pos.BASELINE_CENTER);
	        
	        Button show = new Button();
	        show.setText("Show Pending Deliveries");
	        show.setId("LoginButton");
	        show.setMinWidth(150);
			
			Button submit = new Button();
			submit.setText("Submit");
			submit.setId("LoginButton");
			submit.setMinWidth(150);
			
			Button clear = new Button();
			clear.setText("Clear");
			clear.setId("ResetButton");
			clear.setMinWidth(150);
			
			String year[] = {"0000","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035"};
			ComboBox yearbox = new ComboBox(FXCollections.observableArrayList(year));
			yearbox.setId("box");
			yearbox.setValue("0000");
			yearbox.setPromptText("Year");
			yearbox.setMinWidth(150);

			String month[] = {"0","01","02","03","04","05","06","07","08","09","10","11","12"};
			ComboBox monthbox = new ComboBox(FXCollections.observableArrayList(month));
			monthbox.setId("box");
			monthbox.setValue("0");
			monthbox.setPromptText("Date");
			monthbox.setMinWidth(150);
			
			String date[] = {"0","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
			ComboBox datebox = new ComboBox(FXCollections.observableArrayList(date));
			datebox.setId("box");
			datebox.setValue("0");
			datebox.setPromptText("Date");
			datebox.setMinWidth(150);
			
			grid.setMargin(cid_label, new Insets(0,0,0,0));
	        grid.setMargin(cid_textfield, new Insets(0,0,0,0));
	        grid.setMargin(dateoforder, new Insets(0,0,0,0));

	        grid.setHalignment(cid_label, HPos.LEFT);
	        grid.setHalignment(cid_textfield, HPos.LEFT);
	        grid.setHalignment(dateoforder, HPos.LEFT);
	        
	        
	        LocalDate value = null;
	        DatePicker calendar = new DatePicker();
	        calendar.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        calendar.setId("box");
			calendar.setValue(value);
			calendar.setPromptText("Date of Order");
			calendar.setMinWidth(150);
	        
	        grid.add(cid_label, 0, 0);
	        grid.add(cid_textfield, 1, 0);
	        grid.add(dateoforder, 0, 1);
	        grid.add(calendar, 1, 1);
	        grid.add(show, 0, 3);
	        grid.add(submit, 2, 3);
	        grid.add(clear, 3, 3);
	        
	        
	        Alert error = new Alert(AlertType.ERROR);
	        error.setTitle("Error Dialog");
	        error.setHeaderText(null);
	        error.setContentText("Incorrect Entry !");
	        error.initStyle(StageStyle.UNDECORATED);
	        
	        Alert success = new Alert(AlertType.INFORMATION);
	        success.setTitle("Error Dialog");
	        success.setHeaderText(null);
	        success.setContentText("Added Successfully !");
	        success.initStyle(StageStyle.UNDECORATED);
	        
	        Alert removed_success = new Alert(AlertType.INFORMATION);
	        removed_success.setTitle("Error Dialog");
	        removed_success.setHeaderText(null);
	        removed_success.setContentText("Removed Successfully !");
	        removed_success.initStyle(StageStyle.UNDECORATED);
	        
	        ArrayList<Long> customer_id = new ArrayList<Long>();
			ArrayList<String> customer_name = new ArrayList<String>();
	        
			try
  			{
				int sid = Integer.parseInt(Admin_Home_Page_Panels.id);
  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
				Statement stmt=con.createStatement();
				String query = "SELECT customer_id FROM requests where supplier_id = "+ sid + ";";
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next())
				{
					customer_id.add(Long.parseLong(rs.getString(1)));
				}
				con.close();
  			}
  			catch(Exception err)
  			{
  				err.printStackTrace();
  			}
	        try
  			{
				int sid = Integer.parseInt(Admin_Home_Page_Panels.id);
  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
				Statement stmt=con.createStatement();
				for(long i : customer_id)
				{
					String query = "SELECT username FROM account_user where customer_id = "+ i + ";";
					ResultSet rs = stmt.executeQuery(query);
					while(rs.next())
					{
						customer_name.add(rs.getString(1));
					}
				}
				con.close();
  			}
  			catch(Exception err)
  			{
  				err.printStackTrace();
  			}
	        
	        clear.setOnAction(e ->
    		{
    			calendar.setValue(null);
    			cid_textfield.setText("");
    		});
	        submit.setOnAction(e ->
	  		{
	  			System.out.println();
	  			int sid = Integer.parseInt(Admin_Home_Page_Panels.id);
	  			if(cid_textfield.getText().equals("") || calendar.getValue()==null)
	  			{
	  				error.showAndWait();
	  			}
	  			else
	  			{
	  				LocalDate finaldate = calendar.getValue();
	  				
	  				try
		  			{
		  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
						Statement stmt=con.createStatement();
						int cid = Integer.parseInt(cid_textfield.getText());
						String query = "delete from requests where customer_id = " + cid + " and supplier_id = "+ sid + " and date_order = \'"+finaldate+"\';";
						stmt.executeUpdate(query);
						con.close();
		  			}
		  			catch(Exception err)
		  			{
		  				err.printStackTrace();
		  			}
	  				try
		  			{
		  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
						Statement stmt=con.createStatement();
						int cid = Integer.parseInt(cid_textfield.getText());
						String query = "update order_history set return_status = \'"+"Delivered"+"\' where customer_id = " + cid + " and supplier_id = "+ sid + " and date_order = \'"+finaldate+"\';";
						System.out.println(query);
						stmt.executeUpdate(query);
						con.close();
		  			}
		  			catch(Exception err)
		  			{
		  				err.printStackTrace();
		  			}
	  				removed_success.showAndWait();
	  				calendar.setValue(null);
	    			cid_textfield.setText("");
	  			}
	  		});
	        show.setOnAction(e ->
	  		{	
	  			ObservableList<pending_order> list = FXCollections.observableArrayList();
				ObservableList<pending_order> final_list = FXCollections.observableArrayList();
	  			try
	  			{
	  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
					Statement stmt=con.createStatement();
					int sid = Integer.parseInt(Admin_Home_Page_Panels.id);
					String query = "select * from Requests where supplier_id = " + sid + ";";
					ResultSet rs = stmt.executeQuery(query);
					int i = 0;
					while(rs.next())
					{
//						System.out.println(sid+" "+Long.parseLong(rs.getString(2))+" "+Long.parseLong(rs.getString(3))+" "+Integer.parseInt(rs.getString(4))+" "+rs.getString(5)+" "+customer_name.get(i));
						final_list.add(new pending_order(sid, Long.parseLong(rs.getString(2)), Long.parseLong(rs.getString(4)), Integer.parseInt(rs.getString(5)), rs.getString(6),customer_name.get(i)));
						i++;
					}
					con.close();
	  			}
	  			catch(Exception err)
	  			{
	  				err.printStackTrace();
	  			}
	  			DisplayPendingOrder(ans, final_list);
	  		});
	        
	        return ans;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static VBox CheckOutPanel()
	{
		try
		{
			Double sum = 0.0;
			ArrayList<Integer> product_id = new ArrayList<Integer>();
			ArrayList<Integer> quantity = new ArrayList<Integer>();
			ArrayList<String> product_name = new ArrayList<String>();
			ArrayList<Double> price = new ArrayList<Double>();
			
			try
  			{
				int cid = Integer.parseInt(Admin_Home_Page_Panels.id);
  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
				Statement stmt=con.createStatement();
				String query = "SELECT * FROM Checkout WHERE customer_id = " + cid + ";";
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next())
				{
					product_id.add(Integer.parseInt(rs.getString(4)));
					quantity.add(Integer.parseInt(rs.getString(3)));
				}
				con.close();
  			}
  			catch(Exception err)
  			{
  				err.printStackTrace();
  			}
			try
  			{
				int cid = Integer.parseInt(Admin_Home_Page_Panels.id);
  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
				Statement stmt=con.createStatement();
				for(int i : product_id)
				{
					String query = "SELECT product_name,price FROM product_type where product_id = "+ i + ";";
					ResultSet rs = stmt.executeQuery(query);
					while(rs.next())
					{
						product_name.add(rs.getString(1));
						price.add(Double.parseDouble(rs.getString(2)));
					}
				}
				con.close();
  			}
  			catch(Exception err)
  			{
  				err.printStackTrace();
  			}
			
			for(int i=0;i<price.size();i++)
			{
				sum += ((price.get(i))*(quantity.get(i)));
			}
			
			VBox ans = new VBox();
	    	ans.setSpacing(20);
	    	ans.getStylesheets().add("button.css");
	    	ans.setPrefSize(1500, 1500);
	    	ans.setStyle("-fx-background-color: #b5b5b5;");
	    	
	    	GridPane grid = new GridPane();
	    	grid.setHgap(50);
	    	grid.setVgap(60);
	    	grid.setPadding(new Insets(30,10,30,30));
	    	grid.getStylesheets().add("button.css");
	        grid.setStyle("-fx-background-color: #3a3a3a;");
	        ans.getChildren().add(grid);
	        
	        GridPane last_grid = new GridPane();
	        last_grid.setHgap(50);
	        last_grid.setVgap(60);
	        last_grid.setPadding(new Insets(30,10,30,30));
	        last_grid.getStylesheets().add("button.css");
	        last_grid.setStyle("-fx-background-color: #3a3a3a;");
	        
	        Label cost_label = new Label();
	        cost_label.setStyle("-fx-text-fill: #ffffff;");
	        cost_label.setText("Total Cost : ");
	        cost_label.setAlignment(Pos.BASELINE_RIGHT);
	        cost_label.setMinWidth(250);
	        
	        Label price_label = new Label();
	        price_label.setStyle("-fx-text-fill: #dc143c;-fx-background-color: #ffffff;");
	        price_label.setText(Double.toString(sum));
	        price_label.setAlignment(Pos.BASELINE_CENTER);
	        price_label.setMinWidth(250);
	        
	        Button confirm = new Button();
	        confirm.setText("Confirm your Order !");
	        confirm.setId("GeneralButtonGreen");
	        confirm.setMinWidth(250);
			
			Button show = new Button();
			show.setText("Show your Cart");
			show.setId("LoginButton");
			show.setMinWidth(250);
			
			Button empty = new Button();
			empty.setText("Empty your Cart");
			empty.setId("ResetButton");
			empty.setMinWidth(250);
			
			grid.setMargin(cost_label, new Insets(0,0,0,0));
	        grid.setMargin(price_label, new Insets(0,0,0,0));

	        grid.setHalignment(cost_label, HPos.LEFT);
	        grid.setHalignment(price_label, HPos.LEFT);
			
	        
			grid.add(empty, 1, 0);
			grid.add(show, 0, 0);
			grid.add(cost_label, 0, 1);
			grid.add(price_label, 1, 1);
			grid.add(confirm, 2, 1);
			
	        
	        Alert error = new Alert(AlertType.ERROR);
	        error.setTitle("Error Dialog");
	        error.setHeaderText(null);
	        error.setContentText("Incorrect Entry !");
	        error.initStyle(StageStyle.UNDECORATED);
	        
	        Alert success = new Alert(AlertType.INFORMATION);
	        success.setTitle("Error Dialog");
	        success.setHeaderText(null);
	        success.setContentText("Added Successfully !\nThank you for shopping with us");
	        success.initStyle(StageStyle.UNDECORATED);
	        
	        Alert removed_success = new Alert(AlertType.INFORMATION);
	        removed_success.setTitle("Error Dialog");
	        removed_success.setHeaderText(null);
	        removed_success.setContentText("Removed Successfully !");
	        removed_success.initStyle(StageStyle.UNDECORATED);
	        
	        Alert confirm_pop_up = new Alert(AlertType.CONFIRMATION);
	        confirm_pop_up.setTitle("Error Dialog");
	        confirm_pop_up.setHeaderText(null);
	        confirm_pop_up.setContentText("You sure you want to proceed ?");
	        confirm_pop_up.initStyle(StageStyle.UNDECORATED);
	        
	        
	        empty.setOnAction(e ->
    		{
    			
    			try
	  			{
					int a = Integer.parseInt(Admin_Home_Page_Panels.id);
	  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
					Statement stmt=con.createStatement();
					String query = "DELETE from Checkout where customer_id = " + a + ";";
					stmt.executeUpdate(query);
					con.close();
					removed_success.showAndWait();
	  			}
	  			catch(Exception err)
	  			{
	  				err.printStackTrace();
	  			}
    		});
	        confirm.setOnAction(e ->
    		{
    			Optional<ButtonType> result = confirm_pop_up.showAndWait();
    			String choice = result.get().getText();
    			if(choice.equals("OK"))
    			{
    				int customerid = Integer.parseInt(Admin_Home_Page_Panels.id);
    				
    				Calendar calender = Calendar.getInstance();
    				SimpleDateFormat time_formatter = new SimpleDateFormat("HH:mm:ss");
    				SimpleDateFormat date_formatter= new SimpleDateFormat("yyyy/MM/dd");
    			    Date today = new Date();
    			    
    			    String time = time_formatter.format(today);
    			    String date = date_formatter.format(today);
    			    
    			    calender.add(Calendar.DAY_OF_MONTH, 10);
    			    
    			    String delivery_date = date_formatter.format(today);
    			    String status = "Pending";
    			    
    			    try
    	  			{
    	  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
    					Statement stmt=con.createStatement();
    					int n = product_id.size();
    					for(int i=0;i<n;i++)
    					{
    						String query = "INSERT INTO Order_History (customer_id,date,time,product_name,delivery_date,return_status,product_id) VALUES "
    						+ "("+customerid+",\'"+date+"\',\'"+time+"\',\'"+product_name.get(i)+"\',\'"+delivery_date+"\',\'"+status+"\',"+product_id.get(i)+");";
    						stmt.executeUpdate(query);
    					}
    					con.close();
    					success.showAndWait();
    	  			}
    	  			catch(Exception err)
    	  			{
    	  				err.printStackTrace();
    	  			}
    			    try
    	  			{
    					int a = Integer.parseInt(Admin_Home_Page_Panels.id);
    	  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
    					Statement stmt=con.createStatement();
    					String query = "DELETE from Checkout where customer_id = " + a + ";";
    					stmt.executeUpdate(query);
    					con.close();
    	  			}
    	  			catch(Exception err)
    	  			{
    	  				err.printStackTrace();
    	  			}
    			}
    		});
	        show.setOnAction(e ->
	  		{	
	  			ObservableList<temp_order> list = FXCollections.observableArrayList();
				ObservableList<order> final_list = FXCollections.observableArrayList();
	  			try
	  			{
					int a = Integer.parseInt(Admin_Home_Page_Panels.id);
	  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
					Statement stmt=con.createStatement();
					String query = "SELECT * from Checkout where customer_id = " + a + ";";
					ResultSet rs = stmt.executeQuery(query);
					while(rs.next())
					{
						long tid = Long.parseLong(rs.getString(4));
						int tqty = Integer.parseInt(rs.getString(3));
						list.add(new temp_order(tid,tqty));
					}
					con.close();
	  			}
	  			catch(Exception err)
	  			{
	  				err.printStackTrace();
	  			}
	  			try
	  			{
	  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
					Statement stmt=con.createStatement();
					for(int i=0;i<list.size();i++)
					{
						int aid = Integer.parseInt(Admin_Home_Page_Panels.id);
						int bqty = (int) list.get(i).getQuantity();
						int cid = (int) list.get(i).getId();
						String query = "select * from Product_Type where product_id = " + cid + ";";
						ResultSet rs = stmt.executeQuery(query);
						rs.next();
						final_list.add(new order(rs.getLong(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7),bqty));
					}
					con.close();
	  			}
	  			catch(Exception err)
	  			{
	  				err.printStackTrace();
	  			}
	  			DisplayOrder(ans, final_list);
	  			
	  			
	  		});
	        
	        return ans;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static VBox ConsolePanel()
	{
		try
		{
			VBox ans = new VBox();
	    	ans.setSpacing(20);
	    	ans.getStylesheets().add("button.css");
	    	ans.setPrefSize(1500, 1500);
	    	ans.setPadding(new Insets(50,0,50,0));;
	    	ans.setStyle("-fx-background-color: #3a3a3a;");
	    	
	    	GridPane grid = new GridPane();
	    	grid.setHgap(50);
	    	grid.setVgap(30);
	    	grid.setPadding(new Insets(10,10,10,50));
	    	grid.getStylesheets().add("button.css");
	        grid.setStyle("-fx-background-color: #3a3a3a;");
	        ans.getChildren().add(grid);
	        
	        Label commands_label = new Label();
	        commands_label.setStyle("-fx-text-fill: #ffffff;");
	        commands_label.setText("Enter SQL Commands");
	        commands_label.setAlignment(Pos.BASELINE_LEFT);
	        
	        final TextField commands_textfield = new TextField();
	        commands_textfield.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        commands_textfield.setPromptText("SQL Commands");
	        commands_textfield.setAlignment(Pos.BASELINE_LEFT);
	        commands_textfield.setText("");
	        commands_textfield.setMinWidth(800);
	        commands_textfield.setMinHeight(100);
	        
	        Label result_textfield = new Label();
	        result_textfield.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
	        result_textfield.setText("");
	        result_textfield.setAlignment(Pos.BASELINE_LEFT);
	        result_textfield.setMinWidth(800);
	        result_textfield.setMinHeight(300);
	        
	        Button submit = new Button();
	        submit.setText("Submit");
			submit.setId("LoginButton");
			submit.setMinWidth(150);
			
			Button clear = new Button();
	        clear.setText("Cancel");
			clear.setId("ResetButton");
			clear.setMinWidth(150);
			
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setContent(result_textfield);
			scrollPane.setStyle("-fx-background-color: #ffaaaa;-fx-prompt-text-fill: #ffffff;-fx-text-fill: #3a3a3a;");
			scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
			scrollPane.setMinHeight(300);
			scrollPane.setMaxHeight(800);
			
	        grid.setMargin(commands_label, new Insets(0,0,0,0));
	        grid.setMargin(commands_textfield, new Insets(0,0,0,0));
	        grid.setMargin(submit, new Insets(0,0,0,0));
	        grid.setMargin(clear, new Insets(0,0,0,0));
	        
	        grid.setHalignment(commands_label, HPos.CENTER);
	        grid.setHalignment(commands_textfield, HPos.CENTER);
	        
	        grid.add(commands_label, 0, 0);
	        grid.add(commands_textfield, 0, 1);
	        grid.add(submit, 0, 3);
	        grid.add(clear, 0, 4);
	        grid.add(scrollPane, 0, 5);
	        
	        Alert error = new Alert(AlertType.ERROR);
	        error.setTitle("Error Dialog");
	        error.setHeaderText(null);
	        error.setContentText("Either Old or New Password doesn't match !");
	        error.initStyle(StageStyle.UNDECORATED);
	        
	        Alert empty = new Alert(AlertType.ERROR);
	        empty.setTitle("Error Dialog");
	        empty.setHeaderText(null);
	        empty.setContentText("Empty or Invalid Query");
	        empty.initStyle(StageStyle.UNDECORATED);
	        
	        Alert success = new Alert(AlertType.INFORMATION);
	        success.setTitle("Error Dialog");
	        success.setHeaderText(null);
	        success.setContentText("Password Changed Successfully !");
	        success.initStyle(StageStyle.UNDECORATED);
	        
	        Alert updatequerysucess = new Alert(AlertType.INFORMATION);
	        updatequerysucess.setTitle("Error Dialog");
	        updatequerysucess.setHeaderText(null);
	        updatequerysucess.setContentText("SQL Query Successful");
	        updatequerysucess.initStyle(StageStyle.UNDECORATED);
	        
	        String str = "";
	        
	        submit.setOnAction(e -> 
			{
				try
	  			{
					result_textfield.setText("");
	  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
					Statement stmt=con.createStatement();
					String query = commands_textfield.getText();
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					int n = rs.getMetaData().getColumnCount();
					for(int i =0;i<n;i++)
					{
						result_textfield.setText(result_textfield.getText().concat(rsmd.getColumnName(i+1)+"\t"));
					}
					result_textfield.setText(result_textfield.getText().concat("\n"));
					while(rs.next())
					{
						for(int i=0;i<n;i++)
						{
							result_textfield.setText(result_textfield.getText().concat(rs.getString(i+1)+"\t"));
						}
						result_textfield.setText(result_textfield.getText().concat("\n"));
					}
					con.close();
	  			}
	  			catch(Exception err)
	  			{
	  				try
		  			{
		  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
						Statement stmt=con.createStatement();
						String query = commands_textfield.getText();
						stmt.executeUpdate(query);
						result_textfield.setText("Query Successfully Run");
						con.close();
		  			}
		  			catch(Exception err2)
		  			{
		  				empty.showAndWait();
		  			}
	  			}
			});
			clear.setOnAction(e ->
	  		{
	  			commands_textfield.setText(null);
	  		});
	        
	        return ans;
		}
		catch(Exception e) 
		{
			e.printStackTrace();	
		}
		return null;
	}
	public static VBox OrderHistoryPanel()
	{
		try
		{
			int sid = Integer.parseInt(Admin_Home_Page_Panels.id);
			
			VBox ans = new VBox();
	    	ans.setSpacing(20);
	    	ans.getStylesheets().add("button.css");
	    	ans.setPrefSize(1500, 1500);
	    	ans.setStyle("-fx-background-color: #b5b5b5;");
	    	
	    	GridPane grid = new GridPane();
	    	grid.setHgap(50);
	    	grid.setVgap(60);
	    	grid.setPadding(new Insets(30,10,30,30));
	    	grid.getStylesheets().add("button.css");
	        grid.setStyle("-fx-background-color: #3a3a3a;");
	        ans.getChildren().add(grid);
	        
	        GridPane last_grid = new GridPane();
	        last_grid.setHgap(50);
	        last_grid.setVgap(60);
	        last_grid.setPadding(new Insets(30,10,30,30));
	        last_grid.getStylesheets().add("button.css");
	        last_grid.setStyle("-fx-background-color: #3a3a3a;");
	        
			Button show = new Button();
			show.setText("Previous Orders");
			show.setId("LoginButton");
			show.setMinWidth(250);
			
			Button empty = new Button();
			empty.setText("Empty your Cart");
			empty.setId("ResetButton");
			empty.setMinWidth(250);
			
			grid.add(show, 1, 0);
	        
	        Alert error = new Alert(AlertType.ERROR);
	        error.setTitle("Error Dialog");
	        error.setHeaderText(null);
	        error.setContentText("Incorrect Entry !");
	        error.initStyle(StageStyle.UNDECORATED);
	        
	        Alert success = new Alert(AlertType.INFORMATION);
	        success.setTitle("Error Dialog");
	        success.setHeaderText(null);
	        success.setContentText("Added Successfully !\nThank you for shopping with us");
	        success.initStyle(StageStyle.UNDECORATED);
	        
	        Alert removed_success = new Alert(AlertType.INFORMATION);
	        removed_success.setTitle("Error Dialog");
	        removed_success.setHeaderText(null);
	        removed_success.setContentText("Removed Successfully !");
	        removed_success.initStyle(StageStyle.UNDECORATED);
	        
	        Alert confirm_pop_up = new Alert(AlertType.CONFIRMATION);
	        confirm_pop_up.setTitle("Error Dialog");
	        confirm_pop_up.setHeaderText(null);
	        confirm_pop_up.setContentText("You sure you want to proceed ?");
	        confirm_pop_up.initStyle(StageStyle.UNDECORATED);
	        
	        show.setOnAction(e ->
	  		{	
	  			ObservableList<orderhistory> list = FXCollections.observableArrayList();
				ObservableList<orderhistory> final_list = FXCollections.observableArrayList();
	  			try
	  			{
	  				Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
					Statement stmt=con.createStatement();
					String query = "select * from order_history where supplier_id = " + sid + ";";
					ResultSet rs = stmt.executeQuery(query);
					while(rs.next())
					{
						
						final_list.add(new orderhistory(Long.parseLong(rs.getString(1)),Long.parseLong(rs.getString(2)), sid, Long.parseLong(rs.getString(7)), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(9), rs.getString(10)));
					}
					con.close();
	  			}
	  			catch(Exception err)
	  			{
	  				err.printStackTrace();
	  			}
	  			DisplayOrderHistory(ans, final_list);
	  			
	  			
	  		});
	        
	        return ans;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}

