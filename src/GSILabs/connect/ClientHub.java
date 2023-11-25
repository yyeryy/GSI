package GSILabs.connect;

/**
 * Clase ClientHub
 * Clase ejecutora del cliente que se conecta con el servidor.
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 15.11.2023
 */
import GSILabs.BModel.Bar;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class ClientHub {

    public static void main(String[] args) {
        try {
            //Pedir al usuario la dirección del servidor
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese la dirección del servidor: ");
            String serverAddress = scanner.nextLine();

            //Pedir al usuario el puerto del servidor
            System.out.print("Ingrese el puerto del servidor: ");
            int serverPort = scanner.nextInt();
            scanner.nextLine();
            
            //Establecemos el tag
            String tagCliente = "ClientGateway";

            //Obtener el objeto remoto desde el servidor
            ClientGateway clientGateway = (ClientGateway) LocateRegistry.getRegistry(serverAddress, serverPort).lookup(tagCliente);
            
            //Pedir al usuario el nombre de la ciudad
            System.out.print("Ingrese el nombre de la ciudad: ");
            String ciudad = scanner.nextLine();
            
            Bar bestBar = clientGateway.mejorBar(ciudad);
            System.out.println("El mejor bar de la ciudad es: " + bestBar.getNombre());

        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
            //System.out.println("Error en la ejecución del código.");
            //System.out.println("Excepción: \n" + e);
        }
    }
}


