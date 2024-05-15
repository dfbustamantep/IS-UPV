package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

//este contendeor tiene tnato la casa como su dueño

@XmlAccessorType(XmlAccessType.FIELD)
public class RuralHouseContainer {
	private Owner o;
	private RuralHouse rh;
	
	public RuralHouseContainer(RuralHouse rh) {
		 this.rh = rh;
		 this.o=rh.getOwner(); 
	 }
	
	public RuralHouseContainer() {
		 o = null;
		 rh = null;
	}
	
	public Owner getOwner(){
		return o; 
	}
	
	public RuralHouse getRuralHouse(){
		return rh; 
	}
	
	public String toString(){
		return o+"/"+rh; 
	}
}