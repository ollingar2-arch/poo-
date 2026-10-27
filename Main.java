// Importamos la clase Scanner para capturar datos por consola
import java.util.Scanner;
// Importamos la clase ArrayList para manejar arreglos dinámicos de objetos
import java.util.ArrayList;

// =========================================================================
// 0. INTERFAZ PRESTABLE
// =========================================================================
// Definimos la interfaz Prestable que establece un contrato de comportamiento
interface Prestable {
    // Método abstracto para prestar un elemento
    void prestar();
    // Método abstracto para devolver un elemento
    void devolver();
}

// =========================================================================
// 1. CLASE ABSTRACTA PERSONA (Abstracción y Herencia)
// =========================================================================
// Declaramos la clase Persona como abstracta para servir de base a las subclases
abstract class Persona {
    // Atributo protegido para el ID
    protected int id;
    // Atributo protegido para el nombre completo
    protected String nombre;
    // Atributo protegido para el correo de contacto
    protected String correo;

    // Constructor sin parámetros
    public Persona() {
        // Inicializamos id en 0 por defecto
        this.id = 0;
        // Asignamos nombre por defecto
        this.nombre = "Sin nombre";
        // Asignamos correo genérico por defecto
        this.correo = "sin@correo.com";
    }

    // Constructor parametrizado para recibir los valores base
    public Persona(int id, String nombre, String correo) {
        // Asignamos el id recibido
        this.id = id;
        // Asignamos el nombre recibido
        this.nombre = nombre;
        // Asignamos el correo recibido
        this.correo = correo;
    }

    // Método abstracto que obliga a redefinir cómo se muestra la información
    public abstract void mostrarInformacion();

    // Getter del ID
    public int getId() { return id; }
    // Setter del ID
    public void setId(int id) { this.id = id; }

    // Getter del Nombre
    public String getNombre() { return nombre; }
    // Setter del Nombre
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Getter del Correo
    public String getCorreo() { return correo; }
    // Setter del Correo
    public void setCorreo(String correo) { this.correo = correo; }
}

// =========================================================================
// 2. CLASE USUARIO (Hereda de Persona)
// =========================================================================
// Clase concreta Usuario que representa a un alumno o cliente de la biblioteca
class Usuario extends Persona {
    // Atributo privado para contar libros prestados activos
    private int librosPrestados;
    // Atributo privado para guardar el teléfono celular
    private String telefonoCelular;
    // Atributo privado para indicar si el usuario está activo o suspendido
    private boolean activo;

    // Constructor sin parámetros
    public Usuario() {
        // Llamada al constructor por defecto de Persona
        super();
        // Inicializamos contador de libros prestados en 0
        this.librosPrestados = 0;
        // Teléfono por defecto
        this.telefonoCelular = "00-00-00-00-00";
        // Activo por defecto
        this.activo = true;
    }

    // Constructor sobrecargado 1
    public Usuario(int id, String nombre, String correo, boolean activo) {
        // Asignamos datos base a super
        super(id, nombre, correo);
        // Inicializamos libros prestados en 0
        this.librosPrestados = 0;
        // Marcamos teléfono no registrado
        this.telefonoCelular = "No registrado";
        // Asignamos estatus
        this.activo = activo;
    }

    // Constructor sobrecargado 2 (Evita colisión de firmas incluyendo correo y celular)
    public Usuario(int id, String nombre, String correo, String telefonoCelular, boolean activo) {
        // Asignamos id, nombre y correo a super
        super(id, nombre, correo);
        // Inicializamos libros prestados en 0
        this.librosPrestados = 0;
        // Asignamos el número celular
        this.telefonoCelular = telefonoCelular;
        // Asignamos estatus de la cuenta
        this.activo = activo;
    }

    // Método para simular la solicitud de un préstamo por el usuario
    public void solicitarPrestamo() {
        // Validamos si la cuenta está activa
        if (activo) {
            // Aumentamos en 1 la cuenta de libros prestados
            librosPrestados++;
            // Imprimimos el resultado en pantalla
            System.out.println("-> " + nombre + " ha solicitado un préstamo. Libros prestados: " + librosPrestados);
        } else {
            // Mensaje de advertencia si la cuenta no está activa
            System.out.println("-> El usuario " + nombre + " no está activo.");
        }
    }

