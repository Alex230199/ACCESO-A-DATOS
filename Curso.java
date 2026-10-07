package act1_texto;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Clase Curso: Modelo de datos con anotaciones JAXB
 * 
 * Las anotaciones @Xml* permiten mapear declarativamente los atributos
 * de la clase a elementos y atributos XML sin necesidad de manipular DOM.
 */

// Define el elemento raíz del documento XML que se generará (<curso>)
@XmlRootElement(name = "curso")

// Especifica que JAXB acceda a los atributos de la clase (no a los getters)
// para serializar/deserializar
@XmlAccessorType(XmlAccessType.FIELD)

public class Curso {
	 // El atributo codigo se mapea como un atributo XML (ej: <curso codigo="DAM01">)
    @XmlAttribute
    private String codigo;

    // El atributo nombre se mapea como un elemento XML anidado (<nombre>)
    @XmlElement
    private String nombre;

    // El atributo horas se mapea como un elemento XML anidado (<horas>)
    @XmlElement
    private int horas;

    /**
     * Constructor vacío OBLIGATORIO para JAXB
     * Sin este constructor, el Unmarshaller no puede crear instancias
     * de la clase al deserializar XML
     */
    public Curso() {
    }

    /**
     * Constructor con parámetros para crear objetos Curso con valores iniciales
     */
    public Curso(String codigo, String nombre, int horas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.horas = horas;
    }

    // GETTERS Y SETTERS
    // Permiten acceder y modificar los atributos privados de la clase

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    /**
     * Método toString: Representación en texto del objeto Curso
     * Útil para depuración y visualización en consola
     */
    @Override
    public String toString() {
        return "Curso{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", horas=" + horas +
                '}';
    }
}

