package com.linkcode.hibernate1.Hibernate1;

import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeDAO.openConnection();
		boolean flag = true;
		while (flag) {
			System.out.println("1. Insert\n2. Display Employee \n3. Display All \n4. Update \n5. Delete\n6. Exit");
			System.out.println("Enter choice:");
			int ch = sc.nextInt();
			switch (ch) {
			case 1 -> {
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
						EmployeeDAO.insert(id, name, designation);
					}

					catch (DuplicateEmployeeException e) {
						System.out.println("ERROR: " + e.getMessage());
						i--;
					}
				}

			}
			case 2 -> {
				System.out.println("Enter id to display:");
				int id = sc.nextInt();
				EmployeeDAO.display(id);

			}
			case 3 -> {
				EmployeeDAO.displayAll();
			}
			case 4 -> {
				System.out.println("Enter id to update:");
				int id = sc.nextInt();
				Boolean flag1 = true;
				while (flag1) {
					System.out.println("1. Name\n2. Designation\n3. Exit\n");
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
						flag1 = false;
					}
					}
				}
			}
			case 5 -> {
				System.out.println("Enter id to delete");
				int id = sc.nextInt();
				EmployeeDAO.delete(id);
			}
			case 6 -> {
				System.out.println("Connection closed");
				flag = false;
				EmployeeDAO.closeConnection();
				sc.close();
			}

			}
		}
	}

}