    // Método para simular la devolución de un libro por el usuario
    public void devolverLibro() {
        // Validamos si el usuario tiene préstamos pendientes
        if (librosPrestados > 0) {
            // Reducimos en 1 el contador
            librosPrestados--;
            // Mostramos confirmación
            System.out.println("-> " + nombre + " devolvió un libro. Libros pendientes: " + librosPrestados);
        } else {
            // Avisamos que no debe nada
            System.out.println("-> " + nombre + " no tiene libros pendientes por devolver.");
        }
    }

    // Redefinición del método de la clase Persona
    @Override
    public void mostrarInformacion() {
        // Imprimimos el estado completo del usuario
        System.out.println("[USUARIO] ID: " + id + " | Nombre: " + nombre + " | Correo: " + correo +
                " | Teléfono: " + telefonoCelular + " | Activo: " + activo +
                " | Libros prestados: " + librosPrestados);
    }

    // Getters y Setters
    public int getLibrosPrestados() { return librosPrestados; }
    public String getTelefonoCelular() { return telefonoCelular; }
    public void setTelefonoCelular(String telefonoCelular) { this.telefonoCelular = telefonoCelular; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}

// =========================================================================
// 3. CLASE BIBLIOTECARIO (Hereda de Persona)
// =========================================================================
// Clase que representa a los empleados de la biblioteca
class Bibliotecario extends Persona {
    // Atributo privado para el turno de trabajo
    private String turno;
    // Atributo privado para el salario
    private double salario;

    // Constructor sin parámetros
    public Bibliotecario() {
        // Llamada a Persona()
        super();
        // Turno por defecto
        this.turno = "Matutino";
        // Salario por defecto
        this.salario = 0.0;
    }

    // Constructor parametrizado
    public Bibliotecario(int id, String nombre, String correo, String turno, double salario) {
        // Enviamos datos a la superclase
        super(id, nombre, correo);
        // Asignamos turno laboral
        this.turno = turno;
        // Asignamos salario
        this.salario = salario;
    }

    // Acción para autorizar un préstamo
    public void autorizarPrestamo() {
        // Imprimimos acción
        System.out.println("-> El bibliotecario " + nombre + " ha autorizado un préstamo.");
    }

    // Acción para registrar un libro
    public void registrarLibro() {
        // Imprimimos acción
        System.out.println("-> El bibliotecario " + nombre + " ha registrado un nuevo libro.");
    }

    // Acción para dar de baja un libro
    public void eliminarLibro() {
        // Imprimimos acción
        System.out.println("-> El bibliotecario " + nombre + " ha eliminado un libro.");
    }

    // Implementación del método de visualización
    @Override
    public void mostrarInformacion() {
        // Imprimimos ficha del bibliotecario
        System.out.println("[BIBLIOTECARIO] ID: " + id + " | Nombre: " + nombre + " | Correo: " + correo +
                " | Turno: " + turno + " | Salario: $" + salario);
    }

    // Getters y Setters
    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}

// =========================================================================
// 4. CLASE LIBRO (Implementa la Interfaz Prestable)
// =========================================================================
// Representa un ejemplar disponible en catálogo e implementa Prestable
class Libro implements Prestable {
    // Atributo privado para el ISBN
    private String isbn;
    // Atributo privado para el título
    private String titulo;
    // Atributo privado para el autor
    private String autor;
    // Atributo privado para el año de edición
    private int anioPublicacion;
    // Atributo privado para controlar disponibilidad
    private boolean disponible;

    // Atributo estático para contar el total de libros creados
    private static int totalLibros = 0;

    // Constructor sin parámetros
    public Libro() {
        // ISBN por defecto
        this.isbn = "0000000000";
        // Título por defecto
        this.titulo = "Sin título";
        // Autor por defecto
        this.autor = "Anónimo";
        // Año por defecto
        this.anioPublicacion = 0;
        // Disponible por defecto
        this.disponible = true;
        // Incrementamos contador global static
        totalLibros++;
    }

    // Constructor sobrecargado 1 (Título y Autor)
    public Libro(String titulo, String autor) {
        // ISBN por defecto
        this.isbn = "0000000000";
        // Asignamos título
        this.titulo = titulo;
        // Asignamos autor
        this.autor = autor;
        // Año base 2024
        this.anioPublicacion = 2024;
        // Marcamos disponible
        this.disponible = true;
        // Incrementamos contador global static
        totalLibros++;
    }

