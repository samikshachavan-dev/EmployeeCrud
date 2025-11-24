package com.linkcode.hibernate1.Hibernate1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EmployeeDAO {
	 public static void main(String[] args) {
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("hibernate");
	EntityManager manager=factory.createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	transaction.begin();
	
	EmployeeDTO emp=new EmployeeDTO();
	emp.setEmpId(1);
	emp.setEmpName("Samiksha");
	emp.setEmpDesignation("SDE");
	
	manager.persist(emp);
	transaction.commit();
	factory.close();
	manager.close();
	 }
	


}
