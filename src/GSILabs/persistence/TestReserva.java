package GSILabs.persistence;

import GSILabs.BModel.Cliente;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Reserva;
import java.io.IOException;
import java.time.LocalDate;

public class TestReserva {
    public static void main(String[] args) throws IOException {

        Cliente cliente = new Cliente("Alfonso", "1234", LocalDate.of(2000,1,1));
        String str = "Reserva{cliente="+ cliente.toString()+", fechaReserva=2000-12-06, hora=02:00, descuento=20%}";

        System.out.println("Original: \n" + str);
        Reserva reserva = parser.parseReserva(str);
        System.out.println("Final: \n" + reserva.toString());
        System.out.println(str.equals(str.equals(reserva.toString())));
    }
    
 
}
