package modelo.dispositivos.sensores;


//Importamos la clase padre porque está en un nivel superior

import modelo.Sensor;

//Imports necesarios para abrir y leer el archivo

import java.nio.file.Files;
import java.nio.file.Paths;

//Imports para entender el formato JSON

import org.json.JSONArray;
import org.json.JSONObject;

// Clase concreta para el Sensor de Humedad. Aquí definimos el comportamiento de un tipo de sensor en concreto

public class SensorHumedad extends Sensor {

    public SensorHumedad(String id){

        // Llamamos al constructor de Sensor (id, nombre)

        super(id, "Sensor de Humedad", "");
    }

    //Actualizamos el método de la clase padre leyendo el JSON

    @Override
    public void actualizarValor(){

        try{

            // Leemos completamente el archivo JSON

            String datosJson = new String(Files.readAllBytes(Paths.get("datos_sensores.json")));

            // Creamos un Array con los datos del JSON

            JSONArray listaDatos = new JSONArray((datosJson));

            // Recorremos los elementos del array buscando el valor deseado

            for (int i = 0; i <listaDatos.length(); i++){

                JSONObject objetoSensor = listaDatos.getJSONObject(i);

                // Comparamos el "sensor_id" del JSON con el ID de esta clase

                if (objetoSensor.getString("sensor_id").equals(this.id)){

                    // Asignamos el valor del JSON y la unidad de medida al valor y unidad de medida de esta clase

                    this.valor = objetoSensor.getDouble("value");
                    this.unidadMedida = objetoSensor.getString("unit");

                    // Al encontrarlo, rompemos el buble para que no siga iterando

                    break;
                }
            }

        } catch (Exception e){

            // Si hay algún error con el JSON como que no tenga datos correctos o no exista, recogemos el error y mostramos por pantalla

            System.out.println("Error al actualizar el sensor" + this.id + ": " + e.getMessage());
        }

    }

}