package com.mqd.contact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.Driver;

public class GetSQL {
	
	static Connection con;
	static PreparedStatement sql;
	static ResultSet res;
	
	static String id1;
	static String name;
	static String pw;
	static String zybh;
	static String bjbh;
	static String room_number;
	static String telephone;
	


	
	public static void ConnectSQL() {   //用于连接数据库的方法   可用于子类的继承
		
		try {
			Driver driver = new Driver();
			String url = "jdbc:mysql://localhost:3306/lms?serverTimezone=GMT%2B8";
			Properties info = new Properties();
			info.put("user","root");
			info.put("password", "hq441521hq");
			con = driver.connect(url, info);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void glyLogin(String id) throws SQLException {
			sql = con.prepareStatement("select * from id where id = "+ id);
			res = sql.executeQuery();//向数据库发出查询语句
			while(res.next()) {
				id1 = res.getString(1);
				pw = res.getString(2);
			}	
	}
	
	
	
	public static void stuLogin(String id  ) throws SQLException {	
		sql = con.prepareStatement("select * from id where id = "+ id);				
		res = sql.executeQuery();//向数据库发出查询语句
		while(res.next()) {
			id1 = res.getString(1);
			pw = res.getString(2);
			
		}
}
	
	
	
	public static void Tea_chaxun(String id) throws SQLException{
		id1 = id;
		sql = con.prepareStatement("select 姓名,专业编号,班级编号,宿舍编号,联系电话  from students where id = "+ id);	
		res = sql.executeQuery();//向数据库发出查询语句
		while(res.next()) {
			name = res.getString(1);
			zybh = res.getString(2);
			bjbh = res.getString(3);
			room_number = res.getString(4);
			telephone = res.getString(5);	
		}
	}
	
	
	
	public static void Tea_tianjia(String id,String name,String zybh,String bjbh,String telephone,String room_number) throws SQLException{
		
		sql = con.prepareStatement("insert into students values('"+id+"','"+name+"','"+zybh+"','"+bjbh+"','"+room_number+"','"+telephone+"');");//获得Statement对象				
		sql.executeUpdate();//向数据库发出sql语句
	}
	
	
	
	
	public static void Tea_shanchu(String id) throws SQLException{
		
		
		sql = con.prepareStatement("select * from students where id = "+ id);
		res = sql.executeQuery();//向数据库发出查询语句
		while(res.next()) {
			id1 = res.getString(1);
		}	
		if(id1.equals(id) == false) {
			JOptionPane.showMessageDialog(null,"查无此人，请核对后再进行删除","提示消息",JOptionPane.WARNING_MESSAGE); //弹出提示信息
		}else {
		Statement stmt = con.createStatement();
		stmt.executeUpdate("delete from students where id = "+id);
		JOptionPane.showMessageDialog(null,"删除成功","提示消息",JOptionPane.WARNING_MESSAGE); 

		} 
	}
	
	
	
	public static void Tea_xiugai(String id,String id1,String name,String zybh,String bjbh,String telephone,String room_number) throws SQLException{
		
		System.out.println("updata students set id = "+id1+"',姓名 = '"+name+"',专业编号  ='"+zybh+ "',班级编号  ='"+bjbh+ "',宿舍编号 = '" +room_number+ "',联系电话 = '" +telephone+ "' where id = '"+id+"';");
		
		sql = con.prepareStatement("updata students set id = "+id1+"',姓名 = '"+name+"',专业编号  ='"+zybh+ "',班级编号  ='"+bjbh+ "',宿舍编号 = '" +room_number+ "',联系电话 = '" +telephone+ "' where id = '"+id+"';");
		sql.executeUpdate();
		JOptionPane.showMessageDialog(null,"修改成功","提示消息",JOptionPane.WARNING_MESSAGE); //弹出提示信息
	}
	
	
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
	
	

}
