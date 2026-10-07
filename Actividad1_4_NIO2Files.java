package act1_texto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Actividad1_4_NIO2Files {

public static void main(String[] args) {
        
        // 1. Definir el texto de configuración especificado
        String config = "db.host=localhost\ndb.port=3306\ndb.user=admin\ndb.pass=secret";
        
        try {
            // Definir las rutas (creamos la carpeta 'datos' si no existe previamente)
        	  Path directorio = Paths.get("datos");
              if (!Files.exists(directorio)) {
                  Files.createDirectories(directorio);
              }
            
            Path archivoPath = directorio.resolve("database.properties");
            
            // 2. Escribir los datos en el archivo con Files.writeString()
            Files.writeString(archivoPath, config);
            System.out.println(">> Archivo escrito con éxito en: " + archivoPath);
            
            // 3. Comprobar el tamaño con Files.size() e imprimir los bytes ocupados
            long tamanio = Files.size(archivoPath);
            System.out.println(">> Tamaño del archivo: " + tamanio + " bytes");
            
            // 4. Recuperar el contenido íntegro con Files.readString() y mostrarlo
            String contenidoLeido = Files.readString(archivoPath);
            System.out.println("\n--- CONTENIDO RECUPERADO DEL ARCHIVO ---");
            System.out.println(contenidoLeido);
            
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

