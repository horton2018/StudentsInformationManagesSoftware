package com.mqd.contact.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import pojo.className;
import pojo.major;
import pojo.person;
import pojo.project;
import pojo.students;

public class DAOimpl {
	private static Socket socket;
	private static InputStream inputStream;
	private static ObjectInputStream objectIS;
	private static OutputStream outputStream;
	private static ObjectOutputStream objectOS;
	
	
	/**
	 * ���ӷ���˵ķ���
	 */
	public static void connect() { // �����׽��ַ���
		
		try { // ��׽�쳣
			socket = new Socket("192.168.1.3", 8998); // ʵ����Socket����
			outputStream = socket.getOutputStream();
			objectOS = new ObjectOutputStream(outputStream);
			inputStream = socket.getInputStream();
			objectIS = new ObjectInputStream(inputStream);			
     		} catch (Exception e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
	}
	
	
	
	/**
	 * ��ѯ�˺������Ƿ���ȷ
	 * @param type���������ͣ���ѯ
	 * @param account���˺š����롢Ȩ��
	 * @return�����ز�ѯ����(person)
	 */
	public static person Login(int type,int search_type,person account) {
		connect();
		project project = new project(type,search_type, account);
		person result = null;
		try {
			objectOS.writeObject(project);
			objectOS.flush();
			Object object = getMessage();
			if (object != null) {
				if (object instanceof person) {
					result = (person)object;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return result;
		
	}
	
	
	/**
	 * ��ѯѧ������
	 * @param type���������ͣ���
	 * @param search_type����ѯ���ݣ�ѧ�š��༶���ơ�����������������
	 * @param Field����ѯ�ֶ�
	 * @param identity��ʹ�������
	 * @return�����ط���Ϊѧ����List��
	 */
	public static List<students> search(int type,int search_type,String Field,person identity){
		connect();
		project project = new project(type, search_type, Field, identity);
		List<students> stu = new ArrayList<students>();
		try {
			objectOS.writeObject(project);
			objectOS.flush();
			int cycle_index = objectIS.readInt();
			if (cycle_index == 1) {
				Object object = getMessage();
				if (object != null) {
					if (object instanceof students) {
						students stu1 = (students)object;
						stu.add(stu1);
					}
				}
			} else if(cycle_index > 1){
				for (int i = 0; i < cycle_index; i++) {
					Object object = getMessage();
					if (object != null) {
						if (object instanceof students) {
							students stu1 = (students)object;
							stu.add(stu1);
						}
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return stu;
	}
	
	
	/**
	 * �����༶id�����Ƽ�רҵid������
	 * @param search_type����������
	 * @param Field���༶id�����ƻ�רҵid������
	 * @return ���������object��
	 */
	public static Object search_other(int search_type,String Field) {
		connect();
		project project = new project(4, 10, Field, null);
		Object object = null;
		try {
			objectOS.writeObject(project);
			objectOS.flush();
			int cycle_index = objectIS.readInt();
			if (cycle_index == 1) {
				object = getMessage();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return object;
	}
	
	/**
	 * ��������ѧ����������Ϣ
	 * @param type���������ͣ�����
	 * @param search_type����������
	 * @param students����������ѧ��
	 * @param identity��ʹ�������
	 * return ����ѧ������(students)
	 */
	public static students search1(int type,int search_type,students students,person identity) {
		connect();
		project project = new project(type, search_type, students, identity);
		try {
			objectOS.writeObject(project);
			objectOS.flush();
			int cycle_index = objectIS.readInt();
			if (cycle_index == 1) {
				Object object = getMessage();
				if (object != null) {
					if (object instanceof students) {
						students = (students)object;
					}
				}
			} else if(cycle_index > 1){
				for (int i = 0; i < cycle_index; i++) {
					Object object = getMessage();
					if (object != null) {
						if (object instanceof students) {
							students = (students)object;
						}
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return students;
	} 
	
	
	/**
	 * ɾ��ѧ������
	 * @param type���������ͣ�ɾ��
	 * @param Field��ɾ��ѧ��ѧ��
	 * @return������ɾ�������boolean��
	 */
	public static Boolean deleteStudents(String Field) {
		connect();
		project project = new project(3, Field);
		Boolean result = null;
		try {
			objectOS.writeObject(project);
			objectOS.flush();
			Object object = getMessage();
			if (object != null) {
				if (object instanceof Boolean) {
					result = (Boolean)object;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return result;
	}
	
	
	
	/**
	 * �޸�ѧ������
	 * @param type���������ͣ��޸�
	 * @param students���޸ĺ��ѧ�����ݣ���ѧ����idΪ�޸�ǰ�ģ�
	 * @param newStudentID���޸ĺ��ѧ��id
	 * @return�������޸Ľ����Boolean��
	 */
	public static Boolean changeStudents(students students,String newStudentID) {
		connect();
		project project = new project(2, students, newStudentID);
		Boolean result = null;
		try {
			objectOS.writeObject(project);
			objectOS.flush();
			Object object = getMessage();
			if (object != null) {
				if (object instanceof Boolean) {
					result = (Boolean)object;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return result;
	}
	
	
	/**
	 * ���ѧ������
	 * @param type���������ͣ����
	 * @param students ��ӵ�ѧ������
	 * @return ��������ӽ����Boolean��
	 */
	public static Boolean addStudents(students students) {
		connect();
		project project = new project(1, students, null);
		Boolean result = null;
		try {
			objectOS.writeObject(project);
			objectOS.flush();
			Object object = getMessage();
			if (object != null) {
				if (object instanceof Boolean) {
					result = (Boolean)object;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return result;
	}
	
	
	
	/**
	 * ���շ���˷����Ľ��
	 * 
	 * @return������object��������
	 */
	public static Object getMessage() {
		Object object = null;
		try {
			object = objectIS.readObject();
			
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
	
	public static void close() {
		try {
			if(objectIS != null) {
				objectIS.close();
				
			}
			if (objectOS != null) {
				objectOS.close();
			}
			if(socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
