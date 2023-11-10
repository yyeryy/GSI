/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import java.util.Objects;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase Pub
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Pub extends Local implements XMLRepresentable{
    private String horaApertura;
    private String horaClausura;

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

    @Override
    public String toXML() {
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        xmlData += "<Pub>\n";
        xmlData += "    <horaApertura>" + this.getHoraApertura() + "</horaApertura>\n";
        xmlData += "    <horaClausura>" + this.getHoraClausura() + "</horaClausura>\n";
        xmlData += "    <nombre>" + this.getNombre() + "</nombre>\n";
        xmlData += "    <direccion>" + this.getDireccion() + "</direccion>\n";
        xmlData += "    <descripcion>" + this.getDescripcion() + "</descripcion>\n";
        xmlData += "    <propietario>" + this.getPropietarios() + "</propietario>\n";
        xmlData += "</Pub>\n";
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
