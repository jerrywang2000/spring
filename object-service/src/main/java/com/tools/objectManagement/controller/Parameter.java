package com.tools.objectManagement.controller;

public class Parameter implements Comparable<Parameter>{

	private int sequence;
	private String type;
	private String value;
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public int compareTo(Parameter para) {
		
		int seq = para.getSequence(); 
		
		//ascending order
		return this.sequence - seq;
		
		//descending order
		//return seq - this.sequence;
		
	}
	
}
