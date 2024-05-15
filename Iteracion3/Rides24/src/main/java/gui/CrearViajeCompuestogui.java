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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Driver;
import domain.Ride;
import domain.Viajecompuesto;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

public class CrearViajeCompuestogui extends JFrame {
	private static final long serialVersionUID = 1L;


	private Driver driver;
	private JTextField fieldOrigin=new JTextField();
	private JTextField fieldIntermedias=new JTextField();

	private JLabel jLabelOrigin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.LeavingFrom"));
	private JLabel jLabelIntermedias = new JLabel("Intermedias");
	private JLabel jLabelSeats = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.NumberOfSeats"));
	private JLabel jLabRideDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.RideDate"));
	private JLabel jLabelPrice = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.Price"));



	private JTextField jTextFieldSeats = new JTextField();
	private JTextField jTextFieldPrice = new JTextField();

	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.CreateRide"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();

	private List<Date> datesWithEventsCurrentMonth;
	private JTextField fieldDestination;


	private ArrayList<String> paradasIntermedias;


	public CrearViajeCompuestogui(Driver driver) {

		this.driver=driver;
		paradasIntermedias = new ArrayList<String>();
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.CreateRide"));

		jLabelOrigin.setBounds(new Rectangle(20, 32, 92, 20));
		jLabelSeats.setBounds(new Rectangle(6, 180, 173, 20));
		jTextFieldSeats.setBounds(new Rectangle(139, 180, 60, 20));

		jLabelPrice.setBounds(new Rectangle(6, 214, 173, 20));
		jTextFieldPrice.setBounds(new Rectangle(139, 214, 60, 20));

		jCalendar.setBounds(new Rectangle(300, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jButtonCreate.setBounds(new Rectangle(100, 263, 130, 30));

		jButtonCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBounds(new Rectangle(275, 263, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 210, 305, 20));
		jLabelMsg.setForeground(Color.red);

		jLabelError.setBounds(new Rectangle(6, 191, 320, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonCreate, null);
		this.getContentPane().add(jTextFieldSeats, null);

		this.getContentPane().add(jLabelSeats, null);
		this.getContentPane().add(jLabelOrigin, null);




		this.getContentPane().add(jCalendar, null);

		this.getContentPane().add(jLabelPrice, null);
		this.getContentPane().add(jTextFieldPrice, null);




		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getThisMonthDatesWithRides("a","b",jCalendar.getDate());

		jLabRideDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabRideDate.setBounds(298, 16, 140, 25);
		getContentPane().add(jLabRideDate);

		jLabelIntermedias.setBounds(20, 71, 61, 16);
		getContentPane().add(jLabelIntermedias);


		fieldOrigin.setBounds(100, 29, 130, 26);
		getContentPane().add(fieldOrigin);
		fieldOrigin.setColumns(10);


		fieldIntermedias.setBounds(107, 66, 123, 26);
		getContentPane().add(fieldIntermedias);
		fieldIntermedias.setColumns(10);

		fieldDestination = new JTextField();
		fieldDestination.setText(""); //$NON-NLS-1$
		fieldDestination.setBounds(113, 136, 117, 20);
		getContentPane().add(fieldDestination);
		fieldDestination.setColumns(10);

		JLabel jLabelDestination2 = new JLabel("Destino");
		jLabelDestination2.setBounds(20, 139, 61, 14);
		getContentPane().add(jLabelDestination2);
		
		JButton btnParadasIntermedias = new JButton("Añadir Parada");
		btnParadasIntermedias.setBounds(100, 105, 130, 21);
		getContentPane().add(btnParadasIntermedias);
		
		btnParadasIntermedias.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
            	try {      		       		
            		String paradaIntermedia = fieldIntermedias.getText();
            		paradasIntermedias.add(paradaIntermedia);
            		fieldIntermedias.setText("");
            		jLabelMsg.setText("Parada añadida." + paradaIntermedia);
            	} catch (Exception er) {
            		jLabelMsg.setText("");
            		jLabelMsg.setText("Añade una parada");
            	}
            	
            	
            }
		});
		 //Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
//
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());

					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolverá 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}

						jCalendar.setCalendar(calendarAct);

					}
					jCalendar.setCalendar(calendarAct);
					int offset = jCalendar.getCalendar().get(Calendar.DAY_OF_WEEK);

						if (Locale.getDefault().equals(new Locale("es")))
							offset += 4;
						else
							offset += 5;
				Component o = jCalendar.getDayChooser().getDayPanel().getComponent(jCalendar.getCalendar().get(Calendar.DAY_OF_MONTH) + offset);
				}}});

	}
	private void jButtonCreate_actionPerformed(ActionEvent e) {
		jLabelMsg.setText("");
		String error=field_Errors();
		if (error!=null)
			jLabelMsg.setText(error);
		else
			try {
				BLFacade facade = MainGUI.getBusinessLogic();
				int inputSeats = Integer.parseInt(jTextFieldSeats.getText());
				float price = Float.parseFloat(jTextFieldPrice.getText());

				//Ride r=facade.createRide(fieldOrigin.getText(), fieldDestination.getText(), UtilDate.trim(jCalendar.getDate()), inputSeats, price, driver.getEmail());
				Viajecompuesto r = facade.crearviajecompuesto(fieldOrigin.getText(), fieldDestination.getText(), UtilDate.trim(jCalendar.getDate()),  inputSeats, price,driver.getEmail(), paradasIntermedias);
				DataAccess db = facade.get_database();
				db.open();
				//db.storeViajecompuesto(r);
				db.close();
				jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.RideCreated"));

			} catch (RideMustBeLaterThanTodayException e1) {
				// TODO Auto-generated catch block
				jLabelMsg.setText(e1.getMessage());
			} catch (RideAlreadyExistException e1) {
				// TODO Auto-generated catch block
				jLabelMsg.setText(e1.getMessage());
			}

		}


	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	private String field_Errors() {

		try {
			if ((fieldOrigin.getText().length()==0) || (jTextFieldSeats.getText().length()==0) || (jTextFieldPrice.getText().length()==0))
				return ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorQuery");
			else {

				// trigger an exception if the introduced string is not a number
				int inputSeats = Integer.parseInt(jTextFieldSeats.getText());

				if (inputSeats <= 0) {
					return ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.SeatsMustBeGreaterThan0");
				}
				else {
					float price = Float.parseFloat(jTextFieldPrice.getText());
					if (price <= 0)
						return ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.PriceMustBeGreaterThan0");

					else
						return null;

				}
			}
		} catch (java.lang.NumberFormatException e1) {

			return  ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorNumber");
		} catch (Exception e1) {

			e1.printStackTrace();
			return null;

		}
	}
}