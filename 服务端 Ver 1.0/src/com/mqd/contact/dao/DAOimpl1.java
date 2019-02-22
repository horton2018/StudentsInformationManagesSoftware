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
	 * 开启数据库连接
	 */
	public static void ConnectSQL() {   //用于连接数据库的方法   可用于子类的继承	
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
	 * 关闭数据库连接
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
	 * 添加学生信息
	 * @param stu ：添加的学生对象
	 * @return ：返回boolean值
	 */
	public static boolean addStudent(students stu) {
		boolean result = false;
		try {
			ConnectSQL();
			sql = con.prepareStatement("INSERT INTO students VALUES('"+stu.getId()+"','"+stu.getName()+"','"+stu.getSex()
										+"','"+stu.getClassId()+"','"+stu.getTelephoneNumber()+"','"+stu.getAddress()+
										"','123456')");//获得Statement对象
			sql.executeUpdate();//向数据库发出sql语句
			result = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
	
	/**
	 * 查找学生的信息
	 * @param id ：根据学生的id查找
	 * @return 返回学生对象
	 */
	public static students selectUseStudentID(String id,person identity) {
		ConnectSQL();
		students stu= new students();
		try {
			if(identity.getLimit().equals("老师")) {				
			sql = con.prepareStatement("SELECT * FROM students s INNER JOIN class c ON s.班级编号 = c.班级编号 WHERE ID = "+ id);
			res = sql.executeQuery();//向数据库发出查询语句
		while(res.next()) {
			stu = new students(id,res.getString(2),res.getString(3),res.getString(4),
					res.getString(5),res.getString(6),res.getString(7),res.getString(11));
			}	
		}else if(identity.getLimit().equals("学生")) {
			if(identity instanceof students) {
				students students = (students) identity;
				sql = con.prepareStatement("select 班级编号 from students where ID = "+ students.getId());//先查询班级编号
				res = sql.executeQuery();//向数据库发出查询语句
				while(res.next()) {
					students.setClassId(res.getString(1));//设置班级编号
				}
				//依据班级编号和学号进行查找，限制查找范围在班级范围内
				sql = con.prepareStatement("SELECT * FROM students s INNER JOIN class c ON s.班级编号 = c.班级编号 WHERE ID = "+ id +" and s.班级编号 = "+students.getClassId());
				res = sql.executeQuery();//向数据库发出查询语句
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
	 * 查找学生的信息
	 * @param name ：根据学生的姓名查找
	 * @param identity ：传入登陆账号
	 * @return 返回学生对象
	 */
	public static List<students> selectUseStudentName(String name,person identity) {
		ConnectSQL();
		List<students> stu = new ArrayList();
		try {
			if(identity.getLimit().equals("老师")) {		
				sql = con.prepareStatement("select * from students s inner join class c on s.班级编号 = c.班级编号 where 姓名 like '%"+ name +"%'");
				res = sql.executeQuery();//向数据库发出查询语句
				
				
				while (res.next()) {
						stu.add(new students(res.getString(1),res.getString(2),res.getString(3),
								res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(11)));
					}
				
			}else if(identity.isMonitor()) {
				if(identity instanceof students) {
					students students = (students) identity;
					sql = con.prepareStatement("select 班级编号 from students where ID = "+ students.getId());//先查询班级编号
					res = sql.executeQuery();//向数据库发出查询语句
					while(res.next()) {
						students.setClassId(res.getString(1));//设置班级编号
					}
					//依据班级编号和姓名进行查找，限制查找范围在班级范围内
					sql = con.prepareStatement("select * from students s inner join class c on s.班级编号 = c.班级编号 where 姓名 like '%"+ name +"%' and s.班级编号 = "+students.getClassId());
					res = sql.executeQuery();//向数据库发出查询语句
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
	 * 查找学生的信息
	 * @param name ：根据学生的专业名称查找
	 * @return 返回学生对象集合
	 */
	public static List<students> selectUseStudentMajorName(String majorName) {
		ConnectSQL();
		List<students> stu = new ArrayList();
		try {
			sql = con.prepareStatement("select * from majores where 专业名称  like '%"+ majorName+"%'");//先根据专业名称查找专业编号(专业表中)
			res = sql.executeQuery();//向数据库发出查询语句
			while(res.next()) {
				String majorId = res.getString(1);
				sql = con.prepareStatement("select * from class where 专业编号 like '%"+ majorId+"%'");//依据专业编号查找班级编号(班级表中)
				res = sql.executeQuery();//向数据库发出查询语句
				
				while(res.next()) {
					String classId = res.getString(1);
					sql = con.prepareStatement("select * from students where 班级编号 like '%"+ classId+"%'");//依据班级编号查找学生对象(学生表中)
					res = sql.executeQuery();//向数据库发出查询语句
					while(res.next()) {
						stu.add(new students(res.getString(1),res.getString(2),res.getString(3),
							res.getString(4),res.getString(5),res.getString(6),res.getString(7),majorId));
					}
		}
			}
		
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		close();
		return stu;
	}
	
	/**
	 * 查找学生的信息
	 * @param name ：根据学生的性别查找
	 * @return 返回学生对象集合
	 */
	public static List<students> selectUseStudentSex(String sex,person identity) {
		ConnectSQL();
		List<students> stu = new ArrayList();
		try {
		if(identity.getLimit().equals("老师")) {		
			sql = con.prepareStatement("select * from students s inner join class c on s.班级编号 = c.班级编号 where 性别 like '%"+sex+"%'");
			res = sql.executeQuery();//向数据库发出查询语句
			while(res.next()) {
				stu.add(new students(res.getString(1),res.getString(2),res.getString(3),
						res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(11)));
			}
		}else if(identity.isMonitor()) {
			if(identity instanceof students) {
				students students = (students) identity;
				sql = con.prepareStatement("select 班级编号 from students where ID = "+ students.getId());//先查询班级编号
				res = sql.executeQuery();//向数据库发出查询语句
				while(res.next()) {
					students.setClassId(res.getString(1));//设置班级编号
				}
				//依据班级编号和性别进行查找，限制查找范围在班级范围内
				sql = con.prepareStatement("select * from students s inner join class c on s.班级编号 = c.班级编号  where 性别 like '%"+ sex +"%' and s.班级编号 = "+students.getClassId());
				res = sql.executeQuery();//向数据库发出查询语句
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
	 * 查找学生的信息
	 * @param name ：根据学生的电话查找
	 * @return 返回学生对象集合
	 */
	public static List<students> selectUseStudentTelephoneNumber(String telephoneNumber,person identity) {
		ConnectSQL();
		List<students> stu = new ArrayList();
		try {
		if(identity.getLimit().equals("老师")) {		
			sql = con.prepareStatement("select * from students s inner join class c on s.班级编号 = c.班级编号 where 联系电话 like '"+ telephoneNumber+"%'");
			res = sql.executeQuery();//向数据库发出查询语句
			while(res.next()) {
				stu.add(new students(res.getString(1),res.getString(2),res.getString(3),
						res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(11)));
			}
		}else if(identity.isMonitor()) {
			if(identity instanceof students) {
				students students = (students) identity;
				sql = con.prepareStatement("select 班级编号 from students where ID = "+ students.getId());//先查询班级编号
				res = sql.executeQuery();//向数据库发出查询语句
				while(res.next()) {
					students.setClassId(res.getString(1));//设置班级编号
				}
				//依据班级编号和电话号码进行查找，限制查找范围在班级范围内
				sql = con.prepareStatement("select * from students s inner join class c on s.班级编号 = c.班级编号  where 联系电话 like '"+ telephoneNumber +"%' and s.班级编号 = "+students.getClassId());
				res = sql.executeQuery();//向数据库发出查询语句
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
	 * 查找学生的信息
	 * @param name ：根据学生的班级名称查找
	 * @return 返回学生对象
	 */
	public static List<students> selectUseStudentClassName(String className) {
		ConnectSQL();
		List<students> stu = new ArrayList(); 
		String classId = null;
		try {
			sql = con.prepareStatement("select * from class where 班级名称 like"+ "'%"+className+"%'");
			res = sql.executeQuery();//向数据库发出查询语句
			while(res.next()) {
			classId = res.getString(1);
			sql = con.prepareStatement("select * from students s inner join class c on s.班级编号 = c.班级编号  where s.班级编号 = "+classId);
			res = sql.executeQuery();//向数据库发出查询语句
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
	 * 查找学生的所有信息
	 * @param stu ： 传入查找的学生对象
	 * @return  返回学生对象
	 */
	public static students slectStudentAll(students stu) {
		ConnectSQL();
		try {
			sql = con.prepareStatement("select * from class where 班级编号 like '%"+stu.getClassId()+"%'");
			res = sql.executeQuery();
			while(res.next()) {
				String className = res.getString(2);//获得班级名称
				stu.setClassName(className);//设置班级名称
				String MajorId = res.getString(4);//获得专业编号
				sql = con.prepareStatement("select * from majores where 专业编号 like '%"+MajorId+"%'");
				res = sql.executeQuery();
				while(res.next()) {
				stu.setMajorName(res.getString(2));
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}//向数据库发出查询语句
		close();
		return stu;
	}
	
	/**
	 * 查找所有学生
	 * 
	 * @return  返回学生对象集合
	 */
	public static List<students> slectAllStudent() {
		ConnectSQL();
		List<students> stu = new ArrayList();
		
		try {
			sql = con.prepareStatement("SELECT * FROM students s INNER JOIN class c ON s.班级编号 = c.班级编号");
			res = sql.executeQuery();
			while(res.next()) {
				stu.add((new students(res.getString(1),res.getString(2),res.getString(3),
						res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(11))));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//向数据库发出查询语句
		close();
		return stu;
	}
	
	
	/**
	 * 查询用户登录账号密码
	 * @param account ：用户账户
	 * @param identity ：身份标识
	 * @return 返回person对象
	 */
	public static person selectUser(String account,String identity) {
		ConnectSQL();
		person id = null;
		
		if("老师".equals(identity)) {
			try {
				id = new teacher();
				id.setId(account);
				sql = con.prepareStatement("select * from teacher where 教师编号 ="+ account);
				res = sql.executeQuery();//向数据库发出查询语句
				while(res.next()) {
						id.setPassword(res.getString(5));
					}	
				} catch (SQLException e) {
			e.printStackTrace();
				}			
		}else if("学生".equals(identity)) {
			try {
				id = new students();
				id.setId(account);
				sql = con.prepareStatement("select * from students where ID ="+ account);
				res = sql.executeQuery();//向数据库发出查询语句
				while(res.next()) {
						id.setPassword(res.getString(7));
						sql = con.prepareStatement("select * from class where 班长编号 = "+ id.getId());
						res = sql.executeQuery();//向数据库发出查询语句
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
	 * 修改用户密码
	 * @param identity  ：人物对象
	 * @param identity1 : 身份标识
	 * @return
	 */
	public static boolean changeUserPassword(person identity,String indentity1) {
		boolean result = false;
		ConnectSQL();
		if("teacher".equals(indentity1)) {
			try {
			sql = con.prepareStatement("update teacher set 密码 = '"+identity.getPassword()+ "' where 教师编号 = '"+identity.getId()+"';");
			sql.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}else if("student".equals(indentity1)) {
			try {
				sql = con.prepareStatement("update students set 密码 = '"+identity.getPassword()+ "' where ID = '"+identity.getId()+"';");
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
	 * 查找学校专业
	 * @param MajorName ：根据专业名称查找
	 * @return 返回专业对象
	 */
	public static major selectUseMajorName(String MajorName) {
		ConnectSQL();
		major ma = new major();
		try {
			sql = con.prepareStatement("select * from majores where 专业名称 like '%"+ MajorName+"%'");
			res = sql.executeQuery();//向数据库发出查询语句
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
	 * 查找学校专业
	 * @param MajorName ：根据专业编号查找
	 * @return 返回专业对象
	 */
	public static major selectUseMajorID(String MajorID) {
		ConnectSQL();
		major ma = new major();
		try {
			sql = con.prepareStatement("select * from majores where 专业编号 like '%"+ MajorID+"%'");
			res = sql.executeQuery();//向数据库发出查询语句
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
	 * 查询班级
	 * @param className ： 依据班级名称查询班级
	 * @return 返回班级对象
	 */
	public static className selectUseClassName(String className) {
		
		className cn = new className();
		try {
			ConnectSQL();
			String selectsql="select * from class where 班级名称 like '%"+ className+"%'";
			sql = con.prepareStatement(selectsql);
			res = sql.executeQuery();//向数据库发出查询语句
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
	 * 查询班级
	 * @param className ： 依据班级ID查询班级
	 * @return 返回班级对象集合
	 */
	public static className selectUseClassID(String classID) {
		ConnectSQL();
		className cn = new className();
		try {
			sql = con.prepareStatement("select * from class where 班级编号 like '%"+ classID+"%'");
			res = sql.executeQuery();//向数据库发出查询语句
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
	 * 删除学生信息
	 * return ： 返回Boolean值
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
	 * 修改学生信息
	 * return ： 返回Boolean
	 */
	public static boolean changeStudent(students stu,String newStudentsId) {
		boolean result = false;
		try {
			ConnectSQL();
			sql = con.prepareStatement("update students set id = '"+newStudentsId+"',姓名 = '"+stu.getName()+
					"',性别  ='"+stu.getSex()+ "',班级编号  ='"+stu.getClassId()+ "',联系电话 = '" +stu.getTelephoneNumber()+ 
					"',家庭住址 = '" +stu.getAddress()+ "' where id = '"+stu.getId()+"';");
			sql.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
}
