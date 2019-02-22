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
	
		//�������
		JPanel accountPanel,passwordPanel,buttonPanel=null;	//�������
		JLabel accountLabel,passwordLabel,limitLabel=null;		//�����ǩ
		JButton loginButton,resetButton,dsipButton=null;       //���尴ť
		JTextField accountTextField=null;        //�����ı���
		JPasswordField passwordTextField=null;    //���������
		private JComboBox<String> limit_combo;	//����������

		
		
		
		
		//���幹�췽��
		public Login() {    //���õ�½���澲̬ʵ��
			//�����������
			accountPanel = new JPanel();
			passwordPanel = new JPanel();
			buttonPanel = new JPanel();
			//����Ȩ��ѡ�����
			JPanel limitPanel = new JPanel();

			//������ǩ
			accountLabel = new JLabel("�û���:");
			passwordLabel = new JLabel("��  ��:");
			limitLabel = new JLabel("Ȩ �ޣ�");
			
			
			
			//��������������
			limit_combo = new JComboBox();
			limitPanel.add(limitLabel);
			limitPanel.add(limit_combo);
			String comboBoxData[] = new String[] {"ѧ��","��ʦ"};	//����������
			DefaultComboBoxModel limitComboBoxModel = new DefaultComboBoxModel(comboBoxData);// ��������������ģ��
			limit_combo.setModel(limitComboBoxModel);
			
			
			loginButton = new JButton("�� ¼");
			resetButton = new JButton("�� ��");
			dsipButton = new JButton("�� ��");
			

			
				//���ü���  
			loginButton.addActionListener(this);  //����¼��ť����������actionPerformance()��
	        resetButton.addActionListener(this);  //�����ð�ť����������actionPerformance()��
	        dsipButton.addActionListener(this);  //���˳���ť����������actionPerformance()��
	        
			
	     
			//�����û����ı��������
			accountTextField = new JTextField(15);
			passwordTextField = new JPasswordField(15);
			
			
			
			//���ظ����
			accountPanel.add(accountLabel);   // ���ء��û�������ǩ
			accountPanel.add(accountTextField);    // �����û����ı���
			passwordPanel.add(passwordLabel);   //���ء����롱��ǩ
			passwordPanel.add(passwordTextField);    //���������
			buttonPanel.add(loginButton);	 //���ء���¼����ť����
			buttonPanel.add(resetButton);    //���ء����á���ť����
			buttonPanel.add(dsipButton);    //���ء��˳�����ť����
			
			
			
			//�����������ص�JFrame����
			getContentPane().add(accountPanel);
			getContentPane().add(passwordPanel);
			getContentPane().add(limitPanel);
			getContentPane().add(buttonPanel);
			
			
			
			//����JFrame���ڴ���
			getContentPane().setLayout(new GridLayout(4,1));//���ò��ֹ�����    ����ʽ����
			this.setTitle("ѧ����Ϣ�������");  //���ô������
			this.setSize(400, 250);     //���ô����С
			this.setLocationRelativeTo(null);//���ô��������ʾ
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����˳�ͬʱ�رս���
			this.setVisible(true);		//���ô���ɼ�
			this.setResizable(false);   //���ô�������

		}
		
		
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {	
			if(e.getActionCommand()=="�� ��") {//����˳���ť��ִ��
				System.exit(0);
			}else if(e.getActionCommand()=="�� ¼") {//�����¼��ť��ִ��
				
				
				if(!(accountTextField.getText().length()==0)&&!(passwordTextField.getPassword().length==0)) {    //����û��� ���벻Ϊ��
					
					//������д���˺źʹ����Ȩ�޽��в���
					person person = new person();
					person.setId(accountTextField.getText());
					person.setLimit(getLimit_combo().getSelectedItem().toString());
					person id = DAOimpl.Login(4,1,person);
					if(id.getPassword()==null) {
						JOptionPane.showMessageDialog(null,"�޴��˺� ������ȷ����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					}else if(id.getId().equals(accountTextField.getText())&&id.getPassword().equals(passwordTextField.getText())) {
						dispose();//�رյ�ǰҳ��
						id.setLimit(getLimit_combo().getSelectedItem().toString());//���õ�¼�˺ŵ�Ȩ��	
						MainFrame t1 = new MainFrame(id);
					}else{
						JOptionPane.showMessageDialog(null,"��������ȷ�˺�  ���룡����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null,"�������˺�  ���룡����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
				}
				
				
				
				
				
			}else if(e.getActionCommand()=="�� ��") {//������ð�ť��ִ��
				this.clear();
			}
			
		}

		public void clear() {   //����û����ı��������
			accountTextField.setText("");
			passwordTextField.setText("");
		}
		
		public JComboBox getLimit_combo() {
			// TODO �Զ����ɵķ������
			return limit_combo;
		}
		
}