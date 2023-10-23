



import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface
{
    private ICurrencyExchanger iCurrencyExchanger;
    private ICurrencyCollection iCurrencyCollection;
    private Scanner scanner = new Scanner(System.in);


    public void printAll(ICurrencyCollection collection)
    {
        System.out.println(collection.ToString());
    }


    public void setCurrencyCollection(ICurrencyCollection collection)
    {
        this.iCurrencyCollection = collection;
    }


    public void setExchange(ICurrencyExchanger exchanger)
    {
        this.iCurrencyExchanger = exchanger;
    }


    public Currency stringToCurrency(String code)
    {
        Currency object = new Currency();
        object.setCode(code);
        return iCurrencyCollection.getCurrencyByCode(object);
    }

    private String parseWithMessageString(String label)
    {
        System.out.println(label);
        String temp;
        try
        {
            temp = scanner.next();
        }
        catch (Exception exception)
        {
            System.err.print("Niepoprawne dane!\n");
            temp = parseWithMessageString(label);
        }
        return temp.toUpperCase();
    }

    private double parseWithMessageDouble(String label)
    {
        System.out.println(label);
        Double temp;
        try
        {
            temp = scanner.nextDouble();
            if (temp < 0)
            {
                throw new Exception();
            }
        }
        catch (InputMismatchException exception)
        {
            System.err.print("Niepoprawne dane!\n");
            scanner = new Scanner(System.in);
            temp = parseWithMessageDouble(label);
        }
        catch (Exception exception)
        {
            System.err.print("Ilosc pieniedzy nie moze byc ujemna!\n");
            temp = parseWithMessageDouble(label);
        }
        return temp;
    }


    public Currency chooseCurrency(String label)
    {
        return stringToCurrency(parseWithMessageString(label));
    }


    public void showMenu()
    {
        char option;
        while (true)
        {
            System.out.println("Wybierz opcje:");
            System.out.println("1 - Wymiana walut");
            System.out.println("2 - Wypisanie walut");
            System.out.println("3 - Zamkniecie programu");

            option = scanner.next().charAt(0);
            switch (option)
            {
                case '1':
                    exchange();
                    break;
                case '2':
                    printAll(iCurrencyCollection);
                    break;
                case '3':
                    System.out.println("Zamykam program");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nie ma takiej opcji");
            }
        }
    }


    public void exchange()
    {
        Currency sourceCurrency = chooseCurrency("Podaj kod waluty ktora chcesz wymienic: \n");
        if (sourceCurrency == null)
        {
            System.err.print("Nie ma takiej waluty!\n");
            exchange();
        }
        else
        {

            Currency finalCurrency = chooseCurrency("Podaj kod waluty na ktora chcesz wymienic: \n");
            if (finalCurrency == null)
            {
                System.err.print("Nie ma takiej waluty!\n");
                exchange();
            }
            else
            {
                double amount = parseWithMessageDouble("Podaj kwote ktora chcesz wymienic: \n");
                System.out.println("Wymieniono na: " + iCurrencyExchanger.exchange(sourceCurrency, finalCurrency, amount) + " " + finalCurrency.getCode());
            }
        }
    }
}
