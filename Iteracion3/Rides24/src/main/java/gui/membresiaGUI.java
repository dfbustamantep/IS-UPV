package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import dataAccess.DataAccess;
import domain.viajero;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

public class membresiaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	

	
	
	public membresiaGUI(viajero v) {
		setTitle("Hacerse miembro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel viajeroLabel = new JLabel("Nombre: ");
		viajeroLabel.setBounds(45, 45, 66, 14);
		contentPane.add(viajeroLabel);
		
		JLabel lblSaldoU = new JLabel("SaldoUsuario");
		lblSaldoU.setBounds(117, 67, 87, 21);
		lblSaldoU.setText(String.valueOf(v.getSaldo())+"€");
		contentPane.add(lblSaldoU);
		
		JButton btnNewButton = new JButton("Hacerse miembro\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BLFacade facade = MainGUI.getBusinessLogic();
				
				facade.hacersemiembro(v);
				lblSaldoU.setText(String.valueOf(v.getSaldo())+"€");
				JOptionPane.showMessageDialog(null, "Ahora eres miembro.");
			}
		});
		btnNewButton.setBounds(56, 192, 129, 43);
		contentPane.add(btnNewButton);
		
		
		
		JLabel lblNewLabel = new JLabel("Precio para hacerse miembro: 30€");
		lblNewLabel.setBounds(10, 133, 214, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Saldo actual:");
		lblNewLabel_1.setBounds(41, 70, 70, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNombre = new JLabel("NombredelUsuario");
		lblNombre.setBounds(107, 42, 109, 21);
		lblNombre.setText(v.getName());
		contentPane.add(lblNombre);
		
		
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(223, 192, 129, 43);
		btnVolver.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				//para cerrar el jframe
				jButtonClose_actionPerformed(e);
				
				
				}
			});
		contentPane.add(btnVolver);
		
		JLabel lblNewLabel_2 = new JLabel("Se aplicara un 10% de descuento en ");
		lblNewLabel_2.setBounds(214, 45, 210, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("todos los viajes");
		lblNewLabel_3.setBounds(214, 70, 98, 14);
		contentPane.add(lblNewLabel_3);
		
	}
	
	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	} 
}
