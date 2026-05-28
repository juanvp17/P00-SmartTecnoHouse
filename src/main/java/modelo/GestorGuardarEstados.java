package modelo;

import modelo.reglas.Regla;
import java.io.*;
import java.util.List;

public class GestorGuardarEstados {

    private static final String directorio_save = "src/main/java/save";
    private static final String ruta_archivo = directorio_save + "/estado_sistema.txt";

    // Método para crear el directorio si no existe

    private static void crearDirectorio(){

        File directorio = new File(directorio_save);

        // Si no existe esa ruta, creamos el directorio

        if (!directorio.exists()){
            directorio.mkdirs();
            System.out.println("Se ha creado el directorio de sace en: " + directorio_save);
        }
    }

    // Método para guardar el estado

    public static void guardarEstado (List<Regla> reglas, List<Actuador> actuadores){

        crearDirectorio();

        try(PrintWriter pw = new PrintWriter(new FileWriter(ruta_archivo))){

            // Aquí guardamos las reglas

            for (Regla r : reglas){
                pw.println(r.getClass().getSimpleName() + "=" + r.isActiva());
            }

            // Aquí guardamos los actuadores

            for (Actuador a : actuadores){
                pw.println(a.getID() + "=" + a.getEstadoActual());
            }

            System.out.println("Estado guardado correctamente al salir");

        } catch (IOException e){

            // Si se produce un error al guardar el estado, se muestra un mensaje por la consola

            System.out.println("Erro al guardar el estado: " + e.getMessage());
        }
    }

    // Método para cargar el estado

    public static void cargarEstado(List<Regla> reglas, List<Actuador> actuadores){

        crearDirectorio();

        File archivo = new File(ruta_archivo);

        // Si es la primera vez que se abre el programa, no hay que cargar  nada

        if(!archivo.exists()){

            return;
        }


        // Usamos una herramienta para leer archivos de texto línea a línea

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))){

            String linea;

            // Establecemos un bucle que, mientras la línea lída no esté en blanco, ejecutamos las acciones del bucle

            while ((linea = br.readLine()) !=null){
                // Separamos por el caracter "="

                String [] partes = linea.split("=");

                // Comprobamos que se ha dividido correctamente y asignamos cada "trozo" a una variable

                if (partes.length == 2){
                    String clave = partes[0];
                    String valor = partes[1];

                // Aquí recorremos las reglas una a una y, si hay coincidencia, establecemos el estado que hemos leído del archivo

                    for (Regla r : reglas){

                        if (r.getClass().getSimpleName().equals(clave)){

                            // Aquí convertimos el texto a tipo booleano para poder aplicarlo correctamente

                            boolean estadoGuardado = Boolean.parseBoolean(valor);
                            r.setActiva(estadoGuardado);
                        }
                    }

                    // Aquí recorremos los actuadores de uno en uno y, si hay coincidencia, establecemos el estado que hemos leído del archivo

                    for (Actuador a : actuadores){

                        if (a.getID().equals(clave)){

                            a.ejecutarAccion(valor);
                        }
                    }
                }

            }

            System.out.println("Estado cargado correctamente");

        } catch (IOException e){

            // Si se produce un error al cargar los datos, muestra un mensaje por consola

            System.out.println("Error al cargar el estado: " + e.getMessage());
        }
    }
}
