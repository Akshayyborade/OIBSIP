package com.db;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.service.ServiceRegistry;

import com.dao.TrainDao;
import com.entity.Books;
import com.entity.Train;
import com.entity.User;


public class Hibernetconfig {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration();
			Properties properties = new Properties();
			properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			properties.put(Environment.URL, "jdbc:mysql://localhost:3306/railconnect_db");
			properties.put(Environment.USER, "root");
			properties.put(Environment.PASS, "admin");
			properties.put(Environment.DIALECT, MySQLDialect.class.getName());

			// Set other Hibernate properties
			properties.put(Environment.SHOW_SQL, "true");
			properties.put(Environment.HBM2DDL_AUTO, "update");

			// Set the storage engine to InnoDB
			properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
			properties.put(Environment.STORAGE_ENGINE, "InnoDB");

			configuration.setProperties(properties);
			// Set more properties if needed
			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Train.class);
			
			configuration.addAnnotatedClass(Books.class);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		return sessionFactory;
	}
}
