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
	


	
	public static void ConnectSQL() {   //�����������ݿ�ķ���   ����������ļ̳�
		
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
			res = sql.executeQuery();//�����ݿⷢ����ѯ���
			while(res.next()) {
				id1 = res.getString(1);
				pw = res.getString(2);
			}	
	}
	
	
	
	public static void stuLogin(String id  ) throws SQLException {	
		sql = con.prepareStatement("select * from id where id = "+ id);				
		res = sql.executeQuery();//�����ݿⷢ����ѯ���
		while(res.next()) {
			id1 = res.getString(1);
			pw = res.getString(2);
			
		}
}
	
	
	
	public static void Tea_chaxun(String id) throws SQLException{
		id1 = id;
		sql = con.prepareStatement("select ����,רҵ���,�༶���,������,��ϵ�绰  from students where id = "+ id);	
		res = sql.executeQuery();//�����ݿⷢ����ѯ���
		while(res.next()) {
			name = res.getString(1);
			zybh = res.getString(2);
			bjbh = res.getString(3);
			room_number = res.getString(4);
			telephone = res.getString(5);	
		}
	}
	
	
	
	public static void Tea_tianjia(String id,String name,String zybh,String bjbh,String telephone,String room_number) throws SQLException{
		
		sql = con.prepareStatement("insert into students values('"+id+"','"+name+"','"+zybh+"','"+bjbh+"','"+room_number+"','"+telephone+"');");//���Statement����				
		sql.executeUpdate();//�����ݿⷢ��sql���
	}
	
	
	
	
	public static void Tea_shanchu(String id) throws SQLException{
		
		
		sql = con.prepareStatement("select * from students where id = "+ id);
		res = sql.executeQuery();//�����ݿⷢ����ѯ���
		while(res.next()) {
			id1 = res.getString(1);
		}	
		if(id1.equals(id) == false) {
			JOptionPane.showMessageDialog(null,"���޴��ˣ���˶Ժ��ٽ���ɾ��","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE); //������ʾ��Ϣ
		}else {
		Statement stmt = con.createStatement();
		stmt.executeUpdate("delete from students where id = "+id);
		JOptionPane.showMessageDialog(null,"ɾ���ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE); 

		} 
	}
	
	
	
	public static void Tea_xiugai(String id,String id1,String name,String zybh,String bjbh,String telephone,String room_number) throws SQLException{
		
		System.out.println("updata students set id = "+id1+"',���� = '"+name+"',רҵ���  ='"+zybh+ "',�༶���  ='"+bjbh+ "',������ = '" +room_number+ "',��ϵ�绰 = '" +telephone+ "' where id = '"+id+"';");
		
		sql = con.prepareStatement("updata students set id = "+id1+"',���� = '"+name+"',רҵ���  ='"+zybh+ "',�༶���  ='"+bjbh+ "',������ = '" +room_number+ "',��ϵ�绰 = '" +telephone+ "' where id = '"+id+"';");
		sql.executeUpdate();
		JOptionPane.showMessageDialog(null,"�޸ĳɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE); //������ʾ��Ϣ
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
