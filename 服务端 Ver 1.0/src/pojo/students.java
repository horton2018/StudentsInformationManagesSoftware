package pojo;

public class students extends person {
	private static final long serialVersionUID = 4713717217654285562L;

	private String Id = null;
	private String name = null;
	private String majorId = null;
	private String classId = null;
	private String telephoneNumber = null;
	private String className = null;
	private String majorName = null;
	private String sex = null;
	private String address =null;
	private String password = null;
	private boolean monitor = false;

	
	public boolean isMonitor() {
		return monitor;
	}

	public void setMonitor(boolean monitor) {
		this.monitor = monitor;
	}

	public students() {
		super();
	}
	
	/**
	 * 设置学生的班级名称和专业编号
	 * @param className ：班级名称
	 * @param majorName ：专业名称
	 * @param majorId : 专业编号
	 */
	public void student1(String className,String majorName,String majorId) {
		this.className = className;
		this.majorName = majorName;
		this.majorId = majorId;
	}
	
	
	public students(String id, String name, String sex, String classId, String telephoneNumber,
			String address, String password,String majorId) {
		super();
		this.Id = id;
		this.name = name;
		this.classId = classId;
		this.telephoneNumber = telephoneNumber;
		this.sex = sex;
		this.address = address;
		this.password = password;
		this.majorId = majorId;

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		this.Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajorId() {
		return majorId;
	}
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	
}
