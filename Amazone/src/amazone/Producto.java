package amazone;

public class Producto {
    private String id;
    private String nombre;
    private String descripcion;
    private String precio;
    private String tipo;

    
    Producto(String id, String nombre, String descripcion, String precio, String tipo) {
       this.id = id;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.tipo = tipo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
    
}
