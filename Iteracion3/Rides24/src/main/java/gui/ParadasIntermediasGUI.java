package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Driver;
import domain.Ride;
import domain.RideSolicitado;
import domain.Viajecompuesto;
import domain.viajero;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import javax.swing.JComboBox;

public class ParadasIntermediasGUI extends JFrame {
	private static final long serialVersionUID = 1L;


	private Driver driver;

	private JLabel jLabelOrigin = new JLabel("Origen:");
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private List<Date> datesWithEventsCurrentMonth;


	private ArrayList<String> paradasIntermedias;


	public ParadasIntermediasGUI(Ride ride, Viajecompuesto vc, viajero usuario, int asientosSol, double costo) {

		this.driver=driver;
		ArrayList<String> paradasIntermedias = new ArrayList<String>();
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.CreateRide"));

		jLabelOrigin.setBounds(new Rectangle(20, 32, 92, 20));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		this.getContentPane().add(jLabelOrigin, null);




		BLFacade facade = MainGUI.getBusinessLogic();

		JLabel jLabelDestination2 = new JLabel("Destino");
		jLabelDestination2.setBounds(20, 90, 61, 14);
		getContentPane().add(jLabelDestination2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(178, 32, 97, 21);
		getContentPane().add(comboBox);
		
	
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(178, 87, 97, 21);
		getContentPane().add(comboBox_1);
		
		comboBox.addItem(ride.getFrom());
	
		for (String parada : vc.paradasIntermedias) {
			comboBox.addItem(parada);
			comboBox_1.addItem(parada);
		}
		comboBox_1.addItem(ride.getTo());
		
		JButton btnNewButton = new JButton("Reservar"); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.setBounds(250, 220, 85, 21);
		getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {									//////////////////////////
		    	//ride.setFrom((String)comboBox.getSelectedItem());
		    	//ride.setTo((String)comboBox_1.getSelectedItem());
		    	RideSolicitado viajeReservado = usuario.ReservarViaje(ride.getRideNumber(),((String)comboBox.getSelectedItem()) ,((String)comboBox_1.getSelectedItem()), ride.getDate(), (int)ride.getnPlaces(), ride.getPrice(), ride.getDriver(), usuario, asientosSol);
               
		    	DataAccess db = facade.get_database();
		        db.open();
		    	
		    	db.storeViajeSolicitado(viajeReservado, usuario);
                
                //usuario.addRideAceptado(viajeReservado);
                //arreglar el updateViajero
                //db.updateViajero(usuario);
                double nuevoSaldo = (double) (usuario.getSaldo()-costo);
                usuario.setSaldo(nuevoSaldo);
                db.updateSaldoViajero(usuario);
               
                JOptionPane.showMessageDialog(null, "El viaje ha sido reservado.Nuevo saldo es" + nuevoSaldo);
                db.close();
           }    
		    });

	
	}
}