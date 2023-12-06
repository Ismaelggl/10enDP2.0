 
/**
 * Enumeration class FuelConsumption - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum FuelConsumption
{
    HIGH ("High", 8),
    MEDIUM ("Medium", 6),
    LOW ("Low", 4);
    
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
      return "<fuel consumption: " + getNombre() + " (value: " + getValor() + ")>"; 
    }
}
