package com.danielgib.practicaheladeria.base;

import java.time.LocalDate;

public class HeladoCono extends Helado{
    private int numBolas;

    public HeladoCono() {

    }

    public HeladoCono(String tipo, String tamano, String sabor, LocalDate fechaCompra, double precio, int stock, boolean sinAzucar, int numBolas) {
        super(tipo, tamano, sabor, fechaCompra, precio, stock, sinAzucar);
        this.numBolas = numBolas;
    }

    public int getNumBolas() {
        return numBolas;
    }

    public void setNumBolas(int numBolas) {
        this.numBolas = numBolas;
    }

    @Override
    public String toString() {
        return "Numero Bolas: " + numBolas;
    }
}
