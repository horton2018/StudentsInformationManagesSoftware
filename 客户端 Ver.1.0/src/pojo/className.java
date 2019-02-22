package pojo;

import java.io.Serializable;

public class className implements Serializable{
	private static final long serialVersionUID = 4713717217654285562L;

	private String classId;
	private String className;
	private String monitor;
	private String majorId;
	private String majorName;

	
	public className(String classId,String className,String monitor,String majorId) {
		this.classId = classId;
		this.className = className;
		this.monitor = monitor;
		this.majorId = majorId;
	}
	public className() {
		
	}
	
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getMajorName() {
		return majorName;
	}


	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}


	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getMonitor() {
		return monitor;
	}
	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}
	public String getMajorId() {
		return majorId;
	}
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

}
