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
    private ArrayList<Reserva> reservas;


    public Restaurante(String nombre, Direccion direccion, String descripcion, double precioMenu, Integer capacidad, Integer capacidadMesa){
        super(nombre, direccion, descripcion, tipoLocal.RESTAURANTE);
        this.precioMenu = precioMenu;
        this.capacidad = capacidad;
        this.capacidadMesa = capacidadMesa;
        this.reservas = new ArrayList<Reserva>();
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
