package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.lowagie.text.List;

import businessLogic.BLFacade;
import domain.viajero;
import domain.Driver;
import domain.Reseñas;
import domain.RideSolicitado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ReseñasUsuarioGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReseñasUsuarioGUI frame = new ReseñasUsuarioGUI();
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
	public ReseñasUsuarioGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Calificar a:");
		lblNewLabel.setBounds(6, 22, 78, 39);
		contentPane.add(lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("0");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(6, 102, 39, 18);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("1");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(67, 102, 34, 18);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("2");
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(137, 102, 39, 18);
		contentPane.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("3");
		buttonGroup.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setBounds(209, 102, 39, 18);
		contentPane.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("4");
		buttonGroup.add(rdbtnNewRadioButton_4);
		rdbtnNewRadioButton_4.setBounds(293, 102, 39, 18);
		contentPane.add(rdbtnNewRadioButton_4);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("5");
		buttonGroup.add(rdbtnNewRadioButton_5);
		rdbtnNewRadioButton_5.setBounds(369, 102, 47, 18);
		contentPane.add(rdbtnNewRadioButton_5);
		
		JLabel lblNewLabel_1 = new JLabel("Dentro de esta escala (0, muy malo; 5, muy bueno)");
		lblNewLabel_1.setBounds(16, 63, 305, 16);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(16, 150, 389, 71);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(26, 233, 89, 28);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Enviar calificación");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setBounds(267, 233, 138, 28);
		contentPane.add(btnNewButton_1);
		
		
	}
	
	public ReseñasUsuarioGUI(viajero viajero) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Calificar a:");
		lblNewLabel.setBounds(6, 22, 78, 39);
		contentPane.add(lblNewLabel);
		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("0");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(6, 102, 39, 18);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("1");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(67, 102, 34, 18);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("2");
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(137, 102, 39, 18);
		contentPane.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("3");
		buttonGroup.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setBounds(209, 102, 39, 18);
		contentPane.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("4");
		buttonGroup.add(rdbtnNewRadioButton_4);
		rdbtnNewRadioButton_4.setBounds(293, 102, 39, 18);
		contentPane.add(rdbtnNewRadioButton_4);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("5");
		buttonGroup.add(rdbtnNewRadioButton_5);
		rdbtnNewRadioButton_5.setBounds(369, 102, 47, 18);
		contentPane.add(rdbtnNewRadioButton_5);
		
		JLabel lblNewLabel_1 = new JLabel("Dentro de esta escala (0, muy malo; 5, muy bueno)");
		lblNewLabel_1.setBounds(16, 63, 305, 16);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(16, 150, 389, 71);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(26, 233, 89, 28);
		contentPane.add(btnNewButton);
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(79, 28, 131, 26);
		contentPane.add(comboBox);
		BLFacade facade = MainGUI.getBusinessLogic();
		facade.get_database().open();
			
		for(RideSolicitado r: facade.get_database().getRidesAceptadosbyViajero(viajero)) {
			comboBox.addItem(r.getDriver());
		}
		
		JButton btnNewButton_1 = new JButton("Enviar calificación");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedValue = 0;
				if(rdbtnNewRadioButton.isSelected()) {
					selectedValue = 0;
				}else if(rdbtnNewRadioButton_1.isSelected()) {
					selectedValue = 1;
				}else if(rdbtnNewRadioButton_2.isSelected()) {
					selectedValue = 2;
				}else if(rdbtnNewRadioButton_3.isSelected()) {
					selectedValue = 3;
				}else if(rdbtnNewRadioButton_4.isSelected()) {
					selectedValue = 4;
				}else if(rdbtnNewRadioButton_5.isSelected()) {
					selectedValue = 5;
				}
				Driver d = (Driver)comboBox.getSelectedItem();
				Reseñas res = new Reseñas(viajero, textField.getText(), selectedValue, d);
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.guardarReseña(res);
				JOptionPane.showMessageDialog(null, "Reseña enviada con exito.");
			}
		});
		btnNewButton_1.setBounds(267, 233, 138, 28);
		contentPane.add(btnNewButton_1);
	}
}
