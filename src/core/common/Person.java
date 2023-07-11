package core.common;

public abstract class Person {

	private String firstName, lastName;
	private Date birthday;
	
	public Person(String firstName, String lastName, Date birthday) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
	}
	
	/**
	 * Gets the person's first name.
	 * @return
	 * 		the person's first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Gets the person's last name.
	 * @return
	 * 		the person's last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
