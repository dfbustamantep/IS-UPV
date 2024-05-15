package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logicaNegocio.Login;
import logicaNegocio.Login1;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login1GUI extends JFrame {
	
	private Login logicaNegocio;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login1GUI frame = new Login1GUI();
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
	public Login1GUI() {
		setTitle("Caso de uso: Hacer Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Introducir cuenta:");
		lblNewLabel.setBounds(29, 26, 109, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Introducir Password:");
		lblNewLabel_1.setBounds(29, 89, 109, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Escoger tipo de usuario:");
		lblNewLabel_2.setBounds(29, 152, 121, 14);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(164, 24, 101, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(164, 82, 101, 29);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Entrar al sistema");
		btnNewButton.setBounds(126, 190, 166, 37);
		contentPane.add(btnNewButton);
		
		JRadioButton rdbtnEstudiante = new JRadioButton("Estudiante");
		buttonGroup.add(rdbtnEstudiante);
		rdbtnEstudiante.setBounds(156, 148, 109, 23);
		contentPane.add(rdbtnEstudiante);
		
		JRadioButton rdbtnProfesor = new JRadioButton("Profesor");
		buttonGroup.add(rdbtnProfesor);
		rdbtnProfesor.setBounds(282, 148, 109, 23);
		contentPane.add(rdbtnProfesor);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(93, 248, 245, 45);
		contentPane.add(textArea);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Evento producido sobre componente: "
						 +e.getActionCommand());
					
				String l = textField.getText();
				String p = new String (passwordField.getPassword());
				
				String u;
				
				if(rdbtnProfesor.isSelected()) {	
					u=rdbtnProfesor.getText();
				}
				else {
					if(rdbtnEstudiante.isSelected()) {
						u=rdbtnEstudiante.getText();
					}
					else {
						u="";
					}
				}
				
				boolean b = logicaNegocio.hacerLogin(l, p, u);
				
				if(b) textArea.setText("OK");
				else textArea.setText("Error");
			}
		});
		
	}
	
	public void setLogicaNegocio(Login nl){
		
		logicaNegocio = nl ;
	}
}
