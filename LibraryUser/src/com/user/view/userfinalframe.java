package com.user.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.user.dao.userdao;
import com.user.util.DBConnection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class userfinalframe extends JFrame {

	DBConnection dbcon = new DBConnection();
	userdao admindao = new userdao();
	
	private JPanel contentPane;
	private JTextField borrow_or_not_id;
	private JTable tableone = new JTable();
	private JTextField borrower_card_id;
	private JTable tablefour = new JTable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userfinalframe frame = new userfinalframe();
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
	public userfinalframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("\u6301\u6709\u501F\u4E66\u5361\u7528\u6237\uFF0C\u6B22\u8FCE\u4F7F\u7528\u672C\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		
		JButton button = new JButton("\u67E5\u8BE2\u56FE\u4E66\u4FE1\u606F");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ResultSet rs = userdao.user_check_book(dbcon.getCon());
						//ResultSet rs = admindao.check_book_message(dbcon.getCon());
					
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
						                   String[] columnNames = { "图书编号", "图书作者", "图书价格", "图书编码", "剩余数量","书名" };
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
		
		JButton button_1 = new JButton("\u67E5\u8BE2\u501F\u9605\u4FE1\u606F");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String borrow_id = borrower_card_id.getText();
				
				try{
					if (tablefour != null) {// 先判断表模型是否为空,如果不是则先清除表
	                       JFrame f = new JFrame();
	                    f.remove(tablefour);
	                   }
					ResultSet rs = admindao.check_borrow_message(dbcon.getCon(), borrow_id);
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
						                   String[] columnNames = { "借书卡账号", "图书编号", "书名", "借阅日期", "归还日期" };
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
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("\u501F\u8FD8\u4E66\u7F16\u53F7");
		
		borrow_or_not_id = new JTextField();
		borrow_or_not_id.setColumns(10);
		
		JButton button_2 = new JButton("\u501F\u4E66");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String borrow_book_id = borrow_or_not_id.getText();
				String borrowercard_id  =  borrower_card_id.getText(); 
				int bookcount = 0;
				boolean borrow = false;
				try{
				bookcount = userdao.check_book_acount(dbcon.getCon(), borrow_book_id);
				}catch(Exception uuu){
					uuu.getStackTrace();
					JOptionPane.showMessageDialog(null, "查询失败");
				}
				
				if (bookcount != 0){
					try{
						borrow = userdao.user_borrow_book(dbcon.getCon(), borrowercard_id, borrow_book_id, bookcount-1);
					}catch(Exception fuckyou)
					{
						fuckyou.getStackTrace();
						borrow = false;
					}
					}
				
				if(borrow)
					JOptionPane.showMessageDialog(null, "借书成功");
				else
					JOptionPane.showMessageDialog(null, "借书失败");
					
				
			}
		});
		
		JButton button_3 = new JButton("\u8FD8\u4E66");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String return_book_id = borrow_or_not_id.getText();
				String returnercard_id  =  borrower_card_id.getText(); 
				int bookcount = 0;
				boolean returnbook = false;
				try{
				bookcount = userdao.check_book_acount(dbcon.getCon(), return_book_id);
				}catch(Exception uuu){
					uuu.getStackTrace();
					JOptionPane.showMessageDialog(null, "查询失败");
				}
				
				
					try{
						returnbook  = userdao.user_return_book(dbcon.getCon(), returnercard_id, return_book_id, bookcount + 1);
					}catch(Exception fuckyou)
					{
						fuckyou.getStackTrace();
						returnbook = false;
					}
					
				
				if(returnbook)
					JOptionPane.showMessageDialog(null, "还书成功");
				else
					JOptionPane.showMessageDialog(null, "还书失败");
				
			}
		});
		
		borrower_card_id = new JTextField();
		borrower_card_id.setColumns(10);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("\u501F\u4E66\u5361\u8D26\u53F7");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(button_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button_3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textPane_1)
								.addComponent(textPane_2))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(borrower_card_id, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(borrow_or_not_id, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(175, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(borrower_card_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(borrow_or_not_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_2)
						.addComponent(button_3)
						.addComponent(button_1))
					.addContainerGap(76, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
