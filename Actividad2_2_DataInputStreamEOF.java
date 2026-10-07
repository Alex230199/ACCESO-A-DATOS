package act1_texto;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;



public class Actividad2_2_DataInputStreamEOF {


	public static void main (String [] args) {
		
		File ficheroBinario = new File ("datos/telemetria.dat");
		
		try ( DataInputStream dis = new DataInputStream
				(new BufferedInputStream(new FileInputStream(ficheroBinario)))){
		
			while(true) 
		
			{
			
			    int id = dis.readInt();
		        String aux = dis.readUTF();
		        double temp = dis.readDouble();
		        boolean alarm = dis.readBoolean();
		        System.out.printf("[Sensor %d %-16s - temp: %6.2f ºC - Alarma: %s%n"
		        		, id, aux, temp, alarm ? "si" : "no");
	
	
			}	
				} catch (EOFException e) {
		            // Se captura específicamente cuando el fichero termina de leerse
		            System.out.println("Fin de fichero alcanzado limpiamente");
		        } catch (IOException e) {
		            // Para otros errores de entrada/salida reales
		            e.printStackTrace();
		        }
		    }
		}
		
		
		
		
		
		
	


		
