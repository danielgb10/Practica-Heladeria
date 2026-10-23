package com.danielgib.practicaheladeria;

import com.danielgib.practicaheladeria.gui.HeladoControlador;
import com.danielgib.practicaheladeria.gui.HeladoModelo;
import com.danielgib.practicaheladeria.gui.Ventana;

public class Principal {
    private Ventana vista = new Ventana();
    private HeladoModelo modelo = new HeladoModelo();
    private HeladoControlador controlador = new HeladoControlador(vista, modelo);


}
