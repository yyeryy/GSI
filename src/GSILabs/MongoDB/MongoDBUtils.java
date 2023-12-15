package GSILabs.MongoDB;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Donacion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Local.tipoLocal;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import GSILabs.BModel.Usuario.tipoUsuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.apache.commons.collections.CollectionUtils.size;
import org.bson.Document;

public class MongoDBUtils {
    /**
     * Funcion crearDireccionDocument
     * Genera un Document a partir de Direccion.
     * @param direccion: Direccion a convertir.
     * @return Document: Document obtenido de Direccion.
     */
    public static Document crearDireccionDocument(Direccion direccion) {
        Document direccionDocument = new Document();
        direccionDocument.append("Localidad", direccion.getLocalidad());
        direccionDocument.append("Provincia", direccion.getProvincia());
        direccionDocument.append("Calle", direccion.getCalle());
        direccionDocument.append("Numero", direccion.getNumero());
        return direccionDocument;
    }
    
    /**
     * Funcion crearDireccionObject
     * Genera una Direccion a partir de Document.
     * @param direccionDocument: Document a convertir.
     * @return Direccion: Direccion obtenida de Document.
     */
    public static Direccion crearDireccionObject(Document direccionDocument) {
        String localidad = direccionDocument.getString("Localidad");
        String provincia = direccionDocument.getString("Provincia");
        String calle = direccionDocument.getString("Calle");
        Integer numero = direccionDocument.getInteger("Numero");
        return new Direccion(localidad,provincia,calle,numero);
    }
    
    /**
     * Funcion crearUsuarioDocument
     * Genera un Document a partir de Direccion.
     * @param usuario: Direccion a convertir.
     * @return Document: Document obtenido de Usuario.
     */
    public static Document crearUsuarioDocument(Usuario usuario) {
        Document usuarioDocument = new Document();
        usuarioDocument.append("Nick", usuario.getNick());
        usuarioDocument.append("Contraseña", usuario.getContraseña());
        usuarioDocument.append("Fecha nacimiento", usuario.getFechaNacimiento().toString());
        usuarioDocument.append("Tipo usuario", usuario.getTipo().name());
        return usuarioDocument;
    }
    
    /**
     * Funcion crearUsuarioObject
     * Genera una Usuario a partir de Document.
     * @param usuarioDocument: Document a convertir.
     * @return Usuario: Usuario obtenida de Document.
     */
    public static Usuario crearUsuarioObject(Document usuarioDocument) {
        String nick = usuarioDocument.getString("Nick");
        String contrasena = usuarioDocument.getString("Contraseña");
        LocalDate fechaNacimiento = LocalDate.parse(usuarioDocument.getString("Fecha nacimiento"));
        tipoUsuario tipo = tipoUsuario.parse(usuarioDocument.getString("Tipo usuario"));
        return new Usuario(nick, contrasena, fechaNacimiento, tipo);
    }
    
    /**
     * Funcion crearPropietarioDocument
     * Genera un Document a partir de Propietario.
     * @param propietario: Propietario a convertir.
     * @return Document: Document obtenido de Propietario.
     */
    public static Document crearPropietarioDocument(Propietario propietario) {
        Document propietarioDocument = new Document();
        propietarioDocument.append("Nick", propietario.getNick());
        propietarioDocument.append("Contraseña", propietario.getContraseña());
        propietarioDocument.append("Fecha nacimiento", propietario.getFechaNacimiento().toString());
        return propietarioDocument;
    }
    
    /**
     * Funcion crearPropietarioObject
     * Genera un Propietario a partir de Document.
     * @param propietarioDocument: Document a convertir.
     * @return Propietario: Propietario obtenido de Document.
     */
    public static Propietario crearPropietarioObject(Document propietarioDocument) {
        String nick = propietarioDocument.getString("Nick");
        String contrasena = propietarioDocument.getString("Contraseña");
        LocalDate fechaNacimiento = LocalDate.parse(propietarioDocument.getString("Fecha nacimiento"));
        return new Propietario(nick, contrasena, fechaNacimiento);
    }

    /**
     * Funcion crearClienteDocument
     * Genera un Document a partir de Cliente.
     * @param cliente: Cliente a convertir.
     * @return Document: Document obtenido de Cliente.
     */
    public static Document crearClienteDocument(Cliente cliente) {
        Document clienteDocument = new Document();
        clienteDocument.append("Nick", cliente.getNick());
        clienteDocument.append("Contraseña", cliente.getContraseña());
        clienteDocument.append("Fecha nacimiento", cliente.getFechaNacimiento().toString());
        return clienteDocument;
    }
    
    /**
     * Funcion crearClienteObject
     * Genera un Cliente a partir de Document.
     * @param clienteDocument: Document a convertir.
     * @return Cliente: Cliente obtenido de Document.
     */
    public static Cliente crearClienteObject(Document clienteDocument) {
        String nick = clienteDocument.getString("Nick");
        String contrasena = clienteDocument.getString("Contraseña");
        LocalDate fechaNacimiento = LocalDate.parse(clienteDocument.getString("Fecha nacimiento"));
        return new Cliente(nick, contrasena, fechaNacimiento);
    }
    
