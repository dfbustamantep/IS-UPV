package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bussinessLogic.FacadeImpl;
import bussinessLogic.FacadeInterface;
import domain.City;
import domain.Country;
import domain.President;
import persistence.DataAccess;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class RemovePresidentFromCountryGUI extends JFrame {

	private JPanel contentPane;
	private DefaultComboBoxModel countryCBContent;
	

    private FacadeInterface facade;
	private JButton btnMoreInfopred;
	private JComboBox comboBox;
	private President pr;
	private Country c;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	
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
					RemovePresidentFromCountryGUI frame = new RemovePresidentFromCountryGUI(facade);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RemovePresidentFromCountryGUI(FacadeInterface pFacade) {
		setTitle("RemovePresidentFromCountryGUI");
		facade=pFacade;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 2));
		
		countryCBContent=new DefaultComboBoxModel();
		List<Country> lc = facade.getAllCountries(); 
		System.out.println(lc);
		for (Country st: lc) {
			countryCBContent.addElement(st);
		}
		
		JLabel lblCountry = new JLabel("Country");
		contentPane.add(lblCountry);
		
		comboBox = new JComboBox(countryCBContent);
		comboBox.setSelectedItem(lc.get(0));
		comboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				c=(Country)comboBox.getSelectedItem();
				pr = c.getPresident();
				if (pr!=null) lblNewLabel.setText(pr.getFirstName()+" "+pr.getFamilyName());
				else lblNewLabel.setText("");
			}
		});
		contentPane.add(comboBox);
		
		
		JLabel lblThePresidentIs = new JLabel("The president is:");
		contentPane.add(lblThePresidentIs);
		
		lblNewLabel = new JLabel("");
		contentPane.add(lblNewLabel);
		
		btnMoreInfopred = new JButton("Remove president");
		btnMoreInfopred.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean b = facade.removePresidentFromCountry(c); 
				if (b) lblNewLabel_1.setText("President deleted");
				else lblNewLabel_1.setText("Error: president not deleted");
			}
		});

		contentPane.add(btnMoreInfopred);
		
		lblNewLabel_1 = new JLabel("");
		contentPane.add(lblNewLabel_1);
	}

}
