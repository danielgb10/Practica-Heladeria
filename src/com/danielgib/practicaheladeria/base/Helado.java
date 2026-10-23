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

    //constructores
    public Helado() {

    }

    public Helado(String sabor, String tipo, String tamano, LocalDate fechaCompra, double precio, int stock, boolean sinAzucar) {
        this.sabor = sabor;
        this.tipo = tipo;
        this.tamano = tamano;
        this.fechaCompra = fechaCompra;
        this.precio = precio;
        this.stock = stock;
        this.sinAzucar = sinAzucar;
    }

    //getters y setters
    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
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
