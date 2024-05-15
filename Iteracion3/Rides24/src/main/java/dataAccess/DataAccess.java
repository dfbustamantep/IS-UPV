package dataAccess;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Driver;
import domain.Factura;
import domain.Reseñas;
import domain.Ride;
import domain.RideSolicitado;
import domain.Viajecompuesto;
import domain.viajero;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	private  EntityManager  db;
	private  EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess()  {
		if (c.isDatabaseInitialized()) {
			String fileName=c.getDbFilename();

			File fileToDelete= new File(fileName);
			if(fileToDelete.delete()){
				File fileToDeleteTemp= new File(fileName+"$");
				fileToDeleteTemp.delete();

				  System.out.println("File deleted");
				} else {
				  System.out.println("Operation failed");
				}
		}
		open();
		if  (c.isDatabaseInitialized())initializeDB();

		System.out.println("DataAccess created => isDatabaseLocal: "+c.isDatabaseLocal()+" isDatabaseInitialized: "+c.isDatabaseInitialized());

		close();

	}

    public DataAccess(EntityManager db) {
    	this.db=db;
    }



	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB(){

		db.getTransaction().begin();

		try {

		   Calendar today = Calendar.getInstance();

		   int month=today.get(Calendar.MONTH);
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=1; year+=1;}


		    //Create drivers
			//Driver driver1=new Driver("driver1@gmail.com","Aitor Fernandez", "a");
			Driver driver1=new Driver("Aitor Fernandez","driver1@gmail.com", "a");
			Driver driver2=new Driver("Ane Gaztañaga","driver2@gmail.com","b");
			Driver driver3=new Driver("Test Driver","driver3@gmail.com", "c");


			//Create rides
			driver1.addRide("Donostia", "Bilbo", UtilDate.newDate(year,month,15), 4, 7);
			driver1.addRide("Donostia", "Gazteiz", UtilDate.newDate(year,month,6), 4, 8);
			driver1.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,25), 4, 4);

			driver1.addRide("Donostia", "Iruña", UtilDate.newDate(year,month,7), 4, 8);

			driver2.addRide("Donostia", "Bilbo", UtilDate.newDate(year,month,15), 3, 3);
			driver2.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,25), 2, 5);
			driver2.addRide("Eibar", "Gasteiz", UtilDate.newDate(year,month,6), 2, 5);

			driver3.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,14), 1, 3);



			db.persist(driver1);
			db.persist(driver2);
			db.persist(driver3);


			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method returns all the cities where rides depart
	 * @return collection of cities
	 */
	public List<String> getDepartCities(){
			TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.from FROM Ride r ORDER BY r.from", String.class);
			List<String> cities = query.getResultList();
			return cities;

	}
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city
	 *
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	public List<String> getArrivalCities(String from){
		TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.to FROM Ride r WHERE r.from=?1 ORDER BY r.to",String.class);
		query.setParameter(1, from);
		List<String> arrivingCities = query.getResultList();
		return arrivingCities;

	}
	/**
	 * This method creates a ride for a driver
	 *
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride
	 * @param nPlaces available seats
	 * @param driverEmail to which ride is added
	 *
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today
 	 * @throws RideAlreadyExistException if the same ride already exists for the driver
	 */
	public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail) throws  RideAlreadyExistException, RideMustBeLaterThanTodayException {
		System.out.println(">> DataAccess: createRide=> from= "+from+" to= "+to+" driver="+driverEmail+" date "+date);
		try {
			if(new Date().compareTo(date)>0) {
				throw new RideMustBeLaterThanTodayException(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorRideMustBeLaterThanToday"));
			}
			db.getTransaction().begin();

			Driver driver = db.find(Driver.class, driverEmail);
			if (driver.doesRideExists(from, to, date)) {
				db.getTransaction().commit();
				throw new RideAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.RideAlreadyExist"));
			}
			Ride ride = driver.addRide(from, to, date, nPlaces, price);
			//next instruction can be obviated
			db.persist(driver);
			db.getTransaction().commit();

			return ride;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			db.getTransaction().commit();
			return null;
		}


	}

	public Viajecompuesto crearviajecompuesto( String from, String to, Date date, int nPlaces, float price, String driverEmail, ArrayList<String> paradasIntermedias) throws  RideAlreadyExistException, RideMustBeLaterThanTodayException {
		System.out.println(">> DataAccess: crearviajecompuesto=> from= "+from+" to= "+to+"  driver="+driverEmail+" date "+date);
		try {
			if(new Date().compareTo(date)>0) {
				throw new RideMustBeLaterThanTodayException(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorRideMustBeLaterThanToday"));
			}
			db.getTransaction().begin();

			Driver driver = db.find(Driver.class, driverEmail);
			if (driver.doesRideExists(from, to, date)) {
				db.getTransaction().commit();
				throw new RideAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.RideAlreadyExist"));
			} 

			//Viajecompuesto viajecompuesto = driver.añadirviajecompuesto(from, to, date, nPlaces, price, paradasIntermedias);
			Ride viaje = new Ride(from, to, date, nPlaces ,price, driver, paradasIntermedias);
			
			
			//db.persist(viaje);
			Viajecompuesto viajecompuesto = new Viajecompuesto(viaje, paradasIntermedias);
			driver.añadirviajecompuesto(viaje, viajecompuesto);
			db.persist(viajecompuesto);
			System.out.println("Insertado:" + viajecompuesto);
			
			//next instruction can be obviated
			db.merge(driver);
			db.getTransaction().commit(); 

			return viajecompuesto;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			db.getTransaction().commit();
			return null;
		}


	}

	/**
	 * This method retrieves the rides from two locations on a given date
	 *
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride
	 * @return collection of rides
	 */
	public List<Ride> getRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getRides=> from= "+from+" to= "+to+" date "+date);

		List<Ride> res = new ArrayList<>();
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date=?3",Ride.class);
		TypedQuery<Viajecompuesto> query2 = db.createQuery("SELECT vc FROM Viajecompuesto vc WHERE vc.from=?1 AND vc.to=?2 AND vc.date=?3",Viajecompuesto.class);
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, date);
		query2.setParameter(1, from);
		query2.setParameter(2, to);
		query2.setParameter(3, date);
		List<Ride> rides = query.getResultList();
	 	 for (Ride ride:rides){
		   res.add(ride);
		  }
	 	 
	 	return res;
	}
	
	public List<Viajecompuesto> getViajesCompuestos(String from, String to, Date date) {
		System.out.println(">> DataAccess: getRides=> from= "+from+" to= "+to+" date "+date);

		List<Viajecompuesto> res = new ArrayList<>();
		
		TypedQuery<Viajecompuesto> query = db.createQuery("SELECT vc FROM Viajecompuesto vc WHERE vc.from=?1 AND vc.to=?2 AND vc.date=?3",Viajecompuesto.class);
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, date);
		
		List<Viajecompuesto> ridesCom = query.getResultList();
	 	 for (Viajecompuesto rideCom:ridesCom){
		   res.add(rideCom);
		  }
	 	 
	 	return res;
	}

	public Driver findDriver(String name) {
		return db.find(Driver.class, name);
	}

	public List<RideSolicitado> getRidesbyDriver(Driver d){
		System.out.println(">> DataAccess: getRidesbyDriver=> from= "+d.getName());

		db.getTransaction().begin();

		Driver driver = db.merge(d);

		List<RideSolicitado> res = new ArrayList<>();
		TypedQuery<RideSolicitado> query=db.createQuery("SELECT r FROM RideSolicitado r WHERE r.aceptado=false and r.getDriver()=?1",RideSolicitado.class) ;
		query.setParameter(1, driver);
		List<RideSolicitado> rides = query.getResultList();
	 	 for (RideSolicitado ride:rides){
		   res.add(ride);
		  }
	 	db.getTransaction().commit();
	 	System.out.println(res);
		return res;
	}

	public Ride getRidebyRideNumber (int rideNumber) {
		//List<Ride> res = new ArrayList<>();
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.rideNumber=?1", Ride.class);
		query.setParameter(1, rideNumber);
		Ride res = query.getSingleResult();
		return res;
	}

	public RideSolicitado getRideSolicitadobyRideNumber (int rideNumber) {
		//List<Ride> res = new ArrayList<>();
		TypedQuery<RideSolicitado> query = db.createQuery("SELECT r FROM RideSolicitado r WHERE r.rideNumber=?1", RideSolicitado.class);
		query.setParameter(1, rideNumber);
		RideSolicitado res = query.getSingleResult();
		return res;
	}

	public List<Ride> getAllRides() {


            TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r", Ride.class);
            List<Ride> rides = query.getResultList();
            return rides;
        }
	
	public List<RideSolicitado> getRidesAceptadosbyDriver(Driver d){
		System.out.println(">> DataAccess: getRidesbyDriver=> from= "+d.getName());

		db.getTransaction().begin();

		Driver driver = db.merge(d);

		List<RideSolicitado> res = new ArrayList<>();
		TypedQuery<RideSolicitado> query=db.createQuery("SELECT r FROM RideSolicitado r WHERE r.aceptado=true and r.getDriver()=?1",RideSolicitado.class) ;
		query.setParameter(1, driver);
		List<RideSolicitado> rides = query.getResultList();
	 	 for (RideSolicitado ride:rides){
		   res.add(ride);
		  }
	 	db.getTransaction().commit();
	 	System.out.println(res);
		return res;
	}
	
	public List<RideSolicitado> getRidesAceptadosbyViajero(viajero v) {
		System.out.println(">> DataAccess: getRidesbyDriver=> from= "+v.getName());

		db.getTransaction().begin();

		viajero viajero = db.merge(v);

		List<RideSolicitado> res = new ArrayList<>();
		TypedQuery<RideSolicitado> query=db.createQuery("SELECT r FROM RideSolicitado r WHERE r.aceptado=true and r.getViajero()=?1",RideSolicitado.class) ;
		query.setParameter(1, viajero);
		List<RideSolicitado> rides = query.getResultList();
	 	 for (RideSolicitado ride:rides){
		   res.add(ride);
		  }
	 	db.getTransaction().commit();
	 	System.out.println(res);
		return res;
	}

	public List<Driver> getAllDrivers() {
		TypedQuery<Driver> query = db.createQuery("SELECT r FROM Driver r", Driver.class);
		List<Driver> drivers = query.getResultList();
		return drivers;
	}

    public void eliminarViajePorNumero(int rideNumber) {
        try {
            db.getTransaction().begin();

            // Busca el viaje por su número
            Ride ride = getRidebyRideNumber(rideNumber);

            if (ride != null) {
                // Elimina el viaje de la base de datos
                db.remove(ride);
                db.getTransaction().commit();
            } else {
                System.out.println("No se encontró ningún viaje con el número: " + rideNumber);
                db.getTransaction().rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.getTransaction().rollback();
        }
    }

    public void eliminarViajeSolicitadoPorNumero(int rideNumber) {
        try {
            db.getTransaction().begin();

            // Busca el viaje por su número
            RideSolicitado ride = getRideSolicitadobyRideNumber(rideNumber);

            if (ride != null) {
                // Elimina el viaje de la base de datos
                db.remove(ride);
                db.getTransaction().commit();
            } else {
                System.out.println("No se encontró ningún viaje con el número: " + rideNumber);
                db.getTransaction().rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.getTransaction().rollback();
        }
    }

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date of the month for which days with rides want to be retrieved
	 * @return collection of rides
	 */
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<>();

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);


		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT r.date FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date BETWEEN ?3 and ?4",Date.class);

		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, firstDayMonthDate);
		query.setParameter(4, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
		   res.add(d);
		  }
	 	return res;
	}


