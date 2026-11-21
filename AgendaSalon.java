// Importamos las clases necesarias para leer/escribir archivos y manejar excepciones
import java.io.BufferedReader; // Sirve para leer el archivo línea por línea rápido
import java.io.BufferedWriter; // Sirve para escribir texto de forma eficiente
import java.io.File; // Representa el archivo en el disco para ver si existe
import java.io.FileReader; // Abre el archivo en modo lectura
import java.io.FileWriter; // Abre el archivo en modo escritura
import java.io.IOException; // Maneja errores de entrada/salida (archivos)
import java.util.ArrayList; // Lista dinámica para guardar las reservaciones en memoria
import java.util.Scanner; // Para leer lo que el usuario teclea en consola

// Clase principal del programa de reservaciones del salón de fiestas
public class AgendaSalon {

    // Nombre del archivo donde se va a guardar la agenda para que no se borre
    private static final String ARCHIVO_AGENDA = "agenda_salon.txt";

    // Clase interna para representar una reservación o espacio en la agenda
    static class Reservacion {
        int diaAnio; // Día del año (por ejemplo del 1 al 365)
        String diaSemana; // Día de la semana (lunes, martes, etc.)
        String hora; // Hora o turno (ej. "14:00" o "14:00-18:00")
        boolean disponible; // true si está libre, false si ya está apartado
        String reservadoPor; // Nombre de la empresa o persona que apartó

        // Constructor para crear una reservación con sus datos
        public Reservacion(int diaAnio, String diaSemana, String hora, boolean disponible, String reservadoPor) {
            this.diaAnio = diaAnio; // Guardamos el día del año
            this.diaSemana = diaSemana; // Guardamos el día de la semana
            this.hora = hora; // Guardamos la hora
            this.disponible = disponible; // Guardamos si está disponible o no
            this.reservadoPor = reservadoPor; // Guardamos quién reservó (o "LIBRE")
        }

        // Convierte el objeto a una línea de texto separada por ";" para guardarla en el .txt
        public String aLineaArchivo() {
            return diaAnio + ";" + diaSemana + ";" + hora + ";" + disponible + ";" + reservadoPor;
        }

        // Reconstruye un objeto Reservacion a partir de una línea leída del archivo .txt
        public static Reservacion desdeLineaArchivo(String linea) {
            String[] partes = linea.split(";"); // Separamos la línea por cada ";"
            if (partes.length < 5) { // Por si hay alguna línea corrupta o vacía
                return null; // Regresamos null para ignorarla
            }
            int dia = Integer.parseInt(partes[0].trim()); // Convertimos el día del año a int
            String semana = partes[1].trim(); // Obtenemos el día de la semana
            String hr = partes[2].trim(); // Obtenemos la hora
            boolean disp = Boolean.parseBoolean(partes[3].trim()); // Convertimos texto a boolean
            String cliente = partes[4].trim(); // Obtenemos el nombre de quien reservó
            return new Reservacion(dia, semana, hr, disp, cliente); // Devolvemos el objeto listo
        }

        // Cómo se muestra cada registro cuando imprimimos la agenda en pantalla
        @Override
        public String toString() {
            String estado = disponible ? "DISPONIBLE" : "NO DISPONIBLE"; // Texto según disponibilidad
            return "Día del año: " + diaAnio +
                    " | Día: " + diaSemana +
                    " | Hora: " + hora +
                    " | Estado: " + estado +
                    " | Cliente/Empresa: " + reservadoPor;
        }
    }

    // Método main: aquí empieza a correr el programa
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner para leer datos del teclado
        ArrayList<Reservacion> agenda = cargarAgenda(); // Cargamos lo que ya exista en el archivo .txt

        int opcion = 0; // Variable para guardar la opción del menú

