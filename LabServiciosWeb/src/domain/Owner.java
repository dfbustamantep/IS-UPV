package domain;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

//Anotacion para indicar que se quiere serializar en XML todos los campos
@XmlAccessorType(XmlAccessType.FIELD)
public class Owner implements Serializable{
	/*Anotacion que indica que el valor de dicho atributo identifica univocamente a un objeto,por lo tanto diferentes valores en 
	 dicho atributo identifica univocamente a un objeto*/
	@XmlID
	private String name;
	private List<RuralHouse> listRuralHouses=new LinkedList<RuralHouse>();


	public Owner() {
		
	}
	
	public Owner(String name) { 
		this.name = name; 
	}
	
	public String getName() { 
		return name; 
	}
	
	public void setName(String name) {
		this.name = name;
	
	}
	
	public List<RuralHouse> getListRuralHouses(){
		return listRuralHouses; 
		}
	
	public void setListRuralHouses(List<RuralHouse> listRH){
	
		listRuralHouses=listRH; }
	
	public String toString(){
		return name+" "+listRuralHouses.toString(); 
		}
	
	public void addRuralHouse(RuralHouse rh){
		listRuralHouses.add (rh);
		} 

}