/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 34636
 */
public class Bar extends Local implements Reservable{
    private List<String> especialidades;
    private ArrayList<ClienteReserva> listaReserva ;

    public Bar(String nombre, Direccion direccion, String descripcion, tipoLocal tipoLocal) {
        super(nombre, direccion, descripcion, tipoLocal);
        this.especialidades = new ArrayList<>();
        this.listaReserva = new  ArrayList<ClienteReserva>();
    }

    public void agregarEspecialidad(String especialidad) {
        especialidades.add(especialidad);
    }

    public void mostrarEspecialidades() {
        System.out.println("Especialidades de " + super.getNombre() + ":");
        for (String especialidad : especialidades) {
            System.out.println("- " + especialidad);
        }
    }
}