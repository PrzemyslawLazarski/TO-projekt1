

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class CurrencyCollectionProvider implements ICurrencyCollectionProvider
{
    @Override
    public void provider(String input, ICurrencyCollection output) throws IOException, SAXException, ParserConfigurationException
    {
        Document doc = inputToDocument(input);
        XMLToCurrencyList(doc, output);
    }

    private String getStringByName(Element element, String name)
    {
        return element.getElementsByTagName(name).item(0).getTextContent();
    }

    private double getDoubleByName(Element element, String name)
    {
        return Double.parseDouble(getStringByName(element, name).replace(",", "."));
    }

    private Currency elementToICurrency(Element element)
    {
        Currency currency = new Currency();
        String code = getStringByName(element, "kod_waluty");
        String name = getStringByName(element, "nazwa_waluty");
        double factor = getDoubleByName(element, "przelicznik");
        double rate = getDoubleByName(element, "kurs_sredni");
        currency.setCode(code);
        currency.setName(name);
        currency.setFactor(factor);
        currency.setRate(rate);
        return currency;
    }



    private void XMLToCurrencyList(Document doc, ICurrencyCollection output)
    {
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("pozycja");
        Node temp;


        for (int i = 0; i < nodeList.getLength(); i++)
        {
            temp = nodeList.item(i);
            if (temp.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) temp;
                Currency tempCurrency = this.elementToICurrency(eElement);
                output.getCurrencyList().add(tempCurrency);
            }
        }
    }

    private Document inputToDocument(String input) throws ParserConfigurationException, IOException, SAXException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        StringReader reader = new StringReader(input);
        InputSource inputSource = new InputSource(reader);
        Document document = builder.parse(inputSource);
        return document;
    }
}
