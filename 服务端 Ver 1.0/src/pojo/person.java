package pojo;

import java.io.Serializable;

public class person implements Serializable {
	private static final long serialVersionUID = 4713717217654285562L;

	protected String password;
	protected String Id;
	protected boolean monitor = false;
	protected String limit;
	

	public person() {
		super();
	}
	
	public person(String Id,String password,String limit) {
		this.Id = Id;
		this.password = password;
		this.limit = limit;
	}
	
	
	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public boolean isMonitor() {
		return monitor;
	}

	public void setMonitor(boolean monitor) {
		this.monitor = monitor;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
