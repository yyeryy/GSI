package GSILabs.connect;

import GSILabs.BModel.Local;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import GSILabs.BSystem.PublicBusinessSystem;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import GSILabs.persistence.XMLParsingException;
import java.io.File;
import java.io.IOException;

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
        
        //Instanciamos PublicBusinessSystem para implementar los interfaces
        PublicBusinessSystem pbs = new PublicBusinessSystem();
        
        //Inicializamos a null las variables de los stubs
        ClientGateway clientStub = null;
        AdminGateway adminStub = null;
        
        //Población mediante XML
        try {
            File file = new File("testbs.xml");
            pbs = PublicBusinessSystem.parseXMLFilePublic(file);
            
        }catch(XMLParsingException | IOException e){
            System.out.println("Población fallida");
        }
        
        /*for (Usuario a : pbs.usuarios) {
            System.out.println(""+a);
        }
        for (Review a : pbs.reviews) {
            System.out.println(""+a);
        }
        for (Local a : pbs.locales) {
            System.out.println(""+a);
        }*/
        
        try{
            //Generar un stub del objeto
            Remote stub = UnicastRemoteObject.exportObject(pbs, 0);
            clientStub = (ClientGateway) stub;
            adminStub = (AdminGateway) stub;

            //Crear un registro en el puerto 1099
            int puerto = 109;
            Registry reg = LocateRegistry.createRegistry(puerto);
            
            //Establecemos los tags
            String tagCliente = "ClientGateway";
            String tagAdmin = "AdminGateway";

            //Asociar el stub a los identificadores ClientGateway y AdminGateway
            reg.rebind(tagCliente, clientStub);
            reg.rebind(tagAdmin, adminStub);
            
            System.out.println("Servidor funcionando");
            System.out.println("IP: "+InetAddress.getLocalHost().getHostAddress());
            System.out.println("Port: "+puerto);
        
        } catch (RemoteException e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }   
}
