/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

/**
 *
 * @author 34636
 */
public class Pub extends Local{
    private String horaApertura;
    private String horaClausura;

    public Pub(String horaApertura, String horaClausura) {
        this.horaApertura = horaApertura;
        this.horaClausura = horaClausura;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public String getHoraClausura() {
        return horaClausura;
    }
}
