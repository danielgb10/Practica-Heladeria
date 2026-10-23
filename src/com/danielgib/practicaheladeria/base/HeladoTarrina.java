package com.danielgib.practicaheladeria.base;

import java.time.LocalDate;

public class HeladoTarrina extends Helado {
    public String tamano;

    public HeladoTarrina() {

    }

    public HeladoTarrina(String tipo, String tamano, String sabor, LocalDate fechaCompra, double precio, int stock, boolean sinAzucar, String tamano1) {
        super(tipo, tamano, sabor, fechaCompra, precio, stock, sinAzucar);
        this.tamano = tamano1;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    @Override
    public String toString() {
        return "Tamaño: " + tamano;
    }
}
