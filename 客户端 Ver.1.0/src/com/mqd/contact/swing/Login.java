package com.mqd.contact.swing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.mqd.contact.dao.DAOimpl;
import pojo.person;




public class Login extends JFrame implements ActionListener {
	
		//定义组件
		JPanel accountPanel,passwordPanel,buttonPanel=null;	//定义面板
		JLabel accountLabel,passwordLabel,limitLabel=null;		//定义标签
		JButton loginButton,resetButton,dsipButton=null;       //定义按钮
		JTextField accountTextField=null;        //定义文本框
		JPasswordField passwordTextField=null;    //定义密码框
		private JComboBox<String> limit_combo;	//搜索下拉框

		
		
		
		
		//定义构造方法
		public Login() {    //设置登陆界面静态实现
			//创建操作面板
			accountPanel = new JPanel();
			passwordPanel = new JPanel();
			buttonPanel = new JPanel();
			//建立权限选择面板
			JPanel limitPanel = new JPanel();

			//创建标签
			accountLabel = new JLabel("用户名:");
			passwordLabel = new JLabel("密  码:");
			limitLabel = new JLabel("权 限：");
			
			
			
			//建立搜索下拉框
			limit_combo = new JComboBox();
			limitPanel.add(limitLabel);
			limitPanel.add(limit_combo);
			String comboBoxData[] = new String[] {"学生","老师"};	//下拉框数据
			DefaultComboBoxModel limitComboBoxModel = new DefaultComboBoxModel(comboBoxData);// 创建下拉框数据模型
			limit_combo.setModel(limitComboBoxModel);
			
			
			loginButton = new JButton("登 录");
			resetButton = new JButton("重 置");
			dsipButton = new JButton("退 出");
			

			
				//设置监听  
			loginButton.addActionListener(this);  //将登录按钮监听放在了actionPerformance()中
	        resetButton.addActionListener(this);  //将重置按钮监听放在了actionPerformance()中
	        dsipButton.addActionListener(this);  //将退出按钮监听放在了actionPerformance()中
	        
			
	     
			//创建用户名文本框、密码框
			accountTextField = new JTextField(15);
			passwordTextField = new JPasswordField(15);
			
			
			
			//加载各组件
			accountPanel.add(accountLabel);   // 加载“用户名”标签
			accountPanel.add(accountTextField);    // 加载用户名文本框
			passwordPanel.add(passwordLabel);   //加载“密码”标签
			passwordPanel.add(passwordTextField);    //加载密码框
			buttonPanel.add(loginButton);	 //加载“登录”按钮监听
			buttonPanel.add(resetButton);    //加载“重置”按钮监听
			buttonPanel.add(dsipButton);    //加载“退出”按钮监听
			
			
			
			//将操作面板加载到JFrame窗口
			getContentPane().add(accountPanel);
			getContentPane().add(passwordPanel);
			getContentPane().add(limitPanel);
			getContentPane().add(buttonPanel);
			
			
			
			//设置JFrame窗口窗体
			getContentPane().setLayout(new GridLayout(4,1));//设置布局管理器    网格式布局
			this.setTitle("学生信息管理软件");  //设置窗体标题
			this.setSize(400, 250);     //设置窗体大小
			this.setLocationRelativeTo(null);//设置窗体居中显示
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击退出同时关闭进程
			this.setVisible(true);		//设置窗体可见
			this.setResizable(false);   //设置窗体锁定

		}
		
		
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {	
			if(e.getActionCommand()=="退 出") {//点击退出按钮后执行
				System.exit(0);
			}else if(e.getActionCommand()=="登 录") {//点击登录按钮后执行
				
				
				if(!(accountTextField.getText().length()==0)&&!(passwordTextField.getPassword().length==0)) {    //如果用户名 密码不为空
					
					//依据填写的账号和传入的权限进行查找
					person person = new person();
					person.setId(accountTextField.getText());
					person.setLimit(getLimit_combo().getSelectedItem().toString());
					person id = DAOimpl.Login(4,1,person);
					if(id.getPassword()==null) {
						JOptionPane.showMessageDialog(null,"无此账号 ，请正确输入","提示消息",JOptionPane.WARNING_MESSAGE);
					}else if(id.getId().equals(accountTextField.getText())&&id.getPassword().equals(passwordTextField.getText())) {
						dispose();//关闭当前页面
						id.setLimit(getLimit_combo().getSelectedItem().toString());//设置登录账号的权限	
						MainFrame t1 = new MainFrame(id);
					}else{
						JOptionPane.showMessageDialog(null,"请输入正确账号  密码！！！","提示消息",JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null,"请输入账号  密码！！！","提示消息",JOptionPane.WARNING_MESSAGE);
				}
				
				
				
				
				
			}else if(e.getActionCommand()=="重 置") {//点击重置按钮后执行
				this.clear();
			}
			
		}

		public void clear() {   //清空用户名文本框、密码框
			accountTextField.setText("");
			passwordTextField.setText("");
		}
		
		public JComboBox getLimit_combo() {
			// TODO 自动生成的方法存根
			return limit_combo;
		}
		
}