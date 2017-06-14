package com.admin.dao;

import java.io.File;
import java.sql.*;
import com.admin.model.admin;

public class admindao {
	public admin login(Connection con, admin admin) throws Exception{
		admin resultadmin = null;
		String sql = "select * from admin where ad_id = ? and ad_password = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		String admin_id = String.valueOf(admin.getAdmin_id());
		statement.setString(1, admin_id);
		statement.setString(2, admin.getAdmin_password());
		ResultSet rs = statement.executeQuery();
		if(rs.next()){
			resultadmin = new admin();
			int i = Integer.valueOf(rs.getString("ad_id")).intValue();
			resultadmin.setAdmin_id(i);
			resultadmin.setAdmin_password(rs.getString("ad_password"));
		}
		return resultadmin;
	}
	public boolean logon(Connection con, String id, String password) throws Exception{
		int i = Integer.valueOf(id).intValue();
		String sql = "insert into admin (ad_id, ad_password) values (" + i + "," + password + ")";
		try{
			PreparedStatement statement = con.prepareStatement(sql);
			statement.executeQuery();
			return true;
		}catch(Exception e){
			e.getStackTrace();
			return false;
		}
	}
	public  ResultSet check_book_message(Connection con) throws Exception{
		// TODO Auto-generated method stub
		String sql = "select * from bookmessage";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			return rs;
		}catch(Exception e){
			e.getStackTrace();
			return null;
		}
	}
	
	public ResultSet check_user_message(Connection con) throws Exception{
		String sql = "select * from bookuserone";
		try{
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			return rs;
		}catch(Exception e){
			e.getStackTrace();
			return null;
		}
	}
	
	public boolean del_user(String user_id, Connection con) throws Exception{
		String sql = "delete from bookuserone where user_id = " + user_id + "";
		try{
			PreparedStatement statement = con.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		}catch(Exception e){
			e.getStackTrace();
			return false;
		}
	}
	
	public ResultSet check_card_message(Connection con) throws Exception{
		String sql = "select * from bookcard";
		try{
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			return rs;
		}catch(Exception e){
			e.getStackTrace();
			return null;
		}
	}
	
	public boolean insertbook(Connection con, String bookno, String bookauthor, String bookprice, String bookcode, String bookamount,String bookname){
		int code =  Integer.valueOf(bookcode).intValue();
		int amount =  Integer.valueOf(bookamount).intValue();
		String sql = "insert into bookmessage (book_no, book_author, book_price, book_typecode, book_amount, book_name) values (" + "'"+bookno+"'" +"," + "'"+bookauthor+"'" + "," + "'"+bookprice+"'"  + "," + code + "," + amount + "," + "'"+bookname +"'"+")";
		try{
			PreparedStatement statement = con.prepareStatement(sql);
			statement.execute();
			return true;
		}catch(Exception e){
			e.getStackTrace();
			return false;
		}
	}
	
	public ResultSet check_borrow_message(Connection con){
		String sql = "select * from borrowmessage";
		try{
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			return rs;
		}catch(Exception e){
			e.getStackTrace();
			return null;
		}
	}
	
	public boolean data_backup(Connection con){
	String sql = "use master backup database Library to disk = 'F:\\database_backup\\Library.bak'";
	try{
		PreparedStatement statement = con.prepareStatement(sql);
		statement.execute();
		return true;
	}catch(Exception e){
		e.getStackTrace();
		return false;
	}
	}
	
	public boolean data_restore(Connection con) throws Exception{
		String sql = "use master restore database Library from disk = 'F:\\database_backup\\Library.bak' with replace";
		try{
			PreparedStatement statement = con.prepareStatement(sql);
			statement.execute();
			return true;
		}catch(Exception e){
			e.getStackTrace();
			return false;
		}
	}
	
	public boolean del_book(Connection con, String book_id) throws Exception{
		String sql = "delete from bookmessage where book_no = '" + book_id + "'";
		try{
			PreparedStatement statement = con.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		}catch(Exception e){
			e.getStackTrace();
			return false;
		}
	}
	
	public boolean update_bookmessage(Connection con, String update_id, String update_type, String update_mes){
		String sql = null;
		switch(update_type)
		{
		case "作者": sql = "update bookmessage set book_author = '" + update_mes + "' where book_no = '" +update_id +"'";
							break;
		case "价格": sql = "update bookmessage set book_price = '" + update_mes + "' where book_no = '" +update_id +"'";
							break;
		case "代码": sql =  "update bookmessage set book_typecode = " + update_mes + " where book_no = '" +update_id +"'";
							break;
		case "数量": sql =  "update bookmessage set book_amount = " + update_mes + " where book_no = '" +update_id +"'";
							break;
		case "书名": sql =  "update bookmessage set book_name = '" + update_mes + "' where book_no = '" +update_id +"'";		
							break;
		}
		try{
			PreparedStatement statement = con.prepareStatement(sql);
			statement.execute();
			return true;
		}catch(Exception e){
			e.getStackTrace();
			return false;
		}
	}
	
}
