package GSILabs.MongoDB;

import GSILabs.BModel.Local;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import GSILabs.BSystem.BusinessSystem;
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
        // LOCALES
        try{
            // Borro lo viejo, sino se duplicara
            database.getCollection("Locales").drop();
            MongoCollection<Document> localesCollection = database.getCollection("Locales");
           
            for(Local local : bs.locales){
                byte[] serializedLocal = serializeObject(local);
                Document document = new Document("serializedLocal",bytesToBinary(serializedLocal));
                localesCollection.insertOne(document);
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
                byte[] serializedReview = serializeObject(review);
                Document document = new Document("serializedReview",bytesToBinary(serializedReview));
                reviewsCollection.insertOne(document);
            }
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido subir la lista de Reviews");
            return false;
        }
        // USUARIOS
        try{
            // Borro lo viejo, sino se duplicara
            database.getCollection("Usuarios").drop();
            MongoCollection<Document> usuariosCollection = database.getCollection("Usuarios");
            for(Usuario usuario : bs.usuarios){
                byte[] serializedUsuario = serializeObject(usuario);
                Document document = new Document("serializedUsuario",bytesToBinary(serializedUsuario));
                usuariosCollection.insertOne(document);
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
            for(Document document : iterable){
                byte[] serializedLocal = binaryToBytes((Binary)document.get("serializedLocal"));
                Local local = deserializeObject(serializedLocal,Local.class);
                locales.add(local);
            }
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido obtener la lista de Locales");
            return null;
        }
        // Review
        List<Review> reviews = new ArrayList<>();
        try{
            MongoCollection<Document> reviewsCollection = database.getCollection("Reviews");
            FindIterable<Document> iterable = reviewsCollection.find();
            for(Document document : iterable){
                byte[] serializedReview = binaryToBytes((Binary)document.get("serializedReview"));
                Review review = deserializeObject(serializedReview,Review.class);
                reviews.add(review);
            }
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido obtener la lista de Reviews");
            return null;
        }
        // Usuarios
        List<Usuario> usuarios = new ArrayList<>();
        try{
            MongoCollection<Document> usuariosCollection = database.getCollection("Usuarios");
            FindIterable<Document> iterable = usuariosCollection.find();
            for(Document document : iterable){
                byte[] serializedUsuarios = binaryToBytes((Binary)document.get("serializedUsuario"));
                Usuario usuario = deserializeObject(serializedUsuarios, Usuario.class);
                usuarios.add(usuario);
            }
        }
        catch(Exception e){
            System.out.println("ERROR: No se ha podido obtener la lista de Usuarios");
            return null;
        }
        // Rellenar BusinessSystem y devolver
        bs.locales = (ArrayList<Local>) locales;
        bs.reviews = (ArrayList<Review>) reviews;
        bs.usuarios = (ArrayList<Usuario>) usuarios;
        return bs;
    }
    
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
