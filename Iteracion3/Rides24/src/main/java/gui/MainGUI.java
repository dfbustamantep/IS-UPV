package gui;

/**
 * @author Software Engineering teachers
 */

import javax.swing.*;

import domain.Driver;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import domain.viajero;

public class MainGUI extends JFrame {

	private Driver driver;
	private viajero Viajero;
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	private JButton btnNewButton = null;

	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		appFacadeInterface = afi;
	}

	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton reservarViaje;
	private JButton visualizarReservasViaje;
	private JButton jButtonLogIn;
	private JButton btnEliminarRes;
	private JButton Reseñas;
	private JButton Crearviajecompuesto;
	private JButton btnVisualizarAcept;  //Este el del driver
	private JButton Monedero;
	private JButton visualizarMisViajesAceptados; //Este es el del viajero
	/**
	 * This is the default constructor
	 * @wbp.parser.constructor
	 */



	public MainGUI(Driver d) {
		super();

		driver=d;

		// this.setSize(271, 295);
		this.setSize(495, 290);


		jContentPane = new JPanel();
		jContentPane.setLayout(new GridLayout(4, 1, 0, 0));
		jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSelectOption.setForeground(Color.BLACK);
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jLabelSelectOption);

		btnNewButton = new JButton();
		btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Registrarse"));
		btnNewButton.setEnabled(false);


		jContentPane.add(btnNewButton);

		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new RegistroGUI();
				a.setVisible(true);
			}
		});

		jButtonLogIn = new JButton("Log In"); //$NON-NLS-1$ //$NON-NLS-2$
		jButtonLogIn.setEnabled(false);
		jButtonLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new LoginGUI();
				a.setVisible(true);
			}
		});
		jContentPane.add(jButtonLogIn);


		JButton ReseñasConductor = new JButton("Mirar Reseñas");
		//ReseñasConductor.setEnabled(false);
		jContentPane.add(ReseñasConductor);
		ReseñasConductor.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new ReseñasConductorGUI(d);
				
				a.setVisible(true);
			}
		});
		
		
		jButtonQueryQueries = new JButton();
		jButtonQueryQueries.setEnabled(false);
		jContentPane.add(jButtonQueryQueries);
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QueryRides"));
		jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new FindRidesGUI();

				a.setVisible(true);
			}
		});

		reservarViaje = new JButton("Reservar Viaje");
		reservarViaje.setEnabled(false);
		reservarViaje.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				JFrame a = new FindRidesGUI();
				a.setVisible(true);
				}
			});

		jContentPane.add(reservarViaje);

		visualizarReservasViaje = new JButton("Visualizar reservas");
		//visualizarReservasViaje.setEnabled(false);
		visualizarReservasViaje.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				JFrame a = new VisualizarSolicitudesGUI(d);
				a.setVisible(true);
				}
			});
		jContentPane.add(visualizarReservasViaje);

		Crearviajecompuesto = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
		Crearviajecompuesto.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Crearviajecompuesto"));
		Crearviajecompuesto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				JFrame a = new CrearViajeCompuestogui(d);
				a.setVisible(true);
				}
			});
		jContentPane.add(Crearviajecompuesto);



		
		
		jButtonCreateQuery = new JButton();
		//jButtonCreateQuery.setEnabled(false);
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.CreateRide"));
		jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new CreateRideGUI(driver);
				a.setVisible(true);
			}
		});

		jContentPane.add(jButtonCreateQuery);


		setContentPane(jContentPane);

		rdbtnNewRadioButton = new JRadioButton("English");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				System.out.println("Locale: "+Locale.getDefault());
				paintAgain();				}
		});
		buttonGroup.add(rdbtnNewRadioButton);

		rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				System.out.println("Locale: "+Locale.getDefault());
				paintAgain();
			}
		});

		btnEliminarRes = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
		btnEliminarRes.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.EliminarRes"));
		//btnEliminarRes.setEnabled(false);
		btnEliminarRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new GestionarReservaGUI(d);
				a.setVisible(true);
			}
		});
		jContentPane.add(btnEliminarRes);
		
		btnVisualizarAcept = new JButton();
		btnVisualizarAcept.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.VisualizarViajesAceptados"));
		btnVisualizarAcept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new VisualizarViajesAceptadosGUI(d);
				a.setVisible(true);
			}
		});
		jContentPane.add(btnVisualizarAcept);


		buttonGroup.add(rdbtnNewRadioButton_2);

			panel = new JPanel();
			panel.add(rdbtnNewRadioButton_2);
			panel.add(rdbtnNewRadioButton);
			jContentPane.add(panel);

			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			panel.add(rdbtnNewRadioButton_1);
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					paintAgain();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
			
		Monedero = new JButton("Monedero");
		Monedero.setEnabled(false);
		Monedero.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				JFrame a = new GestionarMonedero();
				a.setVisible(true);
				}
			});
		jContentPane.add(Monedero);
		
		
		
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle") + " - driver :"+driver.getName());

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

		
	}
	
	public MainGUI(viajero d) {
		super();

		Viajero=d;

		// this.setSize(271, 295);
		this.setSize(495, 290);

		jButtonCreateQuery = new JButton();
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.CreateRide"));
		jButtonCreateQuery.setEnabled(false);
		jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new CreateRideGUI(driver);
				a.setVisible(true);
			}
		});

		//jContentPane.add(jButtonCreateQuery);

		jContentPane = new JPanel();
		jContentPane.setLayout(new GridLayout(4, 1, 0, 0));
		jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSelectOption.setForeground(Color.BLACK);
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jLabelSelectOption);

		btnNewButton = new JButton();
		btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Registrarse"));
		btnNewButton.setEnabled(false);
		jContentPane.add(btnNewButton);


		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new RegistroGUI();
				a.setVisible(true);
			}
		});


		JButton Hacersemiembro = new JButton("Hacerse miembro");
		Hacersemiembro.setEnabled(true);
		Hacersemiembro.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				JFrame a = new membresiaGUI(d);
				a.setVisible(true);
				}
			});
		jContentPane.add(Hacersemiembro);
		
		
		visualizarReservasViaje = new JButton("Visualizar solicitudes");
		visualizarReservasViaje.setEnabled(false);
		visualizarReservasViaje.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				JFrame a = new VisualizarSolicitudesGUI();
				a.setVisible(true);
				}
			});
		
		
		
		jContentPane.add(visualizarReservasViaje);

		jButtonQueryQueries = new JButton();

		jContentPane.add(jButtonQueryQueries);
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QueryRides"));
		jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new FindRidesGUI();

				a.setVisible(true);
			}
		});

		reservarViaje = new JButton("Reservar Viaje");

		reservarViaje.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				JFrame a = new FindRidesGUI(d);
				a.setVisible(true);
				}
			});

		jContentPane.add(reservarViaje);
		jContentPane.add(jButtonCreateQuery);


		setContentPane(jContentPane);

		rdbtnNewRadioButton = new JRadioButton("English");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				System.out.println("Locale: "+Locale.getDefault());
				paintAgain();				}
		});
		buttonGroup.add(rdbtnNewRadioButton);

		rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				System.out.println("Locale: "+Locale.getDefault());
				paintAgain();
			}
		});

		jButtonLogIn = new JButton("Log In"); //$NON-NLS-1$ //$NON-NLS-2$
		jButtonLogIn.setEnabled(false);
		jButtonLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new LoginGUI();
				a.setVisible(true);
			}
		});
		jContentPane.add(jButtonLogIn);


		buttonGroup.add(rdbtnNewRadioButton_2);

			panel = new JPanel();
			panel.add(rdbtnNewRadioButton_2);
			panel.add(rdbtnNewRadioButton);
			jContentPane.add(panel);

			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			panel.add(rdbtnNewRadioButton_1);
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					paintAgain();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle") + " - viajero :"+Viajero.getName());

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		Monedero = new JButton("Monedero");
		//Monedero.setEnabled(false);
		Monedero.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				JFrame a = new GestionarMonedero(d);
				a.setVisible(true);
				}
			});
		jContentPane.add(Monedero);
	
		Reseñas = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
		Reseñas.setText("Reseñar");
		Reseñas.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				JFrame a = new ReseñasUsuarioGUI(d);
				a.setVisible(true);
				}
			});
		jContentPane.add(Reseñas);
	
	visualizarMisViajesAceptados = new JButton("Visualizar Mis Viajes Aceptados");
	//Monedero.setEnabled(false);
	visualizarMisViajesAceptados.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed (java.awt.event.ActionEvent e) {
			JFrame a = new VisualizarMisViajesAceptadosGUI(d);
			a.setVisible(true);
			}
		});
	jContentPane.add(visualizarMisViajesAceptados);
}

	public MainGUI() {
		super();



		// this.setSize(271, 295);
		this.setSize(495, 290);

		jButtonCreateQuery = new JButton();
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.CreateRide"));
		jButtonCreateQuery.setEnabled(false);
		jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new CreateRideGUI(driver);
				a.setVisible(true);
			}
		});



		Crearviajecompuesto = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
		Crearviajecompuesto.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Crearviajecompuesto"));
		Crearviajecompuesto.setEnabled(false);
		
		btnVisualizarAcept = new JButton();
		btnVisualizarAcept.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.EliminarRes"));
		btnVisualizarAcept.setEnabled(false);

		jContentPane = new JPanel();
		jContentPane.setLayout(new GridLayout(4, 1, 0, 0));
		jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSelectOption.setForeground(Color.BLACK);
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jLabelSelectOption);

		btnNewButton = new JButton();
		btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Registrarse"));

		jContentPane.add(btnNewButton);

		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new RegistroGUI();
				a.setVisible(true);
			}
		});

		visualizarReservasViaje = new JButton("Visualizar reservas");
		visualizarReservasViaje.setEnabled(false);
		visualizarReservasViaje.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				JFrame a = new VisualizarSolicitudesGUI();
				a.setVisible(true);
				}
			});
		jContentPane.add(visualizarReservasViaje);

		jButtonQueryQueries = new JButton();

		jContentPane.add(jButtonQueryQueries);
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QueryRides"));
		jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new FindRidesGUI();

				a.setVisible(true);
			}
		});

		reservarViaje = new JButton("Reservar Viaje");
		reservarViaje.setEnabled(false);
		reservarViaje.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				JFrame a = new FindRidesGUI();
				a.setVisible(true);
				}
			});

		jContentPane.add(reservarViaje);
		jContentPane.add(jButtonCreateQuery);


		setContentPane(jContentPane);

		rdbtnNewRadioButton = new JRadioButton("English");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				System.out.println("Locale: "+Locale.getDefault());
				paintAgain();				}
		});
		buttonGroup.add(rdbtnNewRadioButton);

		rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				System.out.println("Locale: "+Locale.getDefault());
				paintAgain();
			}
		});

		jButtonLogIn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Login")); //$NON-NLS-1$ //$NON-NLS-2$
		jButtonLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new LoginGUI();
				a.setVisible(true);
			}
		});
		jContentPane.add(jButtonLogIn);


		buttonGroup.add(rdbtnNewRadioButton_2);

			panel = new JPanel();
			panel.add(rdbtnNewRadioButton_2);
			panel.add(rdbtnNewRadioButton);
			jContentPane.add(panel);

			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			panel.add(rdbtnNewRadioButton_1);
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					paintAgain();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		//setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle") + " - driver :"+driver.getName());

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		Monedero = new JButton("Monedero");
		Monedero.setEnabled(false);
		Monedero.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed (java.awt.event.ActionEvent e) {
				JFrame a = new GestionarMonedero();
				a.setVisible(true);
				}
			});
		jContentPane.add(Monedero);
	
	
	visualizarMisViajesAceptados = new JButton("Visualizar Mis Viajes Aceptados");
	visualizarMisViajesAceptados.setEnabled(false);
	visualizarMisViajesAceptados.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed (java.awt.event.ActionEvent e) {
			JFrame a = new VisualizarMisViajesAceptadosGUI();
			a.setVisible(true);
			}
		});
	jContentPane.add(visualizarMisViajesAceptados);
}

	private void paintAgain() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QueryRides"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.CreateRide"));
		//btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Registrarse"));
		//this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle")+ " - driver :"+driver.getName());
	}


	/*public void ajustarBotones(boolean esConductor) {
		if (esConductor) {
			btnNewButton.setEnabled(false);
			jButtonQueryQueries.setEnabled(false);
			reservarViaje.setEnabled(false);
			jButtonLogIn.setEnabled(true);
			visualizarReservasViaje.setEnabled(true);
			jButtonCreateQuery.setEnabled(true);

		} else {
			btnNewButton.setEnabled(false);
			jButtonQueryQueries.setEnabled(true);
			reservarViaje.setEnabled(true);
			jButtonLogIn.setEnabled(true);
			visualizarReservasViaje.setEnabled(false);
			jButtonCreateQuery.setEnabled(false);
		}
	} */

} // @jve:decl-index=0:visual-constraint="0,0"
