// Importamos la clase Scanner del paquete java.util para poder leer la entrada de datos por consola
import java.util.Scanner;

// =========================================================================
// 1. CLASE ABSTRACTA PERSONA (Aplicamos Abstracción y Herencia)
// =========================================================================
// Declaramos la clase Persona como abstracta porque no queremos instanciar personas genéricas,
// sino únicamente tipos específicos como Usuarios o Bibliotecarios.
abstract class Persona {
    // Atributo protegido para almacenar el ID identificador de la persona
    protected int id;
    // Atributo protegido para el nombre completo de la persona
    protected String nombre;
    // Atributo protegido para el correo electrónico de contacto
    protected String correo;

    // Constructor por defecto que inicializa los atributos con valores neutros
    public Persona() {
        // Asignamos 0 como ID por defecto
        this.id = 0;
        // Asignamos una cadena genérica para el nombre
        this.nombre = "Sin nombre";
        // Asignamos un correo genérico
        this.correo = "sin@correo.com";
    }

    // Constructor con parámetros para inicializar una persona con datos específicos
    public Persona(int id, String nombre, String correo) {
        // Asignamos el valor del parámetro id al atributo de la clase
        this.id = id;
        // Asignamos el nombre recibido al atributo correspondiente
        this.nombre = nombre;
        // Asignamos el correo recibido al atributo correspondiente
        this.correo = correo;
    }

    // Método abstracto obligatorio que obliga a las subclases a definir cómo muestran su información
    public abstract void mostrarInformacion();

    // Método getter para obtener el ID de la persona
    public int getId() { return id; }
    // Método setter para actualizar el ID de la persona
    public void setId(int id) { this.id = id; }

    // Método getter para obtener el nombre de la persona
    public String getNombre() { return nombre; }
    // Método setter para actualizar el nombre de la persona
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Método getter para obtener el correo de la persona
    public String getCorreo() { return correo; }
    // Método setter para actualizar el correo de la persona
    public void setCorreo(String correo) { this.correo = correo; }
}

// =========================================================================
// 2. CLASE USUARIO (Hereda de la clase Persona)
// =========================================================================
// La clase Usuario hereda los atributos y métodos públicos/protegidos de Persona
class Usuario extends Persona {
    // Contador privado que lleva el registro de cuántos libros tiene prestados actualmente
    private int librosPrestados;
    // Cadena privada para almacenar el número telefónico
    private String telefonoCelular;
    // Bandera booleana para saber si el usuario está activo o suspendido en el sistema
    private boolean activo;

    // Constructor por defecto
    public Usuario() {
        // Llamamos al constructor por defecto de la clase madre (Persona)
        super();
        // Inicializamos el contador de libros prestados en 0
        this.librosPrestados = 0;
        // Asignamos un teléfono genérico por defecto
        this.telefonoCelular = "00-00-00-00-00";
        // Definimos el estado del usuario como activo por defecto
        this.activo = true;
    }

    // Primer constructor sobrecargado (recibe id, nombre, correo y estado activo)
    public Usuario(int id, String nombre, String correo, boolean activo) {
        // Invocamos al constructor de Persona pasándole id, nombre y correo
        super(id, nombre, correo);
        // Inicializamos el contador de libros prestados en 0
        this.librosPrestados = 0;
        // Establecemos el teléfono como no registrado
        this.telefonoCelular = "No registrado";
        // Asignamos el valor del estado activo recibido
        this.activo = activo;
    }

    // Segundo constructor sobrecargado (recibe id, nombre, correo, teléfono y estado activo para no chocar firmas)
    public Usuario(int id, String nombre, String correo, String telefonoCelular, boolean activo) {
        // Enviamos los datos generales a la clase padre mediante super
        super(id, nombre, correo);
        // Inicializamos el contador de libros prestados en 0
        this.librosPrestados = 0;
        // Asignamos el número celular recibido
        this.telefonoCelular = telefonoCelular;
        // Asignamos el estado de actividad del usuario
        this.activo = activo;
    }

