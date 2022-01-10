package data;

public class User {
	
	private String name;
	private String password;
	private String api_key;
	private int zugriff;
	
	public User() {
		
	}
	
	

	public User(String name, String password, int zugriff) {
		super();
		this.name = name;
		this.password = password;
		this.zugriff = zugriff;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getZugriff() {
		return zugriff;
	}
	public void setZugriff(int zugriff) {
		this.zugriff = zugriff;
	}



	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", zugriff=" + zugriff + "]";
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	

	
	
	

}