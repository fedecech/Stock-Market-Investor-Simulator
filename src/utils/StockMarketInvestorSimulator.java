package utils;

import accounts.Account;
import accounts.User;
import screens.Login;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StockMarketInvestorSimulator
{
    public static void main(String[] args) throws IOException{
        onStart();
    }

    public static void onStart() throws IOException{
        Database db = Database.readDatabase();
        Login loginWindow = new Login(db);

        loginWindow.getFrame().setVisible(true);

    }

}