        // Ciclo del menú hasta que el usuario elija salir (opción 4)
        do {
            System.out.println("\n=== AGENDA DE RESERVACIONES - SALON DE FIESTAS ==="); // Título del menú
            System.out.println("1. Hacer una reservacion"); // Opción 1
            System.out.println("2. Consultar disponibilidad por fecha y hora"); // Opción 2
            System.out.println("3. Ver toda la agenda"); // Opción 3
            System.out.println("4. Salir"); // Opción 4
            System.out.print("Elige una opcion: "); // Pedimos opción

            // Validamos que el usuario meta un número entero y no truene el programa
            if (sc.hasNextInt()) {
                opcion = sc.nextInt(); // Leemos el número
                sc.nextLine(); // Limpiamos el salto de línea que deja nextInt()
            } else {
                System.out.println("Error: ingresa un numero valido."); // Avisamos si metió letras
                sc.nextLine(); // Limpiamos la entrada inválida
                continue; // Volvemos al inicio del ciclo
            }

            // Evaluamos qué eligió el usuario
            switch (opcion) {
                case 1:
                    hacerReservacion(sc, agenda); // Llamamos al método para reservar
                    guardarAgenda(agenda); // Guardamos cambios en el archivo de texto
                    break; // Salimos del case 1
                case 2:
                    consultarEspacio(sc, agenda); // Consultamos si un horario está libre u ocupado
                    break; // Salimos del case 2
                case 3:
                    mostrarAgenda(agenda); // Imprimimos todos los registros guardados
                    break; // Salimos del case 3
                case 4:
                    guardarAgenda(agenda); // Aseguramos guardar antes de cerrar
                    System.out.println("Saliendo del programa... ¡Hasta luego!"); // Mensaje de despedida
                    break; // Salimos del case 4
                default:
                    System.out.println("Error: opcion no valida, intenta de nuevo."); // Si puso otro número
            }

        } while (opcion != 4); // Se repite mientras no elija 4

