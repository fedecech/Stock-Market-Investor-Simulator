package screens;

import accounts.Account;
import accounts.Admin;
import accounts.User;
import javax.swing.*;
import java.awt.*;


public class Home extends AccountScreen {
    private JPanel panel;
    private GridBagConstraints gbc;
    private JLabel helloLabel;
    private JLabel statsLabel;

    public Home(Account currentUser) {
        super(new JFrame(), currentUser);
        initialize();
    }

    public void initialize() {
        this.panel = new JPanel();
        this.gbc = new GridBagConstraints();
        this.helloLabel = new JLabel("Hello " + getCurrentUser().getUsername());
        this.statsLabel = new JLabel("");

        if(getCurrentUser() instanceof User){
            this.statsLabel.setText("Money" + (((User) getCurrentUser()).getMoney()));
        }else if(getCurrentUser() instanceof Admin){
            this.statsLabel.setText("Admin");
        }


        this.gbc.weightx = 1;
        this.gbc.weighty = 1;
        setLayout(new GridBagLayout());
        setBounds(100, 100, 900, 600);
        setResizable(false);
        setTitle("Stock Market Simulator");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(helloLabel,gbc);

        gbc.gridx = 4;
        gbc.gridy = 4;
        add(statsLabel, gbc);
    }
}
