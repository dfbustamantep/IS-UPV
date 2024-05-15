package Lanzador;

import logicaNegocio.Login;
import logicaNegocio.Login1;
import logicaNegocio.Login2;

import logicaNegocio.LoginOBD;
import presentacion.Login1GUI;
import presentacion.Login2GUI;
import presentacion.Login3GUI;

public class Lanzador {
	public static void main(String[] args) {
		/*Login1GUI a = new Login1GUI();
		//Login nl = new Login1();
		Login nl = new Login2(); //Se usa Login2 en vez de Login1
		a.setLogicaNegocio(nl);
		a.setVisible(true);
		*/
		
		/*Login2GUI a=new Login2GUI(); // Presentación con JComboBox
		Login nl=new Login2(); // Asignar la lógica del negocio
		a.setLogicaNegocio(nl);
		a.setVisible(true);*/
		
		Login3GUI a=new Login3GUI(); // Presentación con JList
		Login nl=new LoginOBD(); // Asignar la lógica del negocio
		a.setLogicaNegocio(nl);
		a.setVisible(true);
	}
}
