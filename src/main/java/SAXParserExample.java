import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXParserExample {

    public static void main(String[] args) {
        try {
            // Crear el SAXParserFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();

            // Crear el SAXParser
            SAXParser saxParser = factory.newSAXParser();

            // Donde está localizado el Personas.xml
            String xmlFilePath = "src/main/resources/Personas.xml";

            // Create a handler to handle SAX events
            DefaultHandler handler = new DefaultHandler() {
                boolean bNombre = false;
                boolean bEdad = false;

                /**
                 * Método startElement: Este método se llama cuando se encuentra una etiqueta de apertura.
                 * @param uri para para
                 * @param localName: para pasar el nombre. Por ejemplo, si tienes un elemento como <nombre>, el nombre local sería "nombre".
                 * @param qName: el nombre de los elementos. Por ejemplo, si tienes un elemento como <nombre> y pertenece a un espacio de nombres con el prefijo "ns", el qName sería "ns:nombre". Si no se utiliza ningún espacio de nombres, qName es lo mismo que localName.
                 * @param attributes: contiene los atributos asociados con el elemento XML.
                 * @throws SAXException: Evita la excepción de sax
                 */
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException {
                    if (qName.equalsIgnoreCase("nombre")) {
                        bNombre = true;
                    } else if (qName.equalsIgnoreCase("edad")) {
                        bEdad = true;
                    }
                }

                /**
                 * Método characters: Este método se llama cuando se encuentran los caracteres entre las etiquetas de apertura y cierre.
                 * @param ch The characters.
                 * @param start The start position in the character array.
                 * @param length The number of characters to use from the
                 *               character array.
                 * @throws SAXException
                 */
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (bNombre) {
                        System.out.println("Nombre: " + new String(ch, start, length));
                        bNombre = false;
                    } else if (bEdad) {
                        System.out.println("Edad: " + new String(ch, start, length));
                        bEdad = false;
                    }
                }
            };

            // Analizar el archivo XML usando el controlador (osea, el handler)
            saxParser.parse(xmlFilePath, handler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
