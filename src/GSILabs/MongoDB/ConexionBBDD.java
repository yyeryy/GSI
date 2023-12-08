package GSILabs.MongoDB;

import GSILabs.BModel.Local;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import GSILabs.BSystem.BusinessSystem;
import static GSILabs.MongoDB.MongoDBUtils.crearLocalDocument;
import static GSILabs.MongoDB.MongoDBUtils.crearLocalObject;
import static GSILabs.MongoDB.MongoDBUtils.crearReviewDocument;
import static GSILabs.MongoDB.MongoDBUtils.crearReviewObject;
import static GSILabs.MongoDB.MongoDBUtils.crearUsuarioDocument;
import static GSILabs.MongoDB.MongoDBUtils.crearUsuarioObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.Binary;

/**
 * ConexionBBDD: Permite la conexion entre la Base de Datos Online y BusinessSystem Local.
 * @author Javier Aranguren.
 */
public class ConexionBBDD {
    /**
     * Funcion CargarDatos
     * Copia el contenido del BusinessSystem local en la Base de Datos Online, borra lo que hay en la Base de Datos.
     * @param bs: BusinessSytem Local que se quiero cargar en la Base de Datos.
     * @return boolean: Indica si se han subido los datos con exito.
    */
    public static boolean CargarDatos(BusinessSystem bs){
        // Conectar a la base de datos
        MongoClient mongoClient = MongoDBSingleton.getMongoClient();
        MongoDatabase database = MongoDBSingleton.getDatabase();
        // Subir el contenido de Business System
        // LOCAL
        try{
            // Borro lo viejo, sino se duplicara
            database.getCollection("Locales").drop();
            MongoCollection<Document> localesCollection = database.getCollection("Locales");
            for(Local local : bs.locales){
                Document localDocument = crearLocalDocument(local);
                localesCollection.insertOne(localDocument);
            }
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido subir la lista de Locales");
            return false;
        }
        
        // REVIEWS
        try{
            // Borro lo viejo, sino se duplicara
            database.getCollection("Reviews").drop();
            MongoCollection<Document> reviewsCollection = database.getCollection("Reviews");
            for(Review review : bs.reviews){
                Document reviewDocument = crearReviewDocument(review);
                reviewsCollection.insertOne(reviewDocument);
            }
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido subir la lista de Reviews");
            e.printStackTrace();
            return false;
        }
        
        // USUARIOS
        try{
            // Borro lo viejo, sino se duplicara
            database.getCollection("Usuarios").drop();
            MongoCollection<Document> usuariosCollection = database.getCollection("Usuarios");
            for(Usuario usuario : bs.usuarios){
                Document usuarioDocument = crearUsuarioDocument(usuario);
                usuariosCollection.insertOne(usuarioDocument);
            }
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido subir la lista de Usuarios");
            return false;
        }
        return true;
    }
    
    /**
     * Funcion DescargarDatos
     * Copia el contenido de la Base de Datos Online en el BusinessSystem local.
     * @return: null si se produce algun error.
     * @return: BusinessSytem Local que se ha descargado de la Base de Datos.
     */
    public static BusinessSystem DescargarDatos(){
        // Conectar a la base de datos
        MongoClient mongoClient = MongoDBSingleton.getMongoClient();
        MongoDatabase database = MongoDBSingleton.getDatabase();
        // Crear BusinnesSytem
        BusinessSystem bs = new BusinessSystem();
        // Obtener los datos de la base de datos
        
// LOCALES
        List<Local> locales = new ArrayList<>();
        try{
            MongoCollection<Document> localesCollection = database.getCollection("Locales");
            FindIterable<Document> iterable = localesCollection.find();
            for(Document localDocument: iterable){
                Local local = crearLocalObject(localDocument);
                locales.add(local);
            }
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido obtener la lista de Locales");
            return null;
        }
        // REVIEW
        List<Review> reviews = new ArrayList<>();
        try{
            MongoCollection<Document> reviewsCollection = database.getCollection("Reviews");
            FindIterable<Document> iterable = reviewsCollection.find();
            for(Document reviewDocument : iterable){
                Review review = crearReviewObject(reviewDocument);
                reviews.add(review);
            }
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido obtener la lista de Reviews");
            return null;
        }
        
        // USUARIOS
        List<Usuario> usuarios = new ArrayList<>();
        try{
            MongoCollection<Document> usuariosCollection = database.getCollection("Usuarios");
            FindIterable<Document> iterable = usuariosCollection.find();
            for(Document usuarioDocument : iterable){
                Usuario usuario = crearUsuarioObject(usuarioDocument);
                usuarios.add(usuario);
            }
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido obtener la lista de Usuarios");
            e.printStackTrace();
            return null;
        }
        // Rellenar BusinessSystem y devolver
        bs.locales = (ArrayList<Local>) locales;
        bs.reviews = (ArrayList<Review>) reviews;
        bs.usuarios = (ArrayList<Usuario>) usuarios;
        return bs;
    }
    
