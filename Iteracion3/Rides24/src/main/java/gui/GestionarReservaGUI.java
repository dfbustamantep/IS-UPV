package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import dataAccess.DataAccess;
import domain.Driver;
import domain.Ride;
import domain.RideSolicitado;
import domain.viajero;
import domain.Factura;
public class GestionarReservaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaViajes;
	private List<RideSolicitado> solicitudesReserva= new Vector<>();
	//Crear modelo de la tabla
	private DefaultTableModel  model = new DefaultTableModel ();
	private JLabel lblNombre = new JLabel();

	private JLabel lblNewLabel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GestionarReservaGUI frame = new GestionarReservaGUI();
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
	public GestionarReservaGUI() {
		setTitle("Eliminar solicitudes de reserva");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Conductor");
		lblNewLabel.setBounds(10, 23, 89, 21);
		contentPane.add(lblNewLabel);

		 lblNewLabel_1 = new JLabel("Nombre del conductor");
		lblNewLabel_1.setBounds(127, 23, 150, 21);
		contentPane.add(lblNewLabel_1);

		model.addColumn("Viajero");
		model.addColumn("Origen");
		model.addColumn("Destino");
		model.addColumn("Plazas Solicitadas");
		model.addColumn("Fecha");
		model.addColumn("Precio");

		tablaViajes = new JTable(model);
		tablaViajes.setBounds(51, 79, 317, 120);
		contentPane.add(tablaViajes);

		//VisualizarSolicitudesGUI versol = new VisualizarSolicitudesGUI();
		//versol.getTabla();

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		btnVolver.setBounds(10, 210, 117, 40);
		contentPane.add(btnVolver);

		JButton btnEliminar = new JButton("Eliminar reserva");
		btnEliminar.setEnabled(false); // Inicialmente deshabilitado
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	// Obtiene el número de viaje seleccionado en la tabla
                int selectedRow = tablaViajes.getSelectedRow();

                if (selectedRow != -1) {
                	BLFacade facade = MainGUI.getBusinessLogic();
					/*DataAccess db = facade.get_database();
    		        */
                	
                    // Obtiene el número de viaje de la columna 6 (cuenta desde 0)
                    int rideNumber = Integer.parseInt(model.getValueAt(selectedRow, 6).toString());
                    System.out.println(rideNumber);
                    
                    // Obtener la solicitud de reserva asociada al número de viaje
                    RideSolicitado solicitud = facade.get_database().getRideSolicitadobyRideNumber(rideNumber);

                    // Obtener el viajero asociado a la solicitud de reserva
                    viajero viajero = solicitud.getViajero();

                    // Calcular el costo de la reserva que se está eliminando
                    double costoReserva = solicitud.getPlazasSolicitadas() * solicitud.getPrice();

                    // Restar el costo de la reserva al saldo del viajero
                    double nuevoSaldo = viajero.getSaldo() + costoReserva;
                    viajero.setSaldo(nuevoSaldo);

                    // Actualizar el saldo del viajero en la base de datos
                    facade.get_database().updateSaldoViajero(viajero);
                    facade.get_database().eliminarViajeSolicitadoPorNumero(rideNumber);
                    // Eliminar la fila de la tabla
                    model.removeRow(selectedRow);
                } else {
                    // Muestra un mensaje si no se ha seleccionado ninguna fila
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un viaje para eliminar.");
                }
        }});
		btnEliminar.setBounds(298, 209, 128, 40);
		contentPane.add(btnEliminar);

		JButton btnAceptar = new JButton("Aceptar reserva");
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(149, 210, 128, 40);
		contentPane.add(btnAceptar);
	} /* En esta clase hay que acceder a la base de datos y, de la lista de reservas que tenga el usuario (cliente)
	eliminar la reserva con la fecha que se desee*/


	public GestionarReservaGUI(Driver driver) {
		setTitle("Eliminar solicitudes de reserva");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Conductor");
		lblNewLabel.setBounds(10, 23, 89, 21);
		contentPane.add(lblNewLabel);

		 lblNewLabel_1 = new JLabel("Nombre del conductor");
		 lblNewLabel_1.setText(driver.getName());
		lblNewLabel_1.setBounds(127, 23, 150, 21);
		contentPane.add(lblNewLabel_1);


		model.addColumn("Viajero");
		model.addColumn("Origen");
		model.addColumn("Destino");
		model.addColumn("Plazas Solicitadas");
		model.addColumn("Fecha");
		model.addColumn("Precio");
		model.addColumn("Ride Number");

		tablaViajes = new JTable(model);
		tablaViajes.setBounds(51, 79, 317, 120);
		contentPane.add(tablaViajes);


	llenarTabla(driver);

/*		VisualizarSolicitudesGUI versol = new VisualizarSolicitudesGUI();
		versol.getTabla();
		versol.llenarTabla(driver);

*/

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});


		btnVolver.setBounds(61, 210, 117, 40);
		contentPane.add(btnVolver);

		JButton btnAceptar = new JButton("Aceptar reserva");
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(149, 210, 128, 40);
		contentPane.add(btnAceptar);

		JButton btnEliminar = new JButton("Eliminar reserva");
		btnEliminar.setEnabled(false); // Inicialmente deshabilitado

        btnEliminar.setBounds(298, 209, 128, 40);
        contentPane.add(btnEliminar);

        tablaViajes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
			public void valueChanged(ListSelectionEvent event) {
                // Obtener el índice de la fila seleccionada
                int selectedRow = tablaViajes.getSelectedRow();

                // Habilitar o deshabilitar el botón según si hay una fila seleccionada
                btnEliminar.setEnabled(selectedRow != -1);
                btnAceptar.setEnabled(selectedRow != -1);
            }
        });

        btnAceptar.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
            	// Obtiene el número de viaje seleccionado en la tabla
                int selectedRow = tablaViajes.getSelectedRow();

                if (selectedRow != -1) {
                	
                	try {	
                		
                		// Obtiene el número de viaje de la columna 6 (asumiendo que está en la posición 6)
                    int rideNumber = Integer.parseInt(model.getValueAt(selectedRow, 6).toString());

                    // Llama al método eliminarViajePorNumero en tu DataAccess
                    BLFacade facade = MainGUI.getBusinessLogic();
                    facade.get_database().open();
                    RideSolicitado rideAceptado = facade.get_database().getRideSolicitadobyRideNumber(rideNumber); 
                    //Creamos la factura
            		/*
            		  private Integer rideNumber;
						private String from;
						private String to;
						private int nPlaces;
						private Date date;
						private float price;
						private Driver conductor;*/
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Desea generar una factura?", "Generar Factura", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                        System.out.println("Creando Factura");
                        Factura factura = new Factura(rideAceptado.getRideNumber(), rideAceptado.getFrom(), rideAceptado.getTo(), Math.round(rideAceptado.getnPlaces()), rideAceptado.getDate(), rideAceptado.getPrice(), rideAceptado.getDriver(), rideAceptado.getViajero(), rideAceptado.getPlazasSolicitadas());
                        factura.crearPDF();
                        factura.generarReporteCSV();
                        facade.get_database().storeFactura(factura);
                    } else {
                        System.out.println("No se ha generado la factura.");
                    }
                    
                    rideAceptado.setAceptado(true);
        
                    Ride ridePadre = facade.cogerVueloPadre(rideAceptado, driver);
                    //ridePadre.setBetMinimum((float)ridePadre.getnPlaces() - (float)rideAceptado.getPlazasSolicitadas());
                    		
                    float aa = facade.updateRide(ridePadre.getRideNumber(), (float)rideAceptado.getPlazasSolicitadas());
                    System.out.println("plazas" + aa);
                    facade.storeRideSolicitado(rideAceptado);
                    
                    driver.addRideAceptado(rideAceptado);
                    // Elimina la fila de la tabla
                    model.removeRow(selectedRow);
                   // facade.get_database().eliminarViajeSolicitadoPorNumero(rideNumber);		//Se elimina el ride para que no
                    																		//haya duplicados
                	} catch (Exception er) {
                		JOptionPane.showMessageDialog(null, "Ha habido algun error");
                	}
                    
                    
                } else {
                    // Muestra un mensaje si no se ha seleccionado ninguna fila
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un viaje para aceptar.");
                }
           }});

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	// Obtiene el número de viaje seleccionado en la tabla
                int selectedRow = tablaViajes.getSelectedRow();

                if (selectedRow != -1) {
                	BLFacade facade = MainGUI.getBusinessLogic();
					/*DataAccess db = facade.get_database();
    		        */
                	
                    // Obtiene el número de viaje de la columna 6 (cuenta desde 0)
                    int rideNumber = Integer.parseInt(model.getValueAt(selectedRow, 6).toString());
                    System.out.println(rideNumber);
                    
                    // Obtener la solicitud de reserva asociada al número de viaje
                    RideSolicitado solicitud = facade.get_database().getRideSolicitadobyRideNumber(rideNumber);

                   
                    // Obtener el viajero asociado a la solicitud de reserva
                    viajero viajero = solicitud.getViajero();
                    viajero.getMisViajesAceptados().remove(solicitud);
                    //viajero.getMisViajesAceptados().remove(rideAceptado);
                    // Calcular el costo de la reserva que se está eliminando
                    double costoReserva = solicitud.getPlazasSolicitadas() * solicitud.getPrice();

                    // Restar el costo de la reserva al saldo del viajero
                    double nuevoSaldo = viajero.getSaldo() + costoReserva;
                    viajero.setSaldo(nuevoSaldo);

                    // Actualizar el saldo del viajero en la base de datos
                    facade.get_database().updateSaldoViajero(viajero);
                    facade.get_database().eliminarViajeSolicitadoPorNumero(rideNumber);
                    // Eliminar la fila de la tabla
                    model.removeRow(selectedRow);
                } else {
                    // Muestra un mensaje si no se ha seleccionado ninguna fila
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un viaje para eliminar.");
                }
        }});


	}
	public void llenarTabla(Driver conductor){
		try {
			//conexion a la base de datos
			BLFacade facade = MainGUI.getBusinessLogic();

			facade.get_database().open();

			//aca vamos a guardar los datos de la solicitudes de viaje
			String[] datos=new String[7];
			//llamamos al metodo getRidesbyDriver el caul busca en la bd los objetos que tengan al conductor que esta en parametros
			solicitudesReserva = facade.get_database().getRidesbyDriver(conductor);

			lblNombre.setText(conductor.getName());


			model.addRow(new Object[]{"Viajero", "Origen", "Destino", "Plazas Solicitadas", "Fehca", "Precio", "Ride N"});

			for (RideSolicitado solicitud : solicitudesReserva) {
				System.out.println(solicitud);
				datos[0]=String.valueOf(solicitud.getViajero());
				datos[1]=solicitud.getFrom();
				datos[2]=solicitud.getTo();
				datos[3]=String.valueOf(solicitud.getPlazasSolicitadas());
				datos[4]=solicitud.getDate().toString();
				datos[5]=String.valueOf(solicitud.getPrice());
				datos[6]=solicitud.getRideNumber().toString();
				
				model.addRow(datos);


	            // Agrega una fila a la tabla con los datos de la solicitud
	            //model.addRow(solicitud.getFrom(),solicitud.getTo(),solicitud.getnPlaces(),solicitud.getDate());
				//model.addRow(solicitud.getFrom(),solicitud.getTo(),solicitud.getnPlaces(),solicitud.getDate());
	        }
			//tableViajes.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	public JTable getTabla() {
		return tablaViajes;
	}
}

