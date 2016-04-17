package com.test1;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;

public class Demo1_1 extends JFrame{
    Vector rowData,columnNames;
    JTable jt=null;
    JScrollPane jsp=null;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      Demo1_1 demo1_1=new Demo1_1();
	}
	public Demo1_1(){
		columnNames=new Vector();
		columnNames.add("学号");
		columnNames.add("名字");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("籍贯");
		columnNames.add("系别");
		Vector hang=new Vector();
		hang.add("sp001");
		hang.add("孙悟空");
		hang.add("男");
		hang.add("500");
		hang.add("花果山");
		hang.add("少林寺");
		Vector hang1=new Vector();
		hang1.add("sp002");
		hang1.add("猪八戒");
		hang1.add("男");
		hang1.add("400");
		hang1.add("高老庄");
		hang1.add("少林寺");
		rowData=new Vector();
		rowData.add(hang);
		rowData.add(hang1);
		jt=new JTable(rowData,columnNames);
		jsp=new JScrollPane(jt);
		this.add(jsp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}
}
