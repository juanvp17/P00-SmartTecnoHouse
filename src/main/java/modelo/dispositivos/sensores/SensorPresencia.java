package modelo.dispositivos.sensores;

//Importamos la clase padre porque está en un nivel superior

import modelo.Sensor;


//Imports para entender el formato JSON

import org.json.JSONArray;
import org.json.JSONObject;

// Clase concreta para el Sensor de Presencia. Aquí definimos el comportamiento de un tipo de sensor en concreto

public class SensorPresencia extends Sensor {

    public SensorPresencia(String id){

        // Llamamos al constructor de Sensor (id, nombre)

        super(id, "Sensor de Presencia", "");
    }

    //Actualizamos el método de la clase padre

    @Override
    public void actualizarValor(JSONArray datosJSON){

        // Invocamos al método para que nos traiga los datos del sensor

        JSONObject datos =buscarDatos(datosJSON);

        // Comprobamos que los datos no vengan vacíos. De ser así, evaluamos una condición para ver que los datos no sean irreales o desproporcionados (por ejemplo, 2 presence)

        if (datos!= null){

            double valorLeido = datos.getDouble("value");

            if (valorLeido >= 0.0 && valorLeido <= 1.0){
                this.valor = valorLeido;
                this.unidadMedida = datos.getString("unit");

                // Si los valores no cumplen el condicional, sacará un mensaje por pantalla

            } else {

                System.out.println("Error: Valores inválidos :" + valorLeido + datos.getString("sensor_id") + datos.getString("unit"));
            }
        }


        /**
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
         */

    }

}
