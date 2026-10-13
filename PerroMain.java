// Importamos la clase Scanner para poder leer los datos que el usuario escriba por la consola
import java.util.Scanner;

// Definimos la clase padre (superclase) llamada Animal
class Animal {
    // Declaramos un atributo protegido para el nombre (accesible en clases hijas)
    protected String nombre;
    // Declaramos un atributo protegido para la edad del animal
    protected int edad;

    // Constructor de la clase padre que recibe nombre y edad para inicializar los atributos
    public Animal(String nombre, int edad) {
        // Asignamos el parámetro recibido al atributo de la clase
        this.nombre = nombre;
        // Asignamos el parámetro edad al atributo de la clase
        this.edad = edad;
    }

    // Método de la clase padre que imprime un sonido genérico
    public void hacerSonido() {
        // Imprime un mensaje indicando que el animal hace un sonido genérico
        System.out.println(nombre + " hace un sonido propio del animal.");
    }

    // Primer método para la SOBRECARGA: comer sin parámetros
    public void comer() {
        // Imprime un mensaje estándar de que el animal está comiendo
        System.out.println(nombre + " está comiendo su alimento.");
    }

    // Segundo método para la SOBRECARGA: mismo nombre "comer" pero recibe un parámetro (tipo de comida)
    public void comer(String tipoComida) {
        // Imprime un mensaje personalizado usando el parámetro recibido
        System.out.println(nombre + " está comiendo " + tipoComida + ".");
    }
}

// Definimos la clase hija (subclase) Perro que hereda de Animal usando la palabra reservada "extends"
class Perro extends Animal {
    // Atributo privado exclusivo de la clase Perro para la raza
    private String raza;
    // Atributo privado exclusivo de la clase Perro para el nombre del dueño
    private String dueno;

    // Constructor de la clase Perro que recibe los datos necesarios tanto para el padre como para el hijo
    public Perro(String nombre, int edad, String raza, String dueno) {
        // 1. HERENCIA CON 'SUPER': Llamamos al constructor de la clase padre (Animal) pasándole nombre y edad
        super(nombre, edad);
        // Inicializamos el atributo exclusivo raza
        this.raza = raza;
        // Inicializamos el atributo exclusivo dueno
        this.dueno = dueno;
    }

    // 2. HERENCIA SIMPLE (sin super ni override): método que utiliza directamente la variable "edad" heredada del padre
    public void mostrarFichaTecnica() {
        // Muestra una cadena utilizando atributos propios (raza, dueno) y heredados (edad)
        System.out.println("El perro es de raza " + raza + ", tiene " + edad + " años y su dueño es " + dueno + ".");
    }

    // 3. HERENCIA CON OVERRIDE: Anotación que indica que vamos a sobrescribir un método de la clase padre
    @Override
    public void hacerSonido() {
        // Usamos super para invocar primero el método original que está en la clase Animal
        super.hacerSonido();
        // Agregamos comportamiento adicional específico para el perro (el ladrido)
        System.out.println("¡" + nombre + " ladra: ¡Guau guau!");
    }
}

// Clase principal del programa donde se ejecuta la lógica y la interacción con el usuario
public class PerroMain {
    // Método principal main, punto de entrada de la aplicación en Java
    public static void main(String[] args) {
        // Creamos una instancia de Scanner vinculada a la entrada estándar (consola)
        Scanner scanner = new Scanner(System.in);

        // Imprimimos un encabezado en la consola
        System.out.println("=== REGISTRO DE PERRO ===");

        // Solicitamos al usuario que escriba el nombre del perro
        System.out.print("Ingresa el nombre del perro: ");
        // Leemos la línea completa introducida por el usuario y la guardamos en la variable nombre
        String nombre = scanner.nextLine();

        // Solicitamos la edad del perro
        System.out.print("Ingresa el edad del perro: ");
        // Leemos un número entero y lo guardamos en la variable edad
        int edad = scanner.nextInt();
        // Consumimos el salto de línea pendiente (buffer) para evitar errores con los siguientes String
        scanner.nextLine();

        // Solicitamos la raza del perro
        System.out.print("Ingresa la raza del perro: ");
        // Leemos la línea de texto correspondiente a la raza
        String raza = scanner.nextLine();

        // Solicitamos el nombre del dueño
        System.out.print("Ingresa el nombre del dueño: ");
        // Leemos la línea de texto correspondiente al dueño
        String dueno = scanner.nextLine();

        // Instanciamos el objeto miPerro utilizando los datos proporcionados dinámicamente por el usuario
        Perro miPerro = new Perro(nombre, edad, raza, dueno);

        // Imprimimos una línea divisoria para mostrar los resultados
        System.out.println("\n--- RESULTADOS DE LAS PRUEBAS ---");

        // Probamos la Sobrecarga llamando primero al método comer sin argumentos
        System.out.println("\n1. Pruebas de Sobrecarga:");
        miPerro.comer();
        // Llamamos al método comer pasándole un argumento de tipo String (demostrando sobrecarga)
        miPerro.comer("galletas de premio");

        // Probamos la Herencia simple llamando al método que usa atributos heredados sin usar super ni override
        System.out.println("\n2. Prueba de Herencia Simple (sin super ni override):");
        miPerro.mostrarFichaTecnica();

        // Probamos la Herencia con Override ejecutando el método que sobrescribe el comportamiento del padre
        System.out.println("\n3. Prueba de Herencia con Override y super:");
        miPerro.hacerSonido();

        // Cerramos el objeto scanner para liberar los recursos del sistema
        scanner.close();
    }
}
