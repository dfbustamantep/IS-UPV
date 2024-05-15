package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
/*
 * se indica que los atributos
 * firstName y familyName forman
 * la clave compuesta primaria de President.
*/
@Entity @IdClass(President.class)
public class President {
	@Id
	private String firstName;
	@Id
	private String familyName;
	private int started;
	private AccessMode accessPower;
	private int ended;
	@OneToOne
	private President preceededBy;
	@OneToOne
	private President succeededBy;
	
	public String getFirstName() {
		return firstName;
	}
	
	public President(String firstName, String familyName){
		this.firstName=firstName;
		this.familyName=familyName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public int getStarted() {
		return started;
	}
	public void setStarted(int started) {
		this.started = started;
	}
	public AccessMode getAccessPower() {
		return accessPower;
	}
	public void setAccessPower(AccessMode accessPower) {
		this.accessPower = accessPower;
	}
	public int getEnded() {
		return ended;
	}
	public void setEnded(int ended) {
		this.ended = ended;
	}
	public President getPreceededBy() {
		return preceededBy;
	}
	public void setPreceededBy(President preceededBy) {
		this.preceededBy = preceededBy;
	}
	public President getSucceededBy() {
		return succeededBy;
	}
	public void setSucceededBy(President succeededBy) {
		this.succeededBy = succeededBy;
	}

	@Override
	public String toString() {
		return this.firstName+" "+this.familyName;
	}
}
