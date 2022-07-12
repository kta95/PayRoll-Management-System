package entities;

import java.util.Date;
import java.util.Objects;

public abstract class PersonInfo {

	private int id;

	private String name;
	private String gender;
	private Date dateOfBirth;
	private String phone;
	private String email;
	private String address;	
	private Position position;	
	private Department department;
	private Date hiredDate;
	
	
	public PersonInfo() {
		// TODO Auto-generated constructor stub
	}
	public PersonInfo(int id, String name, String gender, Date dateOfBirth, String phone, String email, String address,
			Position position, Department department, Date hiredDate) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.position = position;
		this.department = department;
		this.hiredDate = hiredDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Date getHiredDate() {
		return hiredDate;
	}
	public void setHiredDate(Date string) {
		this.hiredDate = string;
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, dateOfBirth, department, email, gender, hiredDate, id, name, phone, position);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonInfo other = (PersonInfo) obj;
		return Objects.equals(address, other.address) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(department, other.department) && Objects.equals(email, other.email)
				&& Objects.equals(gender, other.gender) && Objects.equals(hiredDate, other.hiredDate) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(phone, other.phone)
				&& Objects.equals(position, other.position);
	}

	
	
}
