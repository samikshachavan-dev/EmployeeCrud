package com.linkcode.hibernate1.Hibernate1;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class ProjectDTO {

	@Id
	private int pid;
	private String pname;
	private String clientName;
	
	@ManyToMany(mappedBy="projectList")
	private List<EmployeeDTO> emplist=new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private CompanyDTO company;

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

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

	public List<EmployeeDTO> getEmpList() {
		return emplist;
	}

	public void setEmpList(List<EmployeeDTO> empList) {
		this.emplist = empList;
	}
	
	
}
