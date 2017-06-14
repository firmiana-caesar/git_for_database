package com.admin.view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.*;
import javax.swing.*;

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
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.net.URL;
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
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF\uFF08\u7BA1\u7406\u5458\u767B\u9646\uFF09");
		
		adminid = new JTextField();
		adminid.setColumns(10);
		
		JLabel label = new JLabel("\u7BA1\u7406\u5458\u8D26\u53F7");
		
		JLabel label_1 = new JLabel("\u7BA1\u7406\u5458\u5BC6\u7801");
		
		//½øÐÐµÇÂ½µÄÏàÓ¦ÏìÓ¦ÊÂ¼þ
		JButton jb_login = new JButton("\u767B\u9646");
		jb_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String admin_id = adminid.getText();
				String admin_password = new String(adminpassword.getPassword());
				int i = Integer.valueOf(admin_id).intValue();
				admin admin = new admin(i, admin_password);
				try {
					
					if(admin_id == null || admin_password == null)
						JOptionPane.showMessageDialog(null, "µÇÂ¼Ê§°Ü");
					
					admin currentadmin = admindao.login(dbcon.getCon(), admin);
					if (currentadmin != null)
						{
							JOptionPane.showMessageDialog(null, "µÇÂ½³É¹¦");
							visibleframe.setVisible(false);
							adminmanagement adminmanagement = new adminmanagement();
							adminmanagement.setVisible(true);
						}
					else
						JOptionPane.showMessageDialog(null, "µÇÂ¼Ê§°Ü");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "µÇÂ¼Ê§°Ü");
				}
			}
		});
		
		//½øÐÐÖØÖÃµÄÏìÓ¦ÊÂ¼þ
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
					
					if(admin_id == null || admin_password == null)
						JOptionPane.showMessageDialog(null, "×¢²áÊ§°Ü");
					
					if (yes_or_no)
						{
							JOptionPane.showMessageDialog(null, "×¢²áÊ§°Ü");
						}
					else
						JOptionPane.showMessageDialog(null, "×¢²á³É¹¦");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "×¢²áÊ§°Ü");
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addComponent(label_1)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(47)
							.addComponent(jb_login)))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(jb_logon)
							.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
							.addComponent(jb_reset)
							.addGap(85))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(adminpassword, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
								.addComponent(adminid, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
								.addComponent(lblNewLabel))
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
