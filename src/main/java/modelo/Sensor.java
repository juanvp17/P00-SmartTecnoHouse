package modelo;

// Imports para entender el formato JSON

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Clase abstracta Sensor
 * Plantilla base para todos los sensores de la casa. Implementa IDispositivo
 */

public abstract class Sensor implements IDispositivos {

    // Variables protegidas

    protected String id;
    protected String nombre;
    protected String unidadMedida;
    protected double valor;

    // Constructor para inicializar los datos por defecto de cualquier sensor

    public Sensor(String id, String nombre, String unidadMedida) {

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
    public String getID() {
        return this.id;
    }

    @Override
    public String getNombre() {

        return this.nombre;
    }

    @Override
    public String getEstadoActual() {

        return this.valor + " " + this.unidadMedida;
    }

    /**
     * Métodos propios de los sensores
     * Obtiene el valor numérico actual del sensor sin la unidad de medida
     */

    public double getValor() {
        return this.valor;
    }

    /**
     * Método abstracto para actualizar el valor del sensor. Cada uno lo hará de forma independiente
     * Recibe los datos ya pre-leídos del JSON para evitar lecturas innecesarias del disco
     * El método protegido se encarga de recorrer los datos y sacar el valor buscado, que será el que se le pase al sensor concreto cuando invoque actualizarValor()
     */

    public abstract void actualizarValor(JSONArray datosJSON);


    protected JSONObject buscarDatos(JSONArray datosJSON) {

        // Recorremos los elementos del array buscando el valor deseado

        for (int i = 0; i < datosJSON.length(); i++) {

            JSONObject objetoSensor = datosJSON.getJSONObject(i);

            //Compraramos el "sensor_id" del JSON con el ID de esta clase

            if (objetoSensor.getString("sensor_id").equals(this.id)) {

                // Cuando encontremos el deseado, devolvemos todos los datos

                return objetoSensor;
            }
        }

        // Devuelve null si los datos no se han encontrado

        return null;

    }

}
