/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import java.time.LocalDate;
import java.util.Objects;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase Contestacion
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Contestacion implements XMLRepresentable{
    
    /**
     * Comentario que realiza un propietario a una review.
     */
    private String comentario;
    /**
     * Fecha en la que un propietario realiza la contestación a una review.
     */
    private LocalDate fechaReview;
    /**
     * Local del cual es el propietario y al cual se ha hecho la review a contestar.
     */
    private Local local;

    /**
     * Constructor Contestacion
     * @param comentario Comentario que realiza un propietario a una review
     * @param fechaReview Fecha en la que un propietario realiza la contestación a una review
     * @param local Local del cual es el propietario y al cual se ha hecho la review a contestar
     */
    public Contestacion(String comentario, LocalDate fechaReview, Local local) {
        this.comentario = comentario;
        this.fechaReview = fechaReview;
        this.local = local;
    }

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaReview() {
        return fechaReview;
    }

    public void setFechaReview(LocalDate fechaReview) {
        this.fechaReview = fechaReview;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }
    //</editor-fold>

    @Override
    public int hashCode() {
        return Objects.hash(fechaReview, local, comentario);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Contestacion other = (Contestacion) obj;
        return Objects.equals(fechaReview, other.fechaReview) &&
               Objects.equals(local, other.local) &&
               Objects.equals(comentario, other.comentario);
    }
    
    @Override
    public String toString() {
        return "Contestacion{" + "comentario=" + comentario + ", fechaReview=" + fechaReview.toString() + ", local=" + local.toString() + '}';
    }

    @Override
    public String toXML() {
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        xmlData += "<Contestacion>\n";
        xmlData += "    <comentario>" + this.getComentario() + "</comentario>\n";
        xmlData += "    <fechaReview>" + this.getFechaReview() + "</fechaReview>\n";
        xmlData += "    <local>" + this.getLocal() + "</local>\n";
        xmlData += "</Contestacion>\n";
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
