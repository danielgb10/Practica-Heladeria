package com.danielgib.practicaheladeria.base;

import java.time.LocalDate;

public class HeladoCono extends Helado{
    public String tipoCono; //chocolate, barquillo...

    public HeladoCono() {
        super();
    }

    public HeladoCono(String sabor, String toppings, LocalDate fechaCompra, double precio, boolean sinAzucar, int numBolas, String tipoCono) {
        super(sabor, numBolas, toppings, fechaCompra, precio, sinAzucar);
        this.tipoCono = tipoCono;
    }

    public String getTipoCono() {
        return tipoCono;
    }

    public void setTipoCono(String tipoCono) {
        this.tipoCono = tipoCono;
    }

    @Override
    public String toString() {
        return "TipoCono: " + tipoCono;
    }
}
