package gpcoder.jpa.hibernate.lifecycle;

import org.hibernate.Session;

import gpcoder.jpa.hibernate.hibernate.User;
import gpcoder.jpa.hibernate.uitl.HibernateUtils;

public class LifeCycleIST {
	public static void main(String[] args) {
		try (Session session = HibernateUtils.getSessionFactory().openSession();) {
			User user = new User("Admin");
			
			// Transient -> Persistent:
			session.getTransaction().begin();
			session.persist(user);
			session.save(user);
			user.setName("Root");
			session.merge(user);
			session.update(user);
			
			
			// Persistent:
			System.out.println("Load: " + session.load(User.class, 2L));
			System.out.println("Get: " + session.get(User.class, 1L));
			System.out.println("Find: "+ session.find(User.class, 1L));
			
			// Persistent –> Detached
			session.evict(user);
			session.clear();
			
			// Detached –> Persistent
			session.refresh(user);
			
			// Persistent –> Removed
			session.delete(user);
			session.remove(user);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
