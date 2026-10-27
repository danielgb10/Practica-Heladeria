package com.danielgib.practicaheladeria.gui;

import com.danielgib.practicaheladeria.base.Helado;
import com.danielgib.practicaheladeria.base.HeladoCono;
import com.danielgib.practicaheladeria.base.HeladoTarrina;

import java.time.LocalDate;
import java.util.ArrayList;

public class HeladoModelo {
    private ArrayList<Helado> listaHelados;

    public HeladoModelo() {
        this.listaHelados = new ArrayList<>();
    }

    public void altaHeladoCono(String sabor, String toppings, LocalDate fechaCompra, double precio, int stock, boolean sinAzucar, int numBolas, String tipoCono) {
        HeladoCono nuevoHeladoCono = new HeladoCono(sabor, toppings, fechaCompra, precio, sabor, sinAzucar, numBolas, tipoCono);
        listaHelados.add(nuevoHeladoCono);
    }

    public void altaHeladoTarrina(String sabor, String toppings, LocalDate fechaCompra, double precio, int stock, boolean sinAzucar, int numBolas, String tamano) {
        HeladoTarrina nuevoHeladoTarrina = new HeladoTarrina(sabor, toppings, fechaCompra, precio, sabor, sinAzucar, numBolas, tamano);
        listaHelados.add(nuevoHeladoTarrina);
    }

}
