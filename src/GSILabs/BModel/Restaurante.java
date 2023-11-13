package GSILabs.BModel;

import static GSILabs.BSystem.BusinessSystem.formatearXML;
import java.util.Objects;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase Restaurante
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */

public class Restaurante extends Local implements Reservable, XMLRepresentable{
    
    /**
     * Precio del menú del restaurante.
     */
    private double precioMenu;
    /**
     * Capacidad de personas del restaurante.
     */
    private Integer capacidad;
    /**
     * Capacidad de personas por mesa del restaurante.
     */
    private Integer capacidadMesa;
    private ArrayList<Reserva> listaReserva;
    //listaReserva se inicia en Reservable

    /**
     * Constructor Restaurante
     * @param nombre Nombre del restaurante
     * @param direccion Dirección del restaurante
     * @param descripcion Descripción del restaurante
     * @param propietario Propietario del restaurante
     * @param precioMenu Precio del menu del restaurante
     * @param capacidad Capacidad del restaurante
     * @param capacidadMesa Capacidad de las mesas del restaurante
     */
    public Restaurante(String nombre, Direccion direccion, String descripcion, Propietario propietario, double precioMenu, Integer capacidad, Integer capacidadMesa){
        super(nombre, direccion, descripcion, tipoLocal.RESTAURANTE, propietario);
        this.precioMenu = precioMenu;
        this.capacidad = capacidad;
        this.capacidadMesa = capacidadMesa;
        this.listaReserva = new ArrayList<Reserva>();
        // listaReserva se incia en Reservable
    }
    
    @Override
    public ArrayList<Reserva> getListaReserva() {
        return listaReserva;
    }

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public double getPrecioMenu(){
        return this.precioMenu;
    }
    
    public Integer getCapacidad(){
        return this.capacidad;
    }
    
    public Integer getCapacidadMesa(){
        return this.capacidadMesa;
    }
    
    public void setPrecioMenu(double precioMenu){
        this.precioMenu = precioMenu;
    }
    
    public void setCapacidad(Integer capacidad){
        this.capacidad=capacidad;
    }
    
    public void setCapacidadMesa(Integer capacidadMesa){
        this.capacidadMesa=capacidadMesa;
    }
//</editor-fold>

    
    @Override
    public int hashCode() {
        return Objects.hash(getDireccion());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (getClass() != obj.getClass())
          return false;
        Restaurante other = (Restaurante) obj;
        return Objects.equals(getDireccion(), other.getDireccion());
    }
    
    @Override
    public String toString() {

        //String de Propietarios
        String strPropietarios = "";
        for(int i = 0; i < this.getPropietarios().size(); i++){
            strPropietarios = strPropietarios + "propietario=" + this.getPropietarios().get(i).toString();
            if(i != this.getPropietarios().size() - 1)
                strPropietarios = strPropietarios + ", ";
        }

        //String de Reservas
        String strReservas = "";
        for(int i = 0; i < this.listaReserva.size(); i++){
            strReservas = strReservas + "reserva=" + this.listaReserva.get(i).toString();
            if(i != this.listaReserva.size() - 1)
                strReservas = strReservas + ", ";
        }


        String salida = "Restaurante{" + "nombre=" + this.getNombre() + ", dirección=" + this.getDireccion().toString() 
                + ", descripción=" + this.getDescripcion() + ", tipo=" + this.getTipo().toString();

        if(getPropietarios().size() > 0){
            salida = salida + ", " + strPropietarios; 
        }
        salida = salida + ", precioMenu=" + this.precioMenu 
                        + ", capacidad=" + this.capacidad 
                        + ", capacidad Mesa=" + this.capacidadMesa;
        if(listaReserva.size() > 0){
            salida = salida + ", " + strReservas; 
        }

        return salida + "}";


    }

    /**
     * Generación de una representación XML del objeto Restaurante.
     * @return Representación XML del objeto en forma de cadena
     */
    @Override
    public String toXML() {
        String[] partes;
        String xmlData = "";
        //Cabecera
        xmlData += "<Restaurante>\n";
        //Nombre
        xmlData += "<nombre>" + this.getNombre() + "</nombre>\n";
        //Direccion
        partes = this.getDireccion().toXML().split("<Direccion>", 2);
        xmlData += "<Direccion>" + partes[1];
        //Descripcion
        xmlData += "<descripcion>" + this.getDescripcion() + "</descripcion>\n";
        // Propietario
        for(int i = 0; i<this.getPropietarios().size(); i++){
            partes = this.getPropietarios().get(i).toXML().split("<Propietario>", 2);
            xmlData += "<Propietario>" + partes[1];
        }
        //Reservas
        for(int i = 0; i < this.listaReserva.size(); i++){
            partes = this.listaReserva.get(i).toXML().split("<Reserva>", 2);
            xmlData += "<Reserva>" + partes[1];
        }
        //Precio menu
        xmlData += "<precioMenu>" + this.getPrecioMenu() + "</precioMenu>\n";
        //Capacidad
        xmlData += "<capacidad>" + this.getCapacidad() + "</capacidad>\n";
        //Capacidad mesa
        xmlData += "<capacidadMesa>" + this.getCapacidadMesa() + "</capacidadMesa>\n";
        //Cierre
        xmlData += "</Restaurante>\n";
        return formatearXML(xmlData);
    }

    /**
     * Guardado de la representación XML del objeto Restaurante
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
     * Guardado de la representación XML del objeto Restaurante
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
