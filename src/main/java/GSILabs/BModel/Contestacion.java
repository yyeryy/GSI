/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import java.time.LocalDate;

/**
 *
 * @author 34636
 */
public class Contestacion {
    
    private String comentario;
    private LocalDate fechaReview;
    private Local local;

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
