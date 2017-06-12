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
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class adminmanagement extends JFrame {
	DBConnection dbcon = new DBConnection();
	admindao admindao = new admindao();
	private JPanel contentPane;
	JTable tableone = new JTable();
	JTable tabletwo = new JTable();
	JTable tablethree = new JTable();
	JTable tablefour = new JTable();
	private JTextField del_user_id;
	private JTextField book_no;
	private JTextField book_author;
	private JTextField book_price;
	private JTextField book_code;
	private JTextField book_amount;
	private JTextField book_name;
	private JTextField del_bookid;
	private JTextField update_bookno;
	private JTextField update_booktype;
	private JTextField update_bookmessage;
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
		setBounds(100, 100, 600, 600);
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
					               try {
					                  if (tableone != null) {// 先判断表模型是否为空,如果不是则先清除表
					                       JFrame f = new JFrame();
					                    f.remove(tableone);
					                   }
					                   // 表赋值
					                   String[] columnNames = { "1", "2", "3", "4", "5","6" };
					                   int chang = rs.getMetaData().getColumnCount();
					               		int kuan = 50;
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
					if (tabletwo != null) {// 先判断表模型是否为空,如果不是则先清除表
	                       JFrame f = new JFrame();
	                    f.remove(tabletwo);
	                   }
					ResultSet rs = admindao.check_user_message(dbcon.getCon());
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
						               try {
						                  if (tableone != null) {// 先判断表模型是否为空,如果不是则先清除表
						                       JFrame f = new JFrame();
						                    f.remove(tabletwo);
						                   }
						                   // 表赋值
						                   String[] columnNames = { "1", "2", "3", "4" };
						                   int chang = rs.getMetaData().getColumnCount();
						               		int kuan = 50;
						                   String[][] cells = new String[kuan][chang];
						                   try{
						                	   ArrayList<String> one = new ArrayList<String>();
						                	   ArrayList<String> two = new ArrayList<String>();
						                	   ArrayList<String> three = new ArrayList<String>();
						                	   ArrayList<String> four = new ArrayList<String>();
						                	   while(rs.next()){
						                		   one.add(rs.getString("user_id"));
						                		   two.add(rs.getString("user_name"));
						                		   three.add(rs.getString("user_password"));
						                		   four.add(rs.getString("user_cardno"));
						                	   }
						                	   for (int longu = 0; longu < kuan; longu++){
						                			   cells[longu][0] = one.get(longu);
						                			   cells[longu][1] = two.get(longu);
						                			   cells[longu][2] = three.get(longu);
						                			   cells[longu][3] = four.get(longu);   
						                	   }
						               		}catch(Exception fucku){
						               			fucku.getStackTrace();
						               		}				               		
						                  DefaultTableModel defaultmodel = new DefaultTableModel(cells, columnNames);
						                  tabletwo = new JTable(defaultmodel);
						                   tabletwo.setAutoCreateRowSorter(true);
						                   scrollPane.setViewportView(tabletwo);// 添加滚动条
						               } catch (Exception ex) {
						               }
						           }
						       });
						       button.setBounds(260, 366, 63, 23);
						       frameone.getContentPane().add(button);
						       frameone.getContentPane().add(tabletwo);
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
		
		JTextPane txtpnid = new JTextPane();
		txtpnid.setText("\u7528\u6237id");
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("\u5220\u9664\u5DF2\u6CE8\u518C\u7528\u6237\uFF1A");
		
		del_user_id = new JTextField();
		del_user_id.setColumns(10);
		
		JButton btnNewButton = new JButton("\u5220\u9664");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user_id = del_user_id.getText();
				try{
				boolean yes_or_no = admindao.del_user(user_id, dbcon.getCon());
				if(yes_or_no)
					JOptionPane.showMessageDialog(null, "删除成功");
				}catch(Exception fuckme){
					fuckme.getStackTrace();
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u67E5\u8BE2\u501F\u4E66\u5361\u8BB0\u5F55");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					if (tablethree != null) {// 先判断表模型是否为空,如果不是则先清除表
	                       JFrame f = new JFrame();
	                    f.remove(tablethree);
	                   }
					ResultSet rs = admindao.check_card_message(dbcon.getCon());
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
						               try {
						                  if (tablethree != null) {// 先判断表模型是否为空,如果不是则先清除表
						                       JFrame f = new JFrame();
						                    f.remove(tablethree);
						                   }
						                   // 表赋值
						                   String[] columnNames = { "1", "2" };
						                   int chang = rs.getMetaData().getColumnCount();
						               		int kuan = 50;
						                   String[][] cells = new String[kuan][chang];
						                   try{
						                	   ArrayList<String> one = new ArrayList<String>();
						                	   ArrayList<String> two = new ArrayList<String>();
						                	   
						                	   while(rs.next()){
						                		   one.add(rs.getString("card_no"));
						                		   two.add(rs.getString("card_userno"));
						                		   
						                	   }
						                	   for (int longu = 0; longu < kuan; longu++){
						                			   cells[longu][0] = one.get(longu);
						                			   cells[longu][1] = two.get(longu);
						                			  
						                	   }
						               		}catch(Exception fucku){
						               			fucku.getStackTrace();
						               		}				               		
						                  DefaultTableModel defaultmodel = new DefaultTableModel(cells, columnNames);
						                  tablethree = new JTable(defaultmodel);
						                   tablethree.setAutoCreateRowSorter(true);
						                   scrollPane.setViewportView(tablethree);// 添加滚动条
						               } catch (Exception ex) {
						               }
						           }
						       });
						       button.setBounds(260, 366, 63, 23);
						       frameone.getContentPane().add(button);
						       frameone.getContentPane().add(tablethree);
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
		
		JTextPane textPane = new JTextPane();
		textPane.setText("    \u56FE\u4E66\u5165\u5E93");
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("\u56FE\u4E66\u7F16\u53F7");
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("\u56FE\u4E66\u4F5C\u8005");
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText("\u56FE\u4E66\u4EF7\u683C");
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setText("\u56FE\u4E66\u4EE3\u7801");
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setText("\u5165\u5E93\u6570\u91CF");
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setText("\u56FE\u4E66\u540D\u79F0");
		
		book_no = new JTextField();
		book_no.setColumns(10);
		
		book_author = new JTextField();
		book_author.setColumns(10);
		
		book_price = new JTextField();
		book_price.setColumns(10);
		
		book_code = new JTextField();
		book_code.setColumns(10);
		
		book_amount = new JTextField();
		book_amount.setColumns(10);
		
		book_name = new JTextField();
		book_name.setColumns(10);
		
		JButton button = new JButton("\u786E\u8BA4\u5165\u5E93");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bookno = book_no.getText();
				String bookauthor = book_author.getText();
				String bookprice = book_price.getText();
				String bookcode = book_code.getText();
				String bookamount = book_amount.getText();
				String bookname = book_name.getText();
				
				try{
					boolean do_or_not = admindao.insertbook(dbcon.getCon(), bookno, bookauthor, bookprice, bookcode, bookamount,bookname);
					if (do_or_not)
						JOptionPane.showMessageDialog(null, "入库成功");
					else
						JOptionPane.showMessageDialog(null, "入库失败");
				}catch(Exception fuckmeagain){
					fuckmeagain.getStackTrace();
					JOptionPane.showMessageDialog(null, "入库失败");
				}
				
			}
		});
		
		JButton button_1 = new JButton("\u67E5\u8BE2\u501F\u9605\u8BB0\u5F55");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					if (tablefour != null) {// 先判断表模型是否为空,如果不是则先清除表
	                       JFrame f = new JFrame();
	                    f.remove(tablefour);
	                   }
					ResultSet rs = admindao.check_borrow_message(dbcon.getCon());
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
						               try {
						                  if (tablefour != null) {// 先判断表模型是否为空,如果不是则先清除表
						                       JFrame f = new JFrame();
						                    f.remove(tablefour);
						                   }
						                   // 表赋值
						                   String[] columnNames = { "1", "2", "3", "4", "5" };
						                   int chang = rs.getMetaData().getColumnCount();
						               		int kuan = 50;
						                   String[][] cells = new String[kuan][chang];
						                   try{
						                	   ArrayList<String> one = new ArrayList<String>();
						                	   ArrayList<String> two = new ArrayList<String>();
						                	   ArrayList<String> three = new ArrayList<String>();
						                	   ArrayList<String> four = new ArrayList<String>();
						                	   ArrayList<String> five = new ArrayList<String>();
						                	   
						                	   while(rs.next()){
						                		   one.add(rs.getString("card_no"));
						                		   two.add(rs.getString("book_no"));
						                		   three.add(rs.getString("book_name"));
						                		   four.add(rs.getString("borrow_date"));
						                		   five.add(rs.getString("return_date"));
						                	   }
						                	   for (int longu = 0; longu < kuan; longu++){
						                			   cells[longu][0] = one.get(longu);
						                			   cells[longu][1] = two.get(longu);
						                			   cells[longu][2] = three.get(longu);
						                			   cells[longu][3] = four.get(longu);
						                			   cells[longu][4] = five.get(longu);
						                	   }
						               		}catch(Exception fucku){
						               			fucku.getStackTrace();
						               		}				               		
						                  DefaultTableModel defaultmodel = new DefaultTableModel(cells, columnNames);
						                  tablefour = new JTable(defaultmodel);
						                   tablefour.setAutoCreateRowSorter(true);
						                   scrollPane.setViewportView(tablefour);// 添加滚动条
						               } catch (Exception ex) {
						               }
						           }
						       });
						       button.setBounds(260, 366, 63, 23);
						       frameone.getContentPane().add(button);
						       frameone.getContentPane().add(tablefour);
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
		
		JButton button_2 = new JButton("\u6570\u636E\u5907\u4EFD");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					boolean can_or_no = admindao.data_backup(dbcon.getCon());
					if(can_or_no)
						JOptionPane.showMessageDialog(null, "备份成功");
					else
						JOptionPane.showMessageDialog(null, "备份失败");
				}catch(Exception fuckagain){
					fuckagain.getStackTrace();
					JOptionPane.showMessageDialog(null, "备份失败");
				}
				
			}
		});
		
		JButton button_3 = new JButton("\u6570\u636E\u6062\u590D");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					boolean fuck_or_not = admindao.data_restore(dbcon.getCon());
					if(fuck_or_not)
						JOptionPane.showMessageDialog(null, "数据恢复成功");
					else
						JOptionPane.showMessageDialog(null, "数据恢复失败");
				}catch(Exception fuckyou){
					fuckyou.getStackTrace();
					JOptionPane.showMessageDialog(null, "数据恢复失败");
				}
			}
		});
		
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setText("\u5220\u9664\u5DF2\u5165\u5E93\u56FE\u4E66\uFF1A");
		
		JTextPane txtpnid_1 = new JTextPane();
		txtpnid_1.setText("\u56FE\u4E66id");
		
		del_bookid = new JTextField();
		del_bookid.setColumns(10);
		
		JButton button_4 = new JButton("\u5220\u9664");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String del_book_id = del_bookid.getText();
				try{
					boolean del_or_not = admindao.del_book(dbcon.getCon(), del_book_id);
					if(del_or_not)
						JOptionPane.showMessageDialog(null, "图书删除成功");
					else 
						JOptionPane.showMessageDialog(null, "图书删除失败");
				}catch(Exception del){
					del.getStackTrace();
					JOptionPane.showMessageDialog(null, "图书删除失败");
				}
				
			}
		});
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setText("\u56FE\u4E66\u4FE1\u606F\u4FEE\u6539");
		
		JTextPane textPane_10 = new JTextPane();
		textPane_10.setText("  \u56FE\u4E66\u7F16\u53F7");
		
		JTextPane textPane_11 = new JTextPane();
		textPane_11.setText("\u8981\u4FEE\u6539\u7684\u4FE1\u606F");
		
		JTextPane textPane_12 = new JTextPane();
		textPane_12.setText("   \u4FEE\u6539\u503C");
		
		update_bookno = new JTextField();
		update_bookno.setColumns(10);
		
		update_booktype = new JTextField();
		update_booktype.setColumns(10);
		
		update_bookmessage = new JTextField();
		update_bookmessage.setColumns(10);
		
		JButton button_5 = new JButton("\u786E\u8BA4\u4FEE\u6539");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String update_bookid = update_bookno.getText();
				String update_booktypecode = update_booktype.getText();
				String update_bookmes = update_bookmessage.getText();
				
				try{
					boolean update_or_not = admindao.update_bookmessage(dbcon.getCon(), update_bookid, update_booktypecode, update_bookmes);
					if(update_or_not)
						JOptionPane.showMessageDialog(null, "图书信息修改成功");
					else
						JOptionPane.showMessageDialog(null, "图书信息修改失败");
				}catch(Exception updatebok){
					updatebok.getStackTrace();
					JOptionPane.showMessageDialog(null, "图书信息修改失败");
				}
				
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(59)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(check_bookmessage, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(check_usermessage, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(50)
							.addComponent(button))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textPane_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(book_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(txtpnid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(del_user_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnNewButton))
										.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(book_no, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textPane_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(book_author, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textPane_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(book_price, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textPane_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(book_code, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textPane_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(book_amount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(45)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(45)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(button_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
												.addComponent(textPane_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(textPane_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(txtpnid_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(del_bookid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addGap(6)
													.addComponent(button_4))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(textPane_12, Alignment.LEADING)
														.addComponent(textPane_10)
														.addComponent(textPane_11))
													.addGap(22)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(update_bookmessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(update_booktype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(update_bookno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(89)
											.addComponent(button_5)))))))
					.addContainerGap(122, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(396, Short.MAX_VALUE)
					.addComponent(button_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_2)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(check_usermessage)
						.addComponent(btnNewButton_1))
					.addGap(51)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(check_bookmessage)
						.addComponent(button_1))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtpnid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textPane_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtpnid_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(del_user_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnNewButton))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(del_bookid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(button_4)))))
					.addGap(18)
					.addComponent(textPane_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(book_no, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textPane_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(book_price, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textPane_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(textPane_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(book_code, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(book_amount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textPane_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(book_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textPane_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(book_author, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(update_bookno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textPane_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textPane_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(update_booktype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(update_bookmessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textPane_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_5)))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_2)
						.addComponent(button_3))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
