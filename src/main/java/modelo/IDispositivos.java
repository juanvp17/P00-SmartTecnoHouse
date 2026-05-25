package modelo;

 // Interfaz IDispositivos

public interface IDispositivos {

    // Obtiene el identificador único de cada dispositivo

    String getID();

    // Obtiene el nombre del dispositivo

    String getNombre();

    // Obtiene el estado actual del dispositivo en formato texto

    String getEstadoActual();
}
