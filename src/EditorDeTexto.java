import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class EditorDeTexto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            // Mostrar menú
            System.out.println("\n--- Menú ---");
            System.out.println("1. Crear un fichero");
            System.out.println("2. Editar un fichero");
            System.out.println("3. Borrar un fichero");
            System.out.println("4. Crear una carpeta");
            System.out.println("5. Borrar una carpeta");
            System.out.println("0. Salir");
            System.out.print("Introduzca una opción del menú: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    crearFichero(scanner);
                    break;
                case 2:
                    editarFichero(scanner);
                    break;
                case 3:
                    borrarFichero(scanner);
                    break;
                case 4:
                    crearCarpeta(scanner);
                    break;
                case 5:
                    borrarCarpeta(scanner);
                    break;
                case 0:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción del menú.");
                    break;
            }
        }
        scanner.close();
    }

    // Método para crear un fichero
    private static void crearFichero(Scanner scanner) {
        System.out.print("Introduzca el nombre del fichero a crear (con extensión, por ejemplo, archivo.txt): ");
        String nombreFichero = scanner.nextLine();
        File fichero = new File(nombreFichero);

        try {
            if (fichero.createNewFile()) {
                System.out.println("Fichero creado: " + fichero.getName());
            } else {
                System.out.println("El fichero ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el fichero.");
            e.printStackTrace();
        }
    }

    // Método para editar un fichero
    private static void editarFichero(Scanner scanner) {
        System.out.print("Introduzca el nombre del fichero a editar: ");
        String nombreFichero = scanner.nextLine();
        File fichero = new File(nombreFichero);

        if (fichero.exists()) {
            System.out.print("Introduzca el texto que desea agregar al fichero: ");
            String texto = scanner.nextLine();

            try {
                Files.write(Paths.get(nombreFichero), (texto + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
                System.out.println("Texto agregado al fichero.");
            } catch (IOException e) {
                System.out.println("Ocurrió un error al editar el fichero.");
                e.printStackTrace();
            }
        } else {
            System.out.println("El fichero no existe.");
        }
    }

    // Método para borrar un fichero
    private static void borrarFichero(Scanner scanner) {
        System.out.print("Introduzca el nombre del fichero a borrar: ");
        String nombreFichero = scanner.nextLine();
        File fichero = new File(nombreFichero);

        if (fichero.exists()) {
            if (fichero.delete()) {
                System.out.println("Fichero eliminado con éxito.");
            } else {
                System.out.println("No se pudo eliminar el fichero.");
            }
        } else {
            System.out.println("El fichero no existe.");
        }
    }

    // Método para crear una carpeta
    private static void crearCarpeta(Scanner scanner) {
        System.out.print("Introduzca el nombre de la carpeta a crear: ");
        String nombreCarpeta = scanner.nextLine();
        File carpeta = new File(nombreCarpeta);

        if (carpeta.mkdir()) {
            System.out.println("Carpeta creada con éxito.");
        } else {
            System.out.println("No se pudo crear la carpeta o ya existe.");
        }
    }

    // Método para borrar una carpeta
    private static void borrarCarpeta(Scanner scanner) {
        System.out.print("Introduzca el nombre de la carpeta a borrar: ");
        String nombreCarpeta = scanner.nextLine();
        File carpeta = new File(nombreCarpeta);

        if (carpeta.exists()) {
            if (carpeta.isDirectory()) {
                if (carpeta.delete()) {
                    System.out.println("Carpeta eliminada con éxito.");
                } else {
                    System.out.println("No se pudo eliminar la carpeta (debe estar vacía).");
                }
            } else {
                System.out.println("El nombre proporcionado no corresponde a una carpeta.");
            }
        } else {
            System.out.println("La carpeta no existe.");
        }
    }
}
