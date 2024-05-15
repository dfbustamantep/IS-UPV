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




public class VisualizarSolicitudesGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableViajes;
	private List<RideSolicitado> solicitudesReserva= new Vector<>();
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
	public VisualizarSolicitudesGUI() {
		setTitle("Visualizar solicitudes de reserva");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConductor = new JLabel("Conductor");
		lblConductor.setBounds(51, 37, 93, 21);
		contentPane.add(lblConductor);

		lblNombre = new JLabel("Nombre del conductor");
		lblNombre.setBounds(170, 37, 268, 21);
		contentPane.add(lblNombre);

		model.addColumn("Conductor");
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

	public VisualizarSolicitudesGUI(Driver conductor) {
		setTitle("Visualizar solicitudes de reserva");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConductor = new JLabel("Conductor");
		lblConductor.setBounds(51, 37, 93, 21);
		contentPane.add(lblConductor);

		lblNombre = new JLabel("Nombre del conductor");
		lblNombre.setText(conductor.getName());
		lblNombre.setBounds(170, 37, 268, 21);
		contentPane.add(lblNombre);


		model.addColumn("Viajero");
		model.addColumn("Origen");
		model.addColumn("Destino");
		model.addColumn("Plazas Solicitadas");
		model.addColumn("Fecha");
		model.addColumn("Precio");
		model.addColumn("Ride Number");

		tableViajes = new JTable(model);
		tableViajes.setBounds(51, 84, 460, 149);
		contentPane.add(tableViajes);

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
		llenarTabla(conductor);
	}

	///aca vamos a llenar la tabla teniendo como parametro un conductor
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

			model.addRow(new Object[]{"Viajero", "Origen", "Destino", "Plazas Solicitadas", "Fehca", "Precio"});

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
			tableViajes.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public JTable getTabla() {
		return tableViajes;
	}
}
