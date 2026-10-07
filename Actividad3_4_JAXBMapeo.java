package act1_texto;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

/** 
 * Actividad 3.4: Mapeo Declarativo Objeto-XML con JAXB
 * 
 * Demuestra cómo persistir y recuperar entidades complejas en XML 
 * mediante anotaciones declarativas (Jakarta XML Binding) sin manipular 
 * nodos DOM manualmente.
 */

public class Actividad3_4_JAXBMapeo {
	
	 public static void main(String[] args) throws JAXBException {
	        
	        // 1. CREAR UN OBJETO CURSO
	        // Se instancia un objeto Curso con datos de ejemplo
	        Curso curso = new Curso("DAM01", "Desarrollo de Aplicaciones Multiplataforma", 1200);
	        
	        // 2. MARSHALLING: Convertir un objeto Java a formato XML
	        
	        // Crear el contexto JAXB asociado a la clase Curso
	        // El contexto es responsable de las operaciones de serialización/deserialización
	        JAXBContext ctx = JAXBContext.newInstance(Curso.class);
	        
	        // Crear el Marshaller: herramienta que convierte objetos Java a XML
	        Marshaller m = ctx.createMarshaller();
	        
	        // Configurar la propiedad JAXB_FORMATTED_OUTPUT para que el XML sea legible
	        // (con indentación y saltos de línea)
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        
	        // Definir la ruta del archivo XML donde se guardará el objeto serializado
	        File archivo = new File("datos/curso.xml");
	        
	        // Crear el directorio "datos" si no existe
	        if (!archivo.getParentFile().exists()) {
	            archivo.getParentFile().mkdirs();
	        }
	        
	        // Marshalling: Escribir el objeto Curso en formato XML al archivo
	        m.marshal(curso, archivo);
	        System.out.println("XML creado en: " + archivo.getAbsolutePath());
	        
	        // 3. UNMARSHALLING: Convertir XML de vuelta a un objeto Java
	        
	        // Crear el Unmarshaller: herramienta que convierte XML a objetos Java
	        Unmarshaller u = ctx.createUnmarshaller();
	        
	        // Unmarshalling: Leer el archivo XML y convertirlo en un objeto Curso
	        Curso c = (Curso) u.unmarshal(archivo);
	        
	        // Mostrar el objeto recuperado en la consola
	        System.out.println("Curso: " + c);
	    }
}

