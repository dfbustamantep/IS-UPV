package client;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import service.WebServiceLogic;
import service.WebServiceLogicInterface;

import domain.Owner;
import domain.RuralHouse;
import domain.RuralHouseContainer;

public class Main {
	public static void main(String[] args) throws Exception{
		//se crea un objeto de una clase URL que accede a la URL donde se encuntra publica la interfaz WSDL del servicio web
		URL url = new URL("http://localhost:9999/ws?wsdl");
		
		//se crea un objeto de la clase QName con el nombre global que se le asigna al servicio web
			//hay dos partes,la primera el espacio de nombre o namespaces url y el recurso local
			//como espacio de nombre se pondra despues del http el nombre del paquete de la clase que implementa el servicio web
			//como nombre de recuros local la concatenacion entre el nombre de la clase y el string  Service
		QName qname = new QName("http://service/","WebServiceLogicService");
		
		/*
		Se necesita crear previamente un objeto de la clase Service que permitirá
		obtener el objeto proxy o intermediario definido en el paso siguiente
		 */
		Service service = Service.create(url, qname);
		
		/*
		 Se crea un objeto proxy del servicio web.
		 A este objeto se le podra pedir directamente que ejecute los servicios web 
		 * */
		WebServiceLogicInterface wsl=
		service.getPort(WebServiceLogicInterface.class);
		
		/*
		System.out.println("BUSCANDO E IMPRIMIENDO OWNERS");
		//Lo que se dice antes se comprueba aca,se le pide al objeto proxy que ejecute el metodo getListOwners
		List<Owner> listOwners=wsl.getListOwners();
		Iterator<Owner> it=listOwners.iterator();
		
		while (it.hasNext()) {
			Owner o=it.next();
			System.out.println("Owner: "+ o.getName()+
			 " y sus RH: "+o.getListRuralHouses());
		}
		
		System.out.println("BUSCANDO E IMPRIMIENDO RURAL HOUSES");
		List<RuralHouse> listRH=wsl.getListRuralHouses();
		Iterator<RuralHouse> itrh=listRH.iterator();
		
		while (itrh.hasNext()){
			RuralHouse rh=itrh.next();
			System.out.println("Su casa: "+rh.getAddress()+
			 " y su Owner: "+rh.getOwner());
		 }
		
		*/
		///*
		System.out.println("BUSCANDO E IMPRIMIENDO RURAL HOUSE CONTAINERS");
		List<RuralHouseContainer> listRHC=wsl.getListRuralHouseContainers();
		Iterator<RuralHouseContainer> itc=listRHC.iterator();
		 
		 while (itc.hasNext()) {
			 RuralHouseContainer rhc=itc.next();
			 System.out.println("RH: "+ rhc.getRuralHouse().getAddress()+
			 " y su Owner: "+rhc.getOwner().getName());
		 }//*/
	}
}
