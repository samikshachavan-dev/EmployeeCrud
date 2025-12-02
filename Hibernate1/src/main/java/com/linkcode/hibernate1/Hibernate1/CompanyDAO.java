package com.linkcode.hibernate1.Hibernate1;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CompanyDAO {

	public static void addCompany(int cid, String cname, String cloc) {
		// TODO Auto-generated method stub
		EntityManager manager = HibernateUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		CompanyDTO company = null;
		try {
			transaction.begin();
			company = new CompanyDTO();
			company.setCid(cid);
			company.setCname(cname);
			company.setLoc(cloc);
			company.setEmplist(null);
			manager.persist(company);
			System.out.println("Company added successfully");
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();

		} finally {
			manager.close();
		}

	}

	public static void searchCompany(int cid) {
		// TODO Auto-generated method stub
		EntityManager manager = HibernateUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			CompanyDTO company = manager.find(CompanyDTO.class, cid);
			if (company != null) {
				System.out.printf("%-10s | %-40s | %-20s%n", "CompanyID", "Name", "Location");
				System.out.println(
						"-----------+----------------------------------------+--------------------+-------------------");

				System.out.printf("%-10d | %-40s | %-20s%n", company.getCid(), company.getCname(), company.getLoc());
				System.out.println(
						"-----------+----------------------------------------+--------------------+-------------------");
				System.out.printf("%-10s | %-40s | %-20s%n", "EmpID", "Name", "Designation");
				System.out.println(
						"-----------+----------------------------------------+--------------------+-------------------");

				for (EmployeeDTO employee : company.getEmplist()) {
					System.out.printf("%-10d | %-40s | %-20s%n", employee.getEmpId(), employee.getEmpName(),
							employee.getEmpDesignation());
					System.out.println(
							"-----------+----------------------------------------+--------------------+---------------");

				}
			} else {
				System.out.println("Company Id Not Found!");

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

	public static void displayAll() {
		// TODO Auto-generated method stub
		EntityManager manager = HibernateUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			List<CompanyDTO> companylist = manager.createQuery("FROM CompanyDTO", CompanyDTO.class).getResultList();
			System.out.printf("%-10s | %-40s | %-20s%n", "CompanyID", "Name", "Location");
			System.out.println(
					"-----------+----------------------------------------+--------------------+-------------------");

			for (CompanyDTO company : companylist) {

				System.out.printf("%-10d | %-40s | %-20s%n", company.getCid(), company.getCname(), company.getLoc());
				System.out.println(
						"-----------+----------------------------------------+--------------------+--------------------");

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

	public static void addEmployeeToCompany(int cid, int eid, String ename, String designation) {
		// TODO Auto-generated method stub
		EntityManager manager = HibernateUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			CompanyDTO company = manager.find(CompanyDTO.class, cid);
			if (company != null) {
				EmployeeDTO employee = new EmployeeDTO();
				employee.setEmpId(eid);
				employee.setEmpName(ename);
				employee.setEmpDesignation(designation);
				employee.setCompany(company);
				manager.persist(employee);
				transaction.commit();

			} else {
				System.out.println("Company not found");
			}

		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		} finally {
			manager.close();
		}
	}

	public static void removeCompany(int cid) {
		// TODO Auto-generated method stub
		EntityManager manager = HibernateUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			CompanyDTO company = manager.find(CompanyDTO.class, cid);

			if (company == null) {
				transaction.rollback();
				System.out.println("Company Not Found");
			} else {
				manager.remove(company);
				System.out.println(company.getCname() + " deleted successfully!");
				transaction.commit();

			}
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		} finally {
			manager.close();
		}
	}

}
