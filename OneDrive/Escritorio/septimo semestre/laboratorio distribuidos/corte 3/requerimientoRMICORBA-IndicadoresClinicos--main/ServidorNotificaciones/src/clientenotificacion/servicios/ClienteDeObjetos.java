
package clientenotificacion.servicios;
import static clientenotificacion.Utilidades.UtilidadesConsola.leerCadena;
import static clientenotificacion.Utilidades.UtilidadesConsola.leerEntero;
import clientenotificacion.Utilidades.UtilidadesRegistroC;
import clientenotificacion.controladores.ControladorCallBackImpl;
import java.rmi.RemoteException;
import servidoralertas.controladores.ControladorGestorNotificacionInt;
/**
 *
 * @author brayan
 */
public class ClienteDeObjetos {

    public static void main(String[] args) throws RemoteException {
      int numPuertoRMIRegistry = 0;
      String direccionIpRMIRegistry = "";     
      
      
        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
        direccionIpRMIRegistry =leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
        numPuertoRMIRegistry = leerEntero(); 
        
         ControladorCallBackImpl objRemoto= new ControladorCallBackImpl();
         ControladorGestorNotificacionInt objRemotoServidor=
                (ControladorGestorNotificacionInt)
                UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry, "idGestorNotificacion");
        objRemotoServidor.registrarReferenciaRemota(objRemoto);
        System.out.println("Esperando notificaciones");
    }
}
