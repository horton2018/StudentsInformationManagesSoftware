package com.mqd.contact.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.mqd.contact.dao.AboutExcel;
import com.mqd.contact.dao.DAOimpl;


//import com.mqd.contact.dao.DAOimpl1;


import pojo.person;
import pojo.students;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;;
/**
 * @author horton
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame{	
	
	private FixedTable table;			 //ѧ����Ϣ���
	private FixedTable DepTable;			 //ϵ����Ϣ���
	private FixedTable MajTable;			 //רҵ��Ϣ���
	private JComboBox<String> surchType_combo;	//ѧ������������
	private JComboBox<String> dep_surchType_combo;	//ϵ������������
	private JComboBox<String> maj_surchType_combo;	//רҵ����������
	JTextField fuzzySurch_t;	//�����ı���
	private byte surchNumber = 0;	//������¼
	JTextField DepFuzzySurch_t;	//ϵ�������ı���
	JTextField MajFuzzySurch_t;	//רҵ�����ı���

	
	public MainFrame(person LoginIdentity) {	
		JPanel panel = new JPanel();//����ѡ�����
		getContentPane().add(panel);//�ڴ��������ѡ�����
		panel.setLayout(new GridLayout(1, 1));//����ѡ��������ַ�ʽ
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);//����ѡ����
		panel.add(tabbedPane, BorderLayout.NORTH);//��ѡ������м���ѡ����
		this.setTitle("ѧ����Ϣ�������");  //���ô������
		this.setSize(800, 750);     //���ô����С
		this.setLocationRelativeTo(null);//���ô��������ʾ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����˳�ͬʱ�رս���
		this.setVisible(true);		//���ô���ɼ�
		this.setResizable(true);   //���ô�������
		//����ѧ����ѯ�������
		JPanel Stupanel = new JPanel();
		tabbedPane.addTab("ѧ����ѯ", Stupanel);//���ѡ�ҳ��
		Stupanel.setLayout(new BorderLayout(0, 0));
		
		
		
		//����������˵���
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);// ���˵���������ӵ�����Ĳ˵�����
		JMenu yonghu_guanli = new JMenu("�û�����");//�������û������˵�����
		JMenuItem changePassword = new JMenuItem("�޸�����");//�������޸����롱�˵������
		JMenuItem tuichu_login = new JMenuItem("�˳���¼");//�������˳���¼���˵������
		JMenu informationManagement = new JMenu("��Ϣ����");//��������Ϣ�����˵�����
		JMenuItem input = new JMenuItem("��������");//�������������ݡ��˵������
		JMenuItem output = new JMenuItem("��������");//�������������ݡ��˵������
		
		menuBar.add(informationManagement);//�����û������˵�������ӵ��˵���
		if(LoginIdentity.getLimit().equals("��ʦ")) {//�ж��Ƿ�Ϊ��ʦ��¼
			informationManagement.add(input);//�����������ݡ��˵��������ӵ��û�����˵�����
		}
		informationManagement.add(output);//�����������ݡ��˵��������ӵ��û�����˵�����
		
		menuBar.add(yonghu_guanli);//�����û������˵�������ӵ��˵���
		yonghu_guanli.add(changePassword);//�����޸����롱�˵��������ӵ��û�����˵�����
		yonghu_guanli.add(tuichu_login);//�����˳���¼���˵��������ӵ��û�����˵�����
		
		//�����������
		JPanel surchPanel = new JPanel();		
		//����Ȩ����ʾ������壬��ʦ���೤�ɲ鿴
		if(LoginIdentity.getLimit().equals("��ʦ")) {
			//��������������������ı���  ��ť����
			Stupanel.add(surchPanel, BorderLayout.NORTH);
		}else if(LoginIdentity.isMonitor()) {
			//��������������������ı���  ��ť����
			Stupanel.add(surchPanel, BorderLayout.NORTH);
		}
		
		
		surchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		//��������������
		surchType_combo = new JComboBox<String>();
		surchPanel.add(surchType_combo);
		
		
		String TeachercomboBoxData[] = new String[] { "����","ѧ��","�Ա�","�༶","רҵ","�绰"};	//���������ݣ���ʦ��
		String StudentcomboBoxData[] = new String[] { "����","ѧ��","�Ա�","�绰"};	//���������ݣ�ѧ����
		
		//�ж��Ƿ�Ϊ��ʦ���߰೤������Ȩ����������������
		if(LoginIdentity.getLimit().equals("��ʦ")) {
			DefaultComboBoxModel<String> surchComboBoxModel = new DefaultComboBoxModel<String>(TeachercomboBoxData);// ��������������ģ��
			surchType_combo.setModel(surchComboBoxModel);
		}else if(LoginIdentity.isMonitor()) {
			DefaultComboBoxModel<String> surchComboBoxModel = new DefaultComboBoxModel<String>(StudentcomboBoxData);// ��������������ģ��
			surchType_combo.setModel(surchComboBoxModel);
		}
		//���������ı���
		fuzzySurch_t = new JTextField();
		fuzzySurch_t.setColumns(20);
		surchPanel.add(fuzzySurch_t);
		//���������ť
		JButton surchButton = new JButton("����");
		surchPanel.add(surchButton);
		
		//���� 'ȫ��ѧ��' 'ˢ��' 'ɾ��' '�޸�' '����' �����ť
		JButton AllStudentButton = new JButton("ȫ��ѧ��");
		JButton freshButton = new JButton("ˢ��");
		JButton updateButtom = new JButton("�޸�");
		JButton addButtom = new JButton("���");
		JButton delButtom = new JButton("ɾ��");
		
		/**
		 * �жϵ�¼��ݳ�ʼ������
		 */
		List<students> NameList = new ArrayList<students>();
		if(LoginIdentity.getLimit().equals("����Ա")) {			//���Ϊ����Ա��¼
			//��ʼ���������
			NameList = DAOimpl.search(4, 8, null, LoginIdentity);
		}else if(LoginIdentity.getLimit().equals("��ʦ")) {		//���Ϊ��ʦ��¼
			//��ʼ���������
			NameList = DAOimpl.search(4, 8, null, LoginIdentity);
		}else if(LoginIdentity.getLimit().equals("ѧ��")) {		//���Ϊѧ����¼
			//��ʼ���������
			NameList = DAOimpl.search(4, 2, LoginIdentity.getId(), LoginIdentity);
		}
		
		
		//����������
		JPanel tablePanel = new JPanel();
		Stupanel.add(tablePanel, BorderLayout.CENTER);
		tablePanel.setLayout(new BorderLayout(0, 0));
		
		table = new FixedTable(); // ����ָ�����ģ�͵ı��
		table.setToolTipText("���˫���鿴��ϸ��Ϣ");// �����ͣ��ʾ
		table.setCellEditable(false);//�ñ�񲻿ɱ༭
		JScrollPane scrollPane = new JScrollPane(table);// ��������������
		tablePanel.add(scrollPane, BorderLayout.CENTER);// �����������������
		
		JLabel lblNewLabel = new JLabel("��ʾ��˫�����鿴��ʾ��Ϣ��");// ��ʾ��ǩ
		tablePanel.add(lblNewLabel, BorderLayout.SOUTH);// ��ʾ��ǩ�ŵ�����������λ��
		table.setModel(assmbledModelSoure(NameList));
		
		
		//�����ײ���ť���
		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		Stupanel.add(buttonPanel, BorderLayout.SOUTH);
		
		
		
		
		
		/**
		 * �жϵ�½�˺�Ȩ��
		 */
		if(LoginIdentity.getLimit().equals("����Ա")) {			//���Ϊ����Ա��¼��ʾ�ײ���ť
			//����������  'ȫ��ѧ��' 'ˢ��' 'ɾ��' '�޸�' '����' �����ť
			buttonPanel.add(AllStudentButton);
			buttonPanel.add(freshButton);
			buttonPanel.add(updateButtom);
			buttonPanel.add(addButtom);
			buttonPanel.add(delButtom);
		}else if(LoginIdentity.getLimit().equals("��ʦ")) {		//���Ϊ��ʦ��¼��ʾ�ײ���ť
			//����������  'ȫ��ѧ��' 'ˢ��' '�޸�' ������ť
			buttonPanel.add(AllStudentButton);
			buttonPanel.add(freshButton);
			buttonPanel.add(updateButtom);	
			
			
			buttonPanel.add(addButtom);
			buttonPanel.add(delButtom);
				
		}else if(LoginIdentity.getLimit().equals("ѧ��")) {		//���Ϊѧ����¼��ʾ�ײ���ť
			//����������   'ˢ��' ��ť
			buttonPanel.add(freshButton);
		}
		
		
		
		
		
		
		table.addMouseListener(new MouseAdapter() {	//	����������¼�����
			public void mouseClicked(MouseEvent e) {//�������ʱ
				if(e.getClickCount() == 2) {		//��������˫���¼�
					String id = (String)table.getValueAt(table.getSelectedRow(), 0);//��ȡѡ���еĵ�һ�����ݣ���ֵΪid
					List<students> stu1 = DAOimpl.search(4, 2, id, LoginIdentity);
					students stu = stu1.get(0);
					
					@SuppressWarnings("unused")
					showMassageFrame smf = new showMassageFrame(stu,LoginIdentity);
				}//if������
			}//mouseClicked()����
		});//addMouseListener()����
		
		
		
		
		tuichu_login.addActionListener(new ActionListener() {	//	�˳���¼�˵����������¼�����
			public void actionPerformed(ActionEvent e) {//�������ʱ
				dispose();//�رյ�ǰҳ��
				@SuppressWarnings("unused")
				Login qw = new Login();
			}//mouseClicked()����
		});	
		
		
		
		output.addActionListener(new ActionListener() {	//	�������ݲ˵����������¼�����
			public void actionPerformed(ActionEvent e) {//�������ʱ				
			     if(surchNumber == 0){						//���ص���ʼ����ʱ
			    	 List<students> NameList = DAOimpl.search(4, 8, null, LoginIdentity);
			    	 export(NameList);
			     }else if(surchNumber == 1) {				//��������ѯʱ 
					List<students> NameList = DAOimpl.search(4, 5, getFuzzySurch_t().getText(), LoginIdentity);
					export(NameList);
				}else if(surchNumber == 2) {			//��ѧ�Ų�ѯʱ
					//students NameList = DAOimpl1.selectUseStudentID(getFuzzySurch_t().getText(),LoginIdentity);
					List<students> NameList = DAOimpl.search(4, 2, getFuzzySurch_t().getText(), LoginIdentity);
					
					export(NameList);
				}else if(surchNumber == 3) {			//��רҵ��ѯʱ
					List<students> NameList = DAOimpl.search(4, 4, getFuzzySurch_t().getText(), LoginIdentity);
					export(NameList);
				}else if(surchNumber == 4) {			//�ð༶��ѯʱ
					List<students> NameList = DAOimpl.search(4, 3, getFuzzySurch_t().getText(), LoginIdentity);
					export(NameList);
				}else if(surchNumber == 5) {			//���Ա��ѯʱ
					List<students> NameList = DAOimpl.search(4, 6, getFuzzySurch_t().getText(), LoginIdentity);
					export(NameList);
				}else if(surchNumber == 6) {			//�õ绰��ѯʱ
					List<students> NameList = DAOimpl.search(4, 7, getFuzzySurch_t().getText(), LoginIdentity);
					export(NameList);
				}
				}// if����
				
			}//mouseClicked()����
		);
		
		input.addActionListener(new ActionListener() {	//	�������ݲ˵����������¼�����
			public void actionPerformed(ActionEvent e) {//�������ʱ
				JFileChooser fileChooser = new JFileChooser();	//�����ļ�ѡ��Ի���
				int i = fileChooser.showOpenDialog(getContentPane());
				if(i == JFileChooser.APPROVE_OPTION) {
					File selectFile = fileChooser.getSelectedFile();
					
					try {
						List<students> stu = AboutExcel.ReadExcel(selectFile);
						if (stu.size() >= 1) {
							for(int b = 0;b<stu.size();b++) {
							students stu1 = stu.get(b);
						DAOimpl.addStudents(stu1);
						}
						JOptionPane.showMessageDialog(null, "����ɹ�!");
						}
					} catch (IOException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "����ʧ��!");
					}
				}
				
			}//mouseClicked()����
		});
		
		changePassword.addActionListener(new ActionListener() {	//	�޸�����˵����������¼�����
			public void actionPerformed(ActionEvent e) {//�������ʱ
				@SuppressWarnings("unused")
				showMassageFrame sa = new showMassageFrame(LoginIdentity);
				
			}//mouseClicked()����
		});
		
		surchButton.addActionListener(new  ActionListener() {	//	������ť�������¼�����
			public void actionPerformed(ActionEvent e) {//�������ʱ
				if (getSurchType_combo().getSelectedItem().toString() == "����") {				//��������ѯʱ 
					List<students> NameList= DAOimpl.search(4, 5, getFuzzySurch_t().getText(), LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
					surchNumber = 1; 
				}else if(getSurchType_combo().getSelectedItem().toString() == "ѧ��") {			//��ѧ�Ų�ѯʱ
					List<students> NameList= DAOimpl.search(4, 2, getFuzzySurch_t().getText(), LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
					surchNumber = 2;
				}else if(getSurchType_combo().getSelectedItem().toString() == "רҵ") {			//��רҵ��ѯʱ
					List<students> NameList= DAOimpl.search(4, 4, getFuzzySurch_t().getText(), LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
					surchNumber = 3;
				}else if(getSurchType_combo().getSelectedItem().toString() == "�༶") {			//�ð༶��ѯʱ
					List<students> NameList= DAOimpl.search(4, 3, getFuzzySurch_t().getText(), LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
					surchNumber = 4;
				}else if(getSurchType_combo().getSelectedItem().toString() == "�Ա�") {			//���Ա��ѯʱ
					List<students> NameList= DAOimpl.search(4, 6, getFuzzySurch_t().getText(), LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
					surchNumber = 5;
				}else if(getSurchType_combo().getSelectedItem().toString() == "�绰") {			//�õ绰��ѯʱ
					List<students> NameList= DAOimpl.search(4, 7, getFuzzySurch_t().getText(), LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
					surchNumber = 6;
				}// if����
			}//mouseClicked()����	
		});	
		
		addButtom.addActionListener(new ActionListener() {//��Ӱ�ť��������¼�����
			public void actionPerformed(ActionEvent e) {//�������ʱ
				@SuppressWarnings("unused")
				showMassageFrame sa = new showMassageFrame();
			}//mouseClicked()����
		});
		
		updateButtom.addActionListener(new ActionListener() {//�޸İ�ť��������¼�����
			public void actionPerformed(ActionEvent e) {//�������ʱ
				if(table.getSelectedRowCount() == 1) {
					String id = (String)table.getValueAt(table.getSelectedRow(), 0);//��ȡѡ���еĵ�һ�����ݣ���ֵΪid
					List<students> stu1 = DAOimpl.search(4, 2, id, LoginIdentity);
					students stu = stu1.get(0);
					byte in = 1;	//�˱���ֻΪ���ֹ��췽��������
					@SuppressWarnings("unused")
					showMassageFrame sa = new showMassageFrame(stu,in,LoginIdentity);
				}else {
					JOptionPane.showMessageDialog(null, "��ѡ��һ��ѧ��");
				}
					
			}//mouseClicked()����
		});
		
		delButtom.addActionListener(new ActionListener() {//ɾ����ť��������¼�����
			public void actionPerformed(ActionEvent e) {//�������ʱ
				if(table.getSelectedRowCount()  != 0) {
					
					if(table.getSelectedRowCount()  == 1) {
						
				int res=JOptionPane.showConfirmDialog(null, "�Ƿ�ɾ����ѧ��", "�Ƿ����", JOptionPane.YES_NO_OPTION);
                if(res==JOptionPane.YES_OPTION){ //������ǡ���ִ����������
                	String id = (String)table.getValueAt(table.getSelectedRow(), 0);//��ȡѡ���еĵ�һ�����ݣ���ֵΪid
					DAOimpl.deleteStudents(id);
					List<students> NameList = DAOimpl.search(4, 8, null, LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
					surchNumber = 0;
					JOptionPane.showMessageDialog(null,"ɾ���ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE); //������ʾ��Ϣ
                }else{ //������񡱺�ִ����������
					JOptionPane.showMessageDialog(null,"ɾ��ʧ��","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE); //������ʾ��Ϣ

                }
                
					}else {
						
						JOptionPane.showMessageDialog(null,"ɾ��ʧ�ܣ������ɾ��","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE); //������ʾ��Ϣ
					}
                
				}
			}//mouseClicked()����
		});
		
		freshButton.addActionListener(new ActionListener() {	//ˢ�°�ť��������¼�����
			public void actionPerformed(ActionEvent e) {//�������ʱ
				if(surchNumber == 0) {				//���Ŀǰ����ѯ����Ϊȫ����ѯ
					List<students> NameList = DAOimpl.search(4, 8, null, LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
				}else if(surchNumber == 1) {				//���Ŀǰ����ѯ����Ϊ������ѯ
					List<students> NameList = DAOimpl.search(4, 5, getFuzzySurch_t().getText(), LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
				}else if(surchNumber == 2) {				//���Ŀǰ����ѯ����Ϊѧ�Ų�ѯ
					List<students> NameList = DAOimpl.search(4, 2, getFuzzySurch_t().getText(),LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
				}else if(surchNumber == 3) {				//���Ŀǰ����ѯ����Ϊרҵ��ѯ
					List<students> NameList = DAOimpl.search(4, 4, getFuzzySurch_t().getText(),LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
				}else if(surchNumber == 4) {				//���Ŀǰ����ѯ����Ϊ�༶��ѯ
					List<students> NameList = DAOimpl.search(4, 3, getFuzzySurch_t().getText(),LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
				}else if(surchNumber == 5) {				//���Ŀǰ����ѯ����Ϊ�Ա��ѯ
					List<students> NameList = DAOimpl.search(4, 6, getFuzzySurch_t().getText(),LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
				}
				
		}//mouseClicked()����
		});
		
		AllStudentButton.addActionListener(new ActionListener() {//ȫ��ѧ����ť��������¼�����
			public void actionPerformed(ActionEvent e) {//�������ʱ
				List<students> NameList = DAOimpl.search(4, 8, null, LoginIdentity);
				table.setModel(assmbledModelSoure(NameList));
				surchNumber = 0;
			}//mouseClicked()����
		});
	
		
		
		JPanel depPanel = new JPanel();
		tabbedPane.addTab("ϵ����ѯ",depPanel);
		depPanel.setLayout(new BorderLayout(0, 0));
		
		//�����������
		JPanel dep_surchPanel = new JPanel();		
		depPanel.add(dep_surchPanel, BorderLayout.NORTH);
		
				
		dep_surchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		//��������������
		dep_surchType_combo = new JComboBox<String>();
		dep_surchPanel.add(dep_surchType_combo);
				
				
		String DepComboBoxData[] = new String[] { "רҵ����","ϵ����","ϵ����"};	//����������
		DefaultComboBoxModel<String> DepSurchComboBoxModel = new DefaultComboBoxModel<String>(DepComboBoxData);//��������������ģ��
		dep_surchType_combo.setModel(DepSurchComboBoxModel);
		
		//���������ı���
		DepFuzzySurch_t = new JTextField();
		DepFuzzySurch_t.setColumns(20);
		dep_surchPanel.add(DepFuzzySurch_t);
		//���������ť
		JButton DepSurchButton = new JButton("����");
		dep_surchPanel.add(DepSurchButton);
		
		JPanel DepTablePanel = new JPanel();
		depPanel.add(DepTablePanel, BorderLayout.CENTER);
		DepTablePanel.setLayout(new BorderLayout(0, 0));
		
		DepTable = new FixedTable(); // ����ָ�����ģ�͵ı��
		DepTable.setToolTipText("���˫���鿴��ϸ��Ϣ");// �����ͣ��ʾ
		DepTable.setCellEditable(false);//�ñ�񲻿ɱ༭
		JScrollPane DepScrollPane = new JScrollPane(DepTable);// ��������������
		DepTablePanel.add(DepScrollPane, BorderLayout.CENTER);// �����������������
		
		JLabel DepLblNewLabel = new JLabel("��ʾ��˫�����鿴��ʾ��Ϣ��");// ��ʾ��ǩ
		DepTablePanel.add(DepLblNewLabel, BorderLayout.SOUTH);// ��ʾ��ǩ�ŵ�����������λ��
		
		
		
		
		
	}
	
	

	/**
	 * ��ѯ����ѧ����Ϣ
	 * 
	 * return ѧ����Ϣ�������ģ��
	 */
//	private DefaultTableModel getUsableModleSoure() {
//		List<students> NameList= DAOimpl.search(4, 8,"all student", LoginIdentity);
//		return assmbledModelSoure(usableList);						//��������ѧ���������ģ��
//	}
	
	
	



	/**
	 * ����ѧ�������趨�ı������ģ��
	 * usableList ��ѧ������
	 * return �������ģ��
	 */
	private DefaultTableModel assmbledModelSoure(List<students> usableList) {
		int StudentsCount = usableList.size();			//��ȡ�����е�ѧ������
		String[] columnNames = {"ѧ��","����","�Ա�","רҵ���","�༶���","��ϵ�绰"};//��������������
		String[][] tableValues = new String[StudentsCount][6];//���������������
		for(int i = 0;i < StudentsCount;i++) {//�������������
			students stu = usableList.get(i);//��ȡ���û�����
			tableValues[i][0] = "" + stu.getId();//��һ��Ϊѧ��
			tableValues[i][1] = "" + stu.getName();//�ڶ���Ϊ����
			tableValues[i][2] = "" + stu.getSex();//������Ϊ�Ա�
			tableValues[i][3] = "" + stu.getMajorId();//������Ϊרҵ���
			tableValues[i][4] = "" + stu.getClassId();//������Ϊ�༶���
			tableValues[i][5] = "" + stu.getTelephoneNumber();//������Ϊ��ϵ�绰
		}
		DefaultTableModel tmp = new DefaultTableModel(tableValues,columnNames);//��������������������鴴���������ģ��
		return tmp;
	}
	
	/**
	 * ����ѧ�������趨�ı������ģ��
	 * usableList ��ѧ������
	 * return �������ģ��
	 */
	private DefaultTableModel assmbledModelSoure(students usableList) {
		String[] columnNames = {"ѧ��","����","�Ա�","רҵ���","�༶���","��ϵ�绰"};//��������������
		String[][] tableValues = new String[1][6];//���������������
		for(int i = 0;i < 1;i++) {//�������������
			students stu = usableList;//��ȡ���û�����
			tableValues[i][0] = "" + stu.getId();//��һ��Ϊѧ��
			tableValues[i][1] = "" + stu.getName();//�ڶ���Ϊ����
			tableValues[i][2] = "" + stu.getSex();//������Ϊ�Ա�
			tableValues[i][3] = "" + stu.getMajorId();//������Ϊרҵ���
			tableValues[i][4] = "" + stu.getClassId();//������Ϊ�༶���
			tableValues[i][5] = "" + stu.getTelephoneNumber();//������Ϊ��ϵ�绰
		}
		DefaultTableModel tmp = new DefaultTableModel(tableValues,columnNames);//��������������������鴴���������ģ��
		return tmp;
	}
	
	/**
	 * �������List�ڵ����ݵ���Ϊexcel�ļ�
	 * @param NameList��ѧ������
	 */
	private void export(List<students> NameList) {
		AboutExcel exportExcel = new AboutExcel();
		JFileChooser fcDlg = new JFileChooser();
         fcDlg.setDialogTitle("��ѡ�񵼳��ļ�·��");
         fcDlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         int returnVal = fcDlg.showOpenDialog(null);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
           String filepath = fcDlg.getSelectedFile().getPath();
          exportExcel.exportText(NameList,filepath);
         }
	}
	
	public JTextField getMajFuzzySurch_t() {
		return MajFuzzySurch_t;
	}

	public JComboBox<String> getDep_surchType_combo() {
		return dep_surchType_combo;
	}

	public JComboBox<String> getMaj_surchType_combo() {
		return maj_surchType_combo;
	}

	public JTextField getDepFuzzySurch_t() {
		return DepFuzzySurch_t;
	}
		
	public JComboBox<String> getSurchType_combo() {
		return surchType_combo;
	}

	public JTextField getFuzzySurch_t() {
		return fuzzySurch_t;
	}

}
