public class Cita {
    private String idCita;
    private String fechaHora;
    private String motivo;
    private Doctor doctor;   // Relación directa con el objeto Doctor
    private Paciente paciente; // Relación directa con el objeto Paciente

    // Constructor para inicializar la cita con sus relaciones
    public Cita(String idCita, String fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    // Métodos de acceso (Getters)
    public String getIdCita() {
        return idCita;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    // Método para mostrar los detalles completos de la cita de forma limpia
    public String getDetails() {
        return "Cita ID: " + idCita + " | Fecha: " + fechaHora +
                "\n  " + paciente.toString() +
                "\n  " + doctor.toString() +
                "\n  Motivo: " + motivo + "\n-----------------------------------";
    }
}