package com.danielgib.practicaheladeria.base;

import java.time.LocalDate;

public class HeladoTarrina extends Helado {
    public String tamano;

    public HeladoTarrina(String sabor, String toppings, LocalDate fechaCompra, double precio, String s, boolean sinAzucar, int numBolas, String tamano) {
        super();
    }

    public HeladoTarrina(String sabor, String toppings, LocalDate fechaCompra, double precio, int stock, boolean sinAzucar, int numBolas, String tamano) {
        super(sabor, toppings, fechaCompra, precio, stock, sinAzucar, numBolas);
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
