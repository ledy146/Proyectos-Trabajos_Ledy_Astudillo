package cliente.vista;

import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidoralertas.DTO.IndicadoresDTO;
import servidoralertas.DTO.PacienteDTO;
import servidoralertas.controladores.ControladorGestorIndicadoresInt;
import servidorusuarios_sop_corba.GestionPacientesOperations;
import servidorusuarios_sop_corba.GestionPacientesPackage.pacienteDTO;


public class Menu {

    private final ControladorGestorIndicadoresInt objRemoto;
    static GestionPacientesOperations refCorba;
    private PacienteDTO objPaciente;

    public Menu(ControladorGestorIndicadoresInt objRemoto, GestionPacientesOperations refCorba) {
        this.objRemoto = objRemoto;
        this.refCorba = refCorba;
    }

    private void registrarPaciente() {
        System.out.println("== Registro de Paciente ==");
        String nombres, apellidos;
        int identificacion;
        float edad;

        System.out.println("Digite el número de identificacion: ");
        identificacion = UtilidadesConsola.leerEntero();

        System.out.print("Nombres: ");
        nombres = UtilidadesConsola.leerCadena();

        System.out.print("Apellidos: ");
        apellidos = UtilidadesConsola.leerCadena();
        while (true) {
            System.out.print("Edad: ");
            edad = UtilidadesConsola.leerReal();
            if (edad > 0) {
                break;
            } else {
                System.out.println("\n Edad no valida, debe ser mayor a cero");
            }
        }

        pacienteDTO objPaciente = new pacienteDTO(identificacion, nombres, apellidos, edad);
        boolean bandera = refCorba.registrarPaciente(objPaciente);//invocación al método remoto

        
        if (bandera){
            System.out.println("Paciente registrado con éxito");
            System.out.println("id: "+objPaciente.edad);
            System.out.println("nombre: "+objPaciente.nombres);
            
        }
        else{
            System.out.println("No ha sido posible registrar el Paciente.");
        }
    }

    int numAleatorioFrecuenciaCardiaca() {
        Random rand = new Random();
        return rand.nextInt(120) + 60;

    }

    int numAleatorioFrecuenciaRespiratoria() {
        Random rand = new Random();
        return rand.nextInt(45) + 12;
    }

    int numAleatorioTensionArterialSistolica() {
        Random rand = new Random();
        return rand.nextInt(140) + 70;
    }

    int numAleatorioTensionArterialDiastolica() {
        Random rand = new Random();
        return rand.nextInt(90) + 50;
    }

    float numAleatorioTemperatura() {
        Random rand = new Random();

        float numAleatorio = (rand.nextFloat() * 1.8f) + 36.2f;
        return numAleatorio;
    }

    int numAleatorioSaturacionOxigeno() {
        Random rand = new Random();
        return rand.nextInt(100) + 80;
    }

    private IndicadoresDTO objIndicadores() {
        IndicadoresDTO objIndicadores = new IndicadoresDTO(
                numAleatorioFrecuenciaCardiaca(), numAleatorioTensionArterialSistolica(), numAleatorioTensionArterialDiastolica(), numAleatorioFrecuenciaRespiratoria(), numAleatorioSaturacionOxigeno(), numAleatorioTemperatura(), objPaciente);
        return objIndicadores;

    }

    public void mostrarEco(IndicadoresDTO objIndicadores) {
        System.out.println("\n Enviando Indicadores.... \n");
        System.out.println("\n frecuencia cardiaca:  \n" + objIndicadores.getFrecuenciaCardiaca());
        System.out.println("\n presion arterial sistolica \n" + objIndicadores.getTensionArterialSistolica());
        System.out.println("\n presion arterial diastolica \n" + objIndicadores.getTensionArterialDiastolica());
        System.out.println("\n frecuencia respiratoria \n" + objIndicadores.getFrecuenciaRespiratoria());
        System.out.println("\n temperatura \n" + objIndicadores.getTemperatura());
        System.out.println("\n saturacion de oxigeno \n" + objIndicadores.getSaturacionOxigeno());
    }

    public void capturaCernsores() {
        boolean seguirEjecutando = true;

        while (seguirEjecutando) {

            try {

                IndicadoresDTO indicadores = objIndicadores();

                IndicadoresDTO enviar = objRemoto.enviarIndicadores(indicadores);
                if (enviar == null) {
                    System.out.println("\nFallo en el envio de información");
                }
                mostrarEco(indicadores);

                try {
                    TimeUnit.SECONDS.sleep(8);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (RemoteException ex) {

                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);

            }

        }

    }

    public void ejecutarMenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("==Menu Sensores==");
            System.out.println("1. Ingresar Datos del paciente");
            System.out.println("2. Comenzar lectura de los sensores");
            System.out.println("3. Salir");
            System.out.println("\n");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {

                case 1:
                    registrarPaciente();

                    break;
                case 2:

                    capturaCernsores();
                    break;
                case 3:
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opción incorrecta");

            }

        } while (opcion != 3);
    }
}