public void open(){

		String fileName=c.getDbFilename();
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);
			  db = emf.createEntityManager();
    	   }
		System.out.println("DataAccess opened => isDatabaseLocal: "+c.isDatabaseLocal());


	}

	public void close(){
		db.close();
		System.out.println("DataAcess closed");
	}

	public void storeDriver(String name,String email,String contraseña) {
		db.getTransaction().begin();
		Driver driver = new Driver(name,email,contraseña);
		db.persist(driver);
		 db.getTransaction().commit();
		 System.out.println("Insertado: " + driver);
		 JOptionPane.showInternalMessageDialog(null,"Registro exitoso del conductor: "+driver.getName());
		 }
	
	public void storeViajero(String name,String email,String contraseña) {
		db.getTransaction().begin();
		viajero viajero = new viajero(name,email,contraseña);
		db.persist(viajero);
		 db.getTransaction().commit();
		 System.out.println("Insertado: " + viajero);
		 JOptionPane.showInternalMessageDialog(null,"Registro exitoso del usuario: "+viajero.getName());
		}
	
	public void updateSaldoViajero(viajero v) {
		//en dado caso que el viajero que se reciba este vacio
		if(v == null) {
			System.out.println("viajero nulo");
		}
		
		db.getTransaction().begin();
	    // Actualizar el saldo del viajero existente con el saldo del nuevo viajero
	    //v.setSaldo(saldoNuevo);
	    // Merge el viajero actualizado
	    db.merge(v);
	    db.getTransaction().commit();
	}
	/* 
	public float updateRide(int rideNumber, float numPlazas) {
		Ride ride = getRidebyRideNumber(rideNumber);
		System.out.println(ride);
		float asientos = ride.getnPlaces();
		ride.setBetMinimum(asientos - numPlazas);
		db.persist(ride);
		return ride.getnPlaces();
	}
*/
	/*
	public void updatePilotByNameAddingPoints(String name, int points) {
		Pilot pilot=getPilotById(name);
		if (pilot==null)
		 System.out.println(name + " no está en la base de datos");
		 else {
		db.getTransaction().begin();
		pilot.addPoints(points);
		//db.persist(pilot);
		db.getTransaction().commit();
		 System.out.println(pilot + " ha sido actualizado");
		}
	}
 */
	/*
	 public void updateSaldoViajero(viajero v) {
		db.getTransaction().begin();
	    String id = v.getEmail();
	    viajero existingViajero = getViajerotById(id); // Obtener el viajero existente
	    if (existingViajero != null) { // Verificar si el viajero existe
	        // Actualizar el saldo del viajero existente con el saldo del nuevo viajero
	        existingViajero.setSaldo(v.getSaldo());
	        // Merge el viajero actualizado
	        db.merge(existingViajero);
	    }
	    db.getTransaction().commit();
	}
	 */
		
	 
	/*public void updateSaldoViajero(viajero v) {
	    if (v == null) {
	        // Manejar el caso en el que el viajero sea nulo
	        return;
	    }

	    if (!db.getTransaction().isActive()) {
	        // Comenzar una nueva transacción si no hay una activa
	        db.getTransaction().begin();
	    }

	    try {
	        String id = v.getEmail();
	        viajero existingViajero = getViajerotById(id); // Obtener el viajero existente
	        if (existingViajero != null) { // Verificar si el viajero existe
	            // Actualizar el saldo del viajero existente con el saldo del nuevo viajero
	            existingViajero.setSaldo(v.getSaldo());
	            // Merge el viajero actualizado
	            db.merge(existingViajero);
	            db.getTransaction().commit(); // Hacer commit de la transacción
	        }
	    } catch (Exception e) {
	        // Manejar cualquier excepción y hacer rollback de la transacción
	        if (db.getTransaction().isActive()) {
	            db.getTransaction().rollback();
	        }
	        // Imprimir la traza de la excepción o manejarla de otra manera
	        e.printStackTrace();
	    }
	}*/

	

	
	public void storeViaje(Ride viaje) {
		db.getTransaction().begin();
		db.persist(viaje);
		System.out.println("Insertado: " + viaje);
	}
	public void storeViajecompuesto(Viajecompuesto viaje) {

		db.getTransaction().begin();
		db.persist(viaje);
		System.out.println("Insertado: " + viaje);
	}

	public void storeViajeSolicitado(RideSolicitado rideSolicitado, viajero usuario) {
		db.getTransaction().begin();
		db.persist(rideSolicitado);
		db.getTransaction().commit();
		System.out.println("Insertado: " + rideSolicitado);
	}
	
	public void storeViajeSolicitado2(RideSolicitado rideSolicitado) {
		db.getTransaction().begin();
		db.merge(rideSolicitado);
		db.getTransaction().commit();
		System.out.println("Insertado: " + rideSolicitado);
	}

	public float updateRide(int rideNumber, float numPlazas) {
		db.getTransaction().begin();
		Ride ride = getRidebyRideNumber(rideNumber);
		System.out.println(ride);
		float asientos = ride.getnPlaces();
		ride.setBetMinimum(asientos - numPlazas);
		db.merge(ride);
		db.getTransaction().commit();
		return ride.getnPlaces();
	}
	
	public Driver getdrivertById(String correo) {
		return db.find(Driver.class,correo);
		}
	public viajero getViajerotById(String correo) {
		return db.find(viajero.class,correo);
		}
	
	public Viajecompuesto getViajeCompuesto(Ride viajeCorrespondiente) {
		db.getTransaction().begin();
		
		TypedQuery<Viajecompuesto> query = db.createQuery("SELECT vc FROM Viajecompuesto vc WHERE vc.paradasIntermedias IS NOT EMPTY AND vc.viaje = ?1", Viajecompuesto.class);
		query.setParameter(1, viajeCorrespondiente);
		db.getTransaction().commit();
		return query.getSingleResult();
		
	}

	/*public HashMap<viajero, ArrayList<RideSolicitado>> storeViajeReservado(RideSolicitado viajeSolicitado, viajero usuario) {
		HashMap<viajero, ArrayList<RideSolicitado>> hm = new HashMap<viajero, ArrayList<RideSolicitado>>();
		if (hm.containsKey(usuario)) {
			ArrayList<RideSolicitado> lista = hm.get(usuario);
			if (!lista.contains(viajeSolicitado)) lista.add(viajeSolicitado);
		} else {
			ArrayList<RideSolicitado> nuevaLista = new ArrayList<RideSolicitado>();
			nuevaLista.add(viajeSolicitado);
		}

		storeViajeSolicitado(viajeSolicitado);
		return hm;
	}*/

	public void store(Viajecompuesto viaje) {

		db.getTransaction().begin();
		db.persist(viaje);
		System.out.println("Insertado: " + viaje);
	}
	
