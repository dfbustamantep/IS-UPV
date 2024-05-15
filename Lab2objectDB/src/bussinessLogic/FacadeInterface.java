package bussinessLogic;

import java.util.List;

import persistence.DataAccess;
import persistence.DataAccessInterface;
import domain.City;
import domain.Country;

public interface FacadeInterface {
	public List<String> getAllCountryNames();
	public List<Country> getAllCountries();
	public boolean saveCountryAndCity (String countryName, int countryPopul,String capitalName,int capitalPopul,String airportName, int foundationYear);
	public boolean removeCountry (Country country);
	public boolean removePresidentFromCountry (Country country);
	public void assignNeighbors (Country home, List<Country> neighbors);
	public void setDataAccess(DataAccessInterface pDataAccess);



}