    /**
     * Funcion CargarListaUsuarios
     * Copia el contenido del ArrayList usuarios local en la Base de Datos Online, borra lo que hay en usuarios de la Base de Datos.
     * @param usuarios: lista de usuarios Local que se quiero cargar en la Base de Datos.
     * @return boolean: Indica si se han subido los datos con exito.
    */
    public static boolean CargarListaUsuarios(ArrayList<Usuario> usuarios){
        // Conectar a la base de datos
        MongoClient mongoClient = MongoDBSingleton.getMongoClient();
        MongoDatabase database = MongoDBSingleton.getDatabase();
        // Subir el contenido de la lista
        try{
            database.getCollection("Usuarios").drop();
            MongoCollection<Document> usuariosCollection = database.getCollection("Usuarios");
            for(Usuario usuario:usuarios){
                Document usuarioDocument = crearUsuarioDocument(usuario);
                usuariosCollection.insertOne(usuarioDocument);
            }
            return true;
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido subir la lista de Usuarios");
            return false;
        }
    }
    
    /**
     * Funcion DescargarListaUsuarios
     * Copia el contenido de usuarios la Base de Datos Online en la lista usuarios local.
     * @return: null si se produce algun error.
     * @return: lista usuarios Local que se ha descargado de la Base de Datos.
     */
    public static ArrayList<Usuario> DescargarListaUsuarios(){
        // Conectar a la base de datos
        MongoClient mongoClient = MongoDBSingleton.getMongoClient();
        MongoDatabase database = MongoDBSingleton.getDatabase();
        // Crear Lista Usuarios
        List<Usuario> usuarios = new ArrayList<>();
        try{
            MongoCollection<Document> usuariosCollection = database.getCollection("Usuarios");
            FindIterable<Document> iterable = usuariosCollection.find();
            for(Document usuarioDocument : iterable){
                Usuario usuario = crearUsuarioObject(usuarioDocument);
                usuarios.add(usuario);
            }
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido obtener la lista de Usuarios");
            return null;
        }
        return (ArrayList<Usuario>) usuarios;
    }
    
    public static boolean cargarUsuario(Usuario usuario){
        // Conectar a la base de datos
        MongoClient mongoClient = MongoDBSingleton.getMongoClient();
        MongoDatabase database = MongoDBSingleton.getDatabase();
        
        // Subir el usuarios
        try{
            MongoCollection<Document> usuariosCollection = database.getCollection("Usuarios");
            Document usuarioDocument = crearUsuarioDocument(usuario);
            usuariosCollection.insertOne(usuarioDocument);
            return true;
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido subir el usuario a la lista de Usuarios");
            return false;
        }
    }
    
    /**
     * Funcion CargarListaLocales
     * Copia el contenido del ArrayList locales local en la Base de Datos Online, borra lo que hay en locales de la Base de Datos.
     * @param locales: lista de locales Local que se quiero cargar en la Base de Datos.
     * @return boolean: Indica si se han subido los datos con exito.
    */
    public static boolean CargarListaLocales(ArrayList<Local> locales){
        // Conectar a la base de datos
        MongoClient mongoClient = MongoDBSingleton.getMongoClient();
        MongoDatabase database = MongoDBSingleton.getDatabase();
        // Subir el contenido de la lista
        try{
            database.getCollection("Locales").drop();
            MongoCollection<Document>localesCollection = database.getCollection("Locales");
            for(Local local:locales){
                Document localDocument = crearLocalDocument(local);
                localesCollection.insertOne(localDocument);
            }
            return true;
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido subir la lista de Locales");
            return false;
        }
    }
    
    /**
     * Funcion DescargarListaLocales
     * Copia el contenido de locales la Base de Datos Online en la lista locales local.
     * @return: null si se produce algun error.
     * @return: lista locales Local que se ha descargado de la Base de Datos.
     */
    public static ArrayList<Local> DescargarListaLocal(){
        // Conectar a la base de datos
        MongoClient mongoClient = MongoDBSingleton.getMongoClient();
        MongoDatabase database = MongoDBSingleton.getDatabase();
        // Crear Lista Usuarios
        List<Local> locales = new ArrayList<>();
        try{
            MongoCollection<Document> localesCollection = database.getCollection("Locales");
            FindIterable<Document> iterable = localesCollection.find();
            for(Document localDocument : iterable){
                Local local = crearLocalObject(localDocument);
                locales.add(local);
            }
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido obtener la lista de Locales");
            return null;
        }
        return (ArrayList<Local>) locales;
    }
    
