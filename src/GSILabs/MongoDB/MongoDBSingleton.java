package GSILabs.MongoDB;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBSingleton {
    private static com.mongodb.client.MongoClient mongoClient;
    private static final String DATABASE_NAME = "GSI";

    private MongoDBSingleton() {
        // Constructor privado para evitar instanciación directa
    }

    public static com.mongodb.client.MongoClient getMongoClient() {
        if (mongoClient == null) {
            // Configurar la conexión a MongoDB
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString("mongodb+srv://GSI:G3GSI2023@gsi.lvvnusj.mongodb.net"))
                    .build();

            // Crear una instancia única de MongoClient
            mongoClient = MongoClients.create(settings);
        }
        return mongoClient;
    }

    public static MongoDatabase getDatabase() {
        return getMongoClient().getDatabase(DATABASE_NAME);
    }
}