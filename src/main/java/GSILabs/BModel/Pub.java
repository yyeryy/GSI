/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

/**
 * Clase Pub
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Pub extends Local{
    private String horaApertura;
    private String horaClausura;

    /**
     * Constructor Pub
     * @param horaApertura Hora de apertura del pub
     * @param horaClausura Hora de cierre del pub
     * @param nombre Nombre del pub
     * @param direccion Dirección del pub
     * @param descripcion Descripción del pub
     */
    public Pub(String horaApertura, String horaClausura, String nombre, Direccion direccion, String descripcion) {
        super(nombre, direccion, descripcion, tipoLocal.PUB);
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
}
