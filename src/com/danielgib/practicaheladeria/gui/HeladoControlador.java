package com.danielgib.practicaheladeria.gui;

import com.danielgib.practicaheladeria.base.Helado;

import java.io.File;

public class HeladoControlador {
    private Ventana vista;
    private HeladoModelo modelo;
    private  File ultimaRutaExportacion;

    public HeladoControlador(Ventana vista, HeladoModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    private boolean hayCamposVacios() {
        if(vista.tamanoBox.getSelectedItem().equals("") ||  //corregir
                vista.toppingsTxt.getText().isEmpty() ||
                vista.fechaCompraDatePicker.getText().isEmpty() ||
                vista.precioTxt.getText().isEmpty() ||
                vista.conAzucarRadioButton.getText().isEmpty() ||
                vista.sinAzucarRadioButton.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private void limpiarCampos() {
        vista.toppingsTxt.setText(null);
        vista.fechaCompraDatePicker.setText(null);
        vista.precioTxt.setText(null);
    }

    public void refrescar() {
        vista.dlmHelado.clear();
        for (Helado unHelado : modelo.obtenerHelados()) {
            vista.dlmHelado.addElement(unHelado);
        }
    }


}
