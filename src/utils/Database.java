package utils;

import accounts.Account;
import accounts.AccountType;
import accounts.Admin;
import accounts.User;
import com.google.gson.Gson;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Database
{
    private List<Account> accounts;

    public Database(List<Account> accounts)
    {
        this.accounts = accounts;
    }

    public List<Account> getAccounts(){
        return this.accounts;
    }

    public Account findAccountByUsername(String username){

        for(int i=0; i<this.accounts.size(); i++){
            if(this.accounts.get(i).getUsername().equals(username)){
                if (this.accounts.get(i).getType().equals(AccountType.USER)){
                    return new User(accounts.get(i).getUsername(), accounts.get(i).getPassword());
                }else if(this.accounts.get(i).getType().equals(AccountType.ADMIN)){
                    return new Admin(accounts.get(i).getUsername(), accounts.get(i).getPassword());
                }
            }
        }

        return null;
    }
    public void saveToFile() throws IOException{
        Gson gson = new Gson();

        BufferedWriter bf = new BufferedWriter(new FileWriter("database.json"));

        String jsonStr = gson.toJson(this);

        bf.write(jsonStr);
        bf.close();
    }

    public void addAccount(Account newAccount) throws IOException {
        this.accounts.add(newAccount);
        saveToFile();
    }

    public static Database readDatabase() throws IOException{
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("database.json"));
        String line =  br.lines().collect(Collectors.joining());

        Database db = gson.fromJson(line, Database.class);

        return db;
    }
}
