package mnzw.projekty;

import mnzw.projekty.HiberUtil.Mapping;

import org.hibernate.SessionFactory;

public class Program {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HiberUtil.getSessionFactory(Mapping.XML);
				
	}
}
