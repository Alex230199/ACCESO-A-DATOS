package act1_texto;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * Analizador Reactivo sin Memoria con SAX
 * Problema Profesional: Calcular el valor total del inventario de un catálogo XML masivo
 * (varios gigabytes) con consumo de memoria RAM cercano a 0 MB utilizando el procesador
 * reactivo basado en eventos.
 */
public class Actividad3_3_SaxAnalizador {

    public static void main(String[] args) {
        try {
            // Crear el analizador SAX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // Crear el manejador personalizado
            ManejadorInventario manejador = new ManejadorInventario();

            // Parsear el archivo XML
            String rutaXML = "UT01_ManejoFicheros/src/act3_xml/catalogo.xml";
            parser.parse(new File(rutaXML), manejador);

            // Mostrar resultados
            manejador.mostrarResultados();

        } catch (Exception e) {
            System.err.println("Error al procesar el XML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Manejador personalizado que extiende DefaultHandler
     * Implementa el ciclo de vida del Handler para procesamiento de eventos SAX
     */
    public static class ManejadorInventario extends DefaultHandler {

        private StringBuilder buffer;           // Acumulador para el contenido de caracteres
        private double totalInventario = 0.0;  // Suma total de precios
        private int contadorProductos = 0;     // Contador de productos
        private double sumaPrecios = 0.0;      // Para calcular media
        private String elementoActual = "";    // Elemento que se está procesando

        public ManejadorInventario() {
            this.buffer = new StringBuilder();
        }

        /**
         * CICLO 1: startElement() - Se ejecuta al abrir una etiqueta
         * Limpiar acumulador con buffer.setLength(0)
         */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            // Limpiar el buffer para la próxima captura de caracteres
            buffer.setLength(0);
            elementoActual = qName;

            // Opcional: procesar atributos si existen
            if (attributes.getLength() > 0) {
                System.out.println("Atributos de <" + qName + ">:");
                for (int i = 0; i < attributes.getLength(); i++) {
                    System.out.println("  " + attributes.getQName(i) + " = " + attributes.getValue(i));
                }
            }
        }

        /**
         * CICLO 2: characters() - Se ejecuta cuando se encuentran caracteres de texto
         * El texto puede venir en trozos, por eso se acumula con buffer.append()
         */
        @Override
        public void characters(char[] ch, int start, int len) throws SAXException {
            // Acumular los caracteres en el buffer
            // Nota: El texto puede venir fragmentado en múltiples llamadas
            buffer.append(ch, start, len);
        }

        /**
         * CICLO 3: endElement() - Se ejecuta al cerrar una etiqueta
         * Si la etiqueta es "precio", parsear a double y sumar
         */
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            String contenido = buffer.toString().trim();

            // Procesar el contenido según el tipo de elemento
            if ("precio".equalsIgnoreCase(qName)) {
                try {
                    double precio = Double.parseDouble(contenido);
                    totalInventario += precio;
                    sumaPrecios += precio;
                    contadorProductos++;

                    System.out.println("Precio encontrado: " + precio + " EUR (Total acumulado: " + totalInventario + " EUR)");
                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear precio: '" + contenido + "'");
                }
            } else if ("nombre".equalsIgnoreCase(qName) && !contenido.isEmpty()) {
                System.out.println("Producto: " + contenido);
            } else if ("cantidad".equalsIgnoreCase(qName) && !contenido.isEmpty()) {
                System.out.println("Cantidad: " + contenido + " unidades");
            }

            // Limpiar el buffer después de procesar
            buffer.setLength(0);
            elementoActual = "";
        }

        /**
         * CICLO 4: endDocument() - Se ejecuta al finalizar el documento
         * Calcular y mostrar la media de precios final
         */
        @Override
        public void endDocument() throws SAXException {
            System.out.println("\n=== RESULTADOS FINALES ===");
            System.out.println("Número total de productos: " + contadorProductos);
            System.out.println("Valor total del inventario: " + totalInventario + " EUR");

            if (contadorProductos > 0) {
                double mediaPrecios = sumaPrecios / contadorProductos;
                System.out.println("Precio medio por producto: " + String.format("%.2f", mediaPrecios) + " EUR");
            }
            System.out.println("========================\n");
        }

        /**
         * Método auxiliar para mostrar resultados
         */
        public void mostrarResultados() {
            System.out.println("\n✓ Análisis completado con consumo mínimo de memoria.");
            System.out.println("✓ Se procesó el archivo XML sin cargar la totalidad en memoria.");
            System.out.println("✓ Enfoque reactivo basado en eventos (SAX PUSH).");
        }

        /**
         * Manejo de errores de SAX
         */
        @Override
        public void warning(org.xml.sax.SAXParseException e) throws SAXException {
            System.out.println("ADVERTENCIA (línea " + e.getLineNumber() + "): " + e.getMessage());
        }

        @Override
        public void error(org.xml.sax.SAXParseException e) throws SAXException {
            System.err.println("ERROR (línea " + e.getLineNumber() + "): " + e.getMessage());
        }

        @Override
        public void fatalError(org.xml.sax.SAXParseException e) throws SAXException {
            System.err.println("ERROR FATAL (línea " + e.getLineNumber() + "): " + e.getMessage());
            throw e;
        }
    }
}