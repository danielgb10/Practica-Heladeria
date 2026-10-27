package com.danielgib.practicaheladeria.base;

import java.time.LocalDate;

public class HeladoCono extends Helado{
    public String tipoCono; //chocolate, barquillo...

    public HeladoCono(String sabor, String toppings, LocalDate fechaCompra, double precio, String s, boolean sinAzucar, int numBolas, String tipoCono) {
        super();
    }

    public HeladoCono(String sabor, String toppings, LocalDate fechaCompra, double precio, int stock, boolean sinAzucar, int numBolas, String tipoCono) {
        super(sabor, toppings, fechaCompra, precio, stock, sinAzucar, numBolas);
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
