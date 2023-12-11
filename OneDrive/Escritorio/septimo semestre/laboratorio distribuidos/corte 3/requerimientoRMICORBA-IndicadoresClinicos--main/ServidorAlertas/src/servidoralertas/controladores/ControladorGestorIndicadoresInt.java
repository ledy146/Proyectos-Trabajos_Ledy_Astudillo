/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servidoralertas.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidoralertas.DTO.IndicadoresDTO;

/**
 *
 * @author brayan
 */
public interface ControladorGestorIndicadoresInt extends Remote{
    IndicadoresDTO enviarIndicadores(IndicadoresDTO objIndicadores )throws RemoteException;
}
