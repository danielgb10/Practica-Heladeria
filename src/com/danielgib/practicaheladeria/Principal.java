package com.danielgib.practicaheladeria;


import com.danielgib.practicaheladeria.gui.HeladoControlador;
import com.danielgib.practicaheladeria.gui.HeladoModelo;
import com.danielgib.practicaheladeria.gui.Ventana;

public class Principal {
    public static void main(String[] args) {
        Ventana vista = new Ventana();
        HeladoModelo modelo = new HeladoModelo();
        HeladoControlador controlador = new HeladoControlador(vista, modelo);
    }

}
