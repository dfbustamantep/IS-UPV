package service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.Owner;
import domain.RuralHouse;
import domain.RuralHouseContainer;
//implementamos todos los metodos de la interfaz

//anotacion que indica de manera explicita que se implementa un servicio web el definido en service.WebServiceLogicInterface
@WebService(endpointInterface = "service.WebServiceLogicInterface")
public class WebServiceLogic {
	
	List<Owner> listOwners=new LinkedList<Owner>();
	List<RuralHouse> listRH=new LinkedList<RuralHouse>();
	
	public WebServiceLogic(){
		initialize(); 
	}
	
	@WebMethod
	public Owner getOwner(String nameA){
		 Iterator<Owner> it=listOwners.iterator();
		 while (it.hasNext()) {
			 Owner o=it.next();
			 if (o.getName().equals(nameA))
				 return o; 
			 }
		 return null; 
	}
	
	@WebMethod
	public RuralHouse getRuralHouse(String address){
		 Iterator<RuralHouse> it=listRH.iterator();
		 while (it.hasNext()) {
			 RuralHouse rh=it.next();
			 if (rh.getAddress().equals(address))
			 return rh; 
		 }
		return null; 
	}
	
	@WebMethod
	public List<Owner> getListOwners(){
		return listOwners;
	}
	
	@WebMethod
	public List<RuralHouse> getListRuralHouses(){
		return listRH; 
	}
	
	public void initialize(){
		listOwners=new LinkedList<Owner>();
		listRH=new LinkedList<RuralHouse>();
		Owner o1= new Owner("jon");
		Owner o2= new Owner("mikel");
		RuralHouse rh1 = new RuralHouse("jonTolosa",o1);
		RuralHouse rh2 = new RuralHouse("jonDonostia",o1);
		RuralHouse rh3 = new RuralHouse("mikelTolosa",o2);
		RuralHouse rh4 = new RuralHouse("mikelDonostia",o2);
		o1.addRuralHouse(rh1);
		o1.addRuralHouse(rh2);
		o2.addRuralHouse(rh3);
		o2.addRuralHouse(rh4);
		listOwners.add(o1); listOwners.add(o2);
		listRH.add(rh1); listRH.add(rh2);
		listRH.add(rh3); listRH.add(rh4); 
	}
	
	@WebMethod
	public List<RuralHouseContainer> getListRuralHouseContainers(){
		List<RuralHouseContainer> listRHC=
		 new LinkedList<RuralHouseContainer>();
		 Iterator<RuralHouse> it=getListRuralHouses().iterator();
		 while (it.hasNext()) {
			 RuralHouse rh=it.next();
			 listRHC.add(new RuralHouseContainer(rh));
		 }
		return listRHC;
	}
}