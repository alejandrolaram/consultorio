public class Administrador extends Persona {
    private String usuario;
    private String contrasena;

    public Administrador(String id, String nombreCompleto, String usuario, String contrasena) {
        super(id, nombreCompleto);
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Método para validar el acceso al sistema
    public boolean login(String userIngresado, String passIngresado) {
        return this.usuario.equals(userIngresado) && this.contrasena.equals(passIngresado);
    }
}