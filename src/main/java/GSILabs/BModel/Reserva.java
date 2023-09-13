/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;
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
    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minuto;
    private float descuento;

    public Reserva(int dia, int mes, int ano, int hora, int minuto, int descuento)
    {
        setDia(dia);
        setMes(mes);
        setAno(ano);
        setHora(hora);
        setMinuto(minuto);
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
    
    public int[] getFecha()
    {
        int[] fecha = {getDia(),getHora(),getAno()};
        return fecha;
    }
    
    public int[] getHoraYMinuto()
    {
        int[] horaYMinuto = {getHora(), getMinuto()};
        return horaYMinuto;
    }
}
