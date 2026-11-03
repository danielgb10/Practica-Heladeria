package com.danielgib.practicaheladeria.base;

import java.time.LocalDate;

public class HeladoCono extends Helado{
    public String tipoCono; //chocolate, barquillo...

    public HeladoCono() {
        super();
    }

    public HeladoCono(String tipoCono, String sabor, int numBolas, String toppings, LocalDate fechaCompra, int precio, boolean azucar) {
        super(sabor, numBolas, toppings, fechaCompra, precio, azucar);
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
        return "Cono - " + getTipoCono() + " (" + getSabor() + ", " + getToppings() + ")" + " - " + getPrecio() + "€";
    }
}
