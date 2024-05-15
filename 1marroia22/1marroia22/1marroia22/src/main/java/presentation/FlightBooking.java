package presentation;


import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import businessLogic.FlightManager;
import dataAcces.Flight_objectdbAccess;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

import domain.ConcreteFlight;

public class FlightBooking extends JFrame {
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane= null;
	private JLabel lblDepartCity = new JLabel("Departing city:");
	private JLabel lblArrivalCity = new JLabel("Arrival City");
	private JLabel lblYear = new JLabel("Year:");
	private JLabel lblRoomType = new JLabel("Room Type:");
	private JLabel lblMonth = new JLabel("Month:");
	private JLabel lblDay = new JLabel("Day:");;
	private JLabel jLabelResult = new JLabel();
	private JLabel searchResult =   new JLabel();
	private JLabel fechaIncorrecta = new JLabel("");
	private JTextField day = null;
	private JComboBox<String> months = null;
	private DefaultComboBoxModel<String> monthNames = new DefaultComboBoxModel<String>();

	private JTextField year = null;
	
	private JRadioButton bussinesTicket = null;
	private JRadioButton firstTicket = null;
	private JRadioButton touristTicket = null;

	private ButtonGroup fareButtonGroup = new ButtonGroup();  
	
	private JButton lookforFlights = null;
	private DefaultListModel<ConcreteFlight> flightInfo = new DefaultListModel<ConcreteFlight>();

	
	JComboBox<ConcreteFlight> flightList = new JComboBox<ConcreteFlight>();
	private JButton bookFlight = null;
	
	

	
	private Collection<ConcreteFlight> concreteFlightCollection;
	
	private FlightManager businessLogic;  //  @jve:decl-index=0:
	private JScrollPane flightListScrollPane = new JScrollPane();;
	
	
	private ConcreteFlight selectedConcreteFlight;
	private final JComboBox comboBox = new JComboBox();
	
	private Flight_objectdbAccess flightObjectdbAccess;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightBooking frame = new FlightBooking();
					frame.setBusinessLogic(new FlightManager());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Custom operations
	public void setBusinessLogic(FlightManager g) {
		businessLogic = g;
	}
	
	private Date newDate(int year,int month,int day) {

	     Calendar calendar = Calendar.getInstance();
	     calendar.setLenient(false); // To avoid use heuristics to parse inputs as dates
	     calendar.set(year, month, day,0,0,0);
	     calendar.set(Calendar.MILLISECOND, 0);

	     return calendar.getTime();
	}