    // Método para simular la solicitud de un préstamo por parte del usuario
    public void solicitarPrestamo() {
        // Verificamos si el usuario se encuentra con estatus activo
        if (activo) {
            // Incrementamos la cantidad de libros que tiene prestados
            librosPrestados++;
            // Imprimimos la confirmación en consola indicando la nueva cantidad de préstamos
            System.out.println("-> El usuario " + nombre + " ha solicitado un préstamo. Libros prestados actualmente: " + librosPrestados);
        } else {
            // Si el usuario no está activo, mostramos un mensaje de restricción
            System.out.println("-> El usuario " + nombre + " no está activo.");
        }
    }

    // Método para simular la devolución de un libro prestado
    public void devolverLibro() {
        // Comprobamos si el usuario tiene al menos un libro en su poder
        if (librosPrestados > 0) {
            // Decrementamos la cuenta de libros prestados
            librosPrestados--;
            // Mostramos la confirmación en consola indicando la cantidad restante
            System.out.println("-> El usuario " + nombre + " devolvió un libro. Libros pendientes: " + librosPrestados);
        } else {
            // Si el contador está en 0, indicamos que no tiene adeudos de libros
            System.out.println("-> El usuario " + nombre + " no tiene libros pendientes por devolver.");
        }
    }

    // Sobreescritura del método abstracto declarado en Persona
    @Override
    public void mostrarInformacion() {
        // Imprimimos todos los datos concatenados del usuario
        System.out.println("[USUARIO] ID: " + id + " | Nombre: " + nombre + " | Correo: " + correo +
                " | Teléfono: " + telefonoCelular + " | Activo: " + activo +
                " | Libros prestados: " + librosPrestados);
    }

    // Getter para consultar la cantidad de libros prestados
    public int getLibrosPrestados() { return librosPrestados; }
    // Getter para consultar el teléfono celular
    public String getTelefonoCelular() { return telefonoCelular; }
    // Setter para modificar el teléfono celular
    public void setTelefonoCelular(String telefonoCelular) { this.telefonoCelular = telefonoCelular; }
    // Getter para validar el estatus de la cuenta del usuario
    public boolean isActivo() { return activo; }
    // Setter para cambiar el estatus de la cuenta del usuario
    public void setActivo(boolean activo) { this.activo = activo; }
}

// =========================================================================
// 3. CLASE BIBLIOTECARIO (Hereda de la clase Persona)
// =========================================================================
// La clase Bibliotecario representa al personal encargada de administrar los libros y préstamos
class Bibliotecario extends Persona {
    // Atributo privado para indicar si trabaja en el turno matutino o vespertino
    private String turno;
    // Atributo privado que guarda el salario asignado al bibliotecario
    private double salario;

    // Constructor por defecto
    public Bibliotecario() {
        // Ejecutamos el constructor por defecto de Persona
        super();
        // Definimos por defecto el turno matutino
        this.turno = "Matutino";
        // Asignamos un salario base inicial de 0.0
        this.salario = 0.0;
    }

    // Constructor parametrizado para registrar un bibliotecario completo
    public Bibliotecario(int id, String nombre, String correo, String turno, double salario) {
        // Reutilizamos el constructor de Persona para id, nombre y correo
        super(id, nombre, correo);
        // Asignamos el turno de trabajo
        this.turno = turno;
        // Asignamos el salario
        this.salario = salario;
    }

    // Método para representar la acción de autorizar un préstamo a un alumno/usuario
    public void autorizarPrestamo() {
        // Mostramos el mensaje en pantalla con el nombre del empleado que autoriza
        System.out.println("-> El bibliotecario " + nombre + " ha autorizado un préstamo.");
    }

