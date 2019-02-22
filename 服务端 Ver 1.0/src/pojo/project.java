package pojo;

import java.io.Serializable;

public class project implements Serializable{
	private int type;			//增删查改、登陆等操作类型
	private int search_type;	//搜索类型
	private String field;		//搜索、删除字符串
	private students students;  //修改、添加学生数据
	private String newStudentId;		//修改学生数据使用的新学生id
	private person login;		//登陆使用的账号
	private person identity;	//登陆角色
	private static final long serialVersionUID = 4713717217654285562L;

	

	


	


	/**
	 * 
	 * @param type   操作类型：删
	 * @param field  学生学号
	 */
	public project(int type,String field) {
		this.type = type;
		this.field = field;
	}
	
	
	/**
	 * 
	 * @param type：操作类型：搜索
	 * @param search_type：搜索类型
	 * @param field：搜索字符串
	 */
	public project(int type,int search_type,String field,person identity) {
		this.search_type = search_type;
		this.type = type;
		this.field = field;
		this.identity = identity;
	}
	
	/**
	 * 搜索单个学生所有信息
	 * @param type：操作类型：搜索
	 * @param search_type：搜索类型
	 * @param field：搜索字符串
	 */
	public project(int type,int search_type,students students,person identity) {
		this.search_type = search_type;
		this.type = type;
		this.students = students;
		this.identity = identity;
	}
	
	/**
	 * 
	 * @param type：操作类型：添加、修改
	 * @param students：添加、修改的学生数据
	 */
	public project(int type,students students,String newStudentId) {
		this.type = type;
		this.students = students;
		this.newStudentId = newStudentId;
	}
	
	
	/**
	 * 
	 * @param type   操作类型：查询数据库内用户账户
	 * @param person  登陆账号信息
	 */
	public project(int type,int search_type,person person) {
		this.type = type;
		this.login = person;
		this.search_type = search_type;
	}
	
	
	public students getStudents() {
		return students;
	}


	public void setStudents(students students) {
		this.students = students;
	}


	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSearch_type() {
		return search_type;
	}
	public void setSearch_type(int search_type) {
		this.search_type = search_type;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	

	public String getNewStudentId() {
		return newStudentId;
	}


	public void setNewStudentId(String newStudentId) {
		this.newStudentId = newStudentId;
	}
	
	public person getLogin() {
		return login;
	}


	public void setLogin(person login) {
		this.login = login;
	}

	public person getIdentity() {
		return identity;
	}


	public void setIdentity(person identity) {
		this.identity = identity;
	}

}
