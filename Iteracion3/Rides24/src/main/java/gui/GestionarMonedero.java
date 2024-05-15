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

public class GestionarMonedero extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMonto;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionarMonedero frame = new GestionarMonedero();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public GestionarMonedero() {
		setTitle("Monedero");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreUsuario = new JLabel("Nombre:");
		lblNombreUsuario.setBounds(10, 10, 52, 21);
		contentPane.add(lblNombreUsuario);
		
		JLabel lblNombre = new JLabel("NombredelUsuario");
		lblNombre.setBounds(82, 10, 109, 21);
		contentPane.add(lblNombre);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(10, 41, 52, 21);
		contentPane.add(lblSaldo);
		
		JLabel lblSaldoU = new JLabel("SaldoUsuario");
		lblSaldoU.setBounds(82, 41, 87, 21);
		//lblSaldoU.setText(String.valueOf(v.getSaldo())+"€");
		contentPane.add(lblSaldoU);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(82, 197, 99, 32);
		btnVolver.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				//para cerrar el jframe
				jButtonClose_actionPerformed(e);
				
				JFrame a = new MainGUI();
				a.setVisible(true);
				}
			});
		contentPane.add(btnVolver);
		
		
		
		JButton btnAgregarSaldo = new JButton("Agregar Saldo");
		btnAgregarSaldo.setBounds(203, 197, 128, 32);
		contentPane.add(btnAgregarSaldo);
		
		JLabel lblMonto = new JLabel("Monto a consignar");
		lblMonto.setBounds(56, 102, 135, 13);
		contentPane.add(lblMonto);
		
		textFieldMonto = new JTextField();
		textFieldMonto.setBounds(211, 99, 120, 19);
		contentPane.add(textFieldMonto);
		textFieldMonto.setColumns(10);
	}
	
	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	} 
	
	public GestionarMonedero(viajero v) {
		setTitle("Monedero");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreUsuario = new JLabel("Nombre:");
		lblNombreUsuario.setBounds(10, 10, 52, 21);
		contentPane.add(lblNombreUsuario);
		
		JLabel lblNombre = new JLabel("NombredelUsuario");
		lblNombre.setBounds(82, 10, 109, 21);
		lblNombre.setText(v.getName());
		contentPane.add(lblNombre);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(10, 41, 52, 21);
		
		contentPane.add(lblSaldo);
		
		JLabel lblSaldoU = new JLabel("SaldoUsuario");
		lblSaldoU.setBounds(82, 41, 87, 21);
		lblSaldoU.setText(String.valueOf(v.getSaldo())+"€");
		contentPane.add(lblSaldoU);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(108, 214, 85, 21);
		btnVolver.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				jButtonClose_actionPerformed(e);
				dispose();
				//JFrame a = new MainGUI(v);
				//a.setVisible(true);
				}
			});
		contentPane.add(btnVolver);
		
		
		JButton btnAgregarSaldo = new JButton("Agregar Saldo");
		btnAgregarSaldo.setBounds(216, 214, 135, 21);
		btnAgregarSaldo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				double saldo=v.getSaldo();
				try {
					BLFacade facade = MainGUI.getBusinessLogic();
					DataAccess db = facade.get_database();
    		        db.open();
					double saldoAgregar =Double.parseDouble( textFieldMonto.getText());
					saldo+=saldoAgregar;
					v.setSaldo(saldo);
					db.updateSaldoViajero(v);
					lblSaldoU.setText(String.valueOf(v.getSaldo())+"€");
					
					JOptionPane.showMessageDialog(null,"El nuevo saldo es "+v.getSaldo());
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Ingrese un monto valido");
					System.out.println(ex.getMessage());
				}
				}
			});
		contentPane.add(btnAgregarSaldo);
		
		JLabel lblMonto = new JLabel("Monto a consignar");
		lblMonto.setBounds(101, 102, 90, 13);
		contentPane.add(lblMonto);
		
		textFieldMonto = new JTextField();
		textFieldMonto.setBounds(211, 99, 96, 19);
		contentPane.add(textFieldMonto);
		textFieldMonto.setColumns(10);
	}
}