    /**
     * Funcion crearLocalDocument
     * Genera un Document a partir de Local.
     * @param local: Cliente a convertir.
     * @return Document: Document obtenido del Local.
     */
    public static Document crearLocalDocument(Local local){
        Document localDocument = new Document();
        localDocument.append("Nombre", local.getNombre());
        localDocument.append("Direccion", crearDireccionDocument(local.getDireccion()));
        localDocument.append("Descripcion", local.getDescripcion());
        localDocument.append("Tipo local", local.getTipo().name());
        int i = 1;
        for(Propietario propietario: local.getPropietarios()){
            Document propietarioDocument = crearPropietarioDocument(propietario);
            localDocument.append("Propietario " + i, propietarioDocument);
            i++;
        }
        return localDocument;
    }
    
    /**
     * Funcion crearLocalObject
     * Genera un Local a partir de un Document.
     * @param localDocument: Document a convertir.
     * @return Local: Local obtenido del Document.
     */
    public static Local crearLocalObject(Document localDocument){
        String nombre = localDocument.getString("Nombre");
        Direccion direccion = crearDireccionObject(localDocument.get("Direccion", Document.class));
        String descripcion = localDocument.getString("Descripcion");
        tipoLocal tipo = tipoLocal.parse(localDocument.getString("Tipo local"));
        List<Propietario> propietarios = new ArrayList<>();
        for(int i = 1; i <= 3; i++){
            try{
                Propietario propietario = crearPropietarioObject(localDocument.get("Propietario " + i, Document.class));
                propietarios.add(propietario);
            }
            catch(Exception e){}
        }
        Local local = new Local(nombre,direccion,descripcion,tipo,propietarios.get(0));
        for(int i = 1; i < size(propietarios); i++)
            local.addPropietario(propietarios.get(i));
        return local;
    }
    
    /**
     * Funcion crearContestacionDocument
     * Genera un Document a partir de Contestacion.
     * @param contestacion: Contestacion a convertir.
     * @return Document: Document obtenido de Contestacion.
     */
    public static Document crearContestacionDocument(Contestacion contestacion){
        Document contestacionDocument = new Document();
        contestacionDocument.append("Comentario", contestacion.getComentario());
        contestacionDocument.append("Fecha review", contestacion.getFechaReview().toString());
        contestacionDocument.append("Local", crearLocalDocument(contestacion.getLocal()));
        return contestacionDocument;
    }
    
    /**
     * Funcion crearContestacionObject
     * Genera un Contestacion a partir de un Document.
     * @param contestacionDocument: Document a convertir.
     * @return Contestacion: Contestacion obtenido del Document.
     */
    public static Contestacion crearContestacionObject(Document contestacionDocument){
        String comentario = contestacionDocument.getString("Comentario");
        LocalDate fechaReview = LocalDate.parse(contestacionDocument.getString("Fecha review"));
        Local local = crearLocalObject(contestacionDocument.get("Local", Document.class));
        return new Contestacion(comentario,fechaReview,local);
    }
    
    /**
     * Funcion crearReviewDocument
     * Genera un Document a partir de Review.
     * @param review: Review a convertir.
     * @return Document: Document obtenido de Review.
     */
    public static Document crearReviewDocument(Review review){
        Document reviewDocument = new Document();
        reviewDocument.append("Valoracion", review.getValoracion());
        reviewDocument.append("Comentario", review.getComentario());
        reviewDocument.append("Fecha review", review.getFechaReview().toString());
        reviewDocument.append("Local", crearLocalDocument(review.getLocal()));
        reviewDocument.append("Usuario", crearUsuarioDocument(review.getUsuario()));
        if(review.getContestacion() != null)
            reviewDocument.append("Contestacion", crearContestacionDocument(review.getContestacion()));
        return reviewDocument;
    }
    
    /**
     * Funcion crearReviewObject
     * Genera un Review a partir de un Document.
     * @param reviewDocument: Document a convertir.
     * @return Review: review obtenido del Document.
     */
    public static Review crearReviewObject(Document reviewDocument){
        int valoracion = reviewDocument.getInteger("Valoracion");
        String comentario = reviewDocument.getString("Comentario");
        LocalDate fechaReview = LocalDate.parse(reviewDocument.getString("Fecha review"));
        Local local = crearLocalObject(reviewDocument.get("Local", Document.class));
        Usuario usuario = crearUsuarioObject(reviewDocument.get("Usuario", Document.class));
        Review review =  new Review(valoracion,comentario,fechaReview,local,usuario);
        try{
            Contestacion contestacion = crearContestacionObject(reviewDocument.get("Contestacion", Document.class));
            review.setContestacion(contestacion);
        }catch(Exception e){}
        return review;
    }
    
    public static Document crearDonacionDocument(Donacion donacion){
        Document donacionDocument = new Document();
        donacionDocument.append("Nombre producto", donacion.getNombreProducto());
        donacionDocument.append("Cantidad producto", donacion.getCantidadProducto());
        donacionDocument.append("Local", crearLocalDocument(donacion.getLocal()));
        if(donacion.getUsuario() != null)
            donacionDocument.append("Usuario", crearUsuarioDocument(donacion.getUsuario()));
        else{
            donacionDocument.append("Usuario", null);
        }
        return donacionDocument;
    }
    
    public static Donacion crearDonacionObject(Document donacionDocument){
        String nombreProducto = donacionDocument.getString("Nombre producto");
        int cantidadProducto = donacionDocument.getInteger("Cantidad producto");
        Local local = crearLocalObject(donacionDocument.get("Local", Document.class));
        Donacion donacion = new Donacion(local,nombreProducto,cantidadProducto);
        if(donacionDocument.get("Usuario", Document.class) != null){
            Usuario usuario = crearUsuarioObject(donacionDocument.get("Usuario", Document.class));
            donacion.setUsuario(usuario);
        }
        return donacion;
    }
}