    // Método para simular el registro formal de un ejemplar en la base de datos
    public void registrarLibro() {
        // Imprimimos el mensaje indicando qué bibliotecario está haciendo el alta
        System.out.println("-> El bibliotecario " + nombre + " registró un nuevo libro en el sistema.");
    }

    // Método para simular la baja de un libro deteriorado o perdido
    public void eliminarLibro() {
        // Imprimimos la confirmación en consola
        System.out.println("-> El bibliotecario " + nombre + " eliminó un libro del sistema.");
    }

    // Implementación concreta del método para desplegar la información del bibliotecario
    @Override
    public void mostrarInformacion() {
        // Imprimimos la ficha técnica del bibliotecario
        System.out.println("[BIBLIOTECARIO] ID: " + id + " | Nombre: " + nombre + " | Correo: " + correo +
                " | Turno: " + turno + " | Salario: $" + salario);
    }

    // Getter para el turno
    public String getTurno() { return turno; }
    // Setter para cambiar el turno
    public void setTurno(String turno) { this.turno = turno; }
    // Getter para el salario
    public double getSalario() { return salario; }
    // Setter para cambiar el salario
    public void setSalario(double salario) { this.salario = salario; }
}

// =========================================================================
// 4. CLASE LIBRO (Encapsulamiento, Sobrecarga y Uso de Atributos Static)
// =========================================================================
// Clase concreta que modela un libro físico dentro del inventario
class Libro {
    // Atributo privado para el código estándar internacional ISBN
    private String isbn;
    // Atributo privado para el título de la obra
    private String titulo;
    // Atributo privado para el autor o autores de la obra
    private String autor;
    // Atributo privado para el año de edición/publicación
    private int anioPublicacion;
    // Atributo privado para controlar si el libro está libre o ya prestado
    private boolean disponible;

    // Variable estática compartida por todos los objetos Libro para contar cuántos se han creado
    private static int totalLibros = 0;

    // Constructor sin parámetros
    public Libro() {
        // Asignamos un valor nulo ficticio para el ISBN
        this.isbn = "0000000000";
        // Asignamos el título genérico
        this.titulo = "Sin título";
        // Asignamos un autor por defecto
        this.autor = "Anónimo";
        // Asignamos 0 al año
        this.anioPublicacion = 0;
        // Por defecto, el libro recién creado estará disponible
        this.disponible = true;
        // Incrementamos el contador global estático de libros instanciados
        totalLibros++;
    }

    // Primer constructor sobrecargado (recibe solo título y autor)
    public Libro(String titulo, String autor) {
        // Asignamos un ISBN por defecto
        this.isbn = "0000000000";
        // Asignamos el título recibido
        this.titulo = titulo;
        // Asignamos el autor recibido
        this.autor = autor;
        // Asignamos el año actual por defecto
        this.anioPublicacion = 2024;
        // Marcamos como disponible
        this.disponible = true;
        // Incrementamos la variable estática contadora
        totalLibros++;
    }

    // Segundo constructor sobrecargado (recibe ISBN, título, autor y año de publicación)
    public Libro(String isbn, String titulo, String autor, int anioPublicacion) {
        // Asignamos el código ISBN recibido
        this.isbn = isbn;
        // Asignamos el título recibido
        this.titulo = titulo;
        // Asignamos el autor del libro
        this.autor = autor;
        // Asignamos el año de publicación
        this.anioPublicacion = anioPublicacion;
        // Establecemos disponibilidad en verdadero
        this.disponible = true;
        // Sumamos uno al acumulador estático de libros
        totalLibros++;
    }

    // Método para verificar la disponibilidad del ejemplar
    public boolean estaDisponible() { return disponible; }

    // Método para marcar que el libro fue prestado
    public void prestar() {
        // Revisamos si el libro está libre para prestarse
        if (disponible) {
            // Cambiamos el estado a no disponible
            disponible = false;
            // Desplegamos el mensaje de préstamo exitoso
            System.out.println("-> El libro '" + titulo + "' ha sido prestado exitosamente.");
        } else {
            // Avisamos que el libro ya se encuentra ocupado
            System.out.println("-> El libro '" + titulo + "' ya se encuentra prestado actualmente.");
        }
    }

