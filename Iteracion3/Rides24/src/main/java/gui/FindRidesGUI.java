package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import com.objectdb.o.BNN.v;
import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Driver;
import domain.Ride;
import domain.RideSolicitado;
import domain.Viajecompuesto;
import domain.viajero;
public class FindRidesGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox<String> jComboBoxOrigin = new JComboBox<>();
	DefaultComboBoxModel<String> originLocations = new DefaultComboBoxModel<>();

	private JComboBox<String> jComboBoxDestination = new JComboBox<>();
	DefaultComboBoxModel<String> destinationCities = new DefaultComboBoxModel<>();

	private JLabel jLabelOrigin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.LeavingFrom"));
	private JLabel jLabelDestination = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.GoingTo"));
	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.RideDate"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.Rides"));

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();

	private List<Date> datesWithRidesCurrentMonth = new Vector<>();

	private JTable tableRides= new JTable();

	private DefaultTableModel tableModelRides;

	private viajero usuario;

	private int numPlazas = 0;

	JSpinner spinner;

	private String[] columnNamesRides = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Driver"),
			ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.NPlaces"),
			ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Price")
	};


	public FindRidesGUI()
	{
	/*
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.FindRides"));
		jLabelEventDate.setBounds(new Rectangle(457, 6, 140, 25));
		jLabelEvents.setBounds(172, 231, 259, 16);
		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelEvents);
		jButtonClose.setBounds(new Rectangle(274, 419, 130, 30));
		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});
		BLFacade facade = MainGUI.getBusinessLogic();
		List<String> origins=facade.getDepartCities();
		for(String location:origins) originLocations.addElement(location);
		jLabelOrigin.setBounds(new Rectangle(6, 56, 92, 20));
		jLabelDestination.setBounds(6, 81, 61, 16);
		getContentPane().add(jLabelOrigin);
		getContentPane().add(jLabelDestination);
		jComboBoxOrigin.setModel(originLocations);
		jComboBoxOrigin.setBounds(new Rectangle(103, 50, 172, 20));
		List<String> aCities=facade.getDestinationCities((String)jComboBoxOrigin.getSelectedItem());
		for(String aciti:aCities) {
			destinationCities.addElement(aciti);
		}
		jComboBoxOrigin.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				destinationCities.removeAllElements();
				BLFacade facade = MainGUI.getBusinessLogic();

				List<String> aCities=facade.getDestinationCities((String)jComboBoxOrigin.getSelectedItem());
				for(String aciti:aCities) {
					destinationCities.addElement(aciti);
				}
				tableModelRides.getDataVector().removeAllElements();
				tableModelRides.fireTableDataChanged();
			}
		});
		jComboBoxDestination.setModel(destinationCities);
		jComboBoxDestination.setBounds(new Rectangle(103, 80, 172, 20));
		jComboBoxDestination.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				paintDaysWithEvents(jCalendar1,datesWithRidesCurrentMonth,	new Color(210,228,238));
				BLFacade facade = MainGUI.getBusinessLogic();
				datesWithRidesCurrentMonth=facade.getThisMonthDatesWithRides((String)jComboBoxOrigin.getSelectedItem(),(String)jComboBoxDestination.getSelectedItem(),jCalendar1.getDate());
				paintDaysWithEvents(jCalendar1,datesWithRidesCurrentMonth,Color.CYAN);
			}
		});
		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jComboBoxOrigin, null);
		this.getContentPane().add(jComboBoxDestination, null);
		jCalendar1.setBounds(new Rectangle(300, 50, 225, 150));
		// Code for JCalendar
		jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}
						jCalendar1.setCalendar(calendarAct);
					}
					try {
						tableModelRides.setDataVector(null, columnNamesRides);
						tableModelRides.setColumnCount(4); // another column added to allocate ride objects

						BLFacade facade = MainGUI.getBusinessLogic();
						List<domain.Ride> rides=facade.getRides((String)jComboBoxOrigin.getSelectedItem(),(String)jComboBoxDestination.getSelectedItem(),UtilDate.trim(jCalendar1.getDate()));

						if (rides.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.NoRides")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Rides")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (domain.Ride ride:rides){
							Vector<Object> row = new Vector<Object>();
							row.add(ride.getDriver().getName());
							row.add(ride.getnPlaces());
							row.add(ride.getPrice());
							row.add(ride); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,3)
							tableModelRides.addRow(row);
						}
						datesWithRidesCurrentMonth=facade.getThisMonthDatesWithRides((String)jComboBoxOrigin.getSelectedItem(),(String)jComboBoxDestination.getSelectedItem(),jCalendar1.getDate());
						paintDaysWithEvents(jCalendar1,datesWithRidesCurrentMonth,Color.CYAN);
					} catch (Exception e1) {

						e1.printStackTrace();
					}
					tableRides.getColumnModel().getColumn(0).setPreferredWidth(170);
					tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);
					tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);
					tableRides.getColumnModel().removeColumn(tableRides.getColumnModel().getColumn(3)); // not shown in JTable
				}
			}
		});
		this.getContentPane().add(jCalendar1, null);

		scrollPaneEvents.setBounds(new Rectangle(172, 257, 346, 150));

		scrollPaneEvents.setViewportView(tableRides);
		tableModelRides = new DefaultTableModel(null, columnNamesRides);

		tableRides.setModel(tableModelRides);

		tableModelRides.setDataVector(null, columnNamesRides);
		tableModelRides.setColumnCount(4); // another column added to allocate ride objects

		tableRides.getColumnModel().getColumn(0).setPreferredWidth(170);
		tableRides.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableRides.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableRides.getColumnModel().getColumn(3).setPreferredWidth(240);
		//tableRides.getColumnModel().removeColumn(tableRides.getColumnModel().getColumn(3)); // not shown in JTable

		this.getContentPane().add(scrollPaneEvents, null);
		datesWithRidesCurrentMonth=facade.getThisMonthDatesWithRides((String)jComboBoxOrigin.getSelectedItem(),(String)jComboBoxDestination.getSelectedItem(),jCalendar1.getDate());
		paintDaysWithEvents(jCalendar1,datesWithRidesCurrentMonth,Color.CYAN);

		JButton btnReservarViaje = new JButton("Reservar viaje");
		btnReservarViaje.setBounds(440, 424, 140, 21);
		getContentPane().add(btnReservarViaje);

		JTextArea textArea = new JTextArea();
		textArea.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		textArea.setBounds(34, 178, 230, 22);
		getContentPane().add(textArea);

		spinner = new JSpinner();
		spinner.setBounds(216, 148, 30, 20);
		spinner.setEnabled(true);
		getContentPane().add(spinner);

		JLabel lbl_Asientos = new JLabel("Selecciona la cantidad de asientos:"); //$NON-NLS-1$ //$NON-NLS-2$
		lbl_Asientos.setBounds(34, 151, 162, 13);
		getContentPane().add(lbl_Asientos);

		btnReservarViaje.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {

		            List<Ride> lista = facade.getAllRides();				// Cojo la lista de todos los viajes
		            String origen = (String) jComboBoxOrigin.getSelectedItem();
		            String destino = (String) jComboBoxDestination.getSelectedItem();
		            Date fecha = UtilDate.trim(jCalendar1.getDate());


		            int filaSeleccionada = tableRides.getSelectedRow();
			    	if (filaSeleccionada != -1) {

			    		String nombreConductor = (String) tableRides.getValueAt(filaSeleccionada, 0);
			    		Driver conductor = new Driver(nombreConductor);

			    		float asientosFloat = (float) tableRides.getValueAt(filaSeleccionada, 1);
			            int asientos = (int) asientosFloat;
			            float precio = (float) tableRides.getValueAt(filaSeleccionada, 2);

			    		Ride viajeCorrespondiente = (Ride) tableRides.getValueAt(filaSeleccionada,3);
			    		Integer rideNumberViaje = viajeCorrespondiente.getRideNumber();		//Obtengo el numero del viaje

			    		numPlazas = (int) spinner.getValue();
			    		if (numPlazas <= asientos) {
		    		    	numPlazas+=numPlazas;
		    		        RideSolicitado viajeReservado = usuario.ReservarViaje(rideNumberViaje, origen, destino, fecha, asientos, precio, conductor, usuario, numPlazas);
		    		        DataAccess db = facade.get_database();
		    		        db.open();
		    		        db.storeViajeSolicitado(viajeReservado);
		    		    }
			    	}
		    }
		});
*/
	}



	public FindRidesGUI(viajero usuario)
	{
		this.usuario = usuario;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.FindRides"));

		jLabelEventDate.setBounds(new Rectangle(457, 6, 140, 25));
		jLabelEvents.setBounds(172, 229, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(274, 419, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});
		BLFacade facade = MainGUI.getBusinessLogic();
		List<String> origins=facade.getDepartCities();

		for(String location:origins) originLocations.addElement(location);

		jLabelOrigin.setBounds(new Rectangle(6, 56, 92, 20));
		jLabelDestination.setBounds(6, 81, 61, 16);
		getContentPane().add(jLabelOrigin);

		getContentPane().add(jLabelDestination);

		jComboBoxOrigin.setModel(originLocations);
		jComboBoxOrigin.setBounds(new Rectangle(103, 50, 172, 20));


		List<String> aCities=facade.getDestinationCities((String)jComboBoxOrigin.getSelectedItem());
		for(String aciti:aCities) {
			destinationCities.addElement(aciti);
		}

		jComboBoxOrigin.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				destinationCities.removeAllElements();
				BLFacade facade = MainGUI.getBusinessLogic();

				List<String> aCities=facade.getDestinationCities((String)jComboBoxOrigin.getSelectedItem());
				for(String aciti:aCities) {
					destinationCities.addElement(aciti);
				}
				tableModelRides.getDataVector().removeAllElements();
				tableModelRides.fireTableDataChanged();


			}
		});


		jComboBoxDestination.setModel(destinationCities);
		jComboBoxDestination.setBounds(new Rectangle(103, 80, 172, 20));
		jComboBoxDestination.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {

				paintDaysWithEvents(jCalendar1,datesWithRidesCurrentMonth,	new Color(210,228,238));

				BLFacade facade = MainGUI.getBusinessLogic();

				datesWithRidesCurrentMonth=facade.getThisMonthDatesWithRides((String)jComboBoxOrigin.getSelectedItem(),(String)jComboBoxDestination.getSelectedItem(),jCalendar1.getDate());
				paintDaysWithEvents(jCalendar1,datesWithRidesCurrentMonth,Color.CYAN);

			}
		});

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jComboBoxOrigin, null);

		this.getContentPane().add(jComboBoxDestination, null);


		jCalendar1.setBounds(new Rectangle(300, 50, 225, 150));

		JLabel lbl_Asientos = new JLabel("Selecciona la cantidad de asientos:"); //$NON-NLS-1$
		lbl_Asientos.setBounds(34, 151, 185, 13);
		getContentPane().add(lbl_Asientos);

		spinner = new JSpinner();
		spinner.setBounds(230, 148, 30, 20);
		spinner.setEnabled(true);
		getContentPane().add(spinner);

		JButton btnReservarViaje = new JButton("Reservar viaje");
		btnReservarViaje.setBounds(440, 424, 140, 21);
		getContentPane().add(btnReservarViaje);

		// Code for JCalendar
		jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();



					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());

					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);

					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}

						jCalendar1.setCalendar(calendarAct);

					}

					try {
						tableModelRides.setDataVector(null, columnNamesRides);
						tableModelRides.setColumnCount(4); // another column added to allocate ride objects

						BLFacade facade = MainGUI.getBusinessLogic();
						List<domain.Ride> rides=facade.getRides((String)jComboBoxOrigin.getSelectedItem(),(String)jComboBoxDestination.getSelectedItem(),UtilDate.trim(jCalendar1.getDate()));
						//List<domain.Viajecompuesto> vc = facade.get_database().getViajesCompuestos((String)jComboBoxOrigin.getSelectedItem(),(String)jComboBoxDestination.getSelectedItem(),UtilDate.trim(jCalendar1.getDate()));
						if (rides.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.NoRides")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Rides")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (domain.Ride ride:rides){
							if (ride.getClass().equals(Ride.class)) {
								Vector<Object> row = new Vector<>();
								row.add(ride.getDriver().getName());
								row.add(ride.getnPlaces());
								if(usuario.getmembresia()) {
									//JOptionPane.showMessageDialog(null, "El viajero tiene membresia se hace 10% de descuento");
									row.add((float)ride.getPrice()*0.9);
								}
									
								else {
									row.add(ride.getPrice());
								}
								
								row.add(ride); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,3)
								tableModelRides.addRow(row);
							}
						}
						

						datesWithRidesCurrentMonth=facade.getThisMonthDatesWithRides((String)jComboBoxOrigin.getSelectedItem(),(String)jComboBoxDestination.getSelectedItem(),jCalendar1.getDate());
						paintDaysWithEvents(jCalendar1,datesWithRidesCurrentMonth,Color.CYAN);


					} catch (Exception e1) {

						e1.printStackTrace();
					}
					tableRides.getColumnModel().getColumn(0).setPreferredWidth(170);
					tableRides.getColumnModel().getColumn(1).setPreferredWidth(50);
					tableRides.getColumnModel().getColumn(2).setPreferredWidth(50);
					tableRides.getColumnModel().getColumn(3).setPreferredWidth(240);
					//tableRides.getColumnModel().removeColumn(tableRides.getColumnModel().getColumn(3)); // not shown in JTable

				}
			}

		});

		this.getContentPane().add(jCalendar1, null);

		scrollPaneEvents.setBounds(new Rectangle(172, 257, 346, 150));

		scrollPaneEvents.setViewportView(tableRides);
		tableModelRides = new DefaultTableModel(null, columnNamesRides);

		tableRides.setModel(tableModelRides);

		tableModelRides.setDataVector(null, columnNamesRides);
		tableModelRides.setColumnCount(4); // another column added to allocate ride objects

		tableRides.getColumnModel().getColumn(0).setPreferredWidth(170);
		tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);
		tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);

		tableRides.getColumnModel().removeColumn(tableRides.getColumnModel().getColumn(3)); // not shown in JTable

		this.getContentPane().add(scrollPaneEvents, null);
		datesWithRidesCurrentMonth=facade.getThisMonthDatesWithRides((String)jComboBoxOrigin.getSelectedItem(),(String)jComboBoxDestination.getSelectedItem(),jCalendar1.getDate());
		paintDaysWithEvents(jCalendar1,datesWithRidesCurrentMonth,Color.CYAN);

		JLabel lblNewLabel = new JLabel(""); //$NON-NLS-1$
		lblNewLabel.setBounds(325, 231, 314, 13);
		lblNewLabel.setVisible(true);


		JTextArea textArea = new JTextArea();
		textArea.setText(""); //$NON-NLS-1$
		textArea.setBounds(34, 178, 190, 22);
		textArea.setVisible(false);
		getContentPane().add(textArea);

		btnReservarViaje.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {

		            List<Ride> lista = facade.getAllRides();				// Cojo la lista de todos los viajes
		            String origen = (String) jComboBoxOrigin.getSelectedItem();
		            String destino = (String) jComboBoxDestination.getSelectedItem();
		            Date fecha = UtilDate.trim(jCalendar1.getDate());


		            int filaSeleccionada = tableRides.getSelectedRow();

			    	if (filaSeleccionada != -1) {
			    		
			    		

			    		btnReservarViaje.setEnabled(true);
			    		String nombreConductor = (String) tableRides.getValueAt(filaSeleccionada, 0);

			    		DataAccess db = facade.get_database();
	    		        db.open();
	    		        String correo = "";
	    		        for (Driver d : db.getAllDrivers()) {
	    		        	if (d.getName().equals(nombreConductor)) {
	    		        		 correo = d.getEmail();
	    		        		 break;
	    		        	}
	    		        }
			    		Driver conductor = db.getdrivertById(correo);
			    		System.out.print(conductor);

			    		float asientosFloat = (float) tableRides.getValueAt(filaSeleccionada, 1);
			            int asientos = (int) asientosFloat;
			            double precio = (double) tableRides.getValueAt(filaSeleccionada, 2);
			    		int numPlazasSeleccionadas = (int) spinner.getValue();

			    		Ride viajeCorrespondiente = (Ride) tableRides.getValueAt(filaSeleccionada,3);
			    		int rideNumberViaje = viajeCorrespondiente.getRideNumber();		//Obtengo el numero del viaje

			    		
			    		
			    		
			    		
			    		numPlazas = (int) spinner.getValue();
			    		
			    		double costo = (precio*numPlazas);
			    		//Para realizar la solicitud debemos gestionar y saber si el usuario tiene saldo suficiente
			            double saldoViajero = usuario.getSaldo();
			            if (saldoViajero >= costo) {
			            	
			            	boolean esCompuesto = false;
			                if (numPlazasSeleccionadas <= asientos && numPlazasSeleccionadas > 0) {
			                
			                
			                	
			                try {
			                	Viajecompuesto vc = db.getViajeCompuesto(viajeCorrespondiente);
			                	if (vc != null && vc.paradasIntermedias != null) {		//El viaje es compuesto
					    			JFrame a = new ParadasIntermediasGUI(viajeCorrespondiente,vc, usuario, asientos, costo);
									a.setVisible(true);
									esCompuesto = true;
				    		}
			                } catch (Exception er) {
			                	System.out.print("error");
			                }
			                	
			  
			                	
			                	
			                	if (esCompuesto == false) {
			                		RideSolicitado viajeReservado = usuario.ReservarViaje(rideNumberViaje, origen, destino, fecha, asientos, precio, conductor, usuario, numPlazasSeleccionadas);
			                		db.storeViajeSolicitado(viajeReservado, usuario);
			                    
			                		//usuario.addRideAceptado(viajeReservado);
			                		//arreglar el updateViajero
			                		//db.updateViajero(usuario);
			                		
			                		double nuevoSaldo;
				                    
				                    if(usuario.getmembresia()) {
				                    	
				                    	//double descuento = 0.1 * costo;
				                    	nuevoSaldo = (double) (usuario.getSaldo()-costo);//-(costo*0.9));
				                    }
				                    else {
				                    	 nuevoSaldo = (double) (usuario.getSaldo()-costo);
				                    }
			                		
			                		usuario.setSaldo(nuevoSaldo);
			                		db.updateSaldoViajero(usuario);
			                		textArea.setText("El viaje ha sido reservado");
			                		JOptionPane.showMessageDialog(null, "El viaje ha sido reservado.Nuevo saldo es " + nuevoSaldo);
			                		db.close();
			                		textArea.setVisible(true);
			                	}
			                    
			              
			                    
			                } else {
			                    textArea.setVisible(true);
			                    textArea.setText("No hay tantos asientos para el viaje");
			                }
			            } else {
			                // Mostrar un mensaje indicando que el viajero no tiene saldo suficiente
			                JOptionPane.showMessageDialog(null, "No tienes saldo suficiente para reservar "+numPlazas+" en este viaje.");
			            }
			        } else {
			            textArea.setVisible(true);
			            textArea.setText("Vuelo o cantidad de asientos incorrecto");
			        }
			    }

		});



	}



	public static void paintDaysWithEvents(JCalendar jCalendar,List<Date> datesWithEventsCurrentMonth, Color color) {
		//		// For each day with events in current month, the background color for that day is changed to cyan.


		Calendar calendar = jCalendar.getCalendar();

		int month = calendar.get(Calendar.MONTH);
		int today=calendar.get(Calendar.DAY_OF_MONTH);
		int year=calendar.get(Calendar.YEAR);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;


		for (Date d:datesWithEventsCurrentMonth){

			calendar.setTime(d);


			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
			//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);;
			Component o = jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(color);
		}

		calendar.set(Calendar.DAY_OF_MONTH, today);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);


	}
	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
