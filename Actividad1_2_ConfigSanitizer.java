package act1_texto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Actividad1_2_ConfigSanitizer {

	public static void main (String [] args) {
		
		Path origen = Path.of("datos", "config.ini");
		Path destino = Path.of("datos", "config_clean.ini");
		
		try (BufferedReader br = Files.newBufferedReader(origen, StandardCharsets.ISO_8859_1);
				BufferedWriter bw = Files.newBufferedWriter(destino, StandardCharsets.UTF_8)){
			
			String linea;
			
			while((linea = br.readLine()) != null) {
				linea = linea.trim();
				
				if (linea.isEmpty() || linea.startsWith("#") || linea.startsWith(";")) {
					continue;
				}
				
				String[] partes = linea.split("=", 2);
				
				String parteUno = partes[0].trim().toUpperCase();
				String parteDos = partes[1].trim();
				
				bw.write(parteUno + "=" + parteDos);
				bw.newLine();
				
			}
			
		
		} catch (IOException e) {
			System.err.println("fallo: " + e.getMessage());
	
	   }
	}
}