    // Método para registrar la devolución del libro a la estantería
    public void devolver() {
        // Restauramos el estatus a disponible
        disponible = true;
        // Mostramos el mensaje en consola
        System.out.println("-> El libro '" + titulo + "' ha sido marcado como disponible.");
    }

    // Getter para ISBN
    public String getIsbn() { return isbn; }
    // Setter para ISBN
    public void setIsbn(String isbn) { this.isbn = isbn; }
    // Getter para el Título
    public String getTitulo() { return titulo; }
    // Setter para el Título
    public void setTitulo(String titulo) { this.titulo = titulo; }
    // Getter para el Autor
    public String getAutor() { return autor; }
    // Setter para el Autor
    public void setAutor(String autor) { this.autor = autor; }
    // Getter para el Año de publicación
    public int getAnioPublicacion() { return anioPublicacion; }
    // Setter para el Año de publicación
    public void setAnioPublicacion(int anioPublicacion) { this.anioPublicacion = anioPublicacion; }
    // Setter directo para la disponibilidad
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    // Método estático de clase para consultar el contador global de libros creados
    public static int getTotalLibros() { return totalLibros; }

    // Método auxiliar para formatear e imprimir la información completa del objeto Libro
    public void mostrarInformacion() {
        // Imprimimos la ficha técnica con todos sus atributos encapsulados
        System.out.println("ISBN: " + isbn + " | Título: " + titulo + " | Autor: " + autor +
                " | Año: " + anioPublicacion + " | Disponible: " + disponible);
    }
}

