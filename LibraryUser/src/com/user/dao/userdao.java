package com.user.dao;

import java.sql.*;

public class userdao {
	public static ResultSet user_check_book(Connection con){
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
	
	public static ResultSet check_borrow_message(Connection con, String borrow_id){
		String sql = "select * from borrowmessage where card_no = '" + borrow_id + "'";
		try{
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			return rs;
		}catch(Exception e){
			e.getStackTrace();
			return null;
		}
	}
	
	public static int check_book_acount(Connection con, String borrow_book_id){
		String sql = "select book_amount from bookmessage where book_no = '" + borrow_book_id +"'";
		ResultSet rs = null;
		int count = 0;
		try{
			PreparedStatement statement = con.prepareStatement(sql);
			rs = statement.executeQuery();
		}catch(Exception e){
			e.getStackTrace();
		}
		try{
			while(rs.next()){
				count = Integer.valueOf(rs.getString("book_amount")).intValue();
			}
		}catch(Exception v){
			v.getStackTrace();
		}
		return count;
	}
		
	public static boolean user_borrow_book(Connection con, String borrowercard_id, String borrow_book_id, int book_final_account){
		String sql = "select * from bookmessage where book_no = '" + borrow_book_id + "'";
		ResultSet rs1 = null;
		String book_name = null;
		
		try{
		PreparedStatement statement = con.prepareStatement(sql);
		rs1 = statement.executeQuery();
		}catch(Exception e){
			e.getStackTrace();
		}
		
		try{
			while(rs1.next()){
				book_name = rs1.getString("book_name");
			}
		}catch(Exception v){
			v.getStackTrace();
		}
		
		String sqlone = "update bookmessage set book_amount = " + book_final_account + "where book_no = '" + borrow_book_id + "'";
		try{
			PreparedStatement statementtwo = con.prepareStatement(sqlone);
			statementtwo.executeUpdate();
		}catch(Exception clap){
			clap.getStackTrace();
		}
		
		String sql1 = "insert into borrowmessage(card_no, book_no, book_name, borrow_date) values ('" + borrowercard_id  + "','" + borrow_book_id +"','" + book_name +"', getdate())";                                       
		
		try{
			PreparedStatement statementone = con.prepareStatement(sql1);
			statementone.executeUpdate();
			return true;
		}catch(Exception uuuuuu)
		{
			uuuuuu.getStackTrace();
			return false;
		}
	}
	
	public static boolean user_return_book(Connection con, String returnercard_id, String return_book_id, int bookcount){
		
		String sql = "select * from bookmessage where book_no = '" + return_book_id + "'";
		ResultSet rs1 = null;
		String book_name = null;
		
		try{
			PreparedStatement statement = con.prepareStatement(sql);
			rs1 = statement.executeQuery();
			}catch(Exception e){
				e.getStackTrace();
				return false;
			}
			
			try{
				while(rs1.next()){
					book_name = rs1.getString("book_name");
				}
			}catch(Exception v){
				v.getStackTrace();
				return false;
			}
			
			String sqlone = "update bookmessage set book_amount = " + bookcount + "where book_no = '" + return_book_id + "'";
			try{
				PreparedStatement statementtwo = con.prepareStatement(sqlone);
				statementtwo.executeUpdate();
			}catch(Exception clap){
				clap.getStackTrace();
				return false;
			}
			
			String sql1 = "update borrowmessage set return_date = getdate() where card_no = '" + returnercard_id + "' and book_no = '" + return_book_id + "'";
		
			try{
				PreparedStatement statementthree = con.prepareStatement(sql1);
				statementthree.executeUpdate();
				return true;
			}catch(Exception poi){
				poi.getStackTrace();
				return false;
			}
			
	}
	
}
	
	

