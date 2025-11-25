package com.linkcode.hibernate1.Hibernate1;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EmployeeDAO {

	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;

	public static void openConnection() {
		factory = Persistence.createEntityManagerFactory("hibernate");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	}

	public static void closeConnection() {
		if (factory != null) {
			factory.close();
		}
		if (manager != null) {
			manager.close();
		}
		if (transaction.isActive()) {
			transaction.rollback();
		}
	}

	public static void insert(int id, String name, String designation) throws DuplicateEmployeeException {
		transaction.begin();
		EmployeeDTO empObj = manager.find(EmployeeDTO.class, id);
		if (empObj != null) {
			transaction.rollback();
			throw new DuplicateEmployeeException("Employee with ID " + id + " already exists!");
		}
		EmployeeDTO emp = new EmployeeDTO();
		emp.setEmpId(id);
		emp.setEmpName(name);
		emp.setEmpDesignation(designation);
		manager.persist(emp);
		System.out.println("Employee added successfully");
		transaction.commit();

	}

	public static void display(int id) {
		transaction.begin();
		EmployeeDTO employee = manager.find(EmployeeDTO.class, id);
		if (employee != null) {
			System.out.printf("%-10s | %-40s | %-20s%n", "EmpID", "Name", "Designation");
			System.out.println("-----------+----------------------+----------------------");

			System.out.printf("%-10d | %-40s | %-20s%n", employee.getEmpId(), employee.getEmpName(),
					employee.getEmpDesignation());
			System.out.println("-----------+----------------------+----------------------");

			transaction.commit();
		} else {
			System.out.println("Employee not found!!");
		}

	}

	public static void updateName(int id, String name) {
		transaction.begin();
		EmployeeDTO employee = manager.find(EmployeeDTO.class, id);
		if (employee != null) {
			System.out.println("Employee Found!!");
			employee.setEmpName(name);
			System.out.println("Employee Updated");
			manager.persist(employee);
			transaction.commit();
		} else {
			System.out.println(id + "Not Found!!");
			transaction.rollback();
		}

	}

	public static void updateDesignation(int id, String designation) {
		transaction.begin();
		EmployeeDTO employee = manager.find(EmployeeDTO.class, id);
		if (employee != null) {
			System.out.println("Employee Found!!");
			employee.setEmpDesignation(designation);
			System.out.println("Employee designation updated!!");
			manager.persist(employee);
			transaction.commit();

		} else {
			System.out.println(id + " Not Found!!");
			transaction.rollback();
		}

	}

	public static void delete(int id) {
		transaction.begin();
		EmployeeDTO employee = manager.find(EmployeeDTO.class, id);
		if (employee == null) {
			transaction.rollback();
			System.out.println("No Employee Found!!");
		} else {
			manager.remove(employee);
			System.out.println(employee.getEmpId() + " deleted successfully!");
			transaction.commit();
		}

	}

	public static void displayAll() {
		// TODO Auto-generated method stub
		transaction.begin();
		List<EmployeeDTO> emplist = manager.createQuery("FROM  EmployeeDTO", EmployeeDTO.class).getResultList();

		System.out.println("-----------+----------------------+----------------------");

		System.out.printf("%-10s | %-20s | %-20s%n", "EmpID", "Name", "Designation");
		System.out.println("-----------+----------------------+----------------------");

		for (EmployeeDTO emp : emplist) {
			System.out.printf("%-10d | %-20s | %-20s%n", emp.getEmpId(), emp.getEmpName(), emp.getEmpDesignation());
			System.out.println("-----------+----------------------+----------------------");
		}
		transaction.commit();

	}

}
