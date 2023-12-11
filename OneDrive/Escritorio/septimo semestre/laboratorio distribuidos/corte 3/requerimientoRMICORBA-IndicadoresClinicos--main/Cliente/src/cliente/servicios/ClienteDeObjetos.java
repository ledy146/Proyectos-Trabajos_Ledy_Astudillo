
package cliente.servicios;

import cliente.utilidades.UtilidadesConsola;
import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import servidoralertas.controladores.ControladorGestorIndicadoresInt;
import servidorusuarios_sop_corba.GestionPacientesHelper;
import servidorusuarios_sop_corba.GestionPacientesOperations;

/**
 *
 * @author brayan
 */
public class ClienteDeObjetos {

    private static ControladorGestorIndicadoresInt objRemoto;
    private static GestionPacientesOperations refCorba;
    public static void main(String[] args) throws InvalidName, NotFound {
        try {
            String[] vec = new String[4];
            vec[0] = "-ORBInitialPort";
            System.out.println("Ingrese la dirección IP donde escucha el n_s");
            vec[1] = UtilidadesConsola.leerCadena();
            vec[2] = "-ORBInitialPort";
            System.out.println("Ingrese el puerto donde escucha el n_s");
            vec[3] = UtilidadesConsola.leerCadena();

            // se crea e inicia el ORB
            ORB orb = ORB.init(vec, null);

            // se obtiene un link al name service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // *** Resuelve la referencia del objeto en el N_S ***
            String name = "objPacientes";
            refCorba = GestionPacientesHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Obtenido el manejador sobre el servidor de objetos: " + refCorba);
            
            
            int numPuertoRMIRegistry = 0;
            String direccionIpRMIRegistry = "";
            System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
            direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
            numPuertoRMIRegistry = cliente.utilidades.UtilidadesConsola.leerEntero();
            objRemoto =  (ControladorGestorIndicadoresInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry, "objServicioGestionPacientes"  );
            Menu objMenu= new Menu(objRemoto,refCorba);
            objMenu.ejecutarMenuPrincipal();
        } catch (CannotProceed ex) {
            Logger.getLogger(ClienteDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            Logger.getLogger(ClienteDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
