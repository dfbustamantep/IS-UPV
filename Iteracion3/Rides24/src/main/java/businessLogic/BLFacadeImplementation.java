package businessLogic;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Driver;
import domain.Reseñas;
import domain.Ride;
import domain.RideSolicitado;
import domain.Viajecompuesto;
import domain.viajero;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {
		System.out.println("Creating BLFacadeImplementation instance");


		    dbManager=new DataAccess();

		//dbManager.close();


	}

    public BLFacadeImplementation(DataAccess da)  {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();

		dbManager=da;
	}


    /**
     * {@inheritDoc}
     */
    @Override
	@WebMethod public List<String> getDepartCities(){
    	dbManager.open();

		 List<String> departLocations=dbManager.getDepartCities();

		dbManager.close();

		return departLocations;

    }
    /**
     * {@inheritDoc}
     */
	@Override
	@WebMethod public List<String> getDestinationCities(String from){
		dbManager.open();

		 List<String> targetCities=dbManager.getArrivalCities(from);

		dbManager.close();

		return targetCities;
	}

	/**
	 * {@inheritDoc}
	 */
   @Override
@WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail ) throws RideMustBeLaterThanTodayException, RideAlreadyExistException{

		dbManager.open();
		Ride ride=dbManager.createRide(from, to, date, nPlaces, price, driverEmail);
		dbManager.close();
		return ride;
   }

   @Override
public Viajecompuesto crearviajecompuesto( String from, String to, Date date, int nPlaces, float price, String driverEmail, ArrayList<String> paradasIntermedias) throws RideMustBeLaterThanTodayException, RideAlreadyExistException{


		dbManager.open();
		Viajecompuesto viajesCompuestos =dbManager.crearviajecompuesto(from,  to,  date, nPlaces, price, driverEmail, paradasIntermedias);

		dbManager.close();

		return viajesCompuestos;
  }

   /**
    * {@inheritDoc}
    */
	@Override
	@WebMethod
	public List<Ride> getRides(String from, String to, Date date){
		dbManager.open();
		List<Ride>  rides=dbManager.getRides(from, to, date);
		dbManager.close();
		return rides;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	@WebMethod
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date){
		dbManager.open();
		List<Date>  dates=dbManager.getThisMonthDatesWithRides(from, to, date);
		dbManager.close();
		return dates;
	}


	public void close() {
		DataAccess dB4oManager=new DataAccess();

		dB4oManager.close();

	}


	@Override
	public List<Ride> getAllRides() {
		dbManager.open();
		List<Ride> listaTodos = dbManager.getAllRides();
		dbManager.close();
		for (Ride r : listaTodos) System.out.println(r);
		return listaTodos;

	}

	@Override
	public Ride encontrarViaje(List<Ride> listaDeViajes, String origen, String destino, Date fecha, Driver driver, float plazas, float precio) {
        for (Ride ride : listaDeViajes) {

        /*	System.out.println("Origen: " + origen);
            System.out.println("Destino: " + destino);
            System.out.println("Fecha: " + fecha);
            System.out.println("Driver: " + driver);
            System.out.println("Plazas: " + plazas);
            System.out.println("Precio: " + precio); */

            if (ride.getFrom().equals(origen) &&
                ride.getTo().equals(destino) &&
                ride.getDate().equals(fecha) &&
                ride.getDriver().getName().equals(driver.getName()) &&
                ride.getnPlaces() == plazas &&
                ride.getPrice() == (precio)) {
                return ride; // Se encontró un viaje que coincide con los atributos proporcionados
            }
        } return null; // No se encontró ningún viaje que coincida con los atributos proporcionados

    }
	
	public Ride cogerVueloPadre(RideSolicitado rideAceptado, Driver driver) {
		Ride vueloPadre = null;
		for (Ride r : getAllRides()) {
        	System.out.println(rideAceptado.getFatherRideNumber());
        	if (r.getRideNumber() == rideAceptado.getFatherRideNumber()) {	
        		vueloPadre = r; 
        		break;       		
        	} 
        	
        }   
		return vueloPadre;
	}
	
	public float updateRide(int rideNumberPadre, float plazasSolicitadas) {
		dbManager.open();
		float nuevosAsientos = dbManager.updateRide(rideNumberPadre, plazasSolicitadas);
		dbManager.close();
		return nuevosAsientos;		 
	}
	
	public void storeRideSolicitado(RideSolicitado rideSolicitado) {
		dbManager.open();
		dbManager.storeViajeSolicitado2(rideSolicitado);
		dbManager.close();
	}

/*	public float updateRide(int rideNumber, float numAsientos) {
		dbManager.open();
		float nuevosAsientos = dbManager.updateRide(rideNumber, numAsientos);
		dbManager.close();
		return nuevosAsientos;
}*/

	/**
	 * {@inheritDoc}
	 */
    @Override
	@WebMethod
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
    @Override
	public DataAccess get_database(){
    	return dbManager;
    }
    public void hacersemiembro(viajero v) {
    	
    	
    	dbManager.hacersemiembro(v);
    }
    
    public List<Reseñas> getReseñas(Driver d) {
    	dbManager.open();
    	List<Reseñas> reseñas = dbManager.getReseñas(d);
    	dbManager.close();
    	return reseñas;
    }
    
    public void guardarReseña(Reseñas res) {
    	dbManager.open();
    	dbManager.guardarReseña(res);
    	dbManager.close();
    }

}

