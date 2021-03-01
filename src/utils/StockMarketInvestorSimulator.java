package utils;

import accounts.Account;
import accounts.User;
import screens.Login;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class StockMarketInvestorSimulator
{
    public static void main(String[] args) throws IOException{
        onStart();
    }

    public static void onStart() throws IOException{
        List<Account> list = new ArrayList<>(List.of());
        list.add(new User("test", "test"));
        Database db = Database.readDatabase();
        db.saveToFile();

        Login loginWindow = new Login(db);

        loginWindow.getFrame().setVisible(true);

    }

}
