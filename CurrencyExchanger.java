

public class CurrencyExchanger implements ICurrencyExchanger
{
    private static CurrencyExchanger currencyExchanger;

    public static CurrencyExchanger getInstance()
    {
        if (currencyExchanger == null)
        {
            currencyExchanger = new CurrencyExchanger();
        }
        return currencyExchanger;
    }

    @Override
    public double exchange(Currency firstCurrency, Currency secondCurrency, double amount)
    {
        double result = (Math.round((amount / firstCurrency.getFactor() * firstCurrency.getRate() / secondCurrency.getRate() * secondCurrency.getFactor()) * 1000)) / 1000.0;

        return result;
    }
}
