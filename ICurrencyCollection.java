

import java.util.List;

public interface ICurrencyCollection {
    List<Currency> getCurrencyList();
    String ToString();
    Currency getCurrencyByCode(Currency currency);
}