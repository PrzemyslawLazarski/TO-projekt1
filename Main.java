

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import org.xml.sax.SAXException;



public class Main
{
    static ICurrencyExchanger exchanger;
    static UserInterface ui;
    static ICurrencyProvider provider;
    static ICurrencyCollection collection;
    static ICurrencyCollectionProvider collectionProvider;

    public static void main(String[] args)
    {
        collectionProvider = new CurrencyCollectionProvider();
        collection = new CurrencyCollection();
        ui = new UserInterface();
        provider = new CurrencyProvider();
        exchanger = CurrencyExchanger.getInstance();


        try
        {
            String result = provider.acquireData("https://www.nbp.pl/kursy/xml/lasta.xml");
            collectionProvider.provider(result, collection);

            ui.setCurrencyCollection(collection);
            ui.setExchange(exchanger);

            ui.showMenu();

        } catch (IOException exception)
        {
            exception.printStackTrace();
        } catch (SAXException exception)
        {
            exception.printStackTrace();
        } catch (ParserConfigurationException exception)
        {
            exception.printStackTrace();
        }
    }
}