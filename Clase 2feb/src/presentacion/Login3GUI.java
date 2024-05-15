package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logicaNegocio.Login;
import javax.swing.JList;

public class Login3GUI extends JFrame {
	
	private Login logicaNegocio;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	//private DefaultComboBoxModel usuarios = new DefaultComboBoxModel();
	private DefaultListModel usuarios = new DefaultListModel();


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
	public Login3GUI() {
		setTitle("Caso de uso: Hacer Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 361);
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
		textField.setBounds(164, 23, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(164, 86, 86, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Entrar al sistema");
		btnNewButton.setBounds(126, 204, 177, 23);
		contentPane.add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(93, 248, 245, 45);
		contentPane.add(textArea);
		
		JList list = new JList();
		list.setBounds(192, 152, 121, 42);
		contentPane.add(list);
		list.setModel(usuarios);
		usuarios.addElement("Estudiante");
		usuarios.addElement("Profesor");

		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Evento producido sobre componente: "
						 +e.getActionCommand());
					
				String l = textField.getText();
				String p = new String (passwordField.getPassword());
				
				String u;
				
				
				//u=usuarios.getSelectedItem().toString();
				//u=comboBox.getSelectedItem().toString();

				 u=list.getSelectedValue().toString();

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
