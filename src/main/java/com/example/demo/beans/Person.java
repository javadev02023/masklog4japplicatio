package com.example.demo.beans;

public class Person {

	private String firstName;
	private String lastName;
	private Integer age;
	private Integer creditCardNumber;
	private String address;
	private String AccountNumber;

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(Integer creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", creditCardNumber="
				+ creditCardNumber + ", address=" + address + ", AccountNumber=" + AccountNumber + "]";
	}

	public Person(String firstName, String lastName, Integer age, Integer creditCardNumber, String address,
			String AccountNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.creditCardNumber = creditCardNumber;
		this.address = address;
		this.AccountNumber = AccountNumber;
	}
}