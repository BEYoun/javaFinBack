package fr.younes.metier;

public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private int phone;
	private String role;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(int id, 
			String firstName, 
			String lastName, 
			String email,
			String address,
			int phone, 
			String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName= lastName;
		this.email= email;
		this.address = address;
		this.phone=phone;
		this.role = role;
	}
	public Employee(
			String firstName, 
			String lastName, 
			String email,
			String address,
			int phone, 
			String role) {
		super();
		this.firstName = firstName;
		this.lastName= lastName;
		this.email= email;
		this.address = address;
		this.phone=phone;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address=" + address + ", phone=" + phone + ", role=" + role + "]";
	}
	
	
	


	
}
