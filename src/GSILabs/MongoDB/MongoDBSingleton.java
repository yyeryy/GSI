package GSILabs.MongoDB;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDBSingleton {
    private static com.mongodb.client.MongoClient mongoClient;
    private static final String DATABASE_NAME = "GSI";

    // Configuración del nivel de registro en el cargador estático
    static {
        // Configurar el nivel de registro para el logger de MongoDB a WARNING
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.WARNING);

        // También puedes desactivar mensajes para el logger JULLogger específico
        Logger juLogger = Logger.getLogger("com.mongodb.diagnostics.logging.JULLogger");
        juLogger.setLevel(Level.WARNING);
        juLogger.setUseParentHandlers(false);

        // Configurar el manejador de consola con nivel de WARNING para evitar mensajes INFO
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.WARNING);
        juLogger.addHandler(consoleHandler);
    }

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
