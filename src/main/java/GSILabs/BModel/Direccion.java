package GSILabs.BModel;

/**
 *
 * @author 34636
 */
public class Direccion {
    /*El sistema almacena información de locales de ocio en España. Un local tienen un nombre y está
en una localización concreta. (Localidad, Provincia, Calle y Número). No puede haber dos locales
en la misma dirección. Los locales pueden almacenar una breve descripción de no más de 300
caracteres.*/
    public String localidad;
    public String provincia;
    public String calle;
    public int numero;

    public Direccion(String localidad, String provincia, String calle, int numero) {
	this.localidad = localidad;
	this.provincia = provincia;
	this.calle = calle;
	this.numero = numero;
    }

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public String getLocalidad() {
	return localidad;
    }
    
    public void setLocalidad(String localidad) {
	this.localidad = localidad;
    }
    
    public String getProvincia() {
	return provincia;
    }
    
    public void setProvincia(String provincia) {
	this.provincia = provincia;
    }
    
    public String getCalle() {
	return calle;
    }
    
    public void setCalle(String calle) {
	this.calle = calle;
    }
    
    public int getNumero() {
	return numero;
    }
    
    public void setNumero(int numero) {
	this.numero = numero;
    }
//</editor-fold>

    @Override
    public String toString() {
        return "Dirección{" + "localidad=" + localidad + ", provincia=" + provincia + ", calle=" + calle + ", numero=" + numero + '}';
    }
}
