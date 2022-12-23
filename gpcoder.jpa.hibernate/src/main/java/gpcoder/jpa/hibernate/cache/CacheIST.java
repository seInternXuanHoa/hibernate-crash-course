package gpcoder.jpa.hibernate.cache;

import org.hibernate.Session;

import gpcoder.jpa.hibernate.hibernate.User;
import gpcoder.jpa.hibernate.uitl.HibernateUtils;

public class CacheIST {
	public static void main(String[] args) {
		try (Session session = HibernateUtils.getSessionFactory().openSession();
				Session otherSession = HibernateUtils.getSessionFactory().openSession()) {
			session.getTransaction().begin();
			// First Level:
			for (int i = 0; i < 10; i++) {
				User user = session.get(User.class, 2L);
				User otherUser = otherSession.get(User.class, 2L);
				System.err.println("First Level: " + user);
				System.err.println("First Level 2: " + otherUser);
				if (i == 2) {
					session.evict(user);
				}
				if (i == 5) {
					session.clear();
				}

			}

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
