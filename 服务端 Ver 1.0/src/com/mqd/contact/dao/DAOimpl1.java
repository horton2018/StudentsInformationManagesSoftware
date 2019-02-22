package com.mqd.contact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.Driver;
import pojo.*;

public class DAOimpl1 {
	
	static Connection con;
	static PreparedStatement sql;
	static ResultSet res;
	
	/**
	 * �������ݿ�����
	 */
	public static void ConnectSQL() {   //�����������ݿ�ķ���   ����������ļ̳�	
		try {
			Driver driver = new Driver();
			String url = "jdbc:mysql://localhost:3306/information?serverTimezone=GMT%2B8";
			Properties info = new Properties();
			info.put("user","root");
			info.put("password", "hq441521hq");
			con = driver.connect(url, info);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ر����ݿ�����
	 */
	public static void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				con =null;
			}
		}
	}
	
	/**
	 * ���ѧ����Ϣ
	 * @param stu ����ӵ�ѧ������
	 * @return ������booleanֵ
	 */
	public static boolean addStudent(students stu) {
		boolean result = false;
		try {
			ConnectSQL();
			sql = con.prepareStatement("INSERT INTO students VALUES('"+stu.getId()+"','"+stu.getName()+"','"+stu.getSex()
										+"','"+stu.getClassId()+"','"+stu.getTelephoneNumber()+"','"+stu.getAddress()+
										"','123456')");//���Statement����
			sql.executeUpdate();//�����ݿⷢ��sql���
			result = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
	
	/**
	 * ����ѧ������Ϣ
	 * @param id ������ѧ����id����
	 * @return ����ѧ������
	 */
	public static students selectUseStudentID(String id,person identity) {
		ConnectSQL();
		students stu= new students();
		try {
			if(identity.getLimit().equals("��ʦ")) {				
			sql = con.prepareStatement("SELECT * FROM students s INNER JOIN class c ON s.�༶��� = c.�༶��� WHERE ID = "+ id);
			res = sql.executeQuery();//�����ݿⷢ����ѯ���
		while(res.next()) {
			stu = new students(id,res.getString(2),res.getString(3),res.getString(4),
					res.getString(5),res.getString(6),res.getString(7),res.getString(11));
			}	
		}else if(identity.getLimit().equals("ѧ��")) {
			if(identity instanceof students) {
				students students = (students) identity;
				sql = con.prepareStatement("select �༶��� from students where ID = "+ students.getId());//�Ȳ�ѯ�༶���
				res = sql.executeQuery();//�����ݿⷢ����ѯ���
				while(res.next()) {
					students.setClassId(res.getString(1));//���ð༶���
				}
				//���ݰ༶��ź�ѧ�Ž��в��ң����Ʋ��ҷ�Χ�ڰ༶��Χ��
				sql = con.prepareStatement("SELECT * FROM students s INNER JOIN class c ON s.�༶��� = c.�༶��� WHERE ID = "+ id +" and s.�༶��� = "+students.getClassId());
				res = sql.executeQuery();//�����ݿⷢ����ѯ���
				while(res.next()) {
					stu = new students(res.getString(1),res.getString(2),res.getString(3),
					res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(11));
				}
			}
			
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		return stu;
	}
	
	/**
	 * ����ѧ������Ϣ
	 * @param name ������ѧ������������
	 * @param identity �������½�˺�
	 * @return ����ѧ������
	 */
	public static List<students> selectUseStudentName(String name,person identity) {
		ConnectSQL();
		List<students> stu = new ArrayList();
		try {
			if(identity.getLimit().equals("��ʦ")) {		
				sql = con.prepareStatement("select * from students s inner join class c on s.�༶��� = c.�༶��� where ���� like '%"+ name +"%'");
				res = sql.executeQuery();//�����ݿⷢ����ѯ���
				
				
				while (res.next()) {
						stu.add(new students(res.getString(1),res.getString(2),res.getString(3),
								res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(11)));
					}
				
			}else if(identity.isMonitor()) {
				if(identity instanceof students) {
					students students = (students) identity;
					sql = con.prepareStatement("select �༶��� from students where ID = "+ students.getId());//�Ȳ�ѯ�༶���
					res = sql.executeQuery();//�����ݿⷢ����ѯ���
					while(res.next()) {
						students.setClassId(res.getString(1));//���ð༶���
					}
					//���ݰ༶��ź��������в��ң����Ʋ��ҷ�Χ�ڰ༶��Χ��
					sql = con.prepareStatement("select * from students s inner join class c on s.�༶��� = c.�༶��� where ���� like '%"+ name +"%' and s.�༶��� = "+students.getClassId());
					res = sql.executeQuery();//�����ݿⷢ����ѯ���
					while(res.next()) {
						stu.add(new students(res.getString(1),res.getString(2),res.getString(3),
						res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(11)));
					}
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return stu;
	}
	
	
	
	
	/**
	 * ����ѧ������Ϣ
	 * @param name ������ѧ����רҵ���Ʋ���
	 * @return ����ѧ�����󼯺�
	 */
	public static List<students> selectUseStudentMajorName(String majorName) {
		ConnectSQL();
		List<students> stu = new ArrayList();
		try {
			sql = con.prepareStatement("select * from majores where רҵ����  like '%"+ majorName+"%'");//�ȸ���רҵ���Ʋ���רҵ���(רҵ����)
			res = sql.executeQuery();//�����ݿⷢ����ѯ���
			while(res.next()) {
				String majorId = res.getString(1);
				sql = con.prepareStatement("select * from class where רҵ��� like '%"+ majorId+"%'");//����רҵ��Ų��Ұ༶���(�༶����)
				res = sql.executeQuery();//�����ݿⷢ����ѯ���
				
				while(res.next()) {
					String classId = res.getString(1);
					sql = con.prepareStatement("select * from students where �༶��� like '%"+ classId+"%'");//���ݰ༶��Ų���ѧ������(ѧ������)
					res = sql.executeQuery();//�����ݿⷢ����ѯ���
					while(res.next()) {
						stu.add(new students(res.getString(1),res.getString(2),res.getString(3),
							res.getString(4),res.getString(5),res.getString(6),res.getString(7),majorId));
					}
		}
			}
		
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		close();
		return stu;
	}
	
	/**
	 * ����ѧ������Ϣ
	 * @param name ������ѧ�����Ա����
	 * @return ����ѧ�����󼯺�
	 */
	public static List<students> selectUseStudentSex(String sex,person identity) {
		ConnectSQL();
		List<students> stu = new ArrayList();
		try {
		if(identity.getLimit().equals("��ʦ")) {		
			sql = con.prepareStatement("select * from students s inner join class c on s.�༶��� = c.�༶��� where �Ա� like '%"+sex+"%'");
			res = sql.executeQuery();//�����ݿⷢ����ѯ���
			while(res.next()) {
				stu.add(new students(res.getString(1),res.getString(2),res.getString(3),
						res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(11)));
			}
		}else if(identity.isMonitor()) {
			if(identity instanceof students) {
				students students = (students) identity;
				sql = con.prepareStatement("select �༶��� from students where ID = "+ students.getId());//�Ȳ�ѯ�༶���
				res = sql.executeQuery();//�����ݿⷢ����ѯ���
				while(res.next()) {
					students.setClassId(res.getString(1));//���ð༶���
				}
				//���ݰ༶��ź��Ա���в��ң����Ʋ��ҷ�Χ�ڰ༶��Χ��
				sql = con.prepareStatement("select * from students s inner join class c on s.�༶��� = c.�༶���  where �Ա� like '%"+ sex +"%' and s.�༶��� = "+students.getClassId());
				res = sql.executeQuery();//�����ݿⷢ����ѯ���
				while(res.next()) {
					stu.add(new students(res.getString(1),res.getString(2),res.getString(3),
					res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(11)));
				}
			}
			
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return stu;
	}
	
	/**
	 * ����ѧ������Ϣ
	 * @param name ������ѧ���ĵ绰����
	 * @return ����ѧ�����󼯺�
	 */
	public static List<students> selectUseStudentTelephoneNumber(String telephoneNumber,person identity) {
		ConnectSQL();
		List<students> stu = new ArrayList();
		try {
		if(identity.getLimit().equals("��ʦ")) {		
			sql = con.prepareStatement("select * from students s inner join class c on s.�༶��� = c.�༶��� where ��ϵ�绰 like '"+ telephoneNumber+"%'");
			res = sql.executeQuery();//�����ݿⷢ����ѯ���
			while(res.next()) {
				stu.add(new students(res.getString(1),res.getString(2),res.getString(3),
						res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(11)));
			}
		}else if(identity.isMonitor()) {
			if(identity instanceof students) {
				students students = (students) identity;
				sql = con.prepareStatement("select �༶��� from students where ID = "+ students.getId());//�Ȳ�ѯ�༶���
				res = sql.executeQuery();//�����ݿⷢ����ѯ���
				while(res.next()) {
					students.setClassId(res.getString(1));//���ð༶���
				}
				//���ݰ༶��ź͵绰������в��ң����Ʋ��ҷ�Χ�ڰ༶��Χ��
				sql = con.prepareStatement("select * from students s inner join class c on s.�༶��� = c.�༶���  where ��ϵ�绰 like '"+ telephoneNumber +"%' and s.�༶��� = "+students.getClassId());
				res = sql.executeQuery();//�����ݿⷢ����ѯ���
				while(res.next()) {
					stu.add(new students(res.getString(1),res.getString(2),res.getString(3),
					res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(11)));
				}
			}
			
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return stu;
	}
	
	/**
	 * ����ѧ������Ϣ
	 * @param name ������ѧ���İ༶���Ʋ���
	 * @return ����ѧ������
	 */
	public static List<students> selectUseStudentClassName(String className) {
		ConnectSQL();
		List<students> stu = new ArrayList(); 
		String classId = null;
		try {
			sql = con.prepareStatement("select * from class where �༶���� like"+ "'%"+className+"%'");
			res = sql.executeQuery();//�����ݿⷢ����ѯ���
			while(res.next()) {
			classId = res.getString(1);
			sql = con.prepareStatement("select * from students s inner join class c on s.�༶��� = c.�༶���  where s.�༶��� = "+classId);
			res = sql.executeQuery();//�����ݿⷢ����ѯ���
			while(res.next()) {
			stu.add(new students(res.getString(1),res.getString(2),res.getString(3),
					res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(11)));
		}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		return stu;
	}
	
	
	/**
	 * ����ѧ����������Ϣ
	 * @param stu �� ������ҵ�ѧ������
	 * @return  ����ѧ������
	 */
	public static students slectStudentAll(students stu) {
		ConnectSQL();
		try {
			sql = con.prepareStatement("select * from class where �༶��� like '%"+stu.getClassId()+"%'");
			res = sql.executeQuery();
			while(res.next()) {
				String className = res.getString(2);//��ð༶����
				stu.setClassName(className);//���ð༶����
				String MajorId = res.getString(4);//���רҵ���
				sql = con.prepareStatement("select * from majores where רҵ��� like '%"+MajorId+"%'");
				res = sql.executeQuery();
				while(res.next()) {
				stu.setMajorName(res.getString(2));
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}//�����ݿⷢ����ѯ���
		close();
		return stu;
	}
	
	/**
	 * ��������ѧ��
	 * 
	 * @return  ����ѧ�����󼯺�
	 */
	public static List<students> slectAllStudent() {
		ConnectSQL();
		List<students> stu = new ArrayList();
		
		try {
			sql = con.prepareStatement("SELECT * FROM students s INNER JOIN class c ON s.�༶��� = c.�༶���");
			res = sql.executeQuery();
			while(res.next()) {
				stu.add((new students(res.getString(1),res.getString(2),res.getString(3),
						res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(11))));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//�����ݿⷢ����ѯ���
		close();
		return stu;
	}
	
	
	/**
	 * ��ѯ�û���¼�˺�����
	 * @param account ���û��˻�
	 * @param identity ����ݱ�ʶ
	 * @return ����person����
	 */
	public static person selectUser(String account,String identity) {
		ConnectSQL();
		person id = null;
		
		if("��ʦ".equals(identity)) {
			try {
				id = new teacher();
				id.setId(account);
				sql = con.prepareStatement("select * from teacher where ��ʦ��� ="+ account);
				res = sql.executeQuery();//�����ݿⷢ����ѯ���
				while(res.next()) {
						id.setPassword(res.getString(5));
					}	
				} catch (SQLException e) {
			e.printStackTrace();
				}			
		}else if("ѧ��".equals(identity)) {
			try {
				id = new students();
				id.setId(account);
				sql = con.prepareStatement("select * from students where ID ="+ account);
				res = sql.executeQuery();//�����ݿⷢ����ѯ���
				while(res.next()) {
						id.setPassword(res.getString(7));
						sql = con.prepareStatement("select * from class where �೤��� = "+ id.getId());
						res = sql.executeQuery();//�����ݿⷢ����ѯ���
						while(res.next()) {
							id.setMonitor(true);
						}
					}	
				} catch (SQLException e) {
			e.printStackTrace();
				}
		}					
		close();
		return id;
	}
	
	
	/**
	 * �޸��û�����
	 * @param identity  ���������
	 * @param identity1 : ��ݱ�ʶ
	 * @return
	 */
	public static boolean changeUserPassword(person identity,String indentity1) {
		boolean result = false;
		ConnectSQL();
		if("teacher".equals(indentity1)) {
			try {
			sql = con.prepareStatement("update teacher set ���� = '"+identity.getPassword()+ "' where ��ʦ��� = '"+identity.getId()+"';");
			sql.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}else if("student".equals(indentity1)) {
			try {
				sql = con.prepareStatement("update students set ���� = '"+identity.getPassword()+ "' where ID = '"+identity.getId()+"';");
				sql.executeUpdate();
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close();
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ����ѧУרҵ
	 * @param MajorName ������רҵ���Ʋ���
	 * @return ����רҵ����
	 */
	public static major selectUseMajorName(String MajorName) {
		ConnectSQL();
		major ma = new major();
		try {
			sql = con.prepareStatement("select * from majores where רҵ���� like '%"+ MajorName+"%'");
			res = sql.executeQuery();//�����ݿⷢ����ѯ���
		while(res.next()) {
			ma = new major(res.getString(1),res.getString(2),res.getString(3),res.getString(4));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return ma;
	}
	
	/**
	 * ����ѧУרҵ
	 * @param MajorName ������רҵ��Ų���
	 * @return ����רҵ����
	 */
	public static major selectUseMajorID(String MajorID) {
		ConnectSQL();
		major ma = new major();
		try {
			sql = con.prepareStatement("select * from majores where רҵ��� like '%"+ MajorID+"%'");
			res = sql.executeQuery();//�����ݿⷢ����ѯ���
		while(res.next()) {
			ma = new major(res.getString(1),res.getString(2),res.getString(3),res.getString(4));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return ma;
	}
	
	/**
	 * ��ѯ�༶
	 * @param className �� ���ݰ༶���Ʋ�ѯ�༶
	 * @return ���ذ༶����
	 */
	public static className selectUseClassName(String className) {
		
		className cn = new className();
		try {
			ConnectSQL();
			String selectsql="select * from class where �༶���� like '%"+ className+"%'";
			sql = con.prepareStatement(selectsql);
			res = sql.executeQuery();//�����ݿⷢ����ѯ���
		while(res.next()) {
			cn = new className(res.getString(1),res.getString(2),res.getString(3),res.getString(4));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return cn;
	}
	
	/**
	 * ��ѯ�༶
	 * @param className �� ���ݰ༶ID��ѯ�༶
	 * @return ���ذ༶���󼯺�
	 */
	public static className selectUseClassID(String classID) {
		ConnectSQL();
		className cn = new className();
		try {
			sql = con.prepareStatement("select * from class where �༶��� like '%"+ classID+"%'");
			res = sql.executeQuery();//�����ݿⷢ����ѯ���
		while(res.next()) {
			cn = new className(res.getString(1),res.getString(2),res.getString(3),res.getString(4));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return cn;
	}
	
	
	/**
	 * ɾ��ѧ����Ϣ
	 * return �� ����Booleanֵ
	 */
	public static boolean deleteStudent(String id) {
		boolean result = false;
		try {
			ConnectSQL();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("delete from students where id = "+id);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return result;
	}

	/**
	 * �޸�ѧ����Ϣ
	 * return �� ����Boolean
	 */
	public static boolean changeStudent(students stu,String newStudentsId) {
		boolean result = false;
		try {
			ConnectSQL();
			sql = con.prepareStatement("update students set id = '"+newStudentsId+"',���� = '"+stu.getName()+
					"',�Ա�  ='"+stu.getSex()+ "',�༶���  ='"+stu.getClassId()+ "',��ϵ�绰 = '" +stu.getTelephoneNumber()+ 
					"',��ͥסַ = '" +stu.getAddress()+ "' where id = '"+stu.getId()+"';");
			sql.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
}
