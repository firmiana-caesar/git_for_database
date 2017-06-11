package com.admin.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.admin.model.admin;
import com.admin.util.DBConnection;
import com.admin.dao.admindao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class adminlogin extends JFrame {
	
	DBConnection dbcon = new DBConnection();
	admindao admindao = new admindao();
	
	private JPanel contentPane;
	private JTextField adminid;
	private JPasswordField adminpassword;

	/**
	 * Launch the application.
	 */
	private static adminlogin visibleframe;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminlogin frame = new adminlogin();
					visibleframe = frame;
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
	public adminlogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u8FD9\u662F\u4E00\u4E2A\u767B\u9646\u754C\u9762");
		
		adminid = new JTextField();
		adminid.setColumns(10);
		
		JLabel label = new JLabel("\u7BA1\u7406\u5458\u8D26\u53F7");
		
		JLabel label_1 = new JLabel("\u7BA1\u7406\u5458\u5BC6\u7801");
		
		//进行登陆的相应响应事件
		JButton jb_login = new JButton("\u767B\u9646");
		jb_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String admin_id = adminid.getText();
				String admin_password = new String(adminpassword.getPassword());
				int i = Integer.valueOf(admin_id).intValue();
				admin admin = new admin(i, admin_password);
				try {
					admin currentadmin = admindao.login(dbcon.getCon(), admin);
					if (currentadmin != null)
						{
							JOptionPane.showMessageDialog(null, "登陆成功");
							visibleframe.setVisible(false);
							adminmanagement adminmanagement = new adminmanagement();
							adminmanagement.setVisible(true);
						}
					else
						JOptionPane.showMessageDialog(null, "登录失败");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "登录失败");
				}
			}
		});
		
		//进行重置的响应事件
		JButton jb_reset = new JButton("\u91CD\u7F6E");
		jb_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminid.setText("");
				adminpassword.setText("");
			}
		});
		
		adminpassword = new JPasswordField();
		
		JButton jb_logon = new JButton("\u7BA1\u7406\u5458\u6CE8\u518C");
		jb_logon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String admin_id = adminid.getText();
				String admin_password = new String (adminpassword.getPassword());
				try {
					boolean yes_or_no = admindao.logon(dbcon.getCon(), admin_id, admin_password);
					if (yes_or_no)
						{
							JOptionPane.showMessageDialog(null, "注册失败");
						}
					else
						JOptionPane.showMessageDialog(null, "注册成功");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "注册失败");
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(166, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(162))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addComponent(label_1)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(47)
							.addComponent(jb_login)))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(jb_logon)
							.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
							.addComponent(jb_reset)
							.addGap(85))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(adminpassword, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
								.addComponent(adminid, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
							.addGap(125))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(adminid, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(adminpassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(jb_login)
						.addComponent(jb_reset)
						.addComponent(jb_logon))
					.addGap(22))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
