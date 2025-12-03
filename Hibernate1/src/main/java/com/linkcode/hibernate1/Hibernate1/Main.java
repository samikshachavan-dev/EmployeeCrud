package com.linkcode.hibernate1.Hibernate1;

import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean running = true;
		while (running) {
			System.out.println("1. Add Company and Employees");
			System.out.println("2. Search Employee");
			System.out.println("3. Search Company");
			System.out.println("4. Display Employee");
			System.out.println("5. Display Company");
			System.out.println("6. Add Employees to Company");
			System.out.println("7. Add Employees to Project");
			System.out.println("8. Update Employee");
			System.out.println("9. Update Company");
			System.out.println("10. Remove Employee");
			System.out.println("11. Remove Company");
			System.out.println("12. Add Project");
			System.out.println("13. Display Project");
			System.out.println("14. Update Project");
			System.out.println("15. Delete Project");
			System.out.println("16. Search Projects");
			System.out.println("17. Remove Projects");
			System.out.println("18. Exit");
			System.out.println("Enter your choice?");
			int ch = sc.nextInt();
			switch (ch) {
			case 1 -> {
				System.out.println("Which company need to specify?");

				System.out.println("Enter Id");
				int cid = sc.nextInt();
				System.out.println("Enter Company Name:");
				sc.nextLine();
				String cname = sc.nextLine();
				System.out.println("Enter Company Location:");
				String cloc = sc.nextLine();
				CompanyDAO.addCompany(cid, cname, cloc);

				System.out.println("How many employees you want to insert?");
				int n = sc.nextInt();
				for (int i = 0; i < n; i++) {
					try {
						System.out.println("Enter id:");
						int id = sc.nextInt();

						System.out.println("Enter name:");
						sc.nextLine();
						String name = sc.nextLine();
						System.out.println("Enter designation:");
						String designation = sc.nextLine();
						EmployeeDAO.addEmployee(id, name, designation, cid);
					}

					catch (DuplicateEmployeeException e) {
						System.out.println("ERROR: " + e.getMessage());
						i--;
					}
				}

			}
			case 2 -> {
				System.out.println("Enter id to search:");
				int id = sc.nextInt();
				EmployeeDAO.searchEmployee(id);

			}
			case 3 -> {
				System.out.println("Enter Company Id to search: ");
				int cid = sc.nextInt();
				CompanyDAO.searchCompany(cid);

			}
			case 4 -> {
				EmployeeDAO.displayAll();
			}
			case 5 -> {
				CompanyDAO.displayAll();
			}
			case 6 -> {
				System.out.println("Enter Company Id");
				int cid = sc.nextInt();
				System.out.println("Enter Employee Id");
				int eid = sc.nextInt();
				System.out.println("Enter Employee Name:");
				String ename = sc.next();
				System.out.println("Enter Employee Designation:");
				String designation = sc.next();
				CompanyDAO.addEmployeeToCompany(cid, eid, ename, designation);
			}
			case 7 -> {

			}
			case 8 -> {
				System.out.println("Enter id to update:");
				int id = sc.nextInt();
				Boolean flag1 = true;
				while (flag1) {
					System.out.println("1. Name\n2. Designation\n3. Company\n4. Exit\n");
					int ch1 = sc.nextInt();
					switch (ch1) {
					case 1 -> {
						System.out.println("Enter Name to update: ");
						sc.nextLine();
						String name = sc.nextLine();
						EmployeeDAO.updateName(id, name);
					}
					case 2 -> {
						System.out.println("Enter Designation to update: ");
						sc.nextLine();
						String designation = sc.nextLine();
						EmployeeDAO.updateDesignation(id, designation);
					}
					case 3 -> {
						System.out.println("Enter Company Id to update: ");
						int cid = sc.nextInt();
						EmployeeDAO.updateCompany(id, cid);
					}
					case 4 -> {
						flag1 = false;
					}
					}
				}
			}
			case 9 -> {
				System.out.println("Enter Company Id: ");
				int cid = sc.nextInt();
				boolean flag = true;
				while (flag) {
					System.out.println("1. Company Name\n2. Company Location\n3. Exit");
					System.out.println("Enter Your choice:");
					int ch2 = sc.nextInt();
					switch (ch2) {
					case 1 -> {
						sc.nextLine();
						System.out.println("Enter name to update");
						String cname = sc.nextLine();
						CompanyDAO.updateName(cid, cname);
					}
					case 2 -> {
						sc.nextLine();
						System.out.println("Enter location to update");
						String cloc = sc.nextLine();
						CompanyDAO.updateLoc(cid, cloc);

					}
					case 3 -> {
						flag = false;
					}
					}

				}

			}
			case 10 -> {
				System.out.println("Enter Employee ID: ");
				int eid = sc.nextInt();
				EmployeeDAO.removeEmployee(eid);
			}
			case 11 -> {
				System.out.println("Enter Company ID: ");
				int cid = sc.nextInt();
				CompanyDAO.removeCompany(cid);
			}
			case 12 -> {
				System.out.println("Enter Project ID: ");
				int pid = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Project Name: ");
				String pname = sc.nextLine();
				System.out.println("Enter Project Client Name");
				String cName = sc.nextLine();
				ProjectDAO.addProject(pid, pname, cName);
			}
			
			case 13->{
				
			}
			case 18 -> {
				System.out.println("Connection closed");
				HibernateUtil.shutdown();
				running = false;
				sc.close();
			}

			}
		}
	}

}
