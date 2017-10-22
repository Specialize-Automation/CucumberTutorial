package com.cucumber.test;

public class UserData 
{
	private String firstname;
	private String lastname;
	private String phone;
	private String email;
	private String username;
	private String password;
	private String confirmpassword;
	
	public UserData(String firstName, String lastName, String phone, String email, String username, String password, String confirmpassword)
	{
		this.firstname=firstName;
		this.lastname=lastName;
		this.phone=phone;
		this.email=email;
		this.username=username;
		this.password=password;
		this.confirmpassword=confirmpassword;
	}
	
	public String getFirstName() { return firstname; }
	public String getLastName() { return lastname; }
	public String getPhone() { return phone; }
	public String getEMail() { return email; }
	public String getUserName() { return username; }
	public String getPassword() { return password; }
	public String getConfirmPassword() { return confirmpassword; }
}

