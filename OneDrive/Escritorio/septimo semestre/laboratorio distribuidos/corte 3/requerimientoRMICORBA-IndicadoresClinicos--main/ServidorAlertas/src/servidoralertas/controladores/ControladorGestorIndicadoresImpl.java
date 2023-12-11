package servidoralertas.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;

import servidoralertas.DTO.IndicadoresDTO;
import servidoralertas.DTO.NotificacionDTO;
import servidoralertas.DTO.PacienteDTO;
import servidoralertas.repositorio.PacienteRepositorioInt;

import servidorusuarios_sop_corba.GestionPacientesOperations;
import servidorusuarios_sop_corba.GestionPacientesPackage.pacienteDTO;

/**
 *
 * @author brayan
 */
public class ControladorGestorIndicadoresImpl extends UnicastRemoteObject implements ControladorGestorIndicadoresInt {

    private NotificacionDTO objNotificacion;
    private PacienteRepositorioInt objPacienteRepositorio;
    private IndicadoresDTO indicadores;
    private static GestionPacientesOperations refCorba;
    private final ControladorGestorNotificacionImpl objRemotoGestionNotificacion;

    public ControladorGestorIndicadoresImpl(
            PacienteRepositorioInt objPacienteRepositorio,
            ControladorGestorNotificacionImpl objRemotoGestionNotificacion, GestionPacientesOperations refCorba) throws RemoteException {
        super();
        this.objPacienteRepositorio = objPacienteRepositorio;
        this.objNotificacion = new NotificacionDTO();
        this.indicadores = new IndicadoresDTO();
        this.objRemotoGestionNotificacion = objRemotoGestionNotificacion;
        this.refCorba = refCorba;

    }

    @Override
    public IndicadoresDTO enviarIndicadores(IndicadoresDTO objIndicadores) throws RemoteException {
        String mensaje1 = "La enfermera debe revisar el paciente";
        String mensaje2 = "La enfermera y el medico debe veisar el paciente";
        this.indicadores = objIndicadores;
        int puntuacion = 0;
        puntuacion = verificarPuntuacion(indicadores);
        if (puntuacion >= 2) {
            System.out.println("llego hasyta aca");
            //System.out.println("fecha"+obtenerFechaActual());
            // System.out.println("fecha"+obtenerHoraActual());
            this.objNotificacion.getObjFechaHora().setFechaActual(obtenerFechaActual());
            this.objNotificacion.getObjFechaHora().setHoraActual(obtenerHoraActual());
            this.objNotificacion.setObjPaciente(obtenerInfoPaciente(indicadores));
            this.objPacienteRepositorio.guardarInformacionArchivo(indicadores, objNotificacion, puntuacion);

            if (puntuacion == 2) {
                this.objNotificacion.setMensaje(mensaje1);
            } else {
                this.objNotificacion.setMensaje(mensaje2);
            }
            this.objNotificacion.setAlertas(this.objPacienteRepositorio.leerInformacionArchivo(obtenerInfoPaciente(indicadores).getIdentificacion()));
            this.objNotificacion.setCantidadAlertas(this.objPacienteRepositorio.cantida());
            this.objRemotoGestionNotificacion.notificarNuevaAlerta(objNotificacion);
        }
        System.out.println("paso aca");
        return objIndicadores;
    }

