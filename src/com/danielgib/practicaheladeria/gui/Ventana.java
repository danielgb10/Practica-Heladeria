package com.danielgib.practicaheladeria.gui;

import com.danielgib.practicaheladeria.base.Helado;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Ventana {
    public JPanel panel1;
    public JRadioButton conoRadioButton;
    public JRadioButton tarrinaRadioButton;
    public JTextField saborTxt;
    public JRadioButton conAzucarRadioButton;
    public JRadioButton sinAzucarRadioButton;
    public JButton nuevoHeladoButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JTextField precioTxt;
    public JTextField stockTxt;
    public JList list1;
    public DatePicker fechaCompra;
    public JTextArea valoracionText;

    public JFrame frame;
    public  DefaultListModel<Helado> dlmHelado;

    public Ventana() {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
