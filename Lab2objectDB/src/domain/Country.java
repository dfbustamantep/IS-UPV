
package domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;

@Entity
public class Country {
	//estrategia LAZY es la estrategia por defecto para el caso @OneToMany
	//a el caso @OneToOne, la estrategia por defecto es la EAGER
	
	@Id
	private String nameCountry;
	@OneToOne (cascade=CascadeType.ALL)
	private City capital;
	private int populCountry;
	//@OneToMany(fetch=FetchType.EAGER) //usamos la estrategia eager o ansiosa
	//@OneToMany(fetch=FetchType.LAZY) //usamos la estrategia lazy o perezosa con esta no aparecen los paises vecinos
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	private Set<Country> neighbors=new HashSet<Country>();
	@OneToOne(orphanRemoval=true)
	private President president;
	
	public Country(String couName, int couPopulation) {
		this.nameCountry = couName;
		this.populCountry = couPopulation;
		this.neighbors = new HashSet<Country>();
	}
	
	public String getNameCountry() {
		return this.nameCountry;
	}
	
	public void setNameCountry(String couName) {
		this.nameCountry = couName;
	}
	
	public City getCapital() {
		return this.capital;
	}

	public void setCapital(City theCapital) {
		this.capital = theCapital;
	}

	public int getPopulation() {
		return this.populCountry;
	}
	
	public void setPopulation(int thePopulation) {
		this.populCountry = thePopulation;
	}
	
	public Set<Country> getNeighbors() {
		return this.neighbors;
	}
	
	public void addNeighbor(Country newNeighbor) {

	/*	System.out.println( "Before: " + this + " has " + this.neighbors.size() + " neighbors and "
				+ newNeighbor + " has " + newNeighbor.neighbors.size() + " neighbors");
*/
		System.out.println("neighs "+ neighbors);
		this.neighbors.add(newNeighbor);
/*
		System.out.println("After: " + this + " has " + this.neighbors.size() + " neighbors and "
				+ newNeighbor + " has " + newNeighbor.neighbors.size() + " neighbors");
	*/
		}
	
	public President getPresident() {
		return president;
	}
	
	public void setPresident(President thisPresident) {
		this.president = thisPresident;
	}

	@Override
	public String toString() {
		return this.nameCountry;
	}
	public void setNeighBor(HashSet<Country> neigh) {
		 this.neighbors=neigh;
	}

}
