/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidoralertas.utilidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author brayan
 */
public class UtilidadesConsola {
    public static int leerEntero()
    {
    	String linea = "";
    	int opcion = 0;
    	boolean valido = false;
    	do
    	{
    		try
    		{
                    //System.out.println("Ingrese la opcion: ");
                    BufferedReader br = new BufferedReader(new
                    InputStreamReader(System.in));
                    linea = br.readLine();
                    opcion = Integer.parseInt(linea);
                    valido = true;
    		}
    		catch(Exception e)
    		{
    			System.out.println("Error intente nuevamente...");
    			valido = false;
    		}
    	}while(!valido);
    	
    	return opcion;
    
    }
	
	public static String leerCadena()
    {
    	String linea = "";
    	boolean valido = false;
    	do
    	{
    		try
    		{
                    System.out.println("Ingrese la opcion: ");
                    BufferedReader br = new BufferedReader(new
                    InputStreamReader(System.in));
                    linea = br.readLine();
                    valido = true;
    		}
    		catch(Exception e)
    		{
    			System.out.println("Error intente nuevamente...");
    			valido = false;
    		}
    	}while(!valido);
    	
    	return linea;
    
    }
}
