package amazone;

import java.io.Serializable;

public class Producto implements Serializable{
    private String nombre;
    private String descripcion;
    private String precio;
    private String tipo;
    private String cantidad;
    private String imagen;
    
    Producto(String nombre, String descripcion, String precio, String tipo, String imagen,String cantidad) {
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.tipo = tipo;
       this.precio = precio;
       this.imagen = imagen;
       this.cantidad = cantidad;
    }

    Producto() {}

    //Peoductos carrito
    Producto(String cantidad) {
       this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
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
