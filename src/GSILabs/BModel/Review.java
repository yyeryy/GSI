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
 * Clase Review
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */

public class Review implements XMLRepresentable{

    /**
     * Valoración que un usuario hace a un local.
     */
    private int valoracion;
    /**
     * Comentario que un usuario hace a un local.
     */
    private String comentario;
    /**
     * Fecha en la que el usuario realiza la review.
     */
    private LocalDate fechaReview;
    /**
     * Local al cual el usuario realiza la review.
     */
    private Local local;
    /**
     * Usuario que realiza la review a un local.
     */
    private Usuario usuario;
    /**
     * Contestación que recibe la review.
     */
    private Contestacion contestacion;

    /**
     * Constructor Review
     * @param valoracion Valoración que un usuario realiza a un local.
     * @param comentario Comentario que un usuario realiza a un local.
     * @param fechaReview Fecha en la que un usuario realiza la review a un local.
     * @param local Local al cual un usuario realiza la review.
     * @param usuario Usuario que realiza la review a un local.
     */
    public Review(int valoracion, String comentario, LocalDate fechaReview, Local local, Usuario usuario) {
        this.valoracion = valoracion;
        this.comentario = comentario;
        this.fechaReview = fechaReview;
        this.local = local;
        this.usuario = usuario;
    }

    

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public int getValoracion() {
        return valoracion;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getFechaReview() {
        return fechaReview;
    }

    public Local getLocal() {
        return local;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public Contestacion getContestacion() {
        return contestacion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setFechaReview(LocalDate fechaReview) {
        this.fechaReview = fechaReview;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setContestacion(Contestacion contestacion) {
        this.contestacion = contestacion;
    }
//</editor-fold>
    
    @Override
    public int hashCode() {
        return Objects.hash(fechaReview, local, usuario);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Review other = (Review) obj;
        return Objects.equals(fechaReview, other.fechaReview) &&
               Objects.equals(local, other.local) &&
               Objects.equals(usuario, other.usuario);
    }

    
    @Override
    public String toString() {
        String salida = "Review{" + "valoracion=" + valoracion + ", comentario=" + comentario + ", fechaReview=" + fechaReview.toString() + ", local=" + local.toString() + ", usuario=" + usuario.toString();
    
        if(this.contestacion != null){
            salida = salida + ", contestacion=" + contestacion.toString();
        }
        return salida + '}';
    }

    @Override
    public String toXML() {
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        xmlData += "<Review>\n";
        xmlData += "    <valoracion>" + this.getValoracion() + "</valoracion>\n";
        xmlData += "    <comentario>" + this.getComentario() + "</comentario>\n";
        xmlData += "    <fecha>" + this.getFechaReview() + "</fecha>\n";
        xmlData += "    <local>" + this.getLocal() + "</local>\n";
        xmlData += "    <usuario>" + this.getUsuario() + "</usuario>\n";
        xmlData += "    <contestacion>" + this.getContestacion() + "</contestacion>\n";
        xmlData += "</Review>\n";
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
