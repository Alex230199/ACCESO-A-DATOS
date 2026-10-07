package act1_texto;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedOutputStream;



public class Actividad2_1_DataOutputStream {

	public static void main (String [] args) {
		//---------------------------FORMA 2 DE HACER EL EJERCICIO-------------------------------		
		File ficheroBinario = new File ("datos/telemetria.dat");
		
		try ( DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(ficheroBinario)));)
		 {
			
		
	
			dos.writeInt(101);
			dos.writeUTF("Caldera Central");
			dos.writeDouble(84.5);
			dos.writeBoolean(false);
			
			dos.writeInt(102);
			dos.writeUTF("Turbina 01");
			dos.writeDouble(102.3);
			dos.writeBoolean(true);
				
			
			dos.writeInt(103);
			dos.writeUTF("Compresor gas");
			dos.writeDouble(45.0);
			dos.writeBoolean(false);
			
			dos.flush();	
			
		}catch (IOException e) {
			//TODO AUTO-generated catch block
			e.printStackTrace();
		}
		
		
		
		//--------------------FORMA 1 DE HACER EL EJERCICIO-------------------------
		/*
		Path directorio = Paths.get("datos");
		
		try {
			if (!Files.exists(directorio)) {
                Files.createDirectories(directorio);
		}
		
	} catch (IOException e) {
        System.err.println("Error al crear el directorio " + e.getMessage());
       return;
    }
		
		Path archivoSalida = Paths.get("datos/telemetria.dat");
		
		
		try (DataOutputStream dos = new DataOutputStream(
				new BufferedOutputStream(
				new FileOutputStream(archivoSalida.toFile())
				)
				)){
		
			//sensor 1:
			
			dos.writeInt(101);
			dos.writeUTF("Caldera Central");
			dos.writeDouble(84.5);
			dos.writeBoolean(false);
			
			//sensor 2
			
			dos.writeInt(102);
			dos.writeUTF("Turbina 01");
			dos.writeDouble(102.3);
			dos.writeBoolean(true);
			
			//sensor 3
			
			dos.writeInt(103);
			dos.writeUTF("Compresor gas");
			dos.writeDouble(45.0);
			dos.writeBoolean(false);
			
			dos.flush();
			
			System.out.println("datos de telemetría binarios guardados correctamente en " + archivoSalida);
			
		
			
		}catch (IOException e) {
			System.out.println("Error de E/S al escribir el archivo binario" + e.getMessage());
			
		}
}
*/
}
}
