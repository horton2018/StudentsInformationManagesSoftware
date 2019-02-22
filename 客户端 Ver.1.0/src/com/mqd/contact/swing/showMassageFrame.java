package com.mqd.contact.swing;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mqd.contact.dao.DAOimpl;



   //import com.mqd.contact.dao.DAOimpl1;

import pojo.person;
import pojo.students;
import pojo.teacher;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import java.awt.FlowLayout;

public class showMassageFrame extends JDialog{
	
	private static JTextField NameTextField;
	private static JTextField passwordTextField;
	private static JTextField IdTextField;
	private static JTextField MajorTextField;
	private static JTextField ClassNameTextField;
	private static JTextField SexTextField;
	private static JTextArea AddressTextArea;
	private static FixedTable table;			 //学生信息表格
	private static JTextField DormitoryTextField;
	private static JTextField TelephoneTextField;
		
	public showMassageFrame(students stu,person identity) {				//双击表格查看详细信息调用构造方法
		students students = DAOimpl.search1(4, 9, stu, identity);
		students stu1 = students;
		this.setTitle("详细信息");  //设置窗体标题
		this.setSize(500, 300);     //设置窗体大小
		this.setLocationRelativeTo(null);//设置窗体居中显示
		JPanel textPanel = new JPanel();
		getContentPane().add(textPanel, BorderLayout.NORTH);
		textPanel.setLayout(new GridLayout(3, 4));
		
		JLabel IdLabel = new JLabel("学 号");
		textPanel.add(IdLabel);
		
		IdTextField = new JTextField();
		textPanel.add(IdTextField);
		IdTextField.setColumns(15);
		IdTextField.setText(stu1.getId());
		
		JLabel NameLabel = new JLabel("姓 名");
		textPanel.add(NameLabel);
		
		NameTextField = new JTextField();
		textPanel.add(NameTextField);
		NameTextField.setColumns(15);
		NameTextField.setText(stu1.getName());
		
		JLabel MajorLabel = new JLabel("专 业");
		textPanel.add(MajorLabel);
		
		MajorTextField = new JTextField();
		textPanel.add(MajorTextField);
		MajorTextField.setColumns(20);
		MajorTextField.setText(stu1.getMajorName());
		
		JLabel ClassNameLabel = new JLabel("班  级");
		textPanel.add(ClassNameLabel);
		
		ClassNameTextField = new JTextField();
		textPanel.add(ClassNameTextField);
		ClassNameTextField.setColumns(15);
		ClassNameTextField.setText(stu1.getClassName());
		
		JLabel SexLabel = new JLabel("性 别");
		textPanel.add(SexLabel);
		
		SexTextField = new JTextField();
		textPanel.add(SexTextField);
		SexTextField.setColumns(15);
		SexTextField.setText(stu1.getSex());
		
		JPanel tablePanel = new JPanel();
		getContentPane().add(tablePanel, BorderLayout.CENTER);
		
		
		
		JPanel buttomPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttomPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(buttomPanel, BorderLayout.SOUTH);
		
		JButton CloseButton = new JButton("关 闭");
		buttomPanel.add(CloseButton);
		
		table = new FixedTable(); // 创建指定表格模型的表格
		table.setCellEditable(false);//让表格不可编辑
		JScrollPane scrollPane = new JScrollPane(table);// 给表格添加下拉框
		tablePanel.add(scrollPane, BorderLayout.CENTER);// 表格编办添加下拉框面板
		
		//初始化表格数据
		
		students NameList = stu1;
		table.setModel(assmbledModelSoure(NameList));
		
		setVisible(true);		//设置窗体可见
		
		CloseButton.addActionListener(new ActionListener() {		// 关闭按钮添加动作监听
			public void actionPerformed(ActionEvent e) {// 当点击时
				dispose();
			}// actionPerformed()结束
		});// CloseButton.addActionListener()结束
		
		}


