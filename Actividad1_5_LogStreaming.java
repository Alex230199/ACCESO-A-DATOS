package act1_texto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path ;
import java.util.Iterator;
import java.util.stream.Stream;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class Actividad1_5_LogStreaming {

	public static void main (String [] args) {
		
		Path logOrigen = Paths.get("app.log");
		Path logAlertas = Path.of("datos", "alertas.txt");
		
		try (Stream<String> lineasStream =Files.lines(logAlertas,
				StandardCharsets.UTF_8);
				BufferedWriter bf = Files.newBufferedWriter
						(logAlertas, StandardCharsets.UTF_8)){
					
			int lineasCriticas = 0;
			
			for (Iterator<String> it = lineasStream.iterator();it.hasNext();) {
				String linea = it.next();
				
				if (linea.contains("[ERROR]")) {
					
					bf.write(linea);
					bf.newLine();
					lineasCriticas++;
			}
				
				bf.write("El numero de lineas criticas es " + lineasCriticas);
			
			bf.flush();
			
			bf.close();
			
				} 
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