    public PacienteDTO obtenerInfoPaciente(IndicadoresDTO objIndicadores) {
        indicadores = objIndicadores;
        try {
            if (refCorba != null && indicadores != null && indicadores.getObjPaciente() != null) {
                pacienteDTO objpaciente = refCorba.buscarPaciente(indicadores.getObjPaciente().getIdentificacion());
                PacienteDTO objPaci = mapearPaciente(objpaciente);
                return objPaci;
            } else {
                System.out.println("No se pudo obtener la referencia CORBA");
                return new PacienteDTO();
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("NullPointerException:CORBA " + e.getMessage());
            return new PacienteDTO();
        }

    }

    public PacienteDTO mapearPaciente(pacienteDTO pDto) {

        PacienteDTO paciente = new PacienteDTO();
        paciente.setIdentificacion(pDto.identificacion);
        paciente.setNombres(pDto.nombres);
        paciente.setApellidos(pDto.apellidos);
        paciente.setEdad(pDto.edad);

        return paciente;
    }

    public int determinarRangoNormalFrecuenciaCardiaca(float edad, int frecuencia) {
        int puntuacion = 1;
        if ((edad >= 0.0 && edad <= 0.087) && (frecuencia >= 120 && frecuencia <= 130)) {
            puntuacion = 0;
        } else if ((edad >= 0.09 && edad <= 1.0) && (frecuencia >= 100 && frecuencia <= 130)) {
            puntuacion = 0;
        } else if ((edad > 1.0 && edad <= 2.0) && (frecuencia >= 100 && frecuencia <= 120)) {
            puntuacion = 0;
        } else if ((edad > 2.0 && edad <= 6.0) && (frecuencia >= 80 && frecuencia <= 120)) {
            puntuacion = 0;
        } else if ((edad > 6.0 && edad <= 13.0) && (frecuencia >= 80 && frecuencia <= 100)) {
            puntuacion = 0;
        } else if ((edad > 13.0 && edad <= 16.0) && (frecuencia >= 70 && frecuencia <= 80)) {
            puntuacion = 0;
        } else if (edad > 16.0 && (frecuencia >= 60 && frecuencia <= 80)) {
            puntuacion = 0;
        }

        return puntuacion;
    }

    private int determinarRangoNormalPresionArterialSistolica(float edad, int presion) {
        int puntuacion = 1;
        if ((edad >= 0.0 && edad <= 0.087) && (presion >= 70 && presion <= 100)) {
            puntuacion = 0;
        } else if ((edad >= 0.09 && edad <= 1.0) && (presion >= 84 && presion <= 106)) {
            puntuacion = 0;
        } else if ((edad > 1.0 && edad <= 2.0) && (presion >= 98 && presion <= 106)) {
            puntuacion = 0;
        } else if ((edad > 2.0 && edad <= 6.0) && (presion >= 99 && presion <= 112)) {
            puntuacion = 0;
        } else if ((edad > 6.0 && edad <= 13.0) && (presion >= 104 && presion <= 124)) {
            puntuacion = 0;
        } else if ((edad > 13.0 && edad <= 16.0) && (presion >= 118 && presion <= 132)) {
            puntuacion = 0;
        } else if (edad > 16.0 && (presion >= 110 && presion <= 140)) {
            puntuacion = 0;
        }
        return puntuacion;
    }

    private int determinarRangoNormalPresionArterialDiastolica(float edad, int presion) {
        int puntuacion = 1;
        if ((edad >= 0.0 && edad <= 0.087) && (presion >= 50 && presion <= 68)) {
            puntuacion = 0;
        } else if ((edad >= 0.09 && edad <= 1.0) && (presion >= 56 && presion <= 70)) {
            puntuacion = 0;
        } else if ((edad > 1.0 && edad <= 2.0) && (presion >= 58 && presion <= 70)) {
            puntuacion = 0;
        } else if ((edad > 2.0 && edad <= 6.0) && (presion >= 64 && presion <= 70)) {
            puntuacion = 0;
        } else if ((edad > 6.0 && edad <= 13.0) && (presion >= 64 && presion <= 86)) {
            puntuacion = 0;
        } else if ((edad > 13.0 && edad <= 16.0) && (presion >= 70 && presion <= 82)) {
            puntuacion = 0;
        } else if (edad > 16.0 && (presion >= 70 && presion <= 90)) {
            puntuacion = 0;
        }
        return puntuacion;
    }

    int determinarRangoNormalFrecuenciaRespiratoria(float edad, int frecuencia) {
        int puntuacion = 1;
        if ((edad >= 0.0 && edad <= 0.087) && (frecuencia >= 40 && frecuencia <= 45)) {
            puntuacion = 0;
        } else if ((edad >= 0.09 && edad <= 1.0) && (frecuencia >= 20 && frecuencia <= 30)) {
            puntuacion = 0;
        } else if ((edad > 1.0 && edad <= 2.0) && (frecuencia >= 20 && frecuencia <= 30)) {
            puntuacion = 0;
        } else if ((edad > 2.0 && edad <= 6.0) && (frecuencia >= 20 && frecuencia <= 30)) {
            puntuacion = 0;
        } else if ((edad > 6.0 && edad <= 13.0) && (frecuencia >= 12 && frecuencia <= 20)) {
            puntuacion = 0;
        } else if ((edad > 13.0 && edad <= 16.0) && (frecuencia >= 12 && frecuencia <= 20)) {
            puntuacion = 0;
        } else if (edad > 16.0 && (frecuencia >= 12 && frecuencia <= 20)) {
            puntuacion = 0;
        }

        return puntuacion;
    }

    private int determinarRangoNormalSaturacionOxigeno(float edad, int saturacion) {
        int puntuacion = 1;
        if ((edad >= 0.0 && edad <= 120.0) && (saturacion >= 95 && saturacion <= 100)) {
            puntuacion = 0;
        }
        return puntuacion;
    }

    private int determinarRangoNormalTemperatura(float edad, double temperatura) {
        int puntuacion = 1;
        if ((edad >= 0.0 && edad <= 0.087) && (temperatura == 38.0)) {
            puntuacion = 0;
        } else if ((edad >= 0.09 && edad <= 1.0) && (temperatura >= 37.5 && temperatura <= 37.8)) {
            puntuacion = 0;
        } else if ((edad > 1.0 && edad <= 2.0) && (temperatura >= 37.5 && temperatura <= 37.8)) {
            puntuacion = 0;
        } else if ((edad > 2.0 && edad <= 6.0) && (temperatura >= 37.5 && temperatura <= 37.8)) {
            puntuacion = 0;
        } else if ((edad > 6.0 && edad <= 13.0) && (temperatura >= 37 && temperatura <= 37.5)) {
            puntuacion = 0;
        } else if ((edad > 13.0 && edad <= 16.0) && (temperatura == 37.0)) {
            puntuacion = 0;
        } else if (edad > 16.0 && (temperatura >= 36.2 && temperatura <= 37.2)) {
            puntuacion = 0;
        }

        return puntuacion;
    }

    private int verificarPuntuacion(IndicadoresDTO objIndicadores) {
        indicadores = objIndicadores;
        int totalPuntuacion = 0;
        if (determinarRangoNormalFrecuenciaCardiaca(obtenerInfoPaciente(indicadores).getEdad(), objIndicadores.getFrecuenciaCardiaca()) == 1) {
            totalPuntuacion++;
            this.objNotificacion.getObjIndicador().setFrecuenciaCardiaca(objIndicadores.getFrecuenciaCardiaca());
        } else {
            this.objNotificacion.getObjIndicador().setFrecuenciaCardiaca(-1);
        }

        if (determinarRangoNormalPresionArterialSistolica(obtenerInfoPaciente(indicadores).getEdad(), objIndicadores.getTensionArterialSistolica()) == 1) {
            totalPuntuacion++;
            this.objNotificacion.getObjIndicador().setTensionArterialSistolica(objIndicadores.getTensionArterialSistolica());
        } else {
            this.objNotificacion.getObjIndicador().setTensionArterialSistolica(-1);
        }
        if (determinarRangoNormalPresionArterialDiastolica(obtenerInfoPaciente(indicadores).getEdad(), objIndicadores.getTensionArterialDiastolica()) == 1) {
            totalPuntuacion++;
            this.objNotificacion.getObjIndicador().setTensionArterialDiastolica(objIndicadores.getTensionArterialDiastolica());
        } else {
            this.objNotificacion.getObjIndicador().setTensionArterialDiastolica(-1);
        }
        if (determinarRangoNormalFrecuenciaRespiratoria(obtenerInfoPaciente(indicadores).getEdad(), objIndicadores.getFrecuenciaRespiratoria()) == 1) {
            totalPuntuacion++;
            objNotificacion.getObjIndicador().setFrecuenciaRespiratoria(objIndicadores.getFrecuenciaRespiratoria());
        } else {
            objNotificacion.getObjIndicador().setFrecuenciaRespiratoria(-1);
        }
        if (determinarRangoNormalSaturacionOxigeno(obtenerInfoPaciente(indicadores).getEdad(), objIndicadores.getSaturacionOxigeno()) == 1) {
            totalPuntuacion++;
            objNotificacion.getObjIndicador().setSaturacionOxigeno(objIndicadores.getSaturacionOxigeno());
        } else {
            objNotificacion.getObjIndicador().setSaturacionOxigeno(-1);
        }
        if (determinarRangoNormalTemperatura(obtenerInfoPaciente(indicadores).getEdad(), objIndicadores.getTemperatura()) == 1) {
            totalPuntuacion++;
            objNotificacion.getObjIndicador().setTemperatura(objIndicadores.getTemperatura());
        } else {
            objNotificacion.getObjIndicador().setTemperatura(-1.0);
        }
        return totalPuntuacion;
    }

    public LocalDateTime obtenerHoraActual() {
        return LocalDateTime.now();
    }

    public LocalDate obtenerFechaActual() {
        return LocalDate.now();
    }

}
