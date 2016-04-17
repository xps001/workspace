package com.test4;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class StuManager extends JFrame implements ActionListener{
	JPanel jp1,jp2=null;
	JTextField jtf=null;
	JButton jb1,jb2,jb3,jb4=null;
	JLabel jl1=null;
	JTable jt1=null;
	JTable jt2=null;
	JScrollPane jsp=null;
	StuModel sm=null;
    Vector rowData,columnNames;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StuManager demo1_3=new StuManager();
	}
public StuManager(){
	
	jp1=new JPanel();
	jtf=new JTextField(10);
	jb1=new JButton("查询");
	jb1.addActionListener(this);
	jl1=new JLabel("请输入名字");
	jp1.add(jtf);
	jp1.add(jb1);
	jp1.add(jl1);
	
	jp2=new JPanel();
	jb2=new JButton("添加");
	jb2.addActionListener(this);
	jb3=new JButton("修改");
	jb3.addActionListener(this);
	jb4=new JButton("删除");
	jb4.addActionListener(this);
	jp2.add(jb2);
	jp2.add(jb3);
	jp2.add(jb4);
	
	sm=new StuModel();
	jt2=new JTable(sm);
	jsp=new JScrollPane(jt2);
    this.add(jsp);
	this.add(jp1,"North");
	this.add(jp2,"South");
	this.setSize(400,300);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);		
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if (e.getSource()==jb1) {
		String strName=this.jtf.getText().trim();//过滤空字符串
		String sql="select * from stu where stuName='"+strName+"'";
		sm=new StuModel(sql,0);
	}else if(e.getSource()==jb2){
		StuAddDialog sad=new StuAddDialog(this, "添加", true);//f非模式对话框会阻塞
		sm=new StuModel();
	}else if(e.getSource()==jb3){

		int row=this.jt2.getSelectedRow();
		if (row==-1) {
			JOptionPane.showMessageDialog(this,"请选择一行");
			return;
		}
		StuUpdDialog sad=new StuUpdDialog(this, "修改", true,sm,row);//f非模式对话框会阻塞
		sm=new StuModel();
	}else if(e.getSource()==jb4){
		int row=this.jt2.getSelectedRow();
		if (row==-1) {
			JOptionPane.showMessageDialog(this,"请选择一行");
			return;
		}
		String strId=(String)sm.getValueAt(row, 0);
	    String sql="delete from stu where stuId='"+strId+"'";
		sm=new StuModel(sql,3);
		sm=new StuModel();//这个是什么屌东西
	}
	jt2.setModel(sm);
}
}
