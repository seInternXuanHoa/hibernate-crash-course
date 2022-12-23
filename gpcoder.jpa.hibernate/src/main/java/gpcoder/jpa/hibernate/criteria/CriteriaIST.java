package gpcoder.jpa.hibernate.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import gpcoder.jpa.hibernate.hibernate.User;
import gpcoder.jpa.hibernate.uitl.HibernateUtils;

public class CriteriaIST {
	public static void main(String[] args) {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			session.getTransaction().begin();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);

			// Find all:
			System.out.println("FIND ALL: ");
			session.createQuery(query).getResultList().forEach(System.err::println);

			// WHERE:
			query.where(builder.equal(root.get("id"), 2));
			System.out.println("WHERE: " + session.createQuery(query).uniqueResult());

			// SELECT:
			query.select(root.get("name"));
			System.out.println("SELECT: " + session.createQuery(query).getResultList());

			// MULTI SELECT:

			// Aggregate Functions

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
