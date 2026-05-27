import modelo.Sensor;
import modelo.Actuador;
import modelo.LectorJSON;
import modelo.dispositivos.sensores.SensorTemperatura;
import modelo.dispositivos.actuadores.ActuadorBombilla;
import modelo.dispositivos.actuadores.ActuadorDeshumidificador;
import modelo.dispositivos.actuadores.ActuadorVentilador;
import modelo.dispositivos.sensores.SensorHumedad;
import modelo.dispositivos.sensores.SensorLuz;
import modelo.dispositivos.sensores.SensorPresencia;


//Imports para entender el formato JSON

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public class MainPrueba {

    public static void main(String[] args) {

        System.out.println("--- INICIANDO PRUEBA DEL SENSOR ---");

        //Instanciamos los sensores. Le pasamos los ID

        Sensor temp = new SensorTemperatura("temp");
        Sensor luz = new SensorLuz("luz");
        Sensor presencia = new SensorPresencia("pir");
        Sensor humedad = new SensorHumedad("wet");

        // Comprobamos su estado inicial. Como el constructor le pone 0.0 por defecto, debería imprimir 0.0

        System.out.println("Estado inicial: " + temp.getEstadoActual());
        System.out.println("Estado inicial: " + luz.getEstadoActual());
        System.out.println("Estado inicial: " + presencia.getEstadoActual());
        System.out.println("Estado inicial: " + humedad.getEstadoActual());

        //Ejecutamos el método que abre el archivo JSON y busca su valor

        System.out.println("Leyendo el archivo JSON...");
        JSONArray arrayDatos = LectorJSON.leerDatosSensores();

        temp.actualizarValor(arrayDatos);
        luz.actualizarValor(arrayDatos);
        presencia.actualizarValor(arrayDatos);
        humedad.actualizarValor(arrayDatos);


        //Comprobamos el resultado. Debería imprimir el valor + tipo de unidad

        System.out.println("Estado tras leer el JSON: " + temp.getEstadoActual());
        System.out.println("Estado tras leer el JSON: " + luz.getEstadoActual());
        System.out.println("Estado tras leer el JSON: " + presencia.getEstadoActual());
        System.out.println("Estado tras leer el JSON: " + humedad.getEstadoActual());

        System.out.println("--- FIN DE LA PRUEBA ---");
/**
 *

        System.out.println("--- INICIANDO PRUEBA DEL ACTUADOR ---");

        //Instanciamos los actuadores. Le pasamos los ID

        Actuador ventilador = new ActuadorVentilador("fan");
        Actuador bombilla = new ActuadorBombilla("bulb");
        Actuador deshumi = new ActuadorDeshumidificador("desh");

        // Comprobamos su estado inicial

        System.out.println("Estado inicial: " + ventilador.getEstadoActual());
        System.out.println("Estado inicial: " + bombilla.getEstadoActual());
        System.out.println("Estado inicial: " + deshumi.getEstadoActual());

        //Mostramos las acciones que puede realiar

        System.out.println("Acciones disponibles: " + Arrays.toString(ventilador.getAccionesPosibles()));
        System.out.println("Acciones disponibles: " + Arrays.toString(bombilla.getAccionesPosibles()));
        System.out.println("Acciones disponibles: " + Arrays.toString(deshumi.getAccionesPosibles()));


        //Le indicamos la acción que queremos que tome

        ventilador.ejecutarAccion("HIGH");
        bombilla.ejecutarAccion("ON");
        deshumi.ejecutarAccion("ON");

        // Mostramos el estado actual de los actuadores

        System.out.println("Estado actual: " + ventilador.getEstadoActual());
        System.out.println("Estado actual: " + bombilla.getEstadoActual());
        System.out.println("Estado actual: " + deshumi.getEstadoActual());

*/


    }
}