package GSILabs.BModel;

import java.util.ArrayList;

/**
 * Clase Restaurante
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Restaurante extends Local implements Reservable{
    private double precioMenu;
    private Integer capacidad;
    private Integer capacidadMesa;
    private ArrayList<Reserva> reservas;

    /**
     * Constructor Restaurante
     * @param nombre Nombre del restaurante
     * @param direccion Dirección del restaurante
     * @param descripcion Descripción del restaurante
     * @param propietario Propietario del restaurante
     * @param precioMenu Precio del menu del restaurante
     * @param capacidad Capacidad del restaurante
     * @param capacidadMesa Capacidad de las mesas del restaurante
     */
    public Restaurante(String nombre, Direccion direccion, String descripcion, Propietario propietario, double precioMenu, Integer capacidad, Integer capacidadMesa){
        super(nombre, direccion, descripcion, tipoLocal.RESTAURANTE, propietario);
        this.precioMenu = precioMenu;
        this.capacidad = capacidad;
        this.capacidadMesa = capacidadMesa;
        this.reservas = new ArrayList<Reserva>();
    }
    
    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public double getPrecioMenu(){
        return this.precioMenu;
    }
    
    public Integer getCapacidad(){
        return this.capacidad;
    }
    
    public Integer getCapacidadMesa(){
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
//</editor-fold>

    @Override
    public String toString() {
        return "Local{" + "nombre=" + this.getNombre() + ", direccion=" + this.getDireccion().toString() + ", descripcion=" + this.getDescripcion() + ", tipo=" + this.getTipo().toString() + ", propietarios=" + this.getPropietarios().toString() + ", precioMenu=" + this.precioMenu + ", capacidad=" + this.capacidad + ", capacidad Mesa=" + this.capacidadMesa + ", capacidad=" + this.reservas.toString() + '}';
    }
}
