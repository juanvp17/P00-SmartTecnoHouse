package modelo.dispositivos.sensores;

//Importamos la clase padre porque está en un nivel superior

import modelo.Sensor;

//Imports para entender el formato JSON

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Clase concreta para el Sensor de Humedad.
 * Aquí definimos el comportamiento de un tipo de sensor en concreto
 */

public class SensorHumedad extends Sensor {

    public SensorHumedad(String id){

        // Llamamos al constructor de Sensor (id, nombre)

        super(id, "Sensor de Humedad", "");
    }

    //Actualizamos el método de la clase padre

    @Override
    public void actualizarValor(JSONArray datosJSON){

        // Invocamos al método para que nos traiga los datos del sensor

        JSONObject datos =buscarDatos(datosJSON);

        // Comprobamos que los datos no vengan vacíos. De ser así, evaluamos una condición para ver que los datos no sean irreales o desproporcionados (por ejemplo, 112 %)

        if (datos!= null){

            double valorLeido = datos.getDouble("value");

            if (valorLeido >= 0.0 && valorLeido <= 100.0){
                this.valor = valorLeido;
                this.unidadMedida = datos.getString("unit");

                // Si los valores no cumplen el condicional, sacará un mensaje por pantalla

            } else {

                System.out.println("Error: Valores inválidos :" + valorLeido + datos.getString("sensor_id") + datos.getString("unit"));
            }
        }
    }

}
