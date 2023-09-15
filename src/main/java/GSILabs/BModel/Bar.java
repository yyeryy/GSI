/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

/**
 *
 * @author 34636
 */
public class Bar extends Local implements Reservable{
    private List<String> especialidades;

    public Bar() {
        this.especialidades = new ArrayList<>();
    }

    public void agregarEspecialidad(String especialidad) {
        especialidades.add(especialidad);
    }

    public void mostrarEspecialidades() {
        System.out.println("Especialidades de " + nombre + ":");
        for (String especialidad : especialidades) {
            System.out.println("- " + especialidad);
        }
    }

    public static void main(String[] args) {
        miBar.agregarEspecialidad("Cerveza artesanal");
        miBar.agregarEspecialidad("Cócteles de autor");
    }
}
