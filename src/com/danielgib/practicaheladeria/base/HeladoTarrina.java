package com.danielgib.practicaheladeria.base;

import java.time.LocalDate;

public class HeladoTarrina extends Helado {
    public String tamano;

    public HeladoTarrina() {
        super();
    }

    public HeladoTarrina(String tamano, String sabor, int numBolas, String toppings, LocalDate fechaCompra, int precio, boolean azucar) {
        super(sabor, numBolas, toppings, fechaCompra, precio, azucar);
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
        return "Tarrina - " + getTamano() + " (" + getSabor() + ", " + getToppings() + ")" + " - " + getPrecio() + "€";

    }
}
