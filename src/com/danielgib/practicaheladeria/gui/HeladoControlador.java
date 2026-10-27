package com.danielgib.practicaheladeria.gui;

import java.io.File;

public class HeladoControlador {
    private Ventana vista;
    private HeladoModelo modelo;
    private  File ultimaRutaExportacion;

    public HeladoControlador(Ventana vista, HeladoModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
    }
}
