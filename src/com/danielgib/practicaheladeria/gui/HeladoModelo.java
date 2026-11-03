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

    public ArrayList<Helado> obtenerHelados() {
        return listaHelados;
    }

    public void altaHeladoCono(String tipoCono, String sabor, int numBolas, String toppings, LocalDate fechaCompra, int precio, boolean azucar) {
        HeladoCono nuevoHeladoCono = new HeladoCono(tipoCono, sabor, numBolas, toppings, fechaCompra, precio, azucar);
        listaHelados.add(nuevoHeladoCono);
    }

    public void altaHeladoTarrina(String tamano,String sabor, int numBolas, String toppings, LocalDate fechaCompra, int precio, boolean azucar) {
        HeladoTarrina nuevoHeladoTarrina = new HeladoTarrina(tamano, sabor, numBolas, toppings, fechaCompra, precio, azucar);
        listaHelados.add(nuevoHeladoTarrina);
    }

    public boolean existeSabor(String sabor) {
        for (Helado unHelado : listaHelados) {
            if(unHelado.getSabor().equals(sabor)) {
                return true;
            }
        }
        return false;
    }

    //exportar
    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        //nodo raiz
        Element raiz = documento.createElement("helado");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoHelados = null;
        Element nodoDatos = null;
        Text texto = null;


        for (Helado unHelado : listaHelados) {
            if(unHelado instanceof HeladoTarrina) {
                nodoHelados = documento.createElement("HeladoTarrina");
            } else if(unHelado instanceof  HeladoCono) {
                nodoHelados = documento.createElement("HeladoCono");
            }
            raiz.appendChild(nodoHelados);

            //dentro de la etiqueta helado
            nodoDatos = documento.createElement("sabor");
            nodoHelados.appendChild(nodoDatos);
            texto = documento.createTextNode(unHelado.getSabor());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("numeroBolas");
            nodoHelados.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unHelado.getNumBolas()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("toppings");
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

            nodoDatos = documento.createElement("azucar");
            nodoHelados.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unHelado.getAzucar()));
            nodoDatos.appendChild(texto);

            //ponemoss los atributos diferentes
            if(unHelado instanceof HeladoTarrina) {
                nodoDatos = documento.createElement("tamano");
                nodoHelados.appendChild(nodoDatos);
                texto = documento.createTextNode(((HeladoTarrina) unHelado).getTamano());
                nodoDatos.appendChild(texto);
            } else if(unHelado instanceof HeladoCono) {
                nodoDatos = documento.createElement("tipoCono");
                nodoHelados.appendChild(nodoDatos);
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
        NodeList listaElementos = documento.getElementsByTagName("*");
        for (int i=0; i<listaElementos.getLength(); i++) {
            Element nodoHelado = (Element) listaElementos.item(i);

            if(nodoHelado.getTagName().equals("HeladoTarrina")) {
                nuevoHeladoTarrina = new HeladoTarrina();


                nuevoHeladoTarrina.setSabor(nodoHelado.getChildNodes().item(0).getTextContent());
                nuevoHeladoTarrina.setNumBolas(Integer.parseInt(nodoHelado.getChildNodes().item(1).getTextContent()));
                nuevoHeladoTarrina.setToppings(nodoHelado.getChildNodes().item(2).getTextContent());
                nuevoHeladoTarrina.setFechaCompra(LocalDate.parse(nodoHelado.getChildNodes().item(3).getTextContent()));
                nuevoHeladoTarrina.setPrecio(Integer.parseInt(nodoHelado.getChildNodes().item(4).getTextContent()));
                nuevoHeladoTarrina.setAzucar(Boolean.parseBoolean(nodoHelado.getChildNodes().item(5).getTextContent()));
                nuevoHeladoTarrina.setTamano(nodoHelado.getChildNodes().item(6).getTextContent());

                listaHelados.add(nuevoHeladoTarrina);

            } else {
                if(nodoHelado.getTagName().equals("HeladoCono")) {
                    nuevoHeladoCono = new HeladoCono();

                    nuevoHeladoTarrina.setSabor(nodoHelado.getChildNodes().item(0).getTextContent());
                    nuevoHeladoTarrina.setNumBolas(Integer.parseInt(nodoHelado.getChildNodes().item(1).getTextContent()));
                    nuevoHeladoTarrina.setToppings(nodoHelado.getChildNodes().item(2).getTextContent());
                    nuevoHeladoTarrina.setFechaCompra(LocalDate.parse(nodoHelado.getChildNodes().item(3).getTextContent()));
                    nuevoHeladoTarrina.setPrecio(Integer.parseInt(nodoHelado.getChildNodes().item(4).getTextContent()));
                    nuevoHeladoTarrina.setAzucar(Boolean.parseBoolean(nodoHelado.getChildNodes().item(5).getTextContent()));
                    nuevoHeladoCono.setTipoCono(nodoHelado.getChildNodes().item(6).getTextContent());

                    listaHelados.add(nuevoHeladoCono);
                }
            }


        }
    }

    public void editarHelado(int posicion, Helado modificar) {
        listaHelados.set(posicion, modificar);
    }

    public void eliminarHelado(Helado eliminar) {
        listaHelados.remove(eliminar);
    }
}


