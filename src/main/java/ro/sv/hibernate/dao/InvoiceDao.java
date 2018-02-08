package ro.sv.hibernate.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ro.sv.hibernate.model.Invoice;

public class InvoiceDao extends GenericDao<Invoice, UUID> {

	public InvoiceDao(Session session) {
		super(session, Invoice.class);
	}

	public List<Invoice> findInvoiceCreatedBefore(LocalDate date) {

		List<Invoice> results = new ArrayList<Invoice>();

		Session session = getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Invoice> criteria = builder.createQuery(Invoice.class);
		Root<Invoice> root = criteria.from(Invoice.class);
		criteria.multiselect(builder.lessThan(root.get("createdOn"), date));
		Query<Invoice> query = session.createQuery(criteria);

		results = query.getResultList();

		return results;

	}

	public List<Invoice> findInvoiceCreatedAfter(LocalDate date) {
		List<Invoice> results = new ArrayList<Invoice>();

		Session session = getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Invoice> criteria = builder.createQuery(Invoice.class);
		Root<Invoice> root = criteria.from(Invoice.class);
		criteria.multiselect(builder.greaterThan(root.get("createdOn"), date));
		Query<Invoice> query = session.createQuery(criteria);

		results = query.getResultList();

		return results;
	}

}
