package clientenotificacion.controladores;
import java.rmi.Remote;
import java.rmi.RemoteException;
import servidoralertas.DTO.NotificacionDTO;
/**
 *
 * @author brayan
 */
public interface ControladorCallBackInt extends Remote {
    public boolean enviarNotificacion(NotificacionDTO objNotificacion)throws RemoteException;
}
