package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

public class RegistroGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RegistroGUI frame = new RegistroGUI();
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
	public RegistroGUI() {
		setTitle("Caso de uso: Registro");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(188, 38, 181, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(188, 77, 181, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(62, 45, 101, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Correo electronico");
		lblNewLabel_1.setBounds(62, 84, 116, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tipo de usuario");
		lblNewLabel_2.setBounds(62, 168, 79, 14);
		contentPane.add(lblNewLabel_2);

		JRadioButton BotonConductor = new JRadioButton("Conductor");
		buttonGroup.add(BotonConductor);
		BotonConductor.setBounds(188, 164, 79, 23);
		contentPane.add(BotonConductor);

		JRadioButton BotonViajero = new JRadioButton("Viajero");
		buttonGroup.add(BotonViajero);
		BotonViajero.setBounds(301, 164, 68, 23);
		contentPane.add(BotonViajero);

		JButton btnNewButton = new JButton("REGISTRARSE");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();

				if(BotonConductor.isSelected()) {
					facade.get_database().open();
					facade.get_database().storeDriver(textField.getText(), textField_1.getText(), textField_2.getText());
				}

				if(BotonViajero.isSelected()) {
					facade.get_database().open();
					facade.get_database().storeViajero(textField.getText(), textField_1.getText(), textField_2.getText());
				}
			}
		});
		btnNewButton.setBounds(83, 206, 129, 28);
		contentPane.add(btnNewButton);

		textField_2 = new JTextField();
		textField_2.setBounds(188, 116, 181, 28);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Contraseña");
		lblNewLabel_3.setBounds(62, 123, 79, 14);
		contentPane.add(lblNewLabel_3);

		JButton CerrarButton_1 = new JButton("Cerrar");
		CerrarButton_1.setBounds(253, 206, 116, 28);
		contentPane.add(CerrarButton_1);
		CerrarButton_1.addActionListener(new ActionListener() {
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
