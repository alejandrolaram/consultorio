public class Paciente extends Persona {

    // Constructor que invoca al padre
    public Paciente(String id, String nombreCompleto) {
        super(id, nombreCompleto);
    }

    @Override
    public String toString() {
        return "Paciente - ID: " + getId() + ", Nombre: " + getNombreCompleto();
    }
}