	public showMassageFrame() {						//点击添加按钮调用构造方法
		
		this.setTitle("添加学生详细信息");  //设置窗体标题
		this.setSize(400, 300);     //设置窗体大小
		this.setLocationRelativeTo(null);//设置窗体居中显示
		JPanel textPanel = new JPanel();
		JPanel textPanelDown = new JPanel();
		getContentPane().add(textPanel, BorderLayout.NORTH);
		getContentPane().add(textPanelDown,BorderLayout.CENTER);
		textPanel.setLayout(new GridLayout(3, 4));
		textPanelDown.setLayout(new FlowLayout(0));
		
		JLabel IdLabel = new JLabel("学 号");
		textPanel.add(IdLabel);
		
		IdTextField = new JTextField();
		textPanel.add(IdTextField);
		IdTextField.setColumns(15);
		
		JLabel NameLabel = new JLabel("姓 名");
		textPanel.add(NameLabel);
		
		NameTextField = new JTextField();
		textPanel.add(NameTextField);
		NameTextField.setColumns(15);
		
		JLabel MajorLabel = new JLabel("专 业");
		textPanel.add(MajorLabel);
		
		MajorTextField = new JTextField();
		textPanel.add(MajorTextField);
		MajorTextField.setColumns(15);
		
		JLabel ClassNameLabel = new JLabel("班 级");
		textPanel.add(ClassNameLabel);
		
		ClassNameTextField = new JTextField();
		textPanel.add(ClassNameTextField);
		ClassNameTextField.setColumns(15);
		
		JLabel SexLabel = new JLabel("性 别");
		textPanel.add(SexLabel);
		
		SexTextField = new JTextField();
		textPanel.add(SexTextField);
		SexTextField.setColumns(15);
		
		JLabel Telephonelabel = new JLabel("电 话");
		textPanel.add(Telephonelabel);
		
		TelephoneTextField = new JTextField();
		TelephoneTextField.setColumns(15);
		textPanel.add(TelephoneTextField);
		
		
		JLabel address = new JLabel("家庭住址");
		textPanelDown.add(address);
		
		AddressTextArea = new JTextArea(3,35);
		//AddressTextArea.setColumns(50);
		textPanelDown.add(AddressTextArea);
		
		
		
		JPanel buttomPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttomPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.CENTER);
		getContentPane().add(buttomPanel, BorderLayout.SOUTH);
		
		
		JButton addButton = new JButton("添 加");
		buttomPanel.add(addButton);
		JButton CloseButton = new JButton("取 消");
		buttomPanel.add(CloseButton);
		
		addButton.addActionListener(new ActionListener() {		// 添加按钮添加动作监听
			public void actionPerformed(ActionEvent e) {// 当点击时
				boolean add = false;
				students stu = new students();
				stu.setMajorName(getMajorTextField());
				stu.setClassName(getClassNameTextField());
				stu.setId(getIdTextField());
				stu.setName(getNameTextField());
				stu.setSex(getSexTextField());
				stu.setTelephoneNumber(getTelephoneTextField());
				stu.setAddress(getAddressTextArea());
				add = DAOimpl.addStudents(stu);
				if(add) {
					JOptionPane.showMessageDialog(null,"添加成功","提示消息",JOptionPane.WARNING_MESSAGE); //弹出提示信息
					dispose();//关闭添加窗口
				}else {
					JOptionPane.showMessageDialog(null,"添加失败，请重新添加","提示消息",JOptionPane.WARNING_MESSAGE); //弹出提示信息
				}
				
			}// actionPerformed()结束
		});// addButton.addActionListener()结束

		CloseButton.addActionListener(new ActionListener() {		// 取消按钮添加动作监听
			public void actionPerformed(ActionEvent e) {// 当点击时
				dispose();
			}// actionPerformed()结束
		});// addButton.addActionListener()结束
		
