package com.danielgib.practicaheladeria.gui;

import com.danielgib.practicaheladeria.base.Helado;
import com.danielgib.practicaheladeria.base.HeladoCono;
import com.danielgib.practicaheladeria.base.HeladoTarrina;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class HeladoModelo {
    private ArrayList<Helado> listaHelados;

    public HeladoModelo() {
        this.listaHelados = new ArrayList<>();
    }

    public void altaHeladoCono(String sabor, String toppings, LocalDate fechaCompra, double precio, boolean sinAzucar, int numBolas, String tipoCono) {
        HeladoCono nuevoHeladoCono = new HeladoCono(sabor, toppings, fechaCompra, precio, sinAzucar, numBolas, tipoCono);
        listaHelados.add(nuevoHeladoCono);
    }

    public void altaHeladoTarrina(String sabor, String toppings, LocalDate fechaCompra, double precio, boolean sinAzucar, int numBolas, String tamano) {
        HeladoTarrina nuevoHeladoTarrina = new HeladoTarrina(sabor, toppings, fechaCompra, precio, sinAzucar, numBolas, tamano);
        listaHelados.add(nuevoHeladoTarrina);
    }

    //exportar
    public void exportarXML(File fichero) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        Element raiz = documento.createElement("helado");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoHelados = null;
        Element nodoDatos = null;
        Text texto = null;


        for (Helado unHelado : listaHelados) {
            if(unHelado instanceof HeladoTarrina) {
                nodoHelados = documento.createElement("Helado Tarrina");
            } else {
                nodoHelados = documento.createElement("Helado Cono");
            }
            raiz.appendChild(nodoDatos);

            //dentro de la etiqueta helado
            nodoDatos = documento.createElement("sabor");
            nodoHelados.appendChild(nodoDatos);
            texto = documento.createTextNode(unHelado.getSabor());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("numeroBolas");
            nodoHelados.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unHelado.getNumBolas()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("topppings");
            nodoHelados.appendChild(nodoDatos);
            texto = documento.createTextNode(unHelado.getToppings());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fechaCompra");
            nodoHelados.appendChild(nodoDatos);
            texto = documento.createTextNode(unHelado.getFechaCompra().toString());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("precio");
            nodoHelados.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unHelado.getPrecio()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("sinAzucar");
            nodoHelados.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unHelado.isSinAzucar()));
            nodoDatos.appendChild(texto);

            //ponemoss los atributos diferentes
            if(unHelado instanceof HeladoTarrina) {
                nodoDatos = documento.createElement("tamano");
                nodoHelados.appendChild(nodoDatos);
                texto = documento.createTextNode(((HeladoTarrina) unHelado).getTamano());
                nodoDatos.appendChild(texto);
            } else if(unHelado instanceof HeladoCono) {
                nodoDatos = documento.createElement("tipocono");
                nodoDatos.appendChild(nodoDatos);
                texto = documento.createTextNode(((HeladoCono) unHelado).getTipoCono());
                nodoDatos.appendChild(texto);
            }
        }


    }

    //importar

}

