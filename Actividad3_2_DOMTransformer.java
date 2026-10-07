package act1_texto;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * ACTIVIDAD 3.2: Generación de XML y Volcado Formateado con Transformer
 *
 * Objetivo: Construir un árbol XML desde código Java y exportarlo a disco
 * con sangría limpia (pretty-print) para intercambio de información entre
 * sistemas externos.
 *
 * Patrón: Utilizar Transformer con configuración de indentación para lograr
 * un XML legible y bien formateado.
 */

public class Actividad3_2_DOMTransformer {

	   public static void main(String[] args) {
	        try {
	            // ============================================================
	            // PASO 1: CREAR LA FÁBRICA DE DOCUMENTOS Y EL CONSTRUCTOR
	            // ============================================================
	            // DocumentBuilderFactory: crea instancias de DocumentBuilder
	            // que permiten construir árboles XML en memoria (DOM)
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

	            // DocumentBuilder: herramienta que construye el documento XML
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

	            // Document: representa el árbol XML completo que vamos a crear
	            Document doc = dBuilder.newDocument();

	            // ============================================================
	            // PASO 2: CONSTRUIR EL ÁRBOL XML (ESTRUCTURA DE NODOS)
	            // ============================================================

	            // Crear nodo raíz <alumnos>
	            Element raiz = doc.createElement("alumnos");
	            doc.appendChild(raiz);  // agregar la raíz al documento

	            // PRIMER ALUMNO
	            Element alumno1 = doc.createElement("alumno");
	            raiz.appendChild(alumno1);

	            // Atributo del alumno (id)
	            alumno1.setAttribute("id", "001");

	            // Subelementos del primer alumno
	            Element nombre1 = doc.createElement("nombre");
	            nombre1.setTextContent("Juan García López");
	            alumno1.appendChild(nombre1);

	            Element edad1 = doc.createElement("edad");
	            edad1.setTextContent("18");
	            alumno1.appendChild(edad1);

	            Element curso1 = doc.createElement("curso");
	            curso1.setTextContent("2º DAW");
	            alumno1.appendChild(curso1);

	            Element email1 = doc.createElement("email");
	            email1.setTextContent("juan.garcia@instituto.es");
	            alumno1.appendChild(email1);

	            // SEGUNDO ALUMNO
	            Element alumno2 = doc.createElement("alumno");
	            raiz.appendChild(alumno2);

	            alumno2.setAttribute("id", "002");

	            Element nombre2 = doc.createElement("nombre");
	            nombre2.setTextContent("María Rodríguez Martín");
	            alumno2.appendChild(nombre2);

	            Element edad2 = doc.createElement("edad");
	            edad2.setTextContent("17");
	            alumno2.appendChild(edad2);

	            Element curso2 = doc.createElement("curso");
	            curso2.setTextContent("2º DAW");
	            alumno2.appendChild(curso2);

	            Element email2 = doc.createElement("email");
	            email2.setTextContent("maria.rodriguez@instituto.es");
	            alumno2.appendChild(email2);

	            // TERCER ALUMNO
	            Element alumno3 = doc.createElement("alumno");
	            raiz.appendChild(alumno3);

	            alumno3.setAttribute("id", "003");

	            Element nombre3 = doc.createElement("nombre");
	            nombre3.setTextContent("Carlos Sánchez Blanco");
	            alumno3.appendChild(nombre3);

	            Element edad3 = doc.createElement("edad");
	            edad3.setTextContent("19");
	            alumno3.appendChild(edad3);

	            Element curso3 = doc.createElement("curso");
	            curso3.setTextContent("2º DAW");
	            alumno3.appendChild(curso3);

	            Element email3 = doc.createElement("email");
	            email3.setTextContent("carlos.sanchez@instituto.es");
	            alumno3.appendChild(email3);

	            // ============================================================
	            // PASO 3: CREAR Y CONFIGURAR EL TRANSFORMER
	            // ============================================================
	            // TransformerFactory: fábrica que crea transformadores
	            // Los transformadores convierten un árbol DOM a formato de salida
	            // (fichero, stream, etc.) con formato específico

	            Transformer transformer = TransformerFactory.newInstance().newTransformer();

	            // CONFIGURACIÓN DE INDENTACIÓN (pretty-print):
	            // Esto hace que el XML sea legible con sangría automática

	            // OutputKeys.INDENT = "yes"
	            // → Activa la indentación automática del XML
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

	            // indent-amount = "4"
	            // → Cantidad de espacios para cada nivel de indentación
	            // → Nota: esta propiedad es específica de Apache Xalan (no estándar)
	            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	            // OutputKeys.ENCODING = "UTF-8"
	            // → Especifica la codificación del fichero de salida
	            // → UTF-8 es estándar y soporta caracteres especiales (acentos, ñ, etc.)
	            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

	            // (OPCIONAL) Agregar declaración XML al inicio del fichero
	            transformer.setOutputProperty(OutputKeys.VERSION, "1.0");

	            // ============================================================
	            // PASO 4: EXPORTAR EL ÁRBOL XML A FICHERO FORMATEADO
	            // ============================================================

	            // Crear directorio si no existe
	            File directorio = new File("datos");
	            if (!directorio.exists()) {
	                directorio.mkdirs();
	            }

	            // DOMSource: encapsula el árbol DOM como origen de datos
	            DOMSource source = new DOMSource(doc);

	            // StreamResult: define el destino de la transformación (fichero)
	            StreamResult result = new StreamResult(new File("datos/alumnos.xml"));

	            // TRANSFORMACIÓN: convierte el árbol DOM a XML formateado en disco
	            // - Aplica la indentación configurada en el transformer
	            // - Escribe en UTF-8
	            // - Genera un fichero XML legible y bien estructurado
	            transformer.transform(source, result);

	            // ============================================================
	            // PASO 5: CONFIRMACIÓN DE ÉXITO
	            // ============================================================
	            System.out.println("✓ Archivo XML creado exitosamente en: datos/alumnos.xml");
	            System.out.println("✓ Indentación: 4 espacios");
	            System.out.println("✓ Codificación: UTF-8");
	            System.out.println("✓ Registros de alumnos: 3");

	        } catch (ParserConfigurationException e) {
	            // Error durante la configuración del parser XML
	            System.err.println("Error en la configuración del parser XML: " + e.getMessage());
	            e.printStackTrace();
	        } catch (TransformerException e) {
	            // Error durante la transformación/escritura del fichero
	            System.err.println("Error durante la transformación XML: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	}

