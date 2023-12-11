

package servidoralertas.controladores;
import clientenotificacion.controladores.ControladorCallBackInt;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author brayan
 */
public interface ControladorGestorNotificacionInt extends Remote{
     public boolean registrarReferenciaRemota(ControladorCallBackInt referencia)throws RemoteException;
}
