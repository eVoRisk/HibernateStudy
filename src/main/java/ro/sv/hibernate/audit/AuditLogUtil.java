package ro.sv.hibernate.audit;

import java.sql.Connection;
import java.sql.Timestamp;

import org.hibernate.StatelessSession;

import ro.sv.hibernate.persistence.HibernateUtil;

public class AuditLogUtil {

	public static void LogIt(String action, IAuditLog entity, Object[] currentState, Object[] previousState,
			String[] propertyNames, Connection conn) {

		StatelessSession tempSession = HibernateUtil.getSessionFactory().openStatelessSession(conn);

		StringBuffer propName = new StringBuffer();
		StringBuffer oldValue = new StringBuffer();
		StringBuffer newValue = new StringBuffer();

		for (int i = 0; i < propertyNames.length; i++) {
			if (previousState[i] != null && currentState[i] != null && propertyNames[i] != null
					&& !previousState[i].toString().equalsIgnoreCase(currentState[i].toString())) {
				propName.append(propertyNames[i].toString() + " ");
				oldValue.append(previousState[i].toString() + " ");
				newValue.append(currentState[i].toString() + " ");
			}
		}

		try {

			AuditLog auditRecord = new AuditLog(action, new Timestamp(System.currentTimeMillis()),
					entity.getClass().getSimpleName(), propName.toString(), oldValue.toString(), newValue.toString(),
					entity.getLogDetails());
			tempSession.insert(auditRecord);

		} finally {
			tempSession.close();
		}
	}
}
