package gpcoder.jpa.hibernate.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import gpcoder.jpa.hibernate.uitl.HibernateUtils;

public class Application {
	public static void main(String[] args) {
		try (Session session = HibernateUtils.getSessionFactory().openSession();) {
			session.beginTransaction();
			User user = new User("Le Xuan Hoa");
			Account account = new Account("seInternXuanHoa", "seInternXuanHoa", new Date(), new Date());
			Account ist = new Account("seInternXuanHoa", "seInternXuanHoa", new Date(), new Date());

			// Save
			System.out.println("Save: " + session.save(user));

			// Count
			System.out.println("Count: " + session.createQuery("SELECT COUNT(id) FROM User", Long.class).uniqueResult());

			// Find By Id
			User usr = session.find(User.class, 1L);
			System.out.println("User at index 1: " + usr);

			// Find All
			List<User> users = session.createQuery("FROM User", User.class).list();
			System.out.println("Find all: ");
			users.stream().forEach(System.out::println);

			// Update
			usr.setName("seInternXuanHoa");
			session.update(user);
			System.out.println("Update: ");

			// Delete
			System.out.println("Delete: ");
			session.delete(usr);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
