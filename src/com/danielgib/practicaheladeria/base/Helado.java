package com.danielgib.practicaheladeria.base;

import java.time.LocalDate;

public abstract class Helado {
    //atributos
    private String sabor;
    private int numBolas;
    private String toppings;
    private LocalDate fechaCompra;
    private int precio;
    private boolean azucar;

    public Helado() {

    }

    public Helado(String sabor, int numBolas, String toppings, LocalDate fechaCompra, int precio, boolean azucar) {
        this.sabor = sabor;
        this.numBolas = numBolas;
        this.toppings = toppings;
        this.fechaCompra = fechaCompra;
        this.precio = precio;
        this.azucar = azucar;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public int getNumBolas() {
        return numBolas;
    }

    public void setNumBolas(int numBolas) {
        this.numBolas = numBolas;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean getAzucar() {
        return azucar;
    }

    public void setAzucar(boolean azucar) {
        this.azucar = azucar;
    }
}
