package com.user.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.user.dao.userdao;
import com.user.util.DBConnection;

import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class userlogin extends JFrame {
	
	DBConnection dbcon = new DBConnection();
	userdao admindao = new userdao();
	

	private JPanel contentPane;
	private JTextField userloginid;
	private JTextField userloginpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userlogin frame = new userlogin();
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
	public userlogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF\uFF08\u666E\u901A\u7528\u6237\u767B\u9646\uFF09");
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText(" \u4F60\u7684\u7528\u6237\u540D");
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("\u4F60\u7684\u8D26\u53F7\u5BC6\u7801");
		
		userloginid = new JTextField();
		userloginid.setColumns(10);
		
		userloginpassword = new JTextField();
		userloginpassword.setColumns(10);
		
		JButton button = new JButton("\u7528\u6237\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String user_login_id = userloginid.getText();
				String user_login_password = userloginpassword.getText();
				ResultSet rs = null;
				String sql = "select * from bookuserone where user_id = " + user_login_id;
				try{
				PreparedStatement statement = dbcon.getCon().prepareStatement(sql);
				 rs = statement.executeQuery();
				}catch(Exception fuck){
					fuck.getStackTrace();
					JOptionPane.showMessageDialog(null, "µÇÂ½Ê§°Ü");
				}
				String get_id = null;
				String get_password = null;
				try{
					while(rs.next()){
						get_id = rs.getString("user_id");
						get_password = rs.getString("user_password");
					}
				}catch(Exception lu){
					lu.getStackTrace();
				}
				boolean correct;
				if (user_login_id.equals(get_id) && user_login_password.equals(get_password))
					correct = true;
				else
					correct = false;
				
				if(correct){
					JOptionPane.showMessageDialog(null, "µÇÂ½³É¹¦");
				   userfirstframe userframe = new userfirstframe();
				   userframe.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "µÇÂ½Ê§°Ü");
			}
		});
		
		
		//ÓÃ»§×¢²á
		JButton button_1 = new JButton("\u7528\u6237\u6CE8\u518C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String user_logon_id = userloginid.getText();
				String user_logon_password = userloginpassword.getText();
				
				try{
					boolean yes_or_no;
					String sql = "insert into bookuserone (user_id, user_password) values(" + user_logon_id + ", '" + user_logon_password + "')";
					try{
						PreparedStatement statement = dbcon.getCon().prepareStatement(sql);
						statement.executeUpdate();
						yes_or_no = true;
					}catch(Exception v){
						v.getStackTrace();
						yes_or_no = false;
					}
					
					if (yes_or_no)
					JOptionPane.showMessageDialog(null, "×¢²á³É¹¦");
					else
						JOptionPane.showMessageDialog(null, "×¢²áÊ§°Ü");
				}catch(Exception v){
					v.getStackTrace();
					JOptionPane.showMessageDialog(null, "×¢²áÊ§°Ü");
				}
				
			}
		});
		
		
		//ÓÃ»§ÊäÈëÖØÖÃ
		JButton button_2 = new JButton("\u8F93\u5165\u91CD\u7F6E");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userloginid.setText("");
				userloginpassword.setText("");
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(textPane_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPane_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(userloginid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(userloginpassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(210, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addComponent(button_1)
					.addGap(32)
					.addComponent(button_2)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(222, Short.MAX_VALUE)
					.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(88))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(userloginid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(userloginpassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_2)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap(81, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
