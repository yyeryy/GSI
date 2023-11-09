package GSILabs.BModel;

import java.util.Objects;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
public class Direccion implements XMLRepresentable{

    public String localidad;
    public String provincia;
    public String calle;
    public int numero;
    
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

    @Override
    public String toXML() {
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        xmlData += "<Direccion>\n";
        xmlData += "    <localidad>" + this.getLocalidad() + "</localidad>\n";
        xmlData += "    <provincia>" + this.getProvincia() + "</provincia>\n";
        xmlData += "    <calle>" + this.getCalle() + "</calle>\n";
        xmlData += "    <numero>" + this.getNumero() + "</numero>\n";
        xmlData += "</Direccion>\n";
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
