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
import javax.swing.SwingConstants;

public class RemoveCountryGUI extends JFrame {

	private JPanel contentPane;
	private DefaultComboBoxModel countryCBContent;
	

    private FacadeInterface facade;
	private JButton btnMoreInfopred;
	private JComboBox comboBox;
	private President pr;
	private Country c;
	private JLabel lblNewLabel;

	
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
					RemoveCountryGUI frame = new RemoveCountryGUI(facade);
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
	public RemoveCountryGUI(FacadeInterface pFacade) {
		setTitle("RemoveCountryGUI");
		facade=pFacade;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1));
		
		countryCBContent=new DefaultComboBoxModel();
		List<Country> lc = facade.getAllCountries(); 
		System.out.println(lc);
		for (Country st: lc) {
			countryCBContent.addElement(st);
		}
		
		JLabel lblCountry = new JLabel("Choose country to delete");
		lblCountry.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCountry);
		
		comboBox = new JComboBox(countryCBContent);
		comboBox.setSelectedItem(lc.get(0));
	
		contentPane.add(comboBox);
		
		btnMoreInfopred = new JButton("Delete country");
		btnMoreInfopred.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c=(Country)comboBox.getSelectedItem();
				boolean b = facade.removeCountry(c);
				if (b) lblNewLabel.setText("Country removed");
				else lblNewLabel.setText("Country not removed");}
		});
		
				contentPane.add(btnMoreInfopred);
		
		lblNewLabel = new JLabel("");
		contentPane.add(lblNewLabel);
	}

}
