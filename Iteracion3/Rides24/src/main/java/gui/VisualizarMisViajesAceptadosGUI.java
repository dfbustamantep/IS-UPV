package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Driver;
import domain.RideSolicitado;
import domain.viajero;



public class VisualizarMisViajesAceptadosGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableViajes;
	private List<RideSolicitado> viajesAceptados= new Vector<>();
	//Crear modelo de la tabla
	private DefaultTableModel  model = new DefaultTableModel ();

	JLabel lblNombre;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VisualizarSolicitudesGUI frame = new VisualizarSolicitudesGUI();
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
	public VisualizarMisViajesAceptadosGUI() {
		setTitle("Visualizar viajes aceptados");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConductor = new JLabel("Viajero");
		lblConductor.setBounds(51, 37, 93, 21);
		contentPane.add(lblConductor);

		lblNombre = new JLabel("Nombre del viajero");
		lblNombre.setBounds(170, 37, 268, 21);
		contentPane.add(lblNombre);

		model.addColumn("Viajero");
		model.addColumn("Numero de viaje");
		model.addColumn("Origen");
		model.addColumn("Destino");
		model.addColumn("Plazas");
		model.addColumn("Fecha");
		model.addColumn("Precio");
		model.addColumn("Ride Number");


		//model.addColumn("");

		tableViajes = new JTable(model);
		tableViajes.setBounds(51, 84, 460, 149);
		contentPane.add(tableViajes);
		//definimos las columnas que va a tener el modelo de la tabla


		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		btnVolver.setBounds(208, 256, 103, 37);
		contentPane.add(btnVolver);
		//llenarTabla();
	}
	
	public VisualizarMisViajesAceptadosGUI(viajero viajero) {
		setTitle("Visualizar viajes aceptados");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConductor = new JLabel("Viajero");
		lblConductor.setBounds(51, 37, 93, 21);
		contentPane.add(lblConductor);

		lblNombre = new JLabel("Nombre del viajero");
		lblNombre.setBounds(170, 37, 268, 21);
		contentPane.add(lblNombre);


		model.addColumn("Conductor");
		model.addColumn("Origen");
		model.addColumn("Destino");
		model.addColumn("Plazas Solicitadas");
		model.addColumn("Fecha");
		model.addColumn("Precio");
		model.addColumn("Ride Number");

		tableViajes = new JTable(model);
		tableViajes.setBounds(51, 84, 460, 149);
		contentPane.add(tableViajes);
		//List<RideSolicitado> listaViajesAceptados = viajero.getMisViajesAceptados();
		llenarTabla(viajero);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		btnVolver.setBounds(208, 272, 85, 21);
		contentPane.add(btnVolver);
		
		
	}

	///aca vamos a llenar la tabla teniendo como parametro un conductor
	public void llenarTabla(viajero viajero){
		try {

			//aca vamos a guardar los datos de la solicitudes de viaje
			String[] datos=new String[7];
			BLFacade facade = MainGUI.getBusinessLogic();

			lblNombre.setText(viajero.getName());
			
			model.addRow(new Object[]{"Conductor", "Origen", "Destino", "Plazas Solicitadas", "Fecha", "Precio", "Ride Num"});
			List<RideSolicitado> listaAceptados;
			facade.get_database().open();
			listaAceptados = facade.get_database().getRidesAceptadosbyViajero(viajero);
			for (RideSolicitado solicitud : listaAceptados) {
				System.out.println(solicitud);
				System.out.println(solicitud.getDriver());
				System.out.println(solicitud.getPlazasSolicitadas());
				datos[0]=String.valueOf(solicitud.getDriver());
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
			tableViajes.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public JTable getTabla() {
		return tableViajes;
	}
}
