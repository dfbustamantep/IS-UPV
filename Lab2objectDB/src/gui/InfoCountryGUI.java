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

public class InfoCountryGUI extends JFrame {

	private JPanel contentPane;
	private DefaultComboBoxModel countryCBContent;
	

    private FacadeInterface facade;
	private JLabel label;
	private JButton btnMoreInfopred;
	private JComboBox comboBox;
	private President pr;
	private Country c;
	private JLabel lblNewLabel;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	
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
					InfoCountryGUI frame = new InfoCountryGUI(facade);
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
	public InfoCountryGUI(FacadeInterface pFacade) {
		setTitle("InfoCountryGUI");
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
				City ci=c.getCapital();
				pr = c.getPresident();
				if (c!=null) label.setText(ci.getName()); 
				else label.setText("");
				if (pr!=null) lblNewLabel.setText(pr.getFirstName()+" "+pr.getFamilyName());
				else lblNewLabel.setText("");
			}
		});
		contentPane.add(comboBox);
		
		
		JLabel lblTheCapitalIs = new JLabel("The capital is:");
		contentPane.add(lblTheCapitalIs);
		
		label = new JLabel("");
		contentPane.add(label);
		
		
		JLabel lblThePresidentIs = new JLabel("The president is:");
		contentPane.add(lblThePresidentIs);
		
		lblNewLabel = new JLabel("");
		contentPane.add(lblNewLabel);
		
		btnMoreInfopred = new JButton("More info (pred & neighbors)");
		btnMoreInfopred.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (pr!=null) {
					textArea.append("Previous president: "+pr.getPreceededBy()+"\n");
				}
				else textArea.append("There is no predecessor\n");
				if (c!=null) {
					textArea.append("Neighbor countries: "+c.getNeighbors()+"\n");
				}
			}
		});

		contentPane.add(btnMoreInfopred);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

}
