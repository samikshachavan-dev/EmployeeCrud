package com.linkcode.hibernate1.Hibernate1;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class EmployeeDTO {
	
	@Id
	private int empId;
	private String empName;
	private String empDesignation;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private CompanyDTO company;
	
//	@ManyToMany
//	@JoinTable(
//	        name = "employee_project",
//	        joinColumns = @JoinColumn(name = "emp_id"),
//	        inverseJoinColumns = @JoinColumn(name = "project_id")
//	    )
//	private List<ProjectDTO> projectList=new ArrayList<>();

	public EmployeeDTO() {
		
	}
	public int getEmpId() {
		return empId;
	}
//
//	public List<ProjectDTO> getProjectList() {
//		return projectList;
//	}
//	public void setProjectList(List<ProjectDTO> projectList) {
//		this.projectList = projectList;
//	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public CompanyDTO getCompany() {
		return company;
	}
	public void setCompany(CompanyDTO company) {
		this.company = company;
	}
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}
}
