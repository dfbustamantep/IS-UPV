package bussiness;
import javax.persistence.TypedQuery;

import dataAccess.F1_objectdbAccess;
import domain.Pilot;

public class Races {
	public static void main(String[] args) {
		F1_objectdbAccess f1_dataMng=new F1_objectdbAccess();
		f1_dataMng.storePilot("Jesus Magarri", "Mexican", 405);
		f1_dataMng.storePilot("Sebastian Vettel", "German", 345);
		
		//Insertamos 6 nuevos pilotos
		f1_dataMng.storePilot("Fernando Alonso", "Spain", 2267);
		f1_dataMng.storePilot("Lance Stroll", "Canada",268 );
		f1_dataMng.storePilot("Lewis Hamilton", "United Kingdom", 4639);
		f1_dataMng.storePilot("George Russell", "United Kingdom", 469);
		f1_dataMng.storePilot("Pierre Gasly ", "France", 394);
		f1_dataMng.storePilot("Esteban Ocon ", "France", 422);
		
		System.out.println(f1_dataMng.getPilotById("Jesus Magarri"));
		
		//f1_dataMng.getAllPilots();
		System.out.println("Resultado Consulta");
		//f1_dataMng.getPilotByNationality("Mexican");
		 f1_dataMng.getPilotByNationality("Spain");
		
		 
		 Pilot jm=f1_dataMng.getPilotById("Jesus Magarri");
		 f1_dataMng.deletePilot(jm);
		 f1_dataMng.deletePilotByName("Lance Stroll");
		 
		 
		 f1_dataMng.updatePilotByNameAddingPoints("George Russell",100);
		 
		 System.out.println();
		 Pilot p=f1_dataMng.getPilotById("Pierre Gasly ");
		 
		 p.getName();
		 
		 f1_dataMng.updatePilotAddingPoints(p, 100);
		f1_dataMng.close();
		
		
	}
	
}
