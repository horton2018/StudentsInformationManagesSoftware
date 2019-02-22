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
	
	private FixedTable table;			 //学生信息表格
	private FixedTable DepTable;			 //系部信息表格
	private FixedTable MajTable;			 //专业信息表格
	private JComboBox<String> surchType_combo;	//学生搜索下拉框
	private JComboBox<String> dep_surchType_combo;	//系部搜索下拉框
	private JComboBox<String> maj_surchType_combo;	//专业搜索下拉框
	JTextField fuzzySurch_t;	//搜索文本框
	private byte surchNumber = 0;	//搜索记录
	JTextField DepFuzzySurch_t;	//系部搜索文本框
	JTextField MajFuzzySurch_t;	//专业搜索文本框

	
	public MainFrame(person LoginIdentity) {	
		JPanel panel = new JPanel();//创建选项卡容器
		getContentPane().add(panel);//在窗体中添加选项卡容器
		panel.setLayout(new GridLayout(1, 1));//设置选项卡容器布局方式
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);//创建选项卡组件
		panel.add(tabbedPane, BorderLayout.NORTH);//在选项卡容器中加载选项卡组件
		this.setTitle("学生信息管理软件");  //设置窗体标题
		this.setSize(800, 750);     //设置窗体大小
		this.setLocationRelativeTo(null);//设置窗体居中显示
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击退出同时关闭进程
		this.setVisible(true);		//设置窗体可见
		this.setResizable(true);   //设置窗体锁定
		//创建学生查询面板容器
		JPanel Stupanel = new JPanel();
		tabbedPane.addTab("学生查询", Stupanel);//添加选项卡页面
		Stupanel.setLayout(new BorderLayout(0, 0));
		
		
		
		//创建主窗体菜单栏
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);// 将菜单栏对象添加到窗体的菜单栏中
		JMenu yonghu_guanli = new JMenu("用户管理");//创建“用户管理”菜单对象
		JMenuItem changePassword = new JMenuItem("修改密码");//创建“修改密码”菜单项对象
		JMenuItem tuichu_login = new JMenuItem("退出登录");//创建“退出登录”菜单项对象
		JMenu informationManagement = new JMenu("信息管理");//创建“信息管理”菜单对象
		JMenuItem input = new JMenuItem("导入数据");//创建“导入数据”菜单项对象
		JMenuItem output = new JMenuItem("导出数据");//创建“导出数据”菜单项对象
		
		menuBar.add(informationManagement);//将”用户管理“菜单对象添加到菜单栏
		if(LoginIdentity.getLimit().equals("老师")) {//判断是否为老师登录
			informationManagement.add(input);//将“导入数据”菜单项对象添加到用户管理菜单对象
		}
		informationManagement.add(output);//将“导出数据”菜单项对象添加到用户管理菜单对象
		
		menuBar.add(yonghu_guanli);//将”用户管理“菜单对象添加到菜单栏
		yonghu_guanli.add(changePassword);//将“修改密码”菜单项对象添加到用户管理菜单对象
		yonghu_guanli.add(tuichu_login);//将“退出登录”菜单项对象添加到用户管理菜单对象
		
		//创建搜索面板
		JPanel surchPanel = new JPanel();		
		//依据权限显示搜索面板，老师、班长可查看
		if(LoginIdentity.getLimit().equals("老师")) {
			//建立顶部搜索下拉框和文本框  按钮容器
			Stupanel.add(surchPanel, BorderLayout.NORTH);
		}else if(LoginIdentity.isMonitor()) {
			//建立顶部搜索下拉框和文本框  按钮容器
			Stupanel.add(surchPanel, BorderLayout.NORTH);
		}
		
		
		surchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		//建立搜索下拉框
		surchType_combo = new JComboBox<String>();
		surchPanel.add(surchType_combo);
		
		
		String TeachercomboBoxData[] = new String[] { "姓名","学号","性别","班级","专业","电话"};	//下拉框数据，老师版
		String StudentcomboBoxData[] = new String[] { "姓名","学号","性别","电话"};	//下拉框数据，学生版
		
		//判断是否为老师或者班长，依据权限设置搜索下拉框
		if(LoginIdentity.getLimit().equals("老师")) {
			DefaultComboBoxModel<String> surchComboBoxModel = new DefaultComboBoxModel<String>(TeachercomboBoxData);// 创建下拉框数据模型
			surchType_combo.setModel(surchComboBoxModel);
		}else if(LoginIdentity.isMonitor()) {
			DefaultComboBoxModel<String> surchComboBoxModel = new DefaultComboBoxModel<String>(StudentcomboBoxData);// 创建下拉框数据模型
			surchType_combo.setModel(surchComboBoxModel);
		}
		//建立搜索文本框
		fuzzySurch_t = new JTextField();
		fuzzySurch_t.setColumns(20);
		surchPanel.add(fuzzySurch_t);
		//添加搜索按钮
		JButton surchButton = new JButton("搜索");
		surchPanel.add(surchButton);
		
		//创建 '全部学生' '刷新' '删除' '修改' '增加' 五个按钮
		JButton AllStudentButton = new JButton("全部学生");
		JButton freshButton = new JButton("刷新");
		JButton updateButtom = new JButton("修改");
		JButton addButtom = new JButton("添加");
		JButton delButtom = new JButton("删除");
		
		/**
		 * 判断登录身份初始化数据
		 */
		List<students> NameList = new ArrayList<students>();
		if(LoginIdentity.getLimit().equals("管理员")) {			//如果为管理员登录
			//初始化表格数据
			NameList = DAOimpl.search(4, 8, null, LoginIdentity);
		}else if(LoginIdentity.getLimit().equals("老师")) {		//如果为老师登录
			//初始化表格数据
			NameList = DAOimpl.search(4, 8, null, LoginIdentity);
		}else if(LoginIdentity.getLimit().equals("学生")) {		//如果为学生登录
			//初始化表格数据
			NameList = DAOimpl.search(4, 2, LoginIdentity.getId(), LoginIdentity);
		}
		
		
		//建立表格面板
		JPanel tablePanel = new JPanel();
		Stupanel.add(tablePanel, BorderLayout.CENTER);
		tablePanel.setLayout(new BorderLayout(0, 0));
		
		table = new FixedTable(); // 创建指定表格模型的表格
		table.setToolTipText("鼠标双击查看详细信息");// 鼠标悬停提示
		table.setCellEditable(false);//让表格不可编辑
		JScrollPane scrollPane = new JScrollPane(table);// 给表格添加下拉框
		tablePanel.add(scrollPane, BorderLayout.CENTER);// 表格编办添加下拉框面板
		
		JLabel lblNewLabel = new JLabel("提示：双击鼠标查看提示信息。");// 提示标签
		tablePanel.add(lblNewLabel, BorderLayout.SOUTH);// 提示标签放到表格面板最南位置
		table.setModel(assmbledModelSoure(NameList));
		
		
		//建立底部按钮面板
		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		Stupanel.add(buttonPanel, BorderLayout.SOUTH);
		
		
		
		
		
		/**
		 * 判断登陆账号权限
		 */
		if(LoginIdentity.getLimit().equals("管理员")) {			//如果为管理员登录显示底部按钮
			//在面板里添加  '全部学生' '刷新' '删除' '修改' '增加' 五个按钮
			buttonPanel.add(AllStudentButton);
			buttonPanel.add(freshButton);
			buttonPanel.add(updateButtom);
			buttonPanel.add(addButtom);
			buttonPanel.add(delButtom);
		}else if(LoginIdentity.getLimit().equals("老师")) {		//如果为老师登录显示底部按钮
			//在面板里添加  '全部学生' '刷新' '修改' 三个按钮
			buttonPanel.add(AllStudentButton);
			buttonPanel.add(freshButton);
			buttonPanel.add(updateButtom);	
			
			
			buttonPanel.add(addButtom);
			buttonPanel.add(delButtom);
				
		}else if(LoginIdentity.getLimit().equals("学生")) {		//如果为学生登录显示底部按钮
			//在面板里添加   '刷新' 按钮
			buttonPanel.add(freshButton);
		}
		
		
		
		
		
		
		table.addMouseListener(new MouseAdapter() {	//	表格添加鼠标事件监听
			public void mouseClicked(MouseEvent e) {//当鼠标点击时
				if(e.getClickCount() == 2) {		//如果鼠标是双击事件
					String id = (String)table.getValueAt(table.getSelectedRow(), 0);//获取选中行的第一列数据，赋值为id
					List<students> stu1 = DAOimpl.search(4, 2, id, LoginIdentity);
					students stu = stu1.get(0);
					
					@SuppressWarnings("unused")
					showMassageFrame smf = new showMassageFrame(stu,LoginIdentity);
				}//if语句结束
			}//mouseClicked()结束
		});//addMouseListener()结束
		
		
		
		
		tuichu_login.addActionListener(new ActionListener() {	//	退出登录菜单项添加鼠标事件监听
			public void actionPerformed(ActionEvent e) {//当鼠标点击时
				dispose();//关闭当前页面
				@SuppressWarnings("unused")
				Login qw = new Login();
			}//mouseClicked()结束
		});	
		
		
		
		output.addActionListener(new ActionListener() {	//	导出数据菜单项添加鼠标事件监听
			public void actionPerformed(ActionEvent e) {//当鼠标点击时				
			     if(surchNumber == 0){						//当回到初始数据时
			    	 List<students> NameList = DAOimpl.search(4, 8, null, LoginIdentity);
			    	 export(NameList);
			     }else if(surchNumber == 1) {				//用姓名查询时 
					List<students> NameList = DAOimpl.search(4, 5, getFuzzySurch_t().getText(), LoginIdentity);
					export(NameList);
				}else if(surchNumber == 2) {			//用学号查询时
					//students NameList = DAOimpl1.selectUseStudentID(getFuzzySurch_t().getText(),LoginIdentity);
					List<students> NameList = DAOimpl.search(4, 2, getFuzzySurch_t().getText(), LoginIdentity);
					
					export(NameList);
				}else if(surchNumber == 3) {			//用专业查询时
					List<students> NameList = DAOimpl.search(4, 4, getFuzzySurch_t().getText(), LoginIdentity);
					export(NameList);
				}else if(surchNumber == 4) {			//用班级查询时
					List<students> NameList = DAOimpl.search(4, 3, getFuzzySurch_t().getText(), LoginIdentity);
					export(NameList);
				}else if(surchNumber == 5) {			//用性别查询时
					List<students> NameList = DAOimpl.search(4, 6, getFuzzySurch_t().getText(), LoginIdentity);
					export(NameList);
				}else if(surchNumber == 6) {			//用电话查询时
					List<students> NameList = DAOimpl.search(4, 7, getFuzzySurch_t().getText(), LoginIdentity);
					export(NameList);
				}
				}// if结束
				
			}//mouseClicked()结束
		);
		
		input.addActionListener(new ActionListener() {	//	导入数据菜单项添加鼠标事件监听
			public void actionPerformed(ActionEvent e) {//当鼠标点击时
				JFileChooser fileChooser = new JFileChooser();	//创建文件选择对话框
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
						JOptionPane.showMessageDialog(null, "导入成功!");
						}
					} catch (IOException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "导入失败!");
					}
				}
				
			}//mouseClicked()结束
		});
		
		changePassword.addActionListener(new ActionListener() {	//	修改密码菜单项添加鼠标事件监听
			public void actionPerformed(ActionEvent e) {//当鼠标点击时
				@SuppressWarnings("unused")
				showMassageFrame sa = new showMassageFrame(LoginIdentity);
				
			}//mouseClicked()结束
		});
		
		surchButton.addActionListener(new  ActionListener() {	//	搜索按钮添加鼠标事件监听
			public void actionPerformed(ActionEvent e) {//当鼠标点击时
				if (getSurchType_combo().getSelectedItem().toString() == "姓名") {				//用姓名查询时 
					List<students> NameList= DAOimpl.search(4, 5, getFuzzySurch_t().getText(), LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
					surchNumber = 1; 
				}else if(getSurchType_combo().getSelectedItem().toString() == "学号") {			//用学号查询时
					List<students> NameList= DAOimpl.search(4, 2, getFuzzySurch_t().getText(), LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
					surchNumber = 2;
				}else if(getSurchType_combo().getSelectedItem().toString() == "专业") {			//用专业查询时
					List<students> NameList= DAOimpl.search(4, 4, getFuzzySurch_t().getText(), LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
					surchNumber = 3;
				}else if(getSurchType_combo().getSelectedItem().toString() == "班级") {			//用班级查询时
					List<students> NameList= DAOimpl.search(4, 3, getFuzzySurch_t().getText(), LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
					surchNumber = 4;
				}else if(getSurchType_combo().getSelectedItem().toString() == "性别") {			//用性别查询时
					List<students> NameList= DAOimpl.search(4, 6, getFuzzySurch_t().getText(), LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
					surchNumber = 5;
				}else if(getSurchType_combo().getSelectedItem().toString() == "电话") {			//用电话查询时
					List<students> NameList= DAOimpl.search(4, 7, getFuzzySurch_t().getText(), LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
					surchNumber = 6;
				}// if结束
			}//mouseClicked()结束	
		});	
		
		addButtom.addActionListener(new ActionListener() {//添加按钮设置鼠标事件监听
			public void actionPerformed(ActionEvent e) {//当鼠标点击时
				@SuppressWarnings("unused")
				showMassageFrame sa = new showMassageFrame();
			}//mouseClicked()结束
		});
		
		updateButtom.addActionListener(new ActionListener() {//修改按钮设置鼠标事件监听
			public void actionPerformed(ActionEvent e) {//当鼠标点击时
				if(table.getSelectedRowCount() == 1) {
					String id = (String)table.getValueAt(table.getSelectedRow(), 0);//获取选中行的第一列数据，赋值为id
					List<students> stu1 = DAOimpl.search(4, 2, id, LoginIdentity);
					students stu = stu1.get(0);
					byte in = 1;	//此变量只为区分构造方法，无用
					@SuppressWarnings("unused")
					showMassageFrame sa = new showMassageFrame(stu,in,LoginIdentity);
				}else {
					JOptionPane.showMessageDialog(null, "请选择一个学生");
				}
					
			}//mouseClicked()结束
		});
		
		delButtom.addActionListener(new ActionListener() {//删除按钮设置鼠标事件监听
			public void actionPerformed(ActionEvent e) {//当鼠标点击时
				if(table.getSelectedRowCount()  != 0) {
					
					if(table.getSelectedRowCount()  == 1) {
						
				int res=JOptionPane.showConfirmDialog(null, "是否删除该学生", "是否继续", JOptionPane.YES_NO_OPTION);
                if(res==JOptionPane.YES_OPTION){ //点击“是”后执行这个代码块
                	String id = (String)table.getValueAt(table.getSelectedRow(), 0);//获取选中行的第一列数据，赋值为id
					DAOimpl.deleteStudents(id);
					List<students> NameList = DAOimpl.search(4, 8, null, LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
					surchNumber = 0;
					JOptionPane.showMessageDialog(null,"删除成功","提示消息",JOptionPane.WARNING_MESSAGE); //弹出提示信息
                }else{ //点击“否”后执行这个代码块
					JOptionPane.showMessageDialog(null,"删除失败","提示消息",JOptionPane.WARNING_MESSAGE); //弹出提示信息

                }
                
					}else {
						
						JOptionPane.showMessageDialog(null,"删除失败，请逐个删除","提示消息",JOptionPane.WARNING_MESSAGE); //弹出提示信息
					}
                
				}
			}//mouseClicked()结束
		});
		
		freshButton.addActionListener(new ActionListener() {	//刷新按钮设置鼠标事件监听
			public void actionPerformed(ActionEvent e) {//当鼠标点击时
				if(surchNumber == 0) {				//如果目前所查询数据为全部查询
					List<students> NameList = DAOimpl.search(4, 8, null, LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
				}else if(surchNumber == 1) {				//如果目前所查询数据为姓名查询
					List<students> NameList = DAOimpl.search(4, 5, getFuzzySurch_t().getText(), LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
				}else if(surchNumber == 2) {				//如果目前所查询数据为学号查询
					List<students> NameList = DAOimpl.search(4, 2, getFuzzySurch_t().getText(),LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
				}else if(surchNumber == 3) {				//如果目前所查询数据为专业查询
					List<students> NameList = DAOimpl.search(4, 4, getFuzzySurch_t().getText(),LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
				}else if(surchNumber == 4) {				//如果目前所查询数据为班级查询
					List<students> NameList = DAOimpl.search(4, 3, getFuzzySurch_t().getText(),LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
				}else if(surchNumber == 5) {				//如果目前所查询数据为性别查询
					List<students> NameList = DAOimpl.search(4, 6, getFuzzySurch_t().getText(),LoginIdentity);
					table.setModel(assmbledModelSoure(NameList));
				}
				
		}//mouseClicked()结束
		});
		
		AllStudentButton.addActionListener(new ActionListener() {//全部学生按钮设置鼠标事件监听
			public void actionPerformed(ActionEvent e) {//当鼠标点击时
				List<students> NameList = DAOimpl.search(4, 8, null, LoginIdentity);
				table.setModel(assmbledModelSoure(NameList));
				surchNumber = 0;
			}//mouseClicked()结束
		});
	
		
		
		JPanel depPanel = new JPanel();
		tabbedPane.addTab("系部查询",depPanel);
		depPanel.setLayout(new BorderLayout(0, 0));
		
		//创建搜索面板
		JPanel dep_surchPanel = new JPanel();		
		depPanel.add(dep_surchPanel, BorderLayout.NORTH);
		
				
		dep_surchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		//建立搜索下拉框
		dep_surchType_combo = new JComboBox<String>();
		dep_surchPanel.add(dep_surchType_combo);
				
				
		String DepComboBoxData[] = new String[] { "专业名称","系名称","系主任"};	//下拉框数据
		DefaultComboBoxModel<String> DepSurchComboBoxModel = new DefaultComboBoxModel<String>(DepComboBoxData);//创建下拉框数据模型
		dep_surchType_combo.setModel(DepSurchComboBoxModel);
		
		//建立搜索文本框
		DepFuzzySurch_t = new JTextField();
		DepFuzzySurch_t.setColumns(20);
		dep_surchPanel.add(DepFuzzySurch_t);
		//添加搜索按钮
		JButton DepSurchButton = new JButton("搜索");
		dep_surchPanel.add(DepSurchButton);
		
		JPanel DepTablePanel = new JPanel();
		depPanel.add(DepTablePanel, BorderLayout.CENTER);
		DepTablePanel.setLayout(new BorderLayout(0, 0));
		
		DepTable = new FixedTable(); // 创建指定表格模型的表格
		DepTable.setToolTipText("鼠标双击查看详细信息");// 鼠标悬停提示
		DepTable.setCellEditable(false);//让表格不可编辑
		JScrollPane DepScrollPane = new JScrollPane(DepTable);// 给表格添加下拉框
		DepTablePanel.add(DepScrollPane, BorderLayout.CENTER);// 表格编办添加下拉框面板
		
		JLabel DepLblNewLabel = new JLabel("提示：双击鼠标查看提示信息。");// 提示标签
		DepTablePanel.add(DepLblNewLabel, BorderLayout.SOUTH);// 提示标签放到表格面板最南位置
		
		
		
		
		
	}
	
	

	/**
	 * 查询所有学生信息
	 * 
	 * return 学生信息表格数据模型
	 */
//	private DefaultTableModel getUsableModleSoure() {
//		List<students> NameList= DAOimpl.search(4, 8,"all student", LoginIdentity);
//		return assmbledModelSoure(usableList);						//返回所有学生表格数据模型
//	}
	
	
	



	/**
	 * 根据学生集合设定的表格数据模型
	 * usableList ：学生集合
	 * return 表格数据模型
	 */
	private DefaultTableModel assmbledModelSoure(List<students> usableList) {
		int StudentsCount = usableList.size();			//获取集合中的学生数量
		String[] columnNames = {"学号","姓名","性别","专业编号","班级编号","联系电话"};//定义表格列名数组
		String[][] tableValues = new String[StudentsCount][6];//创建表格列名数组
		for(int i = 0;i < StudentsCount;i++) {//遍历表格所有行
			students stu = usableList.get(i);//获取行用户对象
			tableValues[i][0] = "" + stu.getId();//第一列为学号
			tableValues[i][1] = "" + stu.getName();//第二列为名字
			tableValues[i][2] = "" + stu.getSex();//第三列为性别
			tableValues[i][3] = "" + stu.getMajorId();//第四列为专业编号
			tableValues[i][4] = "" + stu.getClassId();//第五列为班级编号
			tableValues[i][5] = "" + stu.getTelephoneNumber();//第五列为联系电话
		}
		DefaultTableModel tmp = new DefaultTableModel(tableValues,columnNames);//根据列名数组和数据数组创建表格数据模型
		return tmp;
	}
	
	/**
	 * 根据学生对象设定的表格数据模型
	 * usableList ：学生对象
	 * return 表格数据模型
	 */
	private DefaultTableModel assmbledModelSoure(students usableList) {
		String[] columnNames = {"学号","姓名","性别","专业编号","班级编号","联系电话"};//定义表格列名数组
		String[][] tableValues = new String[1][6];//创建表格列名数组
		for(int i = 0;i < 1;i++) {//遍历表格所有行
			students stu = usableList;//获取行用户对象
			tableValues[i][0] = "" + stu.getId();//第一列为学号
			tableValues[i][1] = "" + stu.getName();//第二列为名字
			tableValues[i][2] = "" + stu.getSex();//第三列为性别
			tableValues[i][3] = "" + stu.getMajorId();//第四列为专业编号
			tableValues[i][4] = "" + stu.getClassId();//第五列为班级编号
			tableValues[i][5] = "" + stu.getTelephoneNumber();//第五列为联系电话
		}
		DefaultTableModel tmp = new DefaultTableModel(tableValues,columnNames);//根据列名数组和数据数组创建表格数据模型
		return tmp;
	}
	
	/**
	 * 将传入的List内的数据导出为excel文件
	 * @param NameList：学生数据
	 */
	private void export(List<students> NameList) {
		AboutExcel exportExcel = new AboutExcel();
		JFileChooser fcDlg = new JFileChooser();
         fcDlg.setDialogTitle("请选择导出文件路径");
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
