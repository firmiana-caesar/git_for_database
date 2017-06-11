package com.admin.dao;

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
}
