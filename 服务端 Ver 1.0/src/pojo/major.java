package pojo;

import java.io.Serializable;

public class major implements Serializable {
	private static final long serialVersionUID = 4713717217654285562L;

	private String majorId;
	private String majorName;
	private String majorTeacher;
	private String depId;
	
	public major(String majorId,String majorName,String majorTeacher,String depId) { 
		this.majorId = majorId;
		this.majorName = majorName;
		this.majorTeacher = majorTeacher;
		this.depId = depId;
	}
	
	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getDepId() {
		return depId;
	}

	public major() {
		super();
	}

	public String getMajorId() {
		return majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getMajorTeacher() {
		return majorTeacher;
	}

	public void setMajorTeacher(String majorTeacher) {
		this.majorTeacher = majorTeacher;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("majorName:");
		sb.append("majorId=" + majorId + ";");
		sb.append("majorname=" + majorName + ";");
		sb.append("majorTeacher=" + majorTeacher + ";");
		return sb.toString();
	}

}
