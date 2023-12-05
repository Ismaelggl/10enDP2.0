    
/**
 * Write a description of class EnumReliable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum Reliable
{
    LOW("LOW",5),
    HIGH("HIGH",10);
    
    private final String nombre;
    private final int valor;

    Reliable(String nombre, int valor){
        this.nombre = nombre;
        this.valor = valor;
    }
    public String getNombre()
    {
        return nombre;
    }
    public int getValor()
    {
        return valor;
    }
    @Override
    public String toString()
    {
      return getNombre() + "(" + getValor() + ")"; 
    }
}