		setVisible(true);		//设置窗体可见
		}

	public showMassageFrame(person LoginIdentity) {						//点击修改密码按钮调用构造方法
		
		this.setTitle("修改账号密码");  //设置窗体标题
		this.setSize(300, 200);     //设置窗体大小
		this.setLocationRelativeTo(null);//设置窗体居中显示
		JPanel textPanel = new JPanel();
		getContentPane().add(textPanel, BorderLayout.NORTH);
		textPanel.setLayout(new GridLayout(2, 4));
		
		JLabel IdLabel = new JLabel("账 号");
		textPanel.add(IdLabel);
		
		
		JLabel IdTestLabel = new JLabel(LoginIdentity.getId());
		textPanel.add(IdTestLabel);
		
		
		JLabel passwordLabel = new JLabel("密 码");
		textPanel.add(passwordLabel);
		
		passwordTextField = new JTextField();
		textPanel.add(passwordTextField);
		passwordTextField.setColumns(15);
		
		
		JPanel buttomPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttomPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.CENTER);
		getContentPane().add(buttomPanel, BorderLayout.CENTER);
		
		
		JButton changeButton = new JButton("修 改");
		buttomPanel.add(changeButton);
		JButton CloseButton = new JButton("取 消");
		buttomPanel.add(CloseButton);
		
		changeButton.addActionListener(new ActionListener() {		// 修改按钮添加动作监听
			public void actionPerformed(ActionEvent e) {// 当点击时
				boolean result = false;
				LoginIdentity.setPassword(getPasswordTextField());
				if(LoginIdentity instanceof teacher) {
					
					
					
					
					//result = DAOimpl1.changeUserPassword(LoginIdentity,"teacher");
				}else if(LoginIdentity instanceof students) {
					// = DAOimpl1.changeUserPassword(LoginIdentity,"student");					
				}
				
				
				
				
				
				if(result) {
					JOptionPane.showMessageDialog(null,"修改成功","提示消息",JOptionPane.WARNING_MESSAGE); //弹出提示信息
					dispose();//关闭添加窗口
				}else {
					JOptionPane.showMessageDialog(null,"修改失败，请重新修改","提示消息",JOptionPane.WARNING_MESSAGE); //弹出提示信息
				}
				
			}// actionPerformed()结束
		});// changeButton.addActionListener()结束

		CloseButton.addActionListener(new ActionListener() {		// 取消按钮添加动作监听
			public void actionPerformed(ActionEvent e) {// 当点击时
				dispose();
			}// actionPerformed()结束
		});// closeButton.addActionListener()结束
		
		setVisible(true);		//设置窗体可见
		}

	
	public showMassageFrame(students stu,byte changeNumber,person identity) {	//点击修改学生信息按钮调用构造方法
		students stu1 = DAOimpl.search1(4, 9, stu, identity);
		this.setTitle("修改详细信息");  //设置窗体标题
		this.setSize(500, 300);     //设置窗体大小
		this.setLocationRelativeTo(null);//设置窗体居中显示
		JPanel textPanel = new JPanel();
		getContentPane().add(textPanel, BorderLayout.NORTH);
		textPanel.setLayout(new GridLayout(3, 4));
		
		JLabel IdLabel = new JLabel("学 号");
		textPanel.add(IdLabel);

		
		IdTextField = new JTextField();
		textPanel.add(IdTextField);
		IdTextField.setColumns(15);
		IdTextField.setText(stu1.getId());
		
		JLabel NameLabel = new JLabel("姓 名");
		textPanel.add(NameLabel);
		
		NameTextField = new JTextField();
		textPanel.add(NameTextField);
		NameTextField.setColumns(15);
		NameTextField.setText(stu1.getName().trim());
		
		JLabel MajorLabel = new JLabel("专 业");
		textPanel.add(MajorLabel);
		
		MajorTextField = new JTextField();
		textPanel.add(MajorTextField);
		MajorTextField.setColumns(15);
		MajorTextField.setText(stu1.getMajorName());
		
		JLabel ClassNameLabel = new JLabel("班 级");
		textPanel.add(ClassNameLabel);
		
		ClassNameTextField = new JTextField();
		textPanel.add(ClassNameTextField);
		ClassNameTextField.setColumns(15);
		ClassNameTextField.setText(stu1.getClassName().trim());
		JLabel SexLabel = new JLabel("性 别");
		textPanel.add(SexLabel);
		
		SexTextField = new JTextField();
		textPanel.add(SexTextField);
		SexTextField.setColumns(15);
		SexTextField.setText(stu1.getSex());
		
		JPanel tablePanel = new JPanel();
		getContentPane().add(tablePanel, BorderLayout.CENTER);
		
		
		
		JPanel buttomPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttomPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(buttomPanel, BorderLayout.SOUTH);
		
		JButton changeButton = new JButton("提 交");
		buttomPanel.add(changeButton);
		JButton CloseButton = new JButton("关 闭");
		buttomPanel.add(CloseButton);
		
		table = new FixedTable(); // 创建指定表格模型的表格
		JScrollPane scrollPane = new JScrollPane(table);// 给表格添加下拉框
		tablePanel.add(scrollPane, BorderLayout.CENTER);// 表格编办添加下拉框面板
		
		//初始化表格数据
		students NameList = stu1;
		table.setModel(assmbledModelSoure(NameList));
		
		changeButton.addActionListener(new ActionListener() {		// 提交按钮添加动作监听
			public void actionPerformed(ActionEvent e) {// 当点击时
				boolean add = false;
				stu1.setMajorName(getMajorTextField());
				stu1.setClassName(getClassNameTextField());
				String StudentsID = getIdTextField();
				stu1.setName(getNameTextField());
				stu1.setSex(getSexTextField());
				table.selectAll();
				stu1.setTelephoneNumber((String)table.getValueAt(table.getSelectedRow(), 1));
				stu1.setAddress((String)table.getValueAt(table.getSelectedRow(), 0));
				
				add = DAOimpl.changeStudents(stu1, StudentsID);
				
				System.out.println('3');
				
				if(add) {
					JOptionPane.showMessageDialog(null,"修改成功","提示消息",JOptionPane.WARNING_MESSAGE); //弹出提示信息
				}else {
					JOptionPane.showMessageDialog(null,"修改失败，请重新添加","提示消息",JOptionPane.WARNING_MESSAGE); //弹出提示信息
				}
				
			}// actionPerformed()结束
		});// changeButton.addActionListener()结束
		
		
		CloseButton.addActionListener(new ActionListener() {		// 关闭按钮添加动作监听
			public void actionPerformed(ActionEvent e) {// 当点击时
				dispose();
			}// actionPerformed()结束
		});// CloseButton.addActionListener()结束
		
		setVisible(true);		//设置窗体可见
		}
	
	
	private DefaultTableModel assmbledModelSoure(students usableList) {		//设置表格模型
		String[] columnNames = {"家庭住址","联系电话"};//定义表格列名数组
		String[][] tableValues = new String[1][6];//创建表格列名数组
		for(int i = 0;i < 1;i++) {//遍历表格所有行
			tableValues[i][0] = "" + usableList.getAddress();//第一列为家庭住址
			tableValues[i][1] = "" + usableList.getTelephoneNumber();//第二列为联系电话
		}
		DefaultTableModel tmp = new DefaultTableModel(tableValues,columnNames);//根据列名数组和数据数组创建表格数据模型
		return tmp;
	}

	
	
	
	public static String getAddressTextArea() {
		return AddressTextArea.getText();
	}
	
	public void setAddressTextArea(JTextArea addressTextArea) {
		addressTextArea = addressTextArea;
	}
	
	public static String getNameTextField() {
		return NameTextField.getText();
	}

	public void setNameTextField(JTextField nameTextField) {
		NameTextField = nameTextField;
	}

	public static String getIdTextField() {
		return IdTextField.getText();
	}

	public void setIdTextField(JTextField idTextField) {
		IdTextField = idTextField;
	}

	public static String getPasswordTextField() {
		return passwordTextField.getText();
	}
	
	public static String getMajorTextField() {
		return MajorTextField.getText();
	}

	public void setMajorTextField(JTextField majorTextField) {
		MajorTextField = majorTextField;
	}

	public static String getClassNameTextField() {
		return ClassNameTextField.getText();
	}

	public void setClassNameTextField(JTextField classNameTextField) {
		ClassNameTextField = classNameTextField;
	}

	public static String getSexTextField() {
		return SexTextField.getText();
	}

	public void setSexTextField(JTextField sexTextField) {
		SexTextField = sexTextField;
	}

	public static FixedTable getTable() {
		return table;
	}

	public void setTable(FixedTable table) {
		this.table = table;
	}

	public static String getDormitoryTextField() {
		return DormitoryTextField.getText();
	}

	public void setDormitoryTextField(JTextField dormitoryTextField) {
		DormitoryTextField = dormitoryTextField;
	}

	public static String getTelephoneTextField() {
		return TelephoneTextField.getText();
	}

	public void setTelephoneTextField(JTextField telephoneTextField) {
		TelephoneTextField = telephoneTextField;
	}




}
