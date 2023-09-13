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
    
    Review(Local local, int valoracion, String comentario, LocalDate fechaReview) {
        this.local = local;
        this.comentario = comentario;
        this.valoracion = valoracion;
        this.fechaReview = fechaReview;
    }
}
