package persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import domain.City;
import domain.Country;
import domain.President;

public class DataAccess implements DataAccessInterface {
	
	private String dbName = "America.odb";
	private EntityManagerFactory emf;
	private EntityManager db;

	City currentCity;
	
	
	//private String dbName = "America.odb";
	private boolean local=false;
	String ip="localhost";
	int port=6136;
	String user="admin";
	String pass="admin";
	
	public DataAccess() {
	}
	private void openDb() {
/*
 //LOCAL
		emf = Persistence.createEntityManagerFactory(dbName);
		db = emf.createEntityManager();	

*/		
		if (!local) {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", user);
			properties.put("javax.persistence.jdbc.password", pass);
			emf = Persistence.createEntityManagerFactory(
			 "objectdb://"+ip+":"+port+"/"+dbName, properties);
			db = emf.createEntityManager();
			}
			else {
			emf = Persistence.createEntityManagerFactory(dbName);
			db = emf.createEntityManager();
			}
		System.out.println("Database opened");
		
	}
	
	private void closeDb() {
		db.close();
		System.out.println("Database closed");

	}
	
	public List<String> getAllCountryNames() {
		List<String> lista = new ArrayList<String>();
		try {
			openDb();
			TypedQuery<Country> query = db.createQuery("SELECT c FROM Country c", Country.class);
			List<Country> results = query.getResultList();
			for (Country pais : results) {
				lista.add(pais.getNameCountry());
			}
		} catch	(Exception e) {	e.printStackTrace();
		} finally { closeDb(); }
		return lista;
	}			
	
	
	public List<Country> getAllCountries() {
		List<Country> results=null;
		try {
			openDb();
			TypedQuery<Country> query = db.createQuery("SELECT c FROM Country c", Country.class);
			results = query.getResultList();
			for (Country c: results) {
				System.out.println(c+" with neighbors: "+c.getNeighbors());
			}
		} catch	(Exception e) {	e.printStackTrace();
		} finally { closeDb(); }
		return results;
	}		
	
	
	public boolean saveCountryAndCity (String countryName, int countryPopul,String capitalName,int capitalPopul,String airportName, int foundationYear){
		boolean error=true;
		try {
			openDb();

			// Your code here (section 2)
			
			db.getTransaction().begin();
			Country country = new Country(countryName, countryPopul);
			City capital = new City( capitalName,country,capitalPopul,airportName,foundationYear);
			db.persist(country);
			//db.persist(capital);
			 db.getTransaction().commit();
			 error=false;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { closeDb(); }
		return error;
	}
	

	public boolean removeCountry (Country country){
		boolean res=true;
		try {
			openDb(); //abrir conexion a a bd
			db.getTransaction().begin(); //abrir una transaccion
			Country c=db.find(Country.class,country.getNameCountry()); //pais que se esta buscando borrar 
			db.remove(c); //se borra el pais
			
			for (Country nc: c.getNeighbors())
				nc.getNeighbors().remove(c);
			
			db.getTransaction().commit();//confirmar
			System.out.println("object removed "+country.getNameCountry());	//imprimir en pantalla
		} catch (Exception e) {
			e.printStackTrace();
			res=false;
		} finally { closeDb(); }//cerramos la bd
		return res;
	}
	

	public boolean removePresidentFromCountry (Country country){
		boolean res=true;
		try {
			openDb();//abrir la conexion a la bd
			db.getTransaction().begin();//abrir una transaccion
			Country c=db.find(Country.class,country.getNameCountry());//buscamos el pais
			c.setPresident(null);//eliminar informacion del presidente
			db.getTransaction().commit();//confirmamos
			System.out.println("president deleted in "+country.getNameCountry());	
		} catch (Exception e) {
			e.printStackTrace();
			res=false;
		} finally { closeDb(); }//cerramos la bd
		return res;
	}
	
	
	
	
	
	public void assignNeighbors (Country home, List<Country> neighbors) {
		try {
			openDb();
			db.getTransaction().begin();
			Country homeDB = db.find(Country.class, home.getNameCountry());
			for (Country neighbor : neighbors) {
  			 Country neighDB = db.find(Country.class, neighbor);
			 homeDB.addNeighbor(neighDB);
			 neighDB.addNeighbor(homeDB);
			}
			// To comment or descomment (section 4)
//			db.persist(homeDB);
//			db.persist(neighDB);
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { closeDb(); }
	}

	
	public Collection<City> hugeCities(int popul){
		
		List<City> results=null;
		try {
			openDb();
//			TypedQuery<City> query = db.createQuery("SELECT ci FROM City ci WHERE ci.populCity>="+popul, City.class);

			TypedQuery<City> query = db.createQuery("SELECT ci FROM City ci where ci.populCity>=?1", City.class);
			query.setParameter(1, popul);
			
			results = query.getResultList();
		} catch	(Exception e) {	e.printStackTrace();
		} finally { closeDb(); }
		return results;
	}

	public Collection<Country> countriesWithHugeCapitals1(int popul){
		
		List<Country> results=null;
		try {
			openDb();
			TypedQuery<Country> query = db.createQuery("SELECT c FROM Country c join c.capital ci where ci.populCity>=?1", Country.class);
			query.setParameter(1, popul);
			results = query.getResultList();
		} catch	(Exception e) {	e.printStackTrace();
		} finally { closeDb(); }
		return results;
	}
	
	public Collection<Country> countriesWithHugeCapitals2(int popul){
		
		List<Country> results=null;
		try {
			openDb();
//			TypedQuery<Country> query = db.createQuery("SELECT ci.country FROM City ci where ci.populCity>="+popul, Country.class);

			TypedQuery<Country> query = db.createQuery("SELECT ci.country FROM City ci where ci.populCity>=?1", Country.class);
			query.setParameter(1, popul);
			
			results = query.getResultList();
		} catch	(Exception e) {	e.printStackTrace();
		} finally { closeDb(); }
		return results;
	}
	
	public Collection<Country> countriesWithHugeCapitals3(int popul){
		
		List<Country> results=null;
		try {
			openDb();
			TypedQuery<Country> query = db.createQuery("SELECT c FROM Country c where c.capital.populCity>=?1", Country.class);
			query.setParameter(1, popul);
			results = query.getResultList();
		} catch	(Exception e) {	e.printStackTrace();
		} finally { closeDb(); }
		return results;
	}
	


	
	public Collection<Country> countriesWithHugeCapitals4(int popul){
		
		ArrayList<Country> res=new ArrayList();
		try {
			openDb();
			TypedQuery<Country> query = db.createQuery("SELECT c FROM Country c", Country.class);
			List<Country> results = query.getResultList();

			for (Country pais : results) {
				if (pais.getCapital().getPopulCity()>=popul)
					res.add(pais);
			}
		} catch	(Exception e) {	e.printStackTrace();
		} finally { closeDb(); }
		return res;
	}


}
