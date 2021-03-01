package stocks;

import java.util.List;

/**
 * Write a description of class stocks.Stock here.
 *
 * @author (Cecchinato Federico)
 * @version (23/02/2021)
 */
public class Stock
{
    private Metadata metadata;
    private List<Price> pricesHistory;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Price> getPricesHistory() {
        return pricesHistory;
    }

    public void setPricesHistory(List<Price> pricesHistory) {
        this.pricesHistory = pricesHistory;
    }

    public Stock(Metadata metadata, List<Price> pricesHistory)
    {
        this.metadata = metadata;
        this.pricesHistory = pricesHistory;
    }
}




