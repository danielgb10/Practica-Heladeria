package com.danielgib.practicaheladeria.gui;

import com.danielgib.practicaheladeria.base.Helado;
import com.danielgib.practicaheladeria.base.HeladoCono;
import com.danielgib.practicaheladeria.base.HeladoTarrina;
import org.xml.sax.SAXException;
import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

public class HeladoControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private HeladoModelo modelo;
    private  File ultimaRutaExportada;

    public HeladoControlador(Ventana vista, HeladoModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        try {
            cargarDatosConfiguracion();
            precioAleatorio();
            max3Bolas();

        } catch (IOException e) {
            System.out.println("No se encontro el archivo " + e.getMessage());
        }
        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
    }

    private boolean hayCamposVacios() {
        if (vista.tamanoBox.getSelectedIndex() == -1 ||
                vista.toppingsTxt.getText().isEmpty() ||
                vista.fechaCompraDatePicker.getText().isEmpty() ||
                vista.precioTxt.getText().isEmpty()) {

            return true;
        }

        return false;
    }

    private void limpiarCampos() {
        vista.tamanoBox.setSelectedIndex(0);
        vista.saborBox.setSelectedIndex(0);
        vista.numBolasInt.setValue(0);
        vista.toppingsTxt.setText(null);
        vista.fechaCompraDatePicker.setText(null);
        vista.precioTxt.setText(null);
        vista.conAzucarRadioButton.doClick(0);
        vista.sinAzucarRadioButton.doClick(0);
    }

    public void refrescar() {
        vista.dlmHelado.clear();
        for (Helado unHelado : modelo.obtenerHelados()) {
            vista.dlmHelado.addElement(unHelado);
        }
    }

    private void addActionListener(ActionListener listener) {
        vista.nuevoButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.tarrinaRadioButton.addActionListener(listener);
        vista.conoRadioButton.addActionListener(listener);
        vista.editarButton.addActionListener(listener);
        vista.eliminarButton.addActionListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("helados.conf"));
        ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

    private void guardarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("helados.conf"),"Datos configuracion helados");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo Helado":
                if (hayCamposVacios()) {
                    Util.mensajeError("Error: Los siguientes campos no pueden " +
                            "estar vacios:\n" + vista.tamanoLbl.getText() + "\nSabor\nNumero Bolas\nToppings" +
                            "\nFecha Compra\n");
                    break;
                }

                if(modelo.existeSabor(vista.saborBox.getSelectedItem().toString())) {
                    Util.mensajeError("Ya existe un helado con ese sabor");
                    break;
                }

                if(vista.tarrinaRadioButton.isSelected()) {
                    modelo.altaHeladoTarrina(
                            vista.tamanoBox.getSelectedItem().toString(),
                            vista.saborBox.getSelectedItem().toString(),
                            Integer.parseInt(vista.numBolasInt.getValue().toString()),
                            vista.toppingsTxt.getText(),
                            vista.fechaCompraDatePicker.getDate(),
                            Integer.parseInt(vista.precioTxt.getText()),
                            vista.conAzucarRadioButton.isSelected());
                            vista.sinAzucarRadioButton.isSelected();

                } else {
                    modelo.altaHeladoCono(
                            vista.tamanoBox.getSelectedItem().toString(),
                            vista.saborBox.getSelectedItem().toString(),
                            Integer.parseInt(vista.numBolasInt.getValue().toString()),
                            vista.toppingsTxt.getText(),
                            vista.fechaCompraDatePicker.getDate(),
                            Integer.parseInt(vista.precioTxt.getText()),
                            vista.conAzucarRadioButton.isSelected());
                    }
                limpiarCampos();
                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada, "Archivos XML", "xml");
                int opt = selectorFichero.showOpenDialog(null);
                if(opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                    refrescar();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportada, "Archivos XML", "xml");
                int opt2 = selectorFichero2.showSaveDialog(null);
                if(opt2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                refrescar();
                break;
            case "Tarrina":
                vista.tamanoLbl.setText("Tamaño");
                vista.tamanoBox.removeAllItems();
                vista.tamanoBox.addItem("");
                vista.tamanoBox.addItem("Pequeño");
                vista.tamanoBox.addItem("Mediano");
                vista.tamanoBox.addItem("Grande");
                break;

            case "Cono":
                vista.tamanoLbl.setText("Tipo Cono");
                vista.tamanoBox.removeAllItems();
                vista.tamanoBox.addItem("");
                vista.tamanoBox.addItem("Chocolate");
                vista.tamanoBox.addItem("Galleta");
                vista.tamanoBox.addItem("Barquillo");
                break;

            case "Editar":
                int posicion = vista.list1.getSelectedIndex();

                if (posicion < 0) {
                    Util.mensajeError("Porfavor, seleccione un Tipo de Helado");
                    break;
                }

                Helado modificar;
                if(vista.tarrinaRadioButton.isSelected()) {
                    modificar = new HeladoTarrina(vista.tamanoBox.getSelectedItem().toString(),
                            vista.saborBox.getSelectedItem().toString(),
                            Integer.parseInt(vista.numBolasInt.getValue().toString()),
                            vista.toppingsTxt.getText(),
                            vista.fechaCompraDatePicker.getDate(),
                            Integer.parseInt(vista.precioTxt.getText()),
                            vista.conAzucarRadioButton.isSelected());
                            vista.sinAzucarRadioButton.isSelected();
                } else {
                    modificar = new HeladoCono(vista.tamanoBox.getSelectedItem().toString(),
                            vista.saborBox.getSelectedItem().toString(),
                            Integer.parseInt(vista.numBolasInt.getValue().toString()),
                            vista.toppingsTxt.getText(),
                            vista.fechaCompraDatePicker.getDate(),
                            Integer.parseInt(vista.precioTxt.getText()),
                            vista.conAzucarRadioButton.isSelected());
                    vista.sinAzucarRadioButton.isSelected();
                }
                modelo.editarHelado(posicion, modificar);
                refrescar();
                break;

            case "Eliminar":
                    Helado eliminar = (Helado) vista.list1.getSelectedValue();

                    if(eliminar == null) {
                        Util.mensajeError("Porfavor, elige un helado");
                        break;
                    }

                    modelo.eliminarHelado(eliminar);
                    limpiarCampos();
                    refrescar();
                break;
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("¿Deseas cerrar la ventana?","Salir");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                guardarDatosConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()) {
            Helado heladoSeleccionado = (Helado) vista.list1.getSelectedValue();

            if (heladoSeleccionado == null) {
                return;
            }

            vista.saborBox.setSelectedItem(heladoSeleccionado.getSabor());
            vista.numBolasInt.setValue(heladoSeleccionado.getNumBolas());
            vista.toppingsTxt.setText(heladoSeleccionado.getToppings());
            vista.fechaCompraDatePicker.setDate(heladoSeleccionado.getFechaCompra());
            vista.precioTxt.setText(String.valueOf(heladoSeleccionado.getPrecio()));

            if(heladoSeleccionado instanceof HeladoTarrina) {
                vista.tarrinaRadioButton.doClick();
                vista.tamanoBox.setSelectedItem(String.valueOf(((HeladoTarrina) heladoSeleccionado).getTamano()));
            } else if (heladoSeleccionado instanceof HeladoCono){
                vista.conoRadioButton.doClick();
                vista.tamanoBox.setSelectedItem(String.valueOf(((HeladoCono) heladoSeleccionado).getTipoCono()));
            }
        }
    }

    public void precioAleatorio() {
        int numero = (int) (Math.random() *10);
        vista.precioTxt.setText(String.valueOf(numero));
    }

    private void max3Bolas() {
        vista.numBolasInt.setModel(new SpinnerNumberModel(1, 1, 3, 1));
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}