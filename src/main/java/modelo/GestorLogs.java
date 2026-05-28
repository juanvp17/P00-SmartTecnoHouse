package modelo;

// Estos imports nos permiten comprobar si existe el archivo de logs, crearlo y escribir sobre él

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase encargada de los Logs
 * Se encarga de guardar un .log cada vez que hay un cambio en los actuadores
 */

public class GestorLogs {

    private static GestorLogs instancia;

    // Aquí definimos las rutas exactas donde se encontrará el archivo actuators.log

    private static final String directorio_logs = "src/main/java/logs";
    private static final String ruta_archivo = directorio_logs + "/actuators.log";

    private GestorLogs(){

        // Nos aseguramos que la carpeta exista

        crearDirectorio();
    }
    // Método para crear una única instancia

    public static GestorLogs getInstancia(){
        if (instancia == null){
            instancia = new GestorLogs();
        }
        return instancia;
    }

    // Método para crear el directorio si no existe

    private void crearDirectorio(){

        File directorio = new File(directorio_logs);

        //Si no existe en esa ruta, creamos el directorio

        if(!directorio.exists()){
            directorio.mkdirs();
            System.out.println("Se ha creado en directorio de logs en: " + directorio_logs);
        }

    }

    // Método para escribir en el archivo de texto

    public void registrarLog(String nombreActuador, String nuevaAccion){

        // Con este comando intentamos añadir el texto al final (como un append)

        try (FileWriter fw = new FileWriter(ruta_archivo, true); PrintWriter pw = new PrintWriter(fw)) {

            // Creamos la fecha actual con el formato de dd/mm/yy y hh/mm/ss

            String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

            pw.println("[" + fechaHora + "] INFO - Actuador '" + nombreActuador + "' cambió su estado a: " + nuevaAccion);

        } catch (IOException e){
            // Si se produce un error al escribir, muestra un error en la consola

            System.out.println("Error al escribir en el archivo log: " + e.getMessage());
        }
    }
}
