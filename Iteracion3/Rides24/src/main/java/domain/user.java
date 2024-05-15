package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;

@Entity
public class user {
	
	@XmlID
	@Id
	private String email;
	private String contraseña;
	private String nombre;
	
	public user(String nombre, String email, String contraseña) {
		this.nombre = nombre;
		this.email = email;
		this.contraseña = contraseña;
	}
	public user() {
		
	}
	public user(String email, String name) {
		this.email = email;
		nombre = name;
	}
	public user(String name) {
		nombre = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContraseña() {
		return contraseña;
	}

	public String getName() {
		return nombre;
	}

	public void setName(String name) {
		nombre = name;
	}

}
