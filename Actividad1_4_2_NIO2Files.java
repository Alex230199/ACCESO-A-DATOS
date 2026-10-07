package act1_texto;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class Actividad1_4_2_NIO2Files {

	public static void main (String [] args) {
		
		Path ruta = Path.of("datos", "database.propierties");
		String config = "db.host=localhost\ndb.port=3306\ndb.user=admin\ndb.pass=secret";
		try {
			Files.writeString(ruta, config);
			long bytes = Files.size(ruta);
			System.out.println("Tamaño " + bytes);
			if (Files.exists(ruta)) {
				String contenido = Files.readString(ruta);
				System.out.println("\n--- Contenido Recuperado ---");
				System.out.println(contenido);
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}

