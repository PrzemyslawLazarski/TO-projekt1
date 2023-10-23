public class Currency
{
    private String name;
    private String code;
    private double factor;
    private double rate;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public double getFactor()
    {
        return factor;
    }

    public void setFactor(double factor)
    {
        this.factor = factor;
    }

    public double getRate()
    {
        return rate;
    }

    public void setRate(double rate)
    {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Currency currency = (Currency) obj;
        return this.code.equals(currency.getCode());
    }

}
