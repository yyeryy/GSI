package GSILabs.connect;

import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Propietario;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase AdminHub
 * Clase con la que pediremos al usuario datos para conectarse al servidor
 * RMI y ejecutar funciones de dicho objeto remoto.
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 19.11.2023
 */
public class AdminHub {
    public static void main(String[] args) {
        try {
            // Pedir al usuario la dirección del servidor
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese la dirección del servidor: ");
            String serverAddress = scanner.nextLine();

            // Pedir al usuario el puerto del servidor
            System.out.print("Ingrese el puerto del servidor: ");
            int serverPort = scanner.nextInt();

            // Obtener el objeto remoto desde el servidor
            AdminGateway adminGateway = (AdminGateway) LocateRegistry.getRegistry(serverAddress, serverPort).lookup("AdminGateway");
            
            // Eliminar un Local del objeto remoto
            Local local = new Local("La Vinoteca del Valle", new Direccion("Granada", "Granada", "Vinos Selectos", 12), "Ofrece una amplia variedad de vinos de la región", Local.tipoLocal.BAR, new Propietario("Sara", "pass7890", LocalDate.of(1988, 2, 10)));
            System.out.println("Resultado eliminar local: " + adminGateway.eliminaLocal(local));
        } catch(RemoteException | NotBoundException ex) {
            Logger.getLogger(AdminHub.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}
