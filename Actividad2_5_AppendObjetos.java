package act1_texto;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Actividad 2.5: Persistencia Incremental
 * Clase que practica la escritura de objetos al final de un fichero serializado
 * sin provocar StreamCorruptedException al usar append mode (FileOutputStream con true).
 * 
 * PROBLEMA: FileOutputStream(f, true) + ObjectOutputStream normal inyecta una cabecera
 * intermedia que corrompe el stream cuando se intenta leer.
 * 
 * SOLUCIÓN: Subclase MiObjectOutputStream que anula writeStreamHeader() para evitar
 * la escritura de cabecera secundaria.
 */

public class Actividad2_5_AppendObjetos {

	/**
	 * Subclase de ObjectOutputStream que NO escribe cabecera intermedia.
	 * Esto permite hacer append seguro a un archivo serializado existente.
	 */
	public static class MiObjectOutputStream extends ObjectOutputStream {
		
		/**
		 * Constructor que inicializa el stream sin escribir cabecera adicional.
		 * @param out OutputStream destino (típicamente FileOutputStream en append mode)
		 * @throws IOException si hay error en la inicialización
		 */
		public MiObjectOutputStream(OutputStream out) throws IOException {
			super(out);
		}
		
		/**
		 * Sobrescribe el método writeStreamHeader() dejándolo vacío.
		 * De este modo, NO se inyecta la cabecera de 4 bytes en mitad del archivo.
		 * Esto evita la corrupción del stream al leer posteriormente.
		 * 
		 * @throws IOException si hay error (en este caso nunca la lanza porque está vacío)
		 */
		@Override
		protected void writeStreamHeader() throws IOException {
			// Método intencional vacío: NO escribimos cabecera secundaria
			// Esto permite concatenar objetos sin corrupción de datos
		}
	}
	
	/**
	 * Ejemplo de Usuario serializable para las pruebas de persistencia.
	 */
	public static class Usuario implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private int id;
		private String nombre;
		private String email;
		
		public Usuario(int id, String nombre, String email) {
			this.id = id;
			this.nombre = nombre;
			this.email = email;
		}
		
		@Override
		public String toString() {
			return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + "]";
		}
	}
	
	/**
	 * Método para escribir/append objetos a un fichero binario.
	 * Usa el patrón canónico:
	 * - Si el archivo existe y tiene datos: usa MiObjectOutputStream (sin cabecera intermedia)
	 * - Si el archivo no existe o está vacío: usa ObjectOutputStream normal (con cabecera única)
	 * 
	 * @param archivo Ruta del fichero donde guardar el objeto
	 * @param objeto El objeto a serializar y guardar
	 * @throws IOException si hay error de I/O
	 */
	public static void anadirObjeto(String archivo, Object objeto) throws IOException {
		File f = new File(archivo);
		
		// PASO 1: Detectar si el archivo ya existe y contiene datos
		boolean yaExisteConDatos = f.exists() && f.length() > 0;
		
		// PASO 2: Abrir el stream de salida en modo append (no sobrescribir)
		try (FileOutputStream fos = new FileOutputStream(f, true);
		     // PASO 3: Elegir el ObjectOutputStream correcto según el estado del archivo
		     ObjectOutputStream oos = yaExisteConDatos 
		             ? new MiObjectOutputStream(fos)      // Archivo con datos: SIN cabecera
		             : new ObjectOutputStream(fos)) {      // Archivo nuevo/vacío: CON cabecera
			
			// PASO 4: Escribir el objeto al final del archivo
			oos.writeObject(objeto);
			oos.flush();
		}
	}
	
	/**
	 * Método para leer todos los objetos de un fichero binario.
	 * Lee secuencialmente hasta encontrar EOFException.
	 * 
	 * @param archivo Ruta del fichero a leer
	 * @throws IOException si hay error de I/O
	 * @throws ClassNotFoundException si la clase del objeto no se encuentra
	 */
	public static void leerObjetos(String archivo) throws IOException, ClassNotFoundException {
		File f = new File(archivo);
		
		if (!f.exists()) {
			System.out.println("El archivo no existe: " + archivo);
			return;
		}
		
		// Abrir el stream de entrada
		try (FileInputStream fis = new FileInputStream(f);
		     ObjectInputStream ois = new ObjectInputStream(fis)) {
			
			System.out.println("\n--- Leyendo objetos del archivo ---");
			int contador = 0;
			
			// PASO 1: Bucle infinito que lee hasta EOFException
			while (true) {
				try {
					// PASO 2: Leer el siguiente objeto del stream
					Object obj = ois.readObject();
					contador++;
					System.out.println(contador + ". " + obj);
					
				} catch (EOFException e) {
					// PASO 3: Cuando llegamos al final, la excepción indica fin de lectura
					System.out.println("\nFin de archivo alcanzado. Total objetos leídos: " + contador);
					break;
				}
			}
		}
	}
	
	/**
	 * Método principal para probar la funcionalidad.
	 * Ejecuta 3 veces para demostrar que el append funciona sin corrupción.
	 * 
	 * @param args argumentos de línea de comandos (no utilizados)
	 */
	public static void main(String[] args) {
		String rutaArchivo = "UT01_ManejoFicheros/datos/usuarios.obj";
		
		try {
			// Crear archivo si no existe
			File f = new File(rutaArchivo);
			f.getParentFile().mkdirs();
			
			// EJECUCIÓN 1: Escribir usuario 1
			System.out.println("=== EJECUCIÓN 1 ===");
			Usuario usuario1 = new Usuario(1, "Alice", "alice@example.com");
			anadirObjeto(rutaArchivo, usuario1);
			System.out.println("Guardado: " + usuario1);
			leerObjetos(rutaArchivo);
			
			// EJECUCIÓN 2: Escribir usuario 2 (append sin corrupción)
			System.out.println("\n=== EJECUCIÓN 2 ===");
			Usuario usuario2 = new Usuario(2, "Bob", "bob@example.com");
			anadirObjeto(rutaArchivo, usuario2);
			System.out.println("Guardado: " + usuario2);
			leerObjetos(rutaArchivo);
			
			// EJECUCIÓN 3: Escribir usuario 3 (append sin corrupción)
			System.out.println("\n=== EJECUCIÓN 3 ===");
			Usuario usuario3 = new Usuario(3, "Carlos", "carlos@example.com");
			anadirObjeto(rutaArchivo, usuario3);
			System.out.println("Guardado: " + usuario3);
			leerObjetos(rutaArchivo);
			
			System.out.println("\n✅ Actividad completada exitosamente sin StreamCorruptedException");
			
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("❌ Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

