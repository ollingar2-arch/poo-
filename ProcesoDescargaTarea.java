import java.util.Scanner; // Importo Scanner para leer los datos que escriba el usuario en consola

public class ProcesoDescargaTarea implements Runnable { // Implemento Runnable para definir una tarea ejecutable por un hilo
    private String nombreTarea; // Guardo el nombre de la descarga o tarea a simular
    private int bloques; // Guardo en cuántos bloques se dividirá la descarga
    private int mbPorBloque; // Guardo cuántos megabytes representa cada bloque

    public ProcesoDescargaTarea(String nombreTarea, int bloques, int mbPorBloque) { // Constructor para inicializar los datos de la tarea
        this.nombreTarea = nombreTarea; // Asigno el nombre de la tarea al atributo de la clase
        this.bloques = bloques; // Asigno la cantidad de bloques al atributo de la clase
        this.mbPorBloque = mbPorBloque; // Asigno el tamaño en MB por bloque al atributo de la clase
    }

    @Override // Indico que estoy implementando el método run() de la interfaz Runnable
    public void run() { // Código que se ejecuta cuando el objeto Thread inicia esta tarea
        String hiloActual = Thread.currentThread().getName(); // Obtengo el nombre del hilo que está ejecutando este Runnable
        int totalMb = bloques * mbPorBloque; // Calculo el tamaño total en MB de la descarga

        for (int b = 1; b <= bloques; b++) { // Recorro cada bloque desde el 1 hasta el total de bloques
            int descargado = b * mbPorBloque; // Calculo cuántos MB van descargados hasta este bloque
            int porcentaje = (b * 100) / bloques; // Calculo el porcentaje de avance de la descarga
            System.out.println("(" + hiloActual + ") Tarea '" + nombreTarea + // Muestro qué hilo trabaja y en qué tarea
                    "': " + descargado + "/" + totalMb + " MB (" + porcentaje + "%)"); // Imprimo el progreso en MB y porcentaje
            try { // Uso try-catch porque Thread.sleep puede lanzar InterruptedException
                Thread.sleep(120); // Pauso 120 ms entre bloques para simular descarga y ver la concurrencia
            } catch (InterruptedException e) { // Si el hilo se interrumpe mientras duerme, entro aquí
                System.out.println("(" + hiloActual + ") Tarea '" + nombreTarea + "' interrumpida."); // Aviso que la tarea se interrumpió
                return; // Salgo del método run() para detener esta tarea
            }
        } // Termina el ciclo de descarga por bloques
        System.out.println("--> Tarea '" + nombreTarea + "' completada en " + hiloActual + "."); // Indico que la tarea terminó y qué hilo la completó
    }

    public static void main(String[] args) { // Método principal donde arranca el programa
        Scanner sc = new Scanner(System.in); // Creo el objeto Scanner para leer entradas del usuario

        System.out.print("¿Cuántas tareas deseas ejecutar con Runnable?: "); // Pido cuántas tareas se van a crear
        int numTareas = sc.nextInt(); // Leo el número de tareas como entero
        sc.nextLine(); // Limpio el salto de línea pendiente para no afectar el siguiente nextLine()

        Thread[] hilos = new Thread[numTareas]; // Creo un arreglo de Thread porque Runnable no tiene start() por sí solo

        for (int i = 0; i < numTareas; i++) { // Ciclo para capturar los datos de cada tarea
            System.out.print("Nombre de la tarea " + (i + 1) + ": "); // Pido el nombre de la tarea actual
            String tarea = sc.nextLine(); // Leo el nombre ingresado por el usuario

            System.out.print("Cantidad de bloques para '" + tarea + "': "); // Pido el número de bloques
            int bloques = sc.nextInt(); // Leo la cantidad de bloques como entero

            System.out.print("MB por bloque para '" + tarea + "': "); // Pido cuántos MB pesa cada bloque
            int mb = sc.nextInt(); // Leo los MB por bloque como entero
            sc.nextLine(); // Limpio el salto de línea que deja nextInt()

            Runnable r = new ProcesoDescargaTarea(tarea, bloques, mb); // Creo la instancia de la tarea (Runnable)
            hilos[i] = new Thread(r, "Worker-" + (i + 1)); // Envuelvo el Runnable en un Thread y le asigno un nombre
        } // Fin del ciclo de creación de tareas e hilos

        for (Thread h : hilos) { // Recorro el arreglo de hilos ya armados
            h.start(); // Arranco cada hilo para que corra su respectivo Runnable
        }

        for (Thread h : hilos) { // Recorro de nuevo los hilos para esperar a que todos terminen
            try { // Uso try-catch porque join() puede lanzar InterruptedException
                h.join(); // Espero a que termine el hilo h antes de continuar con el siguiente
            } catch (InterruptedException e) { // Capturo si ocurre una interrupción mientras espero
                System.out.println("Error esperando a " + h.getName()); // Imprimo qué hilo dio problema al esperar
            }
        }

        System.out.println("Todas las tareas Runnable han finalizado."); // Mensaje final al terminar todas las tareas
        sc.close(); // Cierro el Scanner para liberar el recurso de entrada
    }
}