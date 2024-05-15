package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bussinessLogic.FacadeImpl;
import bussinessLogic.FacadeInterface;
import domain.Country;
import persistence.DataAccess;

public class ChooseNeighborsGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField countryField;
	private JList neighborCandidates;
	private DefaultListModel countryListContent;
	private JButton btnOrigin;
	private JButton btnAddNeighbors;
	private Country chosenHome=null;

    private FacadeInterface facade;
	
	public void setBusinessLogic(FacadeInterface pFacade){
		facade=pFacade;
	}
	/**
	 * Create the frame.
	 */
	public ChooseNeighborsGUI(FacadeInterface pFacade) {
		setTitle("ChooseNeighborsGUI");
		facade=pFacade;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCountry = new JLabel("COUNTRY:");
		countryField = new JTextField(15);
		countryField.setEditable(false);

		JLabel lblPossibleNeighbors = new JLabel("Possible Neighbors:");
		countryListContent = new DefaultListModel();

		for (Country st: facade.getAllCountries()) {
			countryListContent.addElement(st);
		}
		
		
		
		neighborCandidates = new JList(countryListContent);
		neighborCandidates.setVisibleRowCount(9);
		neighborCandidates.setLayoutOrientation(JList.VERTICAL_WRAP);
		
		btnOrigin = new JButton("Choose country");
		btnOrigin.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent arg0) {
				chosenHome = (Country) neighborCandidates.getSelectedValue();
				countryField.setText(chosenHome.toString());
				countryListContent.removeElement(chosenHome);
			}
		});
		
		btnAddNeighbors = new JButton("Add selected as neighbor countries");
		btnAddNeighbors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chosenHome!=null) {
					List<Country> neighbors = neighborCandidates.getSelectedValuesList();
					System.out.println(facade);
					facade.assignNeighbors(chosenHome, neighbors);
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.CENTER)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(lblCountry)
						.addComponent(countryField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addComponent(lblPossibleNeighbors)
					.addComponent(neighborCandidates, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnOrigin)
							.addComponent(btnAddNeighbors)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCountry)
						.addGap(36)
						.addComponent(countryField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(lblPossibleNeighbors)
					.addGap(18)
					.addComponent(neighborCandidates, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOrigin)
						.addComponent(btnAddNeighbors))
					)
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
					FacadeInterface facade=new FacadeImpl();
					DataAccess dataAccess=new DataAccess();
					facade.setDataAccess(dataAccess);
					ChooseNeighborsGUI frame = new ChooseNeighborsGUI(facade);
					frame.setSize(500, 300);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.pack();
					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