    // Constructor sobrecargado 2 (ISBN, Título, Autor y Año)
    public Libro(String isbn, String titulo, String autor, int anioPublicacion) {
        // Asignamos ISBN
        this.isbn = isbn;
        // Asignamos título
        this.titulo = titulo;
        // Asignamos autor
        this.autor = autor;
        // Asignamos año
        this.anioPublicacion = anioPublicacion;
        // Marcamos disponible
        this.disponible = true;
        // Incrementamos contador global static
        totalLibros++;
    }

    // Consulta si el libro está libre
    public boolean estaDisponible() { return disponible; }

    // Implementación del método de la interfaz Prestable
    @Override
    public void prestar() {
        // Si está disponible lo cambiamos a false
        if (disponible) {
            // Cambiamos estado
            disponible = false;
            // Notificamos
            System.out.println("-> El libro '" + titulo + "' ha sido prestado exitosamente.");
        } else {
            // Si ya estaba prestado avisamos
            System.out.println("-> El libro '" + titulo + "' no está disponible.");
        }
    }

    // Implementación del método de la interfaz Prestable
    @Override
    public void devolver() {
        // Marcamos como disponible
        disponible = true;
        // Notificamos devolución
        System.out.println("-> El libro '" + titulo + "' se ha devuelto a la estantería.");
    }

    // Getters y Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public int getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(int anioPublicacion) { this.anioPublicacion = anioPublicacion; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    // Método estático para consultar la variable de clase static
    public static int getTotalLibros() { return totalLibros; }

    // Imprime la ficha del libro
    public void mostrarInformacion() {
        // Mostramos detalles concatenados
        System.out.println("ISBN: " + isbn + " | Título: " + titulo + " | Autor: " + autor +
                " | Año: " + anioPublicacion + " | Disponible: " + disponible);
    }
}

// =========================================================================
// 5. CLASE PRESTAMO (Composición entre Usuario y Libro)
// =========================================================================
// Representa la asociación directa de un préstamo entre un usuario y un libro
class Prestamo {
    // Referencia al objeto Usuario que solicita
    private Usuario usuario;
    // Referencia al objeto Libro solicitado
    private Libro libro;
    // Fecha en la que se realizó el préstamo
    private String fechaPrestamo;
    // Fecha compromiso de devolución
    private String fechaDevolucion;
    // Estatus para saber si el préstamo está activo
    private boolean activo;

    // Constructor principal que recibe las referencias de Usuario y Libro
    public Prestamo(Usuario usuario, Libro libro) {
        // Asignamos el usuario contenido
        this.usuario = usuario;
        // Asignamos el libro contenido
        this.libro = libro;
        // Marcamos una fecha genérica de préstamo
        this.fechaPrestamo = "Hoy";
        // Marcamos fecha compromiso
        this.fechaDevolucion = "En 7 días";
        // Inicializamos activo en falso hasta llamar a iniciarPrestamo()
        this.activo = false;
    }

    // Método para activar el préstamo interactuando directamente entre objetos
    public void iniciarPrestamo() {
        // Verificamos si el libro está disponible en el inventario
        if (libro.estaDisponible() && usuario.isActivo()) {
            // El objeto Prestamo ordena a Libro cambiar a no disponible
            libro.prestar();
            // El objeto Prestamo ordena a Usuario registrar el préstamo
            usuario.solicitarPrestamo();
            // Activamos la bandera del préstamo
            this.activo = true;
            // Confirmamos la transacción
            System.out.println("-> ¡Préstamo iniciado con éxito para " + usuario.getNombre() + "!");
        } else {
            // Notificamos fallo si el libro no estaba libre
            System.out.println("-> No se pudo iniciar el préstamo. Verifique disponibilidad o estatus del usuario.");
        }
    }

    // Método para cerrar la transacción de préstamo
    public void finalizarPrestamo() {
        // Comprobamos que esté activo
        if (activo) {
            // Notificamos al objeto libro que regrese a la estantería
            libro.devolver();
            // Notificamos al objeto usuario que descuente el adeudo
            usuario.devolverLibro();
            // Desactivamos este registro de préstamo
            this.activo = false;
            // Confirmamos fin
            System.out.println("-> ¡Préstamo finalizado exitosamente!");
        } else {
            // Avisamos que la transacción ya estaba cerrada
            System.out.println("-> El préstamo ya se encontraba inactivo.");
        }
    }

    // Consulta el estado del préstamo
    public boolean estaActivo() { return activo; }

