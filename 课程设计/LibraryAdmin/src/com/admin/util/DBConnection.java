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
				System.out.println("���������ɹ���");
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("��������ʧ�ܣ�");
			}
			try{
				dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
					System.out.println("�������ݿ�ɹ���");	
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.print("SQL Server����ʧ�ܣ�");
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
				System.out.print("SQL Server�ر�ʧ�ܣ�");
			}
	  }
	  
	 public static void main(String [] args) {
		 	DBConnection dbconnection = new DBConnection();
		 	try{
		 		dbconnection.getCon();
		 		System.out.println("���ݿ���������");
		 	}catch(Exception e){
		 		e.printStackTrace();
		 		System.out.println("���ݿ�δ����������");
		 	}
	}
}
