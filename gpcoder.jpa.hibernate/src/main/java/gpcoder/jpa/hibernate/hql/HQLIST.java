package gpcoder.jpa.hibernate.hql;

import org.hibernate.Session;

import gpcoder.jpa.hibernate.hibernate.User;
import gpcoder.jpa.hibernate.uitl.HibernateUtils;

public class HQLIST {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			session.getTransaction().begin();
			String query = "";
			// Find All:
			query = "FROM User";
			System.out.println("FIND ALL: ");
			session.createQuery(query, User.class).list().stream().forEach(System.err::println);

			// Find By:
			query = "FROM User AS U WHERE U.id = :id";
			System.out.println("FIND BY: ");
			System.out.println(session.createQuery(query, User.class).setParameter("id", 1L).uniqueResult());

			// Select:
			query = "SELECT U.name FROM User AS U WHERE U.id = :id";
			System.out.println("SELECT: ");
			System.err.println(session.createQuery(query, String.class).setParameter("id", 1L).uniqueResult());

			// Where:
			query = "FROM User AS U WHERE U.id > :id";
			System.out.println("WHERE: ");
			session.createQuery(query, User.class).setParameter("id", 2L).list().stream().forEach(System.err::println);

			// Group By:
			System.out.println("GROUP BY: ");
			query = "SELECT U.name FROM User AS U GROUP BY U.name";
			session.createQuery(query).list().stream().forEach(System.out::println);

			// Update:
			query = "UPDATE User AS U SET U.name = :name WHERE U.id = :id";
			System.out.println("UPDATE: "
					+ session.createQuery(query).setParameter("name", "GUEST").setParameter("id", 1L).executeUpdate());

			// Delete:
			query = "DELETE FROM User AS U WHERE U.id = :id";
			System.out.println("DELETE: " + session.createQuery(query).setParameter("id", 1L).executeUpdate());

			// Insert:
			query = "INSERT INTO User(name) SELECT :name FROM User";
			System.out.println("INSERT: " + session.createQuery(query).setParameter("name", "Guest").executeUpdate());

			// Paging:
			System.out.println("PAGING: ");
			query = "FROM User";
			session.createQuery(query, User.class).setMaxResults(10).setFirstResult(5).list()
					.forEach(System.out::println);

			// Join:
			System.out.println("JOIN: ");

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
