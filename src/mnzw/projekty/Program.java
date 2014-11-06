package mnzw.projekty;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import mnzw.projekty.HiberUtil.Mapping;
import mnzw.projekty.model.JezykProgramowania;
import mnzw.projekty.model.Kierownik;
import mnzw.projekty.model.Osoba;
import mnzw.projekty.model.Programista;
import mnzw.projekty.model.Projekt;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class Program {
	private static Session session;
	private static Transaction transaction;

	public static void main(String[] args) {
		SessionFactory sessionFactory = HiberUtil.getSessionFactory(Mapping.XML);

		insertSampleData(sessionFactory);
		
		session = sessionFactory.openSession();
		
		System.out.println("--------------------Query1-----------------------------------");
		
		hqlQuery1();
		
		System.out.println("-----------------------------------");
		
		criteriaQuery1();
				
		System.out.println("--------------------Query2-----------------------------------");
		
		hqlQuery2();
		
		System.out.println("-----------------------------------");
		
		criteriaQuery2();
		
		System.out.println("--------------------Query3-----------------------------------");
		
		hqlQuery3();
		
		System.out.println("-----------------------------------");
		
		criteriaQuery3();
		
		session.close();
	}
	
	private static void criteriaQuery3() {
		Criteria criteria = session.createCriteria(Programista.class, "prog");
		
		criteria.createAlias("prog.jezyki", "j");
		criteria.createAlias("j.jezyk", "jezyk");		
		
		criteria.setProjection(Projections.projectionList()				
				.add(Projections.groupProperty("jezyk.nazwa"))
				.add(Projections.avg("j.stopienZnajomosci"))
				);
		
		List list = criteria.list();
		
		Iterator iterator = list.iterator();
		
		while (iterator.hasNext()) {
			Object[] row = (Object[]) iterator.next();
			
			if((Double)row[1] < 66 || (Double)row[1] > 100) {
				iterator.remove();
			}
		}
		
		printResults(list);
	}

	private static void hqlQuery3() {
		String hql = "select jezyk.nazwa, avg(j.stopienZnajomosci) from Programista prog join prog.jezyki j join j.jezyk jezyk group by jezyk having avg(j.stopienZnajomosci) between 66 and 100";
		
		Query query = session.createQuery(hql);
		
		printResults(query.list());
	}

	private static void criteriaQuery2() {
		Criteria criteria = session.createCriteria(Projekt.class, "proj");
		criteria.createAlias("proj.zatrudnieni", "zatr");
		criteria.createAlias("zatr.programista", "prog");
		
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("proj.nazwa"))
				.add(Projections.property("prog.imie"))
				.add(Projections.property("prog.nazwisko"))
				.add(Projections.property("zatr.procentEtatu"))
		);
		
		printResults(criteria.list());
	}

	private static void hqlQuery2() {
		String hql = "select p.nazwa, prog.imie, prog.nazwisko, zatr.procentEtatu from Projekt p join p.zatrudnieni zatr join zatr.programista prog";
		
		Query query = session.createQuery(hql);
		
		printResults(query.list());
	}

	private static void criteriaQuery1() {
		Criteria crit = session.createCriteria(Osoba.class);		
		
		crit.setProjection(Projections.projectionList()
				.add(Projections.property("nazwisko"))
				.add(Projections.property("imie"))
		);
		
		crit.add(Restrictions.like("imie", "M%"));
		
		printResults(crit.list());
		
	}

	private static void hqlQuery1() {
		String hql = "select nazwisko, imie from Osoba where substring(imie, 1, 1) = 'M'";
		Query query = session.createQuery(hql);		
		
		List list = query.list();
		
		printResults(list);
	}

	private static void printResults(List list) {
		for (Object object : list) {
			if(object instanceof Object[]){			
				Object[] arr = (Object[])object;
				for (Object f : arr) {
					System.out.print(f);
					System.out.print(" ");
				}
				
				System.out.println();
			} else {
				System.out.println(object);
			}
		}
	}

	private static void insertSampleData(SessionFactory sessionFactory) {
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
			Kierownik janKowalski = new Kierownik("Jan", "Kowalski");

			saveAll(kamilMaciejczek, janKowalski);

			Projekt samolot = new Projekt("Samolot", new GregorianCalendar(2012, 6, 1), new GregorianCalendar(2014, 12, 25), kamilMaciejczek);

			samolot.zatrudnij(maciejNowak, 100, new GregorianCalendar(2012, 6, 1), new GregorianCalendar(2014, 12, 25));
			samolot.zatrudnij(marekFabia, 80, new GregorianCalendar(2013, 6, 1), new GregorianCalendar(2014, 11, 1));

			samolot.zglosZapotrzebowanie(erlang, 30, 35);
			samolot.zglosZapotrzebowanie(pascal, 100, 80);

			saveAll(samolot);

			Projekt magisterka = new Projekt("magisterka", new GregorianCalendar(2014, 6, 1), new GregorianCalendar(2015, 12, 25), janKowalski);
			magisterka.zatrudnij(zbigniewWawrzeczko, 100, new GregorianCalendar(2014, 6, 1), new GregorianCalendar(2015, 12, 25));
			magisterka.zatrudnij(marekFabia, 100, new GregorianCalendar(2014, 6, 1), new GregorianCalendar(2015, 12, 25));
			
			saveAll(magisterka);
			
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
