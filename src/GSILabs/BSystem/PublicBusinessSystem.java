package GSILabs.BSystem;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Local;
import GSILabs.BModel.Restaurante;
import GSILabs.BModel.Review;
import GSILabs.connect.AdminGateway;
import GSILabs.connect.ClientGateway;
import java.rmi.RemoteException;
import java.time.LocalDate;

/**
 * Clase PublicBusinessSystem
 * Clase que extiende a la clase BusinessSystem, para la implementación de las
 * interfaces ClientGateway y AdminGateway.
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 15.11.2023
 */
public class PublicBusinessSystem extends BusinessSystem implements ClientGateway, AdminGateway {

    /**
     * Incorpora una nueva review al sistema, en caso de que sus datos (Usuario, 
     * Local) sean correctos y no haya otra introducida para la misma fecha.
     * @param r La review a introducir al sistema.
     * @return True si y solo si la operacion fue completada.
     * @throws RemoteException If some error happens in the remove invocation.
     */
    @Override
    public boolean insertaReview(Review r) throws RemoteException {
        if(existeNick(r.getUsuario().getNick()) && (r.getValoracion() >= 0) && (r.getValoracion() <= 5) && r.getComentario().length() >= 0 && r.getComentario().length() <= 500){
            if(existeRewiew(r.getUsuario(), r.getLocal(), LocalDate.now())){
                for(Local l : locales){
                    if(l.getDireccion().equals(r.getLocal().getDireccion())){
                        return false;
                    }
                }
            }
            reviews.add(r);
            return true;
        }
        return false;
    }

    /**
     * Elimina una review del sistema, siempre y cuando exista y no tenga una 
     * contestacion asociada.
     * @param r Review a eliminar
     * @return True si y solo si la operacion fue completada.
     * @throws RemoteException If some error happens in the remove invocation.
     */
    @Override
    public boolean quitaReview(Review r) throws RemoteException {
        if(reviews.isEmpty()){
            return false;
        }
        return reviews.remove(r);
    }

    /**
     * Devuelve el mejor bar de la cuidad que coincida con el nombre.
     * @param ciudad nombre de la cuidad
     * @return El bar con mejor puntuación media de la cuidad
     * @throws RemoteException If some error happens in the remove invocation.
     */
    @Override
    public Bar mejorBar(String ciudad) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Devuelve los num restaurantes de la cuidad con mejores puntuaciones.
     * @param ciudad nombre de la cuidad
     * @param num numero de restaurantes
     * @return Una tabla con Num restaurantes, conteniendo nulls en caso de que no existan tantos.
     * @throws RemoteException If some error happens in the remove invocation.
     */
    @Override
    public Restaurante[] mejoresRestaurantes(String ciudad, Integer num) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Retrieves an Locals that matches EXACTLY (up to the case and/or spacing),
     * the name.
     * @param name Name of the local
     * @return The local , or a null response
     * @throws RemoteException If some error happens in the remove invocation.
     */
    @Override
    public Local getLocal(String name) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Retrieves all the Locals that match, either partial o totally (up to the case and/or spacing),
     * the name given as argument.
     * @param name Complete or partial name of the ñpcañ
     * @return The list of local, that might eventually contain zero elements
     * @throws RemoteException If some error happens in the remove invocation.
     */
    @Override
    public Local[] getLocals(String name) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Elimina un local del sistema
     * @param l Local a eliminar
     * @return  True sí y sólo si se pudo completar la tarea
     * @throws RemoteException If some error happens in the remove invocation.
     */
    @Override
    public Boolean eliminaLocal(Local l) throws RemoteException {
        if(locales.size() < 1) {
            return false;
        }
        return locales.remove(l);
    }

    /**
    * Elimina todas la reviews (y contestaciones asociadas) de un local
    * @param l Local cuyas reviews deben eliminarse
    * @return True sí y sólo si se pudo completar la tarea
    * @throws RemoteException If some error happens in the remove invocation.
    */
    @Override
    public Boolean eliminaReviewsDeLocal(Local l) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
    * Elimina una review determinada del sistema
    * @param r Review a eliminar
    * @return True sí y sólo si se pudo completar la tarea
    * @throws RemoteException If some error happens in the remove invocation.
    */
    @Override
    public Boolean eliminarReview(Review r) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
/*  REVISAR SI PODEMOS SOLUCIONARLO ASÍ
    @Override
    public Boolean eliminaReview(Review r) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
*/
    
    /**
    * Elimina todas la reviews de un cliente determinado
    * @param c Cliente cuyas reviews deben ser eliminadas
    * @return Número de reviews eliminadas, o -1 si el cliente no existe.
    * @throws RemoteException If some error happens in the remove invocation.
    */
    @Override
    public Integer eliminaReviewsDeUsuario(Cliente c) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
    * Crea un usuario falso al que se le añade una review falsa con la puntuación
    * determinada en el segunda argumento.
    * @param l Local al que añadir la review falsa
    * @param puntuacion puntuación de la review
    * @return True sí y sólo si se pudo completar la tarea
    * @throws RemoteException If some error happens in the remove invocation.
    */
    @Override
    public Boolean insertaReviewFalsa(Local l, Integer puntuacion) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
