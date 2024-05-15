package domain;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
public class RuralHouse implements Serializable{
	@XmlID
	private String address;
	/*
	 Este atributo juega un rol de clave extranjera,el valor que tome este atributo identificara al objeto al que hace referencia 
	 ya que tomara valores en el atributo clave de la clase a la que se refiere
	 */
	@XmlIDREF	
	public Owner owner;
	
	public RuralHouse() { 
		
	}
	
	public RuralHouse(String address,Owner o) {
		super();
		this.address = address;
		this.owner=o;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString(){
		return address;
	}
	
	public Owner getOwner(){
		return owner;
	}
	
	public void setOwner(Owner o){
		owner=o;
	}
}
