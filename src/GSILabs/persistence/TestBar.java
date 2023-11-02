package GSILabs.persistence;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Reserva;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TestBar {
    public static void main(String[] args) throws IOException {
        String str = ""
                    + "Bar{"
                    +"nombre=Bar, "
                    +"dirección=Dirección{"
                        +"localidad=Pamplona, "
                        + "provincia=Navarra, "
                        + "calle=kalea, "
                        + "numero=1}, "
                    + "descripción=Local para el ejemplo S06, "
                    + "tipo=BAR, "
                    + "propietario=Propietario{"
                        + "nick=Juanjo, "
                        + "contraseña=1234, "
                        + "fecha_de_nacimiento=2005-11-02}, "
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
        Bar bar = parser.parseBar(str);
        System.out.println("Final: \n" + bar.toString());
        System.out.println(str.equals(bar.toString()));
    }
    
 
}
