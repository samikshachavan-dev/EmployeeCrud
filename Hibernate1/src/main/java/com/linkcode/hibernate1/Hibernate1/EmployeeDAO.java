package com.linkcode.hibernate1.Hibernate1;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EmployeeDAO {

	public static void addEmployee(int id, String name, String designation, int companyId)
			throws DuplicateEmployeeException {
		EntityManager manager = HibernateUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			EmployeeDTO empObj = manager.find(EmployeeDTO.class, id);
			if (empObj != null) {
				transaction.rollback();
				throw new DuplicateEmployeeException("Employee with ID " + id + " already exists!");
			}
		
	        CompanyDTO company = manager.find(CompanyDTO.class, companyId);
	        if (company == null) {
	            transaction.rollback();
	            throw new RuntimeException("Company with ID " + companyId + " does not exist!");
	        }

			EmployeeDTO emp = new EmployeeDTO();
			emp.setEmpId(id);
			emp.setEmpName(name);
			emp.setEmpDesignation(designation);
			emp.setCompany(company);
			manager.persist(emp);
			System.out.println("Employee added successfully");
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		}
		finally {
			manager.close();
		}

	}

	public static void searchEmployee(int id) {
		EntityManager manager = HibernateUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();
			EmployeeDTO employee = manager.find(EmployeeDTO.class, id);
			if (employee != null) {
				System.out.printf("%-10s | %-40s | %-20s | %-30s | %-20s%n", "EmpID", "Name", "Designation",
						"Company Name", "Location");
				System.out.println(
						"-----------+----------------------------------------+--------------------+--------------------------------+--------------------");

				System.out.printf("%-10d | %-40s | %-20s | %-30s | %-20s%n", employee.getEmpId(), employee.getEmpName(),
						employee.getEmpDesignation(), employee.getCompany().getCname(), employee.getCompany().getLoc());
				System.out.println(
						"-----------+----------------------------------------+--------------------+--------------------------------+--------------------");

				transaction.commit();
			
			} else {
				System.out.println("Employee Not Found");
			}
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		}
		finally {
			manager.close();
		}

	}

//
	public static void displayAll() {
		// TODO Auto-generated method stub
		EntityManager manager = HibernateUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			List<EmployeeDTO> emplist = manager.createQuery("FROM  EmployeeDTO", EmployeeDTO.class).getResultList();
			System.out.printf("%-10s | %-40s | %-20s | %-30s | %-20s%n", "EmpID", "Name", "Designation", "Company Name",
					"Location");
			System.out.println(
					"-----------+----------------------------------------+--------------------+--------------------------------+--------------------");

			for (EmployeeDTO employee : emplist) {
				System.out.printf("%-10d | %-40s | %-20s | %-30s | %-20s%n", employee.getEmpId(), employee.getEmpName(),
						employee.getEmpDesignation(), employee.getCompany().getCname(), employee.getCompany().getLoc());
				System.out.println(
						"-----------+----------------------------------------+--------------------+--------------------------------+--------------------");
			}
			transaction.commit();
		
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		}
		finally {
			manager.close();
		}
	}

	public static void removeEmployee(int eid) {
		// TODO Auto-generated method stub
		EntityManager manager=HibernateUtil.getEntityManager();
		EntityTransaction transaction=manager.getTransaction();
		try {
			transaction.begin();
			EmployeeDTO employee=manager.find(EmployeeDTO.class, eid);
			if(employee==null) {
				transaction.rollback();
				System.out.println("Employee Not Found");
			}
			else {
				manager.remove(employee);
				System.out.println("Employee Deleted Successfully");
				transaction.commit();
			}
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
//	public static void updateName(int id, String name) {
//		transaction.begin();
//		EmployeeDTO employee = manager.find(EmployeeDTO.class, id);
//		if (employee != null) {
//			System.out.println("Employee Found!!");
//			employee.setEmpName(name);
//			System.out.println("Employee Updated");
//			manager.persist(employee);
//			transaction.commit();
//		} else {
//			System.out.println(id + "Not Found!!");
//			transaction.rollback();
//		}
//
//	}
//
//	public static void updateDesignation(int id, String designation) {
//		transaction.begin();
//		EmployeeDTO employee = manager.find(EmployeeDTO.class, id);
//		if (employee != null) {
//			System.out.println("Employee Found!!");
//			employee.setEmpDesignation(designation);
//			System.out.println("Employee designation updated!!");
//			manager.persist(employee);
//			transaction.commit();
//
//		} else {
//			System.out.println(id + " Not Found!!");
//			transaction.rollback();
//		}
//
//	}

