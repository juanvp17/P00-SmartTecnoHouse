package modelo.reglas;

import modelo.Sensor;
import modelo.Actuador;

// Importamos la clase LIST para manejar colecciones

import java.util.List;

//Definiremos la estructura que todas las reglas deben cumplir

public interface Regla {

    //Recibimos una lista completa de sensores y actuadores para poder leer datos y ejecutar las reglas

    void aplicar(List<Sensor> sensores, List<Actuador> actuadores);

    /**
     * Métodos para el modo manual/automático
     * El usuario debe poder activar y desactivar las reglas a voluntad
     */
    boolean isActiva();
    void setActiva(boolean activa);

}
