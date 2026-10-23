package com.danielgib.practicaheladeria.base;

import java.time.LocalDate;

public class Helado {
    //atributos
    private String tipo;
    private String tamano; //enum
    private String sabor; //enum
    private LocalDate fechaCompra;
    private double precio;
    private int stock;
    private boolean sinAzucar;

    public Helado() {

    }

    public Helado(String tipo, String tamano, String sabor, LocalDate fechaCompra, double precio, int stock, boolean sinAzucar) {
        this.tipo = tipo;
        this.tamano = tamano;
        this.sabor = sabor;
        this.fechaCompra = fechaCompra;
        this.precio = precio;
        this.stock = stock;
        this.sinAzucar = sinAzucar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
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


}
