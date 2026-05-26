public abstract class Persona {
    // Atributos privados encapsulados
    private String id;
    private String nombreCompleto;

    // Constructor de la clase
    public Persona(String id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    // Métodos de acceso (Getters)
    public String getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
}