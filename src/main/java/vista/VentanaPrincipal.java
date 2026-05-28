package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Clase de la Ventana del programa
 * Se encarga de dibujar en la aplicación los datos y cambios en tiempo real
 */

public class VentanaPrincipal extends JFrame {

    /** Estos son los componentes visuales
     * Sensores
     */

    private JLabel lblTemperatura;
    private JLabel lblLuz;
    private JLabel lblPresencia;
    private JLabel lblHumedad;

    // Reglas, su estado y botón para reactivarlas

    private JLabel lblEstadoReglaVent;
    private JLabel lblEstadoReglaHum;
    private JLabel lblEstadoReglaLuz;
    private JButton btnReactivarVent;
    private JButton btnReactivarHum;
    private JButton btnReactivarLuz;

    // Botones de los actuadores (control manual

    private JButton btnVentOff;
    private JButton btnVentLow;
    private JButton btnVentMedium;
    private JButton btnVentHigh;
    private JButton btnLuzOn;
    private JButton btnLuzOff;
    private JButton btnHumiOn;
    private JButton btnHumiOff;

    // Botón para recargar los datos

    private JButton btnCicloAutomatico;

    public VentanaPrincipal(){

        //Configuración de la ventana

        setTitle("Panel de Control - SmartTenoHouse");

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);

        // Fila de los sensores

        JPanel panelSensores = new JPanel(new GridLayout(2,2,10,10));
        panelSensores.setBorder(BorderFactory.createTitledBorder("Estado de los Sensores"));
        lblTemperatura = new JLabel("Temperatura: -- ºC");
        lblLuz = new JLabel("Luz detectada: -- lux");
        lblPresencia= new JLabel("Presencia detectada: --");
        lblHumedad = new JLabel("Humedad ambiente: -- %");

        panelSensores.add(lblTemperatura);
        panelSensores.add(lblLuz);
        panelSensores.add(lblPresencia);
        panelSensores.add(lblHumedad);
        add(panelSensores);

        // Fila del estado de las Reglas

        JPanel panelReglas = new JPanel(new GridLayout(3,2,10,10));
        panelReglas.setBorder(BorderFactory.createTitledBorder("Estado de las Reglas Automáticas"));

        lblEstadoReglaVent = new JLabel("Regla ventilación automática: --");
        btnReactivarVent = new JButton("Reactivar Auto");

        lblEstadoReglaLuz = new JLabel("Regla Iluminación automática: --");
        btnReactivarLuz = new JButton("Reactivar Auto");

        lblEstadoReglaHum = new JLabel("Regla Deshumidificador automático: --");
        btnReactivarHum = new JButton("Reactivar Auto");

        panelReglas.add(lblEstadoReglaVent);
        panelReglas.add(btnReactivarVent);

        panelReglas.add(lblEstadoReglaLuz);
        panelReglas.add(btnReactivarLuz);

        panelReglas.add(lblEstadoReglaHum);
        panelReglas.add(btnReactivarHum);

        add(panelReglas);


        // Fila Actuadores (Modo manual)

        JPanel panelActuadores = new JPanel(new GridLayout(3,1,5,5));
        panelActuadores.setBorder(BorderFactory.createTitledBorder("Control Manual"));

        // Ventilador
        JPanel panelVentilador = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelVentilador.add(new JLabel("Ventilador: "));
        btnVentOff = new JButton("OFF");
        btnVentLow = new JButton("LOW");
        btnVentMedium = new JButton("MEDIUM");
        btnVentHigh = new JButton("HIGH");
        panelVentilador.add(btnVentOff);
        panelVentilador.add(btnVentLow);
        panelVentilador.add(btnVentMedium);
        panelVentilador.add(btnVentHigh);
        panelActuadores.add(panelVentilador);

