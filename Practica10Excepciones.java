import java.io.BufferedReader; // Importo BufferedReader para leer texto de forma mas rapida o por linea
import java.io.File; // Importo File para representar la ruta de un archivo
import java.io.FileNotFoundException; // Importo esta excepcion por si el archivo no existe al abrirlo
import java.io.FileReader; // Importo FileReader para leer caracteres desde un archivo
import java.io.IOException; // Importo IOException para errores generales de entrada/salida (es checked)
import java.io.InputStreamReader; // Sirve de puente para convertir bytes del teclado (System.in) a caracteres
import java.util.InputMismatchException; // Excepcion cuando escribes letras donde esperaba un numero con Scanner
import java.util.Scanner; // Para leer lo que el usuario escriba en consola (requisito del profe)

public class Practica10Excepciones { // Nombre de la clase igual al archivo Practica10Excepciones.java

    public static void main(String[] args) { // Metodo principal donde arranca el programa
        Scanner sc = new Scanner(System.in); // Creo el objeto Scanner para pedir datos al usuario
        int opcion = -1; // Inicializo en -1 para que no coincida con 0 y entre al ciclo

        do { // Uso do-while para que el menu se muestre al menos una vez y se repita hasta elegir 0
            System.out.println("\n=== PRACTICA 10: MANEJO DE EXCEPCIONES ==="); // Titulo del menu en consola
            System.out.println("1. Probar ArithmeticException (division entre cero)"); // Opcion 1 del menu
            System.out.println("2. Probar InputMismatchException (leer entero con texto)"); // Opcion 2 del menu
            System.out.println("3. Probar NumberFormatException (convertir texto a numero)"); // Opcion 3 del menu
            System.out.println("4. Probar NullPointerException (usar referencia nula)"); // Opcion 4 del menu
            System.out.println("5. Probar FileNotFoundException (archivo inexistente)"); // Opcion 5 del menu
            System.out.println("6. Probar IOException (lectura con BufferedReader)"); // Opcion 6 del menu
            System.out.println("0. Salir"); // Opcion para terminar el programa
            System.out.print("Elige una opcion: "); // Pido la opcion sin salto de linea para que se vea mas limpio

            try { // Encierro la lectura de la opcion por si el usuario mete algo que no sea int
                opcion = sc.nextInt(); // Leo el numero que eligio el usuario
                sc.nextLine(); // Limpio el salto de linea que deja nextInt() para no arruinar lecturas siguientes

                switch (opcion) { // Evaluo la opcion elegida para llamar al metodo correspondiente
                    case 1: // Si eligio 1
                        probarArithmeticException(sc); // Llamo al metodo que prueba division entre cero
                        break; // Salgo del switch para no ejecutar los demas casos
                    case 2: // Si eligio 2
                        probarInputMismatchException(sc); // Llamo al metodo de error de tipo de dato en Scanner
                        break; // Salgo del switch
                    case 3: // Si eligio 3
                        probarNumberFormatException(sc); // Llamo al metodo de parseInt con texto invalido
                        break; // Salgo del switch
                    case 4: // Si eligio 4
                        probarNullPointerException(sc); // Llamo al metodo para probar referencia null
                        break; // Salgo del switch
                    case 5: // Si eligio 5
                        probarFileNotFoundException(sc); // Llamo al metodo para probar archivo que no existe
                        break; // Salgo del switch
                    case 6: // Si eligio 6
                        probarIOException(sc); // Llamo al metodo para probar IOException con BufferedReader
                        break; // Salgo del switch
                    case 0: // Si eligio 0
                        System.out.println("Saliendo del programa..."); // Mensaje de despedida
                        break; // Salgo del switch y luego termina el do-while
                    default: // Si puso un numero que no esta en el menu (ej. 9 o -2)
                        System.out.println("Opcion no valida. Intenta de nuevo."); // Le aviso que intente otra vez
                }
            } catch (InputMismatchException e) { // Si en el menu escribio letras en vez de numero
                System.out.println("Error atrapado en menu: InputMismatchException (debes ingresar un numero entero)."); // Muestro mensaje de error sin que truene el programa
                sc.nextLine(); // Muy importante: limpio el buffer porque si no se queda trabado en un bucle infinito
            }

        } while (opcion != 0); // Repite mientras la opcion sea distinta de 0

        sc.close(); // Cierro el Scanner al final para liberar el recurso
    }

    // 1. ArithmeticException
    public static void probarArithmeticException(Scanner sc) { // Metodo estatico para no tener que instanciar la clase
        try { // Bloque try donde puede ocurrir la division entre cero
            System.out.print("Ingresa el numerador (entero): "); // Pido el primer numero
            int a = sc.nextInt(); // Guardo el numerador
            System.out.print("Ingresa el denominador (entero, prueba con 0): "); // Pido el divisor (con 0 truena)
            int b = sc.nextInt(); // Guardo el denominador
            sc.nextLine(); // Limpio el salto de linea pendiente

            int resultado = a / b; // Aqui pasa ArithmeticException si b es 0 (en enteros)
            System.out.println("Resultado de la division: " + resultado); // Solo se imprime si b no fue 0
        } catch (ArithmeticException e) { // Atrapo la excepcion matematica si dividio entre 0
            System.out.println("Excepcion capturada: ArithmeticException -> " + e.getMessage()); // Imprimo el mensaje del error
        } catch (InputMismatchException e) { // Por si en vez de numero metio una letra en a o b
            System.out.println("Error de entrada: InputMismatchException (solo se permiten enteros)."); // Aviso del error de entrada
            sc.nextLine(); // Limpio la entrada mala del Scanner
        }
    }

