import java.util.Scanner; // Importo Scanner para leer los datos que escriba el usuario en consola

public class CuentaRegresivaHilo extends Thread { // Creo la clase heredando de Thread para que cada objeto sea un hilo
    private int inicio; // Guardo el número desde donde empieza la cuenta regresiva
    private int pausaMs; // Guardo cuántos milisegundos va a esperar entre cada número

    public CuentaRegresivaHilo(String nombre, int inicio, int pausaMs) { // Constructor para inicializar el hilo y sus datos
        super(nombre); // Le mando el nombre al constructor de Thread para identificar este hilo
        this.inicio = inicio; // Asigno el valor inicial recibido al atributo de la clase
        this.pausaMs = pausaMs; // Asigno la pausa en milisegundos al atributo de la clase
    }

    @Override // Indico que estoy sobrescribiendo el método run() de la clase Thread
    public void run() { // Aquí va lo que se ejecuta cuando arranca el hilo
        for (int i = inicio; i >= 1; i--) { // Recorro desde el número inicial hasta 1, restando de uno en uno
            System.out.println("[" + getName() + "] Restante: " + i); // Imprimo el nombre del hilo y el valor actual
            try { // Uso try-catch porque Thread.sleep puede lanzar InterruptedException
                Thread.sleep(pausaMs); // Pauso este hilo un rato para notar mejor cómo se intercalan los hilos
            } catch (InterruptedException e) { // Si interrumpen el hilo mientras duerme, entro aquí
                System.out.println("[" + getName() + "] Interrumpido."); // Aviso que este hilo se interrumpió
                return; // Salgo del método run() para terminar el hilo de inmediato
            }
        } // Termina el ciclo de la cuenta regresiva
        System.out.println(">>> [" + getName() + "] Finalizado."); // Muestro un mensaje cuando este hilo ya acabó su conteo
    }

    public static void main(String[] args) { // Método principal donde arranca el programa
        Scanner sc = new Scanner(System.in); // Creo el objeto Scanner para leer entradas del teclado

        System.out.print("¿Cuántos hilos de cuenta regresiva deseas iniciar?: "); // Pido cuántos hilos se van a crear
        int numHilos = sc.nextInt(); // Leo el número de hilos que escribió el usuario
        sc.nextLine(); // Limpio el salto de línea pendiente para que no falle el siguiente nextLine()

        CuentaRegresivaHilo[] hilos = new CuentaRegresivaHilo[numHilos]; // Creo un arreglo para guardar todos los hilos

        for (int i = 0; i < numHilos; i++) { // Ciclo para pedir los datos de cada hilo uno por uno
            System.out.print("Nombre del hilo " + (i + 1) + ": "); // Pido el nombre del hilo actual
            String nombre = sc.nextLine(); // Leo el nombre ingresado por el usuario

            System.out.print("Número inicial para '" + nombre + "': "); // Pido desde qué número empieza este hilo
            int inicio = sc.nextInt(); // Leo el número inicial como entero

            System.out.print("Pausa entre pasos en ms para '" + nombre + "': "); // Pido la pausa en milisegundos
            int pausa = sc.nextInt(); // Leo el tiempo de pausa como entero
            sc.nextLine(); // Limpio el salto de línea que deja nextInt()

            hilos[i] = new CuentaRegresivaHilo(nombre, inicio, pausa); // Instancio el hilo y lo guardo en el arreglo
        } // Fin del ciclo de captura de datos

        for (CuentaRegresivaHilo h : hilos) { // Recorro el arreglo de hilos ya creados
            h.start(); // Lanzo cada hilo (llama a run() en un hilo nuevo, no directamente)
        }

        for (CuentaRegresivaHilo h : hilos) { // Recorro de nuevo el arreglo de hilos
            try { // Uso try-catch porque join() puede lanzar InterruptedException
                h.join(); // Hago que el hilo main espere a que termine el hilo h antes de seguir
            } catch (InterruptedException e) { // Capturo si ocurre una interrupción mientras se espera
                System.out.println("Error esperando a " + h.getName()); // Imprimo qué hilo dio problema al esperar
            }
        }

        System.out.println("Todos los hilos de cuenta regresiva han terminado."); // Mensaje final cuando todos los hilos acabaron
        sc.close(); // Cierro el Scanner para liberar el recurso
    }
}