package pojo;

import java.io.Serializable;

public class teacher extends person implements Serializable{
	private String Id;
	private String Name;
	private String Sex;
	private String majorId;
	private String password;
	private static final long serialVersionUID = 4713717217654285562L;

	public teacher() {
		super();
	}
	
	
	public teacher(String teaId, String name, String sex, String majorId, String password, String limit) {
		super();
		this.Id = teaId;
		this.Name = name;
		this.Sex = sex;
		this.majorId = majorId;
		this.password = password;
		this.limit = limit;
	}
	
	public String getId() {
		return Id;
	}
	public void setId(String teaId) {
		this.Id = teaId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	
	public String getMajorId() {
		return majorId;
	}
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
