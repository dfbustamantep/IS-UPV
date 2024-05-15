package domain;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlID;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Font;

import java.awt.Color;
import java.awt.Graphics;

@Entity
public class Factura implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer rideNumber;
	private String from;
	private String to;
	private int nPlacesTotales;
	private Date date;
	private double price;
	private Driver conductor;
	private viajero viajero;
	private int numPlazasSolicitadas;
	
	
	public Factura(Integer rideNumber, String from, String to, int nPlacesTotales, Date date, double d,Driver conductor,viajero viajero,int PlazasSolicitadas) {
		this.rideNumber = rideNumber;
		this.from = from;
		this.to = to;
		this.nPlacesTotales = nPlacesTotales;
		this.date = date;
		
		//this.viajero.getmembresia()
		
		this.conductor = conductor;
		this.viajero=viajero;
		
			this.price = d;
	
		
		this.numPlazasSolicitadas=PlazasSolicitadas;
	}

	public Integer getRideNumber() {
		return rideNumber;
	}

	public void setRideNumber(Integer rideNumber) {
		this.rideNumber = rideNumber;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public float getnPlaces() {
		return nPlacesTotales;
	}

	public void setnPlaces(int nPlacesTotales) {
		this.nPlacesTotales = nPlacesTotales;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Driver getConductor() {
		return conductor;
	}

	public void setConductor(Driver conductor) {
		this.conductor = conductor;
	}
	
	public void crearPDF() {
		//se va a usar la libreria itext
		 //creacion documento
        Document document = new Document() {};
        
        try{

    		
            //creacion del eritter
            PdfWriter writer=PdfWriter.getInstance((com.lowagie.text.Document) document, new FileOutputStream("Factura_"+viajero.getName()+".pdf"));
            //abrir el documento
            document.open();
            
            PdfContentByte cb=writer.getDirectContent();
            //definir tamaño carta
            Graphics g=cb.createGraphicsShapes(PageSize.LETTER.getWidth(), PageSize.LETTER.getHeight());
        
            Font fontTitulo = new Font(Font.BOLD,18);
            		
            Phrase titulo=new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFactura Viajes ",fontTitulo);
            document.add(titulo);
            
            Phrase lineas=new Phrase("\n---------------------------------------------------------------------------------------------");
            document.add(lineas);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormateada = dateFormat.format(this.date);
            Phrase fecha=new Phrase("\n\nFecha del viaje: "+fechaFormateada);
            document.add(fecha);
            
            
            Phrase from=new Phrase("\n\nOrigen: "+this.from);
            document.add(from);
            
            Phrase to=new Phrase("\n\nDestino: "+this.to);
            document.add(to);
            
            Phrase nPlacesTotales=new Phrase("\n\nPlazas disponibles a la hora de hacer la compra: "+this.nPlacesTotales);
            document.add(nPlacesTotales);
            
            Phrase numeroRide=new Phrase("\n\nNumero de viaje "+this.rideNumber);
            document.add(numeroRide);  

            Phrase nPlazasS=new Phrase("\n\nNumero de plazas adquiridas "+this.numPlazasSolicitadas);
            document.add(nPlazasS);  
            
            document.add(lineas);
            
            Phrase datosViajero=new Phrase("\nDatos del viajero  ");
            document.add(datosViajero);  
            
            document.add(lineas);
            
            Phrase datosViajero1=new Phrase("\n\tNombre: "+this.viajero.getName()+"\n\tEmail: "+this.viajero.getEmail());
            document.add(datosViajero1);
            
            document.add(lineas);
            
            
            Phrase datosConductor=new Phrase("\n\nDatos del condcutor ");
            document.add(datosConductor);   
            
            document.add(lineas);
            
            Phrase datosConductor1=new Phrase("\n\tNombre: "+this.conductor.getName()+"\n\tEmail: "+this.conductor.getEmail());
            document.add(datosConductor1);
            
            document.add(lineas);
            
            Phrase precio=new Phrase("\n\nTotal a pagar: "+this.price+"€");
            document.add(precio);

                    
            JOptionPane.showMessageDialog(null, "Factura creada exitodamente,guarde su factura antes de realizar otra compra");
            
            document.close();
            
            
        }
        catch (DocumentException de) {
            //System.err.println(de.getMessage());
            System.out.println("Error docuemnt Exception"+de);
        } catch (FileNotFoundException ex) {
            //System.err.println(ex.getMessage());
        	System.out.println("Error FileNotFound Exception"+ex);
        }
  
	}
	
	public void generarReporteCSV() {
		try{
            BufferedWriter outStream = new BufferedWriter(new FileWriter("Reporte.csv", true));
            
         // Formatear la fecha utilizando SimpleDateFormat
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormateada = dateFormat.format(this.date);
            
            outStream.write(
            		this.id+" , " +
                    this.rideNumber + " , " +
                    this.from + " , " +
                    this.to + " , " +
                    this.nPlacesTotales + " , " +
                    fechaFormateada + " , " +
                    this.price + " , " +
                    this.conductor.getName() + " , " +
                    this.conductor.getEmail() + " , " +
                    this.viajero.getName() + " , " +
                    this.viajero.getEmail() + " , " +
                    this.numPlazasSolicitadas + "\n"
                );

            outStream.close();
            
             System.out.println("CSV guardado de manera exitosa");
            
        }catch(IOException exception){
        	System.out.println("El CSV no ha podido guardar el reporte");
        }
	}
	
	
}
