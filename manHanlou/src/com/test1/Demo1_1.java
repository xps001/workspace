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
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");
		Vector hang=new Vector();
		hang.add("sp001");
		hang.add("�����");
		hang.add("��");
		hang.add("500");
		hang.add("����ɽ");
		hang.add("������");
		Vector hang1=new Vector();
		hang1.add("sp002");
		hang1.add("��˽�");
		hang1.add("��");
		hang1.add("400");
		hang1.add("����ׯ");
		hang1.add("������");
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
