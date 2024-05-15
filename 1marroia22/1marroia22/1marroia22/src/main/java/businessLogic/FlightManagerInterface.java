package businessLogic;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import domain.ConcreteFlight;
//Interfaz Se definen los metodos pero no se implementan
public interface FlightManagerInterface{
	
	public List<String> getAllDepartingCities();
	public List<String> getArrivalCitiesFrom(String departingCity);
	public Collection<ConcreteFlight> getConcreteFlights(Object departingCity, Object arrivingCity, Date date);

}