package domain;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Cuenta {
	@Id
	String usuario;
	String password;
	String tusuario;
	
	public Cuenta(String usuario, String password, String tusuario) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.tusuario = tusuario;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTusuario() {
		return tusuario;
	}
	public void setTusuario(String tusuario) {
		this.tusuario = tusuario;
	}
}