// =========================================================================
// 5. CLASE PRINCIPAL (Punto de Entrada y Menú Consola Interactivo)
// =========================================================================
// Clase principal ejecutable que debe coincidir con el nombre del archivo Main.java
public class Main {
    // Método principal ejecutable main de Java
    public static void main(String[] args) {
        // Inicializamos la lectura de datos por teclado mediante Scanner
        Scanner sc = new Scanner(System.in);

        // Variable para manipular un objeto Libro dentro del flujo interactivo
        Libro miLibro = null;
        // Variable para manipular un objeto Usuario
        Usuario miUsuario = null;
        // Variable para manipular un objeto Bibliotecario
        Bibliotecario miBibliotecario = null;

        // Variable para guardar la elección del usuario en el menú
        int opcion = 0;

        // Estructura do-while para repetir el menú hasta que el usuario seleccione la opción de salir
        do {
            // Imprimimos el encabezado del programa
            System.out.println("\n==============================================");
            System.out.println("    SISTEMA DE GESTIÓN DE BIBLIOTECA - AVANCE");
            System.out.println("==============================================");
            // Opción 1: Crear libro
            System.out.println("1. Registrar un Libro");
            // Opción 2: Crear usuario
            System.out.println("2. Registrar un Usuario");
            // Opción 3: Crear bibliotecario
            System.out.println("3. Registrar un Bibliotecario");
            // Opción 4: Consultar lo registrado
            System.out.println("4. Mostrar información general");
            // Opción 5: Probar métodos de Usuario
            System.out.println("5. Probar acciones de Usuario (Solicitar/Devolver)");
            // Opción 6: Probar métodos de Libro
            System.out.println("6. Probar acciones de Libro (Prestar/Devolver)");
            // Opción 7: Probar métodos de Bibliotecario
            System.out.println("7. Probar acciones de Bibliotecario");
            // Opción 8: Verificar el total estático
            System.out.println("8. Ver Total de Libros Creados (Atributo Static)");
            // Opción 9: Terminar la ejecución
            System.out.println("9. Salir");
            // Solicitamos la selección
            System.out.print("Selecciona una opción: ");

            // Validamos que la entrada leída por teclado sea un número entero válido
            if (sc.hasNextInt()) {
                // Leemos la opción elegida
                opcion = sc.nextInt();
                // Limpiamos el salto de línea sobrante del buffer del Scanner
                sc.nextLine();
            } else {
                // Notificamos el error si el usuario no teclea un número
                System.out.println("Por favor ingresa un número válido.");
                // Limpiamos el texto inválido del buffer
                sc.nextLine();
                // Saltamos a la siguiente iteración del ciclo
                continue;
            }

            // Estructura de selección múltiple switch para redirigir la acción seleccionada
            switch (opcion) {
                // Caso para el alta de un libro
                case 1:
                    System.out.println("\n--- REGISTRO DE LIBRO ---");
                    // Pedimos el ISBN
                    System.out.print("Ingresa el ISBN: ");
                    String isbn = sc.nextLine();
                    // Pedimos el Título
                    System.out.print("Ingresa el Título: ");
                    String titulo = sc.nextLine();
                    // Pedimos el Autor
                    System.out.print("Ingresa el Autor: ");
                    String autor = sc.nextLine();
                    // Pedimos el Año de publicación
                    System.out.print("Ingresa el Año de Publicación: ");
                    int anio = sc.nextInt();
                    // Limpiamos el buffer tras leer un número
                    sc.nextLine();

                    // Instanciamos el nuevo libro pasando todos sus argumentos al constructor
                    miLibro = new Libro(isbn, titulo, autor, anio);
                    // Avisamos que la creación fue exitosa
                    System.out.println("¡Libro registrado correctamente!");
                    break;

                // Caso para el alta de un usuario
                case 2:
                    System.out.println("\n--- REGISTRO DE USUARIO ---");
                    // Pedimos la matrícula o ID
                    System.out.print("Ingresa el ID del usuario: ");
                    int idU = sc.nextInt();
                    // Limpiamos buffer
                    sc.nextLine();
                    // Pedimos el nombre del alumno o usuario
                    System.out.print("Ingresa el Nombre: ");
                    String nomU = sc.nextLine();
                    // Pedimos el correo institucional/personal
                    System.out.print("Ingresa el Correo: ");
                    String correoU = sc.nextLine();
                    // Pedimos el número de contacto celular
                    System.out.print("Ingresa el Teléfono Celular: ");
                    String telU = sc.nextLine();

                    // Instanciamos el objeto Usuario con el constructor completo que no colisiona
                    miUsuario = new Usuario(idU, nomU, correoU, telU, true);
                    // Mostramos la confirmación
                    System.out.println("¡Usuario registrado correctamente!");
                    break;

                // Caso para el alta de un bibliotecario
                case 3:
                    System.out.println("\n--- REGISTRO DE BIBLIOTECARIO ---");
                    // Pedimos el número de empleado / ID
                    System.out.print("Ingresa el ID del bibliotecario: ");
                    int idB = sc.nextInt();
                    // Limpiamos buffer
                    sc.nextLine();
                    // Pedimos nombre completo
                    System.out.print("Ingresa el Nombre: ");
                    String nomB = sc.nextLine();
                    // Pedimos correo
                    System.out.print("Ingresa el Correo: ");
                    String correoB = sc.nextLine();
                    // Pedimos el turno laboral
                    System.out.print("Ingresa el Turno (Matutino/Vespertino): ");
                    String turnoB = sc.nextLine();
                    // Pedimos el sueldo
                    System.out.print("Ingresa el Salario: ");
                    double salarioB = sc.nextDouble();
                    // Limpiamos buffer
                    sc.nextLine();

                    // Creamos la instancia de la clase Bibliotecario
                    miBibliotecario = new Bibliotecario(idB, nomB, correoB, turnoB, salarioB);
                    // Mostramos confirmación
                    System.out.println("¡Bibliotecario registrado correctamente!");
                    break;

                // Caso para imprimir las propiedades registradas
                case 4:
                    System.out.println("\n--- INFORMACIÓN DE ENTIDADES ---");
                    // Si el objeto libro ya fue creado, llamamos a su método
                    if (miLibro != null) miLibro.mostrarInformacion();
                    else System.out.println("No hay ningún libro registrado.");

                    // Si el usuario existe, mostramos sus datos
                    if (miUsuario != null) miUsuario.mostrarInformacion();
                    else System.out.println("No hay ningún usuario registrado.");

                    // Si el bibliotecario existe, mostramos sus datos
                    if (miBibliotecario != null) miBibliotecario.mostrarInformacion();
                    else System.out.println("No hay ningún bibliotecario registrado.");
                    break;

                // Caso para interactuar con las acciones del usuario
                case 5:
                    // Verificamos que exista el usuario antes de invocar métodos
                    if (miUsuario == null) {
                        System.out.println("Primero debes registrar un usuario (Opción 2).");
                    } else {
                        // Submenú numérico
                        System.out.println("\n1. Solicitar Préstamo");
                        System.out.println("2. Devolver Libro");
                        System.out.print("Elige una opción: ");
                        int opU = sc.nextInt();
                        sc.nextLine();
                        // Ejecutamos la solicitud
                        if (opU == 1) miUsuario.solicitarPrestamo();
                            // Ejecutamos la devolución
                        else if (opU == 2) miUsuario.devolverLibro();
                        else System.out.println("Opción inválida.");
                    }
                    break;

                // Caso para modificar directamente el estado de un objeto Libro
                case 6:
                    // Verificamos que exista el libro
                    if (miLibro == null) {
                        System.out.println("Primero debes registrar un libro (Opción 1).");
                    } else {
                        // Submenú numérico
                        System.out.println("\n1. Prestar Libro");
                        System.out.println("2. Devolver Libro");
                        System.out.print("Elige una opción: ");
                        int opL = sc.nextInt();
                        sc.nextLine();
                        // Llamamos al método prestar
                        if (opL == 1) miLibro.prestar();
                            // Llamamos al método devolver
                        else if (opL == 2) miLibro.devolver();
                        else System.out.println("Opción inválida.");
                    }
                    break;

                // Caso para ejecutar comportamientos propios del personal bibliotecario
                case 7:
                    // Verificamos que exista el empleado bibliotecario
                    if (miBibliotecario == null) {
                        System.out.println("Primero debes registrar un bibliotecario (Opción 3).");
                    } else {
                        // Submenú de operaciones
                        System.out.println("\n1. Registrar Libro en sistema");
                        System.out.println("2. Autorizar Préstamo");
                        System.out.println("3. Eliminar Libro de sistema");
                        System.out.print("Elige una opción: ");
                        int opB = sc.nextInt();
                        sc.nextLine();
                        // Ejecutamos el registro simulado
                        if (opB == 1) miBibliotecario.registrarLibro();
                            // Autorizamos préstamo
                        else if (opB == 2) miBibliotecario.autorizarPrestamo();
                            // Eliminamos del catálogo
                        else if (opB == 3) miBibliotecario.eliminarLibro();
                        else System.out.println("Opción inválida.");
                    }
                    break;

                // Caso para revisar el funcionamiento de la variable estática static
                case 8:
                    // Invocamos el método estático directamente a través del nombre de la clase Libro
                    System.out.println("\nTotal de objetos 'Libro' instanciados: " + Libro.getTotalLibros());
                    break;

                // Opción para salir
                case 9:
                    System.out.println("Saliendo del programa...");
                    break;

                // Captura de opción fuera de rango del menú
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
            // Condición para mantener la ejecución viva mientras no sea la opción 9
        } while (opcion != 9);

        // Cerramos la lectura del Scanner para liberar recursos
        sc.close();
    }
}