    public static boolean cargarLocal(Local local){
        // Conectar a la base de datos
        MongoClient mongoClient = MongoDBSingleton.getMongoClient();
        MongoDatabase database = MongoDBSingleton.getDatabase();
        
        // Subir el usuarios
        try{
            MongoCollection<Document> localesCollection = database.getCollection("Locales");
            Document localDocument = crearLocalDocument(local);
            localesCollection.insertOne(localDocument);
            return true;
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido subir el local a la lista de Locales");
            return false;
        }
    }
    
    /**
     * Funcion CargarListaReviews
     * Copia el contenido del ArrayList reviews local en la Base de Datos Online, borra lo que hay en reviews de la Base de Datos.
     * @param reviews: lista de reviews Local que se quiero cargar en la Base de Datos.
     * @return boolean: Indica si se han subido los datos con exito.
    */
    public static boolean CargarListaReviews(ArrayList<Review> reviews){
        // Conectar a la base de datos
        MongoClient mongoClient = MongoDBSingleton.getMongoClient();
        MongoDatabase database = MongoDBSingleton.getDatabase();
        // Subir el contenido de la lista
        try{
            database.getCollection("Reviews").drop();
            MongoCollection<Document>reviewsCollection = database.getCollection("Reviews");
            for(Review review:reviews){
                Document reviewDocument = crearReviewDocument(review);
                reviewsCollection.insertOne(reviewDocument);
            }
            return true;
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido subir la lista de Locales");
            return false;
        }
    }
    
    /**
     * Funcion DescargarListaReviews
     * Copia el contenido de reviews la Base de Datos Online en la lista revies local.
     * @return: null si se produce algun error.
     * @return: lista revies Local que se ha descargado de la Base de Datos.
     */
    public static ArrayList<Review> DescargarListaReview(){
        // Conectar a la base de datos
        MongoClient mongoClient = MongoDBSingleton.getMongoClient();
        MongoDatabase database = MongoDBSingleton.getDatabase();
        // Crear Lista Usuarios
        List<Review> reviews = new ArrayList<>();
        try{
            MongoCollection<Document> reviewsCollection = database.getCollection("Reviews");
            FindIterable<Document> iterable = reviewsCollection.find();
            for(Document reviewDocument : iterable){
                Review review = crearReviewObject(reviewDocument);
                reviews.add(review);
            }
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido obtener la lista de Usuarios");
            return null;
        }
        return (ArrayList<Review>) reviews;
    }
    
    public static boolean cargarReview(Review review){
        // Conectar a la base de datos
        MongoClient mongoClient = MongoDBSingleton.getMongoClient();
        MongoDatabase database = MongoDBSingleton.getDatabase();
        
        // Subir el usuarios
        try{
            MongoCollection<Document> reviewsCollection = database.getCollection("Reviews");
            Document reviewDocument = crearReviewDocument(review);
            reviewsCollection.insertOne(reviewDocument);
            return true;
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido subir la review a la lista de Reviews");
            return false;
        }
    }
    
    /* NO SE USAN */
    /**
     * serializeObject serializa el objeto para poder almacenarlo en la Base de Datos.
     * @param obj: Objeto serializable que se quiere almacenar.
     * @return: Bytes a almacenar en la BBDD
     * @throws IOException 
     */
    private static byte[] serializeObject(Object obj) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
            return bos.toByteArray();
        }
    }
    
    /**
     * deserializeObject deserializa el objeto que se ha obtenido de la Base de Datos.
     * @param <T>: Tipo de objeto que obtiene.
     * @param bytes: Bytes a conevertir en Object.
     * @param clazz: Clase del Objeto serializable que se quiere obtener.
     * @return: Objeto deseriazado.
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private static <T> T deserializeObject(byte[] bytes, Class<T> clazz) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis)) {
            return clazz.cast(ois.readObject());
        }
    }

    /**
     * Método auxiliar para convertir Binary a array de bytes
     * @param binary
     * @return 
     */
    private static byte[] binaryToBytes(Binary binary) {
        return binary.getData();
    }

    /**
     * Método auxiliar para convertir array de bytes a Binary
     * @param bytes
     * @return 
     */
    private static Binary bytesToBinary(byte[] bytes) {
        return new Binary(bytes);
    }
}
