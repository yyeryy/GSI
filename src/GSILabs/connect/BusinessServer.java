package GSILabs.connect;

import GSILabs.BSystem.PublicBusinessSystem;
import GSILabs.persistence.XMLParsingException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Remote;
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
public class BusinessServer implements Serializable{

    public static void main(String[] args) throws RemoteException, UnknownHostException {
        PublicBusinessSystem pbs = new PublicBusinessSystem();
        ClientGateway clientStub = null;
        AdminGateway adminStub = null;
        

    //Copiar los valores de BusinessSystem a PublicBusinessSystem
    //Suponiendo que tienes un método en PublicBusinessSystem para hacerlo
    
        //Poblacion
        
        try {
            File file = new File("bs.xml");
            pbs = PublicBusinessSystem.parseXMLFilePublic(file);
            
        }catch(XMLParsingException | IOException e){
            System.out.println("Población fallida");
        }
        
        try{
            //Generar un stub del objeto
            Remote stub = UnicastRemoteObject.exportObject( pbs, 0);
            clientStub = (ClientGateway) stub;
            adminStub = (AdminGateway) stub;

            //Crear un registro en el puerto 1099
            Registry reg = LocateRegistry.createRegistry(1099);

            //Asociar el stub a los identificadores ClientGateway y AdminGateway
            reg.rebind("ClientGateway", clientStub);
            reg.rebind("AdminGateway", adminStub);
            
            System.out.println("Servidor funcionando");
            System.out.println("IP: "+InetAddress.getLocalHost().getHostAddress());
            System.out.println("Port: 1099");
        
        } catch (RemoteException e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }   
}
