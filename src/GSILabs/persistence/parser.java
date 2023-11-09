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
        String strFiltrado = str.substring(4, str.length()-1); //Elimino Bar{ y }.
        String[] strTroceado = strFiltrado.split(", "); //Trocear las distintas partes
        
        // Atributos a almacenar
        String strNombre = null;
        String strDireccion = null;
        String strDescripcion = null;
        String strTipoLocal = null;
        List<String> listaStrPropietarios = new ArrayList<>();
        List<String> strEspecialidades = new ArrayList<>();
        ArrayList<String> strReservas = new ArrayList<>();

        
        // Obtener String basicos
        for(int i = 0; i < strTroceado.length ; i++)
        {
            String[] atributoValor = strTroceado[i].split("=");
            if(null != atributoValor[0]) 
                switch (atributoValor[0]){
                case "nombre":
                    strNombre = atributoValor[1];
                    break;
                case "dirección":
                    strDireccion = atributoValor[1] +"="+ atributoValor[2];
                    i++;
                    while(!strTroceado[i].substring(strTroceado[i].length() -1).equals("}")){
                        strDireccion = strDireccion + ", " + strTroceado[i];
                        i++;
                    }
                    strDireccion = strDireccion + ", " + strTroceado[i];
                    break;
                case "descripción":
                    strDescripcion = atributoValor[1];
                    break;
                case "tipo":
                    strTipoLocal = atributoValor[1];
                    break;
                case "propietario":
                    String pro = null;
                    pro = atributoValor[1] +"="+ atributoValor[2];
                    i++;

                    while(!strTroceado[i].substring(strTroceado[i].length() -1).equals("}")){
                        pro = pro + ", " + strTroceado[i];
                        i++;
                    }
                    pro = pro + ", " + strTroceado[i];

                    listaStrPropietarios.add(pro);
                    break;
                case "especialidad":
                    String esp = null;
                    esp = atributoValor[1] +"="+ atributoValor[2];
                    i++;

                    while(!strTroceado[i].substring(strTroceado[i].length() -1).equals("}")){
                        esp = esp + ", " + strTroceado[i];
                        i++;
                    }
                    esp = esp + ", " + strTroceado[i];

                    strEspecialidades.add(esp);
                    break;
                case "reserva":
                    String res = null;
                    res = atributoValor[1] +"="+ atributoValor[2] + "=" + atributoValor[3];
                    i++;
                    int j = 0;

                    while(j != 2){
                        while(!strTroceado[i].substring(strTroceado[i].length() -1).equals("}")){
                            res = res + ", " + strTroceado[i];
                            i++;
                        }
                        res = res + ", " + strTroceado[i];
                        j++;
                        i++;
                    }
                    i--;
                    strReservas.add(res);
                    break;
                default:
                    break;
            }
        }


        // Comprobar si los datos son válidos
        if (strNombre == null || strDescripcion == null || strTipoLocal == null || strDireccion == null
                || listaStrPropietarios.isEmpty()) {
            throw new IOException("Uno de los campos necesarios está vacío");
        }
        // Obtener objetos
        Direccion direccion = parseDireccion(strDireccion);
        List<Propietario> listaPropietarios = new ArrayList<>();
        for(int i = 0; i < listaStrPropietarios.size(); i++)
            listaPropietarios.add(parsePropietario(listaStrPropietarios.get(i)));

        // Crear Bar
        Bar bar =new Bar(strNombre, direccion, strDescripcion, listaPropietarios.get(0));
        for(int i = 1; i < listaPropietarios.size(); i++)
            bar.addPropietario(listaPropietarios.get(i));

        // Introducir Reservas 
        for(int i = 0; i < strReservas.size(); i++){

            Reserva r = parseReserva(strReservas.get(i));
            bar.nuevaReserva(r.getCliente(), r.getFecha(), r.getHora());
        }

        // Introducir Especialidades
        for(int i = 0; i < strEspecialidades.size(); i++){
            System.out.println(strEspecialidades.size());
            bar.agregarEspecialidad(strEspecialidades.get(i));
        }

        return bar;
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
    public static Contestacion parseContestacion(String str) throws IOException{
        String strFiltrado = str.substring(13, str.length()-1); //Elimino Dirección{ y }.
        String[] strTroceado = strFiltrado.split(", "); //Trocear las distintas partes
        
        // Atributos a almacenar
        String strComentario = null;
        String strFecha = null;
        String strLocal = null;
        
        // Campos del atributo Bar
        // Obtener String basicos
        for(int i = 0; i < strTroceado.length ; i++)
        {

            String[] atributoValor = strTroceado[i].split("=");
            if(null != atributoValor[0]) 

                switch (atributoValor[0]){
                case "comentario":
                    strComentario = atributoValor[1];
                    break;
                case "fechaReview":
                    strFecha = atributoValor[1];
                    break;
                case "local":
                    String res = null;
                    res = atributoValor[1] +"="+ atributoValor[2];
                    i++;
                    int j = 0;

                    while(j != 2){
                        while(!strTroceado[i].substring(strTroceado[i].length() -1).equals("}")){
                            res = res + ", " + strTroceado[i];
                            i++;
                        }
                        res = res + ", " + strTroceado[i];
                        j++;
                        i++;
                    }
                    i--;
                    strLocal = res + "}";
                    break;
                default:
                    break;
            }
        }

        // Comprobar si los datos son validos
        if(strComentario == null || strFecha == null || strLocal == null){
            throw new IOException("Uno de los campos necesarios esta vacio");
        }
        Local local = parseLocal(strLocal);
        // Crear objetos que se usan para crear propietario
        Contestacion contestacion = new Contestacion(strComentario, LocalDate.parse(strFecha), local);
        
        return contestacion;
    }
    public static Contestacion parseContestacion(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
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
    public static Direccion parseDireccion(File f) throws FileNotFoundException, IOException {
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
    public static Pub parsePub(String str) throws IOException{
        String strFiltrado = str.substring(4, str.length()-1); //Elimino PUB{ y }.
        String[] strTroceado = strFiltrado.split(", "); //Trocear las distintas partes
        
        // Atributos a almacenar
        String strHoraA = null;
        String strHoraC = null;
        String strNombre = null;
        String strDireccion = null;
        String strDescripcion = null;
        String strTipoLocal = null;
        List<String> listaStrPropietarios = new ArrayList<>();


        
        // Obtener String basicos
        for(int i = 0; i < strTroceado.length ; i++)
        {
            String[] atributoValor = strTroceado[i].split("=");
            if(null != atributoValor[0]) 
                switch (atributoValor[0]){
                case "nombre":
                    strNombre = atributoValor[1];
                    break;
                case "dirección":
                    strDireccion = atributoValor[1] +"="+ atributoValor[2];
                    i++;
                    while(!strTroceado[i].substring(strTroceado[i].length() -1).equals("}")){
                        strDireccion = strDireccion + ", " + strTroceado[i];
                        i++;
                    }
                    strDireccion = strDireccion + ", " + strTroceado[i];
                    break;
                case "descripción":
                    strDescripcion = atributoValor[1];
                    break;
                case "tipo":
                    strTipoLocal = atributoValor[1];
                    break;
                case "propietario":
                    String pro = null;
                    pro = atributoValor[1] +"="+ atributoValor[2];
                    i++;

                    while(!strTroceado[i].substring(strTroceado[i].length() -1).equals("}")){
                        pro = pro + ", " + strTroceado[i];
                        i++;
                    }
                    pro = pro + ", " + strTroceado[i];

                    listaStrPropietarios.add(pro);
                    break;
                case "hora Apertura":
                    strHoraA = atributoValor[1];
                    break;
                case "hora Clausura":
                    strHoraC = atributoValor[1];
                    break;
                default:
                    break;
            }
        }

        // Comprobar si los datos son válidos
        if (strNombre == null || strDescripcion == null || strTipoLocal == null || strDireccion == null
                || strHoraA == null || strHoraC == null || listaStrPropietarios.isEmpty()) {
            throw new IOException("Uno de los campos necesarios está vacío");
        }
        // Obtener objetos
        Direccion direccion = parseDireccion(strDireccion);
        List<Propietario> listaPropietarios = new ArrayList<>();
        for(int i = 0; i < listaStrPropietarios.size(); i++)
            listaPropietarios.add(parsePropietario(listaStrPropietarios.get(i)));

        // Crear Bar
        Pub pub = new Pub(strHoraA, strHoraC,strNombre, direccion, strDescripcion, listaPropietarios.get(0));
        for(int i = 1; i < listaPropietarios.size(); i++)
            pub.addPropietario(listaPropietarios.get(i));

        return pub;
    }
    public static Pub parsePub(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Reserva
    public static Reserva parseReserva(String str) throws IOException{
        
        String strFiltrado = str.substring(8, str.length()-1); //Eliminar "Propietario{" y "}".
        String[] strTroceado = strFiltrado.split(", "); //Trocear las distintas partes
        
        // Atributos a almacenar
        String strCliente = null;
        String strFecha = null;
        String strHora = null;
        String strDescuento = null;
        
        // Obtener String basicos
        for(int i = 0; i < strTroceado.length ; i++)
        {
            String[] atributoValor = strTroceado[i].split("=");
            if(null != atributoValor[0]) 
                switch (atributoValor[0]){
                case "cliente":
                    strCliente = atributoValor[1] +"="+ atributoValor[2];
                    i++;
                    while(!strTroceado[i].substring(strTroceado[i].length() -1).equals("}")){
                        strCliente = strCliente + ", " + strTroceado[i];
                        i++;
                    }
                    strCliente = strCliente + ", " + strTroceado[i];

                    break;
                case "fechaReserva":
                    strFecha = atributoValor[1];
                    break;
                case "hora":
                    strHora = atributoValor[1];
                    break;
                case "descuento":
                    strDescuento = atributoValor[1];
                    strDescuento = strDescuento.substring(0, strDescuento.length() - 1);
                    break;
                default:
                    break;
            }
        }
 
        // Comprobar si los datos son válidos
        if (strCliente == null || strFecha == null || strHora == null || strDescuento == null) {
            throw new IOException("Uno de los campos necesarios está vacío");
        }
        // System.out.println(strCliente);
        // Obtener objetos
        Cliente cliente = parseCliente(strCliente);   
        // Crear el objeto Local
        Reserva reserva = new Reserva(cliente, LocalDate.parse(strFecha), LocalTime.parse(strHora), Integer.parseInt(strDescuento));
    
        return reserva;

    }
    public static Reserva parseReserva(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Restarurante
    public static Restaurante parseRestaurante(String str) throws IOException{

        String strFiltrado = str.substring(12, str.length()-1); //Eliminar "Restaurante{" y "}".
        String[] strTroceado = strFiltrado.split(", "); //Trocear las distintas partes

        // Atributos a almacenar
        String strNombre = null;
        String strDireccion = null;
        String strDescripcion = null;
        String strTipoLocal = null;
        String strPrecioMenu = null;  
        String strCapacidad = null;
        String strCapacidadMesa = null;
        List<String> listaStrPropietarios = new ArrayList<>();
        ArrayList<String> strReservas = new ArrayList<>();

        
        // Obtener String basicos
        for(int i = 0; i < strTroceado.length ; i++)
        {
            String[] atributoValor = strTroceado[i].split("=");
            if(null != atributoValor[0]) 
                switch (atributoValor[0]){
                case "nombre":
                    strNombre = atributoValor[1];
                    break;
                case "dirección":
                    strDireccion = atributoValor[1] +"="+ atributoValor[2];
                    i++;
                    while(!strTroceado[i].substring(strTroceado[i].length() -1).equals("}")){
                        strDireccion = strDireccion + ", " + strTroceado[i];
                        i++;
                    }
                    strDireccion = strDireccion + ", " + strTroceado[i];
                    break;
                case "descripción":
                    strDescripcion = atributoValor[1];
                    break;
                case "tipo":
                    strTipoLocal = atributoValor[1];
                    break;
                case "propietario":
                    String pro = null;
                    pro = atributoValor[1] +"="+ atributoValor[2];
                    i++;

                    while(!strTroceado[i].substring(strTroceado[i].length() -1).equals("}")){
                        pro = pro + ", " + strTroceado[i];
                        i++;
                    }
                    pro = pro + ", " + strTroceado[i];

                    listaStrPropietarios.add(pro);
                    break;
                case "precioMenu":
                    strPrecioMenu = atributoValor[1];
                    break;
                case "capacidad":
                    strCapacidad = atributoValor[1];
                    break;
                case "capacidad Mesa":
                    strCapacidadMesa = atributoValor[1];
                    break;
                case "reserva":
                    String res = null;
                    res = atributoValor[1] +"="+ atributoValor[2] + "=" + atributoValor[3];
                    i++;
                    int j = 0;

                    while(j != 2){
                        while(!strTroceado[i].substring(strTroceado[i].length() -1).equals("}")){
                            res = res + ", " + strTroceado[i];
                            i++;
                        }
                        res = res + ", " + strTroceado[i];
                        j++;
                        i++;
                    }
                    i--;
                    strReservas.add(res);
                    break;
                default:
                    break;
            }
        }


        // Comprobar si los datos son válidos
        if (strNombre == null || strDescripcion == null || strTipoLocal == null || strDireccion == null
                || listaStrPropietarios.isEmpty()) {
            throw new IOException("Uno de los campos necesarios está vacío");
        }
        // Obtener objetos
        Direccion direccion = parseDireccion(strDireccion);
        List<Propietario> listaPropietarios = new ArrayList<>();
        for(int i = 0; i < listaStrPropietarios.size(); i++)
            listaPropietarios.add(parsePropietario(listaStrPropietarios.get(i)));

        // Crear Bar
        Restaurante restaurante =new Restaurante(strNombre, direccion, strDescripcion, listaPropietarios.get(0),
                 Double.parseDouble(strPrecioMenu), Integer.parseInt(strCapacidad), Integer.parseInt(strCapacidadMesa));
        for(int i = 1; i < listaPropietarios.size(); i++)
            restaurante.addPropietario(listaPropietarios.get(i));

        // Introducir Reservas 
        for(int i = 0; i < strReservas.size(); i++){
            Reserva r = parseReserva(strReservas.get(i));
            restaurante.nuevaReserva(r.getCliente(), r.getFecha(), r.getHora());
        }


        return restaurante;
    }
    public static Restaurante parseRestaurante(File f) {
        throw new UnsupportedOperationException("Este método aún no está implementado");
    }
    
    // Review
    public static Review parseReview(String str) throws IOException{
        String strFiltrado = str.substring(7, str.length()-1); //Elimino Dirección{ y }.
        String[] strTroceado = strFiltrado.split(", "); //Trocear las distintas partes
        
        // Atributos a almacenar
        String strValoracion = null;
        String strComentario = null;
        String strFecha = null;
        String strLocal = null;
        String strUsuario = null;
        String strConstestacion = null;
        
        int j;
        // Campos del atributo Bar
        // Obtener String basicos
        for(int i = 0; i < strTroceado.length ; i++)
        {

            String[] atributoValor = strTroceado[i].split("=");
            if(null != atributoValor[0]) 

                switch (atributoValor[0]){
                case "valoracion":
                    strValoracion = atributoValor[1];
                    break;
                case "comentario":
                    strComentario = atributoValor[1];
                    break;
                case "fechaReview":
                    strFecha = atributoValor[1];
                    break;
                case "local":
                    String res = null;
                    res = atributoValor[1] +"="+ atributoValor[2];
                    i++;
                    j = 0;

                    while(j != 2){
                        while(!strTroceado[i].substring(strTroceado[i].length() -1).equals("}")){
                            res = res + ", " + strTroceado[i];
                            i++;
                        }
                        res = res + ", " + strTroceado[i];
                        j++;
                        i++;
                    }
                    i--;
                    strLocal = res + "}";
                    break;
                case "usuario":
                    String usu = null;
                    usu = atributoValor[1] +"="+ atributoValor[2];
                    i++;
                    j = 0;

                    while(j != 1){
                        while(!strTroceado[i].substring(strTroceado[i].length() -1).equals("}")){
                            usu = usu + ", " + strTroceado[i];
                            i++;
                        }
                        usu = usu + ", " + strTroceado[i];
                        j++;
                        i++;
                    }
                    i--;
                    strUsuario = usu;
                    break;
                case "contestacion":
                    String con = null;
                    con = atributoValor[1] +"="+ atributoValor[2];
                    i++;
                    j = 0;

                    while(j != 2){
                        while(!strTroceado[i].substring(strTroceado[i].length() -1).equals("}")){
                            con = con + ", " + strTroceado[i];
                            i++;
                        }
                        con = con + ", " + strTroceado[i];
                        j++;
                        i++;
                    }
                    i--;
                    strConstestacion = con;
                    break; 
                default:
                    break;
            }
        }
/*
        System.out.println(strValoracion);
        System.out.println(strComentario);
        System.out.println(strFecha);
        System.out.println(strLocal);
        System.out.println(strUsuario);
        System.out.println(strConstestacion);
*/

        // Comprobar si los datos son validos
        if(strValoracion == null || strComentario == null || strFecha == null
            || strLocal == null || strUsuario == null || strConstestacion == null){
            throw new IOException("Uno de los campos necesarios esta vacio");
        }
        Usuario usuario = parseUsuario(strUsuario);
        Local local = parseLocal(strLocal);
        Contestacion contestacion = parseContestacion(strConstestacion);
        // Crear objetos que se usan para crear propietario
        Review review = new Review(Integer.parseInt(strValoracion), strComentario, LocalDate.parse(strFecha), local, usuario);
        review.setContestacion(contestacion);     

        return review;
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
    

    // creo que no funcionaria en el caso de Review -,-
    public static List<String> extraerObjecto(String strEntrada, String strABuscar) {
        List<String> strSalida = new ArrayList<>();
        Pattern patron = Pattern.compile(strABuscar + "\\{[^\\{\\}]*\\}");
        Matcher emparejador = patron.matcher(strEntrada);
        while (emparejador.find()) {
            strSalida.add(emparejador.group());
        }
        return strSalida;
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
