package act1_texto;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Gestiona registros de longitud fija en un archivo binario.
 * Se usa RandomAccessFile para modificar o leer un empleado concreto sin
 * necesidad de recorrer todo el fichero.
 */
public class Actividad2_3_RandomAccessFile {
	private static final int REGISTRO = 36; // 4+20+4+8
	
	public static void main (String [] main) {
		File f = new File("datos/empleados.dat");
		
		crearDatosIniciales(f);
		modificarSalario(f, 1, 3550.00);
		leerEmpleadoEnPosicion(f, 1);
	}
	
	private static void crearDatosIniciales (File f) {
			try (RandomAccessFile raf = new RandomAccessFile(f, "rw")) {
				// Vaciamos el contenido para crear un fichero limpio con los registros.
				raf.setLength(0);
				writeEmpleado(raf, 1, "Garcia", 10, 2100.50);
				writeEmpleado(raf, 2, "López", 20, 2800.00);
				writeEmpleado(raf, 3, "Pérez", 10, 1950.75);
				
				System.out.println("Archivo creado o reiniciado");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private static void writeEmpleado (RandomAccessFile raf, int id, String apellido, int dep, double salario) throws IOException {
		// Escribimos el identificador del empleado.
		raf.writeInt(id);
		
		// Formateamos el apellido a 10 caracteres para mantener longitud fija.
		StringBuilder sb = new StringBuilder(apellido);
		sb.setLength(10);
		
		raf.writeChars(sb.toString());
		
		raf.writeInt(dep);
		raf.writeDouble(salario);
	}
	
	public static void modificarSalario(File f, int posicion, double nuevoSalario) {
		// El salario está en una posición fija dentro del registro.
		long offsetSalario = ((long) posicion * REGISTRO) + 28;
		
		try (RandomAccessFile raf = new RandomAccessFile(f, "rw")) {
			raf.seek(offsetSalario);
			raf.writeDouble(nuevoSalario);
			
			System.out.println("Salario actualizado a " + nuevoSalario);
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public static void leerEmpleadoEnPosicion (File f, int posicion) {
		long offsetRegistro = (long) posicion * REGISTRO;
		
		try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
			// Nos posicionamos en el registro solicitado y leemos su primera parte.
			raf.seek(offsetRegistro);
			raf.readInt();
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}