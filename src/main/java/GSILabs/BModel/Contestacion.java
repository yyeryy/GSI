/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import java.time.LocalDate;

/**
 * Clase Contestacion
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Contestacion {
    
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
    
}
