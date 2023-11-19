/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GSILabs.connect;

/**
 *
 * @author rauli
 */
import GSILabs.BModel.Bar;
import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class ClientHub {

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
            ClientGateway clientGateway = (ClientGateway) LocateRegistry.getRegistry(serverAddress, serverPort).lookup("ClientGateway");
            
            // Pedir al usuario el nombre de la ciudad
            System.out.print("Ingrese el nombre de la ciudad: ");
            String ciudad = scanner.nextLine();

            Bar bestBar = clientGateway.mejorBar(ciudad);
            System.out.println("El mejor bar de la ciudad es: " + bestBar);

        } catch (NotBoundException | RemoteException e) {
        }
    }
}


