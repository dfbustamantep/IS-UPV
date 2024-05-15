package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Viajecompuesto implements Serializable {
	@XmlID
	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer rideNumber;
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private LinkedList<Ride> viajecompuesto;
	private Ride viaje;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	public ArrayList<String> paradasIntermedias;

	private Driver driver;

	public Viajecompuesto(){
		super();
	}
	
	public Viajecompuesto(Ride ride, ArrayList<String> paradasIntermedias) {
		super();
		//this.rideNumber = rideNumber;
		this.viaje = ride;
		this.paradasIntermedias = paradasIntermedias;
	}

	public Viajecompuesto(Integer rideNumber,Ride ride1, Ride ride2) {
		super();
		this.rideNumber = rideNumber;
		viajecompuesto.add(ride1);
		viajecompuesto.add(ride2);

	}

	public Viajecompuesto(Ride ride1, Ride ride2) {
		super();
		viajecompuesto = new LinkedList<>();
		viajecompuesto.add(ride1);
		viajecompuesto.add(ride2);

	}



	public Driver getDriver() {
		return driver;
	}

	/**
	 * Set the driver associated to the ride
	 *
	 * @param driver to associate to the ride
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public LinkedList<Ride> getviajecompuesto(){
		return viajecompuesto;
	}

	public ArrayList<String> getParadasIntermedias() {
		return this.paradasIntermedias;
	}












}