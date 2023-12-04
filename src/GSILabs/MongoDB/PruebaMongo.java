package GSILabs.MongoDB;
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
import org.bson.Document;
public class PruebaMongo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String username = URLEncoder.encode("GSI", "UTF-8");
        String password = URLEncoder.encode("G3GSI2023", "UTF-8");
        String cluster = "gsi.lvvnusj.mongodb.net/";
        String connectionString = "mongodb+srv://" + username + ":" + password + "@" + cluster +"?retryWrites=true&w=majority";
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();
        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("GSI");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                
                // OPERACIONES //
                // Crear coleccion usuario
                MongoCollection<Document> usuariosCollection = database.getCollection("usuarios");
                
                // Insertar usuario
                Document usuario = new Document("_id", 1)
                    .append("nombre", "John Doe")
                    .append("edad", 30)
                    .append("correo", "john.doe@example.com");
                usuariosCollection.insertOne(usuario);
                
                // Crear coleccion tareas
                MongoCollection<Document> tareasCollection = database.getCollection("tareas");
                
                // Insertar una tarea asociada al usuario con ID 1
                Document tarea = new Document("_id", 1)
                    .append("descripcion", "Hacer la compra")
                    .append("fecha", "2023-01-15")
                    .append("usuario_id", 1);
                tareasCollection.insertOne(tarea);
                
                // Consultar todos los usuarios
                FindIterable<Document> usuarios = usuariosCollection.find();
                MongoCursor<Document> usuariosCursor = usuarios.iterator();
                
                // Imprimir resultados de usuarios
                System.out.println("Usuarios:");
                while (usuariosCursor.hasNext()) {
                    Document usuarioResult = usuariosCursor.next();
                    System.out.println(usuarioResult.toJson());
                }

                // Consultar todas las tareas
                FindIterable<Document> tareas = tareasCollection.find();
                MongoCursor<Document> tareasCursor = tareas.iterator();

                // Imprimir resultados de tareas
                System.out.println("Tareas:");
                while (tareasCursor.hasNext()) {
                    Document tareaResult = tareasCursor.next();
                    System.out.println(tareaResult.toJson());
                }
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}