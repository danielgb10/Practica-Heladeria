package com.danielgib.practicaheladeria.base;

import java.time.LocalDate;

public class HeladoTarrina extends Helado {
    public String tamano;

    public HeladoTarrina() {
        super();
    }

    public HeladoTarrina(String sabor, String toppings, LocalDate fechaCompra, double precio, boolean sinAzucar, int numBolas, String tamano) {
        super(sabor, numBolas, toppings, fechaCompra, precio, sinAzucar);
        this.tamano = tamano;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    @Override
    public String toString() {
        return "Tamaño: " + tamano ;
    }
}
