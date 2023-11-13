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
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// import static jdk.internal.joptsimple.internal.Strings.repeat;


/**
 * Clase parser
 * Clase estatica que permite generar objetos definidos en el modelo a partir del formato XML
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 10.11.2023
 */
public class parser {
    /**
     * Crea un objeto Bar a partir de un String XML que le represente
     * @param str String que contiene el XML.
     * @return Objeto bar creado a partir del XML
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Bar parseBar(String str) throws XMLParsingException{
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
        if(null == strNombre) throw new XMLParsingException("Nombre vacio o invalido");
        if(null == strDireccion) throw new XMLParsingException("Direccion vacia o invalida");
        if(null == strDescripcion) throw new XMLParsingException("Descripcion vacia o invalida");
        if(strPropietarios.isEmpty()) throw new XMLParsingException("Propietarios vacio o invalido");
        
        // Conversion de datos
        Direccion direccion = parseDireccion(strDireccion);
        List<Propietario> propietarios = new ArrayList<>();
        for(String strPropietario : strPropietarios){
            propietarios.add(parsePropietario(strPropietario));}

        // Construccion objeto
        Bar bar =new Bar(strNombre, direccion, strDescripcion, propietarios.get(0));
        for(int i = 1; i < propietarios.size(); i++){
            bar.addPropietario(propietarios.get(i));}
        
        // Introducir Reservas si las hay
        for(int i = 0; i < strReservas.size(); i++){
            Reserva reserva = parseReserva(strReservas.get(i));
            bar.nuevaReserva(reserva.getCliente(), reserva.getFecha(), reserva.getHora());
        }
        // Introducir Especialidades
        for(int i = 0; i < strEspecialidades.size(); i++){
            bar.agregarEspecialidad(strEspecialidades.get(i));
        }

        // Devolver objeto
        return bar;
    }
    
    /**
     * Crea un objeto Bar a partir de un fichero XML que le represente.
     * @param f SFile que contiene el XML.
     * @return Objeto bar creado a partir del fichero XML .
     * @throws java.io.IOException
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Bar parseBar(File f) throws IOException, XMLParsingException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new XMLParsingException("Fichero vacio.");}
        return(parseBar(contenido));
    }
    
    /**
     * Crea un objeto Cliente a partir de un String XML que le represente.
     * @param str String que contiene el XML.
     * @return Objeto Cliente creado a partir del XML.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Cliente parseCliente(String str) throws XMLParsingException{
        // Obtener datos del XML
        String strNick = obtenerContenidoEtiqueta(str, "nick");
        String strContrasena = obtenerContenidoEtiqueta(str, "contraseña");
        String strFechaNacimiento = obtenerContenidoEtiqueta(str, "fechaNacimiento");
        
        // Comprobar validez XML
        if(null == strNick) throw new XMLParsingException("Nick vacio o invalido.");
        if(null == strContrasena) throw new XMLParsingException("Contraseña vacia o invalida.");
        if(null == strFechaNacimiento) throw new XMLParsingException("Fecha de nacimiento vacia o invalida.");
                
        // Conversion de datos
        String[] strLocalDate = strFechaNacimiento.split("-");
        LocalDate fechaNacimiento = LocalDate.of(Integer.parseInt(strLocalDate[0]),Integer.parseInt(strLocalDate[1]),Integer.parseInt(strLocalDate[2]));
        
        // Construccion objeto
        return new Cliente(strNick, strContrasena, fechaNacimiento);
    }
    
    /**
     * Crea un objeto Cliente a partir de un fichero XML que le represente.
     * @param f Fichero que contiene el XML.
     * @return Objeto cliente creado a partir del XML .
     * @throws java.io.IOException Problema al trabajar con el fichero.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Cliente parseCliente(File f) throws IOException, XMLParsingException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new XMLParsingException("Fichero vacio.");}
        return(parseCliente(contenido));
    }
    
    /**
     * Crea un objeto Local a partir de un String XML que le represente.
     * @param str String que contiene el XML.
     * @return Objeto Local creado a partir del XML.
     * @throws java.io.IOException Problema al tratar con el Local.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Contestacion parseContestacion(String str) throws IOException, XMLParsingException{

        String strComentario = obtenerContenidoEtiqueta(str, "comentario");
        String strFecha = obtenerContenidoEtiqueta(str, "fecha");
        String strLocal = obtenerContenidoEtiqueta(str, "Local");

        Local local = null;
        if(null == strLocal){
            strLocal = obtenerContenidoEtiqueta(str, "Bar");
            if(null == strLocal){
                strLocal = obtenerContenidoEtiqueta(str, "Restaurante");
                if(null == strLocal){
                    strLocal = obtenerContenidoEtiqueta(str, "Pub");
                    if(null != strLocal){
                        local = parsePub(strLocal);
                    }
                }else{
                    local = parseRestaurante(strLocal);
                }
            }else{
                local = parseBar(strLocal);
            }
        }else{
            local = parseLocal(strLocal);
        }
        
        // Comprobar validez XML
        if(null == strComentario) throw new XMLParsingException("Comentario vacio o invalido.");
        if(null == strFecha) throw new XMLParsingException("Fecha vacia o invalida.");
        if(null == strLocal) throw new XMLParsingException("Local  vacia o invalida.");

        // Crear objetos que se usan para crear propietario
        Contestacion contestacion = new Contestacion(strComentario, LocalDate.parse(strFecha), local);
        
        // Devolver objeto
        return contestacion;
    }
    
    /**
     * Crea un objeto Local a partir de un fichero XML que le represente.
     * @param f Fichero que contiene el XML.
     * @return Objeto Fichero creado a partir del XML.
     * @throws java.io.IOException Problema al trabajar con el fichero.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Contestacion parseContestacion(File f) throws IOException, XMLParsingException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new XMLParsingException("Fichero vacio.");}
        return(parseContestacion(contenido));
    }
    
    /**
     * Crea un objeto Direccion a partir de un String XML que le represente.
     * @param str String que contiene el XML.
     * @return Objeto Direccion creado a partir del XML.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Direccion parseDireccion(String str) throws XMLParsingException{
        // Obtener datos del XML
        String strLocalidad = obtenerContenidoEtiqueta(str, "localidad");
        String strProvincia = obtenerContenidoEtiqueta(str, "provincia");
        String strCalle = obtenerContenidoEtiqueta(str, "calle");
        String  strNumero = obtenerContenidoEtiqueta(str, "numero");
        
        // Comprobar validez XML
        if(null == strLocalidad) throw new XMLParsingException("Localidad vacia o invalida.");
        if(null == strProvincia) throw new XMLParsingException("Provincia vacia o invalida.");
        if(null == strCalle) throw new XMLParsingException("Provincia vacia o invalida.");
        if(null == strNumero) throw new XMLParsingException("Número vacio o invalido.");
        
        // Conversion de datos
        int numero = Integer.parseInt(strNumero);
        
        // Contruccion objeto
        return new Direccion(strLocalidad, strProvincia, strCalle, numero);
    }
    
    /**
     * Crea un objeto Direccion a partir de un Fichero XML que le represente.
     * @param f Fichero que contiene el XML.
     * @return Objeto Direccion creado a partir del XML.
     * @throws java.io.IOException Problema con el fichero.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Direccion parseDireccion(File f) throws IOException, XMLParsingException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new XMLParsingException("Fichero vacio.");}
        return(parseDireccion(contenido));
    }
    
    /**
     * Crea un objeto Local a partir de un String XML que le represente.
     * @param str String que contiene el XML.
     * @return Objeto Local creado a partir del XML.
     * @throws java.io.IOException
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Local parseLocal(String str) throws IOException, XMLParsingException{
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
        if(null == strNombre) throw new XMLParsingException("Nombre vacio o invalido");
        if(null == strDireccion) throw new XMLParsingException("Direccion vacia o invalida");
        if(null == strDescripcion) throw new XMLParsingException("Descripcion vacia o invalida");
        if(null == strTipoLocal) throw new XMLParsingException("TipoLocal vacio o invalido");
        if(strPropietarios.isEmpty()) throw new XMLParsingException("Propietarios vacio o invalido");
        
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
    
    /**
     * Crea un objeto Local a partir de un Fichero XML que le represente.
     * @param f Fichero que contiene el XML.
     * @return Objeto Local creado a partir del XML.
     * @throws java.io.IOException Problema con el fichero.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Local parseLocal(File f) throws IOException, XMLParsingException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new XMLParsingException("Fichero vacio.");}
        return(parseLocal(contenido));
    }
    
    /**
     * Crea un objeto Propietario a partir de un String XML que le represente.
     * @param str String que contiene el XML.
     * @return Objeto Propietario creado a partir del XML.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Propietario parsePropietario(String str) throws XMLParsingException{
        // Obtener datos del XML
        String strNick = obtenerContenidoEtiqueta(str, "nick");
        String strContrasena = obtenerContenidoEtiqueta(str, "contraseña");
        String strFechaNacimiento = obtenerContenidoEtiqueta(str, "fechaNacimiento");
        
        // Comprobar validez XML
        if(null == strNick) throw new XMLParsingException("Nick vacio o invalido.");
        if(null == strContrasena) throw new XMLParsingException("Contraseña vacia o invalida.");
        if(null == strFechaNacimiento) throw new XMLParsingException("Fecha de nacimiento vacia o invalida.");
                
        // Conversion de datos
        String[] strLocalDate = strFechaNacimiento.split("-");
        LocalDate fechaNacimiento = LocalDate.of(Integer.parseInt(strLocalDate[0]),Integer.parseInt(strLocalDate[1]),Integer.parseInt(strLocalDate[2]));
        
        // Construccion objeto
        return new Propietario(strNick, strContrasena, fechaNacimiento);
    }
    
    /**
     * Crea un objeto Propietario a partir de un Fichero XML que le represente.
     * @param f Fichero que contiene el XML.
     * @return Objeto Propietario creado a partir del XML.
     * @throws java.io.IOException Problema con el fichero.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Propietario parsePropietario(File f) throws IOException, XMLParsingException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new XMLParsingException("Fichero vacio.");}
        return(parsePropietario(contenido));
    }
    
    /**
     * Crea un objeto Pub a partir de un String XML que le represente.
     * @param str String que contiene el XML.
     * @return Objeto Pub creado a partir del XML.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Pub parsePub(String str) throws XMLParsingException{
        // Obtener datos del XML
        String strNombre = obtenerContenidoEtiqueta(str, "nombre");
        String strDireccion = obtenerContenidoEtiqueta(str, "Direccion");
        String strDescripcion = obtenerContenidoEtiqueta(str, "descripcion");
        String strHoraApertura = obtenerContenidoEtiqueta(str, "horaApertura");
        String strHoraClausura = obtenerContenidoEtiqueta(str, "horaClausura");
        
        // Obtengo la lista de propietarios
        List<String> strPropietarios = new ArrayList<>();
        for(String strPropietario : str.split("<Propietario>")){
            strPropietarios.add(obtenerContenidoEtiqueta("<Propietario>"+strPropietario+"</Propietario>", "Propietario"));
        }
        strPropietarios.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar
        
        // Comprobar validez
        if(null == strNombre) throw new XMLParsingException("Nombre vacio o invalido");
        if(null == strDireccion) throw new XMLParsingException("Direccion vacia o invalida");
        if(null == strDescripcion) throw new XMLParsingException("Descripcion vacia o invalida");
        if(null == strHoraApertura) throw new XMLParsingException("HoraApertura vacia o invalida");
        if(null == strHoraClausura) throw new XMLParsingException("HoraClausura vacio o invalido");
        if(strPropietarios.isEmpty()) throw new XMLParsingException("Propietarios vacio o invalido");
        
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

        // Devolver objeto
        return pub;
    }
    
    /**
     * Crea un objeto Pub a partir de un Fichero XML que le represente.
     * @param f Fichero que contiene el XML.
     * @return Objeto Pub creado a partir del XML.
     * @throws java.io.IOException Problema con el fichero.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Pub parsePub(File f) throws IOException, XMLParsingException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new XMLParsingException("Fichero vacio.");}
        return(parsePub(contenido));
    }
    
    /**
     * Crea un objeto Reserva a partir de un String XML que le represente.
     * @param str String que contiene el XML.
     * @return Objeto Reserva creado a partir del XML.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Reserva parseReserva(String str) throws XMLParsingException{
        
        // Obtener datos del XML
        String strcliente = obtenerContenidoEtiqueta(str, "Cliente");
        String strfecha = obtenerContenidoEtiqueta(str, "fecha");
        String strhora = obtenerContenidoEtiqueta(str, "hora");
        String strdescuento = obtenerContenidoEtiqueta(str, "descuento");
        
        // Comprobar validez XML
        if(null == strcliente) throw new XMLParsingException("Cliente vacio o invalido.");
        if(null == strfecha) throw new XMLParsingException("Fecha vacia o invalida.");
        if(null == strhora) throw new XMLParsingException("Hora vacia o invalida.");
        if(null == strdescuento) throw new XMLParsingException("Descuento vacia o invalida.");
                
        // Obtener objetos
        Cliente cliente = parseCliente(strcliente);   
        
        // Crear el objeto Reserva y lo devuelvo
        return new Reserva(cliente, LocalDate.parse(strfecha), LocalTime.parse(strhora), Integer.parseInt(strdescuento));
    }
    
    /**
     * Crea un objeto Reserva a partir de un String XML que le represente.
     * @param f Fichero que contiene el XML.
     * @return Objeto Reserva creado a partir del XML.
     * @throws java.io.IOException Problema con el fichero.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Reserva parseReserva(File f) throws IOException, XMLParsingException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new XMLParsingException("Fichero vacio.");}
        return(parseReserva(contenido));
    }
    
    /**
     * Crea un objeto Restaurante a partir de un String XML que le represente.
     * @param str String que contiene el XML.
     * @return Objeto Restaurante creado a partir del XML.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Restaurante parseRestaurante(String str) throws XMLParsingException{
        // Obtener datos del XML
        String strNombre = obtenerContenidoEtiqueta(str, "nombre");
        String strDireccion = obtenerContenidoEtiqueta(str, "Direccion");
        String strDescripcion = obtenerContenidoEtiqueta(str, "descripcion");
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
        if(null == strNombre) throw new XMLParsingException("Nombre vacio o invalido");
        if(null == strDireccion) throw new XMLParsingException("Direccion vacia o invalida");
        if(null == strDescripcion) throw new XMLParsingException("Descripcion vacia o invalida");
        if(null == strPrecioMenu) throw new XMLParsingException("PrecioMenu vacia o invalida");
        if(null == strCapacidad) throw new XMLParsingException("Capacidad vacio o invalido");
        if(null == strCapacidadMesa) throw new XMLParsingException("CapacidadMesa vacio o invalido");
        if(strPropietarios.isEmpty()) throw new XMLParsingException("Propietarios vacio o invalido");
        
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
        
        // Devolver objeto
        return restaurante;
    }
    
    /**
     * Crea un objeto Restaurante a partir de un Fichero XML que le represente.
     * @param f Fichero que contiene el XML.
     * @return Objeto Restaurante creado a partir del XML.
     * @throws java.io.IOException Problema con el fichero.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Restaurante parseRestaurante(File f) throws IOException, XMLParsingException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new XMLParsingException("Fichero vacio.");}
        return(parseRestaurante(contenido));
    }
    
    /**
     * Crea un objeto Review a partir de un String XML que le represente.
     * @param str String que contiene el XML.
     * @return Objeto Review creado a partir del XML.
     * @throws java.io.IOException problea con Usuario Local o Contestacion
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Review parseReview(String str) throws IOException, XMLParsingException{
        // Atributos a almacenar
        String strValoracion = obtenerContenidoEtiqueta(str,"valoracion");
        String strComentario = obtenerContenidoEtiqueta(str,"comentario");
        String strFecha = obtenerContenidoEtiqueta(str,"fecha");
        String strLocal = obtenerContenidoEtiqueta(str,"Local");

        Local local = null;
        if(null == strLocal){
            strLocal = obtenerContenidoEtiqueta(str, "Bar");
            if(null == strLocal){
                strLocal = obtenerContenidoEtiqueta(str, "Restaurante");
                if(null == strLocal){
                    strLocal = obtenerContenidoEtiqueta(str, "Pub");
                    if(null != strLocal){
                        local = parsePub(strLocal);
                    }
                }else{
                    local = parseRestaurante(strLocal);
                }
            }else{
                local = parseBar(strLocal);
            }
        }else{
            local = parseLocal(strLocal);
        }

        String strUsuario = obtenerContenidoEtiqueta(str,"Usuario");
        Usuario usuario = null;
        if(null == strUsuario){
            strUsuario = obtenerContenidoEtiqueta(str, "Cliente");
            if(null == strUsuario){
                strUsuario = obtenerContenidoEtiqueta(str, "Propietario");
                if(null != strUsuario){
                    usuario = parsePropietario(strUsuario);
                }
            }else{
                usuario = parseCliente(strUsuario);
            }
        }else{
            usuario = parseUsuario(strUsuario);
        }
    
        String strConstestacion = obtenerContenidoEtiqueta(str,"Contestacion");

        // Comprobar validez XML
        if(null == strValoracion) throw new XMLParsingException("Valoracion vacio o invalido.");
        if(null == strComentario) throw new XMLParsingException("Comentario vacia o invalida.");
        if(null == strFecha) throw new XMLParsingException("Fecha vacia o invalida.");
        if(null == strLocal) throw new XMLParsingException("Local vacio o invalido.");
        if(null == strUsuario) throw new XMLParsingException("Usuario vacio o invalido.");
        // if(null == strConstestacion) throw new XMLParsingException("Contestacion vacia o invalida.");



        // Crear objetos que se usan para crear propietario
        Review review = new Review(Integer.parseInt(strValoracion), strComentario, LocalDate.parse(strFecha), local, usuario);
        if(strConstestacion != null){
            Contestacion contestacion = parseContestacion(strConstestacion);
            review.setContestacion(contestacion);  
        }   

        // Devolver objeto
        return review;
    }
    
    /**
     * Crea un objeto Review a partir de un Fichero XML que le represente.
     * @param f Fichero que contiene el XML.
     * @return Objeto Review creado a partir del XML.
     * @throws java.io.IOException Problema con el fichero.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Review parseReview(File f) throws IOException, XMLParsingException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new XMLParsingException("Fichero vacio.");}
        return(parseReview(contenido));
    }

    /**
     * Crea un objeto Usuario a partir de un String XML que le represente.
     * @param str String que contiene el XML.
     * @return Objeto Usuario creado a partir del XML.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Usuario parseUsuario(String str) throws XMLParsingException{
        // Obtener datos del XML
        String strNick = obtenerContenidoEtiqueta(str,"nick");
        String strContrasena = obtenerContenidoEtiqueta(str, "contraseña");
        String strFechaNacimiento = obtenerContenidoEtiqueta(str, "fechaNacimiento");
        String strTipo = obtenerContenidoEtiqueta(str, "tipo");
        
        // Comprobar validez XML
        if(null == strNick) throw new XMLParsingException("Nick vacio o invalido.");
        if(null == strContrasena) throw new XMLParsingException("Contraseña vacia o invalida.");
        if(null == strFechaNacimiento) throw new XMLParsingException("Fecha de nacimiento vacia o invalida.");
        if(null == strTipo) throw new XMLParsingException("Tipo vacio o invalido.");
        
        // Conversion de datos
        String[] strLocalDate = strFechaNacimiento.split("-");
        LocalDate fechaNacimiento = LocalDate.of(Integer.parseInt(strLocalDate[0]),Integer.parseInt(strLocalDate[1]),Integer.parseInt(strLocalDate[2]));
        tipoUsuario tipo = tipoUsuario.parse(strTipo);
        // Construccion objeto
        return new Usuario(strNick, strContrasena, fechaNacimiento, tipo);
    }
    
    /**
     * Crea un objeto Usuario a partir de un Fichero XML que le represente.
     * @param f Fichero que contiene el XML.
     * @return Objeto Usuario creado a partir del XML.
     * @throws java.io.IOException Problema con el fichero.
     * @throws GSILabs.persistence.XMLParsingException
     */
    public static Usuario parseUsuario(File f) throws IOException, XMLParsingException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        // Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        // Comprobar si esta vacio
        if(contenido.length() == 0) {throw new XMLParsingException("Fichero vacio.");}
        return(parseUsuario(contenido));
    }
    
    /**
     * Devuelve un String que contiene la etiqueta indicada como etiqueta delimitadora
     * Ejemplo:
     *  original = <etiqueta>contenido</etiqueta>
     *  salida = contenido
     * 
     * @param contenidoOriginal String en formato XML.
     * @param etiqueta String etiqueta que se quiere buscar, debe seguir el formato: <etiqueta></etiqueta>.
     * @return String que devuelve el contenido que se encuentra entre el primer conjunto de etiquetas localizado.
     */
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
