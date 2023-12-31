package GSILabs.BModel;

import static GSILabs.BSystem.BusinessSystem.formatearXML;
import java.time.LocalDate;
import java.util.Objects;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Clase Review
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Review implements XMLRepresentable, Serializable{

    /**
     * Valoración que un usuario hace a un local.
     */
    private int valoracion;
    /**
     * Comentario que un usuario hace a un local.
     */
    private String comentario;
    /**
     * Fecha en la que el usuario realiza la review.
     */
    private LocalDate fechaReview;
    /**
     * Local al cual el usuario realiza la review.
     */
    private Local local;
    /**
     * Usuario que realiza la review a un local.
     */
    private Usuario usuario;
    /**
     * Contestación que recibe la review.
     */
    private Contestacion contestacion;

    public Review(){
        
    }
    
    /**
     * Constructor Review
     * @param valoracion Valoración que un usuario realiza a un local.
     * @param comentario Comentario que un usuario realiza a un local.
     * @param fechaReview Fecha en la que un usuario realiza la review a un local.
     * @param local Local al cual un usuario realiza la review.
     * @param usuario Usuario que realiza la review a un local.
     */
    public Review(int valoracion, String comentario, LocalDate fechaReview, Local local, Usuario usuario) {
        this.valoracion = valoracion;
        this.comentario = comentario;
        this.fechaReview = fechaReview;
        this.local = local;
        this.usuario = usuario;
    }
    

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public int getValoracion() {
        return valoracion;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getFechaReview() {
        return fechaReview;
    }

    public Local getLocal() {
        return local;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public Contestacion getContestacion() {
        return contestacion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setFechaReview(LocalDate fechaReview) {
        this.fechaReview = fechaReview;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setContestacion(Contestacion contestacion) {
        this.contestacion = contestacion;
    }
//</editor-fold>
    
    @Override
    public int hashCode() {
        return Objects.hash(fechaReview, local, usuario);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Review other = (Review) obj;
        return Objects.equals(fechaReview, other.fechaReview) &&
               Objects.equals(local, other.local) &&
               Objects.equals(usuario, other.usuario);
    }

    
    @Override
    public String toString() {
        String salida = "Review{" + "valoracion=" + valoracion + ", comentario=" + comentario + ", fechaReview=" + fechaReview.toString() + ", local=" + local.toString() + ", usuario=" + usuario.toString();
    
        if(this.contestacion != null){
            salida = salida + ", contestacion=" + contestacion.toString();
        }
        return salida + '}';
    }

    /**
     * Generación de una representación XML del objeto Review.
     * @return Representación XML del objeto en forma de cadena
     */
    @Override
    public String toXML() {
        String[] partes;
        String xmlData = "";
        //Cabecera
        xmlData += "<Review>\n";
        //Valoracion
        xmlData += "<valoracion>" + this.getValoracion() + "</valoracion>\n";
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

        //Contestacion
        if(this.getContestacion() != null){
            partes = this.getContestacion().toXML().split("<Contestacion>", 2);
            xmlData += "<Contestacion>" + partes[1];
        }

        //Cierre
        xmlData += "</Review>\n";
        return formatearXML(xmlData);
    }

    /**
     * Guardado de la representación XML del objeto Review
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
     * Guardado de la representación XML del objeto Review
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
