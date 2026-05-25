package modelo;

/**
 * Clase abstracta Sensor
 * Plantilla base para todos los sensores de la casa. Implementa IDispositivo
 */

public abstract class Sensor implements IDispositivos {

    // Variables protegidas

    protected String id;
    protected String nombre;
    protected  String unidadMedida;
    protected double valor;

    // Constructor para inicializar los datos por defecto de cualquier sensor

    public Sensor(String id, String nombre, String unidadMedida){

        this.id = id;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.valor = 0.0;
    }

    /**
     * Implementación de la interfaz IDispositivo
     * Sobreescribimos los métodos de la interfaz con los valores correspondientes
     */

    @Override
    public String getID(){
        return this.id;
    }

    @Override
    public String getNombre(){
        return this.nombre;
    }

    @Override
    public String getEstadoActual(){
        return  this.valor + " " + this.unidadMedida;
    }

    /**
     * Métodos propios de los sensores
     * Obtiene el valor numérico actual del sensor sin la unidad de medida
     */

    public double getValor(){
        return this.valor;
    }

     // Método abstracto para actualizar el valor del sensor. Cada uno lo hará de forma independiente

    public abstract void actualizarValor();




}
