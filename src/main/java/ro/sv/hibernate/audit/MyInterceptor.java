package ro.sv.hibernate.audit;

import java.io.Serializable;
import java.sql.SQLException;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.hibernate.type.Type;

public class MyInterceptor extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (entity instanceof IAuditLog) {

			session.doWork(new Work() {
				@Override
				public void execute(java.sql.Connection connection) throws SQLException {
					AuditLogUtil.LogIt("UPDATE", (IAuditLog) entity, currentState, previousState, propertyNames,
							connection);
				}
			});

			return true;
		}
		return false;
	}

}
