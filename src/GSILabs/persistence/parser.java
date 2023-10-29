package GSILabs.persistence;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Pub;
import GSILabs.BModel.Reserva;
import GSILabs.BModel.Restaurante;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import java.io.File;
import java.io.IOException;

public class parser {
    // Bar
    public static Bar parseBar(String str) throws IOException {
        String strFiltrado = str.substring(4, str.length()-1); //Elimino Bar{ y }.
        String[] strTroceado = strFiltrado.split(", "); //Trocear las distintas partes
        
        // Atributos a almacenar
        String strNombre = null;
        String strDireccion = null;
        String strDescripcion = null;
        String strPropietario = null;
        
        // Campos del atributo Bar
        for(String trozo: strTroceado){
            String[] atributoValor = trozo.split("=");
            if("nombre".equals(atributoValor[0])){ strNombre = atributoValor[1];}
            else if("direccion".equals(atributoValor[0])){ strDireccion = atributoValor[1];}
            else if("descripcion".equals(atributoValor[0])){ strDescripcion = atributoValor[1];}
            else if("propietario".equals(atributoValor[0])){ strPropietario = atributoValor[1];}
            // especialidades
            // reservas
        }
        
        // Comprobar si los datos son validos
        if(strNombre == null || strDireccion == null || strDescripcion == null || strPropietario == null)
        {throw new IOException("Uno de los campos necesarios esta vacio");}
        
        // Crear objetos que se usan para crear bar
        Direccion direccion = parseDireccion(strDireccion);
        
        // Obtengo la lista de propietarios
        
        // Creo el objeto bar
        
        // Relleno el resto de campos usando otras funciones
        //      Especialidades
        //      Reservas
        
        // Devuelvo el objeto generado
        return null;
    }
    public static Bar parseBar(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Cliente
    public static Cliente parseCliente(String str){
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    public static Cliente parseCliente(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Contestacion
    public static Contestacion parseContestacion(String str){
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    public static Contestacion parseContestacion(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Direccion
    public static Direccion parseDireccion(String str){
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    public static Direccion parseDireccion(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Local
    public static Local parseLocal(String str){
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    public static Local parseLocal(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Propietario
    public static Propietario parsePropietario(String str){
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    public static Propietario parsePropietario(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Pub
    public static Pub parsePub(String str){
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    public static Pub parsePub(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Reserva
    public static Reserva parseReserva(String str){
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    public static Reserva parseReserva(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Restarurante
    public static Restaurante parseRestaurante(String str){
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    public static Restaurante parseRestaurante(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Review
    public static Review parseRevies(String str){
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    public static Review parseReview(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Usuario
    public static Usuario parseUsuario(String str){
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    public static Usuario parseUsuario(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
}
