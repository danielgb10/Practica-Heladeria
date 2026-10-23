package com.danielgib.practicaheladeria.gui;

public class HeladoControlador {
    private Ventana vista;
    private HeladoModelo modelo;

    public HeladoControlador(Ventana vista, HeladoModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
}
