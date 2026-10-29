package com.danielgib.practicaheladeria.gui;

import com.danielgib.practicaheladeria.base.Helado;
import com.danielgib.practicaheladeria.base.HeladoCono;
import com.danielgib.practicaheladeria.base.HeladoTarrina;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
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
    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
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
        //guardar en el fichero
        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, resultado);
    }

    //importar
    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaHelados = new ArrayList<>();
        HeladoTarrina nuevoHeladoTarrina= null;
        HeladoCono nuevoHeladoCono = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        //recorrer los archivos
        NodeList listaElementos = documento.getElementsByTagName("");
        for (int i=0; i<listaElementos.getLength(); i++) {
            Element nodoHelado = (Element) listaElementos.item(i);

            if(nodoHelado.getTagName().equals("HeladoTarrina")) {
                nuevoHeladoTarrina = new HeladoTarrina();
                nuevoHeladoTarrina.setTamano(nodoHelado.getChildNodes().item(0).getTextContent());
                nuevoHeladoTarrina.setSabor(nodoHelado.getChildNodes().item(1).getTextContent());
                nuevoHeladoTarrina.setNumBolas(Integer.parseInt(nodoHelado.getChildNodes().item(2).getTextContent()));
                nuevoHeladoTarrina.setToppings(nodoHelado.getChildNodes().item(3).getTextContent());
                nuevoHeladoTarrina.setFechaCompra(LocalDate.parse(nodoHelado.getChildNodes().item(4).getTextContent()));
                nuevoHeladoTarrina.setPrecio(Double.parseDouble(nodoHelado.getChildNodes().item(5).getTextContent()));
                nuevoHeladoTarrina.setSinAzucar(Boolean.parseBoolean(nodoHelado.getChildNodes().item(6).getTextContent()));
                listaHelados.add(nuevoHeladoTarrina);

            } else if(nodoHelado.getTagName().equals("HeladoCono")) {
                nuevoHeladoCono = new HeladoCono();
                nuevoHeladoCono.setTipoCono(nodoHelado.getChildNodes().item(0).getTextContent());
                nuevoHeladoCono.setSabor(nodoHelado.getChildNodes().item(1).getTextContent());
                nuevoHeladoCono.setNumBolas(Integer.parseInt(nodoHelado.getChildNodes().item(2).getTextContent()));
                nuevoHeladoCono.setToppings(nodoHelado.getChildNodes().item(3).getTextContent());
                nuevoHeladoCono.setFechaCompra(LocalDate.parse(nodoHelado.getChildNodes().item(4).getTextContent()));
                nuevoHeladoCono.setPrecio(Double.parseDouble(nodoHelado.getChildNodes().item(5).getTextContent()));
                nuevoHeladoCono.setSinAzucar(Boolean.parseBoolean(nodoHelado.getChildNodes().item(6).getTextContent()));
                listaHelados.add(nuevoHeladoCono);
            }
        }
    }
    }


