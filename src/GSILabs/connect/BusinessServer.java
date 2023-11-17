package GSILabs.connect;

import GSILabs.BSystem.BusinessSystem;
import GSILabs.BSystem.PublicBusinessSystem;
import GSILabs.persistence.XMLParsingException;
import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
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
        PublicBusinessSystem pbs = new PublicBusinessSystem();
        ClientGateway clientStub = null;
        AdminGateway adminStub = null;
        

    // Copiar los valores de BusinessSystem a PublicBusinessSystem
    // Suponiendo que tienes un método en PublicBusinessSystem para hacerlo
    
        //poblacion
        
        try {
            File file = new File("bs.xml");
            pbs = PublicBusinessSystem.parseXMLFilePublic(file);
            
        }catch(XMLParsingException | IOException e){
            System.out.println("Población fallida");
        }
        
        try{
            // generar un stub del objeto
            clientStub = (ClientGateway) UnicastRemoteObject.exportObject( pbs, 0);
            adminStub = (AdminGateway) UnicastRemoteObject.exportObject(pbs, 0);

            // crear un registro en el puerto 1099
            Registry reg = LocateRegistry.createRegistry(1099);

            // asociar el stub a los identificadores ClientGateway y AdminGateway
            reg.rebind("ClientGateway", clientStub);
            reg.rebind("AdminGateway", adminStub);
        
        } catch (RemoteException e) {
            /*try {
                Registry registry = LocateRegistry.createRegistry(1100);

                registry.rebind("ClientGateway", clientStub);
                registry.rebind("AdminGateway", adminStub);
            } catch (RemoteException ex) {
                System.out.println("error");
            }*/
        }

        //System.out.println("Servidor RMI listo...");
        
        
    }
    
}
