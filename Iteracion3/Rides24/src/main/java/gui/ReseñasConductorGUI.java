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
import domain.Reseñas;
import domain.RideSolicitado;
import domain.viajero;



public class ReseñasConductorGUI extends JFrame {

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
	public ReseñasConductorGUI() {
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
	
	public ReseñasConductorGUI(Driver d) {
		setTitle("Visualizar viajes aceptados");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConductor = new JLabel("Conductor");
		lblConductor.setBounds(51, 37, 93, 21);
		contentPane.add(lblConductor);

		lblNombre = new JLabel("Nombre del Conductor");
		lblNombre.setBounds(170, 37, 268, 21);
		contentPane.add(lblNombre);


		model.addColumn("Viajero");
		model.addColumn("Valoracion");
		model.addColumn("Reseña");
		

		tableViajes = new JTable(model);
		tableViajes.setBounds(51, 84, 460, 149);
		contentPane.add(tableViajes);
		//List<RideSolicitado> listaViajesAceptados = viajero.getMisViajesAceptados();
		llenarTabla(d);
		
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
	public void llenarTabla(Driver d){
		try {

			//aca vamos a guardar los datos de la solicitudes de viaje
			String[] datos=new String[3];
			BLFacade facade = MainGUI.getBusinessLogic();

			lblNombre.setText(d.getName());
			
			model.addRow(new Object[]{"Viajero", "Valoracion (De 0 a 5)", "Reseña"});
			List<Reseñas> listaReseñas;
			facade.get_database().open();
			listaReseñas = facade.getReseñas(d);
			System.out.println(listaReseñas.size());
			for (Reseñas r : listaReseñas) {
				
				datos[0]=String.valueOf(r.getViajero().getName());
				datos[1]=String.valueOf(r.getValoracion());
				datos[2]=r.getComentario();
				
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
