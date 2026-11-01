package com.danielgib.practicaheladeria.gui;

import com.danielgib.practicaheladeria.base.Helado;
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

    private void addActionListener(ActionListener listener) {
        vista.nuevoButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.tarrinaRadioButton.addActionListener(listener);
        vista.conoRadioButton.addActionListener(listener);
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
        ultimaRutaExportacion = new File(configuracion.getProperty("ultumaRutaExportacion"));
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportacion) {
        this.ultimaRutaExportacion = ultimaRutaExportacion;
    }

    private void guardarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultumaRutaExportacion", ultimaRutaExportacion.getAbsolutePath());
        configuracion.store(new PrintWriter("helados.conf"), "Datos configuracion helados");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                if (hayCamposVacios()) {
                    Util.mensajeError("Error: Los siguientes campos no pueden estar vacios: " +
                            "n\tamaño\nsabor\numero bolas\ntoppings\nfecha compra\n" + vista.tamanoBox.getSelectedItem());
                    break;
                }

                if(vista.tarrinaRadioButton.isSelected()) {
                    modelo.altaHeladoTarrina(
                            vista.saborBox.getSelectedItem().toString(),
                            vista.toppingsTxt.getText(),
                            vista.fechaCompraDatePicker.getDate(),
                            Double.parseDouble(vista.fechaCompraDatePicker.getText()),
                            Boolean.parseBoolean(vista.sinAzucarRadioButton.getText()),
                            (int) vista.numBolasInt.getValue(),
                            vista.tamanoBox.getSelectedItem().toString());
                } else {
                    modelo.altaHeladoCono(
                            vista.saborBox.getSelectedItem().toString(),
                            vista.toppingsTxt.getText(),
                            vista.fechaCompraDatePicker.getDate(),
                            Double.parseDouble(vista.fechaCompraDatePicker.getText()),
                            Boolean.parseBoolean(vista.sinAzucarRadioButton.getText()),
                            (int) vista.numBolasInt.getValue(),
                            vista.tamanoBox.getSelectedItem().toString());
                }
                limpiarCampos();
                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportacion, "Archivos XML", "xml");
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
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportacion, "Archivos XML", "xml");
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
            case "Helado Tarrina":
                vista.tamanoLbl.setText("Tamaño");
                break;
            case "Helado Cono":
                vista.tamanoLbl.setText("Tipo Cono");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

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