    // 2. InputMismatchException
    public static void probarInputMismatchException(Scanner sc) { // Metodo para probar entrada de tipo incorrecto
        try { // Intento leer un entero
            System.out.print("Ingresa tu edad (escribe un numero entero o letras para provocar error): "); // Instruccion para el usuario
            int edad = sc.nextInt(); // Si escribe "veinte" o "abc", aqui lanza InputMismatchException
            sc.nextLine(); // Limpio salto de linea si todo salio bien
            System.out.println("Edad registrada correctamente: " + edad); // Confirmo que se guardo la edad
        } catch (InputMismatchException e) { // Capturo el error cuando el dato no es int
            System.out.println("Excepcion capturada: InputMismatchException (el dato no coincide con el tipo int)."); // Explico que paso
            sc.nextLine(); // Limpio el token invalido del Scanner
        }
    }

    // 3. NumberFormatException
    public static void probarNumberFormatException(Scanner sc) { // Metodo para convertir String a int
        try { // Intento hacer la conversion
            System.out.print("Ingresa una cadena para convertir a entero (ej. '123' o 'abc'): "); // Pido una cadena al usuario
            String texto = sc.nextLine(); // Leo toda la linea como String
            int numero = Integer.parseInt(texto); // Si texto no es un numero valido (ej. "abc"), lanza NumberFormatException
            System.out.println("Conversion exitosa. Numero: " + numero); // Muestro el entero ya convertido
        } catch (NumberFormatException e) { // Atrapo el error de formato numerico
            System.out.println("Excepcion capturada: NumberFormatException -> " + e.getMessage()); // Muestro detalle de la excepcion
        }
    }

    // 4. NullPointerException
    public static void probarNullPointerException(Scanner sc) { // Metodo para probar acceso a algo que vale null
        System.out.print("¿Quieres que la cadena sea null? (s/n): "); // Pregunto si quiere forzar el null
        String resp = sc.nextLine().trim().toLowerCase(); // Quito espacios y paso a minusculas para comparar facil

        String mensaje = null; // Empieza en null (no apunta a ningun objeto String)
        if (!resp.equals("s")) { // Si no dijo 's', entonces si le pido texto real
            System.out.print("Escribe un texto: "); // Pido el texto
            mensaje = sc.nextLine(); // Ahora mensaje si apunta a un String valido
        }

        try { // Intento usar el objeto mensaje
            System.out.println("Longitud del texto: " + mensaje.length()); // Si mensaje es null, aqui lanza NullPointerException
        } catch (NullPointerException e) { // Capturo la excepcion de puntero/referencia nula
            System.out.println("Excepcion capturada: NullPointerException (intentaste acceder a un metodo/atributo en un objeto null)."); // Mensaje claro del error
        }
    }

    // 5. FileNotFoundException
    public static void probarFileNotFoundException(Scanner sc) { // Metodo para probar abrir un archivo que no existe
        System.out.print("Ingresa el nombre o ruta de un archivo a abrir (ej. no_existe.txt): "); // Pido nombre de archivo
        String nombreArchivo = sc.nextLine(); // Guardo el nombre o ruta escrita

        try { // Intento abrir y leer el archivo
            File archivo = new File(nombreArchivo); // Creo el objeto File
            FileReader fr = new FileReader(archivo); // Al crear FileReader, si el archivo no existe lanza FileNotFoundException
            BufferedReader br = new BufferedReader(fr); // Envuelvo FileReader en BufferedReader para leer lineas
            System.out.println("Primera linea del archivo: " + br.readLine()); // Leo y muestro la primera linea
            br.close(); // Cierro el BufferedReader
        } catch (FileNotFoundException e) { // Primero atrapo FileNotFoundException (es mas especifica que IOException)
            System.out.println("Excepcion capturada: FileNotFoundException -> " + e.getMessage()); // Muestro que no se encontro el archivo
        } catch (IOException e) { // Despues atrapo IOException por si hubo otro fallo de lectura/cierre
            System.out.println("Excepcion capturada: IOException -> " + e.getMessage()); // Muestro error general de E/S
        }
    }

    // 6. IOException
    public static void probarIOException(Scanner sc) { // Metodo para probar IOException
        try { // Bloque try porque BufferedReader y throw new IOException requieren manejo de IOException
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in)); // Creo lector basado en entrada estandar
            System.out.print("Escribe un mensaje usando BufferedReader: "); // Pido un mensaje
            String linea = lector.readLine(); // Leo una linea completa (puede lanzar IOException)
            System.out.println("Leiste: " + linea); // Imprimo lo que escribio el usuario

            // Ejemplo adicional lanzando IOException de forma controlada si el usuario lo solicita
            System.out.print("¿Deseas simular una IOException lanzada explicitamente? (s/n): "); // Pregunto si quiere simularla a la fuerza
            String simular = sc.nextLine().trim().toLowerCase(); // Leo y normalizo respuesta
            if (simular.equals("s")) { // Si puso 's'
                throw new IOException("Falla simulada de entrada/salida."); // Lanzo manualmente una IOException con throw
            }
        } catch (IOException e) { // Capturo la IOException (ya sea real o simulada)
            System.out.println("Excepcion capturada: IOException -> " + e.getMessage()); // Muestro el mensaje de la excepcion
        }
    }
} // Fin de la clase Practica10Excepciones
