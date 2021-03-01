package screens;

import accounts.Account;
import accounts.User;
import javax.swing.*;
import java.awt.*;


public class Home extends AccountScreen {
    private JPanel panel;
    private GridBagConstraints gbc;
    private JLabel helloLabel;
    private JLabel statsLabel;

    public Home(Account currentUser) {
        super(new JFrame(), currentUser instanceof User ? new User(currentUser.getUsername(), currentUser.getPassword()) : currentUser);
        initialize();
    }

    public void initialize() {
        this.panel = new JPanel();
        this.gbc = new GridBagConstraints();
        this.helloLabel = new JLabel("Hello " + super.getCurrentUser().getUsername());
        this.statsLabel = new JLabel("");

        if(super.getCurrentUser() instanceof User){
            this.statsLabel.setText("Money" + (((User) super.getCurrentUser()).getMoney()));
        }else {
            this.statsLabel.setText("Admin");
        }


        this.gbc.weightx = 1;
        this.gbc.weighty = 1;
        super.getFrame().setLayout(new GridBagLayout());

        super.getFrame().setBounds(100, 100, 900, 600);
        super.getFrame().setResizable(false);
        super.getFrame().setTitle("Stock Market Simulator");
        super.getFrame().setLocationRelativeTo(null);
        super.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super.getFrame().add(panel);

        gbc.gridx = 1;
        gbc.gridy = 1;
        super.getFrame().add(helloLabel,gbc);

        gbc.gridx = 4;
        gbc.gridy = 4;
        super.getFrame().add(statsLabel, gbc);
    }
}