    // Getters y Setters
    public Usuario getUsuario() { return usuario; }
    public Libro getLibro() { return libro; }
    public String getFechaPrestamo() { return fechaPrestamo; }
    public String getFechaDevolucion() { return fechaDevolucion; }
}

// =========================================================================
// 6. CLASE BIBLIOTECA (Composición y Gestión con ArrayLists)
// =========================================================================
// Clase principal que administra todas las colecciones y entidades de la biblioteca
class Biblioteca {
    // Lista dinámica de libros almacenados
    private ArrayList<Libro> libros;
    // Lista dinámica de usuarios registrados
    private ArrayList<Usuario> usuarios;
    // Lista dinámica de empleados bibliotecarios
    private ArrayList<Bibliotecario> empleados;
    // Lista dinámica de registros de préstamos
    private ArrayList<Prestamo> prestamos;

    // Constructor que se encarga de instanciar todas las listas vacías
    public Biblioteca() {
        // Instanciamos el ArrayList de libros
        this.libros = new ArrayList<>();
        // Instanciamos el ArrayList de usuarios
        this.usuarios = new ArrayList<>();
        // Instanciamos el ArrayList de empleados
        this.empleados = new ArrayList<>();
        // Instanciamos el ArrayList de préstamos
        this.prestamos = new ArrayList<>();
    }

    // --- MÉTODOS DE GESTIÓN DE LIBROS ---
    // Agrega un objeto Libro a la lista
    public void agregarLibro(Libro libro) {
        // Agregamos al ArrayList
        libros.add(libro);
        // Notificamos en consola
        System.out.println("-> Libro '" + libro.getTitulo() + "' agregado a la biblioteca.");
    }

    // Elimina un libro buscando su ISBN
    public void eliminarLibro(String isbn) {
        // Obtenemos la referencia del libro
        Libro buscado = buscarLibro(isbn);
        // Si existe en el catálogo lo removemos
        if (buscado != null) {
            // Removemos de la lista
            libros.remove(buscado);
            // Confirmamos
            System.out.println("-> Libro con ISBN " + isbn + " eliminado de la biblioteca.");
        } else {
            // Notificamos si no existía
            System.out.println("-> No se encontró ningún libro con el ISBN especificado.");
        }
    }

    // Método de búsqueda de libro por ISBN
    public Libro buscarLibro(String isbn) {
        // Recorremos la lista de libros con un for-each
        for (Libro l : libros) {
            // Si el ISBN coincide ignorando mayúsculas/minúsculas devolvemos la instancia
            if (l.getIsbn().equalsIgnoreCase(isbn)) {
                return l;
            }
        }
        // Retornamos null si no se encuentra
        return null;
    }

    // SOBRECARGA DE MÉTODOS DE BÚSQUEDA (Punto 9 de la Práctica)
    // Busca un libro comparando Título y Autor
    public Libro buscarLibro(String titulo, String autor) {
        // Recorremos con bucle for-each
        for (Libro l : libros) {
            // Comprobamos coincidencia de título y autor
            if (l.getTitulo().equalsIgnoreCase(titulo) && l.getAutor().equalsIgnoreCase(autor)) {
                return l;
            }
        }
        // Retornamos null si no hay coincidencia
        return null;
    }

    // Muestra todos los libros del catálogo
    public void mostrarCatalogo() {
        System.out.println("\n--- CATÁLOGO DE LIBROS ---");
        // Verificamos si la lista está vacía
        if (libros.isEmpty()) {
            System.out.println("El catálogo está vacío.");
        } else {
            // Recorremos e imprimimos la información de cada objeto Libro
            for (Libro l : libros) {
                l.mostrarInformacion();
            }
        }
    }

    // --- MÉTODOS DE GESTIÓN DE USUARIOS ---
    // Registra un nuevo usuario en la lista
    public void registrarUsuario(Usuario usuario) {
        // Agregamos al ArrayList de usuarios
        usuarios.add(usuario);
        // Confirmamos
        System.out.println("-> Usuario '" + usuario.getNombre() + "' registrado en la biblioteca.");
    }

    // Busca un usuario mediante su ID
    public Usuario buscarUsuario(int id) {
        // Recorremos la lista de usuarios
        for (Usuario u : usuarios) {
            // Si el id coincide devolvemos el objeto
            if (u.getId() == id) {
                return u;
            }
        }
        // Retornamos null si no lo encuentra
        return null;
    }

