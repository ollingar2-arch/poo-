//Garcia Garcia Santiago, avance del codigo(practica4-8)
import java.util.Scanner;

// ==========================================
// 1. CLASE ABSTRACTA PERSONA
// ==========================================
abstract class Persona {
    protected int id;
    protected String nombre;
    protected String correo;

    public Persona() {
        this.id = 0;
        this.nombre = "Sin nombre";
        this.correo = "sin@correo.com";
    }

    public Persona(int id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    public abstract void mostrarInformacion();

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}

// ==========================================
// 2. CLASE USUARIO
// ==========================================
class Usuario extends Persona {
    private int librosPrestados;
    private String telefonoCelular;
    private boolean activo;

    public Usuario() {
        super();
        this.librosPrestados = 0;
        this.telefonoCelular = "00-00-00-00-00";
        this.activo = true;
    }

    public Usuario(int id, String nombre, String correo, boolean activo) {
        super(id, nombre, correo);
        this.librosPrestados = 0;
        this.telefonoCelular = "No registrado";
        this.activo = activo;
    }

    public Usuario(int id, String nombre, String correo, String telefonoCelular, boolean activo) {
        super(id, nombre, correo);
        this.librosPrestados = 0;
        this.telefonoCelular = telefonoCelular;
        this.activo = activo;
    }

    public void solicitarPrestamo() {
        if (activo) {
            librosPrestados++;
            System.out.println("-> El usuario " + nombre + " ha solicitado un préstamo. Libros prestados actualmente: " + librosPrestados);
        } else {
            System.out.println("-> El usuario " + nombre + " no está activo.");
        }
    }

    public void devolverLibro() {
        if (librosPrestados > 0) {
            librosPrestados--;
            System.out.println("-> El usuario " + nombre + " devolvió un libro. Libros pendientes: " + librosPrestados);
        } else {
            System.out.println("-> El usuario " + nombre + " no tiene libros pendientes por devolver.");
        }
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("[USUARIO] ID: " + id + " | Nombre: " + nombre + " | Correo: " + correo +
                " | Teléfono: " + telefonoCelular + " | Activo: " + activo +
                " | Libros prestados: " + librosPrestados);
    }

    public int getLibrosPrestados() { return librosPrestados; }
    public String getTelefonoCelular() { return telefonoCelular; }
    public void setTelefonoCelular(String telefonoCelular) { this.telefonoCelular = telefonoCelular; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}

// ==========================================
// 3. CLASE BIBLIOTECARIO
// ==========================================
class Bibliotecario extends Persona {
    private String turno;
    private double salario;

    public Bibliotecario() {
        super();
        this.turno = "Matutino";
        this.salario = 0.0;
    }

    public Bibliotecario(int id, String nombre, String correo, String turno, double salario) {
        super(id, nombre, correo);
        this.turno = turno;
        this.salario = salario;
    }

    public void autorizarPrestamo() {
        System.out.println("-> El bibliotecario " + nombre + " ha autorizado un préstamo.");
    }

    public void registrarLibro() {
        System.out.println("-> El bibliotecario " + nombre + " registró un nuevo libro en el sistema.");
    }

    public void eliminarLibro() {
        System.out.println("-> El bibliotecario " + nombre + " eliminó un libro del sistema.");
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("[BIBLIOTECARIO] ID: " + id + " | Nombre: " + nombre + " | Correo: " + correo +
                " | Turno: " + turno + " | Salario: $" + salario);
    }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}

// ==========================================
// 4. CLASE LIBRO
// ==========================================
class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private boolean disponible;

    private static int totalLibros = 0;

    public Libro() {
        this.isbn = "0000000000";
        this.titulo = "Sin título";
        this.autor = "Anónimo";
        this.anioPublicacion = 0;
        this.disponible = true;
        totalLibros++;
    }

    public Libro(String titulo, String autor) {
        this.isbn = "0000000000";
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = 2024;
        this.disponible = true;
        totalLibros++;
    }

    public Libro(String isbn, String titulo, String autor, int anioPublicacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;
        totalLibros++;
    }

    public boolean estaDisponible() { return disponible; }

    public void prestar() {
        if (disponible) {
            disponible = false;
            System.out.println("-> El libro '" + titulo + "' ha sido prestado exitosamente.");
        } else {
            System.out.println("-> El libro '" + titulo + "' ya se encuentra prestado actualmente.");
        }
    }

    public void devolver() {
        disponible = true;
        System.out.println("-> El libro '" + titulo + "' ha sido marcado como disponible.");
    }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public int getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(int anioPublicacion) { this.anioPublicacion = anioPublicacion; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public static int getTotalLibros() { return totalLibros; }

    public void mostrarInformacion() {
        System.out.println("ISBN: " + isbn + " | Título: " + titulo + " | Autor: " + autor +
                " | Año: " + anioPublicacion + " | Disponible: " + disponible);
    }
}

// ==========================================
// 5. CLASE PRINCIPAL CON MENÚ INTERACTIVO
// ==========================================
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Libro miLibro = null;
        Usuario miUsuario = null;
        Bibliotecario miBibliotecario = null;

