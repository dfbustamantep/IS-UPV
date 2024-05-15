package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@Entity
public class Reseñas {
    private String comentario;
    private int valoracion;
    @OneToOne
    private Driver driver;
    @Id
     @XmlJavaTypeAdapter(IntegerAdapter.class)
    @GeneratedValue
    private int id;
    private viajero viajero;


    public Reseñas(viajero viajero, String comentario, int valoracion, Driver conductor) {
        this.comentario = comentario;
        this.valoracion = valoracion;
        driver = conductor;
        this.viajero = viajero;
    }
    public Reseñas(viajero viajero, String comentario, int valoracion) {
        this.comentario = comentario;
        this.valoracion = valoracion;
        this.viajero = viajero;
    }

    public Reseñas() {
        super();
    }

    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public void setValoracion(int valoracion) {
        if(valoracion >= 0 && valoracion <= 5) this.valoracion = valoracion;
    }
    public int getValoracion() {
        return this.valoracion;
    }
    public viajero getViajero() {
    	return this.viajero;
    }
    public Driver getDriver() {
    	return this.driver;
    }
}