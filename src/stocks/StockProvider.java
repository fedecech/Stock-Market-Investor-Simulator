package stocks;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StockProvider{
    private Stock stock;
    private Function function;
    private String APIKEY;
    private String baseURL;
    private String symbol;

    public StockProvider(){
        this.APIKEY = "6EBI8OYFSBKRK03K";
        this.baseURL = "https://www.alphavantage.co/";
        this.symbol = "AAPL";
        this.function = Function.TIME_SERIES_DAILY;
        this.stock = retrieveStocks();
    }

    public String createURL(){
        return this.baseURL + "query?function=" + this.function + "&symbol=" + this.symbol + "&apikey=" + this.APIKEY;
    }

    public Stock getStock() {
        return stock;
    }

    public Stock retrieveStocks(){
        Stock stock = null;
        try{
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(createURL())
                    .build();

            Response response = client.newCall(request).execute();
            Gson gson = new Gson();
            Map<String,Map<String, Map<String, String>>> map = gson.fromJson(response.body().string(), Map.class);
            List<Price> prices = new ArrayList<>(List.of());
            Metadata metadata = null;
             for (Map.Entry<String, Map<String, Map<String, String>>> entry : map.entrySet()) {
                String key = entry.getKey();
                Map<String, Map<String, String>> value = entry.getValue();

                if(key.equals("Meta Data")){
                    Map<String, Map<String, String>> metadataDoubleMap = value;
                    Map<String, String> metadataMap = gson.fromJson(gson.toJson(metadataDoubleMap), Map.class);

                    String info = metadataMap.get("1. Information");
                    String ticker = metadataMap.get("2. Symbol");
                    String last_refreshed = metadataMap.get("3. Last Refreshed");
                    String output_size = metadataMap.get("4. Output Size");
                    String time_zone = metadataMap.get("5. Time Zone");

                    metadata = new Metadata(info, ticker, last_refreshed, output_size, time_zone);
                }else if(key.contains("Time Series")){

                    Map<String,Map<String, String>> newMap = gson.fromJson(gson.toJson(value), Map.class);
                    for (Map.Entry<String, Map<String, String>> newEntry : newMap.entrySet()) {
                        String date = newEntry.getKey();
                        Map<String, String> details = newEntry.getValue();

                        String open = details.get("1. open");
                        String high = details.get("2. high");
                        String low = details.get("3. low");
                        String close = details.get("4. close");
                        String volume = details.remove("5. volume");

                        Price price = new Price(date, open, high, low, close, volume);

                        prices.add(price);
                    }
                }
            }
            stock = new Stock(metadata, prices);

        }catch (Exception e){}
        return stock;
    }
}



