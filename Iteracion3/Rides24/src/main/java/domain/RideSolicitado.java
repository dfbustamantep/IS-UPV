package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Entity
public class RideSolicitado extends Ride implements Serializable {
	@XmlID
	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ride_solicitado_sequence")
    @SequenceGenerator(name="ride_solicitado_sequence", sequenceName = "ride_solicitado_seq", allocationSize = 1)
	private Integer rideNumber;
	private Integer fatherRideNumber;
	private viajero viajero;
	private int numPlazasSolicitadas;
	private boolean aceptado;


	public RideSolicitado(Integer fatherRideNumber,String from, String to, Date date, int nPlaces, double precio, Driver driver, viajero viajero, int numPlazasSolicitadas) {
        super(from, to, date, nPlaces, precio, driver);
        this.fatherRideNumber = fatherRideNumber;
        //this.rideNumber = rideNumber;
        this.viajero = viajero;
        this.numPlazasSolicitadas = numPlazasSolicitadas;
        this.aceptado = false;
    }

	public int getPlazasSolicitadas() {
		return this.numPlazasSolicitadas;
	}

	public viajero getViajero() {
		return this.viajero;
	}

	@Override
	public Integer getRideNumber() {
		return this.rideNumber;
	}
	
	public Integer getFatherRideNumber() {
		return this.fatherRideNumber;
	}
	
	@Override
	public String getFrom() {
		return super.getFrom();
	}
	@Override
	public String getTo() {
		return super.getTo();
	}
	@Override
	public Date getDate() {
		return super.getDate();
	}
	public int getNPlaces() {
		return (int) super.getnPlaces();
	}
	@Override
	public double getPrice() {
		return super.getPrice();
	}
	@Override
	public Driver getDriver() {
		return super.getDriver();
	}
	public boolean getAceptado() {
		return this.aceptado;
	}
	public void setAceptado(boolean estado) {
		this.aceptado = estado;
	}

}
