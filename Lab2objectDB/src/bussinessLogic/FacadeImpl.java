package bussinessLogic;

import java.util.List;

import persistence.DataAccess;
import persistence.DataAccessInterface;
import domain.City;
import domain.Country;

public class FacadeImpl implements FacadeInterface {
	private DataAccessInterface dataAccess;
	
	public void setDataAccess(DataAccessInterface pDataAccess){
		dataAccess=pDataAccess;
	}

	@Override
	public List<String> getAllCountryNames() {
		return dataAccess.getAllCountryNames();
	}

	@Override
	public List<Country> getAllCountries() {
		return dataAccess.getAllCountries();
	}

	@Override
	public boolean saveCountryAndCity (String countryName, int countryPopul,String capitalName,int capitalPopul,String airportName, int foundationYear){
		return dataAccess.saveCountryAndCity(countryName,countryPopul,capitalName,capitalPopul,airportName,foundationYear);		
	}

	@Override
	public boolean removeCountry (Country country){
		return dataAccess.removeCountry(country);
	}
	
	@Override
	public boolean removePresidentFromCountry (Country country){
		return dataAccess.removePresidentFromCountry(country);
	}
	
	
	@Override
	public void assignNeighbors (Country home, List<Country> neighbors) {
		dataAccess.assignNeighbors(home,  neighbors);
		// TODO Auto-generated method stub
		
	}

	public void changePresident (Country home, List<Country> neighbors) {
		dataAccess.assignNeighbors(home,  neighbors);
		// TODO Auto-generated method stub
		
	}
}
