package accounts;

import stocks.Stock;

import java.util.List;

public class User extends Account
{
    private List<Stock> stocks;
    public double money;

    public User(String username, String password)
    {
        super(username, password, 100, AccountType.USER);
        this.money = 10000;
    }

    public void buyStocks(Stock stock){
        this.stocks.add(stock);
    }

    public double getMoney() {
        return money;
    }

    public int getStocksLength() {
        return stocks.size();
    }
}
