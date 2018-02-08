package ro.sv.hibernate.persistence;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import ro.sv.hibernate.audit.MyInterceptor;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	private static final ServiceRegistry serviceRegistry;
	private static MyInterceptor interceptor;

	static {
		try {
			Properties properties = new Properties();

			properties.setProperty("hibernate.bytecode.use_reflection_optimizer", "false");
			properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			properties.setProperty("hibernate.connection.password", "password");
			properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/study");
			properties.setProperty("hibernate.connection.username", "root");
			properties.setProperty("connection.pool_size", "5");
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
			properties.setProperty("hibernate.show_sql", "true");
			properties.setProperty("hibernate.format_sql", "true");
			properties.setProperty("hibernate.hbm2ddl.auto", "update");
			properties.setProperty("hibernate.globally_quoted_identifiers", "true");
			properties.setProperty("hibernate.globally_quoted_identifiers_skip_column_definitions", "true");

			interceptor = new MyInterceptor();
			Configuration configuration = new Configuration().setInterceptor(interceptor);
			configuration.addProperties(properties);

			configuration.addAnnotatedClass(ro.sv.hibernate.model.Person.class);
			configuration.addAnnotatedClass(ro.sv.hibernate.model.Invoice.class);
			configuration.addAnnotatedClass(ro.sv.hibernate.model.PersonRegular.class);
			configuration.addAnnotatedClass(ro.sv.hibernate.model.PersonStudent.class);
			configuration.addAnnotatedClass(ro.sv.hibernate.audit.AuditLog.class);

			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

	public static MyInterceptor getInterceptor() {
		return interceptor;
	}

}
