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
public class Review {

    private int valoracion;
    private String comentario;
    private LocalDate fechaReview;
    private Local local;
    private Usuario usuario;
    private Contestacion contestacion;

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

    
}
