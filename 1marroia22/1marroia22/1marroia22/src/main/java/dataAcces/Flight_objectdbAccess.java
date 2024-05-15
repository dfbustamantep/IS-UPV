package dataAcces;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import domain.ConcreteFlight;
import domain.Flight;


public class Flight_objectdbAccess {
	private EntityManager db;
	private EntityManagerFactory emf;
	String fileName = "vuelos.odb";
	
	public Flight_objectdbAccess() {
		emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
	 	db = emf.createEntityManager();
	 	System.out.println("Base de datos abierta");
	 }
	
	public Flight_objectdbAccess(boolean initializeDB) {
		if(initializeDB) {
			// Si initializeDB es true, borramos la base de datos existente y creamos una nueva
			File file = new File(fileName);
	        if (file.exists()) {
	            if (file.delete()) {
	                System.out.println("Base de datos anterior eliminada");
	            } else {
	                System.err.println("Error al eliminar la base de datos anterior");
	            }
	        }
            emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
            db = emf.createEntityManager();
            System.out.println("Nueva base de datos creada");
		}
		else {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
            db = emf.createEntityManager();
            System.out.println("Base de datos abierta");
		}
		
		
	 }

	public void close(){
		db.close();
	 	System.out.println("Base de datos cerrada");
	}
	//metodos Flight
	public void storeFlight(Flight flight) {
		try {
			if (!db.getTransaction().isActive()) {
	            db.getTransaction().begin(); // Solo inicia una nueva transacción si no hay una activa
	        }
			
			if(flight!=null && !flightExist(flight)) {
				db.persist(flight);
				 db.getTransaction().commit();
				 System.out.println("Insertado: " + flight);
			}
			else {
				System.out.println("El vuelo ya existe "+flight);
			}
		}
		catch(Exception e) {
			 if (db.getTransaction().isActive()) {
		            db.getTransaction().rollback(); // Revierte la transacción en caso de error
		        }
			 e.printStackTrace(); // Puedes imprimir o manejar la excepción según tus necesidades
			}
		}
		
	
	public boolean flightExist (Flight flight) {
		return db.find(Flight.class,flight.getFlightCode())!=null;
	}
	
	public Flight getFlightbyCode(String code) {
		return db.find(Flight.class,code);
	}
	
	public void getAllFlights() {
		TypedQuery<Flight> query = db.createQuery("SELECT f FROM Flight f",Flight.class);
		List<Flight> flights = query.getResultList();	
	}
	
	public void getFlightByCode(String code) {
		TypedQuery<Flight> query =
		 db.createQuery("SELECT f FROM Flight f WHERE f.flightCode='"+code+"'",Flight.class);
		List<Flight> flights = query.getResultList();
	}
	
	public void deleteFlight(Flight f) {
		if (!db.getTransaction().isActive()) {
            db.getTransaction().begin(); // Solo inicia una nueva transacción si no hay una activa
        }
		db.remove(f);
		db.getTransaction().commit();
	}
	
	public void deleteFlightByCode(String code) {
		if (!db.getTransaction().isActive()) {
            db.getTransaction().begin(); // Solo inicia una nueva transacción si no hay una activa
        }
		Query query = db.createQuery("DELETE FROM Flight f WHERE f.flightCode='"+code+"'");
		int deletedFlights = query.executeUpdate();
	}
	
	
	
	//metodos ConcreteFlight
	public void storeConcreteFlight(ConcreteFlight Cflight) {
		if (!db.getTransaction().isActive()) {
            db.getTransaction().begin(); // Solo inicia una nueva transacción si no hay una activa
        }
		//boolean existe = db.find(Flight.class, flight.getFlightID()) != null;;
		if(Cflight!=null && !ConcreteFlightExist (Cflight) ) {
			db.persist(Cflight);
			 db.getTransaction().commit();
			 System.out.println("Insertado: " + Cflight);
		}
		else {
			System.out.println("No se pudo insertar "+Cflight);
		}
	}
	
	public boolean ConcreteFlightExist (ConcreteFlight Cflight) {
		return db.find(ConcreteFlight.class,Cflight.getConcreteFlightCode())!=null;
	}
	
	public ConcreteFlight getConcreteFlightbyCode(String code) {
		return db.find(ConcreteFlight.class,code);
	}
	
	public void getAllConcreteFlight() {
		TypedQuery<ConcreteFlight> query = db.createQuery("SELECT cf FROM ConcreateFlight cf",ConcreteFlight.class);
		List<ConcreteFlight> flights = query.getResultList();
	}
	
	public void getConcreteFlightByCode(String code) {
		TypedQuery<ConcreteFlight> query =
		db.createQuery("SELECT cf FROM ConcreteFlight cf WHERE cf.concreteFlightCode='"+code+"'",ConcreteFlight.class);
		List<ConcreteFlight> concreteFlight = query.getResultList();
	}
	
	public void deleteConcreteFlight(ConcreteFlight cf) {
		try {
			if (!db.getTransaction().isActive()) {
	            db.getTransaction().begin(); // Solo inicia una nueva transacción si no hay una activa
	        }
			db.remove(cf);
			db.getTransaction().commit();
		}
		catch(Exception e) {
			
		}
		
	}
	
	public void deleteConcreteFlightByCode(String code) {
		if (!db.getTransaction().isActive()) {
            db.getTransaction().begin(); // Solo inicia una nueva transacción si no hay una activa
        }
		Query query = db.createQuery("DELETE FROM ConcreteFlight cf WHERE cf.concreteFlightCode='"+code+"'");
		int deletedConcreteFlight = query.executeUpdate();
	}
	
	public void updateConcreteFlight(String concreteFlightCode, String tipoAsiento) {
	    ConcreteFlight concreteFlight = getConcreteFlightbyCode(concreteFlightCode);

	    if (concreteFlight == null) {
	        System.out.println("Vuelo con código " + concreteFlightCode + " no encontrado en la base de datos");
	    } else {
	    	if (!db.getTransaction().isActive()) {
	            db.getTransaction().begin(); // Solo inicia una nueva transacción si no hay una activa
	        }

	        // Actualizar el número de asientos según el tipo
	        switch (tipoAsiento) {
	            case "Business":
	                concreteFlight.setBusinessNumber(concreteFlight.getBusinessNumber() - 1);
	                break;
	            case "First":
	                concreteFlight.setFirstNumber(concreteFlight.getFirstNumber() - 1);
	                break;
	            case "Tourist":
	                concreteFlight.setTouristNumber(concreteFlight.getTouristNumber() - 1);
	                break;
	            default:
	                System.out.println("Tipo de asiento no válido: " + tipoAsiento);
	                break;
	        }

	        db.getTransaction().commit();
	        System.out.println("Vuelo actualizado: " + concreteFlight);
	    }
	}
	
	public List<ConcreteFlight> getConcreteFlights(String departCity, String arrivalCity, java.util.Date date) {
        TypedQuery<ConcreteFlight> query = db.createQuery(
        		"SELECT cf FROM ConcreteFlight cf WHERE cf.flight.departingCity = :departCity AND cf.flight.arrivingCity = :arrivalCity AND cf.date = :date",
                ConcreteFlight.class);

        query.setParameter("departCity", departCity);
        query.setParameter("arrivalCity", arrivalCity);
        query.setParameter("date", date);

        return query.getResultList();
    }
	
}
