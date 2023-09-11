import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    public static void main(String[] args) {
        try {
            String APIUrl = "https://currencylayer.com/";
            String exchangeRatesData = fetchExchangeRates(APIUrl);
            Map<String, Double> exchangeRates = parseExchangeRates(exchangeRatesData);
            String baseCurrency = getUserInput("Enter base currency: ");
            String targetCurrency = getUserInput("Enter target currency: ");
            double amount = Double.parseDouble(getUserInput("Enter the amount to convert: "));
            double exchangeRate = exchangeRates.get(targetCurrency);
            double convertedAmount = amount * exchangeRate;
            System.out.printf("%.2f %s is equal to %.2f %s%n",
                    amount, baseCurrency, convertedAmount, targetCurrency);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String fetchExchangeRates(String APIUrl) throws Exception {
        URL url = new URL(APIUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }

    private static Map<String, Double> parseExchangeRates(String exchangeRatesData) {
        Map<String, Double> exchangeRates = new HashMap<>();
        exchangeRates.put("INR", 1.0);
        exchangeRates.put("USD", 0.012);
        exchangeRates.put("EUR", 0.011);
        exchangeRates.put("MVR", 0.19);
        exchangeRates.put("JPY", 0.57);
        return exchangeRates;
    }

    private static String getUserInput(String message) throws Exception {
        System.out.print(message);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
}