        int opcion = 0;

        do {
            System.out.println("\n==============================================");
            System.out.println("    SISTEMA DE GESTIÓN DE BIBLIOTECA - AVANCE");
            System.out.println("==============================================");
            System.out.println("1. Registrar un Libro");
            System.out.println("2. Registrar un Usuario");
            System.out.println("3. Registrar un Bibliotecario");
            System.out.println("4. Mostrar información general");
            System.out.println("5. Probar acciones de Usuario (Solicitar/Devolver)");
            System.out.println("6. Probar acciones de Libro (Prestar/Devolver)");
            System.out.println("7. Probar acciones de Bibliotecario");
            System.out.println("8. Ver Total de Libros Creados (Atributo Static)");
            System.out.println("9. Salir");
            System.out.print("Selecciona una opción: ");

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Por favor ingresa un número válido.");
                sc.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRO DE LIBRO ---");
                    System.out.print("Ingresa el ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Ingresa el Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Ingresa el Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Ingresa el Año de Publicación: ");
                    int anio = sc.nextInt();
                    sc.nextLine();

                    miLibro = new Libro(isbn, titulo, autor, anio);
                    System.out.println("¡Libro registrado correctamente!");
                    break;

                case 2:
                    System.out.println("\n--- REGISTRO DE USUARIO ---");
                    System.out.print("Ingresa el ID del usuario: ");
                    int idU = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ingresa el Nombre: ");
                    String nomU = sc.nextLine();
                    System.out.print("Ingresa el Correo: ");
                    String correoU = sc.nextLine();
                    System.out.print("Ingresa el Teléfono Celular: ");
                    String telU = sc.nextLine();

                    miUsuario = new Usuario(idU, nomU, correoU, telU, true);
                    System.out.println("¡Usuario registrado correctamente!");
                    break;

                case 3:
                    System.out.println("\n--- REGISTRO DE BIBLIOTECARIO ---");
                    System.out.print("Ingresa el ID del bibliotecario: ");
                    int idB = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ingresa el Nombre: ");
                    String nomB = sc.nextLine();
                    System.out.print("Ingresa el Correo: ");
                    String correoB = sc.nextLine();
                    System.out.print("Ingresa el Turno (Matutino/Vespertino): ");
                    String turnoB = sc.nextLine();
                    System.out.print("Ingresa el Salario: ");
                    double salarioB = sc.nextDouble();
                    sc.nextLine();

                    miBibliotecario = new Bibliotecario(idB, nomB, correoB, turnoB, salarioB);
                    System.out.println("¡Bibliotecario registrado correctamente!");
                    break;

                case 4:
                    System.out.println("\n--- INFORMACIÓN DE ENTIDADES ---");
                    if (miLibro != null) miLibro.mostrarInformacion();
                    else System.out.println("No hay ningún libro registrado.");

                    if (miUsuario != null) miUsuario.mostrarInformacion();
                    else System.out.println("No hay ningún usuario registrado.");

                    if (miBibliotecario != null) miBibliotecario.mostrarInformacion();
                    else System.out.println("No hay ningún bibliotecario registrado.");
                    break;

                case 5:
                    if (miUsuario == null) {
                        System.out.println("Primero debes registrar un usuario (Opción 2).");
                    } else {
                        System.out.println("\n1. Solicitar Préstamo");
                        System.out.println("2. Devolver Libro");
                        System.out.print("Elige una opción: ");
                        int opU = sc.nextInt();
                        sc.nextLine();
                        if (opU == 1) miUsuario.solicitarPrestamo();
                        else if (opU == 2) miUsuario.devolverLibro();
                        else System.out.println("Opción inválida.");
                    }
                    break;

                case 6:
                    if (miLibro == null) {
                        System.out.println("Primero debes registrar un libro (Opción 1).");
                    } else {
                        System.out.println("\n1. Prestar Libro");
                        System.out.println("2. Devolver Libro");
                        System.out.print("Elige una opción: ");
                        int opL = sc.nextInt();
                        sc.nextLine();
                        if (opL == 1) miLibro.prestar();
                        else if (opL == 2) miLibro.devolver();
                        else System.out.println("Opción inválida.");
                    }
                    break;

                case 7:
                    if (miBibliotecario == null) {
                        System.out.println("Primero debes registrar un bibliotecario (Opción 3).");
                    } else {
                        System.out.println("\n1. Registrar Libro en sistema");
                        System.out.println("2. Autorizar Préstamo");
                        System.out.println("3. Eliminar Libro de sistema");
                        System.out.print("Elige una opción: ");
                        int opB = sc.nextInt();
                        sc.nextLine();
                        if (opB == 1) miBibliotecario.registrarLibro();
                        else if (opB == 2) miBibliotecario.autorizarPrestamo();
                        else if (opB == 3) miBibliotecario.eliminarLibro();
                        else System.out.println("Opción inválida.");
                    }
                    break;

                case 8:
                    System.out.println("\nTotal de objetos 'Libro' instanciados: " + Libro.getTotalLibros());
                    break;

                case 9:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 9);

        sc.close();
    }
}