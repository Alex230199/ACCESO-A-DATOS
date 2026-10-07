package act1_texto;

/*import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Actividad1_3_FacturaPrintWriter {
	
	public static void main (String [] args) {
		
		File archivoFact = new File("datos,factura.txt");
		
		String[] referencias = {"PORT-01", "RAT-02", "MON-03"};
		String[] descripciones = {"portatil 15 pulgadas", "raton inalambrico", "monitor 27 IPS"};
		Double[] precios = {750.50, 18.90, 199.99};
		int[] unidades = {2, 5 , 1};
		
		try(PrintWriter pw = new PrintWriter("datos,factura.txt", StandardCharsets.UTF_8)){
			// PrintWriter pw = new PrintWriter(new BufferWriter(new fileWriter(archivoFact)));
			
			pw.println("-------------------------------------------------");
			pw.println("          INFORME DE FACTURAFACTURA"              );
			pw.println("-------------------------------------------------");
			pw.printf("%-50 | %-50s | %-50s | %-50s | %-12s%n",
					"CODIGO", "PRODUCTO", "PRECIO", "UNIDADES", "TOTAL");
			pw.println("-------------------------------------------------");
			
			
			double subtotal = 0.0;
			
			for ( int i  = 0; i < referencias.length; i++) {
				
				double totalLinea = precios[i] * unidades[i];
				
				subtotal = subtotal + totalLinea;
				
				pw.printf("%-50 | %-50s | %-50s | %-50s | %-12s%n",
						referencias[i], descripciones[i], precios[i], unidades[i], totalLinea);
			}
			
			double iva = subtotal * 0.21;
			
			double totalFact = subtotal + iva;
			
			pw.println("-------------------------------------------------");
			pw.printf( "%-48s | %11.2f €%n", "BASE IMPONIBLE", subtotal);
			pw.printf( "%-48s | %11.2f €%n", "TOTAL FACTURA", totalFact);
			
			pw.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Actividad1_3_FacturaPrintWriter {
    
    public static void main (String [] args) {
        
        File archivoFact = new File("datos_factura.txt");
        
        String[] referencias = {"PORT-01", "RAT-02", "MON-03"};
        String[] descripciones = {"portatil 15 pulgadas", "raton inalambrico", "monitor 27 IPS"};
        Double[] precios = {750.50, 18.90, 199.99};
        int[] unidades = {2, 5, 1};
        
        // Usamos el objeto File y UTF-8 correctamente
        try (PrintWriter pw = new PrintWriter(archivoFact, StandardCharsets.UTF_8)) {
            
            pw.println("----------------------------------------------------------------------------------------");
            pw.println("                               INFORME DE FACTURA                                      ");
            pw.println("----------------------------------------------------------------------------------------");
            
            // Cabecera alineada con los tipos de datos de abajo
            pw.printf("%-10s | %-25s | %10s | %8s | %12s%n",
                    "CODIGO", "PRODUCTO", "PRECIO", "UNIDADES", "TOTAL");
            pw.println("----------------------------------------------------------------------------------------");
            
            double subtotal = 0.0;
            
            for (int i = 0; i < referencias.length; i++) {
                double totalLinea = precios[i] * unidades[i];
                subtotal += totalLinea;
                
                // Formato corregido para alinear texto y números correctamente
                pw.printf("%-10s | %-25s | %9.2f€ | %8d | %11.2f€%n",
                        referencias[i], descripciones[i], precios[i], unidades[i], totalLinea);
            }
            
            double iva = subtotal * 0.21;
            double totalFact = subtotal + iva;
            
            pw.println("----------------------------------------------------------------------------------------");
            pw.printf("%-52s | %11.2f €%n", "BASE IMPONIBLE", subtotal);
            pw.printf("%-52s | %11.2f €%n", "IVA (21%)", iva);
            pw.printf("%-52s | %11.2f €%n", "TOTAL FACTURA", totalFact);
            pw.println("----------------------------------------------------------------------------------------");
            
            // Nota: Al usar try-with-resources, pw.close() se llama solo automáticamente, 
            // pero dejarlo explícito no genera error.
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

