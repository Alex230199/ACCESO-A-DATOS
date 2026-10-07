package act1_texto;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Actividad2_4_SerializacionTransient {

	/**
	 * Serializa objetos Usuario y los vuelve a leer.
	 * La clase Usuario tiene un campo transient, por lo que no se guarda al serializar.
	 */
		public static void main (String [] args) {
			// Archivo donde se almacenarán los objetos serializados.
			String ruta = "datos/usuarios.ser";
			
			Usuario u1 = new Usuario (1, "admin", "TOKEN_SECRET_666");
			Usuario u2 = new Usuario (2, "belen", "TOKEN_SECRET_555");

			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
				// Se escriben dos objetos Usuario en el mismo archivo.
				oos.writeObject(u1);
				oos.writeObject(u2);
				System.out.println("OUT HECHO");
			} catch (IOException e) {
				System.err.println("Error: " + e.getMessage());
			}

			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
				// Se leen los dos objetos serializados en el mismo orden en que fueron escritos.
				Usuario u1Recuperado = (Usuario) ois.readObject();
				Usuario u2Recuperado = (Usuario) ois.readObject();
				
				System.out.println("IN HECHO\n" + u1Recuperado + "\n" + u2Recuperado);
			} catch (IOException | ClassNotFoundException e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}