	/**
	 * Create the frame
	 * 
	 * @return void
	 */
	public FlightBooking() {
		flightObjectdbAccess = new Flight_objectdbAccess();
		
		setTitle("Book Flight");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Depart City");
		lblNewLabel.setBounds(21, 13, 68, 13);
		contentPane.add(lblNewLabel);
		
		final JComboBox<String> departCity = new JComboBox<String>();
		departCity.setModel(new DefaultComboBoxModel(new String[] {"Barcelona", "Donostia", "Sevilla"}));
		departCity.setBounds(99, 9, 243, 21);
		contentPane.add(departCity);
		
		final JComboBox<String> arrivalCity = new JComboBox<String>();
		arrivalCity.setModel(new DefaultComboBoxModel(new String[] {"Bilbo", "Madrid"}));
		arrivalCity.setBounds(99, 37, 243, 21);
		contentPane.add(arrivalCity);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Bilbo", "Madrid"}));
		comboBox.setBounds(99, 37, 243, 21);
		
		contentPane.add(comboBox);
		
		
		lblYear = new JLabel("Year:");
		lblYear.setBounds(21, 62, 33, 16);
		contentPane.add(lblYear);
		
		lblMonth = new JLabel("Month:");
		lblMonth.setBounds(117, 62, 50, 16);
		contentPane.add(lblMonth);
	    
		months = new JComboBox<String>();
		months.setBounds(163, 58, 116, 27);
		contentPane.add(months);
		months.setModel(monthNames);
		
		monthNames.addElement("January");
		monthNames.addElement("February");
		monthNames.addElement("March");
		monthNames.addElement("April");
		monthNames.addElement("May");
		monthNames.addElement("June");
		monthNames.addElement("July");
		monthNames.addElement("August");
		monthNames.addElement("September");
		monthNames.addElement("October");
		monthNames.addElement("November");
		monthNames.addElement("December");
		months.setSelectedIndex(1);
		
		lblDay = new JLabel("Day:");
		lblDay.setBounds(291, 62, 38, 16);
		contentPane.add(lblDay);
		
		day = new JTextField();
		day.setText("23");
		day.setBounds(331, 57, 50, 26);
		contentPane.add(day);
		day.setColumns(10);
		
		lblRoomType = new JLabel("Seat Type:");
		lblRoomType.setBounds(21, 242, 84, 16);
		contentPane.add(lblRoomType);
		
		
		fechaIncorrecta.setBounds(81, 157, 345, 13);
		contentPane.add(fechaIncorrecta);
		
		
		bussinesTicket = new JRadioButton("Business");
		bussinesTicket.setEnabled(true);
		//bussinesTicket.setSelected(true);
		fareButtonGroup.add(bussinesTicket);
		bussinesTicket.setBounds(99, 238, 101, 23);
		contentPane.add(bussinesTicket);
		
		firstTicket = new JRadioButton("First");
		firstTicket.setEnabled(true);
		fareButtonGroup.add(firstTicket);
		firstTicket.setBounds(202, 238, 77, 23);
		contentPane.add(firstTicket);
		
		touristTicket = new JRadioButton("Tourist");
		touristTicket.setEnabled(true);
		fareButtonGroup.add(touristTicket);
		touristTicket.setBounds(278, 238, 77, 23);
		contentPane.add(touristTicket);
		
		lookforFlights = new JButton("Look for Concrete Flights");
		lookforFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechaIncorrecta.setText("");
				searchResult.setText("");
				
				if (bussinesTicket.isSelected()) bussinesTicket.setSelected(false);
				if (firstTicket.isSelected()) firstTicket.setSelected(false);
				if (touristTicket.isSelected()) touristTicket.setSelected(false);
				
				
				flightList.setVisible(true);
				bookFlight.setEnabled(true);
				flightInfo.clear();
				bookFlight.setText("");
				
		
				java.util.Date date = null;
				try {
					 date = newDate(Integer.parseInt(year.getText()),months.getSelectedIndex(),Integer.parseInt(day.getText()));
				} catch(IllegalArgumentException error) {
					fechaIncorrecta.setText("La fecha introducida no es correcta, introduzca otra");
					searchResult.setText("");
				}
				
				//System.out.print(date);
				 
				//concreteFlightCollection=businessLogic.getConcreteFlights(departCity.getSelectedItem(),arrivalCity.getSelectedItem(),date);
				concreteFlightCollection = flightObjectdbAccess.getConcreteFlights(departCity.getSelectedItem().toString(), arrivalCity.getSelectedItem().toString(), date);
				for (ConcreteFlight f : concreteFlightCollection) {
					flightList.addItem(f);
					flightInfo.addElement(f);
				}		 
				if (concreteFlightCollection.isEmpty() && !fechaIncorrecta.getText().equals("La fecha introducida no es correcta, introduzca otra")) searchResult.setText("No flights in that city in that date");
				else if (date != null) searchResult.setText("Choose an available flight in this list:");
				
			}
		});
		
		lookforFlights.setBounds(81, 90, 261, 40);
		contentPane.add(lookforFlights);	
		
		jLabelResult = new JLabel("");
		jLabelResult.setBounds(109, 180, 243, 16);
		contentPane.add(jLabelResult);
		
		flightListScrollPane.setBounds(new Rectangle(65, 166, 333, 40));
		contentPane.add(flightListScrollPane);
		 flightListScrollPane.setViewportView(flightList);
		
		 flightList.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
				//if (e.getValueIsAdjusting()) return; // The event is activated twice: Before the value is changed, and after changed 
								     // we need to act only after changed 
				//if (!flightList.selected()){  
								
	            	bussinesTicket.setSelected(false);
					firstTicket.setSelected(false);
					touristTicket.setSelected(false);
	            	
					selectedConcreteFlight = (ConcreteFlight) flightList.getSelectedItem();
					bussinesTicket.setEnabled(true);
					firstTicket.setEnabled(true);
					touristTicket.setEnabled(true);
					
					int numBusiness = selectedConcreteFlight.getBusinessNumber();
					if (numBusiness == 0) bussinesTicket.setEnabled(false);
					int numFirst = selectedConcreteFlight.getFirstNumber();
					if (numFirst == 0) firstTicket.setEnabled(false);
					int numTourist = selectedConcreteFlight.getTouristNumber();
					if (numTourist == 0) touristTicket.setEnabled(false);
					bookFlight.setEnabled(true);
					bookFlight.setText("Book: "+selectedConcreteFlight);  // TODO Auto-generated Event stub valueChanged()
				//}
			}
		});
		
		bookFlight = new JButton("");
		bookFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int num=0;
				boolean error=false;
				
				if (bussinesTicket.isSelected()) { 
					num=selectedConcreteFlight.getBusinessNumber();
					if (num>0) {
						selectedConcreteFlight.setBusinessNumber(num-1);
					}
					else {
						error=true; 
					}
					 
				}
				else if (firstTicket.isSelected()) {
					num=selectedConcreteFlight.getFirstNumber();
					if (num>0) {
						selectedConcreteFlight.setFirstNumber(num-1);
					}
					else {
						error=true;
					}
						
				}
				
				else if (touristTicket.isSelected()) {
					num=selectedConcreteFlight.getTouristNumber();
					if (num>0) {
						selectedConcreteFlight.setTouristNumber(num-1); 
					}
					else {
						error=true;
					}
				}
				
				if (error) {
					bookFlight.setText("Error: There were no seats available!");
				}
				else {
					String tipoAsiento = "";
		            if (bussinesTicket.isSelected()) {
		            	tipoAsiento = "Business";
		            } else if (firstTicket.isSelected()) {
		            	tipoAsiento = "First";
		            } else if (touristTicket.isSelected()) {
		            	tipoAsiento = "Tourist";
		            }
		            
		            flightObjectdbAccess.updateConcreteFlight(selectedConcreteFlight.getConcreteFlightCode(), tipoAsiento);
		            
					bookFlight.setText("Booked. #seat left: "+(num-1));
				}
				
				if (bussinesTicket.isSelected()) bussinesTicket.setSelected(false);
				if (firstTicket.isSelected()) firstTicket.setSelected(false);
				if (touristTicket.isSelected()) touristTicket.setSelected(false);
				
				bookFlight.setEnabled(false);
				
				
				flightList.setVisible(false);
				bussinesTicket.setEnabled(false);
				firstTicket.setEnabled(false);
				touristTicket.setEnabled(false);
			
				
			}
		});
		bookFlight.setBounds(31, 273, 399, 40);
		contentPane.add(bookFlight);

		year = new JTextField();
		year.setText("2023");
		year.setBounds(57, 57, 50, 26);
		contentPane.add(year);
		year.setColumns(10);
		
		lblArrivalCity.setBounds(21, 39, 84, 16);
		contentPane.add(lblArrivalCity);
		
		searchResult.setBounds(57, 130, 314, 16);
		contentPane.add(searchResult);
		
	}
}  //  @jve:decl-index=0:visual-constraint="18,9"
