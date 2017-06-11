package com.admin.view;

import java.sql.ResultSet;

//import java.awt.Color;
import javax.swing.*;

class Demo1 {
    private String[] columnNames = { "1", "2", "3", "4", "5","6" };
    private String[][] cells ;
    private ResultSet rs;

    public JTable search(ResultSet rs) {
    	this.rs = rs;
        JTable JTable = new JTable(buildObject(), columnNames);// 创建表格
       return JTable;
    }
    public String[][] buildObject(){
		try{
    	int chang = this.rs.getMetaData().getColumnCount();
		int kuan = this.rs.getRow();
		for (int i  = 0; i < chang; i++)
		{
			for (int j = 0; j < kuan ; j++){
				this.cells[i][j] = rs.getString(i);
			}
		}
		}catch(Exception e){
			e.getStackTrace();
		}
		return this.cells;
    }
}
