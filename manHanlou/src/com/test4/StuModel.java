package com.test4;

import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.nio.channels.SelectableChannel;
import java.sql.*;
import java.util.*;

public class StuModel extends AbstractTableModel{
    Vector rowData,columnNames;
    PreparedStatement ps=null;
    Connection ct=null;
    ResultSet rs=null;
    public StuModel(){
    	String sql="";
    	this.getData(sql,0);		
    }
    public StuModel(String sql,int key){
    	this.getData(sql,key);
    }	
    public void getData(String sql,int key){

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
    	switch (key) {
		case 0:
			if (sql=="") {
				sql="select * from stu";
			}
	    	ps=ct.prepareStatement(sql);
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
			break;
		case 1:
		case 2:
		case 3:
	    	ps=ct.prepareStatement(sql);
	    	ps.executeUpdate();		
			break;
		}
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
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return ((Vector)this.rowData.get(row)).get(column);
	}

}
