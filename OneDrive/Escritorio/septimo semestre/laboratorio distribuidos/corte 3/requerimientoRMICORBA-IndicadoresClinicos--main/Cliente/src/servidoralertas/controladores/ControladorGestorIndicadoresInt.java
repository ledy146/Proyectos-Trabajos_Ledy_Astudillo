
package servidoralertas.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidoralertas.DTO.IndicadoresDTO;

/**
 *
 * @author brayan
 */
public interface ControladorGestorIndicadoresInt extends Remote {
    IndicadoresDTO enviarIndicadores(IndicadoresDTO objIndicadores )throws RemoteException;
}
