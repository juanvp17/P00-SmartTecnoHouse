package modelo;

// Importamos todas las clases


import modelo.dispositivos.actuadores.ActuadorBombilla;
import modelo.dispositivos.actuadores.ActuadorDeshumidificador;
import modelo.dispositivos.actuadores.ActuadorVentilador;
import modelo.dispositivos.sensores.SensorHumedad;
import modelo.dispositivos.sensores.SensorLuz;
import modelo.dispositivos.sensores.SensorPresencia;
import modelo.dispositivos.sensores.SensorTemperatura;
import modelo.reglas.*;
import modelo.dispositivos.actuadores.*;
import modelo.dispositivos.sensores.*;

//Imports para entender el formato JSON

import modelo.reglas.reglas_especificas.DeshumidificarAutomaticamente;
import modelo.reglas.reglas_especificas.IluminacionAutomatica;
import modelo.reglas.reglas_especificas.VentilacionConfortable;
import org.json.JSONArray;
import org.json.JSONObject;

// Importamos la clase LIST y ArrayList para manejar colecciones y guardar los datos

import java.util.ArrayList;
import java.util.List;


/**
 * Clase principal del Modelo
 * Contiene el estado completo de la casa inteligente
 */


public class SmartTecnoHouse {

    // Declaramos una variable estática que instanciará en memoria la "casa"

    private static SmartTecnoHouse casaInteligente;

    // Aquí listamos nuestros sensores, actuadores y reglas en listas

    private List<Sensor> sensores;
    private List<Actuador> actuadores;
    private List<Regla> reglas;

    // Creamos un constructor privado

    private SmartTecnoHouse(){

        // Creamos listas vacías

        this.sensores = new ArrayList<>();
        this.actuadores = new ArrayList<>();
        this.reglas = new ArrayList<>();



        iniciarCasa();

        // Aqui cargamos los datos anteriores

        GestorGuardarEstados.cargarEstado(this.reglas, this.actuadores);

    }

    // Creamos un método que hace de intermediario entre todas las clases

    public static SmartTecnoHouse getInstance(){

        if (casaInteligente == null){
            casaInteligente = new SmartTecnoHouse();
        }

        return casaInteligente;
    }

    // Creamos los objetos físicos

    private void iniciarCasa(){

        System.out.println("[SISTEMA] Arrancando...");

        // Añadimos sensores, actuadores y reglas

        sensores.add(new SensorTemperatura("temp"));
        sensores.add(new SensorLuz("luz"));
        sensores.add(new SensorPresencia("pir"));
        sensores.add(new SensorHumedad("wet"));

        actuadores.add(new ActuadorVentilador("fan"));
        actuadores.add(new ActuadorBombilla("bulb"));
        actuadores.add(new ActuadorDeshumidificador("desh"));

        reglas.add(new VentilacionConfortable());
        reglas.add(new IluminacionAutomatica());
        reglas.add(new DeshumidificarAutomaticamente());
    }

    // Creamos un método que actualiza todos los datos, reglas y demás

    public void ejecutarCiclo(){

        System.out.println("--- INICIANDO ---");

        // Leemos el JSON del disco duro una sola vez
        JSONArray datosJSON = LectorJSON.leerDatosSensores();

        // Asignamos los valores a todos los sensores en la lista

        for (Sensor s : sensores){
            s.actualizarValor(datosJSON);
        }

        // Ejecutamos las reglas

        for (Regla r : reglas){
            r.aplicar(this.sensores, this.actuadores);
        }

        System.out.println("--- FIN DEL CICLO---");
    }

    // Para ver cómo están los sensores y actuadores

    public List<Sensor> getSensores(){

        return sensores;
    }

    public List<Actuador> getActuadores(){

        return actuadores;
    }

    public List<Regla> getReglas(){

        return reglas;
    }

    // Método para guardar el estado al cerrar la ventana

    public void guardarEstadoSistema(){

        GestorGuardarEstados.guardarEstado(this.reglas, this.actuadores);
    }

}