        sc.close(); // Cerramos el Scanner para liberar recursos
    }

    // Método para registrar una nueva reservación o marcar error si ya está ocupada
    private static void hacerReservacion(Scanner sc, ArrayList<Reservacion> agenda) {
        System.out.print("Ingresa el dia del anio (1-365): "); // Pedimos día del año
        int diaAnio = leerEnteroSeguro(sc); // Leemos el entero de forma segura

        System.out.print("Ingresa el dia de la semana (ej. Viernes): "); // Pedimos día de la semana
        String diaSemana = sc.nextLine().trim(); // Leemos y quitamos espacios extra

        System.out.print("Ingresa la hora o turno (ej. 18:00): "); // Pedimos la hora
        String hora = sc.nextLine().trim(); // Leemos la hora

        // Buscamos si ya existe un registro con ese mismo día del año y hora
        Reservacion existente = buscarReservacion(agenda, diaAnio, hora);

        if (existente != null) { // Si ya existe ese espacio en la agenda
            if (!existente.disponible) { // Y NO está disponible (ya está reservado)
                System.out.println("ERROR: Ese horario ya esta reservado por '" + existente.reservadoPor + "' y no esta disponible."); // Marcamos error como pide la práctica
                return; // Salimos sin modificar nada
            } else {
                // Si existía pero estaba marcado como disponible, pedimos el nombre y actualizamos
                System.out.print("Ingresa el nombre de la persona o empresa que reserva: "); // Pedimos cliente
                String cliente = sc.nextLine().trim(); // Leemos nombre
                existente.diaSemana = diaSemana; // Actualizamos día de la semana por si cambió
                existente.disponible = false; // Marcamos como NO disponible
                existente.reservadoPor = cliente; // Guardamos quién reservó
                System.out.println("¡Reservacion actualizada con exito para " + cliente + "!"); // Confirmación
                return; // Terminamos
            }
        }

        // Si no existía un registro previo para ese día y hora, lo creamos como reservado
        System.out.print("Ingresa el nombre de la persona o empresa que reserva: "); // Pedimos nombre
        String cliente = sc.nextLine().trim(); // Leemos nombre

        Reservacion nueva = new Reservacion(diaAnio, diaSemana, hora, false, cliente); // Creamos el objeto
        agenda.add(nueva); // Lo agregamos a la lista de la agenda
        System.out.println("¡Salon reservado correctamente para " + cliente + "!"); // Confirmación
    }

    // Método para consultar si una fecha/hora está disponible o ya está apartada
    private static void consultarEspacio(Scanner sc, ArrayList<Reservacion> agenda) {
        System.out.print("Ingresa el dia del anio a consultar (1-365): "); // Pedimos día del año
        int diaAnio = leerEnteroSeguro(sc); // Leemos entero

        System.out.print("Ingresa la hora a consultar (ej. 18:00): "); // Pedimos hora
        String hora = sc.nextLine().trim(); // Leemos hora

        Reservacion r = buscarReservacion(agenda, diaAnio, hora); // Buscamos en la agenda

        if (r == null) { // Si no hay registro, el salón está libre en ese horario
            System.out.println("Estado: DISPONIBLE (no hay reservaciones registradas para ese dia y hora)."); // Avisamos disponible
        } else if (r.disponible) { // Si existe y está marcado disponible
            System.out.println("Estado: DISPONIBLE."); // Avisamos disponible
        } else { // Si ya está ocupado
            System.out.println("ERROR / OCUPADO: No disponible. Reservado por: " + r.reservadoPor + " (" + r.diaSemana + ")."); // Mostramos quién lo apartó
        }
    }

    // Método para imprimir todas las reservaciones guardadas
    private static void mostrarAgenda(ArrayList<Reservacion> agenda) {
        System.out.println("\n--- LISTADO DE RESERVACIONES ---"); // Encabezado
        if (agenda.isEmpty()) { // Si la lista está vacía
            System.out.println("No hay reservaciones registradas todavia."); // Mensaje informativo
            return; // Salimos del método
        }
        for (int i = 0; i < agenda.size(); i++) { // Recorremos toda la lista
            System.out.println((i + 1) + ". " + agenda.get(i).toString()); // Imprimimos cada reservación
        }
    }

    // Busca una reservación que coincida en día del año y hora
    private static Reservacion buscarReservacion(ArrayList<Reservacion> agenda, int diaAnio, String hora) {
        for (Reservacion r : agenda) { // Recorremos cada reservación
            if (r.diaAnio == diaAnio && r.hora.equalsIgnoreCase(hora)) { // Comparamos día y hora
                return r; // Si coincide, la regresamos
            }
        }
        return null; // Si no se encontró, regresamos null
    }

    // Lee un número entero evitando que el programa se cierre si escriben algo que no es número
    private static int leerEnteroSeguro(Scanner sc) {
        while (!sc.hasNextInt()) { // Mientras lo que escriba no sea un entero
            System.out.print("Dato invalido. Ingresa un numero entero: "); // Pedimos de nuevo
            sc.nextLine(); // Descartamos lo que escribió
        }
        int valor = sc.nextInt(); // Guardamos el entero válido
        sc.nextLine(); // Limpiamos el salto de línea pendiente
        return valor; // Regresamos el número
    }

    // Guarda toda la agenda en el archivo de texto agenda_salon.txt
    private static void guardarAgenda(ArrayList<Reservacion> agenda) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_AGENDA))) { // Abrimos el archivo para escribir (y cerramos automático con try-with-resources)
            for (Reservacion r : agenda) { // Por cada reservación en la lista
                bw.write(r.aLineaArchivo()); // Escribimos la línea formateada
                bw.newLine(); // Agregamos un salto de línea para el siguiente registro
            }
        } catch (IOException e) { // Si ocurre algún error al escribir el archivo
            System.out.println("Error al guardar la agenda en el archivo: " + e.getMessage()); // Mostramos el error
        }
    }

    // Carga las reservaciones desde agenda_salon.txt al iniciar el programa
    private static ArrayList<Reservacion> cargarAgenda() {
        ArrayList<Reservacion> lista = new ArrayList<>(); // Lista donde iremos metiendo lo leído
        File archivo = new File(ARCHIVO_AGENDA); // Referencia al archivo en disco

        if (!archivo.exists()) { // Si el archivo todavía no existe (primera vez que se corre)
            return lista; // Regresamos la lista vacía sin marcar error
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) { // Abrimos el archivo para leer
            String linea; // Variable para cada línea del archivo
            while ((linea = br.readLine()) != null) { // Leemos hasta llegar al final del archivo
                Reservacion r = Reservacion.desdeLineaArchivo(linea); // Convertimos la línea a objeto Reservacion
                if (r != null) { // Si se pudo convertir bien
                    lista.add(r); // La agregamos a la lista
                }
            }
        } catch (IOException e) { // Si falla la lectura del archivo
            System.out.println("Error al leer el archivo de agenda: " + e.getMessage()); // Mostramos mensaje de error
        }

        return lista; // Regresamos la agenda cargada
    }
}