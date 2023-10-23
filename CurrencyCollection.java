
import java.util.ArrayList;
import java.util.List;

public class CurrencyCollection implements ICurrencyCollection
{
    private List<Currency> list;

    public CurrencyCollection()
    {
        list = new ArrayList<>();
    }

    @Override
    public String ToString()
{
    String temp = "";
    for (Currency x : list)
    {
        temp += x.getCode() + " -> Przelicznik: " + x.getFactor() + "  Kurs: " + x.getRate() + "\n";
    }
    return temp;
}

    @Override
    public List<Currency> getCurrencyList()
    {
        return this.list;
    }

    @Override
    public Currency getCurrencyByCode(Currency currency)
    {
        for (Currency x : list)
        {
            if (currency.equals(x))
            {
                return x;
            }
        }
        return null;
    }
}

