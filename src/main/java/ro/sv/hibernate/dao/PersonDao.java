package ro.sv.hibernate.dao;

import static ro.sv.hibernate.utils.Constants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ro.sv.hibernate.model.Person;
import ro.sv.hibernate.utils.CustomQueryReturnType;
import ro.sv.hibernate.utils.PersonAndPhone;

public class PersonDao extends GenericDao<Person, UUID> {

	public PersonDao(Session session) {
		super(session, Person.class);
	}

	public void addPerson(Person p) {
		super.insert(p);
	}

	public void updatePerson(Person p) {
		super.update(p);
	}

	public List<Person> listPersons() {
		@SuppressWarnings("unchecked")
		List<Person> personsList = getSession().createQuery("from Person").list();

		return personsList;
	}

	public Person getPersonById(UUID id) {
		return super.findById(id);
	}

	public void removePerson(Person p) {
		super.delete(p);
	}

	public List<Person> findByNameLike(String like) {
		List<Person> results = new ArrayList<Person>();

		Session session = getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
		Root<Person> root = criteria.from(Person.class);
		criteria.multiselect(builder.like(root.get("firstName"), like));
		Query<Person> query = session.createQuery(criteria);

		results = query.getResultList();

		return results;
	}

	public List<PersonAndPhone> getPersonAndPhoneByPersonId(String personId) {
		Session session = getSession();

		List<PersonAndPhone> results = session.createQuery(GET_PERSON_AND_PHONE_BY_PERSON_ID, PersonAndPhone.class)
				.setParameter("personId", UUID.fromString(personId)).getResultList();

		return results;
	}

	public List<CustomQueryReturnType> getCustomReturnType() {
		Session session = getSession();

		List<CustomQueryReturnType> customReturnType = session
				.createQuery(GET_CUSTOM_QUERY_RETURN_TYPE, CustomQueryReturnType.class).getResultList();

		return customReturnType;
	}
}
