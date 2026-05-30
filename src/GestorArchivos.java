import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {
    private static final String CARPETA_DB = "db/";
    private static final String ARCHIVO_DOCTORES = CARPETA_DB + "doctores.txt";
    private static final String ARCHIVO_PACIENTES = CARPETA_DB + "pacientes.txt";
    private static final String ARCHIVO_CITAS = CARPETA_DB + "citas.txt";

    // Método para validar que la carpeta y los archivos existan al iniciar el programa
    public static void inicializarBaseDeDatos() {
        File carpeta = new File(CARPETA_DB);
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        String[] archivos = {"doctores.txt", "pacientes.txt", "citas.txt"};
        for (String nombreArchivo : archivos) {
            File archivo = new File(CARPETA_DB + nombreArchivo);
            try {
                if (!archivo.exists()) {
                    archivo.createNewFile();
                    System.out.println("Archivo verificado y regenerado: " + nombreArchivo);
                }
            } catch (IOException e) {
                System.out.println("Error al inicializar el archivo: " + nombreArchivo);
            }
        }
    }

    // Guarda un doctor al final del archivo de texto
    public static void guardarDoctor(Doctor doctor) {
        try (FileWriter fw = new FileWriter(ARCHIVO_DOCTORES, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(doctor.getId() + "," + doctor.getNombreCompleto() + "," + doctor.getSpecialty());
        } catch (IOException e) {
            System.out.println("Error al guardar el registro del médico.");
        }
    }

    // Carga todos los doctores del archivo a una lista en memoria
    public static List<Doctor> cargarDoctores() {
        List<Doctor> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_DOCTORES);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_DOCTORES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    lista.add(new Doctor(datos[0], datos[1], datos[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los registros de los médicos.");
        }
        return lista;
    }

    // Guarda un paciente al final del archivo de texto
    public static void guardarPaciente(Paciente paciente) {
        try (FileWriter fw = new FileWriter(ARCHIVO_PACIENTES, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(paciente.getId() + "," + paciente.getNombreCompleto());
        } catch (IOException e) {
            System.out.println("Error al guardar el registro del paciente.");
        }
    }

    // Carga todos los pacientes del archivo a una lista en memoria
    public static List<Paciente> cargarPacientes() {
        List<Paciente> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_PACIENTES);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_PACIENTES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 2) {
                    lista.add(new Paciente(datos[0], datos[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los registros de los pacientes.");
        }
        return lista;
    }

    // Guarda una cita en el archivo usando los IDs de doctor y paciente
    public static void guardarCita(Cita cita) {
        try (FileWriter fw = new FileWriter(ARCHIVO_CITAS, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            // Estructura: idCita,fechaHora,motivo,idDoctor,idPaciente
            out.println(cita.getIdCita() + "," + cita.getFechaHora() + "," +
                    cita.getMotivo() + "," + cita.getDoctor().getId() + "," +
                    cita.getPaciente().getId());
        } catch (IOException e) {
            System.out.println("Error al guardar la cita en el archivo.");
        }
    }

    // Carga las citas y las vincula con los objetos Doctor y Paciente reales
    public static List<Cita> cargarCitas(List<Doctor> doctores, List<Paciente> pacientes) {
        List<Cita> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_CITAS);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CITAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    String idCita = datos[0];
                    String fechaHora = datos[1];
                    String motivo = datos[2];
                    String idDoc = datos[3];
                    String idPac = datos[4];

                    // Busca los objetos en las listas que ya están cargadas
                    Doctor dFound = doctores.stream().filter(d -> d.getId().equals(idDoc)).findFirst().orElse(null);
                    Paciente pFound = pacientes.stream().filter(p -> p.getId().equals(idPac)).findFirst().orElse(null);

                    if (dFound != null && pFound != null) {
                        lista.add(new Cita(idCita, fechaHora, motivo, dFound, pFound));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las citas del archivo.");
        }
        return lista;
    }
}