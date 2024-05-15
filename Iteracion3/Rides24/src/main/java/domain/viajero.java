package domain;

import java.util.Date;
import java.util.List;
import java.util.Objects;
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

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class viajero extends user{

	private static final long serialVersionUID = 1L;
	
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Ride> viajesreservados=new Vector<>();
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<RideSolicitado> misViajesAceptados = new Vector<>();
	private String contraseña;
	//monedero se usa .
	private double saldo;
	private boolean miembro;
	
	public viajero() {
		super();
	}

	public viajero(String email, String name) {
		super(email,name);
		this.saldo=0.0;
		this.miembro = false;
	}
	
	public viajero( String name,String email,String contraseña) {
		super(name,email,contraseña);
		this.saldo=0.0;
		this.miembro = false;
	}

	public viajero( String name,String email,String contraseña,double saldo) {
		super(name,email,contraseña);
		this.saldo = saldo;
		this.miembro = false;
	}
	
	public RideSolicitado addRideAceptado(RideSolicitado rideAceptado) {
  		//RideSolicitado rideAceptado = new RideSolicitado(rideNumber, from, to, date, nPlaces, price, this, viajero, numPlazasSolicitadas);
  		misViajesAceptados.add(rideAceptado);
  		return rideAceptado;
  	}
	
	public List<RideSolicitado> getMisViajesAceptados() {
		return misViajesAceptados;
	}

	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double nuevoSaldo) {
		this.saldo=nuevoSaldo;
	}
	
	@Override
	public String toString(){
		return this.getEmail()+";"+this.getName()+viajesreservados;
	}

	public RideSolicitado ReservarViaje(Integer rideNumber, String from, String to, Date date, int nPlaces, double precio, Driver driver, viajero usuario, int plazasSolicitadas)  {
        RideSolicitado rideSolicitado= new RideSolicitado(rideNumber, from,to,date,nPlaces,precio,driver, usuario, plazasSolicitadas);
        viajesreservados.add(rideSolicitado);
        return rideSolicitado;
	}
	public void setmembresia(boolean a) {
		this.miembro=a;
	}
	public boolean getmembresia() {
		return miembro;
	}
	
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        viajero viajero = (viajero) obj;
        return Double.compare(viajero.saldo, saldo) == 0 &&
                Objects.equals( this.getName(), viajero.getName()) &&
                Objects.equals(this.getEmail(), viajero.getEmail()) &&
                Objects.equals(contraseña, viajero.contraseña);
    }

}
