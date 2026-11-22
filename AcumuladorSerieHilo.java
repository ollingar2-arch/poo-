import java.util.Scanner; // Importo Scanner para leer los datos que ingrese el usuario por consola

public class AcumuladorSerieHilo extends Thread { // Defino la clase heredando de Thread para ejecutar sumas de forma concurrente
    private int limite; // Guardo el límite N hasta donde se va a sumar la serie (1 + 2 + ... + N)
    private long sumaTotal; // Uso long para acumular la suma y evitar desbordamientos en límites grandes

    public AcumuladorSerieHilo(String nombre, int limite) { // Constructor para darle nombre al hilo y fijar su límite N
        super(nombre); // Paso el nombre al constructor de Thread para identificar este hilo
        this.limite = limite; // Guardo el límite recibido en el atributo de la instancia
        this.sumaTotal = 0; // Inicializo el acumulador en cero antes de empezar a sumar
    }

    @Override // Indico que sobrescribo el método run() heredado de Thread
    public void run() { // Lógica que ejecutará cada hilo cuando se inicie
        int pasoReporte = Math.max(1, limite / 4); // Calculo cada cuántos pasos imprimir avance (mínimo 1 para no dividir entre 0)
        for (int i = 1; i <= limite; i++) { // Recorro los enteros desde 1 hasta el límite N indicado
            sumaTotal += i; // Acumulo el valor actual i en la suma total
            if (i % pasoReporte == 0 || i == limite) { // Imprimo avance solo en los puntos de reporte o al llegar al final
                System.out.println("[" + getName() + "] i = " + i + "/" + limite + // Muestro el hilo y el progreso actual
                        " | Suma parcial = " + sumaTotal); // Muestro cuánto lleva sumado hasta este momento
            }
            try { // Encierro Thread.sleep en try-catch porque puede lanzar InterruptedException
                Thread.sleep(15); // Duermo 15 ms para que se alcance a notar cómo se intercalan los hilos
            } catch (InterruptedException e) { // Si interrumpen el hilo mientras duerme, manejo la excepción aquí
                System.out.println("[" + getName() + "] Detenido."); // Aviso que este calculador fue detenido
                return; // Termino el método run() anticipadamente
            }
        } // Fin del ciclo de acumulación
        System.out.println("=== [" + getName() + "] Suma final (1.." + limite + ") = " + sumaTotal + " ==="); // Imprimo el resultado final del hilo
    }

    public static void main(String[] args) { // Método principal donde comienza la ejecución del programa
        Scanner sc = new Scanner(System.in); // Creo el objeto Scanner para capturar datos del teclado

        System.out.print("¿Cuántos hilos calculadores deseas crear?: "); // Pregunto cuántos hilos trabajadores se crearán
        int cantidad = sc.nextInt(); // Leo la cantidad de hilos como entero
        sc.nextLine(); // Limpio el salto de línea restante para que no se brinque el siguiente nextLine()

        AcumuladorSerieHilo[] trabajadores = new AcumuladorSerieHilo[cantidad]; // Creo un arreglo para almacenar los hilos

        for (int i = 0; i < cantidad; i++) { // Pido los parámetros de cada hilo calculador uno por uno
            System.out.print("Nombre para el calculador " + (i + 1) + ": "); // Solicito el nombre del hilo actual
            String nombre = sc.nextLine(); // Leo el nombre escrito por el usuario

            System.out.print("Límite entero positivo N para '" + nombre + "': "); // Pido el límite superior N de la serie
            int n = sc.nextInt(); // Leo el límite N ingresado
            sc.nextLine(); // Limpio el buffer después de leer el entero

            trabajadores[i] = new AcumuladorSerieHilo(nombre, n); // Instancio el hilo y lo guardo en el arreglo
        } // Termina la captura de datos de todos los hilos

        for (AcumuladorSerieHilo t : trabajadores) { // Recorro el arreglo de hilos trabajadores
            t.start(); // Arranco cada hilo de forma concurrente
        }

        for (AcumuladorSerieHilo t : trabajadores) { // Recorro nuevamente los hilos para sincronizar el final
            try { // Manejo la posible excepción InterruptedException que lanza join()
                t.join(); // Espero a que el hilo t termine antes de que el main continúe
            } catch (InterruptedException e) { // Si ocurre una interrupción mientras espero al hilo
                System.out.println("Error al esperar el hilo " + t.getName()); // Muestro en qué hilo falló la espera
            }
        }

        System.out.println("Cálculos concurrentes concluidos."); // Confirmo que todos los hilos ya terminaron su trabajo
        sc.close(); // Cierro el Scanner para liberar el flujo de entrada
    }
}