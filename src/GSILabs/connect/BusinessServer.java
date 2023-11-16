package GSILabs.connect;

import GSILabs.BSystem.PublicBusinessSystem;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Clase BusinessServer
 * Clase con la que implementaremos los métodos implementados de las interfaces
 * para publicarla en un registro RMI (puerto 1099).
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 15.11.2023
 */
public class BusinessServer {

    public static void main(String[] args) throws RemoteException {
        LocalFinder pbs = new PublicBusinessSystem();
        
        //poblacion
        
        try {
            File file = new File("bs.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = (Document) builder.parse(file);
            
            
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Población fallida");
        }
        
        // generar un stub del objeto
        LocalFinder stub = (LocalFinder) UnicastRemoteObject.exportObject(pbs, 0);

        // crear un registro en el puerto 1099
        Registry reg = LocateRegistry.createRegistry(1099);

        // asociar el stub a los identificadores ClientGateway y AdminGateway
        reg.rebind("ClientGateway", stub);
        reg.rebind("AdminGateway", stub);

        System.out.println("Servidor RMI listo...");
        
        
    }
    
}
