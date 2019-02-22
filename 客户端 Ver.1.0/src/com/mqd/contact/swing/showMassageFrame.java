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
	private static FixedTable table;			 //ѧ����Ϣ���
	private static JTextField DormitoryTextField;
	private static JTextField TelephoneTextField;
		
	public showMassageFrame(students stu,person identity) {				//˫�����鿴��ϸ��Ϣ���ù��췽��
		students students = DAOimpl.search1(4, 9, stu, identity);
		students stu1 = students;
		this.setTitle("��ϸ��Ϣ");  //���ô������
		this.setSize(500, 300);     //���ô����С
		this.setLocationRelativeTo(null);//���ô��������ʾ
		JPanel textPanel = new JPanel();
		getContentPane().add(textPanel, BorderLayout.NORTH);
		textPanel.setLayout(new GridLayout(3, 4));
		
		JLabel IdLabel = new JLabel("ѧ ��");
		textPanel.add(IdLabel);
		
		IdTextField = new JTextField();
		textPanel.add(IdTextField);
		IdTextField.setColumns(15);
		IdTextField.setText(stu1.getId());
		
		JLabel NameLabel = new JLabel("�� ��");
		textPanel.add(NameLabel);
		
		NameTextField = new JTextField();
		textPanel.add(NameTextField);
		NameTextField.setColumns(15);
		NameTextField.setText(stu1.getName());
		
		JLabel MajorLabel = new JLabel("ר ҵ");
		textPanel.add(MajorLabel);
		
		MajorTextField = new JTextField();
		textPanel.add(MajorTextField);
		MajorTextField.setColumns(20);
		MajorTextField.setText(stu1.getMajorName());
		
		JLabel ClassNameLabel = new JLabel("��  ��");
		textPanel.add(ClassNameLabel);
		
		ClassNameTextField = new JTextField();
		textPanel.add(ClassNameTextField);
		ClassNameTextField.setColumns(15);
		ClassNameTextField.setText(stu1.getClassName());
		
		JLabel SexLabel = new JLabel("�� ��");
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
		
		JButton CloseButton = new JButton("�� ��");
		buttomPanel.add(CloseButton);
		
		table = new FixedTable(); // ����ָ�����ģ�͵ı��
		table.setCellEditable(false);//�ñ�񲻿ɱ༭
		JScrollPane scrollPane = new JScrollPane(table);// ��������������
		tablePanel.add(scrollPane, BorderLayout.CENTER);// �����������������
		
		//��ʼ���������
		
		students NameList = stu1;
		table.setModel(assmbledModelSoure(NameList));
		
		setVisible(true);		//���ô���ɼ�
		
		CloseButton.addActionListener(new ActionListener() {		// �رհ�ť��Ӷ�������
			public void actionPerformed(ActionEvent e) {// �����ʱ
				dispose();
			}// actionPerformed()����
		});// CloseButton.addActionListener()����
		
		}


	public showMassageFrame() {						//�����Ӱ�ť���ù��췽��
		
		this.setTitle("���ѧ����ϸ��Ϣ");  //���ô������
		this.setSize(400, 300);     //���ô����С
		this.setLocationRelativeTo(null);//���ô��������ʾ
		JPanel textPanel = new JPanel();
		JPanel textPanelDown = new JPanel();
		getContentPane().add(textPanel, BorderLayout.NORTH);
		getContentPane().add(textPanelDown,BorderLayout.CENTER);
		textPanel.setLayout(new GridLayout(3, 4));
		textPanelDown.setLayout(new FlowLayout(0));
		
		JLabel IdLabel = new JLabel("ѧ ��");
		textPanel.add(IdLabel);
		
		IdTextField = new JTextField();
		textPanel.add(IdTextField);
		IdTextField.setColumns(15);
		
		JLabel NameLabel = new JLabel("�� ��");
		textPanel.add(NameLabel);
		
		NameTextField = new JTextField();
		textPanel.add(NameTextField);
		NameTextField.setColumns(15);
		
		JLabel MajorLabel = new JLabel("ר ҵ");
		textPanel.add(MajorLabel);
		
		MajorTextField = new JTextField();
		textPanel.add(MajorTextField);
		MajorTextField.setColumns(15);
		
		JLabel ClassNameLabel = new JLabel("�� ��");
		textPanel.add(ClassNameLabel);
		
		ClassNameTextField = new JTextField();
		textPanel.add(ClassNameTextField);
		ClassNameTextField.setColumns(15);
		
		JLabel SexLabel = new JLabel("�� ��");
		textPanel.add(SexLabel);
		
		SexTextField = new JTextField();
		textPanel.add(SexTextField);
		SexTextField.setColumns(15);
		
		JLabel Telephonelabel = new JLabel("�� ��");
		textPanel.add(Telephonelabel);
		
		TelephoneTextField = new JTextField();
		TelephoneTextField.setColumns(15);
		textPanel.add(TelephoneTextField);
		
		
		JLabel address = new JLabel("��ͥסַ");
		textPanelDown.add(address);
		
		AddressTextArea = new JTextArea(3,35);
		//AddressTextArea.setColumns(50);
		textPanelDown.add(AddressTextArea);
		
		
		
		JPanel buttomPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttomPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.CENTER);
		getContentPane().add(buttomPanel, BorderLayout.SOUTH);
		
		
		JButton addButton = new JButton("�� ��");
		buttomPanel.add(addButton);
		JButton CloseButton = new JButton("ȡ ��");
		buttomPanel.add(CloseButton);
		
		addButton.addActionListener(new ActionListener() {		// ��Ӱ�ť��Ӷ�������
			public void actionPerformed(ActionEvent e) {// �����ʱ
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
					JOptionPane.showMessageDialog(null,"��ӳɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE); //������ʾ��Ϣ
					dispose();//�ر���Ӵ���
				}else {
					JOptionPane.showMessageDialog(null,"���ʧ�ܣ����������","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE); //������ʾ��Ϣ
				}
				
			}// actionPerformed()����
		});// addButton.addActionListener()����

		CloseButton.addActionListener(new ActionListener() {		// ȡ����ť��Ӷ�������
			public void actionPerformed(ActionEvent e) {// �����ʱ
				dispose();
			}// actionPerformed()����
		});// addButton.addActionListener()����
		
		setVisible(true);		//���ô���ɼ�
		}

	public showMassageFrame(person LoginIdentity) {						//����޸����밴ť���ù��췽��
		
		this.setTitle("�޸��˺�����");  //���ô������
		this.setSize(300, 200);     //���ô����С
		this.setLocationRelativeTo(null);//���ô��������ʾ
		JPanel textPanel = new JPanel();
		getContentPane().add(textPanel, BorderLayout.NORTH);
		textPanel.setLayout(new GridLayout(2, 4));
		
		JLabel IdLabel = new JLabel("�� ��");
		textPanel.add(IdLabel);
		
		
		JLabel IdTestLabel = new JLabel(LoginIdentity.getId());
		textPanel.add(IdTestLabel);
		
		
		JLabel passwordLabel = new JLabel("�� ��");
		textPanel.add(passwordLabel);
		
		passwordTextField = new JTextField();
		textPanel.add(passwordTextField);
		passwordTextField.setColumns(15);
		
		
		JPanel buttomPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttomPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.CENTER);
		getContentPane().add(buttomPanel, BorderLayout.CENTER);
		
		
		JButton changeButton = new JButton("�� ��");
		buttomPanel.add(changeButton);
		JButton CloseButton = new JButton("ȡ ��");
		buttomPanel.add(CloseButton);
		
		changeButton.addActionListener(new ActionListener() {		// �޸İ�ť��Ӷ�������
			public void actionPerformed(ActionEvent e) {// �����ʱ
				boolean result = false;
				LoginIdentity.setPassword(getPasswordTextField());
				if(LoginIdentity instanceof teacher) {
					
					
					
					
					//result = DAOimpl1.changeUserPassword(LoginIdentity,"teacher");
				}else if(LoginIdentity instanceof students) {
					// = DAOimpl1.changeUserPassword(LoginIdentity,"student");					
				}
				
				
				
				
				
				if(result) {
					JOptionPane.showMessageDialog(null,"�޸ĳɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE); //������ʾ��Ϣ
					dispose();//�ر���Ӵ���
				}else {
					JOptionPane.showMessageDialog(null,"�޸�ʧ�ܣ��������޸�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE); //������ʾ��Ϣ
				}
				
			}// actionPerformed()����
		});// changeButton.addActionListener()����

		CloseButton.addActionListener(new ActionListener() {		// ȡ����ť��Ӷ�������
			public void actionPerformed(ActionEvent e) {// �����ʱ
				dispose();
			}// actionPerformed()����
		});// closeButton.addActionListener()����
		
		setVisible(true);		//���ô���ɼ�
		}

	
	public showMassageFrame(students stu,byte changeNumber,person identity) {	//����޸�ѧ����Ϣ��ť���ù��췽��
		students stu1 = DAOimpl.search1(4, 9, stu, identity);
		this.setTitle("�޸���ϸ��Ϣ");  //���ô������
		this.setSize(500, 300);     //���ô����С
		this.setLocationRelativeTo(null);//���ô��������ʾ
		JPanel textPanel = new JPanel();
		getContentPane().add(textPanel, BorderLayout.NORTH);
		textPanel.setLayout(new GridLayout(3, 4));
		
		JLabel IdLabel = new JLabel("ѧ ��");
		textPanel.add(IdLabel);

		
		IdTextField = new JTextField();
		textPanel.add(IdTextField);
		IdTextField.setColumns(15);
		IdTextField.setText(stu1.getId());
		
		JLabel NameLabel = new JLabel("�� ��");
		textPanel.add(NameLabel);
		
		NameTextField = new JTextField();
		textPanel.add(NameTextField);
		NameTextField.setColumns(15);
		NameTextField.setText(stu1.getName().trim());
		
		JLabel MajorLabel = new JLabel("ר ҵ");
		textPanel.add(MajorLabel);
		
		MajorTextField = new JTextField();
		textPanel.add(MajorTextField);
		MajorTextField.setColumns(15);
		MajorTextField.setText(stu1.getMajorName());
		
		JLabel ClassNameLabel = new JLabel("�� ��");
		textPanel.add(ClassNameLabel);
		
		ClassNameTextField = new JTextField();
		textPanel.add(ClassNameTextField);
		ClassNameTextField.setColumns(15);
		ClassNameTextField.setText(stu1.getClassName().trim());
		JLabel SexLabel = new JLabel("�� ��");
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
		
		JButton changeButton = new JButton("�� ��");
		buttomPanel.add(changeButton);
		JButton CloseButton = new JButton("�� ��");
		buttomPanel.add(CloseButton);
		
		table = new FixedTable(); // ����ָ�����ģ�͵ı��
		JScrollPane scrollPane = new JScrollPane(table);// ��������������
		tablePanel.add(scrollPane, BorderLayout.CENTER);// �����������������
		
		//��ʼ���������
		students NameList = stu1;
		table.setModel(assmbledModelSoure(NameList));
		
		changeButton.addActionListener(new ActionListener() {		// �ύ��ť��Ӷ�������
			public void actionPerformed(ActionEvent e) {// �����ʱ
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
					JOptionPane.showMessageDialog(null,"�޸ĳɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE); //������ʾ��Ϣ
				}else {
					JOptionPane.showMessageDialog(null,"�޸�ʧ�ܣ����������","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE); //������ʾ��Ϣ
				}
				
			}// actionPerformed()����
		});// changeButton.addActionListener()����
		
		
		CloseButton.addActionListener(new ActionListener() {		// �رհ�ť��Ӷ�������
			public void actionPerformed(ActionEvent e) {// �����ʱ
				dispose();
			}// actionPerformed()����
		});// CloseButton.addActionListener()����
		
		setVisible(true);		//���ô���ɼ�
		}
	
	
	private DefaultTableModel assmbledModelSoure(students usableList) {		//���ñ��ģ��
		String[] columnNames = {"��ͥסַ","��ϵ�绰"};//��������������
		String[][] tableValues = new String[1][6];//���������������
		for(int i = 0;i < 1;i++) {//�������������
			tableValues[i][0] = "" + usableList.getAddress();//��һ��Ϊ��ͥסַ
			tableValues[i][1] = "" + usableList.getTelephoneNumber();//�ڶ���Ϊ��ϵ�绰
		}
		DefaultTableModel tmp = new DefaultTableModel(tableValues,columnNames);//��������������������鴴���������ģ��
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
