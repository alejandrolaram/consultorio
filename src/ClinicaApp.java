import java.util.List;
import java.util.Scanner;

public class ClinicaApp {
    public static void main(String[] args) {
        // Validación y regeneración automática de archivos al arrancar
        GestorArchivos.inicializarBaseDeDatos();

        // Cargar datos existentes de los archivos a la memoria RAM al iniciar
        List<Doctor> listaDoctores = GestorArchivos.cargarDoctores();
        List<Paciente> listaPacientes = GestorArchivos.cargarPacientes();

        Scanner scanner = new Scanner(String.class.cast("").getClass().getSimpleName().equals("String") ? System.in : null);

        // Objeto Administrador de prueba para el login
        Administrador admin = new Administrador("A01", "Administrador General", "admin", "1234");

        System.out.println("----------------------------------------");
        System.out.println("  SISTEMA DE ADMINISTRACIÓN CLÍNICA    ");
        System.out.println("----------------------------------------");

        // Sistema de Autenticación (Login)
        boolean autenticado = false;
        int intentos = 3;

        while (!autenticado && intentos > 0) {
            System.out.println("\n--- Inicio de Sesión ---");
            System.out.print("Usuario: ");
            String user = scanner.nextLine();
            System.out.print("Contraseña: ");
            String pass = scanner.nextLine();

            if (admin.login(user, pass)) {
                autenticado = true;
                System.out.println("\n¡Acceso concedido! Bienvenido, " + admin.getNombreCompleto() + ".");
            } else {
                intentos--;
                System.out.println("Credenciales incorrectas. Intentos restantes: " + intentos);
            }
        }

        if (!autenticado) {
            System.out.println("\nNúmero de intentos excedido. Saliendo del sistema...");
            return;
        }

        // Menú Principal del Sistema
        int opcion = 0;
        do {
            System.out.println("\n--------------------------------------");
            System.out.println("             MENÚ PRINCIPAL             ");
            System.out.println("----------------------------------------");
            System.out.println("1. Registrar Médico");
            System.out.println("2. Registrar Paciente");
            System.out.println("3. Agendar Cita Médica");
            System.out.println("4. Ver Citas Agendadas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- Registro de Nuevo Médico ---");
                        System.out.print("ID del Médico: ");
                        String idDoc = scanner.nextLine();
                        System.out.print("Nombre Completo: ");
                        String nomDoc = scanner.nextLine();
                        System.out.print("Especialidad: ");
                        String espDoc = scanner.nextLine();

                        Doctor nuevoDoc = new Doctor(idDoc, nomDoc, espDoc);
                        listaDoctores.add(nuevoDoc);          // Guardar en memoria RAM
                        GestorArchivos.guardarDoctor(nuevoDoc); // Guardar permanente en archivo
                        System.out.println("\n¡Médico registrado y guardado con éxito!");
                        break;

                    case 2:
                        System.out.println("\n--- Registro de Nuevo Paciente ---");
                        System.out.print("ID del Paciente: ");
                        String idPac = scanner.nextLine();
                        System.out.print("Nombre Completo: ");
                        String nomPac = scanner.nextLine();

                        Paciente nuevoPac = new Paciente(idPac, nomPac);
                        listaPacientes.add(nuevoPac);            // Guardar en memoria RAM
                        GestorArchivos.guardarPaciente(nuevoPac); // Guardar permanente en archivo
                        System.out.println("\n¡Paciente registrado y guardado con éxito!");
                        break;

                    case 3:
                        System.out.println("\n[Funcionalidad en desarrollo: Agendar Cita]");
                        break;
                    case 4:
                        System.out.println("\n[Funcionalidad en desarrollo: Mostrar Citas]");
                        break;
                    case 5:
                        System.out.println("\nCerrando sesión. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("\nOpción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nError: Por favor, ingrese un número entero.");
            }

        } while (opcion != 5);

        scanner.close();
    }
}