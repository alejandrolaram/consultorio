public class Doctor extends Persona {
    private String specialty; // Atributo propio de la clase

    // Constructor que invoca al padre con 'super'
    public Doctor(String id, String nombreCompleto, String specialty) {
        super(id, nombreCompleto);
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    @Override
    public String toString() {
        return "Doctor - ID: " + getId() + ", Nombre: " + getNombreCompleto() + ", Especialidad: " + specialty;
    }
}