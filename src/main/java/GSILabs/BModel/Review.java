/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import java.time.LocalDate;

/**
 * Clase Review
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Review {

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

    
}