        // Bombilla
        JPanel panelBombilla = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBombilla.add(new JLabel("Bombilla: "));
        btnLuzOff = new JButton("OFF");
        btnLuzOn = new JButton("ON");
        panelBombilla.add(btnLuzOff);
        panelBombilla.add(btnLuzOn);
        panelActuadores.add(panelBombilla);

        // Deshumidificador

        JPanel panelDeshumi = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDeshumi.add(new JLabel("Deshumidificador: "));
        btnHumiOff = new JButton("OFF");
        btnHumiOn = new JButton("ON");
        panelDeshumi.add(btnHumiOff);
        panelDeshumi.add(btnHumiOn);
        panelActuadores.add(panelDeshumi);


        add(panelActuadores);

        // Sistema automático

        JPanel panelSistema = new JPanel();
        btnCicloAutomatico = new JButton("Actualizar");
        panelSistema.add(btnCicloAutomatico);
        add(panelSistema);

    }

    // Para que el controlador pueda escuchar los clicks de los botones

    public JButton getBtnCicloAutomatico(){
        return btnCicloAutomatico;
    }

    public JButton getBtnReactivarVent(){
        return btnReactivarVent;
    }
    public JButton getBtnReactivarHum(){
        return btnReactivarHum;
    }
    public JButton getBtnReactivarLuz(){
        return btnReactivarLuz;
    }
    public JButton getBtnVentOff(){
        return btnVentOff;
    }
    public JButton getBtnVentLow(){
        return btnVentLow;
    }
    public JButton getBtnVentMedium(){
        return btnVentMedium;
    }
    public JButton getBtnVentHigh(){
        return btnVentHigh;
    }

    public JButton getBtnLuzOn(){
        return btnLuzOn;
    }
    public JButton getBtnLuzOff(){
        return btnLuzOff;
    }
    public JButton getBtnHumiOn(){
        return btnHumiOn;
    }
    public JButton getBtnHumiOff(){
        return btnHumiOff;
    }

    // Métodos para actualizar sensores

    public void actualizarTemperatura(String texto){
        lblTemperatura.setText("Temperatura: " + texto);
    }
    public void actualizarLuz(String texto){
        lblLuz.setText("Luz: " + texto);
    }
    public void actualizarPresencia(String texto){
        lblPresencia.setText("Presencia: " + texto);
    }
    public void actualizarHumedad(String texto){
        lblHumedad.setText("Humedad: " + texto);
    }

    // Métodos para actualizar actuadores (botones)

    public void actualizarReglaVent(boolean act){
        lblEstadoReglaVent.setText("Regla de Ventilación automática: " + (act ? "AUTO" : "MANUAL"));
    }
    public void actualizarReglaLuz(boolean act){
        lblEstadoReglaLuz.setText("Regla de Iluminación automática automática: " + (act ? "AUTO" : "MANUAL"));
    }
    public void actualizarReglaHum(boolean act){
        lblEstadoReglaHum.setText("Regla de Deshumidificación automática: " + (act ? "AUTO" : "MANUAL"));
    }

    // Métodos para colorear el botón que está activo actualmente

    // Botones del ventilador
    public void marcarVentilador (String est){

        btnVentOff.setBackground(est.equals("OFF") ? Color.LIGHT_GRAY : null);
        btnVentLow.setBackground(est.equals("LOW") ? Color.CYAN : null);
        btnVentMedium.setBackground(est.equals("MEDIUM") ? Color.CYAN : null);
        btnVentHigh.setBackground(est.equals("HIGH") ? Color.CYAN : null);
    }

    public void marcarBombilla (String est){

        btnLuzOn.setBackground(est.equals("ON") ? Color.YELLOW : null);
        btnLuzOff.setBackground(est.equals("OFF") ? Color.LIGHT_GRAY : null);
    }

    public void marcarDeshumidificador (String est){
        btnHumiOn.setBackground(est.equals("ON") ? Color.ORANGE : null);
        btnHumiOff.setBackground(est.equals("OFF") ? Color.LIGHT_GRAY : null);
    }


}
