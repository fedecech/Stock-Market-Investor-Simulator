package accounts;

abstract public class Account
{
    private String username;
    private String password;
    private int maxStocks;
    private AccountType type;

    public Account(String username, String password, int maxStocks, AccountType type)
    {
        this.username = username;
        this.password = password;
        this.maxStocks = maxStocks;
        this.type = type;
    }

    public int getMaxStocks(){
        return this.maxStocks;
    }

    public String getUsername(){
        return  this.username;
    }

    public String getPassword() {
        return password;
    }

    public AccountType getType() {
        return type;
    }

    public boolean signIn(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)){
            return true;
        }
        return false;
    }
}