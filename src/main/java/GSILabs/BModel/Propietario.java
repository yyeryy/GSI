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
public class Propietario extends Usuario{

    public Propietario(String n, String c, LocalDate f, tipoUsuario t) {
        super(n, c, f, t);
    }
    
    /*El dueño puede realizar una Contestación a una Review, 
    siempre y cuand oel local al que se refiera sea suyo.*/
    public void realizarContestacion() {

    }
}
