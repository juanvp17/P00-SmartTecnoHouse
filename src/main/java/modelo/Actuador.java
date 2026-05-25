package modelo;

/**
 * Clase abstracta Actuador
 * Plantilla base para todos los dispositivos que realizan alguna acción. Implementa IDispositivo
 */

public abstract class Actuador implements IDispositivos {

    // Variables protegidas

    protected String id;
    protected String nombre;
    protected boolean encendido;

    // Constructor para inicializar los datos por defecto de cualquier actuador

    public Actuador (String id, String nombre){

        this.id = id;
        this.nombre = nombre;
        this.encendido = false;
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
        if (this.encendido){
            return "ON";
        } else {
            return "OFF";
        }
    }

    /**
     * Métodos propios de los actuadores
     *
     * Método abstracto para ejecutar una acción
     */

    public abstract void ejecutarAccion (String accion);

    // Método abstracto que devuelve una lista de las acciones disponibles

    public abstract String[] getAccionesPosibles();

}
