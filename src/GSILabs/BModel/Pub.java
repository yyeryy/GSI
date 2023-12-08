package GSILabs.BModel;

import static GSILabs.BSystem.BusinessSystem.formatearXML;
import java.util.Objects;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Clase Pub
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Pub extends Local implements XMLRepresentable, Serializable{
    
    /**
     * Hora de apertura del pub.
     */
    private String horaApertura;
    /**
     * Hora de clausura del pub.
     */
    private String horaClausura;

    public Pub(){
        
    }
    
    /**
     * Constructor Pub
     * @param horaApertura Hora de apertura del pub
     * @param horaClausura Hora de cierre del pub
     * @param nombre Nombre del pub
     * @param direccion Dirección del pub
     * @param descripcion Descripción del pub
     * @param propietario Propietario del pub
     */
    public Pub(String horaApertura, String horaClausura, String nombre, Direccion direccion, String descripcion, Propietario propietario) {
        super(nombre, direccion, descripcion, tipoLocal.PUB, propietario);
        this.horaApertura = horaApertura;
        this.horaClausura = horaClausura;
    }

    //<editor-fold defaultstate="collapsed" desc="getters adn setters">
    public String getHoraApertura() {
        return horaApertura;
    }
    
    
    public String getHoraClausura() {
        return horaClausura;
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
        Pub other = (Pub) obj;
        return Objects.equals(getDireccion(), other.getDireccion());
    }
    
    @Override
    public String toString() {

        // String de Propietarios
        String strPropietarios = "";
        for(int i = 0; i < this.getPropietarios().size(); i++){
            strPropietarios = strPropietarios + "propietario=" + this.getPropietarios().get(i).toString();
            if(i != this.getPropietarios().size() - 1)
                strPropietarios = strPropietarios + ", ";
        }

        String salida = "Pub{" + "nombre=" + this.getNombre() + ", dirección=" + this.getDireccion().toString() + ", descripción=" + this.getDescripcion() + ", tipo=" + this.getTipo().toString();

        if(getPropietarios().size() > 0){
            salida = salida + ", " + strPropietarios; 
        }

        return salida + ", hora Apertura=" + this.horaApertura + ", hora Clausura=" + this.horaClausura + '}';
        
    }

    /**
     * Generación de una representación XML de Pub.
     * @return Representación XML del objeto en forma de cadena
     */
    @Override
    public String toXML() {
        String[] partes;
        String xmlData = "";
        //Cabecera
        xmlData += "<Pub>\n";
        //Hora de Apertura
        xmlData += "<horaApertura>" + this.getHoraApertura() + "</horaApertura>\n";
        //Hora de Cierre
        xmlData += "<horaClausura>" + this.getHoraClausura() + "</horaClausura>\n";
        //Nombre
        xmlData += "<nombre>" + this.getNombre() + "</nombre>\n";
        //Direccion
        partes = this.getDireccion().toXML().split("<Direccion>", 2);
        xmlData += "<Direccion>" + partes[1];
        //Descripcion
        xmlData += "<descripcion>" + this.getDescripcion() + "</descripcion>\n";
        //Propietario
        for(int i = 0; i<this.getPropietarios().size(); i++){
            partes = this.getPropietarios().get(i).toXML().split("<Propietario>", 2);
            xmlData += "<Propietario>" + partes[1];
        }
        //Cierre
        xmlData += "</Pub>\n";
        return formatearXML(xmlData);
    }

    /**
     * Guardado de la representación XML del objeto Pub
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
     * Guardado de la representación XML del objeto Pub
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
