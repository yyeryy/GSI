package GSILabs.BModel;

import java.time.LocalDate;
import java.util.Objects;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase Propietario
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Propietario extends Usuario implements XMLRepresentable{
    
    /**
     * Constructor Propietario
     * @param n Nick del propietario
     * @param c Contraseña del propietario
     * @param f Fecha de Nacimiento del propietario
     */
    public Propietario(String n, String c, LocalDate f) {
        super(n, c, f, tipoUsuario.PROPIETARIO);
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
        Propietario other = (Propietario) obj;
        return Objects.equals(getNick(), other.getNick());
    }

    @Override
    public String toString() {
        return "Propietario{" + "nick=" + this.getNick() + ", contraseña=" + this.getContraseña() + ", fecha_de_nacimiento=" + this.getFechaNacimiento().toString() + "}";
    }

    
    @Override
    public String toXML() {
        String xmlData = "";
        // Cabecera
        xmlData += "<Propietario>\n";
        // Nick
        xmlData += "<nick>" + this.getNick() + "</nick>\n";
        // Contraseña
        xmlData += "<contraseña>" + this.getContraseña() + "</contraseña>\n";
        // Fecha de Nacimiento
        xmlData += "<fechaNacimiento>" + this.getFechaNacimiento() + "</fechaNacimiento>\n";
        // Cierre
        xmlData += "</Propietario>\n";
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
