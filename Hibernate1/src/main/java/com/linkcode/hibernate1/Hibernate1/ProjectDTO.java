package com.linkcode.hibernate1.Hibernate1;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProjectDTO {

	@Id
	private int pid;
	private String pname;
	private String clientName;
	
//	@ManyToMany(mappedBy="projects")
//	private List<EmployeeDTO> empList=new ArrayList<>();

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

//	public List<EmployeeDTO> getEmpList() {
//		return empList;
//	}
//
//	public void setEmpList(List<EmployeeDTO> empList) {
//		this.empList = empList;
//	}
//	
	
}
