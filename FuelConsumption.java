 
/**
 * Enumeration class FuelConsumption - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum FuelConsumption
{
    ALTA ("HIGH", 8),
    MEDIA ("MEDIUM", 6),
    BAJA("LOW", 4);
    
    private final String nombre;
    private final int valor;

    FuelConsumption(String nombre, int valor){
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
