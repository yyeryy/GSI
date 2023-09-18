/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author 34636
 */
public class Reserva {
    /*
    Tanto los restaurantes como los Bares son Reservables. El sistema debe poder almacenar Reservas
    de Clientes para Bares o Restaurantes. Cada Reserva incluye únicamente la fecha y hora en que
    se efectuará, así como un posible porcentaje de descuento.
     */
    private LocalDate fecha;
    private LocalTime hora;
    private int descuento;
    
    public Reserva(LocalDate fecha, LocalTime hora, int descuento) {
        this.fecha = fecha;
        this.hora = hora;
        this.descuento = descuento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }  


    @Override
    public int hashCode() {
        return Objects.hash(fecha,hora, descuento);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (getClass() != obj.getClass())
          return false;
        Reserva other = (Reserva) obj;
        boolean result = (
            Objects.equals(fecha, other.fecha)&&
            Objects.equals(hora,other.hora) &&
            Objects.equals(descuento, other.descuento)
        );
        return result;
    }
     
}