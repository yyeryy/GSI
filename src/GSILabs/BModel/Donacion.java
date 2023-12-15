/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import static GSILabs.BSystem.BusinessSystem.formatearXML;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author Ivan1
 */
public class Donacion {
    
    /**
     * Local que hace la Donacion.
     */
    private Local local;
    /**
     * Nombre del Producto a Donar.
     */
    private String nombreProducto;
    /**
     * Cantidad de Producto que se dona.
     */
    private int cantidadProducto;
    /**
     * Usuario que reserva la Donacion.
     */
    private Usuario usuario;


    /**
     * Constructor Donacion
     * @param local Local que hace la Donacion.
     * @param nombreProducto Nombre del Producto a Donar.
     * @param cantidadProducto Cantidad de Producto que se dona.
     */
    public Donacion(Local local, String nombreProducto, int cantidadProducto) {
        this.local = local;
        this.nombreProducto = nombreProducto;
        this.cantidadProducto = cantidadProducto;
        this.usuario = null; // No tiene usuario 
    }


    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public Local getLocal() {
        return local;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
//</editor-fold>



    @Override
    public int hashCode() {
        return Objects.hash(local, usuario);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Donacion other = (Donacion) obj;
        return Objects.equals(nombreProducto, other.nombreProducto) &&
               Objects.equals(local, other.local) &&
               Objects.equals(usuario, other.usuario);
    }

    
    @Override
    public String toString() {
        String salida = "Donacion{" + "local=" + local.toString() + ", nombre=" + nombreProducto + ", cantidad=" + cantidadProducto;
    
        if(this.usuario != null){
            salida = salida + ", usuario=" + usuario.toString();
        }
        return salida + '}';
    }

    /**
     * Generación de una representación XML del objeto Donacion.
     * @return Representación XML del objeto en forma de cadena
     */
    public String toXML() {
        String[] partes;
        String xmlData = "";
        //Cabecera
        xmlData += "<Donacion>\n";
        //NombreProducto
        xmlData += "<nombreProducto>" + this.getNombreProducto() + "</nombreProducto>\n";
        //CantidadProducto
        xmlData += "<cantidadProducto>" + this.getCantidadProducto() + "</cantidadProducto>\n";

        //Local
        partes = this.getLocal().toXML().split("<Local>", 2);
        if(partes.length == 2){
            xmlData += "<Local>" + partes[1];
        }else{
            partes = this.getLocal().toXML().split("<Bar>", 2);
            if(partes.length == 2){
                xmlData += "<Bar>" + partes[1];
            } else{
                partes = this.getLocal().toXML().split("<Restaurante>", 2);
                if(partes.length == 2){
                    xmlData += "<Restaurante>" + partes[1];
                }else{
                    partes = this.getLocal().toXML().split("<Pub>", 2);
                    if(partes.length == 2){
                        xmlData += "<Pub>" + partes[1];
                    }
                }
            }
        }

        //Usuario
        partes = this.getUsuario().toXML().split("<Usuario>", 2);
        if(partes.length == 2){
            xmlData += "<Usuario>" + partes[1];
        }else{
            partes = this.getUsuario().toXML().split("<Cliente>", 2);
            if(partes.length == 2){
                xmlData += "<Cliente>" + partes[1];
            }else{
            partes = this.getUsuario().toXML().split("<Propietario>", 2);
            if(partes.length == 2){
                xmlData += "<Propietario>" + partes[1];
            }
            }
        }

        //Cierre
        xmlData += "</Donacion>\n";
        return formatearXML(xmlData);
    }

    /**
     * Guardado de la representación XML del objeto Donacion
     * en el fichero indicado por parámetro.
     * @param f Fichero XML en el que se guarda la representación XML del objeto
     * @return Booleano que indica si el fichero se ha guardado exitosamente.
     */
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

    /**
     * Guardado de la representación XML del objeto Donacion
     * en un fichero XML que se almacenará en la ruta indicada por parámetro.
     * @param filePath Ruta del fichero donde se va a guardar la reprentación XML.
     * @return Booleano que indica si el fichero se ha guardado exitosamente.
     */
    public boolean saveToXML(String filePath) {
        File file = new File(filePath);
        return saveToXML(file);
    }







}