package com.linkcode.hibernate1.Hibernate1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {

private static final EntityManagerFactory factory=Persistence.createEntityManagerFactory("hibernate");
	
public static EntityManager getEntityManager() {
	return factory.createEntityManager();
}
	
public static void shutdown() {
	factory.close();
}
}
