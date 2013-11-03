package com.ziooo.model;

public class Type {

	private long idType;
	private String name;

	public Type() {}

	public Type(String name) {
		this.name = name;
	}

	public Type(long idType, String name) {
		this(name);
		this.idType = idType;
	}

	public long getIdType() {
		return idType;
	}

	public void setIdType(long idType) {
		this.idType = idType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