    // Despliega la lista completa de usuarios
    public void mostrarUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS REGISTRADOS ---");
        // Validamos si la lista tiene elementos
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            // Recorremos e invocamos mostrarInformacion() en cada usuario
            for (Usuario u : usuarios) {
                u.mostrarInformacion();
            }
        }
    }

    // Permite registrar empleados bibliotecarios
    public void registrarEmpleado(Bibliotecario empleado) {
        // Agregamos al ArrayList de empleados
        empleados.add(empleado);
        // Notificamos registro
        System.out.println("-> Bibliotecario '" + empleado.getNombre() + "' registrado en el sistema.");
    }

    // --- MÉTODOS DE GESTIÓN DE PRÉSTAMOS ---
    // Genera y realiza la transacción de un préstamo
    public void realizarPrestamo(Usuario usuario, Libro libro) {
        // Validamos que ambas referencias sean válidas
        if (usuario != null && libro != null) {
            // Instanciamos un nuevo objeto Prestamo
            Prestamo nuevoPrestamo = new Prestamo(usuario, libro);
            // Iniciamos la colaboración entre objetos
            nuevoPrestamo.iniciarPrestamo();
            // Si la transacción se activó, la guardamos en la lista global
            if (nuevoPrestamo.estaActivo()) {
                prestamos.add(nuevoPrestamo);
            }
        } else {
            // Notificamos datos inválidos
            System.out.println("-> No se puede realizar el préstamo: Usuario o Libro no encontrado.");
        }
    }

    // Procesa la devolución de un préstamo existente
    public void devolverLibro(Prestamo prestamo) {
        // Validamos que el préstamo exista
        if (prestamo != null && prestamo.estaActivo()) {
            // Ejecutamos el cierre del préstamo
            prestamo.finalizarPrestamo();
        } else {
            // Avisamos si no es válido
            System.out.println("-> Registro de préstamo no válido o ya cerrado.");
        }
    }

    // Muestra todos los préstamos históricos/activos
    public void mostrarPrestamos() {
        System.out.println("\n--- LISTA DE PRÉSTAMOS ---");
        // Revisamos si existen préstamos registrados
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
        } else {
            // Recorremos e imprimimos detalle de cada préstamo
            for (int i = 0; i < prestamos.size(); i++) {
                // Tomamos cada elemento por índice
                Prestamo p = prestamos.get(i);
                // Imprimimos la información clave concatenada
                System.out.println("[" + (i + 1) + "] Usuario: " + p.getUsuario().getNombre() +
                        " | Libro: " + p.getLibro().getTitulo() +
                        " | Activo: " + p.estaActivo());
            }
        }
    }

    // Getter para acceder a la lista de préstamos desde el menú
    public ArrayList<Prestamo> getPrestamos() { return prestamos; }
}

