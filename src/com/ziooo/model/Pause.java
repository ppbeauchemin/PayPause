package com.ziooo.model;

public class Pause {

	private long idPause;
	private String date;
	private String time;
	private double gain;
	private Person person;
	private Type type;

	public Pause() {}

	public Pause(String date, String time, double gain, Person person, Type type) {
		this.date = date;
		this.time = time;
		this.gain = gain;
		this.person = person;
		this.type = type;
	}

	public Pause(long idPause, String date, String time, double gain, Person person, Type type) {
		this(date, time, gain, person, type);
		this.idPause = idPause;
	}

	public long getIdPause() {
		return idPause;
	}

	public void setIdPause(long idPause) {
		this.idPause = idPause;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getGain() {
		return gain;
	}

	public void setGain(double gain) {
		this.gain = gain;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return person + " a gagner " + gain + " pour une pause " + type + " d'une durée de " + time + " le " + date;
	}

}
