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
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class userfirstframe extends JFrame {
	
	DBConnection dbcon = new DBConnection();
	userdao admindao = new userdao();

	private JPanel contentPane;
	private JTextField card_id;
	private JTable tableone = new JTable();
	private JTextField user_id;
	
	public String user_id_for_use;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userfirstframe frame = new userfirstframe();
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
	public userfirstframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
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
		
		JTextPane textPane = new JTextPane();
		textPane.setText("\u501F\u4E66\u5361\u8D26\u53F7");
		
		card_id = new JTextField();
		card_id.setColumns(10);
		
		JButton button_1 = new JButton("\u501F\u4E66\u5361\u767B\u9646");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userid = user_id.getText();
				String usercardno = card_id.getText();
				ResultSet rs = null;
				String sql = "select * from bookcard where card_userno = " + userid;
				try{
				PreparedStatement statement = dbcon.getCon().prepareStatement(sql);
				 rs = statement.executeQuery();
				}catch(Exception fuck){
					fuck.getStackTrace();
					JOptionPane.showMessageDialog(null, "登陆失败");
				}
				String get_id = null;
				String get_password = null;
				try{
					while(rs.next()){
						get_id = rs.getString("card_userno");
						get_password = rs.getString("card_no");
					}
				}catch(Exception lu){
					lu.getStackTrace();
				}
				boolean correct;
				if (userid.equals(get_id) && usercardno.equals(get_password))
					correct = true;
				else
					correct = false;
				
				if(correct)
					{
						JOptionPane.showMessageDialog(null, "登陆成功");
						user_id_for_use = userid;
						userfinalframe finalframe = new userfinalframe();
						finalframe.setVisible(true);
					}
				else
					JOptionPane.showMessageDialog(null, "登陆失败");
				
			}
		});
		
		JButton button_2 = new JButton("\u501F\u4E66\u5361\u6CE8\u518C");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cardid = card_id.getText();
				String userid = user_id.getText();
				try{
					boolean yes_or_no;
					String sqlone = "update bookuserone set user_cardno = '" + cardid  +  "' where user_id = " + userid;
					String sqltwo = "insert into bookcard(card_no, card_userno) values ('" + cardid +"'," + userid +")";
					try{
						PreparedStatement statement = dbcon.getCon().prepareStatement(sqlone);
						statement.executeUpdate();
						statement = dbcon.getCon().prepareStatement(sqltwo);
						statement.executeUpdate();
						yes_or_no = true;
					}catch(Exception v){
						v.getStackTrace();
						yes_or_no = false;
					}
					if (yes_or_no)
					JOptionPane.showMessageDialog(null, "注册成功");
					else
						JOptionPane.showMessageDialog(null, "注册失败");
				}catch(Exception v){
					v.getStackTrace();
					JOptionPane.showMessageDialog(null, "注册失败");
				}
			}
		});
		
		JButton button_3 = new JButton("\u8F93\u5165\u91CD\u7F6E");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user_id.setText("");
				card_id.setText("");
				//card_password.setText("");
			}
		});
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText(" \u7528\u6237\u8D26\u53F7");
		
		user_id = new JTextField();
		user_id.setColumns(10);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("\u666E\u901A\u7528\u6237\u6B22\u8FCE\u4F7F\u7528\u672C\u7CFB\u7EDF\uFF0C\u5728\u6CE8\u518C\u501F\u4E66\u5361\u4E4B\u524D\u60A8\u53EA\u80FD\u67E5\u8BE2\u56FE\u4E66\u4FE1\u606F");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(button)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(card_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_3))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(user_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(135, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(356, Short.MAX_VALUE)
					.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(button)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(card_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(user_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button_2)
						.addComponent(button_3))
					.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
					.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
