package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bussinessLogic.FacadeImpl;
import bussinessLogic.FacadeInterface;
import domain.City;
import domain.Country;
import persistence.DataAccess;
import javax.swing.LayoutStyle.ComponentPlacement;

public class NewCountriesGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtCountry;
	private JTextField txtPopulation;
	private JTextField txtCapital;
	private JTextField txtPopInCapital;
	private JTextField txtAirport;
	private JTextField txtFoundationYear;
	
	private JButton btnCreate;

	
	private FacadeInterface facade;

	private JLabel lblMessage;
	
	public void setBusinessLogic(FacadeInterface pFacade){
		facade=pFacade;
	}

	public NewCountriesGUI() {
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblCountry = new JLabel("Country:");
		JLabel lblPopulation = new JLabel("Population (in millions):");
		JLabel lblCapital = new JLabel("Capital:");
		JLabel lblCappopul = new JLabel("Population (in thousands):");
		JLabel lblAirport = new JLabel("Airport:");
		JLabel lblFoundation = new JLabel("Foundation year:");

		txtCountry = new JTextField(15);
		txtPopulation = new JTextField(15);
		txtCapital = new JTextField(15);
		txtCapital.setColumns(15);
		txtPopInCapital = new JTextField(15);
		txtAirport = new JTextField(15);
		txtFoundationYear = new JTextField(15);

		btnCreate = new JButton("Save country");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				Country currentCountry = new Country(txtCountry.getText(), 
//						(int)  (1000000.0 * Float.parseFloat(txtPopulation.getText())));
//				City currentCity = new City(txtCapital.getText(), currentCountry, 
//						(int) (1000.0 * Float.parseFloat(txtPopInCapital.getText())), 
//						txtAirport.getText(), Integer.parseInt(txtFoundationYear.getText()));
//				facade.saveCountryAndCity(currentCountry, currentCity);
				boolean error=true;
				try {
				error=facade.saveCountryAndCity(txtCountry.getText(),(int)  (1000000.0 * Float.parseFloat(txtPopulation.getText())),
										  txtCapital.getText(),(int) (1000.0 * Float.parseFloat(txtPopInCapital.getText())),
										  txtAirport.getText(), Integer.parseInt(txtFoundationYear.getText()));
				}
				catch (Exception e) {error=true;};
				if (error) lblMessage.setText("Error: not added");
				else lblMessage.setText("Country added");
				txtCountry.setText("");
				txtPopulation.setText("");
				txtCapital.setText("");
				txtPopInCapital.setText("");
				txtAirport.setText("");
				txtFoundationYear.setText("");
			}
		});
		
		lblMessage = new JLabel("");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.CENTER)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCountry)
								.addComponent(lblPopulation)
								.addComponent(lblCapital)
								.addComponent(lblCappopul)
								.addComponent(lblAirport)
								.addComponent(lblFoundation))
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPopulation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCapital, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPopInCapital, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAirport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFoundationYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCreate)
							.addGap(18)
							.addComponent(lblMessage)))
					.addGap(135))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCountry)
						.addComponent(txtCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPopulation)
						.addComponent(txtPopulation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCapital)
						.addComponent(txtCapital, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCappopul)
						.addComponent(txtPopInCapital, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAirport)
						.addComponent(txtAirport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFoundation)
						.addComponent(txtFoundationYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreate)
						.addComponent(lblMessage))
					.addGap(80))
		);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCountriesGUI frame = new NewCountriesGUI();
					frame.setSize(500, 400);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.pack();
					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
					FacadeInterface facade=new FacadeImpl();
					DataAccess dataAccess=new DataAccess();
					facade.setDataAccess(dataAccess);
					frame.setBusinessLogic(facade);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
