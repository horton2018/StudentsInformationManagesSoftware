package Server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import pojo.className;
import pojo.major;
import pojo.person;
import pojo.project;
import pojo.students;
import com.mqd.contact.dao.DAOimpl1;


public class server1 {
	
	private static final long serialVersionUID = 4713717217654285562L;
	private ServerSocket server;
	private Socket socket;
	private InputStream inputStream2;
	private ObjectInputStream objectIS;
	private OutputStream outputStream;
	private ObjectOutputStream objectOS;
	
	public void getserver() {
		try {
			server = new ServerSocket(8998);
			System.out.println("服务器套接字已经创建成功");
			while(true) {
				System.out.println("等待客户机连接");
				socket = server.accept();
				inputStream2 = socket.getInputStream();
				System.out.println("客户机连接成功1");
				objectIS = new ObjectInputStream(inputStream2);
				System.out.println("客户机连接成功2");
				outputStream = socket.getOutputStream();
				System.out.println("客户机连接成功3");
				objectOS = new ObjectOutputStream(outputStream);
				System.out.println("客户机连接成功");
				getClientMessage();
			}
		}catch (Exception e) {
			e.printStackTrace();
			
			
		}
	}
	
	
	private void getClientMessage() {
		try {
			while(true) {
				
				 
				project project =(project) objectIS.readObject();
				
				
				switch (project.getType()) {
				case 1:
					//添加学生数据，调用添加数据方法
					students students = project.getStudents();
					major major = DAOimpl1.selectUseMajorName(students.getMajorName());
					className className = DAOimpl1.selectUseClassName(students.getClassName());
					students.setMajorId(major.getMajorId());
					students.setClassId(className.getClassId());
					Boolean result = DAOimpl1.addStudent(students);
					objectOS.writeObject(result);
					objectOS.flush();
					students = null;
					result = null;
					break;
				case 2:
					//修改数据，调用修改数据方法
					students students2 = project.getStudents();
					String newStudentsId = project.getNewStudentId();
					major major1 = DAOimpl1.selectUseMajorName(students2.getMajorName());
					className className1 = DAOimpl1.selectUseClassName(students2.getClassName());
					students2.setMajorId(major1.getMajorId());
					students2.setClassId(className1.getClassId());
					Boolean result1 = DAOimpl1.changeStudent(students2, newStudentsId);
					objectOS.writeObject(result1);
					objectOS.flush();
					students2 = null;
					newStudentsId = null;
					result1 = null;
					break;
				case 3:
					//删除数据，调用删除数据方法
					String studentId = project.getField();
					Boolean result2 = DAOimpl1.deleteStudent(studentId);
					objectOS.writeObject(result2);
					objectOS.flush();
					studentId = null;
					result2 = null;
					break;
				case 4:
					
					List<students> stu1;
					//搜索数据，根据搜索依据 调用对应搜索方法
					switch (project.getSearch_type()) {
					case 1:	
						//登陆方法，调用登陆查询方法
						person person = project.getLogin();
						person person1 = DAOimpl1.selectUser(person.getId(), person.getLimit());
						objectOS.writeObject(person1);
						objectOS.flush();
						person = null;
						person1 = null;
						break;
					case 2:	//依靠学号查询学生信息
						
							person people = (person)project.getIdentity();
							students stu = DAOimpl1.selectUseStudentID(project.getField(),people);
							objectOS.writeInt(1);
							objectOS.flush();
							objectOS.writeObject(stu);
							objectOS.flush();
							
							
							//System.out.println(stu.getId()+"   33333" );
							
							people = null;
							stu = null;
						
						break;
					case 3:	//依靠学生班级查询学生信息
						
							
							stu1 = DAOimpl1.selectUseStudentClassName(project.getField());
							objectOS.writeInt(stu1.size());
							objectOS.flush();
							for (int i = 0; i < stu1.size(); i++) {
								objectOS.writeObject(stu1.get(i));
								objectOS.flush();
							}
							stu1 = null;
						
						break;
					case 4: //依靠学生专业名称查询学生信息
						
							
							stu1 = DAOimpl1.selectUseStudentMajorName(project.getField());
							objectOS.writeInt(stu1.size());
							
							objectOS.flush();
							for (int i = 0; i < stu1.size(); i++) {
								objectOS.writeObject(stu1.get(i));
								objectOS.flush();
							}
							stu1 = null;
						
						break;
					case 5:  //依靠学生姓名查询学生信息
						
							
							stu1 = DAOimpl1.selectUseStudentName(project.getField(),project.getIdentity());
							objectOS.writeInt(stu1.size());
							objectOS.flush();
							for (int i = 0; i < stu1.size(); i++) {
								objectOS.writeObject(stu1.get(i));
								objectOS.flush();
							}
							stu1 = null;
						break;
					case 6:  //依靠学生性别查询学生信息
						
							stu1 = DAOimpl1.selectUseStudentSex(project.getField(),project.getIdentity());
							objectOS.writeInt(stu1.size());
							objectOS.flush();
							for (int i = 0; i < stu1.size(); i++) {
								objectOS.writeObject(stu1.get(i));
								objectOS.flush();
							}
							stu1 = null;
						
						break;
					case 7: //依靠学生电话查询学生信息
						
							
							stu1 = DAOimpl1.selectUseStudentTelephoneNumber(project.getField(), project.getIdentity());
							objectOS.writeInt(stu1.size());
							objectOS.flush();
							for (int i = 0; i < stu1.size(); i++) {
								objectOS.writeObject(stu1.get(i));
								objectOS.flush();
							}
							stu1 = null;
						
						break;
					case 8: //查询所有学生
						
						stu1 = DAOimpl1.slectAllStudent();
						objectOS.writeInt(stu1.size());
						objectOS.flush();
						for (int i = 0; i < stu1.size(); i++) {
							objectOS.writeObject(stu1.get(i));
							objectOS.flush();
						}
						stu1 = null;
						break;
					case 9:  //查询学生的所有信息
						students stu2;
						stu2 = DAOimpl1.slectStudentAll(project.getStudents());
						objectOS.writeInt(1);
						objectOS.flush();
						objectOS.writeObject(stu2);
						objectOS.flush();
						stu2 = null;
					case 10:   //通过班级名称查询班级id
						className cName;
						cName = DAOimpl1.selectUseClassName(project.getField());
						objectOS.writeInt(1);
						objectOS.flush();
						objectOS.writeObject(cName);
						objectOS.flush();
						cName = null;
						break;
					case 11: 	//通过班级ID查询班级名称
						className cName1;
						cName1 = DAOimpl1.selectUseClassID(project.getField());
						objectOS.writeInt(1);
						objectOS.flush();
						objectOS.writeObject(cName1);
						objectOS.flush();
						cName = null;
						break;
					case 12:	 //通过专业名称查询专业id
						major major2;
						major2 = DAOimpl1.selectUseMajorName(project.getField());
						objectOS.writeInt(1);
						objectOS.flush();
						objectOS.writeObject(major2);
						objectOS.flush();
						major2 = null;
						break;
					case 13: 	 //通过专业id查询专业名称
						major major3;
						major3 = DAOimpl1.selectUseMajorID(project.getField());
						objectOS.writeInt(1);
						objectOS.flush();
						objectOS.writeObject(major3);
						objectOS.flush();
						major3 = null;
						break;
					default:
						break;
					}
				default:
					break;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接中断");
		}
		
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		server1 server1 = new server1();
		server1.getserver();
	}
	
	

}
