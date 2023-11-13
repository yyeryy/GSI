package GSILabs.BModel;

import static GSILabs.BSystem.BusinessSystem.formatearXML;
import java.time.LocalDate;
import java.util.Objects;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase Contestacion
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Contestacion implements XMLRepresentable{
    
    /**
     * Comentario que realiza un propietario a una review.
     */
    private String comentario;
    /**
     * Fecha en la que un propietario realiza la contestación a una review.
     */
    private LocalDate fechaReview;
    /**
     * Local del cual es el propietario y al cual se ha hecho la review a contestar.
     */
    private Local local;

    /**
     * Constructor Contestacion
     * @param comentario Comentario que realiza un propietario a una review
     * @param fechaReview Fecha en la que un propietario realiza la contestación a una review
     * @param local Local del cual es el propietario y al cual se ha hecho la review a contestar
     */
    public Contestacion(String comentario, LocalDate fechaReview, Local local) {
        this.comentario = comentario;
        this.fechaReview = fechaReview;
        this.local = local;
    }

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaReview() {
        return fechaReview;
    }

    public void setFechaReview(LocalDate fechaReview) {
        this.fechaReview = fechaReview;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }
    //</editor-fold>

    @Override
    public int hashCode() {
        return Objects.hash(fechaReview, local, comentario);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Contestacion other = (Contestacion) obj;
        return Objects.equals(fechaReview, other.fechaReview) &&
               Objects.equals(local, other.local) &&
               Objects.equals(comentario, other.comentario);
    }
    
    @Override
    public String toString() {
        return "Contestacion{" + "comentario=" + comentario + ", fechaReview=" + fechaReview.toString() + ", local=" + local.toString() + '}';
    }

    /**
     * Generación de una representación XML de Contestacion.
     * @return Representación XML del objeto en forma de cadena
     */
    @Override
    public String toXML() {
        String[] partes;
        String xmlData = "";
        //Cabecera
        xmlData += "<Contestacion>\n";
        //Comentario
        xmlData += "<comentario>" + this.getComentario() + "</comentario>\n";
        //Fecha
        xmlData += "<fecha>" + this.getFechaReview() + "</fecha>\n";
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
        //Cierre
        xmlData += "</Contestacion>\n";
        return formatearXML(xmlData);
    }

    /**
     * Guardado de la representación XML del objeto Contestacion
     * en el fichero indicado por parámetro.
     * @param f Fichero XML en el que se guarda la representación XML del objeto
     * @return Booleano que indica si el fichero se ha guardado exitosamente.
     */
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

    /**
     * Guardado de la representación XML del objeto Contestación
     * en un fichero XML que se almacenará en la ruta indicada por parámetro.
     * @param filePath Ruta del fichero donde se va a guardar la reprentación XML.
     * @return Booleano que indica si el fichero se ha guardado exitosamente.
     */
    @Override
    public boolean saveToXML(String filePath) {
        File file = new File(filePath);
        return saveToXML(file);
    }
    
    
}
