package GSILabs.BModel;

import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import static GSILabs.BSystem.BusinessSystem.formatearXML;
import java.time.LocalDate;
import java.util.Objects;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase Cliente
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Cliente extends Usuario implements XMLRepresentable{

    /**
     * Constructor Cliente
     * @param n Nick del cliente
     * @param c Contraseña del cliente
     * @param f Fecha de nacimiento del cliente
     */
    public Cliente(String n, String c, LocalDate f) {
        super(n, c, f, CLIENTE);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getNick());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (getClass() != obj.getClass())
          return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(getNick(), other.getNick());
    }

    @Override
    public String toString() {
        return "Cliente{" + "nick=" + this.getNick() + ", contraseña=" + this.getContraseña() + ", fecha_de_nacimiento=" + this.getFechaNacimiento().toString() + "}";
    }

    /**
     * Generación de una representación XML de Cliente.
     * @return Representación XML del objeto en forma de cadena
     */
    @Override
    public String toXML() {
        String xmlData = "";
        //Cabecera
        xmlData += "<Cliente>\n";
        //Nick
        xmlData += "<nick>" + this.getNick() + "</nick>\n";
        //Contraseña
        xmlData += "<contraseña>" + this.getContraseña() + "</contraseña>\n";
        //Fecha de naciemiento
        xmlData += "<fechaNacimiento>" + this.getFechaNacimiento() + "</fechaNacimiento>\n";
        //Cierre
        xmlData += "</Cliente>\n";
        return formatearXML(xmlData);
    }

    /**
     * Guardado de la representación XML del objeto Cliente
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
     * Guardado de la representación XML del objeto Cliente
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
