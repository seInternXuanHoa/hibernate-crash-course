package gpcoder.jpa.hibernate.hibernate;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.hibernate.Session;

import gpcoder.jpa.hibernate.uitl.HibernateUtils;

public class ManyToOneIST {
	public static void main(String[] args) {
		Session session = HibernateUtils.getSessionFactory().openSession();

		User user = new User("Le Xuan Hoa");
		Account account = new Account("seInternXuanHoa", "seInternXuanHoa", new Date(), new Date());
		Account ist = new Account("seInternXuanHoa", "seInternXuanHoa", new Date(), new Date());

		// Save
		user.setAccounts(new HashSet<>(Arrays.asList(account, ist)));
		session.save(user);
	}
}
