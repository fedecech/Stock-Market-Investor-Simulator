package screens;

import utils.Database;

import javax.swing.*;

public class AuthScreen extends Screen {
    private Database database;

    public AuthScreen(Database database, JFrame frame){
        super(frame);
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }
}