public void hacersemiembro(viajero v) {
		
		if(v.getSaldo() > 30 && !v.getmembresia()) {
			try {
				db.getTransaction().begin();
				v.setSaldo(v.getSaldo()-30);
				v.setmembresia(true);
				// db.persist(pilot);
				db.getTransaction().commit();
				System.out.println(v + " ha sido actualizado");
			}
			catch(Exception e) {
				this.open();
				db.getTransaction().begin();
				v.setSaldo(v.getSaldo()-30);
				v.setmembresia(true);
				// db.persist(pilot);
				db.getTransaction().commit();
				System.out.println(v + " ha sido actualizado");
			}
			
			
    		
    	}
}
public List<Reseñas> getReseñas(Driver d) {
	db.getTransaction().begin();
	Driver dr = db.merge(d);
	TypedQuery<Reseñas> query = db.createQuery("SELECT r FROM Reseñas r WHERE r.driver.nombre = ?1", Reseñas.class);
	query.setParameter(1, dr.getName());
	List<Reseñas> res = query.getResultList();
	db.getTransaction().commit();
	return res;
}

public void storeFactura(Factura factura) {
	db.getTransaction().begin();
	db.persist(factura);
	System.out.println("Insertado: " + factura);
}

public void guardarReseña(Reseñas res) {
	db.getTransaction().begin();
	db.persist(res);
	db.getTransaction().commit();
	System.out.println("Reseña enviada");
}

}
