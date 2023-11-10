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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilderFactory;
import jdk.internal.org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class parser {
    // Bar
    public static Bar parseBar(String str) throws IOException {


        // Obtener datos del XML
        String strNombre = obtenerContenidoEtiqueta(str, "nombre");
        String strDireccion = obtenerContenidoEtiqueta(str, "Direccion");
        String strDescripcion = obtenerContenidoEtiqueta(str, "descripcion");

        // Obtengo la lista de propietarios
        List<String> strPropietarios = new ArrayList<>();
        for(String strPropietario : str.split("<Propietario>")){
            strPropietarios.add(obtenerContenidoEtiqueta("<Propietario>"+strPropietario+"</Propietario>", "Propietario"));
        }
        strPropietarios.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar
        
        // Obtengo la lista de Reservas
        List<String> strReservas = new ArrayList<>();
        for(String strReserva : str.split("<Reserva>")){
            strReservas.add(obtenerContenidoEtiqueta("<Reserva>"+strReserva+"</Reserva>", "Reserva"));
        }
        strReservas.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar

        // Obtengo la lista de Epecialidades
        List<String> strEspecialidades = new ArrayList<>();
        for(String especialidad : str.split("<especialidad>")){
            strEspecialidades.add(obtenerContenidoEtiqueta("<especialidad>"+especialidad+"</especialidad>", "especialidad"));
        }
        strEspecialidades.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar

        // Comprobar validez
        if(null == strNombre) throw new IllegalArgumentException("Nombre vacio o invalido");
        if(null == strDireccion) throw new IllegalArgumentException("Direccion vacia o invalida");
        if(null == strDescripcion) throw new IllegalArgumentException("Descripcion vacia o invalida");
        if(strPropietarios.isEmpty()) throw new IllegalArgumentException("Propietarios vacio o invalido");
        
        // Conversion de datos
        Direccion direccion = parseDireccion(strDireccion);

        List<Propietario> propietarios = new ArrayList<>();
        for(String strPropietario : strPropietarios){
            propietarios.add(parsePropietario(strPropietario));
        }
        

        // Construccion objeto
        Bar bar =new Bar(strNombre, direccion, strDescripcion, propietarios.get(0));
        for(int i = 1; i < propietarios.size(); i++){
            bar.addPropietario(propietarios.get(i));}
        // Introducir Reservas 
        for(int i = 0; i < strReservas.size(); i++){
            Reserva r = parseReserva(strReservas.get(i));
            bar.nuevaReserva(r.getCliente(), r.getFecha(), r.getHora());
        }
        // Introducir Especialidades
        for(int i = 0; i < strEspecialidades.size(); i++){
            bar.agregarEspecialidad(strEspecialidades.get(i));
        }

        return bar;
    }
    public static Bar parseBar(File f) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new IllegalArgumentException("Fichero vacio.");}
        return(parseBar(contenido));
    }
    
    // Cliente
    // COMPLETADO
    // TESTEADO
    public static Cliente parseCliente(String str) throws IOException{
        // Obtener datos del XML
        String strNick = obtenerContenidoEtiqueta(str, "nick");
        String strContrasena = obtenerContenidoEtiqueta(str, "contraseña");
        String strFechaNacimiento = obtenerContenidoEtiqueta(str, "fechaNacimiento");
        
        // Comprobar validez XML
        if(null == strNick) throw new IllegalArgumentException("Nick vacio o invalido.");
        if(null == strContrasena) throw new IllegalArgumentException("Contraseña vacia o invalida.");
        if(null == strFechaNacimiento) throw new IllegalArgumentException("Fecha de nacimiento vacia o invalida.");
                
        // Conversion de datos
        String[] strLocalDate = strFechaNacimiento.split("-");
        LocalDate fechaNacimiento = LocalDate.of(Integer.parseInt(strLocalDate[0]),Integer.parseInt(strLocalDate[1]),Integer.parseInt(strLocalDate[2]));
        
        // Construccion objeto
        return new Cliente(strNick, strContrasena, fechaNacimiento);
    }
    // COMPLETADO
    // TESTEADO
    public static Cliente parseCliente(File f) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new IllegalArgumentException("Fichero vacio.");}
        return(parseCliente(contenido));
    }
    
    // Contestacion
    public static Contestacion parseContestacion(String str) throws IOException{

        String strComentario = obtenerContenidoEtiqueta(str, "comentario");
        String strFecha = obtenerContenidoEtiqueta(str, "fecha");
        String strLocal = obtenerContenidoEtiqueta(str, "Local");
        
        // Comprobar validez XML
        if(null == strComentario) throw new IllegalArgumentException("Comentario vacio o invalido.");
        if(null == strFecha) throw new IllegalArgumentException("Fecha vacia o invalida.");
        if(null == strLocal) throw new IllegalArgumentException("Local  vacia o invalida.");

        Local local = parseLocal(strLocal);
        // Crear objetos que se usan para crear propietario
        Contestacion contestacion = new Contestacion(strComentario, LocalDate.parse(strFecha), local);
        
        return contestacion;
    }
    public static Contestacion parseContestacion(File f) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new IllegalArgumentException("Fichero vacio.");}
        return(parseContestacion(contenido));
    }
    
    // Direccion
    // COMPLETADO
    // TESTEADO
    public static Direccion parseDireccion(String str){
        // Obtener datos del XML
        String strLocalidad = obtenerContenidoEtiqueta(str, "localidad");
        String strProvincia = obtenerContenidoEtiqueta(str, "provincia");
        String strCalle = obtenerContenidoEtiqueta(str, "calle");
        String  strNumero = obtenerContenidoEtiqueta(str, "numero");
        
        // Comprobar validez XML
        if(null == strLocalidad) throw new IllegalArgumentException("Localidad vacia o invalida.");
        if(null == strProvincia) throw new IllegalArgumentException("Provincia vacia o invalida.");
        if(null == strCalle) throw new IllegalArgumentException("Provincia vacia o invalida.");
        if(null == strNumero) throw new IllegalArgumentException("Número vacio o invalido.");
        
        // Conversion de datos
        int numero = Integer.parseInt(strNumero);
        
        // Contruccion objeto
        return new Direccion(strLocalidad, strProvincia, strCalle, numero);
    }
    // COMPLETADO
    // TESTEADO
    public static Direccion parseDireccion(File f) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new IllegalArgumentException("Fichero vacio.");}
        return(parseDireccion(contenido));
    }
    
    // Local
    public static Local parseLocal(String str) throws IOException{
        // Obtener datos del XML
        String strNombre = obtenerContenidoEtiqueta(str, "nombre");
        String strDireccion = obtenerContenidoEtiqueta(str, "Direccion");
        String strDescripcion = obtenerContenidoEtiqueta(str, "descripcion");
        String strTipoLocal = obtenerContenidoEtiqueta(str, "tipo");
        
        // Obtengo la lista de propietarios
        List<String> strPropietarios = new ArrayList<>();
        for(String strPropietario : str.split("<Propietario>")){
            strPropietarios.add(obtenerContenidoEtiqueta("<Propietario>"+strPropietario+"</Propietario>", "Propietario"));
        }
        strPropietarios.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar
        
        // Comprobar validez
        if(null == strNombre) throw new IllegalArgumentException("Nombre vacio o invalido");
        if(null == strDireccion) throw new IllegalArgumentException("Direccion vacia o invalida");
        if(null == strDescripcion) throw new IllegalArgumentException("Descripcion vacia o invalida");
        if(null == strTipoLocal) throw new IllegalArgumentException("TipoLocal vacio o invalido");
        if(strPropietarios.isEmpty()) throw new IllegalArgumentException("Propietarios vacio o invalido");
        
        // Conversion de datos
        Direccion direccion = parseDireccion(strDireccion);
        List<Propietario> propietarios = new ArrayList<>();
        for(String strPropietario : strPropietarios){
            propietarios.add(parsePropietario(strPropietario));
        }
        tipoLocal tipo = tipoLocal.parse(strTipoLocal);
        
        // Construccion objeto
        Local local = new Local(strNombre, direccion, strDescripcion, tipo, propietarios.get(0));
        for(int i = 1; i < propietarios.size(); i++){
            local.addPropietario(propietarios.get(i));}
        return local;
    }
    public static Local parseLocal(File f) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new IllegalArgumentException("Fichero vacio.");}
        return(parseLocal(contenido));
    }
    
    // Propietario
    // COMPLETADO
    // TESTEADO
    public static Propietario parsePropietario(String str) throws IOException{
        // Obtener datos del XML
        String strNick = obtenerContenidoEtiqueta(str, "nick");
        String strContrasena = obtenerContenidoEtiqueta(str, "contraseña");
        String strFechaNacimiento = obtenerContenidoEtiqueta(str, "fechaNacimiento");
        
        // Comprobar validez XML
        if(null == strNick) throw new IllegalArgumentException("Nick vacio o invalido.");
        if(null == strContrasena) throw new IllegalArgumentException("Contraseña vacia o invalida.");
        if(null == strFechaNacimiento) throw new IllegalArgumentException("Fecha de nacimiento vacia o invalida.");
                
        // Conversion de datos
        String[] strLocalDate = strFechaNacimiento.split("-");
        LocalDate fechaNacimiento = LocalDate.of(Integer.parseInt(strLocalDate[0]),Integer.parseInt(strLocalDate[1]),Integer.parseInt(strLocalDate[2]));
        
        // Construccion objeto
        return new Propietario(strNick, strContrasena, fechaNacimiento);
    }
    // COMPLETADO
    // TESTEADO
    public static Propietario parsePropietario(File f) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new IllegalArgumentException("Fichero vacio.");}
        return(parsePropietario(contenido));
    }
    
    // Pub
    public static Pub parsePub(String str) throws IOException{
        // Obtener datos del XML
        String strNombre = obtenerContenidoEtiqueta(str, "nombre");
        String strDireccion = obtenerContenidoEtiqueta(str, "Direccion");
        String strDescripcion = obtenerContenidoEtiqueta(str, "descripcion");
        String strTipoLocal = obtenerContenidoEtiqueta(str, "tipo");
        String strHoraApertura = obtenerContenidoEtiqueta(str, "horaApertura");
        String strHoraClausura = obtenerContenidoEtiqueta(str, "horaClausura");
        
        // Obtengo la lista de propietarios
        List<String> strPropietarios = new ArrayList<>();
        for(String strPropietario : str.split("<Propietario>")){
            strPropietarios.add(obtenerContenidoEtiqueta("<Propietario>"+strPropietario+"</Propietario>", "Propietario"));
        }
        strPropietarios.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar
        
        // Comprobar validez
        if(null == strNombre) throw new IllegalArgumentException("Nombre vacio o invalido");
        if(null == strDireccion) throw new IllegalArgumentException("Direccion vacia o invalida");
        if(null == strDescripcion) throw new IllegalArgumentException("Descripcion vacia o invalida");
        if(null == strHoraApertura) throw new IllegalArgumentException("HoraApertura vacia o invalida");
        if(null == strHoraClausura) throw new IllegalArgumentException("HoraClausura vacio o invalido");
        if(strPropietarios.isEmpty()) throw new IllegalArgumentException("Propietarios vacio o invalido");
        
        // Conversion de datos
        Direccion direccion = parseDireccion(strDireccion);
        List<Propietario> propietarios = new ArrayList<>();
        for(String strPropietario : strPropietarios){
            propietarios.add(parsePropietario(strPropietario));
        }

        
        // Construccion objeto
        Pub pub = new Pub(strHoraApertura, strHoraClausura, strNombre, direccion, strDescripcion, propietarios.get(0));
        for(int i = 1; i < propietarios.size(); i++){
            pub.addPropietario(propietarios.get(i));}

        return pub;
    }
    public static Pub parsePub(File f) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new IllegalArgumentException("Fichero vacio.");}
        return(parsePub(contenido));
    }
    
    // Reserva
    public static Reserva parseReserva(String str) throws IOException{
        
        // Obtener datos del XML
        String strcliente = obtenerContenidoEtiqueta(str, "Cliente");
        String strfecha = obtenerContenidoEtiqueta(str, "fecha");
        String strhora = obtenerContenidoEtiqueta(str, "hora");
        String strdescuento = obtenerContenidoEtiqueta(str, "descuento");
        
        // Comprobar validez XML
        if(null == strcliente) throw new IllegalArgumentException("Cliente vacio o invalido.");
        if(null == strfecha) throw new IllegalArgumentException("Fecha vacia o invalida.");
        if(null == strhora) throw new IllegalArgumentException("Hora vacia o invalida.");
        if(null == strdescuento) throw new IllegalArgumentException("Descuento vacia o invalida.");
                

        // System.out.println(strCliente);
        // Obtener objetos
        Cliente cliente = parseCliente(strcliente);   
        // Crear el objeto Local
        Reserva reserva = new Reserva(cliente, LocalDate.parse(strfecha), LocalTime.parse(strhora), Integer.parseInt(strdescuento));
    
        return reserva;

    }
    public static Reserva parseReserva(File f) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new IllegalArgumentException("Fichero vacio.");}
        return(parseReserva(contenido));
    }
    
    // Restarurante
    public static Restaurante parseRestaurante(String str) throws IOException{
        // Obtener datos del XML
        String strNombre = obtenerContenidoEtiqueta(str, "nombre");
        String strDireccion = obtenerContenidoEtiqueta(str, "Direccion");
        String strDescripcion = obtenerContenidoEtiqueta(str, "descripcion");
        String strTipoLocal = obtenerContenidoEtiqueta(str, "tipo");
        String strPrecioMenu = obtenerContenidoEtiqueta(str, "precioMenu");
        String strCapacidad = obtenerContenidoEtiqueta(str, "capacidad");
        String strCapacidadMesa = obtenerContenidoEtiqueta(str, "capacidadMesa");
        
        // Obtengo la lista de propietarios
        List<String> strPropietarios = new ArrayList<>();
        for(String strPropietario : str.split("<Propietario>")){
            strPropietarios.add(obtenerContenidoEtiqueta("<Propietario>"+strPropietario+"</Propietario>", "Propietario"));
        }
        strPropietarios.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar
        
        // Obtengo la lista de Reservas
        List<String> strReservas = new ArrayList<>();
        for(String strReserva : str.split("<Reserva>")){
            strReservas.add(obtenerContenidoEtiqueta("<Reserva>"+strReserva+"</Reserva>", "Reserva"));
        }
        strReservas.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar

        // Comprobar validez
        if(null == strNombre) throw new IllegalArgumentException("Nombre vacio o invalido");
        if(null == strDireccion) throw new IllegalArgumentException("Direccion vacia o invalida");
        if(null == strDescripcion) throw new IllegalArgumentException("Descripcion vacia o invalida");
        if(null == strPrecioMenu) throw new IllegalArgumentException("PrecioMenu vacia o invalida");
        if(null == strCapacidad) throw new IllegalArgumentException("Capacidad vacio o invalido");
        if(null == strCapacidadMesa) throw new IllegalArgumentException("CapacidadMesa vacio o invalido");
        if(strPropietarios.isEmpty()) throw new IllegalArgumentException("Propietarios vacio o invalido");
        //Posible que no tenga reservas
        //if(strReservas.isEmpty()) throw new IllegalArgumentException("Reservas vacio o invalido");
        
        // Conversion de datos
        Direccion direccion = parseDireccion(strDireccion);

        List<Propietario> propietarios = new ArrayList<>();
        for(String strPropietario : strPropietarios){
            propietarios.add(parsePropietario(strPropietario));
        }

        // Construccion objeto
        Restaurante restaurante =new Restaurante(strNombre, direccion, strDescripcion, propietarios.get(0),
               Double.parseDouble(strPrecioMenu), Integer.parseInt(strCapacidad), Integer.parseInt(strCapacidadMesa));
        for(int i = 1; i < propietarios.size(); i++){
            restaurante.addPropietario(propietarios.get(i));}
        // Introducir Reservas 
        for(int i = 0; i < strReservas.size(); i++){
            Reserva r = parseReserva(strReservas.get(i));
            restaurante.nuevaReserva(r.getCliente(), r.getFecha(), r.getHora());
        }

        return restaurante;
    }
    public static Restaurante parseRestaurante(File f) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new IllegalArgumentException("Fichero vacio.");}
        return(parseRestaurante(contenido));
    }
    
    // Review
    public static Review parseReview(String str) throws IOException{
        // Atributos a almacenar
        String strValoracion = obtenerContenidoEtiqueta(str,"valoracion");
        String strComentario = obtenerContenidoEtiqueta(str,"comentario");
        String strFecha = obtenerContenidoEtiqueta(str,"fecha");
        String strLocal = obtenerContenidoEtiqueta(str,"Local");
        String strUsuario = obtenerContenidoEtiqueta(str,"Usuario");
        String strConstestacion = obtenerContenidoEtiqueta(str,"Constestacion");
    
        // Comprobar validez XML
        if(null == strValoracion) throw new IllegalArgumentException("Valoracion vacio o invalido.");
        if(null == strComentario) throw new IllegalArgumentException("Comentario vacia o invalida.");
        if(null == strFecha) throw new IllegalArgumentException("Fecha vacia o invalida.");
        if(null == strLocal) throw new IllegalArgumentException("Local vacio o invalido.");
        if(null == strUsuario) throw new IllegalArgumentException("Usuario vacio o invalido.");
        if(null == strConstestacion) throw new IllegalArgumentException("Contestacion vacia o invalida.");

        Usuario usuario = parseUsuario(strUsuario);
        Local local = parseLocal(strLocal);
        Contestacion contestacion = parseContestacion(strConstestacion);
        // Crear objetos que se usan para crear propietario
        Review review = new Review(Integer.parseInt(strValoracion), strComentario, LocalDate.parse(strFecha), local, usuario);
        review.setContestacion(contestacion);     

        return review;
    }
    public static Review parseReview(File f) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new IllegalArgumentException("Fichero vacio.");}
        return(parseReview(contenido));
    }

    // Usuario
    // COMPLETADO
    // TESTEADO
    public static Usuario parseUsuario(String str) throws IOException{
        // Obtener datos del XML
        String strNick = obtenerContenidoEtiqueta(str,"nick");
        String strContrasena = obtenerContenidoEtiqueta(str, "contraseña");
        String strFechaNacimiento = obtenerContenidoEtiqueta(str, "fechaNacimiento");
        String strTipo = obtenerContenidoEtiqueta(str, "tipo");
        
        // Comprobar validez XML
        if(null == strNick) throw new IllegalArgumentException("Nick vacio o invalido.");
        if(null == strContrasena) throw new IllegalArgumentException("Contraseña vacia o invalida.");
        if(null == strFechaNacimiento) throw new IllegalArgumentException("Fecha de nacimiento vacia o invalida.");
        if(null == strTipo) throw new IllegalArgumentException("Tipo vacio o invalido.");
        
        // Conversion de datos
        String[] strLocalDate = strFechaNacimiento.split("-");
        LocalDate fechaNacimiento = LocalDate.of(Integer.parseInt(strLocalDate[0]),Integer.parseInt(strLocalDate[1]),Integer.parseInt(strLocalDate[2]));
        tipoUsuario tipo = tipoUsuario.parse(strTipo);
        // Construccion objeto
        return new Usuario(strNick, strContrasena, fechaNacimiento, tipo);
    }
    // COMPLETADO
    // TESTEADO
    public static Usuario parseUsuario(File f) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new IllegalArgumentException("Fichero vacio.");}
        return(parseUsuario(contenido));
    }
    
    // Estrae el string contenido por la etiqueta xml indicada
    public static String obtenerContenidoEtiqueta(String contenidoOriginal, String etiqueta) {
        int posicionInicioEtiqueta = contenidoOriginal.indexOf("<" + etiqueta + ">");

        // Posicion inicial
        if (posicionInicioEtiqueta == -1) {
            return null;
        }

        // Posicion final
        int posicionCierreEtiqueta = contenidoOriginal.indexOf("</" + etiqueta + ">");
        if (posicionCierreEtiqueta == -1) {
            return null;
        }

        return contenidoOriginal.substring(posicionInicioEtiqueta + etiqueta.length() + 2 /*<>*/, posicionCierreEtiqueta);
    }
}
