package com.company.cms.DTO;

import javax.validation.constraints.NotBlank;

public class DepartmentDTO {

	private long id;

	@NotBlank(message="Department name is mandatory")
	private String name;

	@NotBlank(message="Department head is mandatory")
	private String head;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	
	
	
}
