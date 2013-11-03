package com.ziooo.model;

public class Person {

	private long idPerson;
	private String name;
	private double salaryPerSec;

	public Person() {}

	public Person(String name, double salaryPerSec) {
		this.name = name;
		this.salaryPerSec = salaryPerSec;
	}

	public Person(long idPerson, String name, double salaryPerSec) {
		this(name, salaryPerSec);
		this.idPerson = idPerson;
	}

	public long getidPerson() {
		return idPerson;
	}

	public void setidPerson(long idPerson) {
		this.idPerson = idPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalaryPerSec() {
		return salaryPerSec;
	}

	public void setSalaryPerSec(double salaryPerSec) {
		this.salaryPerSec = salaryPerSec;
	}

	@Override
	public String toString() {
		return name;
	}

}
