
import java.io.IOException;

public interface ICurrencyProvider {
    public String acquireData(String address) throws IOException;
}