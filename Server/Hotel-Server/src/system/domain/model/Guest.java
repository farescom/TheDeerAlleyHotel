package system.domain.model;

import java.io.Serializable;

/** represents a Guest */
public class Guest implements Serializable
{
	String name;
	String address;
	String phone;
	String date_of_birth;
	String nationality;
	
	public Guest(String name, String address, String phone, String date_of_birth, String nationality)
	{
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.date_of_birth = date_of_birth;
		this.nationality = nationality;
	}
	
	public Guest()
	{
		this.name = "name";
		this.address = "address";
		this.phone = "phone";
		this.date_of_birth = "date_of_birth";
		this.nationality = "nationality";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	@Override
	public String toString() {
		return "Guest [name=" + name + ", address=" + address + ", phone="
				+ phone + ", date_of_birth=" + date_of_birth + ", nationality="
				+ nationality + "]";
	}
}
