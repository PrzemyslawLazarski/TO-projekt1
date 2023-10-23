

import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface ICurrencyCollectionProvider {
    public void provider(String input, ICurrencyCollection output) throws IOException, SAXException, ParserConfigurationException;
}
