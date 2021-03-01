package screens;

import accounts.Account;
import javax.swing.*;

public class AccountScreen extends Screen {
    private Account currentUser;

    public AccountScreen(JFrame frame, Account currentUser){
        super(frame);
        this.currentUser = currentUser;
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Account currentUser) {
        this.currentUser = currentUser;
    }
}
