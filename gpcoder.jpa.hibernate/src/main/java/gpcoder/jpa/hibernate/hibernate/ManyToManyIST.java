package gpcoder.jpa.hibernate.hibernate;

import org.hibernate.Session;

import gpcoder.jpa.hibernate.uitl.HibernateUtils;

public class ManyToManyIST {
	public static void main(String[] args) {
		Session session = HibernateUtils.getSessionFactory().openSession();

		session.getTransaction().begin();
		Computer computer = new Computer("Windows");
		Computer istComputer = new Computer("Linux");
		session.save(computer);
		session.save(istComputer);

		User user = new User("Le Xuan Hoa");
		User ist = new User("seInternXuanHoa");
		session.save(user);
		session.getTransaction().commit();
	}
}
