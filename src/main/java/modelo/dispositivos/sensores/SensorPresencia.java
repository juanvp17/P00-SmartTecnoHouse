package modelo.dispositivos.sensores;

//Importamos la clase padre porque está en un nivel superior

import modelo.Sensor;

//Imports necesarios para abrir y leer el archivo

import java.nio.file.Files;
import java.nio.file.Paths;

//Imports para entender el formato JSON

import org.json.JSONArray;
import org.json.JSONObject;

// Clase concreta para el Sensor de Presencia

public class SensorPresencia extends Sensor {

    public SensorPresencia(String id){

        super(id, "Sensor de Presencia", "");
    }

    @Override
    public void actualizarValor(){

        try{

            String datosJson = new String(Files.readAllBytes(Paths.get("datos_sensores.json")));

            JSONArray listaDatos = new JSONArray((datosJson));

            for (int i = 0; i <listaDatos.length(); i++){

                JSONObject objetoSensor = listaDatos.getJSONObject(i);

                if (objetoSensor.getString("sensor_id").equals(this.id)){

                    this.valor = objetoSensor.getDouble("value");
                    this.unidadMedida = objetoSensor.getString("unit");

                    break;
                }
            }

        } catch (Exception e){

            System.out.println("Error al actualizar el sensor" + this.id + ": " + e.getMessage());
        }

    }

}
