package com.test3;

import java.awt.*;
import java.rmi.activation.ActivationMonitor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import java.awt.event.*;

public class StuAddDialog extends JDialog implements ActionListener{
     JLabel jl1=null;
     JLabel jl2=null;
     JLabel jl3=null;
     JLabel jl4=null;
     JLabel jl5=null;
     JLabel jl6=null;
     
     JTextField jtf1=null;
     JTextField jtf2=null;
     JTextField jtf3=null;
     JTextField jtf4=null;
     JTextField jtf5=null;
     JTextField jtf6=null;
     
     JButton jb1=null;
     JButton jb2=null;
     
     JPanel jp1=null;
     JPanel jp2=null;
     JPanel jp3=null;
     public StuAddDialog(Frame owner,String title,boolean modal){
    	super(owner,title,modal);//模态和非模态
    	jl1=new JLabel("学号");
    	jl2=new JLabel("名字");  
    	jl3=new JLabel("性别"); 
    	jl4=new JLabel("年龄"); 
    	jl5=new JLabel("籍贯"); 
    	jl6=new JLabel("系别"); 
    	
    	jtf1=new JTextField();
    	jtf2=new JTextField();
    	jtf3=new JTextField();
    	jtf4=new JTextField();
    	jtf5=new JTextField();
    	jtf6=new JTextField();
    	
    	jb1=new JButton("添加");
    	jb2=new JButton("取消");	
    	
    	jp1=new JPanel();
    	jp2=new JPanel();
    	jp3=new JPanel();
    	
    	jp1.setLayout(new GridLayout(6, 1));
    	jp2.setLayout(new GridLayout(6, 1));
    	
    	jp1.add(jl1);
    	jp1.add(jl2);
    	jp1.add(jl3);
    	jp1.add(jl4);
    	jp1.add(jl5);    	 
    	jp1.add(jl6);    	 
    	
    	jp2.add(jtf1);
    	jp2.add(jtf2);
    	jp2.add(jtf3);
    	jp2.add(jtf4);
    	jp2.add(jtf5);
    	jp2.add(jtf6);
    	
    	jp3.add(jb1);
    	jp3.add(jb2);
    	
    	this.add(jp1,BorderLayout.WEST);
    	this.add(jp2,BorderLayout.CENTER);
    	this.add(jp3,BorderLayout.SOUTH);
    	
    	jb1.addActionListener(this);
    	
    	this.setSize(300,250);
    	this.setVisible(true);    	
     }
	@Override
	public void actionPerformed(ActionEvent e) {
	    PreparedStatement ps=null;
	    Connection ct=null;
	    ResultSet rs=null;
		String sql="insert into stu values(?,?,?,?,?,?)";
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
		    try {
		    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");    
		    	ct=DriverManager.getConnection
		    			("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=students","sa","xpslovedkl");
		    	ps=ct.prepareStatement(sql);
		    	ps.setString(1, jtf1.getText().trim());
		    	ps.setString(2, jtf2.getText().trim());
		    	ps.setString(3, jtf3.getText().trim());
		    	ps.setString(4, jtf4.getText().trim());
		    	ps.setString(5, jtf5.getText().trim());
		    	ps.setString(6, jtf6.getText().trim());		    	
		    	ps.executeUpdate();
		        this.dispose();
		    } catch (Exception e1) {
		    	// TODO Auto-generated catch block
		    	e1.printStackTrace(); 
		    }finally {
		    	try {
		    		if(rs!=null){
		    			rs.close();			
		    		}
		    		if(ps!=null){
		    			ps.close();	
		    		} 
		    		if(ct!=null){
		    			ct.close();		
		    		}
		    	} catch (SQLException e1) {
		    		// TODO Auto-generated catch block
		    		e1.printStackTrace();
		    	}
		    	
		    }   
		}
	}

}
