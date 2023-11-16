package GSILabs.connect;

import GSILabs.BSystem.PublicBusinessSystem;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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
