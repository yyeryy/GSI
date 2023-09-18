/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import java.util.ArrayList;

/**
 *
 * @author 34636
 */
public class Restaurante extends Local implements Reservable{
    private double precioMenu;
    private Integer capacidad;
    private Integer capacidadMesa;
    private ArrayList<ClienteReserva> reservas;

    public Restaurante(double precioMenu, Integer capacidad, Integer capacidadMesa, String nombre, Direccion direccion, String descripcion, tipoLocal tipoLocal){
        super(nombre, direccion, descripcion, tipoLocal);
        this.precioMenu = precioMenu;
        this.capacidad = capacidad;
        this.capacidadMesa = capacidadMesa;
        this.reservas = new ArrayList<ClienteReserva>();

    }
    
    public Restaurante(String nombre, Direccion direccion, String descripcion, tipoLocal tipoLocal){
        super(nombre, direccion, descripcion, tipoLocal);
        this.precioMenu = 0;
        this.capacidad = 0;
        this.capacidadMesa = 0;
        this.reservas = new ArrayList<ClienteReserva>();
    }
    
    public double getPrecioMenu(){
        return this.precioMenu;
    }

    public Integer capacidad(){
        return this.capacidad;
    }

    public Integer capacidadMesa(){
        return this.capacidadMesa;
    }

    public void setPrecioMenu(double precioMenu){
        this.precioMenu = precioMenu;
    }

    public void setCapacidad(Integer capacidad){
        this.capacidad=capacidad;
    }

    public void setCapacidadMesa(Integer capacidadMesa){
        this.capacidadMesa=capacidadMesa;
    }
}
