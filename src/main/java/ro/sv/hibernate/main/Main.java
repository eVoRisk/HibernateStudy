package ro.sv.hibernate.main;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import javax.persistence.OptimisticLockException;

import org.hibernate.Session;

import ro.sv.hibernate.dao.InvoiceDao;
import ro.sv.hibernate.dao.PersonDao;
import ro.sv.hibernate.model.Invoice;
import ro.sv.hibernate.model.Person;
import ro.sv.hibernate.model.PersonRegular;
import ro.sv.hibernate.model.PersonStudent;
import ro.sv.hibernate.model.Phone;
import ro.sv.hibernate.persistence.HibernateUtil;
import ro.sv.hibernate.utils.CustomQueryReturnType;
import ro.sv.hibernate.utils.Gender;
import ro.sv.hibernate.utils.PersonAndPhone;

public class Main {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateUtil.getInterceptor().setSession(session);

		// session.buildLockRequest(new
		// LockOptions(LockMode.PESSIMISTIC_WRITE)).lock(person);

		boolean success = false;

		do {
			Session newSession = HibernateUtil.getSessionFactory().openSession();

			PersonDao personDao = new PersonDao(newSession);
			Person person = personDao.findById(UUID.fromString("876f5eb4-5bd5-4d83-82f9-3560ad3cbe74"));

			person.setFirstName("Vladimir");
			person.setLastName("Enescu");

			newSession.beginTransaction();

			try {
				newSession.getTransaction().commit();
				success = true;

			} catch (OptimisticLockException ex) {
				ex.printStackTrace();

				newSession.getTransaction().rollback();
			} finally {
				newSession.evict(person);
				newSession.close();
			}
		} while (!success);

		session.close();
		HibernateUtil.shutdown();
	}

	private static void initialMain(Session session) {
		session.beginTransaction();

		PersonDao personDao = new PersonDao(session);
		InvoiceDao invoiceDao = new InvoiceDao(session);

		Person person = new Person();
		person.setFirstName("Vlad-Gabriel");
		person.setLastName("Enachi");
		person.setGender(Gender.MALE);
		person.setBirthday(LocalDate.of(1993, Month.OCTOBER, 29));
		person.getPhones().add(new Phone("landline", "0232-261-341"));
		person.getPhones().add(new Phone("mobile", "0740-123-562"));
		personDao.insert(person);

		Person secondPerson = new Person();
		secondPerson.setFirstName("First-Name");
		secondPerson.setLastName("Last");
		secondPerson.setGender(Gender.FEMALE);
		secondPerson.setBirthday(LocalDate.of(1993, Month.APRIL, 1));
		personDao.insert(secondPerson);

		PersonStudent personStudent = new PersonStudent(person);
		personStudent.setRegistrationNumber("31040701PM162229");
		personDao.insert(personStudent);

		PersonRegular personRegular = new PersonRegular(person);
		personRegular.setPersonalIdentificationNumber("1931029226472");
		personDao.insert(personRegular);

		person.setFirstName("Vlad");
		person.setLastName("Ena");

		Invoice invoice = new Invoice();
		invoice.setCreatedOn(LocalDate.now());
		invoice.setPerson(person);

		invoiceDao.insert(invoice);

		List<PersonAndPhone> personsAndPhones = personDao
				.getPersonAndPhoneByPersonId("1c8183f1-81d2-49c3-9bba-18ce7764b2ca");

		personsAndPhones.stream().forEach(p -> System.out.println(p.toString()));

		List<CustomQueryReturnType> customReturnType = personDao.getCustomReturnType();
		customReturnType.forEach(p -> System.out.println(p.toString()));

		session.getTransaction().commit();
	}
}
