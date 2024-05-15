package service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.Owner;
import domain.RuralHouse;
import domain.RuralHouseContainer;

//se define que la interfaz es un servicio web mediante la anotacion
@WebService
public interface WebServiceLogicInterface {
	@WebMethod
	public Owner getOwner(String name);
	
	@WebMethod
	public RuralHouse getRuralHouse(String address);
	
	@WebMethod
	public List<Owner> getListOwners();
	
	@WebMethod
	public List<RuralHouse> getListRuralHouses();
	
	@WebMethod
	public List<RuralHouseContainer> getListRuralHouseContainers();
}
