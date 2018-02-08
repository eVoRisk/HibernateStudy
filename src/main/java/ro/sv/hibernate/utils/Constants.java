package ro.sv.hibernate.utils;

public final class Constants {

	private Constants() {

	}

	// Queries
	public final static String GET_PERSON_AND_PHONE_BY_PERSON_ID = "select NEW ro.sv.hibernate.utils.PersonAndPhone(p.id, p.firstName, "
			+ "p.lastName, p.gender,p.birthday, p.personType, pp.name, pp.type) from Person p inner join p.phones pp "
			+ "where p.id = :personId";

	public final static String GET_CUSTOM_QUERY_RETURN_TYPE = "select NEW ro.sv.hibernate.utils.CustomQueryReturnType(i.id, p.id, "
			+ "p.firstName, p.lastName) from Invoice i inner join i.person p";

}
