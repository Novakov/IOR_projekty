package mnzw.projekty;

import java.util.GregorianCalendar;

import mnzw.projekty.HiberUtil.Mapping;
import mnzw.projekty.model.JezykProgramowania;
import mnzw.projekty.model.Kierownik;
import mnzw.projekty.model.Programista;
import mnzw.projekty.model.Projekt;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Program {
	private static Session session;
	private static Transaction transaction;

	public static void main(String[] args) {
		SessionFactory sessionFactory = HiberUtil.getSessionFactory(Mapping.XML);

		session = sessionFactory.openSession();
		transaction = session.beginTransaction();

		try {
			Programista maciejNowak = new Programista("Maciej", "Nowak");
			Programista zbigniewWawrzeczko = new Programista("Zbigniew", "Wawrzeczko");
			Programista marekFabia = new Programista("Marek", "Fabia");

			saveAll(maciejNowak, zbigniewWawrzeczko, marekFabia);

			JezykProgramowania java = new JezykProgramowania("Java");
			JezykProgramowania csharp = new JezykProgramowania("C#");
			JezykProgramowania erlang = new JezykProgramowania("Erlang");
			JezykProgramowania pascal = new JezykProgramowania("Pascal");

			saveAll(java, csharp, erlang, pascal);

			maciejNowak.addJezyk(csharp, 100);
			maciejNowak.addJezyk(erlang, 49);
			maciejNowak.addJezyk(java, 2);

			zbigniewWawrzeczko.addJezyk(java, 100);
			zbigniewWawrzeczko.addJezyk(pascal, 110);

			marekFabia.addJezyk(pascal, 99);
			marekFabia.addJezyk(java, 100);

			saveAll(maciejNowak, zbigniewWawrzeczko, marekFabia);

			Kierownik kamilMaciejczek = new Kierownik("Kamil", "Maciejczek");

			saveAll(kamilMaciejczek);

			Projekt samolot = new Projekt("Samolot", new GregorianCalendar(2012, 6, 1), new GregorianCalendar(2014, 12, 25), kamilMaciejczek);

			samolot.zatrudnij(maciejNowak, 100, new GregorianCalendar(2012, 6, 1), new GregorianCalendar(2014, 12, 25));
			samolot.zatrudnij(marekFabia, 80, new GregorianCalendar(2013, 6, 1), new GregorianCalendar(2014, 11, 1));

			samolot.zglosZapotrzebowanie(erlang, 30, 35);
			samolot.zglosZapotrzebowanie(pascal, 100, 80);

			saveAll(samolot);

			transaction.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void saveAll(Object... entities) {
		for (Object entity : entities) {
			session.save(entity);
		}

		session.flush();
	}
}
