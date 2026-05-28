package modelo;

//Imports necesarios para abrir y leer el archivo

import java.nio.file.Files;
import java.nio.file.Paths;

//Imports para entender el formato JSON

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Clase encargada de leer datos
 * Lee los datos proporcionados por un JSON
 */

public class LectorJSON {

    // Creamos un método estático para poder llamarlo sin tener que crear un objeto

    public static JSONArray leerDatosSensores(){

        try{
            // Leemos completamente el archivo JSON

            String datosJSON = new String(Files.readAllBytes(Paths.get("datos_sensores.json")));

            // Devolvemos la lectura de los datos

            return new JSONArray(datosJSON);

        } catch (Exception e){

            // Si hay algún error con el JSON como que no tenga datos correctos o no exista, recogemos el error y mostramos por pantalla

            System.out.println("Error al lerr el archivo JSON" + e.getMessage());

            // Si hay un error, devolvemos un array vacío para evitar que el programa colapse

            return new JSONArray();
        }
    }
}
