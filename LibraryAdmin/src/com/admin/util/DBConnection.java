package com.admin.util;

import java.sql.*;

public class DBConnection {
	  private String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	  private String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Library";
	  private String userName="sa";
	  private String userPwd="1q784697w543";
	  
	  Connection dbConn;
	  
	  public Connection getCon() throws Exception {
		  try
			{
				Class.forName(driverName);
				System.out.println("加载驱动成功！");
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("加载驱动失败！");
			}
			try{
				dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
					System.out.println("连接数据库成功！");	
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.print("SQL Server连接失败！");
			}
			return dbConn;
	  }
	  
	  public void closeCon(Connection Con) throws Exception{
		 try{
			 if (Con != null)
			  Con.close();
		 }catch(Exception e)
			{
				e.printStackTrace();
				System.out.print("SQL Server关闭失败！");
			}
	  }
	  
	 public static void main(String [] args) {
		 	DBConnection dbconnection = new DBConnection();
		 	try{
		 		dbconnection.getCon();
		 		System.out.println("数据库连接正常");
		 	}catch(Exception e){
		 		e.printStackTrace();
		 		System.out.println("数据库未能正常连接");
		 	}
	}
}
