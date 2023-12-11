
package servidoralertas.controladores;
import clientenotificacion.controladores.ControladorCallBackInt;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.LinkedList;
import servidoralertas.DTO.NotificacionDTO;
/**
 *
 * @author brayan
 */
public class ControladorGestorNotificacionImpl extends UnicastRemoteObject implements ControladorGestorNotificacionInt{
  
     private final LinkedList<ControladorCallBackInt> listaReferencias;
     
    public ControladorGestorNotificacionImpl() throws RemoteException{
        super();
        this.listaReferencias=new LinkedList();
    }
    @Override
    public boolean registrarReferenciaRemota(ControladorCallBackInt referencia) throws RemoteException {
         return this.listaReferencias.add(referencia);
    }
    public void notificarNuevaAlerta(NotificacionDTO objNotificacion){
        this.listaReferencias.forEach(
                obj->{
            try{
                obj.enviarNotificacion(objNotificacion);
            }catch(RemoteException ex){
                 System.out.println("Error al notificar al administrador");
            }
        }
        );
        
    
    }
    
}
