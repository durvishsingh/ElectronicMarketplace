package application;

import java.sql.*;

public class DataBase
{  
	public static void main(String args[])
	{  
		int a = 1,b=2,c=3;
		int customerid = 1;
		int date = 2;
		int time = 3;
		int temp1 = 4;
		int status = 5;
		int temp2 = 6;
		int delivery_date = 7;
		String str = "INSERT INTO Requests (customer_id,supplier_id,product_id,quantity,date_order) VALUES "
				+ "("+customerid+","+date+","+time+","+temp1+",\'"+status+"\';";
		System.out.println(str);
//		try
//		{  
//			Connection con=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/store","root","9153");
//			Statement stmt=con.createStatement();
//			ResultSet rs=stmt.executeQuery("select * from Account_User");
//			
//			ResultSetMetaData rsmd = rs.getMetaData();
//			int columnsNumber = rsmd.getColumnCount();
//			int i = 0;
//			while(i<columnsNumber)
//			{
//				System.out.print(rsmd.getColumnName(i+1) + " ");
////				System.out.println(i);
//				i++;
//			}
//			System.out.println();
//			while(rs.next())  
//				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
//			con.close();  
//		}
//		catch(Exception e)
//		{ 
//			System.out.println(e);
//		}  
	}  
}  
