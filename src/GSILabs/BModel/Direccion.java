package GSILabs.BModel;

import static GSILabs.BSystem.BusinessSystem.formatearXML;
import java.util.Objects;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Clase Direccion
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 * El sistema almacena información de locales de ocio en España. Un local tienen un nombre y está
 * en una localización concreta. (Localidad, Provincia, Calle y Número). No puede haber dos locales
 * en la misma dirección. Los locales pueden almacenar una breve descripción de no más de 300
 * caracteres.
 */
public class Direccion implements XMLRepresentable, Serializable{

    /**
     * Localidad del lugar.
     */
    public String localidad;
    /**
     * Provincia del lugar.
     */
    public String provincia;
    /**
     * Calle del lugar.
     */
    public String calle;
    /**
     * Numero de portal del lugar.
     */
    public int numero;
    
    public Direccion(){
    }
    
    /**
     * Constructor Direccion
     * @param l localidad donde se encuentra el local
     * @param p provincia donde está situado el local
     * @param c su calle
     * @param n el numero de portal
     */

    public Direccion(String l, String p, String c, int n) {
	this.localidad = l;
	this.provincia = p;
	this.calle = c;
	this.numero = n;
    }

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public String getLocalidad() {
	return localidad;
    }
    
    public void setLocalidad(String localidad) {
	this.localidad = localidad;
    }
    
    public String getProvincia() {
	return provincia;
    }
    
    public void setProvincia(String provincia) {
	this.provincia = provincia;
    }
    
    public String getCalle() {
	return calle;
    }
    
    public void setCalle(String calle) {
	this.calle = calle;
    }
    
    public int getNumero() {
	return numero;
    }
    
    public void setNumero(int numero) {
	this.numero = numero;
    }
//</editor-fold>
    
    @Override
    public int hashCode() {
        return Objects.hash(localidad, provincia, calle, numero);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (getClass() != obj.getClass())
          return false;
        Direccion other = (Direccion) obj;
        return Objects.equals(getLocalidad(), other.getLocalidad()) &&
                Objects.equals(getProvincia(), other.getProvincia()) &&
                Objects.equals(getCalle(), other.getCalle()) &&
                Objects.equals(getNumero(), other.getNumero());
    }
    
    @Override
    public String toString() {
        return "Dirección{" + "localidad=" + localidad + ", provincia=" + provincia + ", calle=" + calle + ", numero=" + numero + '}';
    }

    /**
     * Generación de una representación XML del objeto Direccion.
     * @return Representación XML del objeto en forma de cadena
     */
    @Override
    public String toXML() {
        String xmlData = "";
        // Cabecera
        xmlData += "<Direccion>\n";
        // Localidad
        xmlData += "<localidad>" + this.getLocalidad() + "</localidad>\n";
        // Provincia
        xmlData += "<provincia>" + this.getProvincia() + "</provincia>\n";
        // Calle
        xmlData += "<calle>" + this.getCalle() + "</calle>\n";
        // Numero
        xmlData += "<numero>" + this.getNumero() + "</numero>\n";
        // Cierre
        xmlData += "</Direccion>\n";
        return formatearXML(xmlData);
    }

    /**
     * Guardado de la representación XML del objeto Direccion
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
     * Guardado de la representación XML del objeto Dirección
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
