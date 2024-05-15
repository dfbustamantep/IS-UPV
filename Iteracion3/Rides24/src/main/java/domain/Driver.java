package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Driver extends user implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Ride> rides=new Vector<>();
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<RideSolicitado> ridesAceptados = new Vector<>();
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList <Viajecompuesto> listaviajesCompuestos = new ArrayList<>();
	

	public Driver() {
		super();
	}

	public Driver(String email, String name) {
		super(email,name);
	}
	public Driver (String name) {
		super(name);
	}
	public Driver(String name,String email, String contraseña) {
		super(name,email,contraseña);
	}

	@Override
	public String toString(){
		return this.getEmail()+";"+this.getName()+rides;
	}

	/**
	 * This method creates a bet with a question, minimum bet ammount and percentual profit
	 *
	 * @param question to be added to the event
	 * @param betMinimum of that question
	 * @return Bet
	 */
	public Ride addRide(String from, String to, Date date, int nPlaces, float price)  {
        Ride ride=new Ride(from,to,date,nPlaces,price, this);
        rides.add(ride);
        return ride;
	}

	public Viajecompuesto añadirviajecompuesto(Ride ride,  Viajecompuesto vc) throws RideMustBeLaterThanTodayException, RideAlreadyExistException{

	    //Ride ride=new Ride(from,to,date,nPlaces,price,this, paradasIntermedias);
	    rides.add(ride);
        //Viajecompuesto viajeCompuesto = new  Viajecompuesto(ride, paradasIntermedias)
        listaviajesCompuestos.add(vc);
		return vc;
  }

  	public RideSolicitado addRideAceptado(RideSolicitado rideAceptado) {
  		//RideSolicitado rideAceptado = new RideSolicitado(rideNumber, from, to, date, nPlaces, price, this, viajero, numPlazasSolicitadas);
  		ridesAceptados.add(rideAceptado);
  		return rideAceptado;
  	}

  	public List<RideSolicitado> getRidesAceptados() {
  		return this.ridesAceptados;
  	}
  	public List<Ride> getRides() {
  		return this.rides;
  	}

  	/**
	 * This method checks if the ride already exists for that driver
	 *
	 * @param from the origin location
	 * @param to the destination location
	 * @param date the date of the ride
	 * @return true if the ride exists and false in other case
	 */
	public boolean doesRideExists(String from, String to, Date date)  {
		for (Ride r:rides)
			if ( (java.util.Objects.equals(r.getFrom(),from)) && (java.util.Objects.equals(r.getTo(),to)) && (java.util.Objects.equals(r.getDate(),date)) )
			 return true;

		return false;
	}

	@Override
/*	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		if (email != other.email)
			return false;
		return true;
	} */


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Driver other = (Driver) obj;
		if (this.getName() != other.getName())
			return false;
		return true;
	}

	public Ride removeRide(String from, String to, Date date) {
		boolean found=false;
		int index=0;
		Ride r=null;
		while (!found && index<=rides.size()) {
			r=rides.get(++index);
			if ( (java.util.Objects.equals(r.getFrom(),from)) && (java.util.Objects.equals(r.getTo(),to)) && (java.util.Objects.equals(r.getDate(),date)) )
			found=true;
		}

		if (found) {
			rides.remove(index);
			return r;
		} else return null;
	}

}
