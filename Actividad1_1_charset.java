package act1_texto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Actividad1_1_charset {
	
	public static void main(String [] args) {
		
		int numerolineasmigradas = 0;
		
		Path origen = Path.of("datos", "usuarios_legacy.txt");
		Path destino = Path.of("datos", "usuarios_utf8.txt");
		
		try (BufferedReader br = Files.newBufferedReader(origen, StandardCharsets.ISO_8859_1);
				BufferedWriter bw = Files.newBufferedWriter(destino, StandardCharsets.UTF_8)){
			
			String linea;
			while ((linea = br.readLine()) != null) {
				bw.write(linea);
				bw.newLine();
				numerolineasmigradas = numerolineasmigradas + 1;
			}
			
			bw.flush();
			
			System.out.println("Han sido migradas " + numerolineasmigradas + " lineas");
		} catch (IOException e) {
			System.err.println("fallo: " + e.getMessage());
		}
		
		
	}

}
