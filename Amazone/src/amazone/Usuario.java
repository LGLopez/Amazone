package amazone;

public class Usuario {
    private int id;
    private String nombre;
    private String ap;
    private String am;
    private String nombreUsuario;
    private String password;
    private String passwordcon;
    private String perfil;
    private String correo;

    Usuario(String nombre, String ap, String am, String usuario, String correo, String password, String passwordcon, String perfil) {
        this.nombre = nombre;
        this.ap = ap;
        this.am = am;
        nombreUsuario = usuario;
        this.correo = correo;
        this.password = password;
        this.passwordcon = passwordcon;
        this.perfil = perfil;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
       
    public String getPasswordcon() {
        return passwordcon;
    }

    public void setPasswordcon(String passwordcon) {
        this.passwordcon = passwordcon;
    }
}
