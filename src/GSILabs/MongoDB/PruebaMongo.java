package GSILabs.MongoDB;
import GSILabs.BModel.Direccion;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
public class PruebaMongo {
    private static com.mongodb.client.MongoClient mongoClient;
    private static final String DATABASE_NAME = "GSI";
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        Direccion direccion = new Direccion("Pamplona","Navarra","Calle Mayor",1);
        añadirDireccion(direccion);
        mostrarDirecciones();
        obtenerDirecciones();
    }
    
    public static boolean añadirDireccion(Direccion direccion){
        try{
            // Obtener la base de datos
            MongoClient mongoClient = MongoDBSingleton.getMongoClient();
            MongoDatabase database = MongoDBSingleton.getDatabase();
            // Crear la coleccion si no existe
            MongoCollection<Document> direccionCollection = database.getCollection("direccion");
            // Insertar direccion
            Document documentDireccion = new Document("_id_direccion", 1)
                .append("localidad",direccion.getLocalidad())
                .append("provincia",direccion.getProvincia())
                .append("calle",direccion.getCalle())
                .append("numero", direccion.getNumero());
            direccionCollection.insertOne(documentDireccion);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static List<Direccion> obtenerDirecciones()
    {
        // Obtener la base de datos
            MongoClient mongoClient = MongoDBSingleton.getMongoClient();
            MongoDatabase database = MongoDBSingleton.getDatabase();
            // Crear la coleccion si no existe
            MongoCollection<Document> direccionCollection = database.getCollection("direccion");
            // Consulto los datos introducidos
            FindIterable<Document> BBDDdirecciones = direccionCollection.find();
            MongoCursor<Document> direccionCursor = BBDDdirecciones.iterator();
            // almceno las direcciones
            List<Direccion> direcciones = new ArrayList<>();
            while (direccionCursor.hasNext()) {
                Document direccionResult = direccionCursor.next();
                String localidad = (String) direccionResult.get("localidad");
                String provincia = (String) direccionResult.get("provincia");
                String calle = (String) direccionResult.get("localidad");
                Integer numero = (Integer) direccionResult.get("numero");
                Direccion direccion = new Direccion(localidad,provincia,calle,numero);
                direcciones.add(direccion);
            }
            System.out.println(direcciones.toString());
            return direcciones;
    }
    public static void mostrarDirecciones()
    {
        // Obtener la base de datos
            MongoClient mongoClient = MongoDBSingleton.getMongoClient();
            MongoDatabase database = MongoDBSingleton.getDatabase();
            // Crear la coleccion si no existe
            MongoCollection<Document> direccionCollection = database.getCollection("direccion");
            // Consulto los datos introducidos
            FindIterable<Document> BBDDdirecciones = direccionCollection.find();
            MongoCursor<Document> direccionCursor = BBDDdirecciones.iterator();
            // Muestro las direcciones
            System.out.println("Direcciones:");
            while (direccionCursor.hasNext()) {
                Document direccionResult = direccionCursor.next();
                System.out.println(direccionResult);
            }
    }
}