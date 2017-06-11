package com.admin.view;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class show_message_table {
   private JFrame frame;
  private JTable table;
  private JScrollPane scrollPane;
  ResultSet rs;
  public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
          public void run() {
              try {
            	  show_message_table window = new show_message_table();
                  window.frame.setVisible(true);
             } catch (Exception e) {
                   e.printStackTrace();
              }
           }
      });
      }
   public show_message_table() {
       initialize();
   }
   private void initialize() {
       frame = new JFrame();
      frame.setBounds(100, 100, 582, 437);
      frame.getContentPane().setLayout(null);
      scrollPane = new JScrollPane();
       scrollPane.setBounds(14, 13, 536, 337);
       frame.getContentPane().add(scrollPane);
       JButton button = new JButton("\u67E5\u627E"); // 查找
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               Demo1 s = new Demo1();
               try {
                   if (table != null) {// 先判断表模型是否为空,如果不是则先清除表
                       JFrame f = new JFrame();
                       f.remove(table);
                   }
                   table = s.search(rs);// 表赋值
                   table.setAutoCreateRowSorter(true);
                   scrollPane.setViewportView(table);// 添加滚动条
               } catch (Exception ex) {
               }
           }
       });
       button.setBounds(260, 366, 63, 23);
       frame.getContentPane().add(button);
       frame.addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosed(WindowEvent e) {
               frame.dispose();
           }
       });
      frame.setVisible(true);
   }
}
