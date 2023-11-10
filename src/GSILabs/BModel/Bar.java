/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase Bar
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Bar extends Local implements Reservable, XMLRepresentable{
    private List<String> especialidades;
    // listaReserva se incia en Reservable

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
        // listaReserva se incia en Reservable
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

        // String de Propietarios
        String strPropietarios = "";
        for(int i = 0; i < this.getPropietarios().size(); i++){
            strPropietarios = strPropietarios + "propietario=" + this.getPropietarios().get(i).toString();
            if(i != this.getPropietarios().size() - 1)
                strPropietarios = strPropietarios + ", ";
        }

        // String de Especialidades
        String strEspecialidades = "";
        for(int i = 0; i < this.especialidades.size(); i++){
            strEspecialidades = strEspecialidades + "especialidad=" + this.especialidades.get(i).toString();
            if(i != this.especialidades.size() - 1)
                strEspecialidades = strEspecialidades + ", ";
        }

        // String de Reservas
        String strReservas = "";
        for(int i = 0; i < this.listaReserva.size(); i++){
            strReservas = strReservas + "reserva=" + this.listaReserva.get(i).toString();
            if(i != this.listaReserva.size() - 1)
                strReservas = strReservas + ", ";
        }


        String salida = "Bar{" + "nombre=" + this.getNombre() + ", dirección=" + this.getDireccion().toString() + ", descripción=" + this.getDescripcion() + ", tipo=" + this.getTipo().toString();


        if(getPropietarios().size() > 0){
            salida = salida + ", " + strPropietarios; 
        }
        if(this.especialidades.size() >0){
            salida = salida + ", " + strEspecialidades; 
        }
        if(this.listaReserva.size() > 0){
            salida = salida + ", " + strReservas; 
        }

        return salida + "}";
    }

    @Override
    public String toXML() {
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        xmlData += "<Bar>\n";
        xmlData += "<nombre>" + this.getNombre() + "</nombre>\n";
        xmlData += "" + this.getDireccion().toXML() + "\n";
        xmlData += "<descripcion>" + this.getDescripcion() + "</descripcion>\n";
        for(int i = 0; i<this.getPropietarios().size(); i++){
            xmlData += "" + this.getPropietarios().get(i).toXML() + "\n";
        }/*Y reservas???? pista, utilizar this.listaReserva.size() y lo mismo en Restaurante*/

        for(int i = 0;i<this.getEspecialidades().size();i++){
            xmlData += "<especialidad>" + this.getEspecialidades().get(i) + "</especialidad>\n";
        }
        xmlData += "</Bar>";
        return xmlData;
    }

    @Override
    public boolean saveToXML(File f) {
        try {
        String xmlData = toXML();
            try (FileWriter writer = new FileWriter(f)) {
                writer.write(xmlData);
            }
        return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean saveToXML(String filePath) {
        File file = new File(filePath);
        return saveToXML(file);
    }
}