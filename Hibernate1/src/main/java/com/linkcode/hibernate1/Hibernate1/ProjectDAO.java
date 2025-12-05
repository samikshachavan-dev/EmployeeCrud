package com.linkcode.hibernate1.Hibernate1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ProjectDAO {

	public static void addProject(int pid, String pname, String cName) {
		// TODO Auto-generated method stub
		EntityManager manager = HibernateUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			ProjectDTO project = manager.find(ProjectDTO.class, pid);
			if (project == null) {
				ProjectDTO projectNew = new ProjectDTO();
				projectNew.setPid(pid);
				projectNew.setPname(pname);
				projectNew.setClientName(cName);
				manager.persist(projectNew);
				System.out.println("Project added successfully");
			} else {
				System.out.println("Project Already Exists");
				transaction.rollback();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		} finally {
			manager.close();
		}
	}

	public static void addEmployeeToProject(int pid, int eid) {
		// TODO Auto-generated method stub
		EntityManager manager = HibernateUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			ProjectDTO project = manager.find(ProjectDTO.class, pid);
			EmployeeDTO employee = manager.find(EmployeeDTO.class, eid);

			if (project == null) {
				System.out.println("Project not found");

				transaction.rollback();
				return;
			}
			if (employee == null) {
				System.out.println("Employee not found");
				transaction.rollback();
				return;
			}

			project.getEmpList().add(employee);
			manager.merge(project);
			employee.getProjectList().add(project);
			manager.merge(employee);
			System.out.println("Employee Added to Projects");
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		} finally {
			manager.close();
		}
	}

	public static void displayAll() {
	    EntityManager manager = HibernateUtil.getEntityManager();

	    try {
	        List<ProjectDTO> projects = manager.createQuery(
	            "SELECT DISTINCT p FROM ProjectDTO p " +
	            "LEFT JOIN FETCH p.emplist e " +
	            "LEFT JOIN FETCH e.company",
	            ProjectDTO.class
	        ).getResultList();

	        System.out.printf(
	            "%-10s | %-30s | %-20s | %-20s | %-60s%n",
	            "ProjID", "Project Name", "Client Name", "Company", "Employees"
	        );

	        System.out.println(
	            "-----------+--------------------------------+----------------------+----------------------+------------------------------------------------------------"
	        );

	        for (ProjectDTO p : projects) {

	            String employees = p.getEmpList().isEmpty()
	                ? "No employees"
	                : p.getEmpList().stream()
	                        .map(e -> e.getEmpName() + " (" + e.getEmpDesignation() + ") - " +
	                                (e.getCompany() != null ? e.getCompany().getCname() : "No Company"))
	                        .collect(Collectors.joining(", "));

	            System.out.printf(
	                "%-10d | %-30s | %-20s | %-20s | %-60s%n",
	                p.getPid(),
	                p.getPname(),
	                p.getClientName(),
	                p.getCompany() != null ? p.getCompany().getCname() : "N/A",
	                employees
	            );

	            System.out.println(
	                "-----------+--------------------------------+----------------------+----------------------+------------------------------------------------------------"
	            );
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        manager.close();
	    }
	}


	public static void searchProject(int pid) {
		// TODO Auto-generated method stub
		EntityManager manager = HibernateUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			ProjectDTO project = manager.find(ProjectDTO.class, pid);
			if (project == null) {
				System.out.println("Project Not Found");
				transaction.rollback();
				return;
			}
			String employees = project.getEmpList().stream()
					.map(e -> e.getEmpName() + " (" + e.getEmpDesignation() + ") - " + e.getCompany().getCname())
					.collect(Collectors.joining(", "));
			System.out.printf("%-10s | %-20s | %-20s | %-20s | %-60s%n", "ProjectID", "ClientName", "ProjectName","Company", "Employees");
			System.out.println(
					"---------------------------------------------------------------------------------------------------------");
			System.out.printf("%-10d | %-20s | %-20s | %-20s | %-60s%n", project.getPid(), project.getClientName(),
					project.getPname(), project.getCompany().getCname(),employees);
			System.out.println(
					"---------------------------------------------------------------------------------------------------------");

			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		} finally {
			manager.close();
		}
	}

	public static void removeProject(int pid) {
	    EntityManager manager = HibernateUtil.getEntityManager();
	    EntityTransaction transaction = manager.getTransaction();

	    try {
	        transaction.begin();

	        ProjectDTO project = manager.find(ProjectDTO.class, pid);

	        if (project == null) {
	            System.out.println("Project Not Found");
	            transaction.rollback();
	            return;
	        }

	       
	        for (EmployeeDTO emp : new ArrayList<>(project.getEmpList())) {
	            emp.getProjectList().remove(project);
	            manager.merge(emp);
	        }

	       
	        if (project.getCompany() != null) {
	            project.getCompany().getProjects().remove(project);
	            manager.merge(project.getCompany());
	        }

	  
	        project.getEmpList().clear();
	        manager.merge(project);

	      
	        manager.remove(project);

	        transaction.commit();
	        System.out.println("Project removed successfully");

	    } catch (Exception e) {
	        if (transaction.isActive())
	            transaction.rollback();
	        e.printStackTrace();
	    } finally {
	        manager.close();
	    }
	}


	public static void addProjectToCompany(int pid, int cid) {
		// TODO Auto-generated method stub
		EntityManager manager=HibernateUtil.getEntityManager();
		EntityTransaction transaction=manager.getTransaction();
		
		try {
			transaction.begin();
			ProjectDTO project=manager.find(ProjectDTO.class, pid);
			CompanyDTO company=manager.find(CompanyDTO.class, cid);
			if(project==null) {
				System.out.println("Project Not Found");
				transaction.rollback();
				return;
			}
			if(company==null) {
				System.out.println("Company Not Found");
				transaction.rollback();
				return;
			}
			project.setCompany(company);
			manager.merge(project);
			company.getProjects().add(project);
			transaction.commit();
			System.out.println("Project Added to Company");
		}
		catch(Exception e) {
			if(transaction.isActive()) transaction.rollback();
			e.printStackTrace();
		}
	}

	public static void updateName(int pid, String pname) {
		// TODO Auto-generated method stub
		EntityManager manager=HibernateUtil.getEntityManager();
		EntityTransaction transaction=manager.getTransaction();
		try {			
			transaction.begin();
			ProjectDTO project=manager.find(ProjectDTO.class, pid);
			if(project==null) {
				System.out.println("Project Not Found");
				transaction.rollback();
				return;
			}
			project.setPname(pname);
			manager.merge(project);
			System.out.println("Project Updated");
			transaction.commit();
		}
		catch(Exception e) {
			if(transaction.isActive()) transaction.rollback();
			e.printStackTrace();
		}
		finally {
			manager.close();
		}
	}

	public static void updateCompany(int pid, int cid) {
		// TODO Auto-generated method stub
		EntityManager manager=HibernateUtil.getEntityManager();
		EntityTransaction transaction=manager.getTransaction();
		try {			
			transaction.begin();
			ProjectDTO project=manager.find(ProjectDTO.class, pid);
			CompanyDTO company=manager.find(CompanyDTO.class, cid);
			if(project==null) {
				System.out.println("Project Not Found");
				transaction.rollback();
				return;
			}
			if(company==null) {
				System.out.println("Company Not Found");
				transaction.rollback();
				return;
			}
			project.setCompany(company);
			manager.merge(project);
			System.out.println("Project Updated");
			transaction.commit();
		}
		catch(Exception e) {
			if(transaction.isActive()) transaction.rollback();
			e.printStackTrace();
		}
		finally {
			manager.close();
		}
	}

	public static void updateClientName(int pid, String clientName) {
		// TODO Auto-generated method stub
		EntityManager manager=HibernateUtil.getEntityManager();
		EntityTransaction transaction=manager.getTransaction();
		try {			
			transaction.begin();
			ProjectDTO project=manager.find(ProjectDTO.class, pid);
			if(project==null) {
				System.out.println("Project Not Found");
				transaction.rollback();
				return;
			}
			project.setClientName(clientName);
			manager.merge(project);
			System.out.println("Project Updated");
			transaction.commit();
		}
		catch(Exception e) {
			if(transaction.isActive()) transaction.rollback();
			e.printStackTrace();
		}
		finally {
			manager.close();
		}
	}

}
