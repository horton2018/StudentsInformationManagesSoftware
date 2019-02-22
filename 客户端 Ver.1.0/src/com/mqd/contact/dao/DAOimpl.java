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
	 * 连接服务端的方法
	 */
	public static void connect() { // 连接套接字方法
		
		try { // 捕捉异常
			socket = new Socket("192.168.1.3", 8998); // 实例化Socket对象
			outputStream = socket.getOutputStream();
			objectOS = new ObjectOutputStream(outputStream);
			inputStream = socket.getInputStream();
			objectIS = new ObjectInputStream(inputStream);			
     		} catch (Exception e) {
			e.printStackTrace(); // 输出异常信息
		}
	}
	
	
	
	/**
	 * 查询账号密码是否正确
	 * @param type：操作类型：查询
	 * @param account：账号、密码、权限
	 * @return：返回查询数据(person)
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
	 * 查询学生数据
	 * @param type：操作类型：查
	 * @param search_type：查询依据（学号、班级名称、姓名。。。。。）
	 * @param Field：查询字段
	 * @param identity：使用者身份
	 * @return：返回泛型为学生的List；
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
	 * 搜索班级id、名称及专业id、名称
	 * @param search_type：搜索依据
	 * @param Field：班级id、名称或专业id、名称
	 * @return 搜索结果（object）
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
	 * 搜索单个学生的所有信息
	 * @param type：操作类型：搜索
	 * @param search_type：搜索依据
	 * @param students：被搜索的学生
	 * @param identity：使用者身份
	 * return 返回学生数据(students)
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
	 * 删除学生数据
	 * @param type：操作类型：删除
	 * @param Field：删除学生学号
	 * @return：返回删除结果（boolean）
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
	 * 修改学生数据
	 * @param type：操作类型：修改
	 * @param students：修改后的学生数据（此学生类id为修改前的）
	 * @param newStudentID：修改后的学生id
	 * @return：返回修改结果（Boolean）
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
	 * 添加学生数据
	 * @param type：操作类型：添加
	 * @param students 添加的学生数据
	 * @return ：返回添加结果（Boolean）
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
	 * 接收服务端发来的结果
	 * 
	 * @return：返回object类型数据
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
