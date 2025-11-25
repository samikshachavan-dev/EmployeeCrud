package com.linkcode.hibernate1.Hibernate1;

import java.util.Scanner;

public class Main {

	private static Scanner sc=new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeDAO.openConnection();
		boolean flag=true;
		while(flag) {
			
			System.out.println("1. Insert\n2. Display Employee \n3. Display All \n4. Update \n5. Delete\n6. Exit");
			System.out.println("Enter choice:");
			int ch=sc.nextInt();
			switch(ch) {
			case 1->{
				
				System.out.println("How many employees you want to insert?");
				int n=sc.nextInt();
				for(int i=0; i<n; i++) {
					try {
				System.out.println("Enter id:");
				int id=sc.nextInt();
				
				System.out.println("Enter name:");
				sc.next();
				String name=sc.nextLine();
				System.out.println("Enter designation:");
				String designation=sc.nextLine();
				EmployeeDAO.insert(id, name, designation);
				}
				
				catch(DuplicateEmployeeException e) {
					System.out.println("ERROR: " + e.getMessage());
					i--;
				}
				}
				
				
			}
			case 2->{
				System.out.println("Enter id to insert:");
				int id=sc.nextInt();
				EmployeeDAO.display(id);
				
			}
			case 3->{
				EmployeeDAO.displayAll();
			}
			case 4->{
				System.out.println("Enter id to insert:");
				int id=sc.nextInt();
			}
			case 6->{
				System.out.println("Connection closed");
				flag=false;
				EmployeeDAO.closeConnection();
				sc.close();
			}
			
			}
		}
	}

}
