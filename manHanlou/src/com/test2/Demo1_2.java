package com.test2;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;

public class Demo1_2 extends JFrame{
    Vector rowData,columnNames;
    JTable jt=null;
    JScrollPane jsp=null;
    PreparedStatement ps=null;
    Connection ct=null;
    ResultSet rs=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo1_2 demo1_2=new Demo1_2();
	}
	public Demo1_2(){
		rowData=new Vector<>();
		columnNames=new Vector<>();
		columnNames.add("学号");
		columnNames.add("名字");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("籍贯");
		columnNames.add("系别");
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");    
		ct=DriverManager.getConnection
				("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=students","sa","xpslovedkl");
		ps=ct.prepareStatement("select * from stu");
		rs=ps.executeQuery();
		while (rs.next()) {
			Vector hang=new Vector();
			hang.add(rs.getString(1));
			hang.add(rs.getString(2));
			hang.add(rs.getString(3));
			hang.add(rs.getInt(4));
			hang.add(rs.getString(5));
			hang.add(rs.getString(6));

			rowData.add(hang);
		}
		jt=new JTable(rowData,columnNames);
		jsp=new JScrollPane(jt);
		this.add(jsp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace(); 
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	}
} 
