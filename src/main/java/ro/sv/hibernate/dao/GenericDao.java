package ro.sv.hibernate.dao;

import java.io.Serializable;

import org.hibernate.Session;

public abstract class GenericDao<T, I extends Serializable> {

	private Session session;
	private Class<T> klass;

	public Session getSession() {
		return session;
	}
	
	public GenericDao(Session session, Class<T> klass) {
		this.session = session;
		this.klass = klass;
	}

	public T findById(I id) {
		T result = null;

		if (id != null) {
			result = session.get(klass, id);
		}

		return result;
	}

	public void insert(T obj) {
		session.save(obj);
	}

	public void delete(T obj) { 
		session.delete(obj);
	}

	public void update(T obj) {
		session.update(obj);
	}

}
