package com.admin.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.admin.dao.admindao;
import com.admin.util.DBConnection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class adminmanagement extends JFrame {
	
	DBConnection dbcon = new DBConnection();
	admindao admindao = new admindao();

	private JPanel contentPane;
	JTable tableone = new JTable();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminmanagement frame = new adminmanagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public adminmanagement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//查询图书信息
		JButton check_usermessage = new JButton("\u67E5\u8BE2\u56FE\u4E66\u8BB0\u5F55");
		check_usermessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ResultSet rs = admindao.check_book_message(dbcon.getCon());
					if (rs != null){
						JOptionPane.showMessageDialog(null, "查询成功");
						
						
						JFrame frameone = new JFrame();
						frameone.setBounds(100, 100, 582, 437);
						 frameone.getContentPane().setLayout(null);
					      JScrollPane scrollPane = new JScrollPane();
					       scrollPane.setBounds(14, 13, 536, 337);
					       frameone.getContentPane().add(scrollPane);
					       JButton button = new JButton("\u67E5\u627E"); // 查找
					       button.addActionListener(new ActionListener() {
					           public void actionPerformed(ActionEvent e) {
					               //Demo1 s = new Demo1();
					               try {
					                  if (tableone != null) {// 先判断表模型是否为空,如果不是则先清除表
					                       JFrame f = new JFrame();
					                    f.remove(tableone);
					                   }
					                   // 表赋值
					                   
					                   String[] columnNames = { "1", "2", "3", "4", "5","6" };
					                   int chang = rs.getMetaData().getColumnCount();
					               		int kuan = 2;
					                   String[][] cells = new String[kuan][chang];
					                   
					                   try{
					                	  
					                	   ArrayList<String> one = new ArrayList<String>();
					                	   ArrayList<String> two = new ArrayList<String>();
					                	   ArrayList<String> three = new ArrayList<String>();
					                	   ArrayList<String> four = new ArrayList<String>();
					                	   ArrayList<String> five = new ArrayList<String>();
					                	   ArrayList<String> six = new ArrayList<String>();
					                	   
					                	   while(rs.next()){
					                		   one.add(rs.getString("book_no"));
					                		   two.add(rs.getString("book_author"));
					                		   three.add(rs.getString("book_price"));
					                		   four.add(rs.getString("book_typecode"));
					                		   five.add(rs.getString("book_amount"));
					                		   six.add(rs.getString("book_name"));
					                	   }
					                	   
					                	   for (int longu = 0; longu < kuan; longu++){
					                			   cells[longu][0] = one.get(longu);
					                			   cells[longu][1] = two.get(longu);
					                			   cells[longu][2] = three.get(longu);
					                			   cells[longu][3] = four.get(longu);
					                			   cells[longu][4] = five.get(longu);
					                			   cells[longu][5] = six.get(longu);
					                	   }
					                	   
					               		}catch(Exception fucku){
					               			fucku.getStackTrace();
					               		}
					               		
					                  DefaultTableModel defaultmodel = new DefaultTableModel(cells, columnNames);
					                  tableone = new JTable(defaultmodel);
					               
					                  // tableone = new JTable(cells, columnNames);
					                   tableone.setAutoCreateRowSorter(true);
					                   scrollPane.setViewportView(tableone);// 添加滚动条
					               } catch (Exception ex) {
					               }
					           }
					       });
					       button.setBounds(260, 366, 63, 23);
					       frameone.getContentPane().add(button);
					       frameone.getContentPane().add(tableone);
					       frameone.addWindowListener(new WindowAdapter() {
					           @Override
					           public void windowClosed(WindowEvent e) {
					               frameone.dispose();
					           }
					       });
						frameone.setVisible(true);
					}	
				}catch(Exception fuck){
					fuck.getStackTrace();
					JOptionPane.showMessageDialog(null, "查询失败");
				}
			}
		});
		
		//查询用户信息
		JButton check_bookmessage = new JButton("\u67E5\u8BE2\u7528\u6237\u8BB0\u5F55");
		check_bookmessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ResultSet rs = admindao.check_book_message(dbcon.getCon());
					if (rs != null){
						JOptionPane.showMessageDialog(null, "查询成功");
					}	
				}catch(Exception fuck){
					fuck.getStackTrace();
					JOptionPane.showMessageDialog(null, "查询失败");
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(13)
					.addComponent(check_usermessage)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(check_bookmessage)
					.addContainerGap(304, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(450, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(check_usermessage)
						.addComponent(check_bookmessage)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
