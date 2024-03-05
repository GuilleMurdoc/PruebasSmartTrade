package logic;

public class ProductDTO{
    public String nombre;

    public ProductDTO(){
        this.nombre = "Paco";
    }

    public String toJSON(){
        return this.nombre;
    }
}