package pojo;

import java.io.Serializable;

public class project implements Serializable{
	private int type;			//��ɾ��ġ���½�Ȳ�������
	private int search_type;	//��������
	private String field;		//������ɾ���ַ���
	private students students;  //�޸ġ����ѧ������
	private String newStudentId;		//�޸�ѧ������ʹ�õ���ѧ��id
	private person login;		//��½ʹ�õ��˺�
	private person identity;	//��½��ɫ
	private static final long serialVersionUID = 4713717217654285562L;

	

	


	


	/**
	 * 
	 * @param type   �������ͣ�ɾ
	 * @param field  ѧ��ѧ��
	 */
	public project(int type,String field) {
		this.type = type;
		this.field = field;
	}
	
	
	/**
	 * 
	 * @param type���������ͣ�����
	 * @param search_type����������
	 * @param field�������ַ���
	 */
	public project(int type,int search_type,String field,person identity) {
		this.search_type = search_type;
		this.type = type;
		this.field = field;
		this.identity = identity;
	}
	
	/**
	 * ��������ѧ��������Ϣ
	 * @param type���������ͣ�����
	 * @param search_type����������
	 * @param field�������ַ���
	 */
	public project(int type,int search_type,students students,person identity) {
		this.search_type = search_type;
		this.type = type;
		this.students = students;
		this.identity = identity;
	}
	
	/**
	 * 
	 * @param type���������ͣ���ӡ��޸�
	 * @param students����ӡ��޸ĵ�ѧ������
	 */
	public project(int type,students students,String newStudentId) {
		this.type = type;
		this.students = students;
		this.newStudentId = newStudentId;
	}
	
	
	/**
	 * 
	 * @param type   �������ͣ���ѯ���ݿ����û��˻�
	 * @param person  ��½�˺���Ϣ
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
