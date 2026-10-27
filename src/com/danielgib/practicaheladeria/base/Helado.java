package com.danielgib.practicaheladeria.base;

import java.time.LocalDate;

public abstract class Helado {
    //atributos
    private String sabor;
    private String toppings;
    private LocalDate fechaCompra;
    private double precio;
    private int stock;
    private boolean sinAzucar;
    private int numBolas;

    public Helado() {

    }

    public Helado(String sabor, String toppings, LocalDate fechaCompra, double precio, int stock, boolean sinAzucar, int numBolas) {
        this.sabor = sabor;
        this.toppings = toppings;
        this.fechaCompra = fechaCompra;
        this.precio = precio;
        this.stock = stock;
        this.sinAzucar = sinAzucar;
        this.numBolas = numBolas;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isSinAzucar() {
        return sinAzucar;
    }

    public void setSinAzucar(boolean sinAzucar) {
        this.sinAzucar = sinAzucar;
    }

    public int getNumBolas() {
        return numBolas;
    }

    public void setNumBolas(int numBolas) {
        this.numBolas = numBolas;
    }
}
