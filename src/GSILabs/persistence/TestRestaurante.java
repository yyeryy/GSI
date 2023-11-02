package GSILabs.persistence;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Reserva;
import GSILabs.BModel.Restaurante;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TestRestaurante {
    public static void main(String[] args) throws IOException {

        String str = ""
                    + "Restaurante{"
                    +"nombre=Restaurante, "
                    +"dirección=Dirección{"
                        +"localidad=Pamplona, "
                        + "provincia=Navarra, "
                        + "calle=kalea, "
                        + "numero=1}, "
                    + "descripción=Local para el ejemplo S06, "
                    + "tipo=RESTAURANTE, "
                    + "propietario=Propietario{"
                        + "nick=Juanjo, "
                        + "contraseña=1234, "
                        + "fecha_de_nacimiento=2005-11-02}, "
                    + "precioMenu=24.9, "
                    + "capacidad=175, "
                    + "capacidad Mesa=4, "
                    + "reserva=Reserva{"
                        + "cliente=Cliente{"
                            + "nick=Alfonso, "
                            + "contraseña=1234, "
                            + "fecha_de_nacimiento=2000-01-01}, "
                        + "fechaReserva=2040-01-01, "
                        + "hora=00:00, "
                        + "descuento=0%}, "
                    + "reserva=Reserva{"
                        + "cliente=Cliente{"
                            + "nick=Alfonso, "
                            + "contraseña=1234, "
                            + "fecha_de_nacimiento=2000-01-01}, "
                        + "fechaReserva=2050-01-01, "
                        + "hora=00:00, "
                        + "descuento=0%}}";

        System.out.println("Original: \n" + str);
        Restaurante restaurante = parser.parseRestaurante(str);
        System.out.println("Final: \n" + restaurante.toString());
        System.out.println(str.equals(restaurante.toString()));
    }
    
 
}
