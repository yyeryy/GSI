/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase Bar
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Bar extends Local implements Reservable{
    private List<String> especialidades;
    private ArrayList<Reserva> reservas;

    /**
     * Constructor Bar
     * @param nombre Nombre del bar
     * @param direccion Dirección del bar
     * @param descripcion Descripción del bar
     * @param propietario Propietario del bar
     */
    public Bar(String nombre, Direccion direccion, String descripcion, Propietario propietario) {
        super(nombre, direccion, descripcion, tipoLocal.BAR, propietario);
        this.especialidades = new ArrayList<>();
        this.reservas = new ArrayList<Reserva>();
    }

    /**
     * Función que agrega especialidades al bar.
     * @param especialidad Especialidad del bar
     */
    public void agregarEspecialidad(String especialidad) {
        especialidades.add(especialidad);
    }
    
    public List<String> getEspecialidades() {
        return this.especialidades;
    }

    /**
     * Función que muestra las especialidades del bar.
     */
    public void mostrarEspecialidades() {
        System.out.println("Especialidades de " + super.getNombre() + ":");
        for (String especialidad : especialidades) {
            System.out.println("- " + especialidad);
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getDireccion());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (getClass() != obj.getClass())
          return false;
        Bar other = (Bar) obj;
        return Objects.equals(getDireccion(), other.getDireccion());
    }

    @Override
    public String toString() {
        return "Local{" + "nombre=" + this.getNombre() + ", direccion=" + this.getDireccion().toString() + ", descripcion=" + this.getDescripcion() + ", tipo=" + this.getTipo().toString() + ", propietarios=" + this.getPropietarios().toString() +  ", especialidades=" + this.especialidades.toString() + ", reservas=" + this.reservas.toString() + "}";
    }
}