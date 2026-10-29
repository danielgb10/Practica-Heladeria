package com.danielgib.practicaheladeria.gui;

import com.danielgib.practicaheladeria.base.Helado;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Ventana {
    public JPanel panel1;
    public JButton nuevoHeladoButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JList list1;
    public DatePicker fechaCompraDatePicker;
    public JTextField toppingsTxt;
    public JSpinner numBolasInt;
    public JTextField precioTxt;
    public JRadioButton conoRadioButton;
    public JRadioButton tarrinaRadioButton;
    public JRadioButton conAzucarRadioButton;
    public JRadioButton sinAzucarRadioButton;
    public JComboBox saborBox;
    public JTextArea textArea1;
    public JComboBox tamanoBox;

    public JFrame frame;
    public  DefaultListModel<Helado> dlmHelado;

    public Ventana() {
        frame = new JFrame("Ventana");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        initComponents();
    }

    public void initComponents() {
        dlmHelado = new DefaultListModel<Helado>();
        list1.setModel(dlmHelado);
    }


}
