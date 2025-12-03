package com.linkcode.hibernate1.Hibernate1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ProjectDAO {

	public static void addProject(int pid, String pname, String cName) {
		// TODO Auto-generated method stub
		EntityManager manager=HibernateUtil.getEntityManager();
		EntityTransaction transaction=manager.getTransaction();
		try {
			transaction.begin();
			ProjectDTO project=manager.find(ProjectDTO.class, pid);
			if(project==null) {
				ProjectDTO projectNew=new ProjectDTO();
				projectNew.setPid(pid);
				projectNew.setPname(pname);
				projectNew.setClientName(cName);
				manager.persist(projectNew);
				System.out.println("Project added successfully");
			}
			else {
				System.out.println("Project Already Exists");
				transaction.rollback();
			}
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
