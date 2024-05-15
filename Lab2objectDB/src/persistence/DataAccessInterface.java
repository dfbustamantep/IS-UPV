package persistence;

import java.util.Collection;
import java.util.List;


import domain.City;
import domain.Country;

public interface DataAccessInterface {
	
		
	public List<String> getAllCountryNames();
	public List<Country> getAllCountries();
		public boolean saveCountryAndCity (String countryName, int countryPopul,String capitalName,int capitalPopul,String airportName, int foundationYear);
		public boolean removeCountry (Country country);
		public boolean removePresidentFromCountry (Country country);
		public void assignNeighbors (Country home, List<Country> neighbors);
		public Collection<City> hugeCities(int popul);
		public Collection<Country> countriesWithHugeCapitals1(int popul);
		public Collection<Country> countriesWithHugeCapitals2(int popul);
		public Collection<Country> countriesWithHugeCapitals3(int popul);
		public Collection<Country> countriesWithHugeCapitals4(int popul);


}
