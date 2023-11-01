package GSILabs.persistence;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Local.tipoLocal;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Pub;
import GSILabs.BModel.Reserva;
import GSILabs.BModel.Restaurante;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import GSILabs.BModel.Usuario.tipoUsuario;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        //String str = null;
        //String str = null;
        
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
    public static Cliente parseCliente(String str) throws IOException{
        String strFiltrado = str.substring(8, str.length()-1); //Elimino Cliente{ y }.
        String[] strTroceado = strFiltrado.split(", "); //Trocear las distintas partes
        
        // Atributos a almacenar
        String strNick = null;
        String strContraseña = null;
        String strFecha = null;
        
        // Campos del atributo Bar
        for(String trozo: strTroceado){
            String[] atributoValor = trozo.split("=");
            if(null != atributoValor[0])switch (atributoValor[0]) {
                case "nick":
                    strNick = atributoValor[1];
                    break;
                case "contraseña":
                    strContraseña = atributoValor[1];
                    break;
                case "fecha_de_nacimiento":
                    strFecha = atributoValor[1];
                    break;
                default:
                    break;
            }
        }
        // Comprobar si los datos son validos
        if(strNick == null || strContraseña == null || strFecha == null)
        {throw new IOException("Uno de los campos necesarios esta vacio");}
        
        // Crear objetos que se usan para crear propietario
        Cliente cliente = new Cliente(strNick, strContraseña, LocalDate.parse(strFecha));
        
        return cliente;
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
    public static Direccion parseDireccion(String str) throws IOException{
        String strFiltrado = str.substring(10, str.length()-1); //Elimino Dirección{ y }.
        String[] strTroceado = strFiltrado.split(", "); //Trocear las distintas partes
        
        // Atributos a almacenar
        String strLocalidad = null;
        String strProvincia = null;
        String strCalle = null;
        String strNumero = null;
        
        // Campos del atributo Bar
        for(String trozo: strTroceado){
            String[] atributoValor = trozo.split("=");
            if(null != atributoValor[0])switch (atributoValor[0]) {
                case "localidad":
                    strLocalidad = atributoValor[1];
                    break;
                case "provincia":
                    strProvincia = atributoValor[1];
                    break;
                case "calle":
                    strCalle = atributoValor[1];
                    break;
                case "numero":
                    strNumero = atributoValor[1];
                    break;
                default:
                    break;
            }
        }
        
        // Comprobar si los datos son validos
        if(strLocalidad == null || strProvincia == null || strCalle == null || strNumero == null)
        {throw new IOException("Uno de los campos necesarios esta vacio");}
        
        // Crear objetos que se usan para crear propietario
        Direccion direccion = new Direccion(strLocalidad, strProvincia, strCalle, Integer.parseInt(strNumero));
        
        return direccion;
        
    }
    public static Direccion parseDireccion(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Local
    public static Local parseLocal(String str) throws IOException{
        String strFiltrado = str.substring(6, str.length()-1); //Eliminar "Propietario{" y "}".
        String[] strTroceado = strFiltrado.split(", "); //Trocear las distintas partes
        
        // Atributos a almacenar
        String strNombre = null;
        String strDescripcion = null;
        String strTipoLocal = null;
        List<String> listaStrDirecciones = new ArrayList<>();
        List<String> listaStrPropietarios = new ArrayList<>();
        
        // Obtener String basicos
        for(String trozo: strTroceado)
        {
            String[] atributoValor = trozo.split("=");
            if(null != atributoValor[0]) switch (atributoValor[0]){
                case "nombre":
                    strNombre = atributoValor[1];
                    break;
                case "descripción":
                    strDescripcion = atributoValor[1];
                    break;
                case "tipo":
                    strTipoLocal = atributoValor[1];
                    break;
                default:
                    break;
            }
        }
        
        // Obtener strings de otros objetos
        listaStrDirecciones = (ArrayList<String>) extraerObjecto(strFiltrado, "Dirección");
        listaStrPropietarios = (ArrayList<String>) extraerObjecto(strFiltrado, "Propietario");
        
        // Comprobar si los datos son válidos
        if (strNombre == null || strDescripcion == null || strTipoLocal == null || listaStrDirecciones.isEmpty() || listaStrPropietarios.isEmpty()) {
            throw new IOException("Uno de los campos necesarios está vacío");
        }
        
        // Obtener objetos
        Direccion direccion = parseDireccion(listaStrDirecciones.get(0));
        List<Propietario> listaPropietarios = new ArrayList<>();
        for(int i = 0; i < listaStrPropietarios.size(); i++)
            listaPropietarios.add(parsePropietario(listaStrPropietarios.get(i)));
        
        // Crear el objeto Local
        Local local = new Local(strNombre, direccion, strDescripcion, tipoLocal.parse(strTipoLocal), listaPropietarios.get(0));
        
        // Añadir el resto de propietarios
        for(int i = 1; i < listaStrPropietarios.size();i++){
            local.addPropietario((Propietario) listaPropietarios.get(i));
        }
        
        return local;
    }
    public static Local parseLocal(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Propietario
    public static Propietario parsePropietario(String str) throws IOException{
        String strFiltrado = str.substring(12, str.length()-1); //Elimino Propietario{ y }.
        String[] strTroceado = strFiltrado.split(", "); //Trocear las distintas partes
        
        // Atributos a almacenar
        String strNick = null;
        String strContraseña = null;
        String strFecha = null;
        
        // Campos del atributo Bar
        for(String trozo: strTroceado){
            String[] atributoValor = trozo.split("=");
            if(null != atributoValor[0])switch (atributoValor[0]) {
                case "nick":
                    strNick = atributoValor[1];
                    break;
                case "contraseña":
                    strContraseña = atributoValor[1];
                    break;
                case "fecha_de_nacimiento":
                    strFecha = atributoValor[1];
                    break;
                default:
                    break;
            }
        }
        
        // Comprobar si los datos son validos
        if(strNick == null || strContraseña == null || strFecha == null)
        {throw new IOException("Uno de los campos necesarios esta vacio");}
        
        // Crear objetos que se usan para crear propietario
        Propietario propietario = new Propietario(strNick, strContraseña, LocalDate.parse(strFecha));
        
        return propietario;
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
    public static Usuario parseUsuario(String str) throws IOException{
        String strFiltrado = str.substring(8, str.length()-1); //Elimino Usuario{ y }.
        String[] strTroceado = strFiltrado.split(", "); //Trocear las distintas partes
        
        // Atributos a almacenar
        String strNick = null;
        String strContraseña = null;
        String strFecha = null;
        String strTipo = null;
        
        // Campos del atributo Bar
        for(String trozo: strTroceado){
            String[] atributoValor = trozo.split("=");
            if(null != atributoValor[0])switch (atributoValor[0]) {
                case "nick":
                    strNick = atributoValor[1];
                    break;
                case "contraseña":
                    strContraseña = atributoValor[1];
                    break;
                case "fecha_de_nacimiento":
                    strFecha = atributoValor[1];
                    break;
                case "tipo":
                    strTipo = atributoValor[1];
                    break;
                default:
                    break;
            }
        }
        // Comprobar si los datos son validos
        if(strNick == null || strContraseña == null || strFecha == null || strTipo == null)
        {throw new IOException("Uno de los campos necesarios esta vacio");}

        // Crear objetos que se usan para crear propietario
        Usuario usuario = new Usuario(strNick, strContraseña, LocalDate.parse(strFecha), tipoUsuario.parse(strTipo));
        
        // Devuelvo el usuario
        return usuario;
    }
    public static Usuario parseUsuario(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    public static List<String> extraerObjecto(String strEntrada, String strABuscar) {
        List<String> strSalida = new ArrayList<>();
        Pattern patron = Pattern.compile(strABuscar + "\\{[^\\{\\}]*\\}");
        Matcher emparejador = patron.matcher(strEntrada);
        while (emparejador.find()) {
            strSalida.add(emparejador.group());
        }
        return strSalida;
    }
}
