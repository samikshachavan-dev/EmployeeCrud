package com.linkcode.hibernate1.Hibernate1;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CompanyDTO {

	@Id
	private int cid;
	private String cname;
	private String loc;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<EmployeeDTO> emplist=new ArrayList<>();

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<ProjectDTO> projects = new ArrayList<>();


	public void addEmployee(EmployeeDTO e) {
		emplist.add(e);
		e.setCompany(this);
	} 
	
	
	public int getCid() {
		return cid;
	}
	public List<ProjectDTO> getProjects() {
		return projects;
	}


	public void setProjects(List<ProjectDTO> projects) {
		this.projects = projects;
	}


	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public List<EmployeeDTO> getEmplist() {
		return emplist;
	}
	public void setEmplist(List<EmployeeDTO> emplist) {
		this.emplist = emplist;
	}
	
	
	
	
}
