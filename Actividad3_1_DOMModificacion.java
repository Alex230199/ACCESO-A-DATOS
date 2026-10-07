package act1_texto;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Actividad3_1_DOMModificacion {
//-------------------------------FORMA 1 DE HACERLO-----------------------------------
/*	  public static void main(String[] args) {
	        try {
	            // 1. Ruta del archivo XML
	            File archivo = new File("datos/catalogo.xml");

	            // 2. Cargamos el documento XML en memoria
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.parse(archivo);

	            // 3. Normalizamos el documento para evitar problemas con nodos #text
	            doc.getDocumentElement().normalize();

	            // 4. Mostramos catálogo antes
	            System.out.println("=== CATÁLOGO ORIGINAL ===");
	            mostrarProductos(doc);

	            // 5. Modificamos los precios
	            modificarPrecios(doc);

	            // 6. Mostramos catálogo después
	            System.out.println("\n=== CATÁLOGO MODIFICADO (con inflación +10%) ===");
	            mostrarProductos(doc);

	        } catch (Exception e) {
	            System.err.println("Error al procesar el XML: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }

	    // Método para aumentar el precio un 10%
	    static void modificarPrecios(Document doc) {
	        NodeList productos = doc.getElementsByTagName("producto");

	        for (int i = 0; i < productos.getLength(); i++) {
	            Node nodoProducto = productos.item(i);

	            if (nodoProducto.getNodeType() == Node.ELEMENT_NODE) {
	                Element producto = (Element) nodoProducto;
	                NodeList precioNodes = producto.getElementsByTagName("precio");

	                if (precioNodes.getLength() > 0) {
	                    Element precioElement = (Element) precioNodes.item(0);

	                    String precioTexto = precioElement.getTextContent().trim();
	                    precioTexto = precioTexto.replace(",", ".");

	                    double precioActual = Double.parseDouble(precioTexto);
	                    double precioNuevo = precioActual * 1.10;

	                    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
	                    DecimalFormat df = new DecimalFormat("0.00", symbols);
	                    precioElement.setTextContent(df.format(precioNuevo));
	                }
	            }
	        }
	    }

	    // Método para mostrar productos
	    static void mostrarProductos(Document doc) {
	        NodeList productos = doc.getElementsByTagName("producto");

	        for (int i = 0; i < productos.getLength(); i++) {
	            Node nodoProducto = productos.item(i);

	            if (nodoProducto.getNodeType() == Node.ELEMENT_NODE) {
	                Element producto = (Element) nodoProducto;

	                String id = producto.getAttribute("id");
	                String nombre = producto.getElementsByTagName("nombre").item(0).getTextContent();
	                String precioTexto = producto.getElementsByTagName("precio").item(0).getTextContent();
	                String stock = producto.getElementsByTagName("stock").item(0).getTextContent();

	                precioTexto = precioTexto.replace(",", ".");
	                double precio = Double.parseDouble(precioTexto);

	                System.out.printf("ID: %s | Nombre: %s | Precio: %.2f | Stock: %s unidades%n",
	                        id, nombre, precio, stock);
	            }
	        }
	    }
	    
	    */
	
	//----------------------------FORMA 2 DE HACERLO------------------------------------
		
		File f = new File("datos/catalogo.xml");
		
}
