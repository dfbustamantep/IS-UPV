
package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class City {
	@Id
	private String nameCity;
	@OneToOne
	private Country country;
	private int populCity;
	private String mainAirport;
	private int foundation;
	
	public City(String cityName, Country theCountry, int cityPopulation, String airport,
			int foundatation) {
		super();
		this.nameCity = cityName;
		this.country = theCountry;
		theCountry.setCapital(this);
		this.populCity = cityPopulation;
		this.mainAirport = airport;
		this.foundation = foundatation;
	}
	
	public String getName() {
		return nameCity;
	}

	public void setName(String cityName) {
		this.nameCity = cityName;
	}

	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country theCountry) {
		this.country = theCountry;
	}
	
	public int getPopulCity() {
		return populCity;
	}
	
	public void setPopulCity(int population) {
		this.populCity = population;
	}
	
	public String getMainAirport() {
		return mainAirport;
	}
	
	public void setMainAirport(String airport) {
		this.mainAirport = airport;
	}
	
	public int getFoundation() {
		return foundation;
	}
	
	public void setFoundation(int foundatationYear) {
		this.foundation = foundatationYear;
	}

	@Override
	public String toString() {
		return this.nameCity;
	}
}
