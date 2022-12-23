package gpcoder.jpa.hibernate.nativesql;

import org.hibernate.Session;

import gpcoder.jpa.hibernate.hibernate.User;
import gpcoder.jpa.hibernate.uitl.HibernateUtils;

public class NativeSQLIST {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try (Session session = HibernateUtils.getSessionFactory().openSession();) {
			session.getTransaction().begin();

			// Find all:
			System.out.println("FIND ALL: ");
			session.createNativeQuery("SELECT * FROM user").getResultList().stream().forEach(System.err::println);

			// Paging:
			System.out.println("PAGING: ");
			session.createNativeQuery("SELECT * FROM user", User.class).setFirstResult(1).setMaxResults(2)
					.getResultList().stream().forEach(System.err::println);

			// Where:
			System.out.println("WHERE: ");
			session.createNativeQuery("SELECT * FROM user where name = :name").setParameter("name", "seInternXuanHoa")
					.addEntity(User.class).getResultList().forEach(System.err::println);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
