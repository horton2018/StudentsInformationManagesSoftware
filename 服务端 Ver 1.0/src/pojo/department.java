package pojo;

import java.io.Serializable;

public class department implements Serializable {
	private static final long serialVersionUID = 4713717217654285562L;

	private String depId;
	private String depName;
	private String depHead;
	
	
	public department(String depId, String depName, String depHead) {
		super();
		this.depId = depId;
		this.depName = depName;
		this.depHead = depHead;
	}
	
	
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDepHead() {
		return depHead;
	}
	public void setDepHead(String depHead) {
		this.depHead = depHead;
	}

}
