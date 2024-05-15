package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Driver;
import domain.viajero;
public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldCorreo;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setTitle("Caso de uso:Hacer Login");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Correo electronico");
		lblNewLabel_1.setBounds(111, 70, 116, 14);
		contentPane.add(lblNewLabel_1);

		textFieldCorreo = new JTextField();
		textFieldCorreo.setColumns(10);
		textFieldCorreo.setBounds(237, 64, 181, 28);
		contentPane.add(textFieldCorreo);

		JLabel lblNewLabel_3 = new JLabel("Contraseña");
		lblNewLabel_3.setBounds(111, 109, 79, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_2 = new JLabel("Tipo de usuario");
		lblNewLabel_2.setBounds(111, 154, 116, 14);
		contentPane.add(lblNewLabel_2);

		JRadioButton BotonConductor = new JRadioButton("Conductor");
		buttonGroup.add(BotonConductor);
		BotonConductor.setBounds(234, 150, 106, 23);
		contentPane.add(BotonConductor);

		JRadioButton BotonViajero = new JRadioButton("Viajero");
		buttonGroup.add(BotonViajero);
		BotonViajero.setBounds(342, 150, 116, 23);
		contentPane.add(BotonViajero);

		JButton btnIniciarSesion = new JButton("INICIAR SESION");
		btnIniciarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.get_database().open();

				if(BotonConductor.isSelected()) {
					Driver a = facade.get_database().getdrivertById(textFieldCorreo.getText());
					//try {
						if(a.getContraseña().equals(passwordField.getText())) {
						MainGUI AB = new MainGUI(a);
						AB.setVisible(true);
						//AB.ajustarBotones(true);
					}
					//} catch (Exception er) {
						System.out.println("El login es incorrecto");
					//}

				}
				if(BotonViajero.isSelected()) {
					viajero b = facade.get_database().getViajerotById(textFieldCorreo.getText());
					//try {
						if(b.getContraseña().equals(passwordField.getText())) {
						MainGUI BA = new MainGUI(b);
						BA.setVisible(true);
						//BA.ajustarBotones(false);
					}
					//} catch (Exception err) {
						//System.out.println("El login es incorrecto");
					//}
			}
		}
		});
		btnIniciarSesion.setBounds(91, 197, 167, 28);
		contentPane.add(btnIniciarSesion);

		passwordField = new JPasswordField();
		passwordField.setBounds(237, 103, 181, 28);
		contentPane.add(passwordField);

		JButton btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(280, 197, 159, 28);
		contentPane.add(btnVolver);

		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});
	}
	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