// =========================================================================
// 7. CLASE PRINCIPAL MAIN (Menú Interactivo y Ejecución de Casos de Prueba)
// =========================================================================
public class Main {
    // Método principal ejecutable main
    public static void main(String[] args) {
        // Inicializamos Scanner para captura de datos por consola
        Scanner sc = new Scanner(System.in);
        // Instanciamos el objeto principal Biblioteca que controlará el sistema completo
        Biblioteca miBiblioteca = new Biblioteca();

        // Variable para capturar la opción seleccionada por el usuario
        int opcion = 0;

        // Bucle interactivo principal
        do {
            // Desplegamos las opciones del menú completo
            System.out.println("\n==============================================");
            System.out.println("  SISTEMA DE GESTIÓN DE BIBLIOTECA ");
            System.out.println("==============================================");
            System.out.println("1. Registrar un Libro en la Biblioteca");
            System.out.println("2. Registrar un Usuario en la Biblioteca");
            System.out.println("3. Registrar un Bibliotecario en el Sistema");
            System.out.println("4. Mostrar Catálogo de Libros");
            System.out.println("5. Mostrar Usuarios Registrados");
            System.out.println("6. Realizar Préstamo de Libro");
            System.out.println("7. Devolver Libro Prestado");
            System.out.println("8. Mostrar Lista de Préstamos");
            System.out.println("9. Buscar Libro por ISBN o (Título y Autor)");
            System.out.println("10. Ejecutar Casos de Prueba ");
            System.out.println("11. Ver Total de Libros creados ");
            System.out.println("12. Salir");
            System.out.print("Selecciona una opción: ");

            // Validamos que se ingrese un entero válido
            if (sc.hasNextInt()) {
                // Leemos la opción elegida
                opcion = sc.nextInt();
                // Limpiamos el buffer del Scanner
                sc.nextLine();
            } else {
                // Advertimos sobre entrada inválida
                System.out.println("Por favor ingresa un número entero.");
                // Limpiamos buffer de entrada
                sc.nextLine();
                // Saltamos a la siguiente interacción del ciclo
                continue;
            }

            // Evaluamos la opción seleccionada mediante un switch
            switch (opcion) {
                // Registro interactivo de un nuevo Libro
                case 1:
                    System.out.println("\n--- REGISTRAR NUEVO LIBRO ---");
                    System.out.print("Ingresa ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Ingresa Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Ingresa Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Ingresa Año de Publicación: ");
                    int anio = sc.nextInt();
                    sc.nextLine();

                    // Instanciamos el objeto Libro y lo agregamos a la biblioteca
                    Libro nuevoLibro = new Libro(isbn, titulo, autor, anio);
                    miBiblioteca.agregarLibro(nuevoLibro);
                    break;

                // Registro interactivo de un nuevo Usuario
                case 2:
                    System.out.println("\n--- REGISTRAR NUEVO USUARIO ---");
                    System.out.print("Ingresa ID del Usuario: ");
                    int idU = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ingresa Nombre: ");
                    String nomU = sc.nextLine();
                    System.out.print("Ingresa Correo: ");
                    String correoU = sc.nextLine();
                    System.out.print("Ingresa Celular: ");
                    String telU = sc.nextLine();

                    // Instanciamos el objeto Usuario y lo agregamos a la biblioteca
                    Usuario nuevoUsuario = new Usuario(idU, nomU, correoU, telU, true);
                    miBiblioteca.registrarUsuario(nuevoUsuario);
                    break;

                // Registro interactivo de un Bibliotecario
                case 3:
                    System.out.println("\n--- REGISTRAR BIBLIOTECARIO ---");
                    System.out.print("Ingresa ID: ");
                    int idB = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ingresa Nombre: ");
                    String nomB = sc.nextLine();
                    System.out.print("Ingresa Correo: ");
                    String correoB = sc.nextLine();
                    System.out.print("Ingresa Turno (Matutino/Vespertino): ");
                    String turnoB = sc.nextLine();
                    System.out.print("Ingresa Salario: ");
                    double salarioB = sc.nextDouble();
                    sc.nextLine();

                    // Instanciamos el bibliotecario y lo registramos en el sistema
                    Bibliotecario nuevoEmpleado = new Bibliotecario(idB, nomB, correoB, turnoB, salarioB);
                    miBiblioteca.registrarEmpleado(nuevoEmpleado);
                    break;

                // Consulta de la lista de libros
                case 4:
                    miBiblioteca.mostrarCatalogo();
                    break;

                // Consulta de la lista de usuarios
                case 5:
                    miBiblioteca.mostrarUsuarios();
                    break;

                // Realizar un préstamo dinámico
                case 6:
                    System.out.println("\n--- REALIZAR PRÉSTAMO ---");
                    System.out.print("Ingresa el ID del Usuario: ");
                    int idBusqueda = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ingresa el ISBN del Libro: ");
                    String isbnBusqueda = sc.nextLine();

                    // Buscamos las referencias de los objetos en las listas de la biblioteca
                    Usuario uPrestamo = miBiblioteca.buscarUsuario(idBusqueda);
                    Libro lPrestamo = miBiblioteca.buscarLibro(isbnBusqueda);

                    // Ejecutamos el préstamo en la biblioteca
                    miBiblioteca.realizarPrestamo(uPrestamo, lPrestamo);
                    break;

                // Devolver un libro dinámicamente
                case 7:
                    System.out.println("\n--- DEVOLVER LIBRO ---");
                    // Mostramos la lista de préstamos activos
                    miBiblioteca.mostrarPrestamos();
                    // Si hay préstamos en la lista, solicitamos el número
                    if (!miBiblioteca.getPrestamos().isEmpty()) {
                        System.out.print("Ingresa el número de préstamo a devolver: ");
                        int numP = sc.nextInt();
                        sc.nextLine();
                        // Ajustamos a índice base 0 y procesamos la devolución
                        if (numP > 0 && numP <= miBiblioteca.getPrestamos().size()) {
                            Prestamo pDevolucion = miBiblioteca.getPrestamos().get(numP - 1);
                            miBiblioteca.devolverLibro(pDevolucion);
                        } else {
                            System.out.println("Número de préstamo inválido.");
                        }
                    }
                    break;

                // Consulta la lista de todos los préstamos en el sistema
                case 8:
                    miBiblioteca.mostrarPrestamos();
                    break;

                // Probar la búsqueda sobrecargada de libros
                case 9:
                    System.out.println("\n--- BÚSQUEDA SOBRECARGADA DE LIBROS ---");
                    System.out.println("1. Buscar por ISBN");
                    System.out.println("2. Buscar por Título y Autor");
                    System.out.print("Elige una opción: ");
                    int opBusqueda = sc.nextInt();
                    sc.nextLine();

                    if (opBusqueda == 1) {
                        System.out.print("Ingresa ISBN: ");
                        String busqIsbn = sc.nextLine();
                        Libro resultado = miBiblioteca.buscarLibro(busqIsbn);
                        if (resultado != null) resultado.mostrarInformacion();
                        else System.out.println("Libro no encontrado.");
                    } else if (opBusqueda == 2) {
                        System.out.print("Ingresa Título: ");
                        String busqTitulo = sc.nextLine();
                        System.out.print("Ingresa Autor: ");
                        String busqAutor = sc.nextLine();
                        // Ejecución de la sobrecarga de búsqueda en Biblioteca
                        Libro resultado = miBiblioteca.buscarLibro(busqTitulo, busqAutor);
                        if (resultado != null) resultado.mostrarInformacion();
                        else System.out.println("Libro no encontrado.");
                    } else {
                        System.out.println("Opción inválida.");
                    }
                    break;

                // Ejecución automática de los Casos de Prueba estipulados en las Secciones 11.1 a 11.4
                case 10:
                    System.out.println("\n================================================");
                    System.out.println(" EJECUTANDO CASOS DE PRUEBA DE LA PRACTICA");
                    System.out.println("================================================");

                    // Caso 1: Registro de 3 Libros
                    System.out.println("\n>>> CASO 1: Registro de Libros");
                    Libro lib1 = new Libro("111", "Java Básico", "Herbert Schildt", 2018);
                    Libro lib2 = new Libro("222", "Clean Code", "Robert C. Martin", 2008);
                    Libro lib3 = new Libro("333", "Patrones de Diseño", "Erich Gamma", 1994);
                    miBiblioteca.agregarLibro(lib1);
                    miBiblioteca.agregarLibro(lib2);
                    miBiblioteca.agregarLibro(lib3);

                    // Caso 2: Registro de 2 Usuarios
                    System.out.println("\n>>> CASO 2: Registro de Usuarios");
                    Usuario us1 = new Usuario(1, "Juan Pérez", "juan@mail.com", true);
                    Usuario us2 = new Usuario(2, "María Gómez", "maria@mail.com", true);
                    miBiblioteca.registrarUsuario(us1);
                    miBiblioteca.registrarUsuario(us2);

                    // Caso 3: Solicitud de Préstamo
                    System.out.println("\n>>> CASO 3: Solicitud de Préstamo (Juan solicita 'Clean Code')");
                    System.out.println("Estado inicial 'Clean Code' disponible = " + lib2.estaDisponible());
                    miBiblioteca.realizarPrestamo(us1, lib2);
                    System.out.println("Resultado esperado -> Libro disponible: " + lib2.estaDisponible());

                    // Caso 4: Devolución de Libro
                    System.out.println("\n>>> CASO 4: Devolución de Libro (Juan devuelve 'Clean Code')");
                    if (!miBiblioteca.getPrestamos().isEmpty()) {
                        Prestamo pPrueba = miBiblioteca.getPrestamos().get(miBiblioteca.getPrestamos().size() - 1);
                        miBiblioteca.devolverLibro(pPrueba);
                    }
                    System.out.println("Resultado esperado -> Libro disponible: " + lib2.estaDisponible());
                    break;

                // Consultar el total de la variable estática static
                case 11:
                    System.out.println("\nTotal de libros creados en memoria (Atributo Static): " + Libro.getTotalLibros());
                    break;

                // Salida del programa
                case 12:
                    System.out.println("Saliendo del programa de gestión...");
                    break;

                // Captura de opciones inválidas
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
            // Condición de permanencia en la ejecución
        } while (opcion != 12);

        // Cerramos el objeto Scanner para liberar la consola
        sc.close();
    }
}