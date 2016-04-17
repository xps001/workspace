
package com.test4;

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

public class StuUpdDialog extends JDialog implements ActionListener{
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
     
     StuModel sm=null;   
     public StuUpdDialog(Frame owner,String title,boolean modal,StuModel sm,int rows){
    	super(owner,title,modal);
    	jl1=new JLabel("ѧ��");
    	jl2=new JLabel("����");  
    	jl3=new JLabel("�Ա�"); 
    	jl4=new JLabel("����"); 
    	jl5=new JLabel("����"); 
    	jl6=new JLabel("ϵ��"); 
    	
    	jtf1=new JTextField();
    	jtf1.setText((String)sm.getValueAt(rows, 0));
    	jtf1.setEditable(false);
    	jtf2=new JTextField();
    	jtf2.setText((String)sm.getValueAt(rows, 1));
    	jtf3=new JTextField();
    	jtf3.setText((String)sm.getValueAt(rows, 2));
    	jtf4=new JTextField();
    	jtf4.setText((String)sm.getValueAt(rows, 3).toString());
    	jtf5=new JTextField();
    	jtf5.setText((String)sm.getValueAt(rows, 4));
    	jtf6=new JTextField();
    	jtf6.setText((String)sm.getValueAt(rows, 5));
    	
    	jb1=new JButton("ȷ��");
    	jb2=new JButton("ȡ��");	
    	
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
		String stuId=jtf1.getText().trim();
		String stuName=jtf2.getText().trim();
		String stuSex=jtf3.getText().trim();
		String stuAge=jtf4.getText().trim();
		String stuJg=jtf5.getText().trim();
		String stuDept=jtf6.getText().trim();
		String sql="update stu set stuName=?,stuSex=?,stuAge=?,stuJg=?,stuDept=? where stuId=?";
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			sql=sql.replaceFirst("\\?", "'"+jtf2.getText().trim()+"'");
	    	sql=sql.replaceFirst("\\?", "'"+jtf3.getText().trim()+"'");
	    	sql=sql.replaceFirst("\\?", "'"+jtf4.getText().trim()+"'");
	    	sql=sql.replaceFirst("\\?", "'"+jtf5.getText().trim()+"'");
	    	sql=sql.replaceFirst("\\?", "'"+jtf6.getText().trim()+"'");
	    	sql=sql.replaceFirst("\\?", "'"+jtf1.getText().trim()+"'");
	    	sm=new StuModel(sql,2);
		    this.dispose();
		     
		}
	}

}
