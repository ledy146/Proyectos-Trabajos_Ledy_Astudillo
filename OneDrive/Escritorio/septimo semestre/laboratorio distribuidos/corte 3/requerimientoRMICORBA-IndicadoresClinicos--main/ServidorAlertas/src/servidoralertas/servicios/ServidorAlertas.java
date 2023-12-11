/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package servidoralertas.servicios;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import servidoralertas.controladores.ControladorGestorIndicadoresImpl;
import servidoralertas.controladores.ControladorGestorNotificacionImpl;
import servidoralertas.repositorio.PacienteRepositorioImpl;
import servidoralertas.utilidades.UtilidadesConsola;
import static servidoralertas.utilidades.UtilidadesConsola.leerCadena;
import static servidoralertas.utilidades.UtilidadesConsola.leerEntero;
import servidoralertas.utilidades.UtilidadesRegistroS;
import servidorusuarios_sop_corba.GestionPacientesHelper;
import servidorusuarios_sop_corba.GestionPacientesOperations;



/**
 *
 * @author brayan
 */
public class ServidorAlertas {
    private static GestionPacientesOperations refCorba;
    public static void main(String[] args)throws RemoteException {
        try{
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
            System.out.println("Cual es el la dirección ip donde se encuentra  el rmiRegistry ");
            direccionIpRMIRegistry = leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry ");
            numPuertoRMIRegistry = leerEntero();
            
            //Crear el repositorio
            PacienteRepositorioImpl objPacienteRepositoryImpl=new PacienteRepositorioImpl();
            //crear objet remoto
            ControladorGestorNotificacionImpl objControladorGestorNotificaciones=new ControladorGestorNotificacionImpl ();
            ControladorGestorIndicadoresImpl objRemotoGestionIndicadres=new
                ControladorGestorIndicadoresImpl(objPacienteRepositoryImpl,objControladorGestorNotificaciones,refCorba);
            try{
                UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
                UtilidadesRegistroS.RegistrarObjetoRemoto((Remote)objRemotoGestionIndicadres, direccionIpRMIRegistry, numPuertoRMIRegistry, "objServicioGestionPacientes");
                UtilidadesRegistroS.RegistrarObjetoRemoto((Remote)objControladorGestorNotificaciones,direccionIpRMIRegistry, numPuertoRMIRegistry,"idGestorNotificacion" );
            }catch(Exception e){
                System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" +  e.getMessage());
            }
        }catch(InvalidName ex){
            Logger.getLogger(ServidorAlertas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFound ex) {
            Logger.getLogger(ServidorAlertas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(ServidorAlertas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            Logger.getLogger(ServidorAlertas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
