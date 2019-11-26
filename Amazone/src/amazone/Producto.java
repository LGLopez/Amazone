package amazone;

public class Producto {
    private String nombre;
    private String descripcion;
    private String precio;
    private String tipo;
    private String imagen;
    
    Producto(String nombre, String descripcion, String precio, String tipo, String imagen) {
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.tipo = tipo;
       this.precio = precio;
       this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
}
