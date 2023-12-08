package GSILabs.BModel;

import static GSILabs.BSystem.BusinessSystem.formatearXML;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Clase Reserva
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 * Tanto los restaurantes como los Bares son Reservables. El sistema debe poder almacenar Reservas
 * de Clientes para Bares o Restaurantes. Cada Reserva incluye únicamente la fecha y hora en que
 * se efectuará, así como un posible porcentaje de descuento.
 */
public class Reserva implements XMLRepresentable, Serializable{

    /**
     * Un cliente no puede tener la misma fecha en una reserva, por lo que se 
     * puede utilizar la combinacion de los atributos como una supuesta ID.
     */
    
    /**
     * Cliente que ha realizado la reserva.
     */
    private Cliente cliente;
    /**
     * Fecha de la reserva.
     */
    private LocalDate fecha;
    /**
     * Hora de la reserva.
     */
    private LocalTime hora;
    /**
     * Descuento aplicado en la reserva.
     */
    private int descuento;

    public Reserva(){
        super();
    }
    
    /**
     * Constructor Reserva
     * @param cliente Cliente que quiere reservar
     * @param fecha Fecha de la reserva
     * @param hora Hora de la reserva
     * @param descuento Descuento de la reserva
     */
    public Reserva(Cliente cliente, LocalDate fecha, LocalTime hora, int descuento) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.hora = hora;
        this.descuento = descuento;
    }

    public Cliente getCliente() {
        return cliente;
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }  


    @Override
    public int hashCode() {
        return Objects.hash(cliente,fecha);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (getClass() != obj.getClass())
          return false;
        Reserva other = (Reserva) obj;
        boolean result = (
            Objects.equals(cliente, other.cliente)&&
            Objects.equals(fecha, other.fecha)
        );
        return result;
    }

    @Override
    public String toString() {
        return "Reserva{" + "cliente=" + this.cliente.toString() + ", fechaReserva=" + this.fecha.toString() + ", hora=" + this.hora.toString() + ", descuento=" + this.descuento + "%" + '}';
    }

    /**
     * Generación de una representación XML del objeto Reserva.
     * @return Representación XML del objeto en forma de cadena
     */
    @Override
    public String toXML() {
        String[] partes;
        String xmlData = "";
        xmlData += "<Reserva>\n";
        partes = this.getCliente().toXML().split("<Cliente>", 2);
        xmlData += "<Cliente>" + partes[1];
        xmlData += "<hora>" + this.getHora() + "</hora>\n";
        xmlData += "<fecha>" + this.getFecha() + "</fecha>\n";
        xmlData += "<descuento>" + this.getDescuento() + "</descuento>\n";
        xmlData += "</Reserva>\n";
        return formatearXML(xmlData);
    }

    /**
     * Guardado de la representación XML del objeto Reserva
     * en el fichero indicado por parámetro.
     * @param f Fichero XML en el que se guarda la representación XML del objeto
     * @return Booleano que indica si el fichero se ha guardado exitosamente.
     */
    @Override
    public boolean saveToXML(File f) {
        try {
        String xmlData = toXML();
            try (FileWriter writer = new FileWriter(f)) {
                writer.write(xmlData);
            }
        return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Guardado de la representación XML del objeto Reserva
     * en un fichero XML que se almacenará en la ruta indicada por parámetro.
     * @param filePath Ruta del fichero donde se va a guardar la reprentación XML.
     * @return Booleano que indica si el fichero se ha guardado exitosamente.
     */
    @Override
    public boolean saveToXML(String filePath) {
        File file = new File(filePath);
        return saveToXML(file);
    }
}