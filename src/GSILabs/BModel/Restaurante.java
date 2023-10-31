package GSILabs.BModel;

import java.util.ArrayList;
import java.util.Objects;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase Restaurante
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */

public class Restaurante extends Local implements Reservable, XMLRepresentable{
    private double precioMenu;
    private Integer capacidad;
    private Integer capacidadMesa;
    private ArrayList<Reserva> reservas;

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
        this.reservas = new ArrayList<Reserva>();
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
        return "Local{" + "nombre=" + this.getNombre() + ", direccion=" + this.getDireccion().toString() + ", descripcion=" + this.getDescripcion() + ", tipo=" + this.getTipo().toString() + ", propietarios=" + this.getPropietarios().toString() + ", precioMenu=" + this.precioMenu + ", capacidad=" + this.capacidad + ", capacidad Mesa=" + this.capacidadMesa + ", capacidad=" + this.reservas.toString() + '}';
    }

    @Override
    public String toXML() {
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        xmlData += "<Restaurante>\n";
        xmlData += "    <nombre>" + this.getNombre() + "</nombre>\n";
        xmlData += "    <direccion>" + this.getDireccion() + "</direccion>\n";
        xmlData += "    <descripcion>" + this.getDescripcion() + "</descripcion>\n";
        xmlData += "    <propietario>" + this.getPropietarios() + "</propietario>\n";
        xmlData += "    <precioMenu>" + this.getPrecioMenu() + "</precioMenu>\n";
        xmlData += "    <capacidad>" + this.getCapacidad() + "</capacidad>\n";
        xmlData += "    <capacidadMesa>" + this.getCapacidadMesa() + "</capacidadMesa>\n";
        xmlData += "</Restaurante>";
        return xmlData;
    }

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

    @Override
    public boolean saveToXML(String filePath) {
        File file = new File(filePath);
        return saveToXML(file);
    }
}
